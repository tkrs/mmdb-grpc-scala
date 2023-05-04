addSbtPlugin("io.gatling"     % "gatling-sbt"    % "4.3.1")
addSbtPlugin("com.thesamet"   % "sbt-protoc"     % "1.0.6")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"   % "2.5.0")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"   % "0.10.4")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.11")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.13"
