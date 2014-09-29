package scalikejdbc4j

import scalikejdbc.{DB => ScalaDB, DBSession}

object DB {

  def readOnlySession(): DBSession = ScalaDB.readOnlySession()

  def autoCommitSession(): DBSession = ScalaDB.autoCommitSession()

  def localTx[A](f: java.util.function.Function[DBSession, A]): A = ScalaDB.localTx(f.apply)

  def autoCommit[A](f: java.util.function.Function[DBSession, A]): A = ScalaDB.localTx(f.apply)

  def readOnly[A](f: java.util.function.Function[DBSession, A]): A = ScalaDB.localTx(f.apply)

}
