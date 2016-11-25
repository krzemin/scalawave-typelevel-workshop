package scalawave.solution.ex1

import shapeless.=:!=

import scala.annotation.implicitNotFound

import scalawave.common.TypeLevelNat._


trait ToInt[N <: Nat] {
  def value: Int
}

object ToInt {
  def apply[N <: Nat : ToInt]: ToInt[N] = implicitly[ToInt[N]]

  implicit val zeroCase: ToInt[Zero] = new ToInt[Zero] {
    val value: Int = 0
  }

  implicit def succCase[N <: Nat](implicit toInt: ToInt[N]): ToInt[Succ[N]] =
    new ToInt[Succ[N]] {
      val value: Int = 1 + toInt.value
    }
}


trait Add[N <: Nat, M <: Nat] {
  type Result <: Nat
}

object Add {

  // this enables syntax Add[N, M].toInt
  def apply[N <: Nat, M <: Nat](implicit add: Add[N, M]) = new {
    def toInt(implicit toInt: ToInt[add.Result]): Int = toInt.value
  }

  // 0 + M = M
  implicit def zeroCase[M <: Nat]: Add[Zero, M] { type Result = M } = null

  // (1 + N) + M = 1 + (N + M)
  implicit def succCase[N <: Nat, M <: Nat]                     // for any natural N, M
    (implicit add: Add[N, M])                                   // if we can add them (N + M)
    : Add[Succ[N], M] { type Result = Succ[add.Result] } =      // then result of adding (N+1) + M is 1 + (N + M)
    null                                                        // proof by construction
}

@implicitNotFound("Can't prove that ${N} is less or equal ${M}")
class Lte[N <: Nat, M <: Nat]

object Lte {

  // 0 <= M
  implicit def zeroCase[M <: Nat]: Lte[Zero, M] = null

  // (1 + N) <= (1 + M) iff N <= M
  implicit def succCase[N <: Nat, M <: Nat](implicit lte1: Lte[N, M]): Lte[Succ[N], Succ[M]] = null
}

@implicitNotFound("Can't prove that ${N} is less than ${M}")
class Lt[N <: Nat, M <: Nat]

object Lt {

  implicit def lt[N <: Nat, M <: Nat](implicit neq: N =:!= M,
                                      lte: Lte[N, M]): Lt[N, M] =
    null
}

// alternative solution with explicit recursion
//object Lt {
//
//  // 0 < 1 + M
//  implicit def zeroLCase[M <: Nat]: Lt[Zero, Succ[M]] = null
//
//  // (1 + N) < (1 + M) iff N < M
//  implicit def succCase[N <: Nat, M <: Nat](implicit lte: Lt[N, M]): Lt[Succ[N], Succ[M]] =
//    null
//}

