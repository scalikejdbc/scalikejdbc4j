package sample.dao

import sample.entity._
import scalikejdbc._
import scalikejdbc.jsr310._
import scalikejdbc4j.AllJavaConverters._

object ProgrammerDao extends SQLSyntaxSupport[Programmer] {
  override def tableName = "programmer"
  override lazy val nameConverters = Map("gitHubName" -> "github_name")

  lazy val Table = this
  lazy val CompanyTable = CompanyDao.Table
  lazy val p = syntax("p")
  lazy val c = CompanyDao.c

  def extract(s: SyntaxProvider[Programmer])(rs: WrappedResultSet): Programmer = extract(s.resultName)(rs)
  def extract(rn: ResultName[Programmer])(rs: WrappedResultSet): Programmer = {
    new Programmer(
      rs.get(rn.id),
      rs.get(rn.gitHubName),
      rs.stringOpt(rn.realName).asJava,
      rs.longOpt(rn.companyId).map(toJavaLong).asJava,
      rs.get(rn.createdAt))
  }

  def extract(p: SyntaxProvider[Programmer], c: SyntaxProvider[Company])(rs: WrappedResultSet): Programmer = {
    val pg = extract(p.resultName)(rs)
    if (pg.getCompanyId.isPresent) {
      pg.setCompany(Option(CompanyDao.extract(c)(rs)).asJava)
    }
    pg
  }
}

case class ProgrammerDao(implicit session: DBSession) {
  import ProgrammerDao._

  def createTable() =
    sql"""create table programmer (
       id serial not null,
       github_name varchar(100) not null,
       real_name varchar(100),
       company_id bigint references company(id),
       created_at timestamp not null default current_timestamp)""".execute.apply()

  def create(gitHubName: String, realName: JavaOptional[String], companyId: JavaOptional[JavaLong]) = {
    val id = withSQL {
      insert.into(Table).namedValues(
        column.gitHubName -> gitHubName,
        column.realName -> realName.asScala,
        column.companyId -> companyId.asScala)
    }.updateAndReturnGeneratedKey.apply()
    find(id).get()
  }

  def findAll(): JavaList[Programmer] = {
    withSQL {
      select.from(Table as p)
        .innerJoin(CompanyTable as c).on(p.companyId, c.id)
    }.map(extract(p, c)).list.apply().asJava
  }

  def find(id: JavaLong): JavaOptional[Programmer] = {
    withSQL {
      select.from(Table as p)
        .innerJoin(CompanyTable as c).on(p.companyId, c.id)
        .where.eq(p.id, id)
    }.map(extract(p, c)).single.apply().asJava
  }

  def save(programmer: Programmer): Unit = {
    withSQL {
      update(Table).set(
        column.gitHubName -> programmer.getGitHubName,
        column.realName -> programmer.getRealName.asScala,
        column.companyId -> programmer.getCompanyId.asScala).where.eq(column.id, programmer.getId)
    }.update.apply()
  }

  def delete(id: JavaLong): Unit = {
    withSQL {
      QueryDSL.delete.from(Table).where.eq(column.id, id)
    }.update.apply()
  }

}