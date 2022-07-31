package rover.model

import rover.Cord
import rover.model.Directions.Direction

case class Position(
    x: Cord,
    y: Cord,
    direction: Direction,
  )
