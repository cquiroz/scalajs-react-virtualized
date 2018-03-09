package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import cats.syntax.eq._
import raw._

class TableSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {
  trait Data extends js.Object
  val rowGetterF = (x: Int) => new js.Object()
  "Table" should
    "have some required properties" in {
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.height should be (200)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.rowCount should be (20)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.rowHeight should be (40)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.width should be (500)
      Table(Table.props(headerHeight = 10.5, height = 200, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.headerHeight should be (10.5)
      Table(Table.props(headerHeight = 10, height = 200.5, rowCount = 20, rowHeight = 40, width = 500, rowGetter = rowGetterF)).props.height should be (200.5)
      Table(Table.props(headerHeight = 10, height = 200, rowCount = 20, rowHeight = 40, width = 500.5, rowGetter = rowGetterF)).props.width should be (500.5)
    }
    it should "support rendering" in {
      val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF))
      ReactTestUtils.withRenderedIntoDocument(table) { m =>
        val html =
          """<div class="ReactVirtualized__Table" role="grid">
              |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;"></div>
              |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
                |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 40px; max-width: 500px; max-height: 40px; overflow: hidden; position: relative;">
                  |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 40px; position: absolute; width: 500px; top: 0px;"></div>
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
            |<div aria-label="key" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div>
            |</div>
            |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
              |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 40px; max-width: 500px; max-height: 40px; overflow: hidden; position: relative;">
                |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 40px; position: absolute; width: 500px; top: 0px;">
                  |<div class="ReactVirtualized__Table__rowColumn" role="gridcell" title="" style="overflow: hidden;"></div>
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
      table2.props.rowClassName.asInstanceOf[RawRowClassName](RawIndexParameter(0)) should be("even")
      table2.props.rowClassName.asInstanceOf[RawRowClassName](RawIndexParameter(1)) should be("odd")
    }
    it should "support style" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.style === Some(js.Object()).orUndefined should be(true)
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      val table2 = Table(Table.props(style = Style(styleMap), rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
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
    it should "support scrollToIndex" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.scrollToIndex should be(-1)
      val table2 = Table(Table.props(scrollToIndex = 1, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.scrollToIndex should be(1)
    }
    it should "support scrollTop" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.scrollTop should be(())
      val table2 = Table(Table.props(scrollTop = 1, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.scrollTop should be(1)
      val table3 = Table(Table.props(scrollTop = 1.5, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table3.props.scrollTop should be(1.5)
    }
    it should "support sortDirection" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.sortDirection should be(())
      val table2 = Table(Table.props(sortDirection = SortDirection.DESC, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.sortDirection should be(raw.RawSortDirection.DESC)
    }
    it should "support sort" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.sort should be(())
      def sortFunction(s: String, d: SortDirection): Callback = Callback.empty
      val table2 = Table(Table.props(sort = sortFunction _, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.sort.toOption.map(_(RawSortParam("key", "ASC"))) should contain(())
    }
    it should "support scrollToAlignment" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.scrollToAlignment should be("auto")
      val table2 = Table(Table.props(scrollToAlignment = ScrollToAlignment.Center, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.scrollToAlignment should be("center")
    }
    it should "support rowStyle as object" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.rowStyle.asInstanceOf[js.Object] === js.Object() should be(true)
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      val table2 = Table(Table.props(rowStyle = Style(styleMap), rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.rowStyle.asInstanceOf[js.Object] === style should be(true)
    }
    it should "support rowStyle as function" in {
      val columns = List(Column(Column.props(200, "key")))
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      val rowStyleF = (i: Int) => Style(styleMap)
      val table = Table(Table.props(rowStyle = rowStyleF, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.rowStyle.asInstanceOf[RawRowStyle](RawIndexParameter(1)) === style should be(true)
    }
    it should "support aria-label" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.`aria-label` should be(())
      val table2 = Table(Table.props(ariaLabel = "label", rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.`aria-label` should be("label")
    }
    it should "support auto-height" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.autoHeight should be(())
      val table2 = Table(Table.props(autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.autoHeight.toOption should contain(true)
    }
    it should "support className" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.className should be(())
      val table2 = Table(Table.props(className = "class", autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.className should be("class")
    }
    it should "support estimatedRowSize " in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.estimatedRowSize should be(30)
      val table2 = Table(Table.props(estimatedRowSize = 10, autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.estimatedRowSize should be(10)
      val table3 = Table(Table.props(estimatedRowSize = 10.5, autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table3.props.estimatedRowSize should be(10.5)
    }
    it should "support gridClassName " in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.gridClassName should be(())
      val table2 = Table(Table.props(gridClassName = "class", autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.gridClassName should be("class")
    }
    it should "support gridStyle " in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.gridStyle should be(())
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      val table2 = Table(Table.props(gridStyle = Style(styleMap), autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.gridStyle.toOption.map(_ === style) should contain(true)
    }
    it should "support headerStyle " in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.headerStyle.toOption.map(_ === js.Object()) should contain(true)
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      val table2 = Table(Table.props(headerStyle = Style(styleMap), autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.headerStyle.toOption.map(_ === style) should contain(true)
    }
    it should "support id " in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.id should be(())
      val table2 = Table(Table.props(id = "id", autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.id should be("id")
    }
    it should "support a default headerRowRenderer " in {
      val Hello =
        ScalaComponent.builder[String]("Hello")
          .render_P(name => <<.div("Hello there ", name))
          .build
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val column = Hello.withKey("1")("abc")
      val headerRowParam = RawHeaderRowRendererParameter("class", js.Array(column.rawNode), style)
      val unmounted = table.props.headerRowRenderer(headerRowParam)
      assertRender(unmounted.rawNode, """<div><div class="class" role="row" style="foo:42px;bar:foobar;"><div>Hello there abc</div></div></div>""")
      val html =
        """<div class="ReactVirtualized__Table" role="grid">
            |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;"><div aria-label="key" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div></div>
            |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
              |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 20px; max-width: 500px; max-height: 20px; overflow: hidden; position: relative;">
                |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 20px; position: absolute; width: 500px; top: 0px;"><div class="ReactVirtualized__Table__rowColumn" role="gridcell" title="" style="overflow: hidden;"></div></div>
                |</div>
              |</div>
            |</div>""".stripMargin.replaceAll("[\n\r]", "")
      ReactTestUtils.withRenderedIntoDocument(table) { m =>
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    it should "support a custom headerRowRenderer " in {
      val Hello =
        ScalaComponent.builder[String]("Hello")
          .render_P(name => <<.div("Hello there ", name))
          .build
      val columns = List(Column(Column.props(200, "key")))
      def headerRowRendererF(className: String, cols: Array[VdomNode], style: Style): VdomNode =
        <<.li(^.cls := className, cols.toTagMod)
      val table = Table(Table.props(headerRowRenderer = headerRowRendererF _, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val column = Hello.withKey("1")("abc")
      val headerRowParam = RawHeaderRowRendererParameter("class", js.Array(column.rawNode), style)
      val unmounted = table.props.headerRowRenderer(headerRowParam)
      assertRender(unmounted.rawNode, """<div><li class="class"><div>Hello there abc</div></li></div>""")
      val html =
        """<div class="ReactVirtualized__Table" role="grid">
            |<li class="ReactVirtualized__Table__headerRow"><div aria-label="key" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div></li>
            |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
              |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 20px; max-width: 500px; max-height: 20px; overflow: hidden; position: relative;">
                |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 20px; position: absolute; width: 500px; top: 0px;"><div class="ReactVirtualized__Table__rowColumn" role="gridcell" title="" style="overflow: hidden;"></div></div>
                |</div>
              |</div>
            |</div>""".stripMargin.replaceAll("[\n\r]", "")
      ReactTestUtils.withRenderedIntoDocument(table) { m =>
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    it should "support a row click callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onRowClick(RawIndexParameter(1)) should be(())
      val table2 = Table(Table.props(onRowClick = x => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onRowClick(RawIndexParameter(1)) should be(())
    }
    it should "support a row double click callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onRowDoubleClick(RawIndexParameter(1)) should be(())
      val table2 = Table(Table.props(onRowDoubleClick = x => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onRowDoubleClick(RawIndexParameter(1)) should be(())
    }
    it should "support a row mouse out callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onRowMouseOut(RawIndexParameter(1)) should be(())
      val table2 = Table(Table.props(onRowMouseOut = x => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onRowMouseOut(RawIndexParameter(1)) should be(())
    }
    it should "support a row mouse over callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onRowMouseOver(RawIndexParameter(1)) should be(())
      val table2 = Table(Table.props(onRowMouseOver = x => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onRowMouseOver(RawIndexParameter(1)) should be(())
    }
    it should "support a row right click callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onRowRightClick(RawIndexParameter(1)) should be(())
      val table2 = Table(Table.props(onRowRightClick = x => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onRowRightClick(RawIndexParameter(1)) should be(())
    }
    it should "support a header click callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onHeaderClick(RawHeaderClickParam(js.Object(), "key")) should be(())
      val table2 = Table(Table.props(onHeaderClick = (_, _) => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onHeaderClick(RawHeaderClickParam(js.Object(), "key")) should be(())
    }
    it should "support on rows renderer callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onRowsRendered(RawRowsRendererParam(1, 10, 1, 10)) should be(())
      val table2 = Table(Table.props(onRowsRendered = (_, _, _, _) => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onRowsRendered(RawRowsRendererParam(1, 10, 1, 10)) should be(())
    }
    it should "support on scroll callback" in {
      val columns = List(Column(Column.props(200, "key")))
      val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table.props.onScroll(RawScrollParam(1, 10, 1)) should be(())
      val table2 = Table(Table.props(onScroll = (_, _, _) => Callback.empty, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      table2.props.onScroll(RawScrollParam(1, 10, 1)) should be(())
    }
    it should "support a custom rowRenderer " in {
      val columns = List(Column(Column.props(200, "key")))
      val rowRendererF: RowRenderer[Data] = (className: String, cols: Array[VdomNode], index: Int, isScrolling: Boolean, key: String, data: Data, _: Option[OnRowClick], _: Option[OnRowClick], _: Option[OnRowClick], _: Option[OnRowClick], _: Option[OnRowClick], style: Style) => <<.li(^.cls := className, cols.toTagMod, ^.key := key, ^.style := Style.toJsObject(style))
      val table = Table(Table.props(rowRenderer = rowRendererF, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
      val html =
        """<div class="ReactVirtualized__Table" role="grid">
            |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;">
              |<div aria-label="key" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0">
                |<span class="ReactVirtualized__Table__headerTruncatedText"></span>
              |</div>
            |</div>
            |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" tabindex="0" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;">
              |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 20px; max-width: 500px; max-height: 20px; overflow: hidden; position: relative;">
                |<li class="ReactVirtualized__Table__row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 20px; position: absolute; width: 500px; top: 0px;">
                    |<div class="ReactVirtualized__Table__rowColumn" role="gridcell" title="" style="overflow: hidden;">
                  |</div>
                |</li>
              |</div>
            |</div>
          |</div>""".stripMargin.replaceAll("[\n\r]", "")
      ReactTestUtils.withRenderedIntoDocument(table) { m =>
        assert(m.outerHtmlScrubbed() == html)
      }
    }
}
