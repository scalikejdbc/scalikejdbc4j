package dao

import java.util.Optional
import model._
import scalikejdbc._
import scalikejdbc4j.OptionalConverter
import scala.collection.JavaConverters._

object ProgrammerDao extends SQLSyntaxSupport[Programmer] {
  override lazy val tableName = "programmers"
  lazy val p = syntax("p")
}

case class ProgrammerDao(implicit session: DBSession)
  extends OptionalConverter {

  import ProgrammerDao._
  lazy val Table = ProgrammerDao
  lazy val CompanyTable = CompanyDao
  lazy val c = CompanyDao.c

  def extract(sp: SyntaxProvider[Programmer], csp: SyntaxProvider[Company])(rs: WrappedResultSet): Programmer = {
    val programmer = extract(sp.resultName)(rs)
    programmer.setCompany(Optional.ofNullable(CompanyDao().extract(csp)(rs)))
    programmer
  }
  def extract(sp: SyntaxProvider[Programmer])(rs: WrappedResultSet): Programmer = extract(sp.resultName)(rs)
  def extract(rn: ResultName[Programmer])(rs: WrappedResultSet): Programmer = new Programmer(
    rs.long(rn.id),
    rs.string(rn.name),
    toJava(rs.longOpt(rn.companyId).map(toJavaLong))
  )

  def createTable(): Unit = {
    sql"create table programmers (id bigserial not null, name varchar(256) not null, company_id bigint)".execute.apply()
  }

  def create(name: String, companyId: Optional[Long]): Programmer = {
    val id: Long = withSQL {
      insert.into(Table).namedValues(
        column.name -> name,
        column.companyId -> toScala(companyId)
      )
    }.updateAndReturnGeneratedKey.apply()
    find(id).get()
  }

  def findWithCompany(id: Long): java.util.Optional[Programmer] = {
    toJava(withSQL {
      select.from(Table as p).innerJoin(CompanyTable as c).on(p.companyId, c.id).where.eq(p.id, id)
    }.map(extract(p, c)).single.apply())
  }
  def find(id: Long): java.util.Optional[Programmer] = {
    toJava(withSQL { select.from(Table as p).where.eq(p.id, id) }.map(extract(p)).single.apply())
  }

  def findAll(): java.util.List[Programmer] = {
    withSQL { select.from(Table as p).orderBy(p.id) }.map(extract(p)).list.apply().asJava
  }

}