package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import scala.scalajs.js
import scala.scalajs.js.|
import cats.syntax.eq._
import utest._

object AutoSizerTests extends TestSuite with TestUtils {
  def children(s: Size): VdomNode =
    <<.div(
      ^.width := s.width.px,
      ^.height := s.height.px,
      <<.span("text")
    )

  val tests = Tests {
    'render - {
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
    'supportDisableWidth - {
      AutoSizer(AutoSizer.props(children = children)).props.disableWidth.toOption ==> Some(false)
      AutoSizer(AutoSizer.props(children = children, disableWidth = true)).props.disableWidth.toOption ==> Some(true)
    }
    'supportDisableHeight - {
      AutoSizer(AutoSizer.props(children = children)).props.disableHeight.toOption ==> Some(false)
      AutoSizer(AutoSizer.props(children = children, disableHeight = true)).props.disableHeight.toOption ==> Some(true)
    }
    'supportOnResize - {
      val size = Size(height = 10, width = 20)
      AutoSizer(AutoSizer.props(children = children, onResize = x => Callback.empty)).props.onResize(size) ==> (())
    }
    'supportClassName - {
      AutoSizer(AutoSizer.props(children = children)).props.className ==> (())
      AutoSizer(AutoSizer.props(children = children, className = "classname")).props.className ==> "classname"
    }
    'supportDefaultHeightWidth - {
      AutoSizer(AutoSizer.props(children = children)).props.defaultHeight ==> (())
      AutoSizer(AutoSizer.props(children = children, defaultHeight = 43.1)).props.defaultHeight ==> 43.1
      AutoSizer(AutoSizer.props(children = children, defaultHeight = 43)).props.defaultHeight ==> 43

      AutoSizer(AutoSizer.props(children = children)).props.defaultWidth ==> (())
      AutoSizer(AutoSizer.props(children = children, defaultWidth = 43.1)).props.defaultWidth ==> 43.1
      AutoSizer(AutoSizer.props(children = children, defaultWidth = 43)).props.defaultWidth ==> 43
    }
    'supportStyle - {
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
      AutoSizer(AutoSizer.props(children = children)).props.style === new js.Object() ==> true
      AutoSizer(AutoSizer.props(children = children, style = Style(styleMap))).props.style === style ==> true
    }
  }
}
