package scalawave.exercise.ex4.part3

import org.scalatest.{MustMatchers, WordSpec}

class JsonEncoderSpec extends WordSpec with MustMatchers {

  import JsonEncoderSyntax._

  "JsonEncoder" should {

//    "encode primitive types" in {
//
//      10.toJsonString mustBe "10"
//      "abc".toJsonString mustBe "\"abc\""
//      true.toJsonString mustBe "true"
//      false.toJsonString mustBe "false"
//      3.5.toJsonString mustBe "3.5"
//    }
//
//    "encode case classes" in {
//
//      case class Car(brand: String, model: String, productionYear: Int)
//
//      Car("Aston Martin", "DB5", 1964).toJsonString mustBe
//        """{"brand": "Aston Martin", "model": "DB5", "productionYear": 1964}"""
//      Car("Fiat", "126P", 1975).toJsonString mustBe
//        """{"brand": "Fiat", "model": "126P", "productionYear": 1975}"""
//    }
//
//    "encode tuples" in {
//
//      (true, "Test", 10.5).toJsonString mustBe
//        """{"_1": true, "_2": "Test", "_3": 10.5}"""
//      (42, false).toJsonString mustBe
//        """{"_1": 42, "_2": false}"""
//    }
//
//    "encode algebraic data types" in {
//
//      sealed trait Shape
//      case class Circle(radius: Double) extends Shape
//      case class Rectangle(width: Double, height: Double) extends Shape
//      case class Triangle(a: Double, b: Double, c: Double) extends Shape
//
//      val circle: Shape = Circle(3.78)
//      val rectangle: Shape = Rectangle(10.0, 20.5)
//      val triangle: Shape = Triangle(3.0, 4.0, 5.0)
//
//      circle.toJsonString mustBe """{"Circle": {"radius": 3.78}}"""
//      rectangle.toJsonString mustBe """{"Rectangle": {"width": 10.0, "height": 20.5}}"""
//      triangle.toJsonString mustBe """{"Triangle": {"a": 3.0, "b": 4.0, "c": 5.0}}"""
//    }
  }

}
