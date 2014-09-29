package scalikejdbc4j

import scalikejdbc.DBSession

/**
 * NamedDB factory.
 */
object NamedDB {

  def of(name: AnyRef): NamedDB = new NamedDB(scalikejdbc.NamedDB(name))

}

/**
 * NamedDB
 */
case class NamedDB(underlying: scalikejdbc.NamedDB) {

  def autoCommitSession(): DBSession = underlying.autoCommitSession()

  def readOnlySession(): DBSession = underlying.readOnlySession()

  def localTx(f: java.util.function.Consumer[DBSession]): Unit = underlying.localTx(f.accept)

  def autoCommit(f: java.util.function.Consumer[DBSession]): Unit = underlying.autoCommit(f.accept)

  def readOnly(f: java.util.function.Consumer[DBSession]): Unit = underlying.readOnly(f.accept)

  def withLocalTx[A](f: java.util.function.Function[DBSession, A]): A = underlying.localTx(f.apply)

  def withAutoCommitSession[A](f: java.util.function.Function[DBSession, A]): A = underlying.autoCommit(f.apply)

  def withReadOnlySession[A](f: java.util.function.Function[DBSession, A]): A = underlying.readOnly(f.apply)

}