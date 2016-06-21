package exercises

import org.scalameter.api._

class SimpleBenchmark extends Bench.LocalTime {
  performance of "List" in {
    measure method "sum" in {
      (1 to 100000).sum
    }
  }
}
