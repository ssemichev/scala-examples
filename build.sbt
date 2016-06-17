import Common._
import Settings._
import Tasks._
import sbt.Keys._

name := "scala-examples"

// ---------------------------------------------------------------------------------------------------------------------
// Root Project
// ---------------------------------------------------------------------------------------------------------------------

lazy val publishedProjects = Seq[ProjectReference](
  CommonProject,
  Examples
)

lazy val root = BuildProject("scala-examples-root", ".")
    .settings(rootSettings: _*)
    .settings(Testing.settings: _*)
    .aggregate(publishedProjects: _*)

// ---------------------------------------------------------------------------------------------------------------------
// Modules
// ---------------------------------------------------------------------------------------------------------------------

lazy val CommonProject = BuildProject("common")
    .settings(
      libraryDependencies ++= Dependencies.common,
      makeVersionPropertiesTask := makeVersionProperties((resourceManaged in Compile).value, gitHeadCommitShaTask.value),
      resourceGenerators in Compile <+= makeVersionPropertiesTask
    )

lazy val Examples = BuildProject("examples", "examples")
    .settings(
      libraryDependencies ++= Dependencies.examples
    )
    .dependsOn(CommonProject % "compile->compile;test->test")

gitHeadCommitShaTask in ThisBuild := gitHeadCommitSha
printClassPathTask in ThisBuild <<= printClassPath

addCommandAlias("all", "alias")






