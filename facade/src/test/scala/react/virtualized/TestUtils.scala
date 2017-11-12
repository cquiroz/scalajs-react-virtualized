package react
package virtualized

import japgolly.scalajs.react.ReactDOMServer
import org.scalatest._
import scala.scalajs.js
import cats.Eq
import cats.syntax.eq._
import japgolly.scalajs.react.vdom.html_<^._
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

  def assertRender(e: ReactElement, expected: String): Assertion = {
    val rendered: String = ReactDOMServer.raw.renderToStaticMarkup(e)
    rendered should be(expected.trim.replaceAll("\n", ""))
  }

  def assertRender(e: ReactNode, expected: String): Assertion = {
    assertRenderNode(Some(e), expected)
  }

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

  def assertOuterHTML(node: Element, expect: String): Assertion =
    scrubReactHtml(node.outerHTML) should be(expect)

  private val reactRubbish =
  """\s+data-react\S*?\s*?=\s*?".*?"|<!--(?:.|[\r\n])*?-->""".r

  def scrubReactHtml(html: String): String =
    reactRubbish.replaceAllIn(html, "")
}
