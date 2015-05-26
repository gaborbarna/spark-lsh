name := "spark-lsh"

organization := "com.lesbroot"

version := "0.0.1"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "org.scalatest" % "scalatest_2.10" % "2.2.4" % "test",
  "org.apache.spark" %% "spark-core" % "1.3.1" % "provided",
  "org.apache.spark" %% "spark-mllib" % "1.3.1" % "provided"
)

initialCommands := "import com.lesbroot.sparklsh._"
