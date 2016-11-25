package scalawave.solution.ex4.part3

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

  implicit val intEnc = makeEnc[Int, JsNum](value => JsNum(value))
  implicit val stringEnc = makeEnc[String, JsStr](value => JsStr(value))
  implicit val boolEnc = makeEnc[Boolean, JsBool](value => JsBool(value))
  implicit val doubleEnc = makeEnc[Double, JsNum](value => JsNum(value))

  implicit val hnilEnc = makeEnc[HNil, JsObj](_ => JsObj())

  implicit def hconsEnc[K <: Symbol, V, T <: HList](implicit key: Witness.Aux[K],
                                                    headEnc: JsonEncoder[V],
                                                    tailEnc: JsonEncoder.Aux[T, JsObj]) =
    makeEnc[FieldType[K, V] :: T, JsObj] { hlist =>
      val label = key.value.name
      JsObj(label -> headEnc.toJson(hlist.head)) ++ tailEnc.toJson(hlist.tail)
    }

  implicit val cnilEnc = makeEnc[CNil, JsObj](_ => throw new RuntimeException("will never call"))

  implicit def coproductEnc[K <: Symbol, V, T <: Coproduct](implicit key: Witness.Aux[K],
                                                            headEnc: JsonEncoder[V],
                                                            tailEnc: JsonEncoder.Aux[T, JsObj]) =
    makeEnc[FieldType[K, V] :+: T, JsObj] {
      case Inl(head) =>
        val label = key.value.name
        JsObj(label -> headEnc.toJson(head))
      case Inr(tail) =>
        tailEnc.toJson(tail)
    }

  implicit def genEnc[T, Repr, O <: JsValue](implicit gen: LabelledGeneric.Aux[T, Repr],
                                             enc: JsonEncoder.Aux[Repr, O]) =
    makeEnc[T, O]((obj: T) => enc.toJson(gen.to(obj)))
}

object JsonEncoderSyntax {

  implicit class JsonEncoderOps[T, O <: Json.JsValue](val obj: T) extends AnyVal {
    def toJson(implicit enc: JsonEncoder.Aux[T, O]): O = enc.toJson(obj)
    def toJsonString(implicit enc: JsonEncoder.Aux[T, O]) = Json.toString(obj.toJson)
  }
}
