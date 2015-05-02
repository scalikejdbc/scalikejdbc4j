package scalikejdbc4j

import javax.sql.DataSource
import java.sql.Connection

/**
 * Connection Pool using external DataSource
 */
class DataSourceConnectionPool(
  override val dataSource: DataSource,
  settings: scalikejdbc.DataSourceConnectionPoolSettings)
    extends scalikejdbc.DataSourceConnectionPool(dataSource, settings) {

  def this(dataSource: DataSource) = {
    this(dataSource, scalikejdbc.DataSourceConnectionPoolSettings())
  }
}

/**
 * Connection Pool using external DataSource
 *
 * Note: Commons-DBCP doesn't support this API.
 */
class AuthenticatedDataSourceConnectionPool(
  override val dataSource: DataSource,
  override val user: String,
  password: String,
  settings: scalikejdbc.DataSourceConnectionPoolSettings)
    extends scalikejdbc.AuthenticatedDataSourceConnectionPool(dataSource, user, password, settings) {

  def this(dataSource: DataSource, user: String, password: String) = {
    this(dataSource, user, password, scalikejdbc.DataSourceConnectionPoolSettings())
  }
}
