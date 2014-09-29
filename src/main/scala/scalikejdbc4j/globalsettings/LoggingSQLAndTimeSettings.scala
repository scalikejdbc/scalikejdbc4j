package scalikejdbc4j.globalsettings

import scalikejdbc.{ LoggingSQLAndTimeSettings => ScalaLoggingSQLAndTimeSettings }

import scala.beans.BeanProperty

case class LoggingSQLAndTimeSettings(underlying: ScalaLoggingSQLAndTimeSettings) {

  def this() = this(ScalaLoggingSQLAndTimeSettings())

  @BeanProperty var enabled: Boolean = underlying.enabled
  @BeanProperty var logLevel: LogLevel = LogLevel.valueOf(underlying.logLevel.name)
  @BeanProperty var printUnprocessedStackTrace: Boolean = underlying.printUnprocessedStackTrace
  @BeanProperty var singleLineMode: Boolean = underlying.singleLineMode
  @BeanProperty var stackTraceDepth: Int = underlying.stackTraceDepth
  @BeanProperty var warningEnabled: Boolean = underlying.warningEnabled
  @BeanProperty var warningLogLevel: LogLevel = LogLevel.valueOf(underlying.warningLogLevel.name)
  @BeanProperty var warningThresholdMillis: Long = underlying.warningThresholdMillis

  def asScala: ScalaLoggingSQLAndTimeSettings = ScalaLoggingSQLAndTimeSettings(
    enabled = this.enabled,
    logLevel = Symbol(this.logLevel.name()),
    printUnprocessedStackTrace = this.printUnprocessedStackTrace,
    singleLineMode = this.singleLineMode,
    stackTraceDepth = this.stackTraceDepth,
    warningEnabled = this.warningEnabled,
    warningLogLevel = Symbol(this.warningLogLevel.name()),
    warningThresholdMillis = this.warningThresholdMillis
  )

}
