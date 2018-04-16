package react
package virtualized

import japgolly.scalajs.react.vdom.html_<^.{< => <<, _}
import utest._

object SortIndicaterTests extends TestSuite with TestUtils {

  val tests = Tests {
    'renderNone - {
      val unmounted = <<.div(SortIndicator(SortDirection.NONE))
      val html =
        """<div>
        |<svg class="ReactVirtualized__Table__sortableHeaderIcon" width="18" height="18" viewBox="0 0 24 24"><path d="M7 10l5 5 5-5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>
        |""".stripMargin
      assertRender(Some(unmounted), html)
    }
    'renderAsc - {
      val unmounted = <<.div(SortIndicator(SortDirection.DESC))
      val html =
        """<div>
        |<svg class="ReactVirtualized__Table__sortableHeaderIcon ReactVirtualized__Table__sortableHeaderIcon--DESC" width="18" height="18" viewBox="0 0 24 24"><path d="M7 10l5 5 5-5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>
        |""".stripMargin
      assertRender(Some(unmounted), html)
    }
    'renderDesc - {
      val unmounted = <<.div(SortIndicator(SortDirection.ASC))
      val html =
        """<div>
        |<svg class="ReactVirtualized__Table__sortableHeaderIcon ReactVirtualized__Table__sortableHeaderIcon--ASC" width="18" height="18" viewBox="0 0 24 24"><path d="M7 14l5-5 5 5z"></path><path d="M0 0h24v24H0z" fill="none"></path></svg></div>
        |""".stripMargin
      assertRender(Some(unmounted), html)
    }
  }
}
