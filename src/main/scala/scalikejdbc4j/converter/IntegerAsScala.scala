package scalikejdbc4j.converter

case class IntegerAsScala(i: java.lang.Integer) {
  def asScala: Option[Int] = Option(i).map(_.asInstanceOf[Int])
}
