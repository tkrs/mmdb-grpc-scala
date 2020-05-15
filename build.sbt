name := "mmdb-grpc-gatling"
version := "0.1.0"
enablePlugins(GatlingPlugin)
scalaVersion := "2.12.10"
scalacOptions := Seq(
  "-encoding",
  "UTF-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:existentials",
  "-language:implicitConversions",
  "-language:postfixOps"
)
libraryDependencies ++= Seq(
  "io.grpc" % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion,
  "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.3.1" % "test",
  "io.gatling" % "gatling-test-framework" % "3.3.1" % "test",
  "com.github.phisgr" %% "gatling-grpc" % "0.8.2" % "test"
)

logLevel := Level.Warn

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
