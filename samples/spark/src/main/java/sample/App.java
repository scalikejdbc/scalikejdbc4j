package sample;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sample.dao.CompanyDao;
import sample.dao.ProgrammerDao;
import sample.transformer.JSONResponseTransformer;
import scalikejdbc.DBSession;
import scalikejdbc4j.DataSourceConnectionPool;
import scalikejdbc4j.AutoSession;
import scalikejdbc4j.ConnectionPool;
import scalikejdbc4j.DB;
import scalikejdbc4j.GlobalSettings;
import scalikejdbc4j.globalsettings.LogLevel;
import scalikejdbc4j.globalsettings.LoggingSQLAndTimeSettings;
import spark.Request;
import spark.ResponseTransformer;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Set;

import static spark.Spark.*;

public class App {

    private static final ResponseTransformer asJSON = new JSONResponseTransformer();

    // --------------
    // Invoke HTTP server (localhost:4567)
    // --------------
    public static void main(String[] args) throws Exception {
        initQueryLogLevel();
        initDatabase();

        final DBSession autoCommit = AutoSession.autoCommit();
        final DBSession readOnly = AutoSession.readOnly();

        // --------------
        // before/after
        // --------------
        after((req, res) -> {
            res.type("application/json; charset=utf-8");
        });

        // --------------
        // root
        // --------------
        get("/", (req, res) -> {
            res.redirect("/programmers");
            return null;
        });

        // --------------
        // companies
        // --------------

        get("/companies", (req, res) -> {
            return new CompanyDao(readOnly).findAll();
        }, asJSON);

        get("/companies/:id", (req, res) -> {
            return getId(req).flatMap((id) -> new CompanyDao(readOnly).find(id));
        }, asJSON);

        post("/companies", (req, res) -> {
            return new CompanyDao(autoCommit).create(toOptional(req.queryParams("name")));
        }, asJSON);

        put("/companies/:id", (req, res) -> {
            CompanyDao dao = new CompanyDao(autoCommit);
            return getId(req).flatMap((id) -> dao.find(id).map((c) -> {
                c.setName(toOptional(req.queryParams("name")));
                dao.save(c);
                return c;
            }));
        }, asJSON);

        delete("/companies/:id", (req, res) -> {
            Optional<Long> id = getId(req);
            if (id.isPresent()) new CompanyDao(autoCommit).delete(id.get());
            return null;
        }, asJSON);

        // --------------
        // programmers
        // --------------

        get("/programmers", (req, res) -> {
            return new ProgrammerDao(autoCommit).findAll();
        }, asJSON);

        get("/programmers/:id", (req, res) -> {
            return getId(req).flatMap((id) -> new ProgrammerDao(readOnly).find(id));
        }, asJSON);

        post("/programmers", (req, res) -> DB.withLocalTx((s) -> {
            return new ProgrammerDao(s).create(
                    req.queryParams("gitHubName"),
                    toOptional(req.queryParams("realName")),
                    toOptionalLong(req.queryParams("companyId"))
            );
        }), asJSON);

        put("/programmers/:id", (req, res) -> DB.withLocalTx((s) -> {
            ProgrammerDao dao = new ProgrammerDao(s);
            return getId(req).flatMap((id) -> dao.find(id).map((c) -> {
                Set<String> paramNames = req.queryParams();
                if (paramNames.contains("gitHubName")) c.setGitHubName(req.queryParams("gitHubName"));
                if (paramNames.contains("realName")) c.setRealName(toOptional(req.queryParams("realName")));
                if (paramNames.contains("companyId")) c.setCompanyId(toOptionalLong(req.queryParams("companyId")));
                dao.save(c);
                return c;
            }));
        }), asJSON);

        delete("/programmers/:id", (req, res) -> DB.withLocalTx((s) -> {
            Optional<Long> id = getId(req);
            if (id.isPresent()) new ProgrammerDao(s).delete(id.get());
            return null;
        }), asJSON);
    }

    // --------------
    // Private methods
    // --------------

    private static void initQueryLogLevel() {
        LoggingSQLAndTimeSettings loggingSettings = new LoggingSQLAndTimeSettings();
        loggingSettings.setLogLevel(LogLevel.INFO);
        GlobalSettings.setLoggingSQLAndTime(loggingSettings);
    }

    private static void initDatabase() throws ClassNotFoundException {
        // initializeConnectionPool();
        initializeConnectionPoolViaSpring();
        DB.autoCommit((s) -> {
            new CompanyDao(s).createTable();
            new ProgrammerDao(s).createTable();
        });
    }

    private static void initializeConnectionPool() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        ConnectionPool.singleton("jdbc:h2:mem:sample", "sa", "sa");
    }

    private static void initializeConnectionPoolViaSpring() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ConnectionPool.singleton(new DataSourceConnectionPool((DataSource) context.getBean("dataSource")));
    }

    private static <T> Optional<T> toOptional(T value) {
        return Optional.ofNullable(value);
    }

    private static Optional<Long> toOptionalLong(String value) {
        try {
            return Optional.ofNullable(Long.valueOf(value));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    private static Optional<Long> getId(Request req) {
        return toOptionalLong(req.params(":id"));
    }

}
