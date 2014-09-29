package scalikejdbc4j.converter

case class ShortAsScala(s: java.lang.Short) {
  def asScala: Option[Short] = Option(s).map(_.asInstanceOf[Short])
}
