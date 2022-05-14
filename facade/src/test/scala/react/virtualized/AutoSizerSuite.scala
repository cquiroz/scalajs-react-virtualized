package react
package virtualized

import japgolly.scalajs.react._
import japgolly.scalajs.react.test._
import japgolly.scalajs.react.facade._
import japgolly.scalajs.react.vdom.html_<^.{ < => <<, _ }
import react.common._
import react.common.implicits._
import scala.scalajs.js
import scala.scalajs.js.|
import cats.syntax.eq._

class AutoSizerSuite extends munit.FunSuite with TestUtils {
  def children(s: Size): VdomNode =
    <<.div(
      ^.width := s.width.toDouble.px,
      ^.height := s.height.toDouble.px,
      <<.span("text")
    )

  test("render") {
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
  test("supportDisableWidth") {
    assertEquals(AutoSizer(AutoSizer.props(children = children)).props.disableWidth.toOption,
                 Some(false)
    )
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, disableWidth = true)
                 ).props.disableWidth.toOption,
                 Some(true)
    )
  }
  test("clazz") {
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, clazz = Css("class"))
                 ).props.className.toOption,
                 Some("class")
    )
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, className = "class1", clazz = Css("class2"))
                 ).props.className.toOption,
                 Some("class1 class2")
    )
  }
  test("supportDisableHeight") {
    assertEquals(AutoSizer(AutoSizer.props(children = children)).props.disableHeight.toOption,
                 Some(false)
    )
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, disableHeight = true)
                 ).props.disableHeight.toOption,
                 Some(true)
    )
  }
  test("supportOnResize") {
    val size = Size(height = 10, width = 20)
    assertEquals(
      AutoSizer(AutoSizer.props(children = children, onResize = x => Callback.empty)).props
        .onResize(size),
      ()
    )
  }
  test("supportClassName") {
    assertEquals(AutoSizer(AutoSizer.props(children = children)).props.className, js.undefined)
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, className = "classname")
                 ).props.className,
                 "classname": js.UndefOr[String]
    )
  }
  test("supportDefaultHeightWidth") {
    assertEquals(AutoSizer(AutoSizer.props(children = children)).props.defaultHeight, js.undefined)
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, defaultHeight = 43.1)
                 ).props.defaultHeight,
                 43.1: js.UndefOr[JsNumber]
    )
    assertEquals(
      AutoSizer(AutoSizer.props(children = children, defaultHeight = 43)).props.defaultHeight,
      43: js.UndefOr[JsNumber]
    )

    assertEquals(AutoSizer(AutoSizer.props(children = children)).props.defaultWidth, js.undefined)
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, defaultWidth = 43.1)
                 ).props.defaultWidth,
                 43.1: js.UndefOr[JsNumber]
    )
    assertEquals(
      AutoSizer(AutoSizer.props(children = children, defaultWidth = 43)).props.defaultWidth,
      43: js.UndefOr[JsNumber]
    )
  }
  test("supportStyle") {
    val style    = js.Dynamic.literal(foo = 42, bar = "foobar")
    val styleMap = Map[String, String | Int]("foo" -> 42, "bar" -> "foobar")
    assertEquals(AutoSizer(AutoSizer.props(children = children)).props.style === new js.Object,
                 true
    )
    assertEquals(AutoSizer(
                   AutoSizer.props(children = children, style = Style(styleMap))
                 ).props.style === style,
                 true
    )
  }
}
