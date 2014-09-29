package scalikejdbc4j.converter

case class BooleanAsScala(b: java.lang.Boolean) {
  def asScala: Option[Boolean] = Option(b).map(_.asInstanceOf[Boolean])
}
