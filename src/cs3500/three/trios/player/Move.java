package cs3500.three.trios.player;

import java.util.Objects;

/**
 * A class representing a move in a game of three trios. A move consists of a row index, col index,
 * and card index. The row index is the index of the row of the cell being played to. The col index
 * is the index of the column of the cell being played to. The card index is the index of the card
 * being played.
 */
public class Move {

  private final int rowIndex;
  private final int colIndex;
  private final int cardIndex;

  /**
   * Creates a new move given by the row index, col index, and card index. The row index is the
   * index of the row of the cell being played to. The col index is the index of the column of the
   * cell being played to. The card index is the index of the card being played.
   */
  public Move(int rowIndex, int colIndex, int cardIndex) {
    this.rowIndex = rowIndex;
    this.colIndex = colIndex;
    this.cardIndex = cardIndex;
  }

  /**
   * Returns the index of the row of the cell being played to in this move.
   */
  public int getRowIndex() {
    return rowIndex;
  }

  /**
   * Returns the index of the column of the cell being played to in this move.
   */
  public int getColIndex() {
    return colIndex;
  }

  /**
   * Returns the index of the card in the current player's hand being played in this move.
   */
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
