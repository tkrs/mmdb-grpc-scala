addSbtPlugin("io.gatling"     % "gatling-sbt"    % "4.9.0")
addSbtPlugin("com.thesamet"   % "sbt-protoc"     % "1.0.7")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"   % "2.5.2")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"   % "0.12.1")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.12")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.15"
