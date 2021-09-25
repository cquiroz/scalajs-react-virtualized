package react

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation.JSImport
import js.JSConverters._
import japgolly.scalajs.react.Callback
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.vdom.VdomNode
import react.common.style._
import react.common.EnumValue

package object virtualized {
  implicit class TableJsMethodsOps(val m: virtualized.Table.JsMethods) extends AnyVal {
    def forceUpdateGridCB: Callback = Callback(m.forceUpdateGrid())
    def measureAllRowsCB: Callback  = Callback(m.measureAllRows())
    def recomputeRowHeightsCB(index: Int): Callback =
      Callback(m.recomputeRowHeights(index))
    def recomputeRowsHeightsCB(indexes: Int*): Callback =
      Callback.sequence(indexes.map(recomputeRowHeightsCB))
    def scrollToPositionCB(scrollTop: Int): Callback =
      Callback(m.scrollToPosition(scrollTop))
    def scrollToRowCB(index: Int): Callback = Callback(m.scrollToRow(index))
    def offsetForRow(alignment: String): JsNumber =
      m.getOffsetForRow(js.Dynamic.literal(alignment = alignment))
    def offsetForRow(index: Int): JsNumber =
      m.getOffsetForRow(js.Dynamic.literal(index = index))
    def offsetForRow(alignment: String, index: Int): JsNumber =
      m.getOffsetForRow(js.Dynamic.literal(alignment = alignment, index = index))
  }
  // Column types
  //

  /** Types A Cell data, some js object with row entries B Column data, can be anything C Row data
    */
  type CellRenderer[A <: js.Object, B <: js.Any, C <: js.Object] =
    (A, B, String, C, Int) => VdomNode

  type HeaderRenderer[B <: js.Any] =
    (B, String, Option[Boolean], VdomNode, Option[String], SortDirection) => VdomNode

  type HeaderRowRenderer = (String, Array[VdomNode], Style) => VdomNode

  type CellDataGetter[B, C, A <: js.Object] = (B, String, C) => A

  // Table types
  //
  /** Types A Cell data, some js object with row entries B Column data, can be anything C Row data
    */
  type RowRenderer[C <: js.Object] = (
    String,
    Array[VdomNode],
    Int,
    Boolean,
    String,
    C,
    Option[OnRowClick],
    Option[OnRowClick],
    Option[OnRowClick],
    Option[OnRowClick],
    Option[OnRowClick],
    Style
  ) => VdomNode

  type RowGetter = Int => js.Object
  // Types for NoRowsRenderer
  type NoRowsRenderer = () => VdomNode

  // Types for RowClassName
  type RowClassName = Int => String

  // Types for RowHeight
  type RowHeight = Int => JsNumber

  // Types for RowStyle
  type RowStyle = Int => Style

  // Types for Sort
  type Sort = (String, SortDirection) => Callback

  type OnRowClick = Int => Callback

  type OnHeaderClick = (js.Object, String) => Callback

  type OnRowsRenderer = (Int, Int, Int, Int) => Callback

  type OnScroll = (JsNumber, JsNumber, JsNumber) => Callback

  type IndexParameter = raw.RawIndexParameter

  implicit class RawIndexParameterOps(val r: js.Function1[IndexParameter, Int]) extends AnyVal {
    def toScala: Int => JsNumber = (x: Int) => r(raw.RawIndexParameter(x))
  }

  private implicit class ClickCallbackOps(val cb: OnRowClick) extends AnyVal {
    def toJsCallback: raw.RawOnRowEvent =
      (i: raw.RawIndexParameter) => cb(i.index).runNow()
  }

  val defaultHeaderRendererS = (
    columnData:    js.Any,
    dataKey:       String,
    disableSort:   Option[Boolean],
    label:         VdomNode,
    sortBy:        Option[String],
    sortDirection: SortDirection
  ) =>
    VdomNode(
      defaultHeaderRenderer(
        raw.RawHeaderRendererParameter(columnData,
                                       dataKey,
                                       disableSort.orUndefined,
                                       label,
                                       sortBy.orUndefined,
                                       sortDirection.toRaw
        )
      )
    )

  val defaultCellRendererS =
    (cellData: js.Any, columnData: js.Any, dataKey: String, rowData: js.Any, rowIndex: Int) =>
      VdomNode(
        defaultCellRenderer(
          raw.RawCellRendererParameter(cellData, columnData, dataKey, rowData, rowIndex)
        )
      )

  val defaultHeaderRowRendererS: HeaderRowRenderer =
    (className: String, columns: Array[VdomNode], style: Style) =>
      VdomNode(
        defaultHeaderRowRenderer(
          raw.RawHeaderRowRendererParameter(className,
                                            columns.map(_.rawNode).toJSArray,
                                            Style.toJsObject(style)
          )
        )
      )

  def defaultRowRendererS[C <: js.Object]: RowRenderer[C] =
    (
      className:        String,
      columns:          Array[VdomNode],
      index:            Int,
      isScrolling:      Boolean,
      key:              String,
      rowData:          C,
      onRowClick:       Option[OnRowClick],
      onRowDoubleClick: Option[OnRowClick],
      onRowMouseOut:    Option[OnRowClick],
      onRowMouseOver:   Option[OnRowClick],
      onRowRightClick:  Option[OnRowClick],
      style:            Style
    ) =>
      VdomNode(
        defaultRowRenderer(
          raw.RawRowRendererParameter(
            className,
            columns.map(_.rawNode).toJSArray,
            index,
            isScrolling,
            key,
            rowData,
            onRowClick.map(_.toJsCallback).orUndefined,
            onRowDoubleClick.map(_.toJsCallback).orUndefined,
            onRowMouseOut.map(_.toJsCallback).orUndefined,
            onRowMouseOver.map(_.toJsCallback).orUndefined,
            onRowRightClick.map(_.toJsCallback).orUndefined,
            Style.toJsObject(style)
          )
        )
      )
}

