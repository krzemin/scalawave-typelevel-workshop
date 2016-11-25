name := "scalawave-metaprogramming"

version := "1.0"

scalaVersion := "2.12.0"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scala-lang" % "scala-reflect" % "2.12.0"
)

scalacOptions ++= Seq("-feature", "-language:reflectiveCalls")
