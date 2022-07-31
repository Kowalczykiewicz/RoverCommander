package rover.model

import cats.data.State
import rover.RoverState
import rover.model.Directions.East
import rover.model.Directions.North
import rover.model.Directions.South
import rover.model.Directions.West
import rover.model.Exceptions.CommandException

import scala.util.Failure
import scala.util.Success
import scala.util.Try

object Commands {
  sealed trait Command { def action: RoverState }

  case object Forward extends Command {
    override def action: RoverState = State { position =>
      val newPosition = position.direction match {
        case East => Position(position.x + 1, position.y, position.direction)
        case West => Position(position.x - 1, position.y, position.direction)
        case North => Position(position.x, position.y + 1, position.direction)
        case South => Position(position.x, position.y - 1, position.direction)
      }
      (newPosition, "Moved forward")
    }
  }

  case object Backward extends Command {
    override def action: RoverState = State { position =>
      val newPosition = position.direction match {
        case East => Position(position.x - 1, position.y, position.direction)
        case West => Position(position.x + 1, position.y, position.direction)
        case North => Position(position.x, position.y - 1, position.direction)
        case South => Position(position.x, position.y + 1, position.direction)
      }
      (newPosition, "Moved backward")
    }
  }

  case object RotateLeft extends Command {
    override def action: RoverState = State { position =>
      val newDirection = position.direction match {
        case East => North
        case West => South
        case North => West
        case South => East
      }
      (position.copy(direction = newDirection), "Rotated left")
    }
  }

  case object RotateRight extends Command {
    override def action: RoverState = State { position =>
      val newDirection = position.direction match {
        case East => South
        case West => North
        case North => East
        case South => West
      }
      (position.copy(direction = newDirection), "Rotated right")
    }
  }

  def getCommand(symbol: Char): Try[Command] =
    symbol match {
      case 'F' => Success(Forward)
      case 'B' => Success(Backward)
      case 'L' => Success(RotateLeft)
      case 'R' => Success(RotateRight)
      case c => Failure(CommandException(s"Unknown command: $c"))
    }
}
