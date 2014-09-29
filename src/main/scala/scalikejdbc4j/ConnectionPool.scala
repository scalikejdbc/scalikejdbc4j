package scalikejdbc4j

import scalikejdbc.{ConnectionPool => ScalaConnectionPool}

object ConnectionPool {

  // TODO other wrappers

  def singleton(url: String, user: String, password: String): Unit = {
    ScalaConnectionPool.singleton(url, user, password)
  }

  def add(name: AnyRef, url: String, user: String, password: String): Unit = {
    ScalaConnectionPool.add(name, url, user, password)
  }

}
