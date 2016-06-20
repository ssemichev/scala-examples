package exercises

import org.scalameter.api._
import util.Random.shuffle

object MaxDiffBenchmark extends Bench.LocalTime {

  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges = for {
    size <- sizes
  } yield (0 until size).toArray

  val rangesRandom = for {
    size <- sizes
  } yield shuffle(0 to size).toArray

  performance of "MaxDiff" in {
    measure method "withFoldLeft" in {
      using(ranges) in {
        range => MaxDiff.withFoldLeft(range)
      }
    }

    measure method "withFoldLeft with random values" in {
      using(rangesRandom) in {
        range => MaxDiff.withFoldLeft(range)
      }
    }

    measure method "withTailRecursion" in {
      using(ranges) in {
        range => MaxDiff.withTailRecursion(range)
      }
    }

    measure method "withTailRecursion with random values" in {
      using(rangesRandom) in {
        range => MaxDiff.withTailRecursion(range)
      }
    }

    measure method "customOneLoop" in {
      using(ranges) in {
        range => MaxDiff.withOneLoop(range)
      }
    }

    measure method "customOneLoop with random values" in {
      using(rangesRandom) in {
        range => MaxDiff.withOneLoop(range)
      }
    }

    measure method "withStandardMinMax" in {
      using(ranges) in {
        range => MaxDiff.withStandardMinMax(range)
      }
    }

    measure method "withStandardMinMax with random values" in {
      using(rangesRandom) in {
        range => MaxDiff.withStandardMinMax(range)
      }
    }

    measure method "withStandardSort" in {
      using(ranges) in {
        range => MaxDiff.withStandardSort(range)
      }
    }

//    measure method "withStandardSort with random values" in {
//      using(rangesRandom) in {
//        range => MaxDiff.withStandardSort(range)
//      }
//    }
  }
}
