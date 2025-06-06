scalaVersion := "2.12.12"

name := "middler-server"
version := "0.0.1-SNAPSHOT"

// flink dependencies
val flinkVersion = "1.11.2"
val flinkDependencies = Seq(
  //  for DatStream API
  "org.apache.flink" %% "flink-streaming-scala"   % flinkVersion,
  // for running in local env
  "org.apache.flink" %% "flink-clients"           % flinkVersion,
  // for using Apache Flink Web Dashboard
  "org.apache.flink" %% "flink-runtime-web"       % flinkVersion,
  // for logging
  "ch.qos.logback"   % "logback-classic"          % "1.2.3"
)

libraryDependencies ++= flinkDependencies
