package react
package virtualized

import react.common.TestUtils
import utest._

class CellMeasurerTest extends TestSuite with TestUtils {
  val tests = Tests {
    "be buildable" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      assert(cellMeasurerCache != null)
    }
    "support clear" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.clear(0, 0) ==> (())
    }
    "support clearAll" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.clearAll() ==> (())
    }
    "support columnWidth" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.columnWidth(raw.RawIndexParameter(0)) ==> 100
    }
    "support getHeight" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.getHeight(0, 0) ==> 30
    }
    "support getWidth" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.getWidth(0, 0) ==> 100
    }
    "support has" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.has(0, 0) ==> false
    }
    "support rowHeight" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.rowHeight(raw.RawIndexParameter(0)) ==> 30
    }
    "support set" - {
      val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
      cellMeasurerCache.set(0, 0, 20, 30) ==> (())
    }
  }
}
