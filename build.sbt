lazy val scalikejdbcVersion = "2.2.1"

lazy val root = (project in file(".")).settings(
  organization := "org.scalikejdbc",
  name := "scalikejdbc4j",
  version := "0.1.3",
  scalaVersion := "2.11.4",
  crossScalaVersions := Seq("2.10.4", "2.11.4"),
  libraryDependencies := Seq(
    "org.scalikejdbc" %% "scalikejdbc"                      % scalikejdbcVersion,
    "org.scalikejdbc" %% "scalikejdbc-jsr310"               % scalikejdbcVersion,
    "org.scalikejdbc" %% "scalikejdbc-syntax-support-macro" % scalikejdbcVersion,
    "com.h2database"  %  "h2"                               % "1.4.184"           % "test",
    "ch.qos.logback"  %  "logback-classic"                  % "1.1.2"             % "test",
    "junit"           %  "junit"                            % "4.12"              % "test",
    "com.novocode"    %  "junit-interface"                  % "0.11"              % "test"
  ),
  parallelExecution in Test := false,
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  publishTo <<= version { (v: String) => 
    val nexus = "https://oss.sonatype.org/"
    if (v.trim.endsWith("SNAPSHOT")) Some("snapshots" at nexus + "content/repositories/snapshots")
    else Some("releases" at nexus + "service/local/staging/deploy/maven2")
  },
  publishMavenStyle := true,
  pomIncludeRepository := { x => false },
  pomExtra := <url>http://scalikejdbc.org/</url>
    <licenses>
      <license>
        <name>Apache License, Version 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:scalikejdbc/scalikejdbc4j.git</url>
      <connection>scm:git:git@github.com:scalikejdbc/scalikejdbc4j.git</connection>
    </scm>
    <developers>
      <developer>
        <id>seratch</id>
        <name>Kazuhiro Sera</name>
        <url>http://git.io/sera</url>
      </developer>
    </developers>
).settings(scalariformSettings: _*)
 .settings(sonatypeSettings: _*)

