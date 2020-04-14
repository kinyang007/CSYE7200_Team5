package services

import org.scalatest.{FlatSpec, Matchers}

class DuplicateSpec extends FlatSpec with Matchers{

  "DuplicateSpec" should "find duplicated tickets" in {
    Duplicate.checkNoDuplicated shouldBe true
  }

}
