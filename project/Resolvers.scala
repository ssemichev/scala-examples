import sbt._

object Resolvers {

  lazy val sonatypeOssRepo            = "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

  lazy val sonatypeOssSnapshotsRepo   = "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
}