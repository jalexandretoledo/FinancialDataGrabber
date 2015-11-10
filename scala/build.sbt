import ScalaxbKeys._

name := """financial-data-grabber"""

version := "0.1"

resolvers ++=
  Seq( "Spray Repository"    at "http://repo.spray.io" )

libraryDependencies ++= {
  val akkaVersion       = "2.3.10" 
  val sprayVersion      = "1.3.3"
  Seq(
    "com.typesafe.akka" %% "akka-actor"      % akkaVersion, 
    "io.spray"          %% "spray-can"       % sprayVersion,
    "io.spray"          %% "spray-routing"   % sprayVersion,
    "io.spray"          %% "spray-json"      % "1.3.1",
    "com.typesafe.akka" %% "akka-slf4j"      % akkaVersion,
    "ch.qos.logback"    %  "logback-classic" % "1.1.3",
    "com.typesafe.akka" %% "akka-testkit"    % akkaVersion   % "test",
    "org.scalatest"     %% "scalatest"       % "2.2.0"       % "test"
  )
}


lazy val commonSettings = Seq(
  organization  := "financialdatagrabber",
  scalaVersion  := "2.11.5"
)
 
lazy val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
lazy val scalaParser = "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
lazy val dispatchV = "0.11.2"
lazy val dispatch = "net.databinder.dispatch" %% "dispatch-core" % dispatchV
 
lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name          := "financial-data-grabber",
    libraryDependencies ++= Seq(dispatch),
    libraryDependencies ++= {
      if (scalaVersion.value startsWith "2.11") Seq(scalaXml, scalaParser)
      else Seq()
    }).
  settings(scalaxbSettings: _*).
  settings(
    sourceGenerators in Compile += (scalaxb in Compile).taskValue,
    dispatchVersion in (Compile, scalaxb) := dispatchV,
    async in (Compile, scalaxb)           := true,
    // packageName in (Compile, scalaxb)     := "financialdatagrabber.services"
    packageNames in (Compile, scalaxb)    := Map(uri("http://www.cvm.gov.br/webservices/") -> "financialdatagrabber.services.cvmweb")
    // logLevel in (Compile, scalaxb) := Level.Debug 
  )
