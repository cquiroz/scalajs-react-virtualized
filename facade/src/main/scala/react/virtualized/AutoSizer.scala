package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.raw.ReactNode
import japgolly.scalajs.react.component.Js.{RawMounted, UnmountedMapped}
import japgolly.scalajs.react.internal.Effect.Id

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object AutoSizer {
  @js.native
  @JSImport("react-virtualized", "AutoSizer")
  object RawComponent extends js.Object

  type OnResize = js.Function1[Size, Unit]
  type RawChildren = js.Function1[Size, ReactNode]
  type Children = js.Function1[Size, VdomNode]

  @js.native
  trait Props extends js.Object {
    /** Function responsible for rendering children.*/
    var children: RawChildren = js.native

    /** Disable dynamic :height property */
    var disableHeight: js.UndefOr[Boolean] = js.native

    /** Disable dynamic :width property */
    var disableWidth: js.UndefOr[Boolean] = js.native

    /** Nonce of the inlined stylesheet for Content Security Policy */
    var nonce: js.UndefOr[String] = js.native

    /** Callback to be invoked on-resize */
    var onResize: OnResize = js.native
  }

  def props(
    children: Children,
    disableHeight: js.UndefOr[Boolean] = js.undefined,
    disableWidth: js.UndefOr[Boolean] = js.undefined,
    nonce: js.UndefOr[String] = js.undefined,
    onResize: Size => Callback = _ => Callback.empty,
  ): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.disableHeight = disableHeight
    p.disableWidth = disableWidth
    p.nonce = nonce
    p.onResize = (s: Size) => onResize(s).runNow
    p.children = (s: Size) => children(s).toRaw
    p
  }

  private val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(p: Props, children: VdomNode*): UnmountedMapped[Id, Props, Null, RawMounted, Props, Null] = component(p)(children: _*)
}
