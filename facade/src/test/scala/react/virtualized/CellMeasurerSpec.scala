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
    it should "support clear" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.clear(0, 0) should be(())
    }
    it should "support clearAll" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.clearAll() should be(())
    }
    it should "support columnWidth" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.columnWidth(raw.RawIndexParameter(0)) should be(100)
    }
    it should "support getHeight" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.getHeight(0, 0) should be(30)
    }
    it should "support getWidth" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.getWidth(0, 0) should be(100)
    }
    it should "support has" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.has(0, 0) should be(false)
    }
    it should "support rowHeight" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.rowHeight(raw.RawIndexParameter(0)) should be(30)
    }
    it should "support set" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.set(0, 0, 20, 30) should be(())
    }

  "CellMeasurer" should
    "be buildable" in {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      val cellMeasurer = CellMeasurer(CellMeasurer.props(cellMeasurerCache, CellMeasurer.Parent.Zero), <<.div())
      cellMeasurer should not be(null)
    }

}
