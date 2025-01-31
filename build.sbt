import scala.collection.immutable.Seq

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.18"

lazy val root = (project in file("."))
  .settings(
    name := "SparkLearn",
    idePackagePrefix := Some("learn.scala")
  )
lazy val logbackVersion = "1.5.12"
lazy val slf4jVersion = "2.0.16"
lazy val sparkVersion = "3.5.4"

libraryDependencies ++= Seq(
//  "ch.qos.logback" % "logback-classic" % logbackVersion,
//  "ch.qos.logback" % "logback-core" % logbackVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4" excludeAll (
    ExclusionRule(organization = "org.slf4j")),
  "com.google.guava" % "guava" % "33.2.1-jre",
  "org.scalactic" %% "scalactic" % "3.2.19",
  "org.scalatest" %% "scalatest" % "3.2.19" % "test",
  "org.apache.spark" %% "spark-sql" % sparkVersion
)