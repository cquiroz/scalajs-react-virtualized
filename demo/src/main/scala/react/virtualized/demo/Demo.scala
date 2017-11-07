package react.virutalized.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import io.github.cquiroz.scalajs.react.virtualized._
import io.github.cquiroz.scalajs.react.virtualized.Table._

object TableDemo {
  val columns = List(Column(Column.props(200, "key")))
  val rowGetterF = (x: IndexParameter) => x.index
  val table = Table(Table.props(height = 200, rowCount = 2, rowHeight = 40, width = 500, rowGetter = rowGetterF), columns: _*)

  val component = ScalaComponent.builder[Unit]("TableDemo")
    .render_P(_ => table)
    .build

  def apply() = component()
}
object Demo {
  def main(args: Array[String]): Unit = {
    TableDemo().renderIntoDOM(document.getElementById("root"))
    println("dem")
  }
}
