package rover.model

object Exceptions {
  abstract class RoverException(msg: String) extends Exception(msg)
  case class CommandException(msg: String) extends RoverException(msg)
}
