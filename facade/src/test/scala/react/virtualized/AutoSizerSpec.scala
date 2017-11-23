package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}

class AutoSizerSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {
  def children(s: Size): VdomNode =
    <<.div(
      ^.width := s.width.px,
      ^.height := s.height.px,
      <<.span("text")
    )

  "AutoSizer" should
    "render" in {
      val autoSizer = AutoSizer(AutoSizer.props(children = children))
      ReactTestUtils.withRenderedIntoDocument(autoSizer) { m =>
        val html =
          """<div style="overflow: visible; height: 0px; width: 0px;">
              |<div style="width: 0px; height: 0px;">
                |<span>text</span>
              |</div>
              |</div>""".stripMargin.replaceAll("[\n\r]", "")
        assert(m.outerHtmlScrubbed() == html)
      }
    }
    it should "support disableWidth" in {
      AutoSizer(AutoSizer.props(children = children)).props.disableWidth.toOption should contain(false)
      AutoSizer(AutoSizer.props(children = children, disableWidth = true)).props.disableWidth.toOption should contain(true)
    }
    it should "support disableHeight" in {
      AutoSizer(AutoSizer.props(children = children)).props.disableHeight.toOption should contain(false)
      AutoSizer(AutoSizer.props(children = children, disableHeight = true)).props.disableHeight.toOption should contain(true)
    }
    it should "support onResize" in {
      val size = Size(height = 10, width = 20)
      AutoSizer(AutoSizer.props(children = children, onResize = x => Callback.log(x))).props.onResize(size) should be(())
    }
}
