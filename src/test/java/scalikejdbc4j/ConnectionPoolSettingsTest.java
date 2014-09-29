package scalikejdbc4j;

import dao.CompanyDao;
import dao.ProgrammerDao;
import model.Company;
import model.Programmer;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class ConnectionPoolSettingsTest {

    // TODO DataSource, configuration file, Spring integration

    String testName = "ConnectionPoolSettingsTest";

    @Test
    public void setValues() throws Exception {
        ConnectionPoolSettings settings = new ConnectionPoolSettings();
        settings.setValidationQuery("select 1 from dual");
        ConnectionPool.singleton("jdbc:h2:mem:" + testName, "user", "secret", settings);

        DB.localTx((s) -> {
            new ProgrammerDao(s).createTable();
            new CompanyDao(s).createTable();
        });
        // prepare data
        DB.localTx((s) -> {
            ProgrammerDao programmerDao = new ProgrammerDao(s);
            Company company = new CompanyDao(s).create("Typesafe");
            programmerDao.create("Alice", Optional.of(company.getId()));
            programmerDao.create("Bob", Optional.empty());
        });

        List<Programmer> programmers = DB.withReadOnlySession((s) -> new ProgrammerDao(s).findAll());
        assertThat(programmers.size(), is(2));
    }

}
