package io.github.cquiroz.scalajs
package react
package virtualized

import japgolly.scalajs.react._
import scala.scalajs.js
import japgolly.scalajs.react.raw.{JsNumber, ReactNode}
import scala.scalajs.js.annotation.{JSImport, ScalaJSDefined}

object Column {

  @js.native
  @JSImport("react-virtualized", "Column")
  object RawComponent extends js.Object

  @ScalaJSDefined
  trait CellDataParameter extends js.Object {
    val columnData: js.Any
    val dataKey: String
    val rowData: js.Any
  }
  type CellDataGetter = js.Function1[CellDataParameter, Any]

  @ScalaJSDefined
  trait CellRendererParameter extends js.Object {
    val cellData: js.Any
    val columnData: js.Any
    val dataKey: String
    val rowData: js.Any
    val rowIndex: JsNumber
  }
  type CellRenderer = js.Function1[CellRendererParameter, ReactNode]

  @ScalaJSDefined
  trait HeaderRendererParameter extends js.Object {
    val columnData: js.Any
    val dataKey: String
    val disableSort: Boolean
    val label: ReactNode
    val sortBy: String
    val sortDirection: String
  }
  type HeaderRenderer = js.Function1[HeaderRendererParameter, ReactNode]

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
    var cellRenderer: js.UndefOr[CellRenderer] = js.native

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
    var headerRenderer: js.UndefOr[HeaderRenderer] = js.native

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

  def props(width: Int, dataKey: String): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.width = width
    p.dataKey = dataKey
    p
  }

  val component = JsComponent[Props, Children.None, Null](RawComponent)

  def apply(p: Props) = component(p)

}
