package react

import scala.scalajs.js
import scala.scalajs.js.|
import js.annotation.JSImport
import js.JSConverters._
import japgolly.scalajs.react.raw.ReactNode
import japgolly.scalajs.react.vdom.VdomNode

package virtualized {

  final case class Style(styles: Map[String, String | Int])

  object Style {
    def toJsObject(style: Style): js.Object =
      style.styles.toJSDictionary.asInstanceOf[js.Object]

    def fromJsObject(o: js.Object): Style = {
      val xDict = o.asInstanceOf[js.Dictionary[String | Int]]
      val map = (for ((prop, value) <- xDict) yield
        prop -> value).toMap
      Style(map)
    }

  }

  @js.native
  trait Size extends js.Object {
    var height: Double = js.native
    var width: Double = js.native
  }
  object Size {
    def apply(height: Double, width: Double): Size = {
      val p = (new js.Object).asInstanceOf[Size]
      p.height = height
      p.width = width
      p
    }
  }

  trait RawHeaderRowRendererParameter extends js.Object {
    var className: String
    var columns: js.Array[ReactNode]
    var style: js.Object
  }
  object RawHeaderRowRendererParameter {
    def apply(className: String, columns: js.Array[ReactNode], style: js.Object): RawHeaderRowRendererParameter = {
      val p = (new js.Object).asInstanceOf[RawHeaderRowRendererParameter]
      p.className = className
      p.columns = columns
      p.style = style
      p
    }
  }

  object defs {
    implicit class VdomToRaw(val node: VdomNode) extends AnyVal {
      def toRaw: ReactNode = node.rawNode
    }

    type CellRenderer = (js.Any, js.Any, String, js.Any, Int) => VdomNode
    type HeaderRenderer = (js.Any, String, Option[Boolean], VdomNode, Option[String], SortDirection) => VdomNode

    type RawHeaderRowRenderer = js.Function1[RawHeaderRowRendererParameter, ReactNode]
    type HeaderRowRenderer = (String, Array[VdomNode], Style) => VdomNode
  }

  private[virtualized] object raw {
    trait RawCellRendererParameter extends js.Object {
      var cellData: js.Any
      var columnData: js.Any
      var dataKey: String
      var rowData: js.Any
      var rowIndex: Int
    }
    object RawCellRendererParameter {
      def apply(cellData: js.Any, columnData: js.Any, dataKey: String, rowData: js.Any, rowIndex: Int): RawCellRendererParameter = {
        val p = (new js.Object).asInstanceOf[RawCellRendererParameter]
        p.cellData = cellData
        p.columnData = columnData
        p.dataKey = dataKey
        p.rowData = rowData
        p.rowIndex = rowIndex
        p
      }
    }

    trait RawHeaderRendererParameter extends js.Object {
      var columnData: js.Any
      var dataKey: String
      var disableSort: js.UndefOr[Boolean]
      var label: ReactNode
      var sortBy: js.UndefOr[String]
      var sortDirection: js.UndefOr[String]
    }
    object RawHeaderRendererParameter {
      def apply(columnData: js.Any, dataKey: String, disableSort: js.UndefOr[Boolean], label: VdomNode, sortBy: js.UndefOr[String], sortDirection: js.UndefOr[String]): RawHeaderRendererParameter = {
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

    @js.native
    @JSImport("react-virtualized", "SortDirection")
    private[virtualized]  object RawSortDirection extends js.Object {
      val ASC: String = js.native
      val DESC: String = js.native
    }

    type RawHeaderRenderer = js.Function1[RawHeaderRendererParameter, ReactNode]
    type RawCellRenderer = js.Function1[RawCellRendererParameter, ReactNode]

    @js.native
    @JSImport("react-virtualized", "defaultTableHeaderRenderer", JSImport.Default)
    object defaultHeaderRenderer extends js.Function1[RawHeaderRendererParameter, ReactNode] {
      def apply(i: RawHeaderRendererParameter): ReactNode = js.native
    }
    val defaultHeaderRendererS = (columnData: js.Any, dataKey: String, disableSort: Option[Boolean], label: VdomNode, sortBy: Option[String], sortDirection: SortDirection) => VdomNode(defaultHeaderRenderer(RawHeaderRendererParameter(columnData, dataKey, disableSort.orUndefined, label, sortBy.orUndefined, sortDirection.toRaw)))

    @js.native
    @JSImport("react-virtualized", "defaultTableCellRenderer", JSImport.Default)
    object defaultCellRenderer extends js.Function1[RawCellRendererParameter, ReactNode] {
      def apply(i: RawCellRendererParameter): ReactNode = js.native
    }
    val defaultCellRendererS = (cellData: js.Any, columnData: js.Any, dataKey: String, rowData: js.Any, rowIndex: Int) => VdomNode(defaultCellRenderer(RawCellRendererParameter(cellData, columnData, dataKey, rowData, rowIndex)))


    @js.native
    @JSImport("react-virtualized", "defaultTableHeaderRowRenderer", JSImport.Default)
    object defaultHeaderRowRenderer extends js.Function1[RawHeaderRowRendererParameter, ReactNode] {
      def apply(i: RawHeaderRowRendererParameter): ReactNode = js.native
    }
    val defaultHeaderRowRendererS: defs.HeaderRowRenderer = (className: String, columns: Array[VdomNode], style: Style) => VdomNode(defaultHeaderRowRenderer(RawHeaderRowRendererParameter(className, columns.map(_.rawNode).toJSArray, Style.toJsObject(style))))
  }

}
