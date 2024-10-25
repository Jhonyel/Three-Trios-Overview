package cs3500.three.trios.model;

/**
 * An enum class representing the four directions in which a card can attack.
 */
public enum Direction {
  NORTH(-1, 0),
  EAST(0, 1),
  SOUTH(1, 0),
  WEST(0, -1);

  private final int rowOffset;
  private final int colOffset;

  Direction(int rowOffset, int colOffset) {
    this.rowOffset = rowOffset;
    this.colOffset = colOffset;
  }

  /**
   * Returns the direction opposite of this direction. North and south are opposites, as are east
   * and west.
   */
  public Direction getOppositeDirection() {
    switch (this) {
      case NORTH:
        return SOUTH;
      case EAST:
        return WEST;
      case SOUTH:
        return NORTH;
      case WEST:
        return EAST;
      default:
        throw new IllegalStateException("Unexpected value: " + this);
    }
  }

  /**
   * Returns the row offset of this direction: -1 for north, 1 for south, 0 otherwise.
   */
  public int getRowOffset() {
    return rowOffset;
  }

  /**
   * Returns the column offset of this direction. -1 for west, 1 for east, 0 otherwise.
   */
  public int getColOffset() {
    return colOffset;
  }
}
