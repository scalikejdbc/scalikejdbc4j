package scalikejdbc4j

import java.sql.Connection
import javax.sql.DataSource

import scalikejdbc.{ ConnectionPool => ScalaConnectionPool, _ }

/**
 * Connection Pool Wrapper.
 */
case class ConnectionPool(underlying: ScalaConnectionPool) {

  def borrow(): Connection = underlying.borrow()

  def dataSource: DataSource = underlying.dataSource

  def numActive: Int = underlying.numActive

  def numIdle: Int = underlying.numActive

  def maxActive: Int = underlying.maxActive

  def maxIdle: Int = underlying.maxIdle

  def close(): Unit = underlying.close()

  override def hashCode() = underlying.hashCode()

  override def toString() = underlying.toString()
}

/**
 * ConnectionPool factory and registration.
 */
object ConnectionPool {

  // singleton CP

  def singleton(url: String, user: String, password: String): Unit = {
    ScalaConnectionPool.singleton(url, user, password)
  }

  def singleton(url: String, user: String, password: String, settings: ConnectionPoolSettings): Unit = {
    ScalaConnectionPool.singleton(url, user, password, settings.asScala)
  }

  def singleton(dataSource: AuthenticatedDataSourceConnectionPool) = {
    ScalaConnectionPool.singleton(dataSource)
  }

  def singleton(dataSource: DataSourceConnectionPool) = {
    ScalaConnectionPool.singleton(dataSource)
  }

  // CP registration

  def add(name: AnyRef, url: String, user: String, password: String): Unit = {
    ScalaConnectionPool.add(name, url, user, password)
  }

  def add(name: AnyRef, url: String, user: String, password: String, settings: ConnectionPoolSettings): Unit = {
    ScalaConnectionPool.add(name, url, user, password, settings.asScala)
  }

  def add(name: AnyRef, dataSource: AuthenticatedDataSourceConnectionPool) = {
    ScalaConnectionPool.add(name, dataSource)
  }

  def add(name: AnyRef, dataSource: DataSourceConnectionPool) = {
    ScalaConnectionPool.add(name, dataSource)
  }

  // CP retrieval

  def get(name: AnyRef): ConnectionPool = {
    try ConnectionPool(ScalaConnectionPool.get(name))
    catch {
      case e: IllegalStateException => ConnectionPool(ScalaConnectionPool.get(Symbol(name.toString)))
    }
  }
}
