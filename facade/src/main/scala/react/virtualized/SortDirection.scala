package react
package virtualized

import raw.RawSortDirection

sealed trait SortDirection

object SortDirection {
  case object ASC extends SortDirection
  case object DESC extends SortDirection
  case object NONE extends SortDirection

  implicit class ToRaw(val d: SortDirection) extends AnyVal {
    def toRaw: String = d match {
      case ASC => RawSortDirection.ASC
      case DESC => RawSortDirection.DESC
      case NONE => null
    }
  }
}
