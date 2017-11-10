package react
package virtualized

import org.scalatest._
import Column._
import scala.scalajs.js
import js.JSConverters._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import cats.syntax.eq._
import japgolly.scalajs.react.raw.{ReactElement, ReactNode}
import japgolly.scalajs.react.vdom.{TagOf, TopNode}
import org.scalajs.dom.Element

trait TestUtils extends Matchers with NonImplicitAssertions { self: FlatSpec =>
  implicit def jsUndefOr[A: Eq]: Eq[js.UndefOr[A]] = Eq.instance { (a, b) =>
    (a.toOption, b.toOption) match {
      case (Some(a), Some(b)) => a === b
      case _                  => false
    }
  }

  implicit val jsObj: Eq[js.Object] = Eq.instance { (a, b) =>
    val aDict = a.asInstanceOf[js.Dictionary[Any]]
    val bDict = b.asInstanceOf[js.Dictionary[Any]]
    (aDict.keySet == bDict.keySet) &&
      aDict.keySet.forall(key => aDict(key) === bDict(key))
  }

  implicit val jsEq: Eq[Any] = Eq.instance { (a, b) =>
    (a, b) match {
      case (a: js.Array[_], b: js.Array[_]) =>
        a.length == b.length &&
          a.zip(b).forall{ x => jsEq.eqv(x._1, x._2) }

      case _ if a.asInstanceOf[js.Dynamic].constructor == js.constructorOf[js.Object] &&
        b.asInstanceOf[js.Dynamic].constructor == js.constructorOf[js.Object] =>
        val aDict = a.asInstanceOf[js.Dictionary[Any]]
        val bDict = b.asInstanceOf[js.Dictionary[Any]]
        (aDict.keySet == bDict.keySet) &&
          aDict.keySet.forall(key => aDict(key) === bDict(key))

      case _ =>
        a == b
    }
  }

  def assertRender(e: VdomElement, expected: String): Assertion =
    assertRender(e.rawElement, expected)

  def assertRenderNode[N <: TopNode](e: Option[ReactNode], expected: String): Assertion =
    e.map(x => HtmlTag("div").apply(VdomNode(x))) match {
      case Some(e) => assertRender(e.rawElement, expected)
      case _       => fail()
    }

  def assertRender[N <: TopNode](e: Option[TagOf[N]], expected: String): Assertion =
    e match {
      case Some(e) => assertRender(e.rawElement, expected)
      case _       => fail()
    }

  def assertRender(e: ReactElement, expected: String): Assertion = {
    val rendered: String = ReactDOMServer.raw.renderToStaticMarkup(e)
    rendered should be(expected.trim.replaceAll("\n", ""))
  }

  def assertOuterHTML(node: Element, expect: String): Assertion =
    scrubReactHtml(node.outerHTML) should be(expect)

  private val reactRubbish =
  """\s+data-react\S*?\s*?=\s*?".*?"|<!--(?:.|[\r\n])*?-->""".r

  def scrubReactHtml(html: String): String =
    reactRubbish.replaceAllIn(html, "")
}

class ColumnSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {

