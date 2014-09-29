package scalikejdbc4j

@FunctionalInterface
trait QueryFailureListener {

  def apply(statement: String, params: java.util.List[AnyRef], error: Throwable): Unit

}
