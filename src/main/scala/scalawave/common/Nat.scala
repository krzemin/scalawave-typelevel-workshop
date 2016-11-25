package scalawave.common

object ValueLevelNat {

  sealed trait Nat

  case object Zero extends Nat

  case class Succ(prev: Nat) extends Nat
}

object TypeLevelNat {

  sealed trait Nat

  class Zero extends Nat

  class Succ[N <: Nat] extends Nat
}