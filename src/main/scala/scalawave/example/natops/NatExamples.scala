package scalawave.example.natops

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
  type Aux[N <: Nat, M <: Nat, R <: Nat] = Add[N, M] { type Result = R }

  // this enables syntax Add[N, M].toInt
  def apply[N <: Nat, M <: Nat](implicit add: Add[N, M]) = new {
    def toInt(implicit toInt: ToInt[add.Result]): Int = toInt.value
  }

  // 0 + M = M
  implicit def zeroCase[M <: Nat]: Add.Aux[Zero, M, M] = new Add[Zero, M] {
    type Result = M
  }

  // (1 + N) + M = 1 + (N + M)
  implicit def succCase[N <: Nat, M <: Nat](implicit add: Add[N, M]): Add.Aux[Succ[N], M, Succ[add.Result]] =
  new Add[Succ[N], M] {
    type Result = Succ[add.Result]
  }
}


trait Mul[N <: Nat, M <: Nat] {
  type Result <: Nat
}

object Mul {
  type Aux[N <: Nat, M <: Nat, R <: Nat] = Mul[N, M] { type Result = R }

  def apply[N <: Nat, M <: Nat](implicit mul: Mul[N, M]) = new {
    def toInt(implicit toInt: ToInt[mul.Result]): Int = toInt.value
  }

  // 0 * M == 0
  implicit def zeroCase[M <: Nat]: Mul.Aux[Zero, M, Zero] =
    new Mul[Zero, M] {
      type Result = Zero
    }

  // (1 + N) * M = M + (N * M)
  implicit def succCase[N <: Nat, M <: Nat, NM <: Nat](implicit mul1: Mul.Aux[N, M, NM],
                                                       add1: Add[M, NM]): Mul.Aux[Succ[N], M, add1.Result] =
    new Mul[Succ[N], M] {
      type Result = add1.Result
    }
}

trait Pow[N <: Nat, M <: Nat] {
  type Result <: Nat
}

object Pow {
  type Aux[N <: Nat, M <: Nat, R <: Nat] = Pow[N, M] { type Result = R }

  def apply[N <: Nat, M <: Nat](implicit pow: Pow[N, M]) = new {
    def toInt(implicit toInt: ToInt[pow.Result]): Int = toInt.value
  }

  // N ^ 0 = 1
  implicit def zeroCase[N <: Nat]: Pow.Aux[N, Zero, Succ[Zero]] =
    new Pow[N, Zero] {
      type Result = Succ[Zero]
    }

  // N ^ (1 + M) = N * (N ^ M)
  implicit def succCase[N <: Nat, M <: Nat, NM <: Nat]
    (implicit pow1: Pow.Aux[N, M, NM],
              mul1: Mul[N, NM]): Pow.Aux[N, Succ[M], mul1.Result] =
  new Pow[N, Succ[M]] {
    type Result = mul1.Result
  }
}



trait IsEven[N <: Nat] {
  def value: Boolean
}

object IsEven {
  def apply[N <: Nat](implicit ev: IsEven[N]): IsEven[N] = ev

  implicit val zeroCase: IsEven[Zero] =
    new IsEven[Zero] { val value = true }

  implicit val oneCase: IsEven[Succ[Zero]] =
    new IsEven[Succ[Zero]] { val value = false }

  implicit def stepCase[N <: Nat](implicit ev: IsEven[N]) : IsEven[Succ[N]] =
    new IsEven[Succ[N]] { val value = !ev.value }
}


trait Fib[N <: Nat] {
  type Result <: Nat
}

object Fib {
  type Aux[N <: Nat, R <: Nat] = Fib[N] { type Result = R }

  def apply[N <: Nat](implicit ev: Fib[N]) = new {
    def toInt(implicit ev2: ToInt[ev.Result]): Int = ev2.value
  }

  // F(0) = 1
  implicit val zeroCase: Fib.Aux[Zero, Succ[Zero]] =
    new Fib[Zero] { type Result = Succ[Zero] }

  // F(1) = 1
  implicit val oneCase: Fib.Aux[Succ[Zero], Succ[Zero]] =
    new Fib[Succ[Zero]] { type Result = Succ[Zero] }

  // F(N) + F(N+1) = F(N+2)
  implicit def stepCase[N <: Nat, F0 <: Nat, F1 <: Nat, F2 <: Nat]
    (implicit fib1: Fib.Aux[N, F0],
              fib2: Fib.Aux[Succ[N], F1],
              add: Add.Aux[F0, F1, F2])
    : Fib.Aux[Succ[Succ[N]], F2] =
      new Fib[Succ[Succ[N]]] { type Result = F2 }
}
