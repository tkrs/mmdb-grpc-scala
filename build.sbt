import Dependencies._

lazy val root = project
  .in(file("."))
  .settings(
    name        := "mmdb-grpc-scala",
    description := "The gRPC service for Scala that provides a query to MaxMind's GeoLite2 database"
  )
  .settings(
    inThisBuild(
      Seq(
        organization := "com.github.tkrs",
        homepage     := Some(url("https://github.com/tkrs/mmdb-grpc-scala")),
        licenses     := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php")),
        developers := List(
          Developer(
            "tkrs",
            "Takeru Sato",
            "type.in.type@gmail.com",
            url("https://github.com/tkrs")
          )
        ),
        scalaVersion       := V.`scala2.13`,
        crossScalaVersions := Seq(V.`scala2.12`, V.`scala2.13`, V.scala3),
        scalacOptions ++= compilerOptions,
        fork              := true,
        scalafmtOnCompile := true,
        scalafixOnCompile := true,
        scalafixDependencies += OrganizeImports,
        semanticdbEnabled := true,
        semanticdbVersion := scalafixSemanticdb.revision
      )
    )
  )
  .settings(publish / skip := true)
  .dependsOn(core)
  .aggregate(core)

lazy val core = project
  .in(file("modules/core"))
  .settings(
    moduleName := "mmdb-grpc-core",
    libraryDependencies ++= Seq(GrpcNetty, ScalaPBRuntimeGrpc, Munit),
    Compile / PB.targets := Seq(scalapb.gen() -> (Compile / sourceManaged).value)
  )

lazy val gatling = project
  .in(file("modules/gatling"))
  .settings(
    scalaVersion       := V.`scala2.13`,
    crossScalaVersions := Seq(V.`scala2.13`)
  )
  .settings(publish / skip := true)
  .enablePlugins(GatlingPlugin)
  .settings(
    moduleName := "mmdb-grpc-gatling",
    libraryDependencies ++= Seq(GatlingCharts, GatlingTest, GatlingGrpc)
  )
  .dependsOn(core)

lazy val compilerOptions = Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:existentials",
  "-language:implicitConversions",
  "-language:postfixOps"
)
