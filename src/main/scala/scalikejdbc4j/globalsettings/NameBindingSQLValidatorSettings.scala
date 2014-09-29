package scalikejdbc4j.globalsettings

import scalikejdbc.globalsettings._
import scalikejdbc.{ NameBindingSQLValidatorSettings => ScalaNameBindingSQLValidatorSettings }

import scala.beans.BeanProperty

case class NameBindingSQLValidatorSettings(underlying: ScalaNameBindingSQLValidatorSettings) {

  def this() = this(ScalaNameBindingSQLValidatorSettings())

  @BeanProperty var ignoredParams = underlying.ignoredParams match {
    case NoCheckForIgnoredParams => IgnoredParamsValidation.valueOf("NoCheckForIgnoredParams")
    case InfoLoggingForIgnoredParams => IgnoredParamsValidation.valueOf("InfoLoggingForIgnoredParams")
    case WarnLoggingForIgnoredParams => IgnoredParamsValidation.valueOf("WarnLoggingForIgnoredParams")
    case _ => IgnoredParamsValidation.valueOf("ExceptionForIgnoredParams")
  }

  def asScala: ScalaNameBindingSQLValidatorSettings = ScalaNameBindingSQLValidatorSettings(
    ignoredParams = ignoredParams.name() match {
      case "NoCheckForIgnoredParams" => NoCheckForIgnoredParams
      case "InfoLoggingForIgnoredParams" => InfoLoggingForIgnoredParams
      case "WarnLoggingForIgnoredParams" => WarnLoggingForIgnoredParams
      case _ => ExceptionForIgnoredParams
    }
  )

}
