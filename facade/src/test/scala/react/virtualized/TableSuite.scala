package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.facade.JsNumber
import scala.scalajs.js
import react.common.TestUtils
import react.virtualized.raw._

class TableSuite extends munit.FunSuite with TestUtils {
  trait Data extends js.Object
  val rowGetterF = (x: Int) => new js.Object

  test("have some required properties") {
    assertEquals(Table(
                   Table.props(headerHeight = 10,
                               height = 200,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500,
                               rowGetter = rowGetterF
                   )
                 ).props.height,
                 200: JsNumber
    )
    assertEquals(Table(
                   Table.props(headerHeight = 10,
                               height = 200,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500,
                               rowGetter = rowGetterF
                   )
                 ).props.rowCount,
                 20
    )
    assertEquals(Table(
                   Table.props(headerHeight = 10,
                               height = 200,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500,
                               rowGetter = rowGetterF
                   )
                 ).props.rowHeight: Any,
                 40: JsNumber
    )
    assertEquals(Table(
                   Table.props(headerHeight = 10,
                               height = 200,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500,
                               rowGetter = rowGetterF
                   )
                 ).props.width,
                 500: JsNumber
    )
    assertEquals(Table(
                   Table.props(headerHeight = 10.5,
                               height = 200,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500,
                               rowGetter = rowGetterF
                   )
                 ).props.headerHeight,
                 10.5: JsNumber
    )
    assertEquals(Table(
                   Table.props(headerHeight = 10,
                               height = 200.5,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500,
                               rowGetter = rowGetterF
                   )
                 ).props.height,
                 200.5: JsNumber
    )
    assertEquals(Table(
                   Table.props(headerHeight = 10,
                               height = 200,
                               rowCount = 20,
                               rowHeight = 40,
                               width = 500.5,
                               rowGetter = rowGetterF
                   )
                 ).props.width,
                 500.5: JsNumber
    )
  }
  // test("support rendering") {
  //   val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF))
  //   ReactTestUtils.withRenderedIntoDocument(table) { m =>
  //     val html =
  //       """<div class="ReactVirtualized__Table" role="grid">
  //           |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;"></div>
  //           |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;" tabindex="0">
  //             |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 40px; max-width: 500px; max-height: 40px; overflow: hidden; position: relative;">
  //               |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 40px; position: absolute; width: 500px; top: 0px;"></div>
  //               |</div>
  //             |</div>
  //           |</div>""".stripMargin.replaceAll("[\n\r]", "")
  //     assert(m.outerHtmlScrubbed() == html)
  //   }
  // }
  // test("support rendering with columns") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   val html =
  //     """<div class="ReactVirtualized__Table" role="grid">
  //         |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;">
  //         |<div aria-label="key" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div>
  //         |</div>
  //         |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;" tabindex="0">
  //           |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 40px; max-width: 500px; max-height: 40px; overflow: hidden; position: relative;">
  //             |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 40px; position: absolute; width: 500px; top: 0px;">
  //               |<div class="ReactVirtualized__Table__rowColumn" role="gridcell" style="overflow: hidden;" title=""></div>
  //             |</div>
  //             |</div>
  //           |</div>
  //         |</div>""".stripMargin.replaceAll("[\n\r]", "")
  //   ReactTestUtils.withRenderedIntoDocument(table) { m =>
  //     assert(m.outerHtmlScrubbed() == html)
  //   }
  // }
  // test("support disableHeader") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.disableHeader.toOption ==> Some(false)
  //   val table2 = Table(Table.props(disableHeader = true, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.disableHeader.toOption ==> Some(true)
  // }
  // test("support noRowsRenderer") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.noRowsRenderer.toOption.isDefined ==> true
  //   val noRowsRenderer: NoRowsRenderer = () => <<.div()
  //   val table2 = Table(Table.props(noRowsRenderer = noRowsRenderer, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.disableHeader.toOption.isDefined ==> true
  // }
  // test("support overscanRowCount") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.overscanRowCount ==> 10
  //   val table2 = Table(Table.props(overscanRowCount = 50, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.overscanRowCount ==> 50
  // }
  // test("support rowClassName as string") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.rowClassName ==> (())
  //   val table2 = Table(Table.props(rowClassName = "class", headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.rowClassName ==> "class"
  // }
  // test("support rowClassName as a function") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val rowClassNameFn = (x: Int) => if (x % 2 == 0) "even" else "odd"
  //   val table2 = Table(Table.props(rowClassName = rowClassNameFn, headerHeight = 10, height = 200, rowCount = 1, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.rowClassName.asInstanceOf[RawRowClassName](RawIndexParameter(0)) ==> "even"
  //   table2.props.rowClassName.asInstanceOf[RawRowClassName](RawIndexParameter(1)) ==> "odd"
  // }
  // test("support style") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.style === Some(js.Object()).orUndefined ==> true
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val styleMap = Map[String, String | Int](test("foo")> 42, "bar" -> "foobar")
  //   val table2 = Table(Table.props(style = Style(styleMap), rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.style === Some(style).orUndefined ==> true
  // }
  // test("support tabIndex") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.tabIndex ==> (())
  //   val table2 = Table(Table.props(tabIndex = 1, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.tabIndex ==> 1
  // }
  // test("support sortBy") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.sortBy ==> (())
  //   val table2 = Table(Table.props(sortBy = "key", rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.sortBy ==> "key"
  // }
  // test("support scrollToIndex") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.scrollToIndex ==> -1
  //   val table2 = Table(Table.props(scrollToIndex = 1, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.scrollToIndex ==> 1
  // }
  // test("support scrollTop") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.scrollTop ==> (())
  //   val table2 = Table(Table.props(scrollTop = 1, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.scrollTop ==> 1
  //   val table3 = Table(Table.props(scrollTop = 1.5, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table3.props.scrollTop ==> 1.5
  // }
  // test("support sortDirection") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.sortDirection ==> (())
  //   val table2 = Table(Table.props(sortDirection = SortDirection.DESC, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.sortDirection ==> (raw.RawSortDirection.DESC)
  // }
  // test("support sort") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.sort ==> (())
  //   def sortFunction(s: String, d: SortDirection): Callback = Callback.empty
  //   val table2 = Table(Table.props(sort = sortFunction _, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.sort.toOption.map(_(RawSortParam("key", "ASC"))) ==> Some(())
  // }
  // test("support scrollToAlignment") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.scrollToAlignment ==> "auto"
  //   val table2 = Table(Table.props(scrollToAlignment = ScrollToAlignment.Center, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.scrollToAlignment ==> "center"
  // }
  // test("support rowStyle as object") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.rowStyle.asInstanceOf[js.Object] === js.Object() ==> true
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val styleMap = Map[String, String | Int](test("foo")> 42, "bar" -> "foobar")
  //   val table2 = Table(Table.props(rowStyle = Style(styleMap), rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.rowStyle.asInstanceOf[js.Object] === style ==> true
  // }
  // test("support rowStyle as function") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val styleMap = Map[String, String | Int](test("foo")> 42, "bar" -> "foobar")
  //   val rowStyleF = (i: Int) => Style(styleMap)
  //   val table = Table(Table.props(rowStyle = rowStyleF, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.rowStyle.asInstanceOf[RawRowStyle](RawIndexParameter(1)) === style ==> (true)
  // }
  // "support aria-label" - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.`aria-label` ==> (())
  //   val table2 = Table(Table.props(ariaLabel = "label", rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.`aria-label` ==> "label"
  // }
  // "support auto-height" - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.autoHeight ==> (())
  //   val table2 = Table(Table.props(autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.autoHeight.toOption ==> Some(true)
  // }
  // test("support className") {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.className ==> (())
  //   val table2 = Table(Table.props(className = "class", autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.className ==> "class"
  // }
  // "support estimatedRowSize " - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.estimatedRowSize ==> 30
  //   val table2 = Table(Table.props(estimatedRowSize = 10, autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.estimatedRowSize ==> 10
  //   val table3 = Table(Table.props(estimatedRowSize = 10.5, autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table3.props.estimatedRowSize ==> 10.5
  // }
  // "support gridClassName " - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.gridClassName ==> (())
  //   val table2 = Table(Table.props(gridClassName = "class", autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.gridClassName ==> "class"
  // }
  // "support gridStyle " - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.gridStyle ==> (())
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val styleMap = Map[String, String | Int](test("foo")> 42, "bar" -> "foobar")
  //   val table2 = Table(Table.props(gridStyle = Style(styleMap), autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.gridStyle.toOption.map(_ === style) ==> Some(true)
  // }
  // "support headerStyle " - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.headerStyle.toOption.map(_ === js.Object()) ==> Some(true)
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val styleMap = Map[String, String | Int](test("foo")> 42, "bar" -> "foobar")
  //   val table2 = Table(Table.props(headerStyle = Style(styleMap), autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.headerStyle.toOption.map(_ === style) ==> Some(true)
  // }
  // "support id " - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table.props.id ==> (())
  //   val table2 = Table(Table.props(id = "id", autoHeight = true, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   table2.props.id ==> ("id")
  // }
  // "support a default headerRowRenderer " - {
  //   val Hello =
  //     ScalaComponent.builder[String]("Hello")
  //       .render_P(name => <<.div("Hello there ", name))
  //       .build
  //   val columns = List(Column(Column.props(200, "key")))
  //   val table = Table(Table.props(rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val column = Hello.withKey("1")("abc")
  //   val headerRowParam = RawHeaderRowRendererParameter("class", js.Array(column.rawNode), style)
  //   val unmounted = table.props.headerRowRenderer(headerRowParam)
  //   assertRender(unmounted.rawNode, """<div><div class="class" role="row" style="foo:42px;bar:foobar"><div>Hello there abc</div></div></div>""")
  //   val html =
  //     """<div class="ReactVirtualized__Table" role="grid">
  //         |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;"><div aria-label="key" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div></div>
  //         |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;" tabindex="0">
  //           |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 20px; max-width: 500px; max-height: 20px; overflow: hidden; position: relative;">
  //             |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 20px; position: absolute; width: 500px; top: 0px;"><div class="ReactVirtualized__Table__rowColumn" role="gridcell" style="overflow: hidden;" title=""></div></div>
  //             |</div>
  //           |</div>
  //         |</div>""".stripMargin.replaceAll("[\n\r]", "")
  //   ReactTestUtils.withRenderedIntoDocument(table) { m =>
  //     assert(m.outerHtmlScrubbed() == html)
  //   }
  // }
  // "support a custom headerRowRenderer " - {
  //   val Hello =
  //     ScalaComponent.builder[String]("Hello")
  //       .render_P(name => <<.div("Hello there ", name))
  //       .build
  //   val columns = List(Column(Column.props(200, "key")))
  //   def headerRowRendererF(className: String, cols: Array[VdomNode], style: Style): VdomNode =
  //     <<.li(^.cls := className, cols.toTagMod)
  //   val table = Table(Table.props(headerRowRenderer = headerRowRendererF _, rowHeight = 20, headerHeight = 10, height = 200, rowCount = 1, width = 500, rowGetter = rowGetterF), columns: _*)
  //   val style = js.Dynamic.literal(foo = 42, bar = "foobar")
  //   val column = Hello.withKey("1")("abc")
  //   val headerRowParam = RawHeaderRowRendererParameter("class", js.Array(column.rawNode), style)
  //   val unmounted = table.props.headerRowRenderer(headerRowParam)
  //   assertRender(unmounted.rawNode, """<div><li class="class"><div>Hello there abc</div></li></div>""")
  //   val html =
  //     """<div aria-colcount="1" aria-rowcount="1" class="ReactVirtualized__Table" role="grid">
  //         |<li class="ReactVirtualized__Table__headerRow"><div aria-label="key" aria-sort="none" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0"><span class="ReactVirtualized__Table__headerTruncatedText"></span></div></li>
  //         |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;" tabindex="0">
  //           |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 20px; max-width: 500px; max-height: 20px; overflow: hidden; position: relative;">
  //             |<div aria-label="row" tabindex="0" class="ReactVirtualized__Table__row" role="row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 20px; position: absolute; width: 500px; top: 0px;"><div aria-colindex="1" class="ReactVirtualized__Table__rowColumn" role="gridcell" style="overflow: hidden;" title=""></div></div>
  //             |</div>
  //           |</div>
  //         |</div>""".stripMargin.replaceAll("[\n\r]", "")
  //   ReactTestUtils.withRenderedIntoDocument(table) { m =>
  //     println(m.outerHtmlScrubbed)
  //     assert(m.outerHtmlScrubbed() === html)
  //   }
  // }
  test("support a row click callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onRowClick(RawIndexParameter(1)), ())
    val table2 = Table(Table.props(onRowClick = x => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onRowClick(RawIndexParameter(1)), ())
  }
  test("support a row double click callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onRowDoubleClick(RawIndexParameter(1)), ())
    val table2 = Table(Table.props(onRowDoubleClick = x => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onRowDoubleClick(RawIndexParameter(1)), ())
  }
  test("support a row mouse out callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onRowMouseOut(RawIndexParameter(1)), ())
    val table2 = Table(Table.props(onRowMouseOut = x => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onRowMouseOut(RawIndexParameter(1)), ())
  }
  test("support a row mouse over callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onRowMouseOver(RawIndexParameter(1)), ())
    val table2 = Table(Table.props(onRowMouseOver = x => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onRowMouseOver(RawIndexParameter(1)), ())
  }
  test("support a row right click callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onRowRightClick(RawIndexParameter(1)), ())
    val table2 = Table(Table.props(onRowRightClick = x => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onRowRightClick(RawIndexParameter(1)), ())
  }
  test("support a header click callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onHeaderClick(RawHeaderClickParam(js.Object(), "key")), ())
    val table2 = Table(Table.props(onHeaderClick = (_, _) => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onHeaderClick(RawHeaderClickParam(js.Object(), "key")), ())
  }
  test("support on rows renderer callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onRowsRendered(RawRowsRendererParam(1, 10, 1, 10)), ())
    val table2 = Table(Table.props(onRowsRendered = (_, _, _, _) => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onRowsRendered(RawRowsRendererParam(1, 10, 1, 10)), ())
  }
  test("support on scroll callback") {
    val columns = List(Column(Column.props(200, "key")))
    val table = Table(Table.props(rowHeight = 20,
                                  headerHeight = 10,
                                  height = 200,
                                  rowCount = 1,
                                  width = 500,
                                  rowGetter = rowGetterF
                      ),
                      columns: _*
    )
    assertEquals(table.props.onScroll(RawScrollParam(1, 10, 1)), ())
    val table2 = Table(Table.props(onScroll = (_, _, _) => Callback.empty,
                                   rowHeight = 20,
                                   headerHeight = 10,
                                   height = 200,
                                   rowCount = 1,
                                   width = 500,
                                   rowGetter = rowGetterF
                       ),
                       columns: _*
    )
    assertEquals(table2.props.onScroll(RawScrollParam(1, 10, 1)), ())
  }
  // "support a custom rowRenderer " - {
  //   val columns = List(Column(Column.props(200, "key")))
  //   val rowRendererF: RowRenderer[Data] = (
  //     className:   String,
  //     cols:        Array[VdomNode],
  //     index:       Int,
  //     isScrolling: Boolean,
  //     key:         String,
  //     data:        Data,
  //     _:           Option[OnRowClick],
  //     _:           Option[OnRowClick],
  //     _:           Option[OnRowClick],
  //     _:           Option[OnRowClick],
  //     _:           Option[OnRowClick],
  //     style:       Style
  //   ) =>
  //     <<.li(^.cls := className, cols.toTagMod, ^.key := key, ^.style := Style.toJsObject(style))
  //   val table = Table(Table.props(rowRenderer = rowRendererF,
  //                                 rowHeight    = 20,
  //                                 headerHeight = 10,
  //                                 height       = 200,
  //                                 rowCount     = 1,
  //                                 width        = 500,
  //                                 rowGetter    = rowGetterF),
  //                     columns: _*)
  //   val html =
  //     """<div aria-colcount="1" aria-rowcount="1" class="ReactVirtualized__Table" role="grid">
  //         |<div class="ReactVirtualized__Table__headerRow" role="row" style="height: 10px; overflow: hidden; padding-right: 0px; width: 500px;">
  //           |<div aria-label="key" aria-sort="none" class="ReactVirtualized__Table__headerColumn" role="columnheader" tabindex="0">
  //             |<span class="ReactVirtualized__Table__headerTruncatedText"></span>
  //           |</div>
  //         |</div>
  //         |<div aria-label="grid" aria-readonly="true" class="ReactVirtualized__Grid ReactVirtualized__Table__Grid" role="rowgroup" style="box-sizing: border-box; direction: ltr; height: 190px; position: relative; width: 500px; overflow-x: hidden; overflow-y: hidden;" tabindex="0">
  //           |<div class="ReactVirtualized__Grid__innerScrollContainer" role="rowgroup" style="width: auto; height: 20px; max-width: 500px; max-height: 20px; overflow: hidden; position: relative;">
  //             |<li class="ReactVirtualized__Table__row" style="overflow: hidden; left: 0px; padding-right: 0px; height: 20px; position: absolute; width: 500px; top: 0px;">
  //                 |<div aria-colindex="1" class="ReactVirtualized__Table__rowColumn" role="gridcell" style="overflow: hidden;" title="">
  //               |</div>
  //             |</li>
  //           |</div>
  //         |</div>
  //       |</div>""".stripMargin.replaceAll("[\n\r]", "")
  //   ReactTestUtils.withRenderedIntoDocument(table) { m =>
  //     assert(m.outerHtmlScrubbed() === html)
  //   }
  // }
}
