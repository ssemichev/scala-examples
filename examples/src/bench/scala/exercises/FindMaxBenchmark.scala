package exercises

import org.scalameter.api._

import scala.util.Random.shuffle

object FindMaxBenchmark extends Bench.LocalTime {

  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges = for {
    size <- sizes
  } yield (0 until size).toArray

  val rangesRandom = for {
    size <- sizes
  } yield shuffle(0 to size).toArray

  performance of "FindMax" in {
    measure method "withFoldLeft" in {
      using(ranges) in {
        range => FindMax.withFoldLeft(range)
      }
    }

    measure method "withTailRecursion" in {
      using(ranges) in {
        range => FindMax.withTailRecursion(range)
      }
    }

    measure method "withStandardMax" in {
      using(ranges) in {
        range => FindMax.withStandardMax(range)
      }
    }

    measure method "loop" in {
      using(ranges) in {
        range => FindMax.withLoop(range)
      }
    }

    measure method "loopIf" in {
      using(ranges) in {
        range => FindMax.loopIf(range)
      }
    }

    measure method "withFoldLeft with random values" in {
      using(rangesRandom) in {
        range => FindMax.withFoldLeft(range)
      }
    }

    measure method "withTailRecursion with random values" in {
      using(rangesRandom) in {
        range => FindMax.withTailRecursion(range)
      }
    }

    measure method "withStandardMax with random values" in {
      using(rangesRandom) in {
        range => FindMax.withStandardMax(range)
      }
    }

    measure method "loop with random values" in {
      using(rangesRandom) in {
        range => FindMax.withLoop(range)
      }
    }

    measure method "loopIf with random values" in {
      using(rangesRandom) in {
        range => FindMax.loopIf(range)
      }
    }
  }
}
