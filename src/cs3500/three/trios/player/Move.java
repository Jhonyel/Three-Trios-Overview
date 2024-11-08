package cs3500.three.trios.player;

public class Move {

  private final int rowIndex;
  private final int colIndex;
  private final int cardIndex;

  public Move(int rowIndex, int colIndex, int cardIndex) {
    this.rowIndex = rowIndex;
    this.colIndex = colIndex;
    this.cardIndex = cardIndex;
  }
}
