## ScalikeJDBC4J - ScalikeJDBC in Java apps

[![Build Status](https://travis-ci.org/scalikejdbc/scalikejdbc4j.svg)](https://travis-ci.org/scalikejdbc/scalikejdbc4j)
<!--[![Coverage Status](https://coveralls.io/repos/scalikejdbc/scalikejdbc4j/badge.png?branch=master)](https://coveralls.io/r/scalikejdbc/scalikejdbc4j?branch=master)-->
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.scalikejdbc/scalikejdbc4j_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.scalikejdbc/scalikejdbc4j_2.11)

ScalikeJDBC is a tidy SQL-based DB access library for Scala developers. 

This library naturally wraps JDBC APIs and provides you easy-to-use and very flexible APIs. What’s more, QueryDSL makes your code type-safe and reusable. 

ScalikeJDBC is a practical and production-ready one. Use this library for your real projects.

http://scalikejdbc.org/

ScalikeJDBC4J is an adaptor to use ScalikeJDBC in Java applications. You can seamlessly use the DAO layer built with ScalikeJDBC in your Java applications.

If you're interested in using ScalikeJDBC in Java apps, please feel free to send me your feedback.

### Prerequisites

- Java SE 8
- Scala 2.10 or 2.11
- Maven 3 (if you use Maven)

### How to use

#### Maven

```xml
<dependency>
  <groupId>org.scalikejdbc</groupId>
  <artifactId>scalikejdbc4j_2.11</artifactId>
  <version>0.2.0</version>
</dependency>
```

### Examples

You can build application basically in Java except DAO code in Scala.

#### Company.java

```java
package sample.entity;

import java.util.Optional;

public class Company {

  private final Long id;
  private Optional<String> name;

  public Company(Long id, Optional<String> name) {
    this.id = id;
    setName(name);
  }

  public Long getId() { return id; }
  public Optional<String> getName() { return name; }
  public void setName(Optional<String> name) { this.name = name; }
}
```

#### CompanyDao.scala

```scala
package sample.dao

import java.util.Optional
import sample.entity.Company
import scalikejdbc._
import scalikejdbc4j.AllJavaConverters._

object CompanyDao extends SQLSyntaxSupport[Company] {
  override def tableName = "company"
  lazy val Table = this
  lazy val c = syntax("c")

  def extract(s: SyntaxProvider[Company])(rs: WrappedResultSet): Company = extract(s.resultName)(rs)
  def extract(rn: ResultName[Company])(rs: WrappedResultSet): Company = {
    new Company(rs.get(rn.id), rs.stringOpt(rn.name).asJava)
  }
}

case class CompanyDao(implicit session: DBSession) {
  import CompanyDao._

  def create(name: Optional[String]): JavaLong = {
    withSQL { insert.into(Table).namedValues(column.name -> name.asScala) }
      .updateAndReturnGeneratedKey.apply()
  }

  def find(id: JavaLong): Optional[Company] = {
    withSQL { select.from(Table as c).where.eq(c.id, id) }.map(extract(c)).single.apply().asJava
  }

  def findAll(): JavaList[Company] = {
    withSQL { select.from(Table as c) }.map(extract(c)).list.apply().asJava
  }
}
```

#### Usage.java

```java
import java.util.*;
import scalikejdbc4j.*;
import javax.sql.DataSource;

import sample.entity.Company;
import sample.dao.CompanyDao;

// Load DataSource in some way (e.g. Spring, JNDI)
DataSource dataSource = context.getBean(DataSource.class);
ConnectionPool.singleton(dataSource);

Long companyId = 123L;
Optional<Company> company = new CompanyDao(AutoSession.readOnly()).find(companyId);

List<Company> companies = DB.withReadOnlySession((session) -> new CompanyDao(session).findAll());

DB.localTx((session) -> {
  CompanyDao dao = new CompanyDao(session);
  Long id = dao.create(Optional.of("Typesafe"));
  throw new RuntimeException("This transaction will be rolled back");
});
```

### Samples

See the following samples for details.

- [Spark Framework + Spring Framework + ScalikeJDBC4J](https://github.com/scalikejdbc/scalikejdbc4j/tree/master/samples/spark)
- [Spring Boot + Lombok + ScalikeJDBC4J](https://github.com/emalock3/spring-boot-scalikejdbc4j-sample)


### License

Copyright scalikejdbc.org

Licensed under the Apache License, Version 2.0 (the "License")


