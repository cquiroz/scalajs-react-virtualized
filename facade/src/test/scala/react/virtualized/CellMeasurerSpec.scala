package react
package virtualized

import org.scalatest._
import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}

class CellMeasurerSpec extends FlatSpec with Matchers with NonImplicitAssertions with TestUtils {
  "CellMeasurerCache" should
    "be buildable" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache should not be(null)
    }

  "CellMeasurer" should
    "be buildable" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      val cellMeasurer = CellMeasurer(CellMeasurer.props(cellMeasurerCache, CellMeasurer.Parent.Zero), <<.div())
      cellMeasurer should not be(null)
    }

}
