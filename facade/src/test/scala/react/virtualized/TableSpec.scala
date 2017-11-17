package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react.test._
import Table._
import scala.scalajs.js
import js.JSConverters._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import cats.syntax.eq._

class TableSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {
  val rowGetterF = (x: Int) => new js.Object()
  "Table" should
    "have some required properties" in {
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.height should be (200)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.rowCount should be (20)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.rowHeight should be (40)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.width should be (500)
    }
    it should "support rendering" in {
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF))
      ReactTestUtils.withRenderedIntoDocument(table) { m =>
        val html =
          """<div class="ReactVirtualized__Table" role="grid">
              |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;"></div>
              |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
                |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 40px; max-width: 500px; max-height: 40px; overflow: hidden; position: relative;">
                  |<div class="ReactVirtualized__Table__row" role="row" style="height: 40px; left: 0px; position: absolute; top: 0px; width: 500px; overflow: hidden; padding-right: 0px;"></div>
                  |</div>
                |</div>
              |</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    it should "support rendering with columns" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      val html =
        """<div class="ReactVirtualized__Table" role="grid">
            |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;">
            |<div role="columnheader" class="ReactVirtualized__Table__headerColumn"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div>
            |</div>
            |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
              |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 40px; max-width: 500px; max-height: 40px; overflow: hidden; position: relative;">
                |<div class="ReactVirtualized__Table__row" role="row" style="height: 40px; left: 0px; position: absolute; top: 0px; width: 500px; overflow: hidden; padding-right: 0px;">
                  |<div role="gridcell" class="ReactVirtualized__Table__rowColumn" title="" style="overflow: hidden;"></div>
                |</div>
                |</div>
              |</div>
            |</div>""".stripMargin.replaceAll("[\n\r]", "")
      ReactTestUtils.withRenderedIntoDocument(table) { m =>
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    it should "support disableHeader" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.disableHeader.toOption should contain(false)
      val table2 = Table(Table.props(disableHeader = true, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.disableHeader.toOption should contain(true)
    }
    it should "support noRowsRenderer" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.noRowsRenderer.toOption shouldBe defined
      val noRowsRenderer: NoRowsRenderer = () => <<.div()
      val table2 = Table(Table.props(noRowsRenderer = noRowsRenderer, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.disableHeader.toOption shouldBe defined
    }
    it should "support overscanRowCount" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.overscanRowCount should be(10)
      val table2 = Table(Table.props(overscanRowCount = 50, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.overscanRowCount should be(50)
    }
    it should "support rowClassName as string" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.rowClassName should be(())
      val table2 = Table(Table.props(rowClassName = "class", headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.rowClassName should be("class")
    }
    it should "support rowClassName as a function" in {
      val columns = List(Column(Column.props(200, "key")))
      val rowClassNameFn = (x: Int) => if (x % 2 == 0) "even" else "odd"
      val table2 = Table(Table.props(rowClassName = rowClassNameFn, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.rowClassName.asInstanceOf[RawRowClassName](IndexParameter(0)) should be("even")
      table2.props.rowClassName.asInstanceOf[RawRowClassName](IndexParameter(1)) should be("odd")
    }
    it should "support style" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.style === Some(js.Object()).orUndefined should be(true)
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val table2 = Table(Table.props(style = style, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.style === Some(style).orUndefined should be(true)
    }
    it should "support tabIndex" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.tabIndex should be(())
      val table2 = Table(Table.props(tabIndex = 1, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.tabIndex should be(1)
    }
    it should "support sortBy" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.sortBy should be(())
      val table2 = Table(Table.props(sortBy = "key", rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.sortBy should be("key")
    }
}
