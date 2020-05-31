import Dependencies._

name := "mmdb-grpc-scala"
ThisBuild / scalaVersion := V.`scala2.12`
ThisBuild / crossScalaVersions := Seq(
  V.`scala2.12`,
  V.`scala2.13`
)

ThisBuild / scalacOptions := Seq(
  "-encoding",
  "UTF-8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:existentials",
  "-language:implicitConversions",
  "-language:postfixOps"
)

ThisBuild / scalafmtOnCompile := true

lazy val publishSettings = Seq(
  releaseCrossBuild := true,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  homepage := Some(url("https://github.com/tkrs/mmdb-grpc-scala")),
  licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/mit-license.php")),
  publishMavenStyle := true,
  Test / publishArtifact := false,
  pomIncludeRepository := (_ => false),
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots".at(nexus + "content/repositories/snapshots"))
    else
      Some("releases".at(nexus + "service/local/staging/deploy/maven2"))
  },
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/tkrs/mmdb-grpc-scala"),
      "scm:git:git@github.com:tkrs/mmdb-grpc-scala.git"
    )
  ),
  pomExtra :=
    <developers>
      <developer>
        <id>tkrs</id>
        <name>Takeru Sato</name>
        <url>https://github.com/tkrs</url>
      </developer>
    </developers>
)

lazy val noPublishSettings = Seq(
  publish / skip := true
)

lazy val root = project
  .in(file("."))
  .settings(noPublishSettings)
  .dependsOn(core)
  .aggregate(core)

lazy val core = project
  .in(file("modules/core"))
  .settings(publishSettings)
  .settings(
    moduleName := "mmdb-grpc-core",
    libraryDependencies ++= Seq(GrpcNetty, ScalaPBRuntimeGrpc),
    Compile / PB.targets := Seq(
      scalapb.gen() -> (Compile / sourceManaged).value
    )
  )

lazy val gatling = project
  .in(file("modules/gatling"))
  .settings(noPublishSettings)
  .enablePlugins(GatlingPlugin)
  .settings(
    moduleName := "mmdb-grpc-gatling",
    libraryDependencies ++= Seq(GatlingCharts, GatlingTest, GatlingGrpc)
  )
  .dependsOn(core)
