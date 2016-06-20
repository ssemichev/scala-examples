package exercises

import common.UnitTestSpec

class FindMaxSpec extends UnitTestSpec {

  behavior of "MaxDiff"

  it should "find max using withFold implementation" in {
    FindMax.withFold(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find max using withTailRecursion implementation" in {
    FindMax.withTailRecursion(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
    FindMax.withTailRecursion(Array(0, 1, 2, 3, 4, 5, 10)) shouldBe 10
    FindMax.withTailRecursion(Array(10, 1, 2, 3, 4, 5)) shouldBe 10
    FindMax.withTailRecursion(Array(0, 1, 2, 30, 4, 5)) shouldBe 30
  }

  it should "find max using customOneLoop implementation" in {
    FindMax.withOneLoop(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find max using withStandardMinMax implementation" in {
    FindMax.withStandardMax(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }
}
