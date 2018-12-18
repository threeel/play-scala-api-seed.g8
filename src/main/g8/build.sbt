
import scala.sys.process.Process

lazy val projectDependencies = Seq(
  guice,
  "org.joda" % "joda-convert" % "1.9.2",
  "io.lemonlabs" %% "scala-uri" % "1.1.5",
  "net.codingwell" %% "scala-guice" % "4.1.1",
  "com.typesafe.play" %% "play-mailer" % "6.0.1",
  "com.typesafe.play" %% "play-mailer-guice" % "6.0.1",
  "net.logstash.logback" % "logstash-logback-encoder" % "5.2",
  "com.mohiva" %% "play-silhouette" % "5.0.5",
  "com.nulab-inc" %% "scala-oauth2-core" % "1.3.0",
  "com.nulab-inc" %% "play2-oauth2-provider" % "1.3.0",
  "com.mohiva" %% "play-silhouette-password-bcrypt" % "5.0.5",
  "com.mohiva" %% "play-silhouette-persistence" % "5.0.5",
  "com.mohiva" %% "play-silhouette-crypto-jca" % "5.0.5",
  "com.mohiva" %% "play-silhouette-testkit" % "5.0.5" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
  specs2 % Test
)

lazy val daoDependencies = Seq(
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "io.strongtyped" %% "active-slick" % "0.3.5",
  "com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.slick" %% "slick-codegen" % "3.2.3",
  "com.github.tototoshi" %% "slick-joda-mapper" % "2.3.0",
  "org.joda" % "joda-convert" % "2.1.1",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "com.h2database" % "h2" % "1.4.197" % Test
)

def evictionSettings: Seq[Setting[_]] = Seq(
  evictionWarningOptions in update := EvictionWarningOptions.default
    .withWarnTransitiveEvictions(false)
    .withWarnDirectEvictions(false)
)

def common: Seq[Setting[_]] = evictionSettings ++ Seq(
  organization := "$organization$",
  version := "0.1.0",
  scalaVersion := "2.12.6",
  licenses := Seq(("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))),
  homepage := Some(url("$organization_url$")),

  resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns),
  resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  resolvers += Resolver.JCenterRepository,
  TaskKey[Unit]("check") := {
    val process = Process("java", Seq("-jar", (crossTarget.value / "$name$.jar").toString))
    val out = (process !!)
    if (out.trim != "bye") sys.error("unexpected output: " + out)
    ()
  },

  scalacOptions in(Compile, doc) ++= (scalaBinaryVersion.value match {
    case "2.12" => Seq("-no-java-comments")
    case _ => Seq.empty
  }),

  // Setting javac options in common allows IntelliJ IDEA to import them automatically
  javacOptions in compile ++= Seq(
    "-encoding", "UTF-8",
    "-source", "1.8",
    "-target", "1.8",
    "-parameters",
    "-Xlint:unchecked",
    "-Xlint:deprecation"
  ),

)

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "$organization$.binders._"


lazy val root = (project in file("."))
  .settings(
  name := "$name$",
  libraryDependencies ++= projectDependencies ++ daoDependencies,
  dependencyOverrides ++= Seq(
    //      Seq for SBT 1.0.x Example
    //      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    //      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    //      "com.google.code.findbugs" %% "jsr305" % "3.0.1",
    //      "com.google.guava" % "guava" % "22.0",
    //      "org.slf4j" % "slf4j-api" % "1.7.25"
  )
).settings(common)
  .enablePlugins(PlayScala)