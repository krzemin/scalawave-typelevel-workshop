package scalawave.exercise.ex4.part4

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 4, part IV

  This exercise is continuation of typeclass derivation task for JsonEncoder. In this
  part we focus on providing support for nested products, recursive data structures and
  more convenient encoding of standard scala types like Option, List and Map.

  See src/test/scala/scalawave/exercise/ex4/part4/JsonEncoderSpec.scala for reference.

  Please uncomment the tests first.

  Some of them will not pass due to divergent implicit error. The idea is to use shapeless.Lazy
  in certain places to defer resolving implicits.

  Good luck.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex4.part4.JsonEncoderSpec" passes

  Hints:
  - re-use your solution from part III
  - where do you need to put shapeless.Lazy?
  - define direct implicit instances for Option, List, Map
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
