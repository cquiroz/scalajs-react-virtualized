package react
package virtualized

import scala.scalajs.js
import js.annotation.JSImport
import js.JSConverters._
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedMapped}
import japgolly.scalajs.react.internal.Effect.Id
import japgolly.scalajs.react.raw.{JsNumber, ReactNode}
import japgolly.scalajs.react.vdom.VdomNode
import raw._
import defs._

trait Column extends js.Object

object Column {
  @js.native
  @JSImport("react-virtualized", "Column")
  object RawComponent extends js.Object

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

  @js.native
  trait Props extends js.Object {
    /** Optional aria-label value to set on the column header */
    var `aria-label`: js.UndefOr[String] = js.native

    /**
     * Callback responsible for returning a cell's data, given its :dataKey
     * ({ columnData: any, dataKey: string, rowData: any }): any
     */
    var cellDataGetter: js.UndefOr[CellDataGetter] = js.native

    /**
     * Callback responsible for rendering a cell's contents.
     * ({ cellData: any, columnData: any, dataKey: string, rowData: any, rowIndex: number }): node
     */
    var cellRenderer: js.UndefOr[RawCellRenderer] = js.native

    /** Optional CSS class to apply to cell */
    var className: js.UndefOr[String] = js.native

    /** Optional additional data passed to this column's :cellDataGetter */
    var columnData: js.UndefOr[js.Object] = js.native

    /** Uniquely identifies the row-data attribute corresponding to this cell */
    var dataKey: js.Any = js.native

    /** Optional direction to be used when clicked the first time */
    var defaultSortDirection: String = js.native

    /** If sort is enabled for the table at large, disable it for this column */
    var disableSort: js.UndefOr[Boolean] = js.native

    /** Flex grow style; defaults to 0 */
    var flexGrow: js.UndefOr[JsNumber] = js.native

    /** Flex shrink style; defaults to 1 */
    var flexShrink: js.UndefOr[JsNumber] = js.native

    /** Optional CSS class to apply to this column's header */
    var headerClassName: js.UndefOr[String] = js.native

    /**
     * Optional callback responsible for rendering a column header contents.
     * ({ columnData: object, dataKey: string, disableSort: boolean, label: node, sortBy: string, sortDirection: string }): PropTypes.node
     */
    var headerRenderer: RawHeaderRenderer = js.native

    /** Optional inline style to apply to this column's header */
    var headerStyle: js.UndefOr[js.Object] = js.native

    /** Optional id to set on the column header */
    var id: js.UndefOr[String] = js.native

    /** Header label for this column */
    var label: js.UndefOr[ReactNode] = js.native

    /** Maximum width of column; this property will only be used if :flexGrow is > 0. */
    var maxWidth: js.UndefOr[JsNumber] = js.native

    /** Minimum width of column. */
    var minWidth: js.UndefOr[JsNumber] = js.native

    /** Optional inline style to apply to cell */
    var style: js.UndefOr[js.Object] = js.native

    /** Flex basis (width) for this column; This value can grow or shrink based on :flexGrow and :flexShrink properties. */
    var width: JsNumber = js.native
  }

  def props(
    width: Int,
    dataKey: String,
    ariaLabel: js.UndefOr[String] = js.undefined,
    cellDataGetter: Option[CellDataGetter] = None,
    cellRenderer: CellRenderer = defaultCellRendererS,
    className: js.UndefOr[String] = js.undefined,
    columnData: js.UndefOr[js.Object] = js.undefined,
    disableSort: js.UndefOr[Boolean] = js.undefined,
    defaultSortDirection: SortDirection = SortDirection.ASC,
    flexGrow: js.UndefOr[JsNumber] = js.undefined,
    flexShrink: js.UndefOr[JsNumber] = js.undefined,
    headerClassName: js.UndefOr[String] = js.undefined,
    headerRenderer: HeaderRenderer = defaultHeaderRendererS,
    headerStyle: js.UndefOr[Style] = js.undefined,
    id: js.UndefOr[String] = js.undefined,
    label: VdomNode = VdomNode.cast(()),
    maxWidth: js.UndefOr[JsNumber] = js.undefined,
    minWidth: js.UndefOr[JsNumber] = js.undefined,
    style: js.UndefOr[Style] = js.undefined,
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.width = width
    p.dataKey = dataKey
    p.`aria-label` = ariaLabel
    p.cellDataGetter = cellDataGetter.orUndefined
    p.cellRenderer = Some[RawCellRenderer]((r: raw.RawCellRendererParameter) => cellRenderer(r.cellData, r.columnData, r.dataKey, r.rowData, r.rowIndex).toRaw).orUndefined
    p.className = className
    p.columnData = columnData
    p.disableSort = disableSort
    p.defaultSortDirection = defaultSortDirection.toRaw
    p.flexGrow = flexGrow
    p.flexShrink = flexShrink
    p.headerClassName = headerClassName
    p.headerRenderer = (r: RawHeaderRendererParameter) => headerRenderer(r.columnData, r.dataKey, r.disableSort.toOption, VdomNode(r.label), r.sortBy.toOption, SortDirection.fromRaw(r.sortDirection.getOrElse(""))).toRaw
    p.headerStyle = headerStyle.map(Style.toJsObject)
    p.id = id
    p.label = label.rawNode
    p.maxWidth = maxWidth
    p.minWidth = minWidth
    p.style = style.map(Style.toJsObject)
    p
  }

  private val component = JsComponent[Props, Children.None, Null](RawComponent).addFacade[Column]

  def apply(p: Props): UnmountedMapped[Id, Props, Null, RawMounted with Column, Props, Null] = component(p)

}
