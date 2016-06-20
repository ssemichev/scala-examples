package exercises

import common.UnitTestSpec

class PrimeNumbersSpec extends UnitTestSpec {

  behavior of "PrimeNumbers"

  it should "find prime numbers using sieveFunctional implementation" in {
    PrimeNumbers.sieveFunctional(4) shouldBe Vector(3, 5, 7, 11)
  }

  it should "find prime numbers using primesIterative implementation" in {
    PrimeNumbers.primesIterative(11) shouldBe Vector(3, 5, 7, 11)
  }
}