  "Column" should
    "require width and dataKey" in {
      Column(Column.props(200, "key")).props.width should be (200)
      Column(Column.props(200, "key")).props.dataKey should be ("key")
    }
    it should "support ariaLabel" in {
      Column(Column.props(200, "key")).props.`aria-label` should be(())
      Column(Column.props(200, "key", ariaLabel = "Label")).props.`aria-label` should be("Label")
    }
    it should "have a default cellDataGetter" in {
      val dataMap = Map("key" -> 1, "b" -> 2).toJSDictionary
      Column(Column.props(200, "key")).props.cellDataGetter.toOption.map(_(CellDataParameter("col", "key", dataMap))) should contain(1)
    }
    it should "support cellDataGetter" in {
      Column(Column.props(200, "key", cellDataGetter = Some((x: CellDataParameter) => "abc"))).props.cellDataGetter.toOption.map(_(CellDataParameter("col", "key", "row"))) should contain("abc")
    }
    it should "have a default cellRenderer" in {
      Column(Column.props(200, "key")).props.cellRenderer.toOption.map(_(CellRendererParameter("cellData", "col", "key", "row", 1))) should contain("cellData")
    }
    it should "support cellRenderer" in {
      val r = Column(Column.props(200, "key", cellRenderer = Some((x: CellRendererParameter) => <<.div("abc")))).props.cellRenderer.toOption.map(_(CellRendererParameter("cellData", "col", "key", "row", 1)))
      assertRenderNode(r, "<div><div>abc</div></div>")
    }
    it should "support className" in {
      Column(Column.props(200, "key")).props.className should be(())
      Column(Column.props(200, "key", className = "my-class")).props.className should be("my-class")
    }
    it should "support columnData" in {
      Column(Column.props(200, "key")).props.columnData should be(())
      val dataObject = js.Dynamic.literal(foo = 42, bar = "foobar")
      Column(Column.props(200, "key", columnData = dataObject)).props.columnData should be(dataObject)
    }
    it should "support disableSort" in {
      Column(Column.props(200, "key")).props.disableSort should be(())
      Column(Column.props(200, "key", disableSort = true)).props.disableSort.toOption should be(Some(true))
    }
    it should "support defaultSortDirection" in {
      Column(Column.props(200, "key")).props.defaultSortDirection should be(SortDirection.ASC.toRaw)
      Column(Column.props(200, "key", defaultSortDirection = SortDirection.DESC)).props.defaultSortDirection should be(SortDirection.DESC.toRaw)
    }
    it should "support flexGrow" in {
      Column(Column.props(200, "key")).props.flexGrow should be(0)
      Column(Column.props(200, "key", disableSort = true, flexGrow = 1)).props.flexGrow should be(1)
    }
    it should "support flexShrink" in {
      Column(Column.props(200, "key")).props.flexShrink should be(1)
      Column(Column.props(200, "key", disableSort = true, flexShrink = 0)).props.flexShrink should be(0)
    }
    it should "support headerClassName" in {
      Column(Column.props(200, "key")).props.headerClassName should be(())
      Column(Column.props(200, "key", headerClassName = "my-class")).props.headerClassName should be("my-class")
    }
    it should "have a default headerRenderer" in {
      val label = <<.div("Label")
      val headerParam = HeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
      val unmounted = Column(Column.props(200, "key")).props.headerRenderer(headerParam)
      assertRender(unmounted,
        """<div><span class="ReactVirtualized__Table__headerTruncatedText" title="[object Object]"><div>Label</div></span><svg class="ReactVirtualized__Table__sortableHeaderIcon ReactVirtualized__Table__sortableHeaderIcon--ASC" width="18" height="18" viewBox="0 0 24 24"><path d="M7 14l5-5 5 5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>""")
    }
    it should "support headerRenderer" in {
      val label = <<.div("Label")
      val headerParam = HeaderRendererParameter(js.undefined, "key", disableSort = true, label, "key", "ASC")
      val unmounted = Column(Column.props(200, "key", headerRenderer = _ => <<.div("header"))).props.headerRenderer(headerParam)
      assertRender(unmounted, """<div><div>header</div></div>""")
    }
    it should "support id" in {
      Column(Column.props(200, "key")).props.id should be(())
      Column(Column.props(200, "key", id = "id")).props.id should be("id")
    }
    it should "support label" in {
      val label = <<.div("id")
      Column(Column.props(200, "key")).props.label should be(())
      Column(Column.props(200, "key", label = label)).props.label should be(label.rawNode)
    }
    it should "support maxWidth" in {
      Column(Column.props(200, "key")).props.maxWidth should be(())
      Column(Column.props(200, "key", maxWidth = 100)).props.maxWidth should be(100)
    }
    it should "support minWidth" in {
      Column(Column.props(200, "key")).props.minWidth should be(())
      Column(Column.props(200, "key", minWidth = 100)).props.minWidth should be(100)
    }
    it should "support style" in {
      val style = js.Dynamic.literal(foo = 42, bar = "foobar")
      Column(Column.props(200, "key")).props.style === Some(js.Object()).orUndefined should be(true)
      Column(Column.props(200, "key", style = style)).props.style should be(style)
    }
}
