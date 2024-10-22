package cs3500.three.trios.model;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.cell.Cell;
import java.util.List;

public class ThreeTriosModelImpl implements ThreeTriosModel {

  public ThreeTriosModelImpl(List<Card> cards, boolean shouldShuffle, Cell[][] grid) {
  }

  @Override
  public void place(int rowIndex, int colIndex, Card card) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void battle(int rowIndex, int colIndex) {
    throw new UnsupportedOperationException();

  }

  @Override
  public List<Card> getHand(Player player) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Cell[][] getGrid() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Cell getCellAt(int rowIndex, int colIndex) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void startGame() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isGameOver() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isGameWon() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Player getWinner() {
    throw new UnsupportedOperationException();
  }
}
