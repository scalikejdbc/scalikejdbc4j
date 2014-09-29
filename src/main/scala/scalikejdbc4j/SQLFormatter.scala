package scalikejdbc4j

@FunctionalInterface
trait SQLFormatter extends scalikejdbc.SQLFormatter {

  def format(sql: String): String

}
