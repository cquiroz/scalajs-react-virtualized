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

  trait CellRendererParameter extends js.Object {
    var cellData: js.Any
    var columnData: js.Any
    var dataKey: String
    var rowData: js.Any
    var rowIndex: Int
  }
  object CellRendererParameter {
    def apply(cellData: js.Any, columnData: js.Any, dataKey: String, rowData: js.Any, rowIndex: Int): CellRendererParameter = {
      val p = (new js.Object).asInstanceOf[CellRendererParameter]
      p.cellData = cellData
      p.columnData = columnData
      p.dataKey = dataKey
      p.rowData = rowData
      p.rowIndex = rowIndex
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

    type CellRenderer = js.Function1[CellRendererParameter, VdomNode]
    type HeaderRenderer = js.Function1[HeaderRendererParameter, VdomNode]
    type RawHeaderRowRenderer = js.Function1[RawHeaderRowRendererParameter, ReactNode]
    type HeaderRowRenderer = (String, Array[VdomNode], Style) => VdomNode
  }

  private[virtualized] object raw {
    @js.native
    @JSImport("react-virtualized", "SortDirection")
    private[virtualized]  object RawSortDirection extends js.Object {
      val ASC: String = js.native
      val DESC: String = js.native
    }

    type RawHeaderRenderer = js.Function1[HeaderRendererParameter, ReactNode]
    type RawCellRenderer = js.Function1[CellRendererParameter, ReactNode]

    @js.native
    @JSImport("react-virtualized", "defaultTableHeaderRenderer", JSImport.Default)
    object defaultHeaderRenderer extends js.Function1[HeaderRendererParameter, ReactNode] {
      def apply(i: HeaderRendererParameter): ReactNode = js.native
    }
    val defaultHeaderRendererS = defaultHeaderRenderer andThen VdomNode.apply

    @js.native
    @JSImport("react-virtualized", "defaultTableCellRenderer", JSImport.Default)
    object defaultCellRenderer extends js.Function1[CellRendererParameter, ReactNode] {
      def apply(i: CellRendererParameter): ReactNode = js.native
    }
    val defaultCellRendererS = defaultCellRenderer andThen VdomNode.apply

    @js.native
    @JSImport("react-virtualized", "defaultTableHeaderRowRenderer", JSImport.Default)
    object defaultHeaderRowRenderer extends js.Function1[RawHeaderRowRendererParameter, ReactNode] {
      def apply(i: RawHeaderRowRendererParameter): ReactNode = js.native
    }
    val defaultHeaderRowRendererS: defs.HeaderRowRenderer = (className: String, columns: Array[VdomNode], style: Style) => VdomNode(defaultHeaderRowRenderer(RawHeaderRowRendererParameter(className, columns.map(_.rawNode).toJSArray, Style.toJsObject(style))))
  }

}
