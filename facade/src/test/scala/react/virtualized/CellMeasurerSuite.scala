package react
package virtualized

import react.common.TestUtils

class CellMeasurerSuite extends munit.FunSuite with TestUtils {
  test("be buildable") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assert(cellMeasurerCache != null)
  }
  test("support clear") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.clear(0, 0), ())
  }
  test("support clearAll") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.clearAll(), ())
  }
  test("support columnWidth") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.columnWidth(raw.RawIndexParameter(0)), 100)
  }
  test("support getHeight") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.getHeight(0, 0), 30)
  }
  test("support getWidth") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.getWidth(0, 0), 100)
  }
  test("support has") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.has(0, 0), false)
  }
  test("support rowHeight") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.rowHeight(raw.RawIndexParameter(0)), 30)
  }
  test("support set") {
    val cellMeasurerCache = new CellMeasurerCache(CellMeasurerCacheParams(FixedHeight))
    assertEquals(cellMeasurerCache.set(0, 0, 20, 30), ())
  }
}
