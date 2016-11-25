package scalawave.exercise.ex1

/*
  ScalaWave Typelevel Metaprogramming Workshop
  Exercise 1

  The task is to implement typelevel computations on type-level Peano
  arithemtics. You need to satisfy requirements by providing implicit
  instances for recursive schemas defined here. The intent of this task
  is to compare value-level programming with type-level one.

  See src/test/scala/scalawave/exercise/ex1/NatSpec.scala for reference.
  Please uncomment the tests first.

  Your solution is ready when:
  - sbt "testOnly scalawave.exercise.ex1.NatSpec" passes

  Hints:
  - use recursion on implicits
  - remember to produce instances with the most precise type (use refined types)
  - make use of mathematical properties that are suggested below
*/

import scalawave.common.TypeLevelNat._

import shapeless.=:!=

trait ToInt[N <: Nat] {
  def value: Int
}

object ToInt {
  def apply[N <: Nat : ToInt]: ToInt[N] = implicitly[ToInt[N]]

  // TODO: finish implementing these implicit instances

  implicit val zeroCase: ToInt[Zero] = new ToInt[Zero] {
    val value: Int = ???
  }

  implicit def succCase[N <: Nat](implicit toInt: ToInt[N]): ToInt[Succ[N]] =
    new ToInt[Succ[N]] {
      val value: Int = ???
    }
}


trait Add[N <: Nat, M <: Nat] {
  type Result <: Nat
}

object Add {
  /*
    This combinator is defined to bring convenient syntax for performing
    type-level computation and bringing the result to the value level.
    We may then use our type-level computation in a following way:

    Add[Zero, Succ[Zero]].toInt // should be 1: Int
  */
  def apply[N <: Nat, M <: Nat](implicit add: Add[N, M]) = new {
    def toInt(implicit toInt: ToInt[add.Result]): Int = toInt.value
  }

  // TODO: implement implicit instances here

  // make use of property that for any natural number N:
  // 0 + N = N
  implicit def zeroCase = ???

  // make use of property that for any natural numbers N, M:
  // (1 + N) + M = 1 + (N + M)
  implicit def succCase = ???
}


// NOTE:  We want the instances to be only for N <= M and compile error otherwise

class Lte[N <: Nat, M <: Nat]

object Lte {

  // TODO: implement implicit instances here

  // make use of property that for any natural number N:
  // 0 <= N
  implicit def zeroCase = ???

  // (1 + N) <= (1 + M) iff N <= M
  implicit def succCase = ???
}


// NOTE:  We want the instances to be only for N < M and compile error otherwise

class Lt[N <: Nat, M <: Nat]

object Lt {

  // TODO: implement implicit instance here

  // make use of property that for any natural numbers N, M:
  // N < M if and only if N <= M and N != M

  implicit def lt = ???
}
