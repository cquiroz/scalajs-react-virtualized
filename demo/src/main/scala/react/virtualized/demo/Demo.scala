package react.virutalized.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import io.github.cquiroz.scalajs.react.virtualized._
import io.github.cquiroz.scalajs.react.virtualized.Table._
import io.github.cquiroz.scalajs.react.virtualized.Column._

object TableDemo {
  def headerRenderer(p: HeaderRendererParameter): VdomNode =
    <.div("Full Name")

  val columns = List(
    Column(Column.props(60, "index", label = "Index", disableSort = false)),
    Column(Column.props(90, "name", disableSort = false, headerRenderer = headerRenderer))
  )
                // <Column
                //   dataKey="name"
                //   disableSort={!this._isSortEnabled()}
                //   headerRenderer={this._headerRenderer}
                  //   width={90}
                // />
                //   <Column
                //     label="Index"
                //     cellDataGetter={({ rowData }) => rowData.index}
                //     dataKey="index"
                //     disableSort={!this._isSortEnabled()}
                //     width={60}
                //   />}
                // <Column
  val rowGetterF = (x: IndexParameter) => x.index
  val table = Table(Table.props(height = 200, rowCount = 2, rowHeight = 40, width = 500, rowGetter = rowGetterF, headerClassName = "headerColumn", headerHeight = 30), columns: _*)

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
