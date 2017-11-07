package react.virutalized.demo
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document

object TableDemo {

  val component = ScalaComponent.builder[Unit]("TableDemo")
    .render_P(name => <.div("Hello there "))
    .build

  def apply() = component()
}
object Demo {
  def main(args: Array[String]): Unit = {
    TableDemo().renderIntoDOM(document.getElementById("root"))
    println("dem")
  }
}
