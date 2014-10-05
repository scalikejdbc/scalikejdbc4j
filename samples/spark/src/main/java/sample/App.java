package sample;

import sample.dao.CompanyDao;
import sample.transformer.JSONResponse;
import scalikejdbc4j.ConnectionPool;
import scalikejdbc4j.DB;
import spark.Request;

import java.util.Optional;

import static spark.Spark.*;

public class App {

    // TODO logging configuration

    public static void main(String[] args) throws Exception {
        // initialize DB
        Class.forName("org.h2.Driver");
        ConnectionPool.singleton("jdbc:h2:mem:sample", "sa", "sa");
        DB.autoCommit((s) -> {
            new CompanyDao(s).createTable();
        });

        before((req, res) -> {
            res.header("Content-Type", "application/json; charset=utf-8");
        });

        get("/", (req, res) -> {
            res.redirect("/companies");
            return null;
        });

        get("/companies", (req, res) -> DB.withReadOnlySession((s) -> {
            return new CompanyDao(s).findAll();
        }), new JSONResponse());

        get("/companies/:id", (req, res) -> DB.withReadOnlySession((s) -> {
            return new CompanyDao(s).find(getId(req));
        }), new JSONResponse());

        post("/companies", (req, res) -> DB.withLocalTx((s) -> {
            return new CompanyDao(s).create(Optional.ofNullable(req.queryParams("name")));
        }), new JSONResponse());

        put("/companies/:id", (req, res) -> DB.withLocalTx((s) -> {
            CompanyDao dao = new CompanyDao(s);
            return dao.find(getId(req)).map((c) -> {
                c.setName(Optional.of(req.queryParams("name")));
                dao.save(c);
                return c;
            });
        }), new JSONResponse());

        delete("/companies/:id", (req, res) -> DB.withLocalTx((s) -> {
            new CompanyDao(s).delete(getId(req));
            return null;
        }), new JSONResponse());
    }

    // TODO 400 response
    static Long getId(Request req) {
        return Long.valueOf(req.params(":id"));
    }

}
