package scalawave.exercise.ex4.part2

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 4, part II

  This exercise is continuation of typeclass derivation task for JsonEncoder. In this
  part we focus on improving encoding for product types so that they contain proper
  labels. We don't want to encode case classes or tuples as a JSON array, but arther
  as a JSON object with keys corresponding to case class field names (or "_1", "_2", ...
  in case of tuples).

  See src/test/scala/scalawave/exercise/ex4/part2/JsonEncoderSpec.scala for reference.

  The idea is to use capture labels as very precise types (called singleton types),
  and derive type classes for representation with enriched type information. When
  defining instance for products, we want to materialize this type-level information
  about labels and use it to encode the JSON object keys.

  Please uncomment the tests first.

  Good luck.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex4.part2.JsonEncoderSpec" passes

  Hints:
  - replace JsArr with JsObj for product encoding
  - use LabelledGeneric, take a look at FieldType[K, V] type alias
  - use implicit Witness.Aux to materialize the value-level label from singleton type
*/

import shapeless._
import shapeless.labelled._

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
