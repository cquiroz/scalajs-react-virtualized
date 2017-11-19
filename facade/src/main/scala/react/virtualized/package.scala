package react

import scala.scalajs.js
import scala.scalajs.js.|
import js.JSConverters._

package virtualized {
  final case class Style(styles: Map[String, String | Int])

  object Style {
    def toJsObject(style: Style): js.Object = {
      style.styles.toJSDictionary.asInstanceOf[js.Object]
    }
  }

}
