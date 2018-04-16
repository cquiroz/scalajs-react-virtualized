package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.raw.JsNumber
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedWithRawType}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AutoSizer {
  @js.native
  @JSImport("react-virtualized", "AutoSizer")
  object RawComponent extends js.Object

  type OnResize = js.Function1[Size, Unit]
  type RawChildren = js.Function1[Size, React.Node]
  type Children = js.Function1[Size, VdomNode]

  @js.native
  trait Props extends js.Object {

    /** Function responsible for rendering children.*/
    var children: RawChildren = js.native

    /** Optional custom CSS class name to attach to root AutoSizer element.  */
    var className: js.UndefOr[String] = js.native

    /** Default height to use for initial render; useful for SSR */
    var defaultHeight: js.UndefOr[JsNumber] = js.native

    /** Default width to use for initial render; useful for SSR */
    var defaultWidth: js.UndefOr[JsNumber] = js.native

    /** Disable dynamic :height property */
    var disableHeight: js.UndefOr[Boolean] = js.native

    /** Disable dynamic :width property */
    var disableWidth: js.UndefOr[Boolean] = js.native

    /** Nonce of the inlined stylesheet for Content Security Policy */
    var nonce: js.UndefOr[String] = js.native

    /** Callback to be invoked on-resize */
    var onResize: OnResize = js.native

    /** Optional inline style */
    var style: js.Object = js.native
  }

  def props(
      children: Children,
      className: js.UndefOr[String] = js.undefined,
      defaultHeight: js.UndefOr[JsNumber] = js.undefined,
      defaultWidth: js.UndefOr[JsNumber] = js.undefined,
      disableHeight: js.UndefOr[Boolean] = js.undefined,
      disableWidth: js.UndefOr[Boolean] = js.undefined,
      nonce: js.UndefOr[String] = js.undefined,
      onResize: Size => Callback = _ => Callback.empty,
      style: js.UndefOr[Style] = js.undefined
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.className = className
    p.defaultHeight = defaultHeight
    p.defaultWidth = defaultWidth
    p.disableHeight = disableHeight
    p.disableWidth = disableWidth
    p.nonce = nonce
    p.onResize = (s: Size) => onResize(s).runNow
    p.children = (s: Size) => children(s).toRaw
    p.style = style.map(Style.toJsObject).getOrElse(new js.Object())
    p
  }

  private val component =
    JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(p: Props, children: VdomNode*)
    : UnmountedWithRawType[Props, Null, RawMounted[Props, Null]] =
    component(p)(children: _*)
}
