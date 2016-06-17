package exercises

import common._

object MaxDiff {

  def customFunctional(input: Array[Int]): Int = {
    validate(input)

    val (min, max) = ((input.head, input.head) /: input.tail) {
      (acc: (Int, Int), item: Int) => (acc._1.min(item), acc._1.max(item))
    }

    max - min
  }

  def customOneLoop(input: Array[Int]): Int = {
    validate(input)

    var min = input.head
    var max = min

    input.tail.foreach { item =>
      min = min.min(item)
      max = max.max(item)
    }

    max - min
  }

  def withStandardMinMax(input: Array[Int]): Int = {
    validate(input)

    input.max - input.min
  }

  def withStandardSort(input: Array[Int]): Int = {
    validate(input)

    val sorted = input.sorted
    sorted.max - sorted.min
  }

  private def validate(input: Array[Int]): Unit = {
    require(input.isDefined && input.length > 0, "input cannot be null or empty")
  }
}
