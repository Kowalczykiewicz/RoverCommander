package rover

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import rover.model.Directions._
import rover.model.Position
import rover.model.Rover
import org.scalatest.TryValues

class RoverCommanderSpec extends AnyFlatSpec with Matchers with TryValues {
  private val rover = Rover(position = Position(1, 2, North))

  "RoverCommander" should "move rover to correct position" in {
    RoverCommander.moveRover(rover, "").success.value shouldEqual rover
    RoverCommander.moveRover(rover, "B").success.value shouldEqual Rover(Position(1, 1, North))
    RoverCommander.moveRover(rover, "FFF").success.value shouldEqual Rover(Position(1, 5, North))
    RoverCommander.moveRover(rover, "FFLFFRB").success.value shouldEqual Rover(
      Position(-1, 3, North)
    )
  }

  "RoverCommander" should "handle unknown commands" in {
    RoverCommander
      .moveRover(rover, "FFLXFF")
      .failure
      .exception
      .getMessage shouldEqual "Unknown command: X"
  }
}
