package scalawave.example.query_dsl

import org.scalatest.{MustMatchers, WordSpec}
import shapeless.test.illTyped

class QueryDslSpec extends WordSpec with MustMatchers {

  import scalawave.example.query_dsl.MyDbSchema._
  import Items._
  import Users._
  import scalawave.example.query_dsl.QueryDsl._

  "QueryDsl" when {

    "fromTable" should {

      "produce correct sql string" in {

        fromTable(Users).toSqlString mustBe
          "SELECT * FROM users"
      }
    }

    "select" should {

      "produce correct sql string when selected" in {

        fromTable(Users).select(id, email).toSqlString mustBe
          "SELECT id, email FROM (SELECT * FROM users)"

        Items.query.select(item_name, item_id).toSqlString mustBe
          "SELECT item_name, item_id FROM (SELECT * FROM items)"
      }

      "produce correct sql string when sub-selected" in {

        fromTable(Users)
          .select(id, email, age)
          .select(id, email)
          .toSqlString mustBe
          "SELECT id, email FROM (SELECT id, email, age FROM (SELECT * FROM users))"
      }

      "not compile when accessed column that doesn't exist in relation" in {

        illTyped("fromTable(Users).select(id, email).select(id, age)")
      }

      "not compile when accessed column of wrong table" in {
        illTyped("fromTable(Users).select(email, item_id)")
      }
    }
  }

}
