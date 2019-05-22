import skinny.servlet._, ServletPlugin._, ServletKeys._

name := "OSMCache"

scalaVersion := "2.12.8"

val jettyVersion = "9.4.18.v20190429"
val skinnyMicroVer = "2.0.0"

servletSettings

libraryDependencies ++= Seq(
  "org.mariadb.jdbc" % "mariadb-java-client" % "2.4.1",
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
