organization := "org.scalikejdbc"

name := "scalikejdbc4j"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

lazy val scalikejdbcVersion = "2.1.2"

libraryDependencies := Seq(
  "org.scalikejdbc" %% "scalikejdbc"                      % scalikejdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % scalikejdbcVersion,
  "com.h2database"  %  "h2"                               % "1.4.181"           % "test",
  "ch.qos.logback"  %  "logback-classic"                  % "1.1.2"             % "test",
  "junit"           %  "junit"                            % "4.11"              % "test",
  "com.novocode"    %  "junit-interface"                  % "0.11"              % "test"
)

parallelExecution in Test := false

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

scalariformSettings

publishTo <<= version { (v: String) => 
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

