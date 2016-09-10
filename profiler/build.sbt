
name := "RedisPerformance"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.skinny-framework" %% "skinny-http-client" % "2.2.0"
)

enablePlugins(JmhPlugin)
