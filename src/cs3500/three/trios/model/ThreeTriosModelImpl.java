package cs3500.three.trios.model;

import cs3500.three.trios.model.card.AttackValue;
import cs3500.three.trios.model.card.Card;
import java.util.List;

/**
 * A class for representing a game of Three Trios.
 */
public class ThreeTriosModelImpl implements ThreeTriosModel {

  private final Cell[][] grid;
  private Player currentPlayer;

  /**
   * Creates a new ThreeTriosModelImpl representing a game with the specified grid and cards. If
   * shouldShuffle is true, the cards are shuffled before the game starts. Shuffling the cards does
   * not affect the list passed in, it only affects the model. If the cards are not shuffled, the
   * first half of the list is assigned to player red and the second half is assigned to player
   * blue. The number of cards in the list must be equal to the number of card cells in the grid
   * plus one. Modifying the grid or cards passed in does not affect this model.
   *
   * @param grid          The grid of cells to use. Each Cell[] in the grid represents a row of
   *                      cells. Each element in a row represents a cell.
   * @param cards         the cards to play the game with
   * @param shouldShuffle whether the cards should be shuffled before starting the game
   * @throws IllegalArgumentException if the grid is null, the grid contains null elements, the grid
   *                                  is not rectangular, the number of card cells is not an odd
   *                                  number, cards is null, contains null, or does not have a size
   *                                  equal to the number of card cells in the grid plus one.
   */
  public ThreeTriosModelImpl(Cell[][] grid, List<Card> cards, boolean shouldShuffle) {
    // require grid is non-null, non-empty, non-null containing
    // require rows in grid are non-null, non-empty, non-null containing
    // require all rows are the same width (i.e. the grid is rectangular)
    // require width * height is odd
    int height = grid.length;
    int width = grid[0].length;
    this.grid = new Cell[height][width];
    for (int rowIndex = 0; rowIndex < height; rowIndex++) {
      for (int colIndex = 0; colIndex < width; colIndex++) {
        this.grid[rowIndex][colIndex] = grid[rowIndex][colIndex];
      }
    }
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, Card card) {
    // require:
    // the game has started
    // row index is valid
    // col index is valid
    // card is non-null
    // specified cell is an empty card cell

    // grid[rowIndex][colIndex] = card;
    throw new UnsupportedOperationException();
  }

  private void battle(int rowIndex, int colIndex) {
    for (Direction direction : Direction.values()) {
      battle(rowIndex, colIndex, direction);
    }
  }

  private void battle(int rowIndex, int colIndex, Direction direction) {
    // require indices are valid
    // require adjacent cell exists
    // require adjacent cell is not empty
    // require adjacent player is enemy

    Cell cell = getCellAt(rowIndex, colIndex);
    Card card = cell.getCard();
    int adjacentRowIndex = rowIndex + direction.getRowOffset();
    int adjacentColIndex = colIndex + direction.getColOffset();
    Cell adjacentCell = getCellAt(adjacentRowIndex, adjacentColIndex);
    Card enemyCard = adjacentCell.getCard();
    AttackValue attackValue = card.getAttackValue(direction);
    AttackValue enemyAttackValue = enemyCard.getAttackValue(direction);
    boolean isEnemyWeaker = attackValue.compareTo(enemyAttackValue) > 0;
    if (isEnemyWeaker) {
      Cell flippedCell = Cell.createCardCell(enemyCard, getCurrentPlayer());
      grid[adjacentRowIndex][adjacentColIndex] = flippedCell;
      battle(adjacentRowIndex, adjacentColIndex);
    }
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
  public boolean isGameOver() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Player getWinner() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Player getCurrentPlayer() {
    throw new UnsupportedOperationException();
  }
}
