package scalikejdbc4j

import scala.beans.BeanProperty

/**
 * ConnectionPoolSettings
 */
class ConnectionPoolSettings {

  private[this] val default = scalikejdbc.ConnectionPoolSettings()

  @BeanProperty var initialSize: Int = default.initialSize
  @BeanProperty var maxSize: Int = default.maxSize
  @BeanProperty var connectionTimeoutMillis: Long = default.connectionTimeoutMillis
  @BeanProperty var validationQuery: String = default.validationQuery
  @BeanProperty var connectionPoolFactoryName: String = default.connectionPoolFactoryName

  def asScala: scalikejdbc.ConnectionPoolSettings = scalikejdbc.ConnectionPoolSettings(
    initialSize = this.initialSize,
    maxSize = this.maxSize,
    connectionTimeoutMillis = this.connectionTimeoutMillis,
    validationQuery = this.validationQuery,
    connectionPoolFactoryName = this.connectionPoolFactoryName
  )
}
