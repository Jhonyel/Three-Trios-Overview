package cs3500.three.trios.model;

import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.util.Objects;

/**
 * A class representing a cell on a grid. A cell is either a hole or a card cell. A card cell is
 * either empty or occupied.
 */
public class Cell {

  private final boolean isHole;
  private final PlayerCard card;

  /**
   * Creates a new Cell with the given isHole status and card (null if this cell is not an occupied
   * card cell).
   *
   * @param isHole true if this cell is a hole, else false.
   * @param card   the card occupying this cell if this cell is an occupied card cell, else null.
   */
  private Cell(boolean isHole, PlayerCard card) {
    this.isHole = isHole;
    this.card = card;
  }

  /**
   * Creates and returns a new hole cell.
   */
  public static Cell createHoleCell() {
    return new Cell(true, null);
  }

  /**
   * Creates and returns a new empty card cell.
   */
  public static Cell createEmptyCardCell() {
    return new Cell(false, null);
  }

  /**
   * Creates and returns a new occupied card cell with the given player card.
   *
   * @param card the player card occupying this cell.
   * @throws IllegalArgumentException if card is null.
   */
  public static Cell createOccupiedCardCell(PlayerCard card) {
    return new Cell(false, Requirements.requireNonNull(card));
  }

  /**
   * Returns true if this cell is a hole, false otherwise.
   */
  public boolean isHole() {
    return isHole;
  }

  /**
   * Returns true if this cell is a card cell (empty or occupied), false otherwise.
   */
  public boolean isCardCell() {
    return isEmptyCardCell() || isOccupiedCardCell();
  }

  /**
   * Returns true if this cell is an empty card cell, false otherwise.
   */
  public boolean isEmptyCardCell() {
    return !isHole && card == null;
  }

  /**
   * Returns true if this cell is an occupied card cell, false otherwise.
   */
  public boolean isOccupiedCardCell() {
    return !isHole && card != null;
  }

  /**
   * Returns the card occupying this occupied card cell.
   *
   * @throws IllegalStateException if this cell is not an occupied card cell.
   */
  public PlayerCard getCard() {
    if (!isOccupiedCardCell()) {
      throw new IllegalStateException("Cell is not an occupied card cell");
    }
    return card;
  }

  /**
   * Returns the player occupying this occupied card cell.
   *
   * @throws IllegalStateException if this cell is not an occupied card cell.
   */
  public PlayerColor getPlayerColor() {
    if (!isOccupiedCardCell()) {
      throw new IllegalStateException("Cell is not an occupied card cell");
    }
    return card.getPlayerColor();
  }

  /**
   * Returns true if `other` is the same type of cell as this cell. If this cell is an occupied card
   * cell, the card of `other` must be the same as this cell's card.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Cell) {
      Cell otherCell = (Cell) other;
      if (otherCell.isOccupiedCardCell() && this.isOccupiedCardCell()) {
        return otherCell.getCard().equals(this.getCard());
      }
      return otherCell.isHole == this.isHole;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(isHole, card);
  }

  /**
   * Returns "Hole" if this cell is a hole, "Empty" if this cell is an empty card cell, and a string
   * in the format: "Occupied by: CARD_NAME NORTH SOUTH EAST WEST" if this cell is an occupied card
   * cell, CARD_NAME is the name of the card occupying this cell, and NORTH SOUTH EAST and WEST are
   * the attack values.
   */
  @Override
  public String toString() {
    if (isHole) {
      return "Hole";
    } else if (isOccupiedCardCell()) {
      return "Occupied by: " + card.toString();
    } else {
      return "Empty";
    }
  }

}
