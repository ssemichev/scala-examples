package exercises

import org.scalameter.api._
import util.Random.shuffle

class MaxDiffBenchmark extends Bench.LocalTime {

  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges = for {
    size <- sizes
  } yield (0 until size).toArray

  val rangesRandom = for {
    size <- sizes
  } yield shuffle(0 to size).toArray

  performance of "MaxDiff" in {
    measure method "customFunctional" in {
      using(ranges) in {
        range => MaxDiff.customFunctional(range)
      }
    }

    measure method "customFunctional with random values" in {
      using(rangesRandom) in {
        range => MaxDiff.customFunctional(range)
      }
    }

    measure method "customOneLoop" in {
      using(ranges) in {
        range => MaxDiff.customOneLoop(range)
      }
    }

    measure method "customOneLoop with random values" in {
      using(rangesRandom) in {
        range => MaxDiff.customOneLoop(range)
      }
    }

    measure method "withStandardMinMax" in {
      using(ranges) in {
        range => MaxDiff.withStandardMinMax(range)
      }
    }

    measure method "withStandardSort" in {
      using(ranges) in {
        range => MaxDiff.withStandardSort(range)
      }
    }
  }
}
