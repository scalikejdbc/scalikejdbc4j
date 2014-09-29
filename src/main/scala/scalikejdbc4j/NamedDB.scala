package scalikejdbc4j

import scalikejdbc.DBSession

object NamedDB {

  def of(name: Any): NamedDB = new NamedDB(scalikejdbc.NamedDB(name))

}

case class NamedDB(underlying: scalikejdbc.NamedDB) {

  def readOnlySession(): DBSession = underlying.readOnlySession()

  def autoCommitSession(): DBSession = underlying.autoCommitSession()

  def localTx[A](f: java.util.function.Function[DBSession, A]): A = underlying.localTx(f.apply)

  def autoCommit[A](f: java.util.function.Function[DBSession, A]): A = underlying.localTx(f.apply)

  def readOnly[A](f: java.util.function.Function[DBSession, A]): A = underlying.localTx(f.apply)

}