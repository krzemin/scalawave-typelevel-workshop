package scalawave.exercise.ex4.part4

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
//
//    "encode nested case class" in {
//
//      case class Author(firstName: String, lastName: String)
//      case class Book(title: String, author: Author)
//
//      val cormen = Book("Introduction to Algorithms", Author("Thomas", "Cormen"))
//
//      cormen.toJsonString mustBe
//        """{"title": "Introduction to Algorithms", "author": {"firstName": "Thomas", "lastName": "Cormen"}}"""
//    }
//
//    "encode nested tuples" in {
//
//      (234, ("xyz", false), -36.6).toJsonString mustBe
//        """{"_1": 234, "_2": {"_1": "xyz", "_2": false}, "_3": -36.6}"""
//    }
//
//    "encode recursive data types" in {
//
//      sealed trait MyList[+A]
//      case object MyNil extends MyList[Nothing]
//      case class MyCons[+T](head: T, tail: MyList[T]) extends MyList[T]
//
//      val myListInstance: MyList[Int] = MyCons(1, MyCons(2, MyNil))
//
//      myListInstance.toJsonString mustBe
//        """{"MyCons": {"head": 1, "tail": {"MyCons": {"head": 2, "tail": {"MyNil": {}}}}}}"""
//    }
//
//    "encode standard Option type" in {
//
//      val noneInt: Option[Int] = None
//      val someInt: Option[Int] = Some(123)
//
//      noneInt.toJsonString mustBe """null"""
//      someInt.toJsonString mustBe """123"""
//    }
//
//    "encode standard List type" in {
//
//      List[Int](1, 2, 3).toJsonString mustBe "[1, 2, 3]"
//    }
//
//    "encode standard Map type" in {
//
//      Map("one" -> false, "two" -> true, "three" -> false).toJsonString mustBe
//        """{"one": false, "two": true, "three": false}"""
//    }
//
//    "encode list of options" in {
//
//      val opts = List(None, Some("abc"), None, Some("xyz"))
//
//      opts.toJsonString mustBe """[null, "abc", null, "xyz"]"""
//    }
//
//    "encode list of shapes" in {
//
//      sealed trait Shape
//      case class Circle(radius: Double) extends Shape
//      case class Rectangle(width: Double, height: Double) extends Shape
//      case class Triangle(a: Double, b: Double, c: Double) extends Shape
//
//      val shapes = List[Shape](Circle(1.0), Rectangle(1.0, 1.0), Triangle(1.0, 1.0, 1.0))
//
//      shapes.toJsonString mustBe
//        """[{"Circle": {"radius": 1.0}}, {"Rectangle": {"width": 1.0, "height": 1.0}}, {"Triangle": {"a": 1.0, "b": 1.0, "c": 1.0}}]"""
//    }
//
//    "encode foo/bar/qux example" in {
//
//      sealed trait Foo
//      case class Bar(xs: List[String]) extends Foo
//      case class Qux(i: Int, d: Option[Double]) extends Foo
//
//      val foo: Foo = Qux(13, Some(14.0))
//
//      foo.toJsonString mustBe """{"Qux": {"i": 13, "d": 14.0}}"""
//    }
  }
}
