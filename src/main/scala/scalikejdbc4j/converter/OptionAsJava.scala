package scalikejdbc4j.converter

import java.util.Optional

import scalikejdbc4j.JavaConverters

case class OptionAsJava[A](opt: Option[A]) {
  def asJava: Optional[A] = JavaConverters.toJavaOptional(opt)
}
