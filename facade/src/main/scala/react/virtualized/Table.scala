package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedMapped, UnmountedWithRawType}
import japgolly.scalajs.react.internal.Effect.Id

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._
import japgolly.scalajs.react.raw.{JsNumber, ReactNode}
import japgolly.scalajs.react.vdom.Exports._

import scala.scalajs.js.annotation.JSImport
import defs._
import raw._

object Table {

  type ColumnArg = UnmountedMapped[Id, Column.Props, Null, RawMounted with Column, Column.Props, Null]

  @js.native
  @JSImport("react-virtualized", "Table")
  object RawComponent extends js.Object

  trait IndexParameter extends js.Object {
    var index: Int
  }
  object IndexParameter {
    def apply(index: Int): IndexParameter = {
      val p = (new js.Object).asInstanceOf[IndexParameter]
      p.index = index
      p
    }
  }
  // Types for row getter
  type RawRowGetter = js.Function1[IndexParameter, Any]
  type RowGetter = Int => js.Object

  // Types for NoRowsRenderer
  private type RawNoRowsRenderer = js.Function0[ReactNode]
  type NoRowsRenderer = () => VdomNode

  // Types for RowClassName
  type RawRowClassName = js.Function1[IndexParameter, String]
  type RowClassName = Int => String
  type RowClassNameParam = String | RawRowClassName

  // Types for RowHeight
  type RawRowHeight = js.Function1[IndexParameter, JsNumber]
  type RowHeight = Int => Int
  type RowHeightParam = JsNumber | RawRowHeight

  // Types for RowStyle
  type RawRowStyle = js.Function1[IndexParameter, js.Object]
  type RowStyle = Int => Style
  type RowStyleParam = js.Object | RawRowStyle

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
  type Sort = (String, SortDirection) => Callback

  sealed trait ScrollToAlignment
  object ScrollToAlignment {
    case object Auto extends ScrollToAlignment
    case object End extends ScrollToAlignment
    case object Start extends ScrollToAlignment
    case object Center extends ScrollToAlignment

    def toRaw(s: ScrollToAlignment): String = s match {
      case Auto   => "auto"
      case End    => "end"
      case Start  => "start"
      case Center => "center"
    }
  }

  type RawOnRowEvent = js.Function1[IndexParameter, Unit]
  type OnRowClick = Int => Callback

  @js.native
  trait Props extends js.Object {
    /** Optional aria-label value to set on the column header */
    var `aria-label`: js.UndefOr[String] = js.native

    /**
     * Removes fixed height from the scrollingContainer so that the total height
     * of rows can stretch the window. Intended for use with WindowScroller
     */
    var autoHeight: js.UndefOr[Boolean] = js.native

    /** One or more Columns describing the data displayed in this row */
    var children: js.Array[Children] = js.native

    /** Optional CSS class name */
    var className: js.UndefOr[String] = js.native

    /** Disable rendering the header at all */
    var disableHeader: js.UndefOr[Boolean] = js.native

    /**
     * Used to estimate the total height of a Table before all of its rows have actually been measured.
     * The estimated total height is adjusted as rows are rendered.
     */
    var estimatedRowSize: Int = js.native

    /** Optional custom CSS class name to attach to inner Grid element. */
    var gridClassName: js.UndefOr[String] = js.native

    /** Optional inline style to attach to inner Grid element. */
    var gridStyle: js.UndefOr[js.Object] = js.native

    /** Optional CSS class to apply to all column headers */
    var headerClassName: js.UndefOr[String] = js.native

    /** Fixed height of header row */
    var headerHeight: Int = js.native

    /**
     * Responsible for rendering a table row given an array of columns:
     * Should implement the following interface: ({
     *   className: string,
     *   columns: any[],
     *   style: any
     * }): PropTypes.node
     */
    var headerRowRenderer: RawHeaderRowRenderer = js.native

    /** Optional custom inline style to attach to table header columns. */
    var headerStyle: js.UndefOr[js.Object] = js.native

    /** Fixed/available height for out DOM element */
    var height: JsNumber = js.native

    /** Optional id */
    var id: js.UndefOr[String] = js.native

    /** Optional renderer to be used in place of table body rows when rowCount is 0 */
    var noRowsRenderer: js.UndefOr[RawNoRowsRenderer] = js.native

    /**
    * Optional callback when a column's header is clicked.
    * ({ columnData: any, dataKey: string }): void
    */
    // var onHeaderClick: PropTypes.func,

