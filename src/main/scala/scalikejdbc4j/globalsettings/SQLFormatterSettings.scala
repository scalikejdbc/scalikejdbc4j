package scalikejdbc4j.globalsettings

import java.util.Optional

import scalikejdbc.{ SQLFormatterSettings => ScalaSQLFormatterSettings }
import scalikejdbc4j.JavaConverters._

import scala.beans.BeanProperty

case class SQLFormatterSettings(underlying: ScalaSQLFormatterSettings) {

  def this() = this(ScalaSQLFormatterSettings())

  @BeanProperty var formatterClassName: Optional[String] = underlying.formatterClassName.asJava

  def asScala: ScalaSQLFormatterSettings = ScalaSQLFormatterSettings(
    formatterClassName = formatterClassName.asScala
  )

}
