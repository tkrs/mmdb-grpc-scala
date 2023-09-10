import sbt._

object Dependencies {
  val V = new {
    val `scala2.12` = "2.12.18"
    val `scala2.13` = "2.13.12"
    val scala3      = "3.3.1"

    val gatling     = "3.7.6"
    val gatlingGrpc = "0.12.0"
  }
  lazy val OrganizeImports = "com.github.liancheng" %% "organize-imports" % "0.6.0"

  lazy val ScalaPBRuntimeGrpc =
    "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion
  lazy val Munit     = "org.scalameta" %% "munit"      % "0.7.29" % Test
  lazy val GrpcNetty = "io.grpc"        % "grpc-netty" % scalapb.compiler.Version.grpcJavaVersion
  lazy val GatlingCharts = "io.gatling.highcharts" % "gatling-charts-highcharts" % V.gatling     % Test
  lazy val GatlingTest   = "io.gatling"            % "gatling-test-framework"    % V.gatling     % Test
  lazy val GatlingGrpc   = "com.github.phisgr"    %% "gatling-grpc"              % V.gatlingGrpc % Test
}
