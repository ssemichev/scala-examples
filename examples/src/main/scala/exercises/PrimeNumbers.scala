package exercises

import scala.collection.mutable

object PrimeNumbers {

  private def sieve(s: Stream[Int]): Stream[Int] = {
    s.head #:: sieve(s.tail.filter(_ % s.head != 0))
  }

  def sieveFunctional(n: Int): Vector[Int] = {
    val primes = sieve(Stream.from(3, 2))
    primes.take(n).toVector
  }

  def sieveFunctionalWithInputSize(end: Int): Vector[Int] = {

    val n = end match {
      case 300000  => 25996
      case 600000  => 25996
      case 900000  => 25996
      case 1200000 => 25996
      case 1500000 => 25996
    }

    val primes = sieve(Stream.from(3, 2))
    primes.take(1000).toVector
  }

  def calculatePrimesStream(end: Int): List[Int] = {
    val odds = Stream.from(3, 2).takeWhile(_ <= Math.sqrt(end.toDouble).toInt)
    val composites = odds.flatMap(i => Stream.from(i * i, 2 * i).takeWhile(_ <= end))
    Stream.from(3, 2).takeWhile(_ <= end).diff(composites).toList
  }

  def primesIterative(end: Int): List[Int] = {
    val primeIndices = mutable.ArrayBuffer.fill((end + 1) / 2)(1)

    val intSqrt = Math.sqrt(end.toDouble).toInt
    for (i <- 3 to end by 2 if i <= intSqrt) {
      for (nonPrime <- i * i to end by 2 * i) {
        primeIndices.update(nonPrime / 2, 0)
      }
    }

    (for (i <- primeIndices.indices if primeIndices(i) == 1) yield 2 * i + 1).tail.toList
  }
}

