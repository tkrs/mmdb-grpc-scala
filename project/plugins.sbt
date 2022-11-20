addSbtPlugin("io.gatling"     % "gatling-sbt"    % "4.2.4")
addSbtPlugin("com.thesamet"   % "sbt-protoc"     % "1.0.6")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"   % "2.5.0")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"   % "0.10.3")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.5.10")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.11.11"
