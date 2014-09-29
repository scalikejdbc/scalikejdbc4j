package scalikejdbc4j.converter

import java.util.Optional

import scalikejdbc4j.ScalaConverters

case class OptionalAsScala[A](opt: Optional[A]) {
  def asScala: Option[A] = ScalaConverters.toScalaOption(opt)
}
