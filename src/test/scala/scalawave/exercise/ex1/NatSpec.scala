package scalawave.exercise.ex1

import org.scalatest.{MustMatchers, WordSpec}
import shapeless.test.illTyped

import scalawave.common.TypeLevelNat._

class NatSpec extends WordSpec with MustMatchers {

  type Three = Succ[Succ[Succ[Zero]]]
  type Four = Succ[Three]

  "Nat" when {
//    "toInt" should {
//      "convert encoding of 0" in {
//        ToInt[Zero].value mustBe 0
//      }
//      "convert encoding of 3" in {
//        ToInt[Three].value mustBe 3
//      }
//      "convert encoding of 4" in {
//        ToInt[Four].value mustBe 4
//      }
//    }
//
//    "add" should {
//      "add 0 to 0" in {
//        Add[Zero, Zero].toInt mustBe 0
//      }
//      "add 4 to 0" in {
//        Add[Four, Zero].toInt mustBe 4
//      }
//      "add 3 to 3" in {
//        Add[Three, Three].toInt mustBe 6
//      }
//      "add 3 to 4" in {
//        Add[Three, Four].toInt mustBe 7
//      }
//    }
//
//    "lte" should {
//      "compare 0 to 0" in {
//        implicitly[Lte[Zero, Zero]]
//      }
//      "compare 3 to 4" in {
//        implicitly[Lte[Three, Four]]
//      }
//      "compare 4 to 3" in {
//        illTyped("implicitly[Lte[Four, Three]]")
//      }
//      "compare 4 to 4" in {
//        implicitly[Lte[Four, Four]]
//      }
//    }
//
//    "lt" should {
//      "compare 0 to 0" in {
//        illTyped("implicitly[Lt[Zero, Zero]]")
//      }
//      "compare 3 to 4" in {
//        implicitly[Lt[Three, Four]]
//      }
//      "compare 4 to 3" in {
//        illTyped("implicitly[Lt[Four, Three]]")
//      }
//      "compare 4 to 4" in {
//        illTyped("implicitly[Lt[Four, Four]]")
//      }
//    }
  }
}