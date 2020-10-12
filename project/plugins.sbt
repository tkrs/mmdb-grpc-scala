addSbtPlugin("io.gatling"         % "gatling-sbt"   % "3.2.1")
addSbtPlugin("com.thesamet"       % "sbt-protoc"    % "0.99.34")
addSbtPlugin("pl.project13.scala" % "sbt-jmh"       % "0.4.0")
addSbtPlugin("org.scalameta"      % "sbt-scalafmt"  % "2.4.2")
addSbtPlugin("org.scoverage"      % "sbt-scoverage" % "1.6.1")
addSbtPlugin("org.xerial.sbt"     % "sbt-sonatype"  % "3.9.4")
addSbtPlugin("com.github.gseitz"  % "sbt-release"   % "1.0.13")
addSbtPlugin("com.jsuereth"       % "sbt-pgp"       % "2.0.1")

libraryDependencies += "com.thesamet.scalapb" %% "compilerplugin" % "0.10.8"
