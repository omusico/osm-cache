import skinny.servlet._, ServletPlugin._, ServletKeys._

name := "OSMCache"

scalaVersion := "2.11.8"

val jettyVersion = "9.2.15.v20160210"
val skinnyMicroVer = "1.1.0"

servletSettings

libraryDependencies ++= Seq(
  "org.skinny-framework" %% "skinny-orm" % "2.2.0",
  "org.skinny-framework" %% "skinny-micro" % skinnyMicroVer,
  "org.skinny-framework" %% "skinny-micro-json4s" % skinnyMicroVer,
  "org.skinny-framework" %% "skinny-micro-server" % skinnyMicroVer,
  "org.skinny-framework" %% "skinny-http-client" % "2.2.0",
  "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container",
  "org.eclipse.jetty" % "jetty-plus"   % jettyVersion % "container"
)

port in container.Configuration := 9000
