package scalawave.example.query_dsl

import shapeless.ops.hlist.{SelectAll, ToList}
import shapeless.{Generic, HList, HNil}

object Db {
  trait Table { def name: String }
  trait Column { def name: String }
}

object MyDbSchema {

  object Users extends Db.Table {
    val name = "users"
    object id extends Db.Column { val name = "id" }
    object email extends Db.Column { val name = "email" }
    object username extends Db.Column { val name = "username" }
    object age extends Db.Column { val name = "age" }
    object isAdmin extends Db.Column { val name = "isAdmin" }

    implicit val query = QueryDsl.TableQuery(Users, id :: email :: username :: age :: isAdmin :: HNil)
  }

  object Items extends Db.Table {
    val name = "items"
    object item_id extends Db.Column { val name = "item_id" }
    object item_name extends Db.Column { val name = "item_name" }

    implicit val query = QueryDsl.TableQuery(Items, item_id :: item_name :: HNil)
  }
}


object QueryDsl {

  trait Query[Columns <: HList] {
    def columns: Columns
    def toSqlString: String

    def select[SelectCols <: HList : ColsNames](cols: SelectCols)
                                               (implicit ev: SelectAll[Columns, SelectCols]): Query[SelectCols] =
      SelectQuery(this, cols)

    def select[P <: Product, Cols <: HList](p: P)
                                           (implicit gen: Generic.Aux[P, Cols],
                                            ev: SelectAll[Columns, Cols],
                                            cn: ColsNames[Cols]): Query[Cols] =
      select(gen.to(p))
  }

  case class TableQuery[T <: Db.Table, Cols <: HList](table: T, columns: Cols) extends Query[Cols] {
    def toSqlString = s"SELECT * FROM ${table.name}"
  }

  case class SelectQuery[FromQuery <: Query[_], Cols <: HList : ColsNames](from: FromQuery, columns: Cols) extends Query[Cols] {
    def toSqlString: String = s"SELECT ${ColsNames[Cols](columns).mkString(", ")} FROM (${from.toSqlString})"
  }

  def fromTable[T <: Db.Table, Cols <: HList](table: T)(implicit tq: TableQuery[T, Cols]): Query[Cols] = tq
}


trait ColsNames[Cols <: HList] {
  def apply(cols: Cols): List[String]
}

object ColsNames {
  def apply[Cols <: HList](columns: Cols)(implicit cn: ColsNames[Cols]): List[String] = cn(columns)

  implicit def colsNames[Cols <: HList](implicit toList: ToList[Cols, Db.Column]): ColsNames[Cols] =
    new ColsNames[Cols] {
      def apply(cols: Cols) = toList(cols).map(_.name)
    }
}
