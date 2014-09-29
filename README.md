## ScalikeJDBC in Java apps

ScalikeJDBC is a tidy JDBC wrapper library in Scala.

http://scalikejdbc.org/

This is a proof of concept of ScalikeJDBC Java adapter. 

If you're interested in using ScalikeJDBC in Java apps, please feel free to send me your feedback.

### Prerequisites

 - Java SE 8
 - Maven 3

### Example

src/main/java/app/Main.java

### How to run the example

```sh
mvn compile exec:java
```

### Output Example

```
$ mvn compile exec:java
[INFO] Scanning for projects...
[WARNING]
[WARNING] Some problems were encountered while building the effective model for org.scalikejdbc:scalikejdbc-java-example:jar:0.1-SNAPSHOT
[WARNING] 'build.plugins.plugin.version' for org.codehaus.mojo:exec-maven-plugin is missing. @ line 93, column 21
[WARNING] 'build.plugins.plugin.version' for org.codehaus.mojo:build-helper-maven-plugin is missing. @ line 54, column 21
[WARNING]
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING]
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING]
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building ScalikeJDBC example in Java 0.1-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- build-helper-maven-plugin:1.9.1:add-source (default) @ scalikejdbc-java-example ---
[INFO] Source directory: /Users/seratch/Documents/github/scalikejdbc-java-example/src/main/scala added.
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ scalikejdbc-java-example ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO]
[INFO] --- scala-maven-plugin:3.2.0:add-source (scala-compile-first) @ scalikejdbc-java-example ---
[INFO] Add Test Source directory: /Users/seratch/Documents/github/scalikejdbc-java-example/src/test/scala
[INFO]
[INFO] --- scala-maven-plugin:3.2.0:compile (scala-compile-first) @ scalikejdbc-java-example ---
[WARNING]  Expected all dependencies to require Scala version: 2.11.2
[WARNING]  org.scalikejdbc:scalikejdbc_2.11:2.1.2 requires scala version: 2.11.2
[WARNING]  org.scalikejdbc:scalikejdbc-core_2.11:2.1.2 requires scala version: 2.11.2
[WARNING]  org.scala-lang.modules:scala-parser-combinators_2.11:1.0.2 requires scala version: 2.11.1
[WARNING] Multiple versions of scala libraries detected!
[INFO] /Users/seratch/Documents/github/scalikejdbc-java-example/src/main/java:-1: info: compiling
[INFO] /Users/seratch/Documents/github/scalikejdbc-java-example/src/main/scala:-1: info: compiling
[INFO] Compiling 9 source files to /Users/seratch/Documents/github/scalikejdbc-java-example/target/classes at 1412008110025
[INFO] prepare-compile in 0 s
[INFO] compile in 8 s
[INFO]
[INFO] --- maven-compiler-plugin:2.3.2:compile (default-compile) @ scalikejdbc-java-example ---
[INFO] Compiling 1 source file to /Users/seratch/Documents/github/scalikejdbc-java-example/target/classes
[INFO]
[INFO] --- exec-maven-plugin:1.3.2:java (default-cli) @ scalikejdbc-java-example ---
[WARNING] Warning: killAfter is now deprecated. Do you need it ? Please comment on MEXEC-6.
2014-09-30 01:28:41,144 DEBUG [app.Main.main()] scalikejdbc.ConnectionPool$ Registered connection pool : ConnectionPool(url:jdbc:h2:mem:scalikejdbc, user:sa) using factory : <default>
2014-09-30 01:28:41,148 DEBUG [app.Main.main()] scalikejdbc.ConnectionPool$ Registered singleton connection pool : ConnectionPool(url:jdbc:h2:mem:scalikejdbc, user:sa)
2014-09-30 01:28:41,911 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   create table companies (id bigserial not null, name varchar(256) not null); (15 ms)

  [Stack Trace]
    ...
    dao.CompanyDao.createTable(CompanyDao.scala:26)
    app.Main.lambda$main$0(Main.java:32)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:41,944 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   create table programmers (id bigserial not null, name varchar(256) not null, company_id bigint); (2 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.createTable(ProgrammerDao.scala:35)
    app.Main.lambda$main$0(Main.java:33)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:42,130 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   insert into companies (name) values ('Typesafe'); (2 ms)

  [Stack Trace]
    ...
    dao.CompanyDao.create(CompanyDao.scala:32)
    app.Main.lambda$main$0(Main.java:36)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:42,166 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   select c.id as i_on_c, c.name as n_on_c from companies c where c.id = 1; (0 ms)

  [Stack Trace]
    ...
    dao.CompanyDao.find(CompanyDao.scala:39)
    dao.CompanyDao.create(CompanyDao.scala:33)
    app.Main.lambda$main$0(Main.java:36)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    ...

2014-09-30 01:28:42,190 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   insert into programmers (name, company_id) values ('Alice', 1); (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.create(ProgrammerDao.scala:44)
    app.Main.lambda$main$0(Main.java:37)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:42,196 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   select p.id as i_on_p, p.name as n_on_p, p.company_id as ci_on_p from programmers p where p.id = 1; (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.find(ProgrammerDao.scala:54)
    dao.ProgrammerDao.create(ProgrammerDao.scala:45)
    app.Main.lambda$main$0(Main.java:37)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    ...

2014-09-30 01:28:42,202 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   insert into programmers (name, company_id) values ('Bob', null); (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.create(ProgrammerDao.scala:44)
    app.Main.lambda$main$0(Main.java:38)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:42,206 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   select p.id as i_on_p, p.name as n_on_p, p.company_id as ci_on_p from programmers p where p.id = 2; (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.find(ProgrammerDao.scala:54)
    dao.ProgrammerDao.create(ProgrammerDao.scala:45)
    app.Main.lambda$main$0(Main.java:38)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    ...

2014-09-30 01:28:42,216 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   select p.id as i_on_p, p.name as n_on_p, p.company_id as ci_on_p from programmers p order by p.id; (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.findAll(ProgrammerDao.scala:58)
    app.Main.lambda$main$0(Main.java:41)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:42,232 INFO [app.Main.main()] app.Main id:1, name: Alice, companyId: Optional[1], company: Optional.empty
2014-09-30 01:28:42,232 INFO [app.Main.main()] app.Main id:2, name: Bob, companyId: Optional.empty, company: Optional.empty
2014-09-30 01:28:42,238 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   select p.id as i_on_p, p.name as n_on_p, p.company_id as ci_on_p , c.id as i_on_c, c.name as n_on_c from programmers p inner join companies c on p.company_id = c.id where p.id = 1; (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.findWithCompany(ProgrammerDao.scala:51)
    app.Main.lambda$main$0(Main.java:42)
    app.Main$$Lambda$1/349654436.apply(Unknown Source)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc4j.DB$$anonfun$1.apply(DB.scala:11)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:258)
    scalikejdbc.DB.localTx(DB.scala:75)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:257)
    scalikejdbc.DB$$anonfun$localTx$1.apply(DB.scala:256)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.DB$.using(DB.scala:150)
    scalikejdbc.DB$.localTx(DB.scala:256)
    ...

2014-09-30 01:28:42,243 INFO [app.Main.main()] app.Main id:1, name: Alice, companyId: Optional[1], company: Optional[Typesafe]
2014-09-30 01:28:42,389 DEBUG [app.Main.main()] scalikejdbc.ConnectionPool$ Registered connection pool : ConnectionPool(url:jdbc:h2:mem:scalikejdbc, user:sa) using factory : <default>
2014-09-30 01:28:42,395 DEBUG [app.Main.main()] scalikejdbc.StatementExecutor$$anon$1 SQL execution completed

  [SQL Execution]
   select p.id as i_on_p, p.name as n_on_p, p.company_id as ci_on_p from programmers p order by p.id; (0 ms)

  [Stack Trace]
    ...
    dao.ProgrammerDao.findAll(ProgrammerDao.scala:58)
    app.Main.lambda$main$1(Main.java:52)
    app.Main$$Lambda$5/1318949574.apply(Unknown Source)
    scalikejdbc4j.NamedDB$$anonfun$localTx$1.apply(NamedDB.scala:17)
    scalikejdbc4j.NamedDB$$anonfun$localTx$1.apply(NamedDB.scala:17)
    scalikejdbc.DBConnection$$anonfun$_localTx$1$1.apply(DBConnection.scala:252)
    scala.util.control.Exception$Catch.apply(Exception.scala:103)
    scalikejdbc.DBConnection$class._localTx$1(DBConnection.scala:250)
    scalikejdbc.DBConnection$$anonfun$localTx$1.apply(DBConnection.scala:257)
    scalikejdbc.DBConnection$$anonfun$localTx$1.apply(DBConnection.scala:257)
    scalikejdbc.LoanPattern$class.using(LoanPattern.scala:33)
    scalikejdbc.NamedDB.using(NamedDB.scala:32)
    scalikejdbc.DBConnection$class.localTx(DBConnection.scala:257)
    scalikejdbc.NamedDB.localTx(NamedDB.scala:32)
    scalikejdbc4j.NamedDB.localTx(NamedDB.scala:17)
    ...

2014-09-30 01:28:42,407 INFO [app.Main.main()] app.Main id:1, name: Alice, companyId: Optional[1], company: Optional.empty
2014-09-30 01:28:42,407 INFO [app.Main.main()] app.Main id:2, name: Bob, companyId: Optional.empty, company: Optional.empty
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 15.334 s
[INFO] Finished at: 2014-09-30T01:28:42+09:00
[INFO] Final Memory: 26M/101M
[INFO] ------------------------------------------------------------------------
```

