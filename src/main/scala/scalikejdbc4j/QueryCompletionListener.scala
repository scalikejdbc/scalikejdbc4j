package scalikejdbc4j

@FunctionalInterface
trait QueryCompletionListener {

  def apply(statement: String, params: java.util.List[AnyRef], millis: Long): Unit

}
