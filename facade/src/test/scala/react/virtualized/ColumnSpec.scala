package io.github.cquiroz.scalajs
package react
package virtualized

import org.scalatest.{FlatSpec, Matchers}
import Column._
import scala.scalajs.js
import js.JSConverters._

class ColumnSpec extends FlatSpec with Matchers {
  "Column" should
    "require width and dataKey" in {
      Column(Column.props(200, "key")).props.width should be (200)
      Column(Column.props(200, "key")).props.dataKey should be ("key")
    }
    it should "support ariaLabel" in {
      Column(Column.props(200, "key")).props.`aria-label` should be(())
      Column(Column.props(200, "key", ariaLabel = "Label")).props.`aria-label` should be("Label")
    }
    it should "have a default cellDataGetter" in {
      val dataMap = Map("key" -> 1, "b" -> 2).toJSDictionary
      Column(Column.props(200, "key")).props.cellDataGetter.toOption.map(_(CellDataParameter("col", "key", dataMap))) should contain(1)
    }
    it should "support cellDataGetter" in {
      Column(Column.props(200, "key", cellDataGetter = Some((x: CellDataParameter) => "abc"))).props.cellDataGetter.toOption.map(_(CellDataParameter("col", "key", "row"))) should contain("abc")
    }

}
