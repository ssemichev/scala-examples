import Configs.BenchmarkTest
import Tasks._
import sbt.Keys._
import sbt._

object Testing {

  private lazy val benchmarkSettings =
    inConfig(BenchmarkTest)(Defaults.testSettings) ++
        Seq(
          fork in BenchmarkTest := true,
          parallelExecution in BenchmarkTest := false,
          logBuffered := false,
          scalaSource in BenchmarkTest := baseDirectory.value / "src/bench/scala")

  lazy val settings = benchmarkSettings ++ Seq(
    testAll <<= (test in BenchmarkTest).dependsOn(test in Test)
  )
}