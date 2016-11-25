package scalawave.common


object Json {

  sealed trait JsValue

  case object JsNull extends JsValue

  case class JsNum(num: BigDecimal) extends JsValue

  case class JsStr(str: String) extends JsValue

  case class JsBool(bool: Boolean) extends JsValue

  case class JsArr(elems: List[JsValue]) extends JsValue {
    def +:(elem: JsValue): JsArr = JsArr(elem +: elems)
    def ++(that: JsArr): JsArr = JsArr(elems ++ that.elems)
  }

  object JsArr {
    def apply(elems: JsValue*): JsArr = JsArr(elems.toList)
  }

  case class JsObj(fields: List[(String, JsValue)]) extends JsValue {
    def +:(field: (String, JsValue)): JsObj = JsObj(field +: fields)
    def ++(that: JsObj): JsObj = JsObj(fields ++ that.fields)
  }

  object JsObj {
    def apply[V <: JsValue](fields: (String, V)*): JsObj = JsObj(fields.toList)
  }

  def toString(value: JsValue): String = value match {
    case JsNull => "null"
    case JsNum(num) => num.toString()
    case JsStr(str) => s""""$str""""
    case JsBool(bool) => bool.toString
    case JsArr(elems) => s"[${elems.map(toString).mkString(", ")}]"
    case JsObj(fields) => s"{${fields.map(fieldToString).mkString(", ")}}"
  }

  def fieldToString(field: (String, JsValue)) =
    s""""${field._1}": ${toString(field._2)}"""
}