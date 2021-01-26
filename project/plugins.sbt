addSbtPlugin("io.gatling"    % "gatling-sbt"    % "3.2.1")
addSbtPlugin("com.thesamet"  % "sbt-protoc"     % "0.99.34")
addSbtPlugin("org.scalameta" % "sbt-scalafmt"   % "2.4.2")
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix"   % "0.9.25")
addSbtPlugin("com.geirsson"  % "sbt-ci-release" % "1.5.4")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.10.9"
