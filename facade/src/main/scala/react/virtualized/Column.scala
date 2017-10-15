package io.github.cquiroz.scalajs
package react
package virtualized

import japgolly.scalajs.react._
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Column {

  @js.native
  @JSImport("react-virtualized", "Column")
  object RawComponent extends js.Object

  /*trait RowIndex extends js.Object {
    val index: Int
  }
  type RowGetter = js.Function1[RowIndex, Any]
  */

  @js.native
  trait Props extends js.Object {
    var width: Int = js.native
    var dataKey: String = js.native
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
