package exercises

import common.UnitTestSpec

class MaxDiffSpec extends UnitTestSpec {

  it should "find max diff using customFunctional implementation" in {
    MaxDiff.customFunctional(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find max diff using customOneLoop implementation" in {
    MaxDiff.customOneLoop(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find max diff using withStandardMinMax implementation" in {
    MaxDiff.withStandardMinMax(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }

  it should "find max diff using withStandardSort implementation" in {
    MaxDiff.withStandardSort(Array(0, 1, 2, 3, 4, 5)) shouldBe 5
  }
}