package virtualized {

  sealed trait ScrollToAlignment
  object ScrollToAlignment {
    case object Auto extends ScrollToAlignment
    case object End extends ScrollToAlignment
    case object Start extends ScrollToAlignment
    case object Center extends ScrollToAlignment

    implicit val enum: EnumValue[ScrollToAlignment] = EnumValue.toLowerCaseString
  }

  // Default renderer implementations
  @js.native
  @JSImport("react-virtualized", "defaultTableHeaderRenderer")
  object defaultHeaderRenderer extends js.Function1[raw.RawHeaderRendererParameter, React.Node] {
    def apply(i: raw.RawHeaderRendererParameter): React.Node = js.native
  }

  @js.native
  @JSImport("react-virtualized", "defaultTableCellRenderer")
  object defaultCellRenderer extends js.Function1[raw.RawCellRendererParameter, React.Node] {
    def apply(i: raw.RawCellRendererParameter): React.Node = js.native
  }

  @js.native
  @JSImport("react-virtualized", "defaultTableHeaderRowRenderer")
  object defaultHeaderRowRenderer
      extends js.Function1[raw.RawHeaderRowRendererParameter, React.Node] {
    def apply(i: raw.RawHeaderRowRendererParameter): React.Node = js.native
  }

  @js.native
  @JSImport("react-virtualized", "defaultTableRowRenderer")
  object defaultRowRenderer extends js.Function1[raw.RawRowRendererParameter, React.Node] {
    def apply(i: raw.RawRowRendererParameter): React.Node = js.native
  }

  /** Raw facades
    */
  object raw {
    // Column types
    //
    trait RawHeaderRowRendererParameter extends js.Object {
      var className: String
      var columns: js.Array[React.Node]
      var style: js.Object
    }
    object RawHeaderRowRendererParameter {
      def apply(
        className: String,
        columns:   js.Array[React.Node],
        style:     js.Object
      ): RawHeaderRowRendererParameter = {
        val p = (new js.Object).asInstanceOf[RawHeaderRowRendererParameter]
        p.className = className
        p.columns = columns
        p.style = style
        p
      }
    }

    type RawHeaderRowRenderer =
      js.Function1[RawHeaderRowRendererParameter, React.Node]

    // Types for cellDataGetter
    trait RawCellDataParameter extends js.Object {
      var columnData: js.Any
      var dataKey: String
      var rowData: js.Any
    }
    object RawCellDataParameter {
      def apply(columnData: js.Any, dataKey: String, rowData: js.Any): RawCellDataParameter = {
        val p = (new js.Object).asInstanceOf[RawCellDataParameter]
        p.columnData = columnData
        p.dataKey = dataKey
        p.rowData = rowData
        p
      }
    }
    type RawCellDataGetter = js.Function1[RawCellDataParameter, js.Object]

    // types for cellRenderer
    trait RawCellRendererParameter extends js.Object {
      var cellData: js.Any
      var columnData: js.Any
      var dataKey: String
      var rowData: js.Any
      var rowIndex: Int
    }
    object RawCellRendererParameter {
      def apply(
        cellData:   js.Any,
        columnData: js.Any,
        dataKey:    String,
        rowData:    js.Any,
        rowIndex:   Int
      ): RawCellRendererParameter = {
        val p = (new js.Object).asInstanceOf[RawCellRendererParameter]
        p.cellData = cellData
        p.columnData = columnData
        p.dataKey = dataKey
        p.rowData = rowData
        p.rowIndex = rowIndex
        p
      }
    }
    type RawCellRenderer = js.Function1[RawCellRendererParameter, React.Node]

    // types for headerRenderer
    trait RawHeaderRendererParameter extends js.Object {
      var columnData: js.Any
      var dataKey: String
      var disableSort: js.UndefOr[Boolean]
      var label: React.Node
      var sortBy: js.UndefOr[String]
      var sortDirection: js.UndefOr[String]
    }
    object RawHeaderRendererParameter {
      def apply(
        columnData:    js.Any,
        dataKey:       String,
        disableSort:   js.UndefOr[Boolean],
        label:         VdomNode,
        sortBy:        js.UndefOr[String],
        sortDirection: js.UndefOr[String]
      ): RawHeaderRendererParameter = {
        val p = (new js.Object).asInstanceOf[RawHeaderRendererParameter]
        p.columnData = columnData
        p.dataKey = dataKey
        p.disableSort = disableSort
        p.label = label.rawNode
        p.sortBy = sortBy
        p.sortDirection = sortDirection
        p
      }
    }
    type RawHeaderRenderer = js.Function1[RawHeaderRendererParameter, React.Node]

    // Table types
    //

    trait RawRowRendererParameter extends js.Object {
      var className: String
      var columns: js.Array[React.Node]
      var index: Int
      var isScrolling: Boolean
      var key: String
      var rowData: js.Object
      var onRowClick: js.UndefOr[RawOnRowEvent]
      var onRowDoubleClick: js.UndefOr[RawOnRowEvent]
      var onRowMouseOut: js.UndefOr[RawOnRowEvent]
      var onRowMouseOver: js.UndefOr[RawOnRowEvent]
      var onRowRightClick: js.UndefOr[RawOnRowEvent]
      var style: js.Object
    }

    object RawRowRendererParameter {
      def apply(
        className:        String,
        columns:          js.Array[React.Node],
        index:            Int,
        isScrolling:      Boolean,
        key:              String,
        rowData:          js.Object,
        onRowClick:       js.UndefOr[RawOnRowEvent],
        onRowDoubleClick: js.UndefOr[RawOnRowEvent],
        onRowMouseOut:    js.UndefOr[RawOnRowEvent],
        onRowMouseOver:   js.UndefOr[RawOnRowEvent],
        onRowRightClick:  js.UndefOr[RawOnRowEvent],
        style:            js.Object
      ): RawRowRendererParameter = {
        val p = (new js.Object).asInstanceOf[RawRowRendererParameter]
        p.className = className
        p.columns = columns
        p.index = index
        p.isScrolling = isScrolling
        p.key = key
        p.rowData = rowData
        p.onRowClick = onRowClick
        p.onRowDoubleClick = onRowDoubleClick
        p.onRowMouseOut = onRowMouseOut
        p.onRowMouseOver = onRowMouseOver
        p.onRowRightClick = onRowRightClick
        p.style = style
        p
      }
    }
    type RawRowRenderer = js.Function1[RawRowRendererParameter, React.Node]

    // Types for row getter
    trait RawIndexParameter extends js.Object {
      var index: Int
    }
    object RawIndexParameter {
      def apply(index: Int): RawIndexParameter = {
        val p = (new js.Object).asInstanceOf[RawIndexParameter]
        p.index = index
        p
      }
    }
    type RawRowGetter = js.Function1[RawIndexParameter, Any]

    // No rows renderer
    type RawNoRowsRenderer = js.Function0[React.Node]

    // Class for rows
    type RawRowClassNameParam = String | RawRowClassName
    type RawRowClassName      = js.Function1[RawIndexParameter, String]

    // Types for RowHeight
    type RawRowHeight      = js.Function1[RawIndexParameter, JsNumber]
    type RawRowHeightParam = JsNumber | RawRowHeight

    // Types for RowStyle
    type RawRowStyleParam = js.Object | RawRowStyle
    type RawRowStyle      = js.Function1[RawIndexParameter, js.Object]

    // Types for Sort
    type RawSort = js.Function1[RawSortParam, Unit]
    trait RawSortParam extends js.Object {
      var sortBy: String
      var sortDirection: String
    }
    object RawSortParam {
      def apply(sortBy: String, sortDirection: String): RawSortParam = {
        val p = (new js.Object).asInstanceOf[RawSortParam]
        p.sortBy = sortBy
        p.sortDirection = sortDirection
        p
      }
    }

    // Events
    type RawOnRowEvent = js.Function1[RawIndexParameter, Unit]
    // Header click event
    trait RawHeaderClickParam extends js.Object {
      var columnData: js.Object
      var dataKey: String
    }
    object RawHeaderClickParam {
      def apply(columnData: js.Object, dataKey: String): RawHeaderClickParam = {
        val p = (new js.Object).asInstanceOf[RawHeaderClickParam]
        p.columnData = columnData
        p.dataKey = dataKey
        p
      }
    }
    type RawHeaderClickEvent = js.Function1[RawHeaderClickParam, Unit]

    implicit class RawClickCallbackOps(val cb: RawOnRowEvent) extends AnyVal {
      def toCallback: OnRowClick =
        (i: Int) => Callback(cb(RawIndexParameter(i)))
    }

    // Rows renderer event
    trait RawRowsRendererParam extends js.Object {
      var overscanStartIndex: Int
      var overscanStopIndex: Int
      var startIndex: Int
      var stopIndex: Int
    }
    object RawRowsRendererParam {
      def apply(
        overscanStartIndex: Int,
        overscanStopIndex:  Int,
        startIndex:         Int,
        stopIndex:          Int
      ): RawRowsRendererParam = {
        val p = (new js.Object).asInstanceOf[RawRowsRendererParam]
        p.overscanStartIndex = overscanStartIndex
        p.overscanStopIndex = overscanStopIndex
        p.startIndex = startIndex
        p.stopIndex = stopIndex
        p
      }
    }
    type RawRowsRendererEvent = js.Function1[RawRowsRendererParam, Unit]

    // Scroll event
    trait RawScrollParam extends js.Object {
      var clientHeight: JsNumber
      var scrollHeight: JsNumber
      var scrollTop: JsNumber
    }
    object RawScrollParam {
      def apply(
        clientHeight: JsNumber,
        scrollHeight: JsNumber,
        scrollTop:    JsNumber
      ): RawScrollParam = {
        val p = (new js.Object).asInstanceOf[RawScrollParam]
        p.clientHeight = clientHeight
        p.scrollHeight = scrollHeight
        p.scrollTop = scrollTop
        p
      }
    }
    type RawScrollEvent = js.Function1[RawScrollParam, Unit]

    @js.native
    @JSImport("react-virtualized", "SortDirection")
    private[virtualized] object RawSortDirection extends js.Object {
      val ASC: String  = js.native
      val DESC: String = js.native
    }

  }

}