    /**
     * Callback invoked when a user clicks on a table row.
     * ({ index: number }): void
     */
    var onRowClick: RawOnRowEvent = js.native

    /**
     * Callback invoked when a user double-clicks on a table row.
     * ({ index: number }): void
     */
    var onRowDoubleClick: RawOnRowEvent = js.native

    /**
     * Callback invoked when the mouse leaves a table row.
     * ({ index: number }): void
     */
    var onRowMouseOut: RawOnRowEvent = js.native

    /**
     * Callback invoked when a user moves the mouse over a table row.
     * ({ index: number }): void
     */
    var onRowMouseOver: RawOnRowEvent = js.native

    /**
     * Callback invoked when a user right-clicks on a table row.
     * ({ index: number }): void
     */
    var onRowRightClick: RawOnRowEvent = js.native

    /**
     * Callback invoked with information about the slice of rows that were just rendered.
     * ({ startIndex, stopIndex }): void
     */
    // onRowsRendered: PropTypes.func,

    /**
     * Callback invoked whenever the scroll offset changes within the inner scrollable region.
     * This callback can be used to sync scrolling between lists, tables, or grids.
     * ({ clientHeight, scrollHeight, scrollTop }): void
     */
    // onScroll: PropTypes.func.isRequired,

    /** See Grid#overscanIndicesGetter */
    // overscanIndicesGetter: PropTypes.func.isRequired,

    /**
     * Number of rows to render above/below the visible bounds of the list.
     * These rows can help for smoother scrolling on touch devices.
     */
     var overscanRowCount: JsNumber = js.native

    /**
     * Optional CSS class to apply to all table rows (including the header row).
     * This property can be a CSS class name (string) or a function that returns a class name.
     * If a function is provided its signature should be: ({ index: number }): string
     */
    var rowClassName: RowClassNameParam = js.native

    /**
     * Callback responsible for returning a data row given an index.
     * ({ index: number }): any
     */
    var rowGetter: RawRowGetter = js.native

    /**
     * Either a fixed row height (number) or a function that returns the height of a row given its index.
     * ({ index: number }): number
     */
    var rowHeight: RowHeightParam = js.native

    /** Number of rows in table. */
    var rowCount: JsNumber = js.native

    /**
     * Responsible for rendering a table row given an array of columns:
     * Should implement the following interface: ({
     *   className: string,
     *   columns: Array,
     *   index: number,
     *   isScrolling: boolean,
     *   onRowClick: ?Function,
     *   onRowDoubleClick: ?Function,
     *   onRowMouseOver: ?Function,
     *   onRowMouseOut: ?Function,
     *   rowData: any,
     *   style: any
     * }): PropTypes.node
     */
    // rowRenderer: PropTypes.func,

    /** Optional custom inline style to attach to table rows. */
    var rowStyle: RowStyleParam = js.native

    /** See Grid#scrollToAlignment */
    var scrollToAlignment: String = js.native

    /** Row index to ensure visible (by forcefully scrolling if necessary) */
    var scrollToIndex: JsNumber = js.native

    /** Vertical offset. */
    var scrollTop: js.UndefOr[JsNumber] = js.native

    /**
     * Sort function to be called if a sortable header is clicked.
     * ({ sortBy: string, sortDirection: SortDirection }): void
     */
    var sort: js.UndefOr[RawSort] = js.native

    /** Table data is currently sorted by this :dataKey (if it is sorted at all) */
    var sortBy: js.UndefOr[String] = js.native

    /** Table data is currently sorted in this direction (if it is sorted at all) */
    var sortDirection: js.UndefOr[String] = js.native

    /** Optional inline style */
    var style: js.UndefOr[js.Object] = js.native

    /** Tab index for focus */
    var tabIndex: js.UndefOr[JsNumber] = js.native

    /** Width of list */
    var width: JsNumber = js.native
  }

