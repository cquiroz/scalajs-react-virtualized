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

import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

object Table {

  type ColumnArg = UnmountedMapped[Id, Column.Props, Null, RawMounted with Column, Column.Props, Null]

  @js.native
  @JSImport("react-virtualized", "Table")
  object RawComponent extends js.Object

  /*@js.native
  @JSImport("react-virtualized", "SortDirection")
  object SortDirection extends js.Object {
    val ASC: String = js.native
    val DESC: String = js.native
  }

  @ScalaJSDefined
  trait CellDataParameter extends js.Object {
    var columnData: js.Any
    var dataKey: String
    var rowData: js.Any
  }
  object CellDataParameter {
    def apply(columnData: js.Any, dataKey: String, rowData: js.Any): CellDataParameter = {
      val p = (new js.Object).asInstanceOf[CellDataParameter]
      p.columnData = columnData
      p.dataKey = dataKey
      p.rowData = rowData
      p
    }
  }
  type CellDataGetter = js.Function1[CellDataParameter, Any]

  @ScalaJSDefined
  trait CellRendererParameter extends js.Object {
    var cellData: js.Any
    var columnData: js.Any
    var dataKey: String
    var rowData: js.Any
    var rowIndex: JsNumber
  }
  object CellRendererParameter {
    def apply(cellData: js.Any, columnData: js.Any, dataKey: String, rowData: js.Any, rowIndex: JsNumber): CellRendererParameter = {
      val p = (new js.Object).asInstanceOf[CellRendererParameter]
      p.cellData = cellData
      p.columnData = columnData
      p.dataKey = dataKey
      p.rowData = rowData
      p.rowIndex = rowIndex
      p
    }
  }
  type CellRenderer = js.Function1[CellRendererParameter, VdomNode]
  type RawCellRenderer = js.Function1[CellRendererParameter, ReactNode]

  @ScalaJSDefined
  trait HeaderRendererParameter extends js.Object {
    var columnData: js.Any
    var dataKey: String
    var disableSort: Boolean
    var label: ReactNode
    var sortBy: String
    var sortDirection: String
  }
  object HeaderRendererParameter {
    def apply(columnData: js.Any, dataKey: String, disableSort: Boolean, label: VdomNode, sortBy: String, sortDirection: String): HeaderRendererParameter = {
      val p = (new js.Object).asInstanceOf[HeaderRendererParameter]
      p.columnData = columnData
      p.dataKey = dataKey
      p.disableSort = disableSort
      p.label = label.rawNode
      p.sortBy = sortBy
      p.sortDirection = sortDirection
      p
    }
  }
  type HeaderRenderer = js.Function1[HeaderRendererParameter, VdomNode]
  type RawHeaderRenderer = js.Function1[HeaderRendererParameter, ReactNode]*/
  @ScalaJSDefined
  trait IndexParameter extends js.Object {
    var index: JsNumber
  }
  object IndexParameter {
    def apply(index: JsNumber): IndexParameter = {
      val p = (new js.Object).asInstanceOf[IndexParameter]
      p.index = index
      p
    }
  }
  type RowGetter = js.Function1[IndexParameter, Any]

  // Types for NoRowsRenderer
  private type RawNoRowsRenderer = js.Function0[ReactNode]
  type NoRowsRenderer = () => VdomNode

  // Types for RowClassName
  type RawRowClassName = js.Function1[IndexParameter, String]
  type RowClassName = Int => String
  type RowClassNameParam = String | RawRowClassName

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
    var estimatedRowSize: js.UndefOr[JsNumber] = js.native

    /** Optional custom CSS class name to attach to inner Grid element. */
    var gridClassName: js.UndefOr[String] = js.native

    /** Optional inline style to attach to inner Grid element. */
    var gridStyle: js.UndefOr[String] = js.native

    /** Optional CSS class to apply to all column headers */
    var headerClassName: js.UndefOr[String] = js.native

    /** Fixed height of header row */
    var headerHeight: JsNumber = js.native

    /**
     * Responsible for rendering a table row given an array of columns:
     * Should implement the following interface: ({
     *   className: string,
     *   columns: any[],
     *   style: any
     * }): PropTypes.node
     */
    // headerRowRenderer: PropTypes.func,

    /** Optional custom inline style to attach to table header columns. */
    var headerStyle: js.UndefOr[js.Object] = js.native

    /** Fixed/available height for out DOM element */
    var height: JsNumber = js.native

    /** Optional id */
    var id: js.UndefOr[String] = js.native

