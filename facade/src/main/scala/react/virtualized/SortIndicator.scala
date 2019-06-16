package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.JsFn.Unmounted
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object SortIndicator {
  @js.native
  @JSImport("react-virtualized", "SortIndicator")
  object RawComp extends js.Object

  @js.native
  trait Props extends js.Object {
    var sortDirection: String = js.native
  }

  def props(
    sortDirection: SortDirection,
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.sortDirection = sortDirection.toRaw
    p
  }

  private val component = JsFnComponent[Props, Children.None](RawComp)
  def apply(p: SortDirection): Unmounted[Props] = component(props(p))
}
