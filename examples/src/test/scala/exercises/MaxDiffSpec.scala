package exercises

import common.UnitTestSpec

class MaxDiffSpec extends UnitTestSpec {

  behavior of "MaxDiff"

  it should "find diff using withFold implementation" in {
    MaxDiff.withFold(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find diff using withTailRecursion implementation" in {
    MaxDiff.withTailRecursion(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
    MaxDiff.withTailRecursion(Array(0, 1, 2, 3, 4, 5, 10)) shouldBe 10
    MaxDiff.withTailRecursion(Array(10, 1, 2, 3, 4, 5)) shouldBe 9
    MaxDiff.withTailRecursion(Array(0, 1, 2, 30, 4, 5)) shouldBe 30
  }

  it should "find diff using customOneLoop implementation" in {
    MaxDiff.withOneLoop(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find diff using withStandardMinMax implementation" in {
    MaxDiff.withStandardMinMax(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find diff using withStandardSort implementation" in {
    MaxDiff.withStandardSort(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }
}
