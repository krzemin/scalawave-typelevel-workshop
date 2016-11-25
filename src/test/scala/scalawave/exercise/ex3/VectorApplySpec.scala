package scalawave.exercise.ex3

import org.scalatest.{MustMatchers, WordSpec}
import shapeless.test.illTyped

class VectorApplySpec extends WordSpec with MustMatchers {

  "Vector.apply" should {

//    "construct empty vector" in {
//      Vector() mustBe VNil
//    }
//
//    "construct single element vector" in {
//      Vector(true) mustBe true :: VNil
//    }
//
//    "construct vector of arbitrary number of elements" in {
//
//      Vector(10, "abc") mustBe 10 :: "abc" :: VNil
//      Vector(true, false, true) mustBe true :: false :: true :: VNil
//      Vector(1, 1, 2, 3, 5, 8, 13) mustBe 1 :: 1 :: 2 :: 3 :: 5 :: 8 :: 13 :: VNil
//      Vector(1, 2, 3, 4, 5, 6, 7, 8, 9) mustBe
//        1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: 9 :: VNil
//    }
//
//    "coincidentally work for other products too ;)" in {
//
//      Vector.apply(List(1,2,3,4,5)) mustBe 1 :: 2 :: 3 :: 4 :: 5 :: VNil
//      Vector.apply(List(1,2,3,4,5), List(1,2,3)) mustBe List(1,2,3,4,5) :: List(1,2,3) :: VNil
//
//      case class Counters(viewCounter: Int, clickCounter: Int, downloadCounter: Int)
//
//      Vector(Counters(100, 200, 150)) mustBe 100 :: 200 :: 150 :: VNil
//      Vector(Counters(0, 0, 0), Counters(3, 2, 1), Counters(1, 2, 3)) mustBe
//        Counters(0, 0, 0) :: Counters(3, 2, 1) :: Counters(1, 2, 3) :: VNil
//    }
//
//    "not overcome Scala limitations of 22" in {
//      illTyped("Vector(1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3)")
//    }
  }
}