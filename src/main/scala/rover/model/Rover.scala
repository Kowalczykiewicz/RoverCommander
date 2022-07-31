package rover.model

import rover.model.Commands.Command

case class Rover(position: Position) {
  /** Moves rover according to given commands
    *
    *  @param commands the commands sequence to be applied
    *  @return instance of Rover
    */
  def move(commands: Seq[Command]): Rover =
    if (commands.nonEmpty) {
      val allCommands = commands.tail.foldLeft(commands.head.action) { (acc, command) =>
        acc.flatMap(_ => command.action)
      }
      val newPosition = allCommands.runS(position).value
      Rover(position = newPosition)
    }
    else
      this
}
