package io.github.cquiroz.scalajs
package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedWithRawType}

import scala.scalajs.js
import js.JSConverters._
import japgolly.scalajs.react.raw.{JsNumber, ReactNode}
import japgolly.scalajs.react.vdom.VdomNode

import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

object Column {

  @js.native
  @JSImport("react-virtualized", "Column")
  object RawComponent extends js.Object

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
  type RawHeaderRenderer = js.Function1[HeaderRendererParameter, ReactNode]

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
    var headerRenderer: js.UndefOr[RawHeaderRenderer] = js.native

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

  private def toRawNode(vdomNode: VdomNode): ReactNode = vdomNode.rawNode

  def props(
    width: Int,
    dataKey: String,
    ariaLabel: js.UndefOr[String] = js.undefined,
    cellDataGetter: Option[CellDataGetter] = None,
    cellRenderer: Option[CellRenderer] = None,
    className: js.UndefOr[String] = js.undefined,
    columnData: js.UndefOr[js.Object] = js.undefined,
    disableSort: js.UndefOr[Boolean] = js.undefined,
    flexGrow: js.UndefOr[JsNumber] = js.undefined,
    flexShrink: js.UndefOr[JsNumber] = js.undefined,
    headerClassName: js.UndefOr[String] = js.undefined,
    headerRenderer: Option[HeaderRenderer] = None,
    id: js.UndefOr[String] = js.undefined,
    label: VdomNode = VdomNode.cast(()),
    maxWidth: js.UndefOr[JsNumber] = js.undefined,
    minWidth: js.UndefOr[JsNumber] = js.undefined,
    style: js.UndefOr[js.Object] = js.undefined,
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.width = width
    p.dataKey = dataKey
    p.`aria-label` = ariaLabel
    p.cellDataGetter = cellDataGetter.orUndefined
    p.cellRenderer = cellRenderer.map(_.andThen(toRawNode): RawCellRenderer).orUndefined
    p.className = className
    p.columnData = columnData
    p.disableSort = disableSort
    p.flexGrow = flexGrow
    p.flexShrink = flexShrink
    p.headerClassName = headerClassName
    p.headerRenderer = headerRenderer.map(f => f.andThen(toRawNode): RawHeaderRenderer).orUndefined
    p.id = id
    p.label = label.rawNode
    p.maxWidth = maxWidth
    p.minWidth = minWidth
    p.style = style
    p
  }

  private val component = JsComponent[Props, Children.None, Null](RawComponent)

  def apply(p: Props): UnmountedWithRawType[Props, Null, RawMounted] = component(p)

}
