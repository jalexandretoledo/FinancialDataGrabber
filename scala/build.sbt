name := """financial-data-grabber"""

version := "0.1"

resolvers ++=
  Seq( "Spray Repository"    at "http://repo.spray.io" )

libraryDependencies ++= {
  val akkaVersion       = "2.3.10" 
  val sprayVersion      = "1.3.3"
  val dispatchVersion   = "0.11.2"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion, 
    "io.spray"          %% "spray-can"       % sprayVersion,
    "io.spray"          %% "spray-routing"   % sprayVersion,
    "io.spray"          %% "spray-json"      % "1.3.1",
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.1.3",
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "org.scalatest"     %% "scalatest"       % "2.2.0"       % "test",
    "net.databinder.dispatch" %% "dispatch-core" % dispatchVersion
  )
}


 
