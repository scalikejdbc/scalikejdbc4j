package sample;

import sample.dao.CompanyDao;
import sample.dao.ProgrammerDao;
import sample.transformer.JSONResponseTransformer;
import scalikejdbc4j.ConnectionPool;
import scalikejdbc4j.DB;
import scalikejdbc4j.GlobalSettings;
import scalikejdbc4j.globalsettings.LogLevel;
import scalikejdbc4j.globalsettings.LoggingSQLAndTimeSettings;
import spark.Request;
import spark.ResponseTransformer;

import java.util.Optional;
import java.util.Set;

import static spark.Spark.*;

public class App {

    private static final ResponseTransformer asJSON = new JSONResponseTransformer();

    public static void main(String[] args) throws Exception {
        initQueryLogLevel();
        initDatabase();

        // --------------
        // before/after

        after((req, res) -> {
            res.type("application/json; charset=utf-8");
        });

        // --------------
        // root

        get("/", (req, res) -> {
            res.redirect("/companies");
            return null;
        });

        // --------------
        // companies

        get("/companies", (req, res) -> DB.withReadOnlySession((s) -> {
            return new CompanyDao(s).findAll();
        }), asJSON);

        get("/companies/:id", (req, res) -> DB.withReadOnlySession((s) -> {
            return getId(req).flatMap((id) -> new CompanyDao(s).find(id));
        }), asJSON);

        post("/companies", (req, res) -> DB.withLocalTx((s) -> {
            return new CompanyDao(s).create(toOptional(req.queryParams("name")));
        }), asJSON);

        put("/companies/:id", (req, res) -> DB.withLocalTx((s) -> {
            CompanyDao dao = new CompanyDao(s);
            return getId(req).flatMap((id) -> dao.find(id).map((c) -> {
                c.setName(toOptional(req.queryParams("name")));
                dao.save(c);
                return c;
            }));
        }), asJSON);

        delete("/companies/:id", (req, res) -> DB.withLocalTx((s) -> {
            Optional<Long> id = getId(req);
            if (id.isPresent()) new CompanyDao(s).delete(id.get());
            return null;
        }), asJSON);

        // --------------
        // programmers

        get("/programmers", (req, res) -> DB.withReadOnlySession((s) -> {
            return new ProgrammerDao(s).findAll();
        }), asJSON);

        get("/programmers/:id", (req, res) -> DB.withReadOnlySession((s) -> {
            return getId(req).flatMap((id) -> new ProgrammerDao(s).find(id));
        }), asJSON);

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

    private static void initQueryLogLevel() {
        LoggingSQLAndTimeSettings loggingSettings = new LoggingSQLAndTimeSettings();
        loggingSettings.setLogLevel(LogLevel.INFO);
        GlobalSettings.setLoggingSQLAndTime(loggingSettings);
    }

    private static void initDatabase() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        ConnectionPool.singleton("jdbc:h2:mem:sample", "sa", "sa");

        DB.autoCommit((s) -> {
            new CompanyDao(s).createTable();
            new ProgrammerDao(s).createTable();
        });
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
