package scalikejdbc4j

import java.util.Optional

import scala.collection.JavaConverters._

/**
 * Scala Converters.
 */
object ScalaConverters {

  def toScalaOption[T](javaOptional: Optional[T]): Option[T] = {
    if (javaOptional.isPresent) Option(javaOptional.get()) else None
  }

  def toScalaSeq[T](javaList: java.util.List[T]): Seq[T] = javaList.asScala.toSeq

  def toScalaList[T](javaList: java.util.List[T]): List[T] = javaList.asScala.toList

}
