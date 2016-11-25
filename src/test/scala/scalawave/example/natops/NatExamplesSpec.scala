package scalawave.example.natops

import org.scalatest.{MustMatchers, WordSpec}

import scalawave.common.TypeLevelNat._

class NatExamplesSpec extends WordSpec with MustMatchers {

  type Three = Succ[Succ[Succ[Zero]]]
  type Four = Succ[Three]

  "toInt" should {

    "convert encoding of 0" in {
      ToInt[Zero].value mustBe 0
    }

    "convert encoding of 3" in {
      ToInt[Three].value mustBe 3
    }

    "convert encoding of 4" in {
      ToInt[Four].value mustBe 4
    }
  }

  "add" should {

    "add 0 to 0" in {
      Add[Zero, Zero].toInt mustBe 0
    }

    "add 4 to 0" in {
      Add[Four, Zero].toInt mustBe 4
    }

    "add 3 to 3" in {
      Add[Three, Three].toInt mustBe 6
    }

    "add 3 to 4" in {
      Add[Three, Four].toInt mustBe 7
    }
  }

  "mul" should {

    "mul 0 by 0" in {
      Mul[Zero, Zero].toInt mustBe 0
    }

    "mul 4 by 0" in {
      Mul[Four, Zero].toInt mustBe 0
    }

    "mul 3 by 3" in {
      Mul[Three, Three].toInt mustBe 9
    }

    "mul 3 by 4" in {
      Mul[Three, Four].toInt mustBe 12
    }
  }

  "pow" should {

    "raise 3 to 0" in {
      Pow[Three, Zero].toInt mustBe 1
    }

    "raise 3 to 1" in {
      Pow[Three, Succ[Zero]].toInt mustBe 3
    }

    "raise 3 to 3" in {
      Pow[Three, Three].toInt mustBe 27
    }

    "raise 4 to 3" in {
      Pow[Four, Three].toInt mustBe 64
    }
  }

  "isEven" should {

    "be true for even numbers" in {
      IsEven[Zero].value mustBe true
      IsEven[Four].value mustBe true
    }

    "be false for odd numbers" in {
      IsEven[Succ[Zero]].value mustBe false
      IsEven[Three].value mustBe false
      IsEven[Succ[Four]].value mustBe false
    }
  }

  "fib" should {

    "compute fibonacci sequence" in {
      Fib[Zero].toInt mustBe 1
      Fib[Succ[Zero]].toInt mustBe 1
      Fib[Three].toInt mustBe 3
      Fib[Four].toInt mustBe 5
      Fib[Succ[Four]].toInt mustBe 8
    }
  }
}
