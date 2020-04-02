name := """TicketAgency"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test

libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.6.4",
    "com.typesafe.akka" %% "akka-actor-typed" % "2.6.4",
    "com.typesafe.akka" %% "akka-http" % "10.1.11",
    "com.typesafe.akka" %% "akka-stream" % "2.6.4",
    "com.typesafe.akka" %% "akka-slf4j" % "2.6.4",
    "org.mongodb.scala" %% "mongo-scala-driver" % "2.9.0",
    "com.lightbend.akka" %% "akka-stream-alpakka-mongodb" % "2.0.0-RC1"
)

// Tests package
libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-stream-testkit" % "2.6.4"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
