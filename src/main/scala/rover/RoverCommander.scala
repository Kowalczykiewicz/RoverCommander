package rover

import rover.model.Commands
import scala.util.Try
import rover.model.Rover
import cats.implicits._

object RoverCommander {
  /** Moves rover according to given commands
    *
    *  @param rover the rover to be moved
    *  @param commands the commands to be executed. Known commands - ('F', 'B', 'L', 'R')
    *  @return Success(instance of rover), or Failure on unknown command
    */
  def moveRover(rover: Rover, commands: String): Try[Rover] =
    commands
      .toList
      .map(Commands.getCommand)
      .sequence
      .map(rover.move)
}
