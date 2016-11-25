package scalawave.exercise.ex4.part1

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 4, part I

  This exercise is about typeclass derivation for JsonEncoder. In this part we are
  interested in encoding primitive types and products like tuples and case classes.
  Each primitive should convert to corresponding JSON type, defined in
  package scalawave.exercise.ex4. Product types should be convertible to json arrays.

  See src/test/scala/scalawave/exercise/ex4/part1/JsonEncoderSpec.scala for reference.

  To pass the tests you need to implement type class instances for three different
  kind of types:

  1. primitives like Int, String, Boolean, Double (these 4 are sufficient)
  2. shapeless product (HList)
  3. generic instance that takes scala type, convert it to shapeless representation,
     and use JsonEncoder instance defined for shapeless representation to actually
     encode the data type

  Please uncomment the tests first.

  Good luck.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex4.part1.JsonEncoderSpec" passes

  Hints:
  - define your instances keeping precise type information (JsonEncoder.Aux might be helpful)
  - you can concatenate two json arrays (JsArr) with ++ and prepend an element with +:
  - JsonEncoder.makeEnc might be helpful when defining type class instances
*/

import shapeless._

import scalawave.common.Json


trait JsonEncoder[T] {
  type Out <: Json.JsValue
  def toJson(obj: T): Out
}

object JsonEncoder {
  import Json._

  type Aux[T, O <: JsValue] = JsonEncoder[T] { type Out = O }

  def apply[T](implicit enc: JsonEncoder[T]): JsonEncoder[T] = enc

  def makeEnc[T, O <: JsValue](enc: T => O): JsonEncoder.Aux[T, O] = new JsonEncoder[T] {
    type Out = O
    def toJson(obj: T): O = enc(obj)
  }

  // TODO: write your implicit instances here
}

object JsonEncoderSyntax {

  implicit class JsonEncoderOps[T, O <: Json.JsValue](val obj: T) extends AnyVal {
    def toJson(implicit enc: JsonEncoder.Aux[T, O]): O = enc.toJson(obj)
    def toJsonString(implicit enc: JsonEncoder.Aux[T, O]) = Json.toString(obj.toJson)
  }
}

