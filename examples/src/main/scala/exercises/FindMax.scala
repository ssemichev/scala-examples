package exercises

import common._

import scala.annotation.tailrec

object FindMax {

  def withFoldLeft(input: Array[Int]): Int = {
    validate(input)

    (input.head /: input) {
      (acc: Int, item: Int) => acc.max(item)
    }
  }

  def withTailRecursion(input: Array[Int]): Int = {
    validate(input)

    val length = input.length
    @tailrec
    def findMax(max: Int, next: Int): Int = {
      if (next >= length) max
      else findMax(max.max(input(next)), next + 1)
    }

    findMax(input.head, 1)
  }

  def withLoop(input: Array[Int]): Int = {
    validate(input)

    var max = input.head

    input.foreach { item =>
      max = max.max(item)
    }

    max
  }

  def loopIf(input: Array[Int]): Int = {
    validate(input)

    var max = input.head

    input.foreach { item =>
      if (item > max) max = item
    }

    max
  }

  def withStandardMax(input: Array[Int]): Int = {
    validate(input)

    input.max
  }

  private def validate(input: Array[Int]): Unit = {
    require(input.isDefined && input.length > 0, "input cannot be null or empty")
  }
}
