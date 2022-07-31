package rover.model

object Directions {
  sealed trait Direction
  case object East extends Direction
  case object West extends Direction
  case object North extends Direction
  case object South extends Direction
}
