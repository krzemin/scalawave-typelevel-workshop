package scalawave.example.json_implicit_macro

import org.scalatest.{MustMatchers, WordSpec}
import shapeless.test.illTyped

import scalawave.common.Json._

class JsonEncoderSpec extends WordSpec with MustMatchers {

  import JsonEncoderSyntax._

  "JsonEncoder" should {

    "encode primitive types" in {

      10.toJson mustBe JsNum(10)
      "abc".toJson mustBe JsStr("abc")
      true.toJson mustBe JsBool(true)
      false.toJson mustBe JsBool(false)
      3.5.toJson mustBe JsNum(3.5)
    }

    "encode case classes" in {

      case class Car(brand: String, model: String, productionYear: Int)

      Car("Aston Martin", "DB5", 1964).toJsonString mustBe
        """{"brand": "Aston Martin", "model": "DB5", "productionYear": 1964}"""
      Car("Fiat", "126P", 1975).toJsonString mustBe
        """{"brand": "Fiat", "model": "126P", "productionYear": 1975}"""
    }

    "encode tuples" in {

      (true, "Test", 10.5).toJsonString mustBe
        """{"_1": true, "_2": "Test", "_3": 10.5}"""
      (42, false).toJsonString mustBe
        """{"_1": 42, "_2": false}"""
    }

    "encode nested case class" in {

      case class Author(firstName: String, lastName: String)
      case class Book(title: String, author: Author)

      val cormen = Book("Introduction to Algorithms", Author("Thomas", "Cormen"))

      cormen.toJsonString mustBe
        """{"title": "Introduction to Algorithms", "author": {"firstName": "Thomas", "lastName": "Cormen"}}"""
    }

    "encode nested tuples" in {

      (234, ("xyz", false), -36.6).toJsonString mustBe
        """{"_1": 234, "_2": {"_1": "xyz", "_2": false}, "_3": -36.6}"""
    }

    "catch errors nicely" in {

      case class Measurement(id: Long, value: Float)
      case class Measurements(m1: Measurement, m2: Measurement)

      val m1 = Measurement(1, 2.0f)
      val m2 = Measurements(m1, m1)

      illTyped("m1.toJson", "Can't find implicit instance for JsonEncoder\\[scala.Float\\]\\!")
      illTyped("m2.toJson", "Can't find implicit instance for JsonEncoder\\[scala.Float\\]\\!")
    }

    "lack support for coproduct types" in {

      sealed trait Vehicle
      case class Car(model: String, maxMPH: Double) extends Vehicle
      case class Bike(wheels: Int, colour: String) extends Vehicle

      case class Driver(name: String, vehicle: Vehicle)

      val john = Driver("John", Car("Audi", 180.3))
      val max = Driver("Max", Bike(4, "red"))

      illTyped("john.toJson", "Can't find implicit instance for JsonEncoder\\[scalawave.example.json_implicit_macro.JsonEncoderSpec.Vehicle\\]\\!")
      illTyped("max.toJson", "Can't find implicit instance for JsonEncoder\\[scalawave.example.json_implicit_macro.JsonEncoderSpec.Vehicle\\]\\!")
    }

    "lack support for encode recursive types" in {

      sealed trait MyList[+T]
      case object MyNil extends MyList[Nothing]
      case class MyCons[T](head: T, tail: MyList[T]) extends MyList[T]

      val myObj = MyCons("abc", MyCons("def", MyCons("ghi", MyNil)))

      illTyped("myObj.toJson", "Can't find implicit instance for JsonEncoder\\[scalawave.example.json_implicit_macro.JsonEncoderSpec.MyList\\]\\!")
    }

  }

}