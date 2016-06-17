
package object common {
  implicit class ToAnyUtil(val any: Any) extends AnyVal {

    def isNotDefined: Boolean =
      Option(any) match {
        case Some(s) => false
        case None    => true
      }

    def isDefined: Boolean = !isNotDefined
  }

}
