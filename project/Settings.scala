import Common._
import com.scalapenos.sbt.prompt.SbtPrompt.autoImport._
import com.scalapenos.sbt.prompt._
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt.Keys._
import sbt._
import Resolvers._
import sbtassembly.AssemblyPlugin.autoImport._
import sbtassembly.PathList

import scala.language.postfixOps
import scalariform.formatter.preferences.{AlignArguments, AlignParameters, AlignSingleLineCaseStatements, DoubleIndentClassDeclaration}

object Settings extends Build {

  lazy val buildSettings = Seq(
    organization := "com.targetedvictory",
    description := """Scala examples""",
    organizationHomepage := Some(url("http://www.github.com/targetedvictory")),
    scalaVersion := Versions.ScalaVersion,
    homepage := Some(url("https://github.com/targetedvictory/falcon")),
    promptTheme := promptThemeValue(version.value),
    updateOptions := updateOptions.value.withCachedResolution(cachedResoluton = true),
    assemblyJarName in assembly := s"falcon-${name.value}_${scalaVersion.value}-assembly-${version.value}.jar",
    assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
  ) ++ mergeStrategySettings

  val rootSettings = Seq(
    publishArtifact := false,
    publish := {},
    publishLocal := {}
  )

  lazy val defaultSettings = testSettings ++ Seq(
    scalacOptions ++= commonScalacOptions,
    javacOptions in Compile ++= commonJavacOptions,
    ivyLoggingLevel in ThisBuild := UpdateLogging.Quiet,
    resolvers := Seq(sonatypeOssSnapshotsRepo, sonatypeOssRepo),
    maxErrors := 20,
    pollInterval := 1000,
    offline := true,
    initialCommands := initialCommandsValue.mkString("\n"),
    initialCommands in console += "//import falcon._",
    initialCommands in(Compile, consoleQuick) <<= initialCommands in Compile
  ) ++ scalariformSettings

  lazy val testSettings =
    Seq(
      parallelExecution in Test := false,
      fork in Test := true,
      testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework")
    )

  lazy val mergeStrategySettings = Seq(assemblyMergeStrategy in assembly := {
    case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
    case PathList("javax", "transaction", xs@_*) => MergeStrategy.first
    case PathList("javax", "mail", xs@_*) => MergeStrategy.first
    case PathList("javax", "activation", xs@_*) => MergeStrategy.first
    case PathList("org", "apache", xs@_*) => MergeStrategy.last
    case PathList("com", "google", xs@_*) => MergeStrategy.last
    case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.discard
    case PathList(ps@_*) if ps.last endsWith "pom.properties" => MergeStrategy.first
    case PathList("META-INF", "DEPENDENCIES") => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".html" => MergeStrategy.first
    case x if x.endsWith("pom.properties") => MergeStrategy.last
    case x if x.endsWith("log4j.properties") => MergeStrategy.last
    case "application.conf" => MergeStrategy.concat
    case "unwanted.txt" => MergeStrategy.discard
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  })

  private val scalariformSettings = Seq(ScalariformKeys.preferences := ScalariformKeys.preferences.value
      .setPreference(AlignParameters, true)
      .setPreference(AlignArguments, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentClassDeclaration, true))

  private val initialCommandsValue = Seq[String](
    """
      |import System.{currentTimeMillis => now}
      |def time[T](f: => T): T = {
      |  val start = now
      |  try { f } finally { println("Elapsed: " + (now - start) + " ms") }
      |}
      | """.stripMargin
  )

  private def promptThemeValue(version: String) = PromptTheme(List(
    text("SBT", NoStyle),
    text(" | ", NoStyle),
    gitBranch(clean = NoStyle, dirty = fg(red)),
    text(s" / $version", NoStyle),
    text(" | ", NoStyle),
    currentProject(NoStyle),
    text(" > ", NoStyle)
  ))
}