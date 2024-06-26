import sbt._

object Dependencies {
  val V = new {
    val `scala2.12` = "2.12.19"
    val `scala2.13` = "2.13.14"
    val scala3      = "3.3.3"

    val gatling     = "3.9.5"
    val gatlingGrpc = "0.16.0"
  }
  lazy val OrganizeImports = "com.github.liancheng" %% "organize-imports" % "0.6.0"

  lazy val ScalaPBRuntimeGrpc =
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
  lazy val Munit     = "org.scalameta" %% "munit"      % "1.0.0" % Test
  lazy val GrpcNetty = "io.grpc"        % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion
  lazy val GatlingCharts = "io.gatling.highcharts" % "gatling-charts-highcharts" % V.gatling     % Test
  lazy val GatlingTest   = "io.gatling"            % "gatling-test-framework"    % V.gatling     % Test
  lazy val GatlingGrpc   = "com.github.phisgr"     % "gatling-grpc"              % V.gatlingGrpc % Test
}
