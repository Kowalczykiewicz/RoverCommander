import cats.data.State
import rover.model.Position

package object rover {
  type Cord = Int
  type RoverState = State[Position, String]
}
