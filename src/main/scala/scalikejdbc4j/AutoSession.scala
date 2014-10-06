package scalikejdbc4j

import scalikejdbc.DBSession

object AutoSession {

  def autoCommit(): DBSession = scalikejdbc.AutoSession

  def autoCommit(name: AnyRef): DBSession = scalikejdbc.NamedAutoSession(name)

  def readOnly(): DBSession = scalikejdbc.ReadOnlyAutoSession

  def readOnly(name: AnyRef): DBSession = scalikejdbc.ReadOnlyNamedAutoSession(name)

}
