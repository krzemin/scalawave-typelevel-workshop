package scalawave.solution.ex2

import shapeless._
import shapeless.ops.nat._


/*
    N - type-encoded number of elements hold inside vector
    T - type of elements in vector

    We use List[T] as underlying data representation
 */
case class Vector[N <: Nat, +T](toList: List[T]) {

  def ::[U >: T](elem: U): Vector[Succ[N], U] =
    Vector[Succ[N], U](elem :: this.toList)

  def ++[M <: Nat, U >: T](suffix: Vector[M, U])
                          (implicit add: Sum[N, M]): Vector[add.Out, U] =
    Vector[add.Out, U](toList ++ suffix.toList)

  def head(implicit ev: LT[_0, N]): T = // alternative evidence: (implicit ev: N =:!= _0):
    toList.head

  def at[K <: Nat](implicit lt: LT[K, N], toInt: ToInt[K]): T =
    toList(toInt.apply)

  override def toString: String =
    s"Vector[_${toList.size}](${toList.mkString(", ")})"
}

object VNil extends Vector[_0, Nothing](Nil)
