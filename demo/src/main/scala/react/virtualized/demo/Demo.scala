package react.virutalized.demo

import scala.scalajs.js
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import react.virtualized._
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.annotation.JSExportTopLevel
import Data.DataRow

object TableStaticDemo {

  def datum(data:     List[DataRow])(i: Int) = data(i % data.length)
  def rowheight(data: List[DataRow])(i: Int) = datum(data)(i).size

  final case class Props(useDynamicRowHeight: Boolean, sortBy:     String, s: Size)
  final case class State(sortDirection:       SortDirection, data: List[DataRow])

  def headerRenderer(sortBy: String)(
    columnData:              DataRow,
    dataKey:                 String,
    disableSort:             Option[Boolean],
    label:                   VdomNode,
    sortByParam:             Option[String],
    sortDirection:           SortDirection
  ): VdomNode =
    <.div("Full Name", SortIndicator(SortDirection.ASC).when(sortBy == dataKey))

  def rowClassName(i: Int): String = i match {
    case x if x < 0      => "headerRow"
    case x if x % 2 == 0 => "evenRow"
    case _               => "oddRow"
  }

  val component = ScalaComponent
    .builder[Props]("TableStaticDemo")
    .initialState(State(SortDirection.ASC, Data.generateRandomList))
    .renderPS { ($, props, state) =>
      def sort(index: String, sortDirection: SortDirection): Callback = {
        val sorted = state.data.sortBy(_.index)
        $.setState(
          state.copy(data          = if (sortDirection == SortDirection.ASC) sorted else sorted.reverse,
                     sortDirection = sortDirection)
        )
      }
      val columns = List(
        Column(60, "index", label      = "Index", disableSort  = false),
        Column(90, "name", disableSort = false, headerRenderer = headerRenderer(props.sortBy)),
        Column(
          210,
          "random",
          disableSort = true,
          className   = "exampleColumn",
          label       = "The description label is so long it will be truncated",
          flexGrow    = 1,
          cellRenderer =
            (cellData: DataRow, _: js.Any, _: String, _: js.Any, _: Int) => cellData.toString
        )
      )
      Table(
        Table.props(
          disableHeader    = false,
          noRowsRenderer   = () => <.div(^.cls := "noRows", "No rows"),
          overscanRowCount = 10,
          rowClassName     = rowClassName _,
          height           = 270,
          rowCount         = 1000,
          rowHeight        = if (props.useDynamicRowHeight) rowheight(state.data) _ else 40,
          onRowClick       = x => Callback.log(x),
          onScroll         = (c, s, t) => Callback.log(s"$c $s $t"),
          width            = scala.math.max(1, props.s.width.toInt),
          rowGetter        = datum(state.data),
          headerClassName  = "headerColumn",
          sort             = sort _,
          sortBy           = props.sortBy,
          sortDirection    = state.sortDirection,
          scrollTop        = 2000,
          headerHeight     = 30
        ),
        columns: _*
      )
    }
    .build

  def apply(p: Props) = component(p)
}

object TableCache {
  val cache = CellMeasurerCache(FixedHeight, defaultWidth = 100, minWidth = 25)
}

object TableDynamicDemo {

  def datum(data:     List[DataRow])(i: Int) = data(i % data.length)
  def rowheight(data: List[DataRow])(i: Int) = datum(data)(i).size

  final case class Props(useDynamicRowHeight: Boolean, sortBy:     String, s: Size)
  final case class State(sortDirection:       SortDirection, data: List[DataRow])

  def headerRenderer(sortBy: String)(
    columnData:              DataRow,
    dataKey:                 String,
    disableSort:             Option[Boolean],
    label:                   VdomNode,
    sortByParam:             Option[String],
    sortDirection:           SortDirection
  ): VdomNode =
    <.div("Full Name", SortIndicator(SortDirection.ASC).when(sortBy == dataKey))

  def rowClassName(i: Int): String = i match {
    case x if x < 0      => "headerRow"
    case x if x % 2 == 0 => "evenRow"
    case _               => "oddRow"
  }

  val dynamicCellRenderer: CellRenderer[DataRow, js.Object, js.Object] =
    (cellData: DataRow, _: js.Object, _: String, _: js.Object, rowIndex: Int) =>
      CellMeasurer(
        cache       = TableCache.cache,
        parent      = CellMeasurer.Parent.Zero,
        columnIndex = 2,
        rowIndex    = rowIndex,
        children    = <.div(cellData.toString)
      )

  val component = ScalaComponent
    .builder[Props]("TableDynamicDemo")
    .initialState(State(SortDirection.ASC, Data.generateRandomList))
    .renderPS { ($, props, state) =>
      TableCache.cache.clearAll()
      def sort(index: String, sortDirection: SortDirection): Callback = {
        val sorted = state.data.sortBy(_.index)
        $.setState(
          state.copy(data          = if (sortDirection == SortDirection.ASC) sorted else sorted.reverse,
                     sortDirection = sortDirection)
        )
      }
      val columns = List(
        Column(60, "index", label      = "Index", disableSort  = false),
        Column(90, "name", disableSort = false, headerRenderer = headerRenderer(props.sortBy)),
        Column(
          TableCache.cache.getWidth(2),
          "random",
          disableSort  = true,
          className    = "exampleColumn",
          label        = "The description label is so long it will be truncated",
          flexGrow     = 1,
          cellRenderer = dynamicCellRenderer
        )
      )
      val t = Table(
        Table.props(
          deferredMeasurementCache = TableCache.cache,
          disableHeader            = false,
          noRowsRenderer           = () => <.div(^.cls := "noRows", "No rows"),
          overscanRowCount         = 10,
          rowClassName             = rowClassName _,
          height                   = 600,
          rowCount                 = 10,
          rowHeight                = TableCache.cache.rowHeight.toScala,
          onRowClick               = x => Callback.log(x),
          onScroll                 = (c, s, t) => Callback.log(s"$c $s $t"),
          width                    = props.s.width.toInt,
          rowGetter                = datum(state.data),
          headerClassName          = "headerColumn",
          sort                     = sort _,
          sortBy                   = props.sortBy,
          sortDirection            = state.sortDirection,
          scrollTop                = 2000,
          headerHeight             = 30
        ),
        columns: _*
      )
      t.mapMounted(_.raw.scrollToRow(20))
      t
    }
    .build

  def apply(p: Props) = component(p)
}

@JSExportTopLevel("Demo")
object Demo {
  val tableF = (s: Size) => TableStaticDemo(TableStaticDemo.Props(true, "index", s)).vdomElement

  val tableD = (s: Size) => TableDynamicDemo(TableDynamicDemo.Props(true, "index", s)).vdomElement

  val component = ScalaComponent
    .builder[Unit]("Demo")
    .stateless
    .render_P { _ =>
      <.div(
        AutoSizer(tableF, disableHeight = true)
      )
    }
    .build

  @JSExport
  def start(): Unit = {
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    component().renderIntoDOM(container)
    ()
  }
}
