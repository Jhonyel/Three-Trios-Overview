package cs3500.three.trios.model;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.card.PlayerCard;

public class Cell {
  private final boolean isHole;
  private final PlayerCard card;

  private Cell(boolean isHole, PlayerCard card) {
    this.isHole = isHole;
    this.card = card;
  }

  public static Cell createHoleCell() {
    return new Cell(true, null);
  }

  public static Cell createEmptyCardCell() {
    return new Cell(false, null);
  }

  public static Cell createOccupiedCardCell(PlayerCard card) {
    return new Cell(false, requireNonNull(card));
  }

  public boolean isHole() {
    return isHole;
  }

  public boolean isCardCell() {
    return isEmptyCardCell() || isOccupiedCardCell();
  }

  public boolean isEmptyCardCell() {
    return !isHole && card == null;
  }

  public boolean isOccupiedCardCell() {
    return !isHole && card != null;
  }

  public PlayerCard getCard() {
    if (!isOccupiedCardCell()) {
      throw new IllegalStateException("Cell is not an occupied card cell");
    }
    return card;
  }

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
}
