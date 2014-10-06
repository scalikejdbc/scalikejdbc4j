import dao.CompanyDao;
import dao.ProgrammerDao;
import model.Company;
import model.Programmer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scalikejdbc4j.AutoSession;
import scalikejdbc4j.ConnectionPool;
import scalikejdbc4j.DB;
import scalikejdbc4j.NamedDB;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SimpleUsageTest {

    @Before
    public void setUp() throws Exception {
        // initialize the default connection pool
        Class.forName("org.h2.Driver");
        ConnectionPool.singleton("jdbc:h2:mem:scalikejdbc", "user", "secret");

        DB.localTx((session) -> {
            new ProgrammerDao(session).createTable();
            new CompanyDao(session).createTable();
        });
        // prepare data
        DB.localTx((session) -> {
            ProgrammerDao programmerDao = new ProgrammerDao(session);
            Company company = new CompanyDao(session).create("Typesafe");
            programmerDao.create("Alice", Optional.of(company.getId()));
            programmerDao.create("Bob", Optional.empty());
        });
    }

    @After
    public void tearDown() throws Exception {
        DB.autoCommit((s) -> new ProgrammerDao(s).dropTable());
        DB.autoCommit((s) -> new CompanyDao(s).dropTable());
    }

    @Test
    public void tryNamedDB() throws Exception {
        ConnectionPool.add("other", "jdbc:h2:mem:scalikejdbc", "user", "secret");
        List<Programmer> programmers = NamedDB.of("other").withReadOnlySession((s) -> new ProgrammerDao(s).findAll());
        assertThat(programmers.size(), is(2));
    }

    @Test
    public void tryAutoSession() throws Exception {
        new ProgrammerDao(AutoSession.autoCommit()).create("Oracle", Optional.empty());
        List<Programmer> programmers = new ProgrammerDao(AutoSession.autoCommit()).findAll();
        assertThat(programmers.size(), is(3));
    }

    @Test
    public void tryNamedAutoSession() throws Exception {
        ConnectionPool.add("other", "jdbc:h2:mem:scalikejdbc", "user", "secret");
        new ProgrammerDao(AutoSession.autoCommit("other")).create("Oracle", Optional.empty());
        List<Programmer> programmers = new ProgrammerDao(AutoSession.autoCommit("other")).findAll();
        assertThat(programmers.size(), is(3));
    }

    @Test
    public void tryReadOnlyAutoSession() throws Exception {
        List<Programmer> programmers = new ProgrammerDao(AutoSession.readOnly()).findAll();
        assertThat(programmers.size(), is(2));
    }

    @Test
    public void tryReadOnlyNamedAutoSession() throws Exception {
        ConnectionPool.add("other", "jdbc:h2:mem:scalikejdbc", "user", "secret");
        List<Programmer> programmers = new ProgrammerDao(AutoSession.readOnly("other")).findAll();
        assertThat(programmers.size(), is(2));
    }

    @Test
    public void queryWithAssociations() throws Exception {
        List<Programmer> programmers = DB.withReadOnlySession((session) -> new ProgrammerDao(session).findAll());
        assertThat(programmers.size(), is(2));

        Optional<Programmer> alice = DB.withReadOnlySession((session) -> new ProgrammerDao(session).findByName("Alice"));
        Optional<Programmer> aliceWithCompany = DB.withReadOnlySession((s) ->
                alice.flatMap((a) -> new ProgrammerDao(s).findWithCompany(a.getId())));

        assertThat(alice.map((a) -> a.getCompany().isPresent()), is(Optional.of(false)));
        assertThat(aliceWithCompany.map((a) -> a.getCompany().isPresent()), is(Optional.of(true)));
    }

}
