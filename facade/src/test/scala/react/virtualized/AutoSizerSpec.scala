package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import scala.scalajs.js
import scala.scalajs.js.|
import cats.syntax.eq._

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
      AutoSizer(AutoSizer.props(children = children, onResize = x => Callback.empty)).props.onResize(size) should be(())
    }
    it should "support className" in {
      AutoSizer(AutoSizer.props(children = children)).props.className should be(())
      AutoSizer(AutoSizer.props(children = children, className = "classname")).props.className should be("classname")
    }
    it should "support defaultHeight/defaultWidth" in {
      AutoSizer(AutoSizer.props(children = children)).props.defaultHeight should be(())
      AutoSizer(AutoSizer.props(children = children, defaultHeight = 43.1)).props.defaultHeight should be(43.1)
      AutoSizer(AutoSizer.props(children = children, defaultHeight = 43)).props.defaultHeight should be(43)

      AutoSizer(AutoSizer.props(children = children)).props.defaultWidth should be(())
      AutoSizer(AutoSizer.props(children = children, defaultWidth = 43.1)).props.defaultWidth should be(43.1)
      AutoSizer(AutoSizer.props(children = children, defaultWidth = 43)).props.defaultWidth should be(43)
    }
    it should "support style" in {
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      AutoSizer(AutoSizer.props(children = children)).props.style === new js.Object() should be(true)
      AutoSizer(AutoSizer.props(children = children, style = Style(styleMap))).props.style === style should be(true)
    }
}
