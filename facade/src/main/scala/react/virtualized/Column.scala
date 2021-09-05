package react
package virtualized

import scala.scalajs.js
import js.annotation.JSImport
import js.JSConverters._
import japgolly.scalajs.react.JsComponent
import japgolly.scalajs.react.Children
import japgolly.scalajs.react.component.Js.RawMounted
import japgolly.scalajs.react.component.Js.UnmountedMapped
import japgolly.scalajs.react.util.Effect.Id
import japgolly.scalajs.react.facade.JsNumber
import japgolly.scalajs.react.facade.React
import japgolly.scalajs.react.util.DefaultEffects
import japgolly.scalajs.react.vdom.VdomNode
import react.common.style._
import raw._

trait Column extends js.Object

object Column {
  @js.native
  @JSImport("react-virtualized", "Column")
  object RawComponent extends js.Object

  @js.native
  trait Props extends js.Object {

    /** Optional aria-label value to set on the column header */
    var `aria-label`: js.UndefOr[String] = js.native

    /** Callback responsible for returning a cell's data, given its :dataKey ({ columnData: any,
      * dataKey: string, rowData: any }): any
      */
    var cellDataGetter: js.UndefOr[RawCellDataGetter] = js.native

    /** Callback responsible for rendering a cell's contents. ({ cellData: any, columnData: any,
      * dataKey: string, rowData: any, rowIndex: number }): node
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

    /** Optional callback responsible for rendering a column header contents. ({ columnData: object,
      * dataKey: string, disableSort: boolean, label: node, sortBy: string, sortDirection: string
      * }): PropTypes.node
      */
    var headerRenderer: RawHeaderRenderer = js.native

    /** Optional inline style to apply to this column's header */
    var headerStyle: js.UndefOr[js.Object] = js.native

    /** Optional id to set on the column header */
    var id: js.UndefOr[String] = js.native

    /** Header label for this column */
    var label: js.UndefOr[React.Node] = js.native

    /** Maximum width of column; this property will only be used if :flexGrow is > 0. */
    var maxWidth: js.UndefOr[JsNumber] = js.native

    /** Minimum width of column. */
    var minWidth: js.UndefOr[JsNumber] = js.native

    /** Optional inline style to apply to cell */
    var style: js.UndefOr[js.Object] = js.native

    /** Flex basis (width) for this column; This value can grow or shrink based on :flexGrow and
      * :flexShrink properties.
      */
    var width: JsNumber = js.native
  }

  /** A Cell data B Column data C Row data
    */
  def props[A <: js.Object, B <: js.Object, C <: js.Object](
    width:                JsNumber,
    dataKey:              String,
    ariaLabel:            js.UndefOr[String] = js.undefined,
    cellDataGetter:       Option[CellDataGetter[B, C, A]] = None,
    cellRenderer:         CellRenderer[A, B, C] = defaultCellRendererS,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    columnData:           js.UndefOr[B] = js.undefined,
    disableSort:          js.UndefOr[Boolean] = js.undefined,
    defaultSortDirection: SortDirection = SortDirection.ASC,
    flexGrow:             js.UndefOr[JsNumber] = js.undefined,
    flexShrink:           js.UndefOr[JsNumber] = js.undefined,
    headerClassName:      js.UndefOr[String] = js.undefined,
    headerRenderer:       HeaderRenderer[B] = defaultHeaderRendererS,
    headerStyle:          js.UndefOr[Style] = js.undefined,
    id:                   js.UndefOr[String] = js.undefined,
    label:                VdomNode = VdomNode.cast(()),
    maxWidth:             js.UndefOr[JsNumber] = js.undefined,
    minWidth:             js.UndefOr[JsNumber] = js.undefined,
    style:                js.UndefOr[Style] = js.undefined
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.width = width
    p.dataKey = dataKey
    p.`aria-label` = ariaLabel
    def rawCellDataGetter(cdg: CellDataGetter[B, C, A]): RawCellDataGetter =
      (cdp: RawCellDataParameter) =>
        cdg(cdp.columnData.asInstanceOf[B], cdp.dataKey, cdp.asInstanceOf[C])
    p.cellDataGetter = cellDataGetter.map(rawCellDataGetter).orUndefined
    p.cellRenderer = Some[RawCellRenderer]((r: raw.RawCellRendererParameter) =>
      cellRenderer(r.cellData.asInstanceOf[A],
                   r.columnData.asInstanceOf[B],
                   r.dataKey,
                   r.rowData.asInstanceOf[C],
                   r.rowIndex
      ).rawNode
    ).orUndefined
    p.className = (className, clazz).toJs
    p.columnData = columnData
    p.disableSort = disableSort
    p.defaultSortDirection = defaultSortDirection.toRaw
    p.flexGrow = flexGrow
    p.flexShrink = flexShrink
    p.headerClassName = headerClassName
    p.headerRenderer = (r: RawHeaderRendererParameter) =>
      headerRenderer(
        r.columnData.asInstanceOf[B],
        r.dataKey,
        r.disableSort.toOption,
        VdomNode(r.label),
        r.sortBy.toOption,
        SortDirection.fromRaw(r.sortDirection.getOrElse(""))
      ).rawNode
    p.headerStyle = headerStyle.map(Style.toJsObject)
    p.id = id
    p.label = label.rawNode
    p.maxWidth = maxWidth
    p.minWidth = minWidth
    p.style = style.map(Style.toJsObject)
    p
  }

  /** Create a column with flexGrow/flexShrink as 0 by default
    *
    * A Cell data B Column data C Row data
    */
  def propsNoFlex[A <: js.Object, B <: js.Object, C <: js.Object](
    width:                JsNumber,
    dataKey:              String,
    ariaLabel:            js.UndefOr[String] = js.undefined,
    cellDataGetter:       Option[CellDataGetter[B, C, A]] = None,
    cellRenderer:         CellRenderer[A, B, C] = defaultCellRendererS,
    className:            js.UndefOr[String] = js.undefined,
    clazz:                js.UndefOr[Css] = js.undefined,
    columnData:           js.UndefOr[B] = js.undefined,
    disableSort:          js.UndefOr[Boolean] = true,
    defaultSortDirection: SortDirection = SortDirection.ASC,
    flexGrow:             js.UndefOr[JsNumber] = 0,
    flexShrink:           js.UndefOr[JsNumber] = 0,
    headerClassName:      js.UndefOr[String] = js.undefined,
    headerRenderer:       HeaderRenderer[B] = defaultHeaderRendererS,
    headerStyle:          js.UndefOr[Style] = js.undefined,
    id:                   js.UndefOr[String] = js.undefined,
    label:                VdomNode = VdomNode.cast(()),
    maxWidth:             js.UndefOr[JsNumber] = js.undefined,
    minWidth:             js.UndefOr[JsNumber] = js.undefined,
    style:                js.UndefOr[Style] = js.undefined
  ): Props =
    props(
      width,
      dataKey,
      ariaLabel,
      cellDataGetter,
      cellRenderer,
      className,
      clazz,
      columnData,
      disableSort,
      defaultSortDirection,
      flexGrow,
      flexShrink,
      headerClassName,
      headerRenderer,
      headerStyle,
      id,
      label,
      maxWidth,
      minWidth,
      style
    )

  private val component =
    JsComponent[Props, Children.None, Null](RawComponent).addFacade[Column]

  def apply(
    p: Props
  ): UnmountedMapped[Id, DefaultEffects.Async, Props, Null, RawMounted[Props, Null] with Column, Props, Null] =
    component(p)

}
