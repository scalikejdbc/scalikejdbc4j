package dao

import model._
import scalikejdbc._
import scalikejdbc4j.OptionalConverter

import scala.collection.JavaConverters._

object CompanyDao extends SQLSyntaxSupport[Company] {

  override lazy val tableName = "companies"
  lazy val c = syntax("c")
}

case class CompanyDao(implicit session: DBSession)
  extends OptionalConverter {

  import CompanyDao._
  lazy val Table = CompanyDao

  def extract(sp: SyntaxProvider[Company])(rs: WrappedResultSet): Company = extract(sp.resultName)(rs)
  def extract(rn: ResultName[Company])(rs: WrappedResultSet): Company =
    new Company(rs.long(rn.id), rs.string(rn.name))

  def createTable(): Unit = {
    sql"create table companies (id bigserial not null, name varchar(256) not null)".execute.apply()
  }

  def create(name: String): Company = {
    val id = withSQL {
      insert.into(Table).namedValues(column.name -> name)
    }.updateAndReturnGeneratedKey.apply()
    find(id).get()
  }

  def find(id: Long): java.util.Optional[Company] = {
    toJava(withSQL {
      select.from(Table as c).where.eq(c.id, id)
    }.map(extract(c)).single.apply())
  }

  def findAll(): java.util.List[Company] = {
    withSQL { select.from(Table as c).orderBy(c.id) }.map(extract(c)).list.apply().asJava
  }

}