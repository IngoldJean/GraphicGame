name := "GraphicGame"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.scalaj" % "scalaj-http_2.11" % "2.3.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.11.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

val circeVersion = "0.11.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)
