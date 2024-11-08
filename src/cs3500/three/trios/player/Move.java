package cs3500.three.trios.player;

import java.util.Objects;

public class Move {

  private final int rowIndex;
  private final int colIndex;
  private final int cardIndex;

  public Move(int rowIndex, int colIndex, int cardIndex) {
    this.rowIndex = rowIndex;
    this.colIndex = colIndex;
    this.cardIndex = cardIndex;
  }

  public int getRowIndex() {
    return rowIndex;
  }

  public int getColIndex() {
    return colIndex;
  }

  public int getCardIndex() {
    return cardIndex;
  }

  @Override
  public String toString() {
    return String.format(
        "rowIndex: %d, colIndex: %d, cardIndex: %d", rowIndex, colIndex, cardIndex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rowIndex, colIndex, cardIndex);
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Move) {
      Move that = (Move) other;
      return this.rowIndex == that.rowIndex
          && this.colIndex == that.colIndex
          && this.cardIndex == that.cardIndex;
    }
    return false;
  }
}
