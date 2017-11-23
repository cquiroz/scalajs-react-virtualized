package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react._

class AutoSizerSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {

  "AutoSizer" should
    "require width and dataKey" in {
      Column(Column.props(200, "key")).props.width should be (200)
      Column(Column.props(200, "key")).props.dataKey should be ("key")
    }
    it should "support disableWidth" in {
      AutoSizer(AutoSizer.props()).props.disableWidth.toOption should contain(false)
      AutoSizer(AutoSizer.props(disableWidth = true)).props.disableWidth.toOption should contain(true)
    }
    it should "support disableHeight" in {
      AutoSizer(AutoSizer.props()).props.disableHeight.toOption should contain(false)
      AutoSizer(AutoSizer.props(disableHeight = true)).props.disableHeight.toOption should contain(true)
    }
    it should "support onResize" in {
      val size = Size(height = 10, width = 20)
      AutoSizer(AutoSizer.props(onResize = x => Callback.log(x))).props.onResize(size) should be(())
    }
}
