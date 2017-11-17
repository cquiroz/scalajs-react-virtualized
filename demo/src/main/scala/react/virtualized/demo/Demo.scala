package react.virutalized.demo

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.document
import react.virtualized._
import react.virtualized.Column._

object TableDemo {
  val data = Data.generateRandomList

  def datum(i: Int) = data(i % data.length)
  def rowheight(i: Int) = datum(i).size

  final case class Props(useDynamicRowHeight: Boolean, sortBy: String)

  def headerRenderer(sortBy: String)(p: HeaderRendererParameter): VdomNode =
    <.div("Full Name", SortIndicator(SortDirection.ASC).when(sortBy == p.dataKey))

  def rowClassName(i: Int): String = i match {
    case x if x < 0      => "headerRow"
    case x if x % 2 == 0 => "evenRow"
    case _               => "oddRow"
  }
  def table(props: Props) = {
    val columns = List(
      Column(Column.props(60, "index", label = "Index", disableSort = false)),
      Column(Column.props(90, "name", disableSort = false, headerRenderer = headerRenderer(props.sortBy))),
      Column(Column.props(210, "random", disableSort = true, className = "exampleColumn", label = "The description label is so long it will be truncated", flexGrow = 1, cellRenderer = c => c.cellData.toString))
    )
    Table(
      Table.props(
        disableHeader = false,
        noRowsRenderer = () => <.div(^.cls := "noRows", "No rows"),
        overscanRowCount = 10,
        rowClassName = rowClassName _,
        height = 270,
        rowCount = 1000,
        rowHeight = if (props.useDynamicRowHeight) rowheight _ else 40,
        width = 500,
        rowGetter = datum,
        headerClassName = "headerColumn",
        sortBy = props.sortBy,
        headerHeight = 30), columns: _*)
  }

  val component = ScalaComponent.builder[Props]("TableDemo")
    .render_P(table)
    .build

  def apply(p: Props) = component(p)
}
object Demo {
  def main(args: Array[String]): Unit = {
    TableDemo(TableDemo.Props(true, "index")).renderIntoDOM(document.getElementById("root"))
    println("dem")
  }
}
