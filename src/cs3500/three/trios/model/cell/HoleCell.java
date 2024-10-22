package cs3500.three.trios.model.cell;

import java.util.Optional;

public class HoleCell extends Cell {

  public HoleCell(
      Optional<Cell> north, Optional<Cell> east, Optional<Cell> south, Optional<Cell> west
  ) {
    super(north, east, south, west);
  }
}
