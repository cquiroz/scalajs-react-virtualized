package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react.test._
import Table._
// import scala.scalajs.js
// import js.JSConverters._
// import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
// import cats.syntax.eq._

class TableSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {
  "Table" should
    "have some required properties" in {
      val rowGetterF = (x: IndexParameter) => x.index
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.height should be (200)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.rowCount should be (20)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.rowHeight should be (40)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.width should be (500)
    }
    it should "support rendering" in {
      val rowGetterF = (x: IndexParameter) => x.index
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
      val rowGetterF = (x: IndexParameter) => x.index
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
      val rowGetterF = (x: IndexParameter) => x.index
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.disableHeader.toOption should contain(false)
      val table2 = Table(Table.props(disableHeader = true, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.disableHeader.toOption should contain(true)
    }
    /*it should "support ariaLabel" in {
      Table(Table.props(200, "key")).props.`aria-label` should be(())
      Table(Table.props(200, "key", ariaLabel = "Label")).props.`aria-label` should be("Label")
    }
    it should "have a default cellDataGetter" in {
      val dataMap = Map("key" -> 1, "b" -> 2).toJSDictionary
      Table(Table.props(200, "key")).props.cellDataGetter.toOption.map(_(CellDataParameter("col", "key", dataMap))) should contain(1)
    }
    it should "support cellDataGetter" in {
      Table(Table.props(200, "key", cellDataGetter = Some((x: CellDataParameter) => "abc"))).props.cellDataGetter.toOption.map(_(CellDataParameter("col", "key", "row"))) should contain("abc")
    }
    it should "have a default cellRenderer" in {
      Table(Table.props(200, "key")).props.cellRenderer.toOption.map(_(CellRendererParameter("cellData", "col", "key", "row", 1))) should contain("cellData")
    }
    it should "support cellRenderer" in {
      val r = Table(Table.props(200, "key", cellRenderer = Some((x: CellRendererParameter) => <<.div("abc")))).props.cellRenderer.toOption.map(_(CellRendererParameter("cellData", "col", "key", "row", 1)))
      assertRenderNode(r, "<div><div>abc</div></div>")
    }
    it should "support className" in {
      Table(Table.props(200, "key")).props.className should be(())
      Table(Table.props(200, "key", className = "my-class")).props.className should be("my-class")
    }
    it should "support columnData" in {
      Table(Table.props(200, "key")).props.columnData should be(())
      val dataObject = js.Dynamic.literal(foo = 42, bar = "foobar")
      Table(Table.props(200, "key", columnData = dataObject)).props.columnData should be(dataObject)
    }
    it should "support disableSort" in {
      Table(Table.props(200, "key")).props.disableSort should be(())
      Table(Table.props(200, "key", disableSort = true)).props.disableSort.toOption should be(Some(true))
    }
    it should "support defaultSortDirection" in {
      Table(Table.props(200, "key")).props.defaultSortDirection should be(SortDirection.ASC)
      Table(Table.props(200, "key", defaultSortDirection = SortDirection.DESC)).props.defaultSortDirection should be(SortDirection.DESC)
    }
    it should "support flexGrow" in {
      Table(Table.props(200, "key")).props.flexGrow should be(0)
      Table(Table.props(200, "key", disableSort = true, flexGrow = 1)).props.flexGrow should be(1)
    }
    it should "support flexShrink" in {
      Table(Table.props(200, "key")).props.flexShrink should be(1)
      Table(Table.props(200, "key", disableSort = true, flexShrink = 0)).props.flexShrink should be(0)
    }
    it should "support headerClassName" in {
      Table(Table.props(200, "key")).props.headerClassName should be(())
      Table(Table.props(200, "key", headerClassName = "my-class")).props.headerClassName should be("my-class")
    }
    it should "have a default headerRenderer" in {
      val label = <<.div("Label")
      val headerParam = HeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
      val unmounted = Table(Table.props(200, "key")).props.headerRenderer.toOption.map(_(headerParam))
      assertRenderNode(unmounted,
        """<div><span class="ReactVirtualized__Table__headerTruncatedText" title="[object Object]"><div>Label</div></span><svg class="ReactVirtualized__Table__sortableHeaderIcon ReactVirtualized__Table__sortableHeaderIcon--ASC" width="18" height="18" viewBox="0 0 24 24"><path d="M7 14l5-5 5 5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>""")
    }
    it should "support headerRenderer" in {
      val label = <<.div("Label")
      val headerParam = HeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
      val unmounted = Table(Table.props(200, "key", headerRenderer = Some(x => <<.div("header")))).props.headerRenderer.toOption.map(_(headerParam))
      assertRenderNode(unmounted, """<div><div>header</div></div>""")
    }
    it should "support id" in {
      Table(Table.props(200, "key")).props.id should be(())
      Table(Table.props(200, "key", id = "id")).props.id should be("id")
    }
    it should "support label" in {
      val label = <<.div("id")
      Table(Table.props(200, "key")).props.label should be(())
      Table(Table.props(200, "key", label = label)).props.label should be(label.rawNode)
    }
    it should "support maxWidth" in {
      Table(Table.props(200, "key")).props.maxWidth should be(())
      Table(Table.props(200, "key", maxWidth = 100)).props.maxWidth should be(100)
    }
    it should "support minWidth" in {
      Table(Table.props(200, "key")).props.minWidth should be(())
      Table(Table.props(200, "key", minWidth = 100)).props.minWidth should be(100)
    }
    it should "support style" in {
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      Table(Table.props(200, "key")).props.style === Some(js.Object()).orUndefined should be(true)
      Table(Table.props(200, "key", style = style)).props.style should be(style)
    }*/
}
