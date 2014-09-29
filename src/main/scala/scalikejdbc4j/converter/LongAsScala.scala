package scalikejdbc4j.converter

case class LongAsScala(l: java.lang.Long) {
  def asScala: Option[Long] = Option(l).map(_.asInstanceOf[Long])
}
