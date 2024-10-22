package cs3500.three.trios.model.cell;

import java.util.Optional;

public abstract class Cell {

  protected Optional<Cell> north;
  protected Optional<Cell> east;
  protected Optional<Cell> south;
  protected Optional<Cell> west;

  public Cell(
      Optional<Cell> north, Optional<Cell> east, Optional<Cell> south, Optional<Cell> west
  ) {
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }
}
