import sbt._

object Configs {
  val BenchmarkTest = config("bench") extend Test
  val all = Seq(BenchmarkTest)
}