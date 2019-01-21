package react
package virtualized

import cats.syntax.eq._
import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import japgolly.scalajs.react.vdom.html_<^.{ < => <<, _ }
import react.common.Style
import raw._
import utest._

object ColumnTests extends TestSuite with TestUtils {

  val tests = Tests {
    'requireWidthAndDataKey - {
      Column(Column.props(200, "key")).props.width ==> 200
      Column(Column.props(200, "key")).props.dataKey ==> "key"
    }
    "support ariaLabel" - {
      Column(Column.props(200, "key")).props.`aria-label` ==> (())
      Column(Column.props(200, "key", ariaLabel = "Label")).props.`aria-label` ==> "Label"
    }
    "have a default cellDataGetter" - {
      val dataMap = Map("key" -> 1, "b" -> 2).toJSDictionary
      Column(Column.props(200, "key")).props.cellDataGetter.toOption
        .map(_(RawCellDataParameter("col", "key", dataMap))) ==> Some(1)
    }
    "support cellDataGetter" - {
      val cell: js.Object = js.Dynamic.literal(foo = 42, bar = "foobar")
      Column(Column.props(
        200,
        "key",
        cellDataGetter = Some((_: js.Any, _: String, _: js.Object) => cell))).props.cellDataGetter.toOption
        .map(_(RawCellDataParameter("col", "key", "row")))
        .map(_ === cell) ==> Some(true)
    }
    "have a default cellRenderer" - {
      Column(Column.props(200, "key")).props.cellRenderer.toOption
        .map(_(RawCellRendererParameter("cellData", "col", "key", "row", 1))) ==> Some("cellData")
    }
    "support cellRenderer" - {
      val r = Column(Column.props(200,
                                  "key",
                                  cellRenderer = (_: js.Any, _: js.Any, _, _: js.Any, _) =>
                                    <<.div("abc"))).props.cellRenderer.toOption
        .map(_(RawCellRendererParameter("cellData", "col", "key", "row", 1)))
      assertRenderNode(r, "<div><div>abc</div></div>")
    }
    "support className" - {
      Column(Column.props(200, "key")).props.className ==> (())
      Column(Column.props(200, "key", className = "my-class")).props.className ==> "my-class"
    }
    "support columnData" - {
      Column(Column.props(200, "key")).props.columnData ==> (())
      val dataObject = js.Dynamic.literal(foo = 42, bar = "foobar")
      Column(Column.props(200, "key", columnData = dataObject)).props.columnData ==> dataObject
    }
    "support disableSort" - {
      Column(Column.props(200, "key")).props.disableSort ==> (())
      Column(Column.props(200, "key", disableSort = true)).props.disableSort.toOption ==> Some(true)
    }
    "support defaultSortDirection" - {
      Column(Column.props(200, "key")).props.defaultSortDirection ==> SortDirection.ASC.toRaw
      Column(Column.props(200, "key", defaultSortDirection = SortDirection.DESC)).props.defaultSortDirection ==> SortDirection.DESC.toRaw
    }
    "support flexGrow" - {
      Column(Column.props(200, "key")).props.flexGrow ==> 0
      Column(Column.props(200, "key", disableSort = true, flexGrow = 1)).props.flexGrow ==> 1
    }
    "support flexShrink" - {
      Column(Column.props(200, "key")).props.flexShrink ==> 1
      Column(Column.props(200, "key", disableSort = true, flexShrink = 0)).props.flexShrink ==> 0
    }
    "support headerClassName" - {
      Column(Column.props(200, "key")).props.headerClassName ==> (())
      Column(Column.props(200, "key", headerClassName = "my-class")).props.headerClassName ==> "my-class"
    }
    "have a default headerRenderer" - {
      val label = <<.div("Label")
      val headerParam =
        RawHeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
      val unmounted = Column(Column.props(200, "key")).props.headerRenderer(headerParam)
      assertRender(
        unmounted,
        """<div><span class="ReactVirtualized__Table__headerTruncatedText" title="[object Object]"><div>Label</div></span><svg class="ReactVirtualized__Table__sortableHeaderIcon ReactVirtualized__Table__sortableHeaderIcon--ASC" width="18" height="18" viewBox="0 0 24 24"><path d="M7 14l5-5 5 5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>"""
      )
    }
    "support headerRenderer" - {
      val label = <<.div("Label")
      val headerParam =
        RawHeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
      val unmounted = Column(
        Column
          .props(200, "key", headerRenderer = (_: js.Any, _, _, _, _, _) => <<.div("header"))).props
        .headerRenderer(headerParam)
      assertRender(unmounted, """<div><div>header</div></div>""")
    }
    "support id" - {
      Column(Column.props(200, "key")).props.id ==> (())
      Column(Column.props(200, "key", id = "id")).props.id ==> "id"
    }
    "support label" - {
      val label = <<.div("id")
      Column(Column.props(200, "key")).props.label ==> (())
      Column(Column.props(200, "key", label = label)).props.label ==> label.rawNode
    }
    "support maxWidth" - {
      Column(Column.props(200, "key")).props.maxWidth ==> (())
      Column(Column.props(200, "key", maxWidth = 100)).props.maxWidth ==> 100
    }
    "support minWidth" - {
      Column(Column.props(200, "key")).props.minWidth ==> (())
      Column(Column.props(200, "key", minWidth = 100)).props.minWidth ==> 100
    }
    "support style" - {
      val style    = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      Column(Column.props(200, "key")).props.style === Some(js.Object()).orUndefined ==> true
      Column(Column.props(200, "key", style = Style(styleMap))).props.style.toOption
        .map(_ === style) ==> Some(true)
    }
    "support header style" - {
      val style    = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      Column(Column.props(200, "key")).props.headerStyle ==> (())
      Column(Column.props(200, "key", headerStyle = Style(styleMap))).props.headerStyle.toOption
        .map(_ === style) ==> Some(true)
    }
  }
}
