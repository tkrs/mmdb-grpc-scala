addSbtPlugin("io.gatling"    % "gatling-sbt"    % "4.1.3")
addSbtPlugin("com.thesamet"  % "sbt-protoc"     % "1.0.6")
addSbtPlugin("org.scalameta" % "sbt-scalafmt"   % "2.4.6")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix"   % "0.9.25")
addSbtPlugin("com.geirsson"  % "sbt-ci-release" % "1.5.5")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.10.9"
