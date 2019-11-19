package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomElement
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import react.common.ReactProps

final case class SortIndicator(
  sortDirection: SortDirection
) extends ReactProps {
  @inline def render: VdomElement =
    SortIndicator.component(SortIndicator.props(this.sortDirection))
}

object SortIndicator {
  @js.native
  @JSImport("react-virtualized", "SortIndicator")
  object RawComp extends js.Object

  @js.native
  trait Props extends js.Object {
    var sortDirection: String = js.native
  }

  def props(
    sortDirection: SortDirection
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.sortDirection = sortDirection.toRaw
    p
  }

  private def component = JsFnComponent[Props, Children.None](RawComp)
}
