package scalikejdbc4j

import scalikejdbc.{ DBSession, DB => ScalaDB }

/**
 * DB blocks provider.
 */
object DB {

  def autoCommitSession(): DBSession = ScalaDB.autoCommitSession()

  def readOnlySession(): DBSession = ScalaDB.readOnlySession()

  def localTx(f: java.util.function.Consumer[DBSession]): Unit = ScalaDB.localTx(f.accept)

  def autoCommit(f: java.util.function.Consumer[DBSession]): Unit = ScalaDB.autoCommit(f.accept)

  def readOnly(f: java.util.function.Consumer[DBSession]): Unit = ScalaDB.readOnly(f.accept)

  def withLocalTx[A](f: java.util.function.Function[DBSession, A]): A = ScalaDB.localTx(f.apply)

  def withAutoCommitSession[A](f: java.util.function.Function[DBSession, A]): A = ScalaDB.autoCommit(f.apply)

  def withReadOnlySession[A](f: java.util.function.Function[DBSession, A]): A = ScalaDB.readOnly(f.apply)

}