  def props(
    headerHeight: Int,
    height: Int,
    rowCount: Int,
    rowGetter: RowGetter,
    rowHeight: JsNumber | RowHeight,
    width: Int,
    ariaLabel: js.UndefOr[String] = js.undefined,
    autoHeight: js.UndefOr[Boolean] = js.undefined,
    className: js.UndefOr[String] = js.undefined,
    disableHeader: js.UndefOr[Boolean] = js.undefined,
    estimatedRowSize: Int = 30,
    gridClassName: js.UndefOr[String] = js.undefined,
    gridStyle: js.UndefOr[Style] = js.undefined,
    headerClassName: js.UndefOr[String] = js.undefined,
    headerStyle: js.UndefOr[Style] = js.undefined,
    headerRowRenderer: HeaderRowRenderer = defaultHeaderRowRendererS,
    id: js.UndefOr[String] = js.undefined,
    noRowsRenderer: NoRowsRenderer = () => null, // default from react-virtualized
    onRowClick: OnRowClick = _ => Callback.empty,
    onRowDoubleClick: OnRowClick = _ => Callback.empty,
    onRowMouseOut: OnRowClick = _ => Callback.empty,
    onRowMouseOver: OnRowClick = _ => Callback.empty,
    onRowRightClick: OnRowClick = _ => Callback.empty,
    overscanRowCount: JsNumber = 10, // default from react-virtualized
    rowClassName: String | RowClassName = null,
    style: js.UndefOr[Style] = js.undefined,
    tabIndex: js.UndefOr[Int] = js.undefined,
    sort: js.UndefOr[Sort] = js.undefined,
    sortBy: js.UndefOr[String] = js.undefined,
    scrollToIndex: JsNumber = -1,
    scrollTop: js.UndefOr[Int] = js.undefined,
    sortDirection: js.UndefOr[SortDirection] = js.undefined,
    scrollToAlignment: ScrollToAlignment = ScrollToAlignment.Auto,
    rowStyle: Style | RowStyle = Style(Map.empty)
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.headerHeight = headerHeight
    p.height = height
    p.rowCount = rowCount
    p.rowGetter = (i: IndexParameter) => rowGetter(i.index)
    p.width = width
    p.`aria-label` = ariaLabel
    p.autoHeight = autoHeight
    p.className = className
    p.disableHeader = disableHeader
    p.estimatedRowSize = estimatedRowSize
    p.gridClassName = gridClassName
    p.gridStyle = gridStyle.map(Style.toJsObject)
    p.headerClassName = headerClassName
    p.headerStyle = headerStyle.map(Style.toJsObject)
    p.headerRowRenderer = (r: RawHeaderRowRendererParameter) => headerRowRenderer(r.className, r.columns.map(VdomNode.apply).toArray, Style.fromJsObject(r.style)).toRaw
    p.id = id
    p.noRowsRenderer = Some[RawNoRowsRenderer](() => noRowsRenderer.apply.rawNode).orUndefined
    p.onRowClick = (x: IndexParameter) => onRowClick(x.index).runNow()
    p.onRowDoubleClick = (x: IndexParameter) => onRowDoubleClick(x.index).runNow()
    p.onRowMouseOut = (x: IndexParameter) => onRowMouseOut(x.index).runNow()
    p.onRowMouseOver = (x: IndexParameter) => onRowMouseOver(x.index).runNow()
    p.onRowRightClick = (x: IndexParameter) => onRowRightClick(x.index).runNow()
    p.overscanRowCount = overscanRowCount
    p.style = style.map(Style.toJsObject)
    p.tabIndex = tabIndex
    p.sortBy = sortBy
    p.scrollToIndex = scrollToIndex
    p.scrollTop = scrollTop
    p.sortDirection = sortDirection.map(_.toRaw)
    p.sort = sort.map { f => (i: RawSortParam) => f(i.sortBy, SortDirection.fromRaw(i.sortDirection)).runNow()}
    p.scrollToAlignment = ScrollToAlignment.toRaw(scrollToAlignment)
    // some uglies to get scala and js to talk
    p.rowStyle = (rowStyle: Any) match {
      case o: Style => Style.toJsObject(o)
      case f =>
        ((i: IndexParameter) => Style.toJsObject(f.asInstanceOf[RowStyle](i.index))): RawRowStyle
    }
    (rowClassName: Any) match {
      case null =>
      case s: String =>
        p.rowClassName = s
      case f =>
        p.rowClassName = ((i: IndexParameter) => f.asInstanceOf[RowClassName](i.index)): RawRowClassName
    }
    (rowHeight: Any) match {
      case null =>
      case s: Int =>
        p.rowHeight = s
      case f =>
        p.rowHeight = ((i: IndexParameter) => f.asInstanceOf[RowHeight](i.index)): RawRowHeight
    }
    p
  }

  private val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(p: Props, children: ColumnArg*): UnmountedWithRawType[Props, Null, RawMounted] = component.apply(p)(children.map(_.vdomElement): _*)

}
