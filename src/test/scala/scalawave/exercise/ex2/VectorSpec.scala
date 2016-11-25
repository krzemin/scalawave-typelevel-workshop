package scalawave.exercise.ex2

import org.scalatest.{MustMatchers, WordSpec}
import shapeless.{Succ, _0}
import shapeless.test.illTyped

class VectorSpec extends WordSpec with MustMatchers {

  "Vector" when {

//    "toList" should {
//
//      "convert empty vector to empty list" in {
//        VNil.toList mustBe Nil
//      }
//
//      "convert non-empty vector to corresponding list" in {
//        ("alice" :: "bob" :: "cecilia" :: VNil).toList mustBe
//          List("alice", "bob", "cecilia")
//      }
//    }
//
//    "++" should {
//
//      "append to empty vector" in {
//        VNil ++ (1 :: 2 :: VNil) mustBe
//          1 :: 2 :: VNil
//      }
//
//      "append to non-empty vector" in {
//        (1 :: 2 :: 3 :: VNil) ++ (4 :: 5 :: VNil) mustBe
//          (1 :: 2 :: 3 :: 4 :: 5 :: VNil)
//      }
//    }
//
//    "head" should {
//
//      "not compile for empty vector" in {
//        illTyped("VNil.head")
//        illTyped("(VNil ++ VNil).head")
//      }
//
//      "return first element for non-empty vector" in {
//        (1 :: 2 :: VNil).head mustBe 1
//        ((0 :: VNil) ++ VNil).head mustBe 0
//      }
//    }
//
//    "at" should {
//
//      type _1 = Succ[_0]
//      type _2 = Succ[_1]
//
//      "not compile for empty vectors" in {
//        illTyped("VNil.at[_0]")
//        illTyped("VNil.at[_1]")
//        illTyped("VNil.at[_2]")
//      }
//
//      "not compile if index out of bounds" in {
//        illTyped("(1 :: VNil).at[_1]")
//        illTyped("(1 :: 2 :: VNil).at[_2]")
//      }
//
//      "return indexed value" in {
//        val vec = 1 :: 2 :: 3 :: VNil
//
//        vec.at[_0] mustBe 1
//        vec.at[_1] mustBe 2
//        vec.at[_2] mustBe 3
//      }
//    }
//
//    "toString" should {
//
//      "print elems with number of elems" in {
//
//        VNil.toString mustBe "Vector[_0]()"
//        (1 :: 2 :: 3 :: VNil).toString mustBe "Vector[_3](1, 2, 3)"
//        ("abc" :: "xyz" :: VNil).toString mustBe "Vector[_2](abc, xyz)"
//      }
//    }
  }
}