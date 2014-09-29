package scalikejdbc4j

import java.util.Optional

object OptionalConverter extends OptionalConverter

trait OptionalConverter {

  def toScala[T](javaOptional: Optional[T]): Option[T] = {
    if (javaOptional.isPresent) Some(javaOptional.get())
    else None
  }

  def toJava[T](scalaOption: Option[T]): Optional[T] = {
    if (scalaOption.isDefined) Optional.ofNullable(scalaOption.get)
    else Optional.empty()
  }

  def toJavaLong(l: Long): java.lang.Long = l.asInstanceOf[java.lang.Long]

}
