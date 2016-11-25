package scalawave.exercise.ex4.part3

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 4, part III

  This exercise is continuation of typeclass derivation task for JsonEncoder. In this
  part we focus on providing instances for algebraic data types (simple sealed trait
  hierarchies).

  See src/test/scala/scalawave/exercise/ex4/part3/JsonEncoderSpec.scala for reference.

  The idea is to use shapeless encoding for coproducts and derive instances for them.

  Please uncomment the tests first.

  Good luck.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex4.part3.JsonEncoderSpec" passes

  Hints:
  - you may re-use your solution from part II
  - what is LabelledGeneric representation of scala coproducts (eithers or algebraic data types)?
  - provide type-class instances for shapeless coproducts (CNil and :+:)
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
