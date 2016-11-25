package scalawave.example.json_implicit_macro

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import scalawave.common.Json

trait JsonEncoder[T] {
  def toJson(obj: T): Json.JsValue
}

object JsonEncoder extends LowPriorityJsonEncoder {

  import Json._

  def apply[T](implicit enc: JsonEncoder[T]): JsonEncoder[T] = enc

  def makeEnc[T](encode: T => JsValue): JsonEncoder[T] = new JsonEncoder[T] {
    def toJson(obj: T): JsValue = encode(obj)
  }

  implicit val intEnc: JsonEncoder[Int] = makeEnc(v => JsNum(v))
  implicit val longEnc: JsonEncoder[Long] = makeEnc(v => JsNum(v))
  implicit val stringEnc: JsonEncoder[String] = makeEnc(JsStr)
  implicit val boolEnc: JsonEncoder[Boolean] = makeEnc(JsBool)
  implicit val doubleEnc: JsonEncoder[Double] = makeEnc(v => JsNum(v))
}

trait LowPriorityJsonEncoder {
  implicit def caseClassEnc[T]: JsonEncoder[T] = macro JsonEnoderMacros.caseClassEncImpl[T]
}

object JsonEnoderMacros {

  def caseClassEncImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[JsonEncoder[T]] = {

    import c.universe._

    def abort(message: String) = {
      c.abort(c.prefix.tree.pos, message)
    }

    val T = weakTypeOf[T]
    if(!T.typeSymbol.asClass.isCaseClass) {
      abort(s"Can't find implicit instance for JsonEncoder[${T.typeSymbol.asType.fullName}]!")
    }

    val members = T.decls.filter(s => s.asTerm.isCaseAccessor && s.isPublic).map(_.asTerm)
    val pairTrees = members.map { member =>
      q"(${member.name.toString}, obj.${member.name}.toJson)"
    }

    val tree = q"""
      new JsonEncoder[$T] {
        import scalawave.common.Json
        def toJson(obj: $T): Json.JsObj = {
          Json.JsObj(..$pairTrees)
        }
      }
    """

    c.Expr[JsonEncoder[T]](tree)
  }
}

object JsonEncoderSyntax {

  implicit class JsonEncoderOps[T](val obj: T) extends AnyVal {
    def toJson(implicit enc: JsonEncoder[T]): Json.JsValue = enc.toJson(obj)
    def toJsonString(implicit enc: JsonEncoder[T]) = Json.toString(obj.toJson)
  }
}
