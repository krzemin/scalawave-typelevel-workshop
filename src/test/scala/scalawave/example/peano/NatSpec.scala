package scalawave.example.peano

import org.scalatest.{MustMatchers, WordSpec}

import scalawave.common.ValueLevelNat._

class NatSpec extends WordSpec with MustMatchers {

  val three = Succ(Succ(Succ(Zero)))
  val four = Succ(three)

  "Nat" when {
    "toInt" should {
      "convert encoding of 0" in {
        Nat.toInt(Zero) mustBe 0
      }
      "convert encoding of 3" in {
        Nat.toInt(three) mustBe 3
      }
      "convert encoding of 4" in {
        Nat.toInt(four) mustBe 4
      }
    }

    "add" should {
      "add 0 to 0" in {
        Nat.toInt(Nat.add(Zero, Zero)) mustBe 0
      }
      "add 4 to 0" in {
        Nat.toInt(Nat.add(four, Zero)) mustBe 4
      }
      "add 3 to 3" in {
        Nat.toInt(Nat.add(three, three)) mustBe 6
      }
      "add 3 to 4" in {
        Nat.toInt(Nat.add(three, four)) mustBe 7
      }
    }

    "lte" should {
      "compare 0 to 0" in {
        Nat.lte(Zero, Zero) mustBe true
      }
      "compare 3 to 4" in {
        Nat.lte(three, four) mustBe true
      }
      "compare 4 to 3" in {
        Nat.lte(four, three) mustBe false
      }
      "compare 4 to 4" in {
        Nat.lte(four, four) mustBe true
      }
    }

    "lt" should {
      "compare 0 to 0" in {
        Nat.lt(Zero, Zero) mustBe false
      }
      "compare 3 to 4" in {
        Nat.lt(three, four) mustBe true
      }
      "compare 4 to 3" in {
        Nat.lt(four, three) mustBe false
      }
      "compare 4 to 4" in {
        Nat.lt(four, four) mustBe false
      }
    }
  }
}