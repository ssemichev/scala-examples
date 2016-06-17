import sbt.Keys._
import sbt._

object Tasks {

  lazy val gitHeadCommitShaTask = taskKey[String]("Determines the current git commit SHA")

  lazy val makeVersionPropertiesTask = taskKey[Seq[File]]("Creates a version.properties file we can find at runtime.")

  lazy val printClassPathTask = TaskKey[Unit]("print-class-path")

  lazy val testAll = TaskKey[Unit]("test-all")

  def gitHeadCommitSha: String = Process("git rev-parse HEAD").lines.head

  def makeVersionProperties(path: File, gitHeadCommitSha: String): Seq[File] = {
    val propFile = path / "version.properties"
    val content = "version=%s" format gitHeadCommitSha
    IO.write(propFile, content)
    Seq(propFile)
  }

  // scalastyle:off regex
  def printClassPath(): Def.Initialize[Task[Unit]] = (target, fullClasspath in Compile, compile in Compile) map { (out, cp, analysis) =>
    println("---- fullClasspath:")
    println(cp.files.map(_.getName).mkString("\n"))
    println("---- analysis:")
    println(analysis.relations.allBinaryDeps.toSeq.mkString("\n"))
    println("---- target:")
    println(out)
  }
}