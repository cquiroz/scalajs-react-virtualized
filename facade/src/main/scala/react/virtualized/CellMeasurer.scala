package react

package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedMapped}
import japgolly.scalajs.react.internal.Effect.Id
import scala.scalajs.js
import js.annotation.JSImport
import japgolly.scalajs.react.raw.JsNumber

trait CellMeasurerCacheParams extends js.Object {
  var defaultHeight: js.UndefOr[JsNumber]
  var defaultWidth: js.UndefOr[JsNumber]
  var fixedHeight: js.UndefOr[Boolean]
  var fixedWidth: js.UndefOr[Boolean]
  var minHeight: js.UndefOr[JsNumber]
  var minWidth: js.UndefOr[JsNumber]
}

sealed trait FixedDirection
case object FixedWidth  extends FixedDirection
case object FixedHeight extends FixedDirection

object CellMeasurerCacheParams {

  def apply(fixedDirection: FixedDirection,
            defaultHeight: js.UndefOr[JsNumber] = js.undefined,
            defaultWidth: js.UndefOr[JsNumber] = js.undefined,
            fixedWidth: js.UndefOr[Boolean] = js.undefined,
            minHeight: js.UndefOr[JsNumber] = js.undefined,
            minWidth: js.UndefOr[JsNumber] = js.undefined): CellMeasurerCacheParams =
    rawapply(defaultHeight,
             defaultWidth,
             fixedDirection == FixedHeight,
             fixedDirection == FixedWidth,
             minHeight,
             minWidth)

  private def rawapply(defaultHeight: js.UndefOr[JsNumber],
                       defaultWidth: js.UndefOr[JsNumber],
                       fixedHeight: js.UndefOr[Boolean],
                       fixedWidth: js.UndefOr[Boolean],
                       minHeight: js.UndefOr[JsNumber],
                       minWidth: js.UndefOr[JsNumber]): CellMeasurerCacheParams = {
    val p = (new js.Object).asInstanceOf[CellMeasurerCacheParams]
    p.defaultHeight = defaultHeight
    p.defaultWidth = defaultWidth
    p.fixedHeight = fixedHeight
    p.fixedWidth = fixedWidth
    p.minHeight = minHeight
    p.minWidth = minWidth
    p
  }
}

@js.native
@JSImport("react-virtualized", "CellMeasurerCache")
class CellMeasurerCache(params: CellMeasurerCacheParams) extends js.Object {
  def clear(rowIndex: Int, columnIndex: Int = 0): Unit = js.native
  def clearAll(): Unit = js.native
  def columnWidth: js.Function1[raw.RawIndexParameter, Int] = js.native
  def getHeight(rowIndex: Int, columnIndex: Int = 0): Int = js.native
  def getWidth(rowIndex: Int, columnIndex: Int = 0): Int = js.native
  def has(rowIndex: Int, columnIndex: Int = 0): Boolean = js.native
  def rowHeight: js.Function1[raw.RawIndexParameter, Int] = js.native
  def set(rowIndex: Int, columnIndex: Int, width: Int, height: Int): Unit = js.native
}

object CellMeasurerCache {
  def apply(fixedDirection: FixedDirection,
            defaultHeight: js.UndefOr[JsNumber] = js.undefined,
            defaultWidth: js.UndefOr[JsNumber] = js.undefined,
            fixedWidth: js.UndefOr[Boolean] = js.undefined,
            minHeight: js.UndefOr[JsNumber] = js.undefined,
            minWidth: js.UndefOr[JsNumber] = js.undefined): CellMeasurerCache =
    new CellMeasurerCache(CellMeasurerCacheParams(fixedDirection, defaultHeight, defaultWidth, fixedWidth, minHeight, minWidth))
}

object CellMeasurer {

  @js.native
  @JSImport("react-virtualized", "CellMeasurer")
  object RawComponent extends js.Object

  @js.native
  trait Cell extends js.Object {
    var columnIndex: JsNumber
    var rowIndex: JsNumber
  }

  object Cell {
    def apply(columnIndex: JsNumber, rowIndex: JsNumber): Cell = {
      val p = (new js.Object).asInstanceOf[Cell]
      p.columnIndex = columnIndex
      p.rowIndex = rowIndex
      p
    }
  }

  type ParentFn = Cell => Unit

  @js.native
  trait Parent extends js.Object {
    var invalidateCellSizeAfterRender: js.UndefOr[ParentFn]
    var recomputeGridSize: js.UndefOr[ParentFn]
  }

  object Parent {

    def apply(invalidateCellSizeAfterRender: js.UndefOr[ParentFn] = js.undefined,
              recomputeGridSize: js.UndefOr[ParentFn] = js.undefined): Parent = {
      val p = (new js.Object).asInstanceOf[Parent]
      p.invalidateCellSizeAfterRender = invalidateCellSizeAfterRender
      p.recomputeGridSize = recomputeGridSize
      p
    }

    val Zero: Parent = apply()
  }

  @js.native
  trait Props extends js.Object {
    var cache: CellMeasurerCache
    var columnIndex: js.UndefOr[JsNumber]
    var index: js.UndefOr[JsNumber]
    var parent: Parent
    var rowIndex: js.UndefOr[JsNumber]
  }

  def props(
      cache: CellMeasurerCache,
      parent: Parent,
      columnIndex: js.UndefOr[JsNumber] = js.undefined,
      index: js.UndefOr[JsNumber] = js.undefined,
      rowIndex: js.UndefOr[JsNumber] = js.undefined,
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.cache = cache
    p.columnIndex = columnIndex
    p.index = index
    p.parent = parent
    p.rowIndex = rowIndex
    p
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(p: Props, children: VdomNode*): UnmountedMapped[Id, Props, Null, RawMounted, Props, Null] =
    component.apply(p)(children: _*)

}
