package scalawave.exercise.ex3

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 3

  The task is to implement convenient constructor method (Vector.apply) that
  takes arbitrary number of elements and constructs the Vector instance keeping
  the type-level size information.

  The idea is to define this method for product types (which tuples are),
  convert it to shapeless generic product representation (HList), compute its
  type-level length and convert to standard scala List. Having the last two,
  we can construct our Vector. Good luck :)

  See src/test/scala/scalawave/exercise/ex3/VectorApplySpec.scala for reference.
  Please uncomment the tests first.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex3.VectorApplySpec" passes

  Hints:
  - use shapeless HList and Generic.Aux
  - you can compute type-level length of hlist using shapeless.ops.hlist.Length
  - you can convert hlist to normal scala list using shapeless.ops.hlist.ToList
*/

import shapeless._
import shapeless.ops.nat._
import shapeless.ops.hlist._

/*
    N - type-encoded number of elements hold inside vector
    T - type of elements in vector

    We use List[T] as underlying data representation
 */
case class Vector[N <: Nat, +T](toList: List[T]) {

  // TODO: copy your solution from ex2

  def ::[U >: T](elem: U): Vector[Succ[N], U] = ???
  def ++[M <: Nat, U >: T](suffix: Vector[M, U]) = ???
  def head: T = ???
  def at[K <: Nat]: T = ???

  override def toString: String =
    s"Vector[${toList.size}](${toList.mkString(", ")})"
}

object VNil extends Vector[_0, Nothing](Nil)

object Vector {
  def apply[T](): Vector[_0, T] = VNil
  def apply[T](e1: T): Vector[Succ[_0], T] = e1 :: VNil

  // TODO: write result type explicitly
  def apply[P <: Product, L <: HList, T](p: P) = ???
}
