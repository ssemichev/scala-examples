package exercises

import org.scalameter.api._

object PrimeNumbersBenchmark extends Bench.LocalTime {

  val sizes = Gen.range("size")(300000, 1500000, 300000)

  val ranges = for {
    size <- sizes
  } yield (0 until size).toArray

  performance of "PrimeNumbers" in {
    measure method "primesIterative" in {
      using(ranges) in {
        range => PrimeNumbers.primesIterative(range.length)
      }
    }
  }

  performance of "PrimeNumbers" in {
    measure method "sieveFunctionalWithInputSize" in {
      using(ranges) in {
        range => PrimeNumbers.sieveFunctionalWithInputSize(range.length)
      }
    }
  }

//  performance of "PrimeNumbers" in {
//    measure method "calculatePrimesStream" in {
//      using(ranges) in {
//        range => PrimeNumbers.calculatePrimesStream(range.length)
//      }
//    }
//  }
}
