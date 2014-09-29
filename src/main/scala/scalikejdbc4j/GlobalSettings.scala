package scalikejdbc4j

import java.util.{ List => JavaList }

import scalikejdbc4j.globalsettings._

import scala.collection.JavaConverters._

/**
 * Global Settings.
 */
object GlobalSettings {

  private[this] val underlying = scalikejdbc.GlobalSettings

  // loggingSQLErrors

  def isLoggingSQLErrorsEnabled: Boolean = underlying.loggingSQLErrors

  def setLoggingSQLErrors(enabled: Boolean) = underlying.loggingSQLErrors = enabled

  // LoggingSQLAndTimeSettings

  def getLoggingSQLAndTime: LoggingSQLAndTimeSettings = LoggingSQLAndTimeSettings(underlying.loggingSQLAndTime)

  def setLoggingSQLAndTime(settings: LoggingSQLAndTimeSettings) = underlying.loggingSQLAndTime = settings.asScala

  // SQLFormatterSettings

  def getSQLFormatter: SQLFormatterSettings = SQLFormatterSettings(underlying.sqlFormatter)

  def setSQLFormatter(settings: SQLFormatterSettings) = underlying.sqlFormatter = settings.asScala

  // NameBindingSQLValidatorSettings

  def getNameBindingSQLValidator: NameBindingSQLValidatorSettings = {
    NameBindingSQLValidatorSettings(underlying.nameBindingSQLValidator)
  }

  def setNameBindingSQLValidator(settings: NameBindingSQLValidatorSettings) = {
    underlying.nameBindingSQLValidator = settings.asScala
  }

  // QueryCompletionListener

  def getQueryCompletionListener: QueryCompletionListener = new QueryCompletionListener {
    override def apply(statement: String, params: JavaList[AnyRef], millis: Long): Unit = {
      underlying.queryCompletionListener.apply(statement, params.asScala, millis)
    }
  }

  def setQueryCompletionListener(listener: QueryCompletionListener) = {
    def scalaListener(statement: String, params: Seq[Any], millis: Long) = {
      listener.apply(statement, params.map(_.asInstanceOf[AnyRef]).asJava, millis)
    }
    underlying.queryCompletionListener = scalaListener _
  }

  // FailureCompletionListener

  def getQueryFailureListener: QueryFailureListener = new QueryFailureListener {
    override def apply(statement: String, params: JavaList[AnyRef], error: Throwable): Unit = {
      underlying.queryFailureListener.apply(statement, params.asScala, error)
    }
  }

  def setQueryFailureListener(listener: QueryFailureListener) = {
    def scalaListener(statement: String, params: Seq[Any], error: Throwable) = {
      listener.apply(statement, params.map(_.asInstanceOf[AnyRef]).asJava, error)
    }
    underlying.queryFailureListener = scalaListener _
  }

}
