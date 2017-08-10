import skinny.servlet._, ServletPlugin._, ServletKeys._

name := "OSMCache"

scalaVersion := "2.12.3"

val jettyVersion = "9.2.15.v20160210"
val skinnyMicroVer = "1.2.2"

servletSettings

libraryDependencies ++= Seq(
  "org.mariadb.jdbc" % "mariadb-java-client" % "1.5.2",
  "org.skinny-framework" %% "skinny-orm" % "2.3.1",
  "org.skinny-framework" %% "skinny-micro" % skinnyMicroVer,
  "org.skinny-framework" %% "skinny-micro-json4s" % skinnyMicroVer,
  "org.skinny-framework" %% "skinny-micro-server" % skinnyMicroVer,
  "org.skinny-framework" %% "skinny-http-client" % "2.3.3",
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container",
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "com.google.guava" % "guava" % "19.0"
)

port in container.Configuration := 9001

lazy val profiler = project.in(file("profiler")).enablePlugins(JmhPlugin)
