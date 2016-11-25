package scalawave.example.peano

import scalawave.common.ValueLevelNat._

object Nat {

  def toInt(n: Nat): Int = n match {
    case Zero => 0
    case Succ(k) => 1 + toInt(k)
  }

  def add(n1: Nat, n2: Nat): Nat = n1 match {
    case Zero => n2
    case Succ(k) => add(k, Succ(n2))
  }

  def lte(n1: Nat, n2: Nat): Boolean = (n1, n2) match {
    case (Zero, _) => true
    case (_, Zero) => false
    case (Succ(k1), Succ(k2)) => lte(k1, k2)
  }

  def lt(n1: Nat, n2: Nat): Boolean =
    n1 != n2 && lte(n1, n2)

// alternative, direct implementation
//  def lt(n1: Nat, n2: Nat): Boolean = (n1, n2) match {
//    case (Zero, Succ(_)) => true
//    case (Succ(k1), Succ(k2)) => lt(k1, k2)
//    case _ => false
//  }
}
