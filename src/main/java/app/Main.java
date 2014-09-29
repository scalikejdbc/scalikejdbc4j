package app;

import dao.CompanyDao;
import dao.ProgrammerDao;
import model.Company;
import model.Programmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scalikejdbc4j.ConnectionPool;
import scalikejdbc4j.DB;
import scalikejdbc4j.NamedDB;

import java.util.List;
import java.util.Optional;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        // initialize the default connection pool
        Class.forName("org.h2.Driver");
        ConnectionPool.singleton("jdbc:h2:mem:scalikejdbc", "sa", "");

        Optional<Programmer> aliceWithCompany = DB.localTx((session) -> {
            // initialize DAOs
            ProgrammerDao programmerDao = new ProgrammerDao(session);
            CompanyDao companyDao = new CompanyDao(session);

            // run DDL
            companyDao.createTable();
            programmerDao.createTable();

            // insert data
            Company company = companyDao.create("Typesafe");
            Programmer a = programmerDao.create("Alice", Optional.of(company.getId()));
            Programmer b = programmerDao.create("Bob", Optional.empty());

            // find entities
            programmerDao.findAll().forEach(Main::tapProgrammer);
            Optional<Programmer> alice = programmerDao.findWithCompany(a.getId());
            return alice;
        });
        aliceWithCompany.map(Main::tapProgrammer);

        // initialize other connection pool
        ConnectionPool.add("other", "jdbc:h2:mem:scalikejdbc", "sa", "");

        List<Programmer> programmers = NamedDB.of("other").localTx((session) -> {
            ProgrammerDao programmerDao = new ProgrammerDao(session);
            return programmerDao.findAll();
        });
        programmers.forEach(Main::tapProgrammer);
    }

    private static Programmer tapProgrammer(Programmer it) {
        logger.info("id:" + it.getId() +
                ", name: " + it.getName() +
                ", companyId: " + it.getCompanyId() +
                ", company: " + it.getCompany().map((c) -> c.getName()));
        return it;
    }

}
