import Versions._
import sbt._

object Dependencies {

  // Libraries
  object Compile {
    val logback           = "ch.qos.logback"                %  "logback-classic"              % LogbackVersion
    val slf4jApi          = "org.slf4j"                     %  "slf4j-api"                    % Slf4jVersion
    val log4j             = "log4j"                         %  "log4j"                        % Log4jVersion
    val scalaLogging      = "com.typesafe.scala-logging"    %% "scala-logging-slf4j"          % ScalaLoggingVersion
  }

  object Test {
    val scalaTest         = "org.scalatest"                 %% "scalatest"                    % ScalaTestVersion        % "test"
    val scalaMock         = "org.scalamock"                 %% "scalamock-scalatest-support"  % ScalaMockVersion        % "test"
    val scalaMeterCore    = "com.storm-enroute"             %% "scalameter-core"              % ScalaMeterVersion       % "test"
    val scalaMeter        = "com.storm-enroute"             %% "scalameter"                   % ScalaMeterVersion       % "test"
  }

  import Compile._

  val logging         = Seq(logback, slf4jApi, scalaLogging)
  val test            = Seq(Test.scalaTest, Test.scalaMock, Test.scalaMeter, Test.scalaMeter)

  lazy val shared = logging ++ test

  // Sub-project specific dependencies
  lazy val common = shared

  lazy val examples = shared

}