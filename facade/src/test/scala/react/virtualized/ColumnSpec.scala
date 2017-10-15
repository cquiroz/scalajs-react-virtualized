package io.github.cquiroz.scalajs
package react
package virtualized

import org.scalatest.{FlatSpec, Matchers}

class ColumnSpec extends FlatSpec with Matchers {
  "Column" should
    "be created with minimal properties" in {
      Column(Column.props(200, "key"))
    }

}
