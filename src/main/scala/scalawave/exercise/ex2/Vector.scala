package scalawave.exercise.ex2

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 2

  The task is to implement all methods of type-sized vector. Having
  additional type information about length, we want to enforce
  additional constraints on some methods:
  - invocation of `head` method on empty list should not compile
  - invocation of `at` method should not compile instead of throwing IndexOutOfBound
  - `::` and `++` should maintain correct size information

  See src/test/scala/scalawave/exercise/ex2/VectorSpec.scala for reference.
  Please uncomment the tests first.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex2.VectorSpec" passes

  Hints:
  - you may need to modify signatures of certain methods (like add implicit parameters)
  - use type-level computations on natural numbers defined in shapeless; you may
    be particularly interested in shapeless.ops.nat.Sum and shapeless.ops.nat.LT
*/

import shapeless._
import shapeless.ops.nat._

/*
    N - type-encoded number of elements hold inside vector
    T - type of elements in vector

    We use List[T] as underlying data representation
 */
case class Vector[N <: Nat, +T](toList: List[T]) {

  // TODO: just construct an instance of Vector[Succ[N], T]; use :: on scala List
  def ::[U >: T](elem: U): Vector[Succ[N], U] = ???

  // TODO: write returned type explicitly; use ++ on scala List
  def ++[M <: Nat, U >: T](suffix: Vector[M, U]) = ???

  // should not compile for empty vectors
  def head: T = ???

  // should not compile if index K is out of bound given by M
  def at[K <: Nat]: T = ???

  override def toString: String =
    s"Vector[_${toList.size}](${toList.mkString(", ")})"
}

object VNil extends Vector[_0, Nothing](Nil)
