package dao

import model._
import scalikejdbc._
import scalikejdbc4j.AllJavaConverters._

object CompanyDao extends SQLSyntaxSupport[Company] {
  override lazy val tableName = "companies"
  lazy val c = syntax("c")
}

case class CompanyDao(implicit session: DBSession) {

  import dao.CompanyDao._

  lazy val Table = CompanyDao

  def extract(sp: SyntaxProvider[Company])(rs: WrappedResultSet): Company = extract(sp.resultName)(rs)

  def extract(rn: ResultName[Company])(rs: WrappedResultSet): Company =
    new Company(rs.long(rn.id), rs.string(rn.name))

  def createTable(): Unit = {
    sql"create table companies (id bigserial not null, name varchar(256) not null)".execute.apply()
  }

  def dropTable(): Unit = {
    sql"drop table companies".execute.apply()
  }

  def create(name: String): Company = {
    val id = withSQL {
      insert.into(Table).namedValues(column.name -> name)
    }.updateAndReturnGeneratedKey.apply()
    find(id).get()
  }

  def update(company: Company): Company = {
    withSQL {
      QueryDSL.update(Table).set(column.name -> company.getName).where.eq(column.id, company.getId)
    }.update.apply()
    company
  }

  def delete(company: Company): Unit = {
    withSQL {
      QueryDSL.delete.from(Table).where.eq(column.id, company.getId)
    }.update.apply()
  }

  def find(id: Long): JavaOptional[Company] = {
    withSQL {
      select.from(Table as c).where.eq(c.id, id)
    }.map(extract(c)).single.apply().asJava
  }

  def findAll(): JavaList[Company] = {
    withSQL {
      select.from(Table as c).orderBy(c.id)
    }.map(extract(c)).list.apply().asJava
  }

  def count(): Long = {
    withSQL {
      select(sqls.count).from(Table as c)
    }.map(_.long(1)).single.apply().get
  }

}