    /** Optional renderer to be used in place of table body rows when rowCount is 0 */
    var noRowsRenderer: js.UndefOr[RawNoRowsRenderer]

    /**
    * Optional callback when a column's header is clicked.
    * ({ columnData: any, dataKey: string }): void
    */
    // onHeaderClick: PropTypes.func,

    /**
     * Callback invoked when a user clicks on a table row.
     * ({ index: number }): void
     */
    // onRowClick: PropTypes.func,

    /**
     * Callback invoked when a user double-clicks on a table row.
     * ({ index: number }): void
     */
    // onRowDoubleClick: PropTypes.func,

    /**
     * Callback invoked when the mouse leaves a table row.
     * ({ index: number }): void
     */
    // onRowMouseOut: PropTypes.func,

    /**
     * Callback invoked when a user moves the mouse over a table row.
     * ({ index: number }): void
     */
    // onRowMouseOver: PropTypes.func,

    /**
     * Callback invoked when a user right-clicks on a table row.
     * ({ index: number }): void
     */
    // onRowRightClick: PropTypes.func,

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
    var rowClassName: RowClassNameParam = js.native// PropTypes.oneOfType([PropTypes.string, PropTypes.func]),

    /**
     * Callback responsible for returning a data row given an index.
     * ({ index: number }): any
     */
    // rowGetter: PropTypes.func.isRequired,
    var rowGetter: RowGetter = js.native

    /**
     * Either a fixed row height (number) or a function that returns the height of a row given its index.
     * ({ index: number }): number
     */
    // rowHeight: PropTypes.oneOfType([PropTypes.number, PropTypes.func])
    //   .isRequired,
    var rowHeight: JsNumber = js.native

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
    // rowStyle: PropTypes.oneOfType([PropTypes.object, PropTypes.func])
    //   .isRequired,

    /** See Grid#scrollToAlignment */
    // scrollToAlignment: PropTypes.oneOf(['auto', 'end', 'start', 'center'])
    //   .isRequired,

    /** Row index to ensure visible (by forcefully scrolling if necessary) */
    var scrollToIndex: JsNumber = js.native

    /** Vertical offset. */
    var scrollTop: js.UndefOr[JsNumber] = js.native

    /**
     * Sort function to be called if a sortable header is clicked.
     * ({ sortBy: string, sortDirection: SortDirection }): void
     */
    // sort: PropTypes.func,

    /** Table data is currently sorted by this :dataKey (if it is sorted at all) */
    var sortBy: js.UndefOr[String] = js.native

    /** Table data is currently sorted in this direction (if it is sorted at all) */
    // sortDirection: PropTypes.oneOf([SortDirection.ASC, SortDirection.DESC]),

    /** Optional inline style */
    var style: js.UndefOr[js.Object] = js.native

    /** Tab index for focus */
    var tabIndex: JsNumber = js.native

    /** Width of list */
    var width: JsNumber = js.native
  }

  // private def toRawNode(vdomNode: VdomNode): ReactNode = vdomNode.rawNode

  def props(
    headerHeight: Int,
    height: Int,
    rowCount: Int,
    rowGetter: RowGetter,
    rowHeight: Int,
    width: Int,
    headerClassName: js.UndefOr[String] = js.undefined,
    disableHeader: js.UndefOr[Boolean] = js.undefined,
    noRowsRenderer: NoRowsRenderer = () => null, // default from react-virtualized
    overscanRowCount: JsNumber = 10, // default from react-virtualized
    rowClassName: String | RowClassName = null
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.headerHeight = headerHeight
    p.height = height
    p.rowCount = rowCount
    p.rowGetter = rowGetter
    p.rowHeight = rowHeight
    p.width = width
    p.headerClassName = headerClassName
    p.disableHeader = disableHeader
    p.noRowsRenderer = Some[RawNoRowsRenderer](() => noRowsRenderer.apply.rawNode).orUndefined
    p.overscanRowCount = overscanRowCount
    // some uglies to get scala and js to talk
    (rowClassName: Any) match {
      case null =>
      case s: String =>
        p.rowClassName = s
      case f =>
        p.rowClassName = ((i: IndexParameter) => f.asInstanceOf[RowClassName](i.index.asInstanceOf[Int])): RawRowClassName
    }
    p
  }

  private val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(p: Props, children: ColumnArg*): UnmountedWithRawType[Props, Null, RawMounted] = component.apply(p)(children.map(_.vdomElement): _*)

}
