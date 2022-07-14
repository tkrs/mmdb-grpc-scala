addSbtPlugin("io.gatling"     % "gatling-sbt"    % "4.1.6")
addSbtPlugin("com.thesamet"   % "sbt-protoc"     % "1.0.6")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"   % "2.4.6")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"   % "0.10.1")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.10")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.10"
