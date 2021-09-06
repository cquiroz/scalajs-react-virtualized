package react
package virtualized

import cats.syntax.eq._
import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import japgolly.scalajs.react.vdom.html_<^.{ < => <<, _ }
import japgolly.scalajs.react.facade.JsNumber
import react.common.style._
import react.common.TestUtils
import react.common.implicits._
import raw._
import munit._
import japgolly.scalajs.react.facade.React

class ColumnSuite extends FunSuite with TestUtils {

  test("requireWidthAndDataKey") {
    assertEquals(Column(Column.props(200, "key")).props.width, 200: JsNumber)
    assertEquals(Column(Column.props(200, "key")).props.dataKey, "key": js.Any)
  }
  test("support ariaLabel") {
    assertEquals(Column(Column.props(200, "key")).props.`aria-label`, js.undefined)
    assertEquals(Column(Column.props(200, "key", ariaLabel = "Label")).props.`aria-label`,
                 "Label": js.UndefOr[String]
    )
  }
  // test("have a default cellDataGetter") {
  //   val dataMap = Map("key" -> 1, "b" -> 2).toJSDictionary
  //   assertEquals(Column(Column.props(200, "key")).props.cellDataGetter.toOption
  //                  .map(_(RawCellDataParameter("col", "key", dataMap))),
  //                Some(1: js.Object): Option[js.Object]
  //   )
  // }
  test("support cellDataGetter") {
    val cell: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
    assertEquals(
      Column(
        Column.props(200,
                     "key",
                     cellDataGetter = Some((_: js.Any, _: String, _: js.Object) => cell)
        )
      ).props.cellDataGetter.toOption
        .map(_(RawCellDataParameter("col", "key", "row")))
        .map(_ === cell),
      Some(true)
    )
  }
  test("have a default cellRenderer") {
    assertEquals(
      Column(Column.props(200, "key")).props.cellRenderer.toOption
        .map(_(RawCellRendererParameter("cellData", "col", "key", "row", 1))),
      Some("cellData"): Option[React.Node]
    )
  }
  test("support cellRenderer") {
    val r = Column(
      Column.props(200,
                   "key",
                   cellRenderer = (_: js.Any, _: js.Any, _, _: js.Any, _) => <<.div("abc")
      )
    ).props.cellRenderer.toOption
      .map(_(RawCellRendererParameter("cellData", "col", "key", "row", 1)))
    assertRenderNode(r, "<div><div>abc</div></div>")
  }
  test("support className") {
    assertEquals(Column(Column.props(200, "key")).props.className, js.undefined)
    assertEquals(Column(Column.props(200, "key", className = "my-class")).props.className,
                 "my-class": js.UndefOr[String]
    )
  }
  test("support columnData") {
    assertEquals(Column(Column.props(200, "key")).props.columnData, js.undefined)
    val dataObject = js.Dynamic.literal(foo = 42, bar = "foobar")
    assertEquals(Column(Column.props(200, "key", columnData = dataObject)).props.columnData: Any,
                 dataObject: Any
    )
  }
  test("support disableSort") {
    assertEquals(Column(Column.props(200, "key")).props.disableSort, js.undefined)
    assertEquals(Column(Column.props(200, "key", disableSort = true)).props.disableSort.toOption,
                 Some(true)
    )
  }
  test("support defaultSortDirection") {
    assertEquals(Column(Column.props(200, "key")).props.defaultSortDirection,
                 SortDirection.ASC.toRaw
    )
    assertEquals(Column(
                   Column.props(200, "key", defaultSortDirection = SortDirection.DESC)
                 ).props.defaultSortDirection,
                 SortDirection.DESC.toRaw
    )
  }
  test("support flexGrow") {
    assertEquals(Column(Column.props(200, "key")).props.flexGrow, 0: js.UndefOr[JsNumber])
    assertEquals(Column(Column.props(200, "key", disableSort = true, flexGrow = 1)).props.flexGrow,
                 1: js.UndefOr[JsNumber]
    )
  }
  test("support flexShrink") {
    assertEquals(Column(Column.props(200, "key")).props.flexShrink, 1: js.UndefOr[JsNumber])
    assertEquals(
      Column(Column.props(200, "key", disableSort = true, flexShrink = 0)).props.flexShrink,
      0: js.UndefOr[JsNumber]
    )
  }
  test("support headerClassName") {
    assertEquals(Column(Column.props(200, "key")).props.headerClassName, js.undefined)
    assertEquals(Column(
                   Column.props(200, "key", headerClassName = "my-class")
                 ).props.headerClassName,
                 "my-class": js.UndefOr[String]
    )
  }
  test("have a default headerRenderer") {
    val label = <<.div("Label")
    val headerParam =
      RawHeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
    val unmounted = Column(Column.props(200, "key")).props.headerRenderer(headerParam)
    assertRender(
      unmounted,
      """<div><span class="ReactVirtualized__Table__headerTruncatedText"><div>Label</div></span><svg class="ReactVirtualized__Table__sortableHeaderIcon ReactVirtualized__Table__sortableHeaderIcon--ASC" width="18" height="18" viewBox="0 0 24 24"><path d="M7 14l5-5 5 5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>"""
    )
  }
  test("support headerRenderer") {
    val label = <<.div("Label")
    val headerParam =
      RawHeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
    val unmounted = Column(
      Column
        .props(200, "key", headerRenderer = (_: js.Any, _, _, _, _, _) => <<.div("header"))
    ).props
      .headerRenderer(headerParam)
    assertRender(unmounted, """<div><div>header</div></div>""")
  }
  test("support id") {
    assertEquals(Column(Column.props(200, "key")).props.id, js.undefined)
    assertEquals(Column(Column.props(200, "key", id = "id")).props.id, "id": js.UndefOr[String])
  }
  test("support label") {
    val label = <<.div("id")
    assertEquals(Column(Column.props(200, "key")).props.label, js.undefined)
    assertEquals(Column(Column.props(200, "key", label = label)).props.label,
                 label.rawNode: js.UndefOr[React.Node]
    )
  }
  test("support maxWidth") {
    assertEquals(Column(Column.props(200, "key")).props.maxWidth, js.undefined)
    assertEquals(Column(Column.props(200, "key", maxWidth = 100)).props.maxWidth,
                 100: js.UndefOr[JsNumber]
    )
  }
  test("support minWidth") {
    assertEquals(Column(Column.props(200, "key")).props.minWidth, js.undefined)
    assertEquals(Column(Column.props(200, "key", minWidth = 100)).props.minWidth,
                 100: js.UndefOr[JsNumber]
    )
  }
  test("support style") {
    val style    = js.Dynamic.literal(foo = 42, bar = "foobar")
    val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
    assertEquals(Column(Column.props(200, "key")).props.style === Some(js.Object()).orUndefined,
                 true
    )
    assertEquals(Column(Column.props(200, "key", style = Style(styleMap))).props.style.toOption
                   .map(_ === style),
                 Some(true)
    )
  }
  test("support header style") {
    val style    = js.Dynamic.literal(foo = 42, bar = "foobar")
    val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
    assertEquals(Column(Column.props(200, "key")).props.headerStyle, js.undefined)
    assertEquals(
      Column(Column.props(200, "key", headerStyle = Style(styleMap))).props.headerStyle.toOption
        .map(_ === style),
      Some(true)
    )
  }
}
