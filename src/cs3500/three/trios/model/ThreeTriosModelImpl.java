package cs3500.three.trios.model;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.PlayerCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class for representing a game of Three Trios.
 */
public class ThreeTriosModelImpl implements ThreeTriosModel {

  private final Cell[][] grid;
  private PlayerColor currentPlayerColor;
  private final List<PlayerCard> RedHand;
  private final List<PlayerCard> BlueHand;


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
    this.currentPlayerColor = PlayerColor.RED;
    if (shouldShuffle) {
      Collections.shuffle(cards);
    }
    this.RedHand = new ArrayList<>();
    this.BlueHand = new ArrayList<>();
    for (int i = 0; i < cards.size(); i++) {
      if (i < cards.size()/2) {
        PlayerCard card = new PlayerCard(cards.get(i), PlayerColor.RED);
        this.RedHand.add(card);
      }
      else {
        PlayerCard card = new PlayerCard(cards.get(i), PlayerColor.BLUE);
        this.BlueHand.add(card);
      }
    }
    this.currentPlayerColor = PlayerColor.RED;
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, PlayerCard card) {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    if (rowIndex < 0 || rowIndex >= this.grid.length) {
      throw new IndexOutOfBoundsException("row index out of bounds");
    }
    if (colIndex < 0 || colIndex >= grid[0].length) {
      throw new IndexOutOfBoundsException("column index out of bounds");
    }
    if (grid[rowIndex][colIndex].getCard() != null) {
      throw new IllegalArgumentException("Card already played");
    }
    if (grid[rowIndex][colIndex].isHole()) {
      throw new IllegalArgumentException("Cell is a hole");
    }
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    this.getHand(this.currentPlayerColor).remove(card);
    this.grid[rowIndex][colIndex] = Cell.createOccupiedCardCell(card);

    //call battle here in between these steps

    if (this.currentPlayerColor == PlayerColor.RED) {
      this.currentPlayerColor = PlayerColor.BLUE;
    }
    else {
      this.currentPlayerColor = PlayerColor.RED;
    }
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

    // Cell cell = getCellAt(rowIndex, colIndex);
    // Card card = cell.getCard();
    // int adjacentRowIndex = rowIndex + direction.getRowOffset();
    // int adjacentColIndex = colIndex + direction.getColOffset();
    // Cell adjacentCell = getCellAt(adjacentRowIndex, adjacentColIndex);
    // Card enemyCard = adjacentCell.getCard();
    // AttackValue attackValue = card.getAttackValue(direction);
    // AttackValue enemyAttackValue = enemyCard.getAttackValue(direction);
    // boolean isEnemyWeaker = attackValue.compareTo(enemyAttackValue) > 0;
    // if (isEnemyWeaker) {
    //   Cell flippedCell = Cell.createCardCell(enemyCard, getCurrentPlayer());
    //   grid[adjacentRowIndex][adjacentColIndex] = flippedCell;
    //   battle(adjacentRowIndex, adjacentColIndex);
    // }
  }

  @Override
  public List<PlayerCard> getHand(PlayerColor playerColor) {
    if (playerColor == PlayerColor.RED) {
      return this.RedHand;
    }
    return this.BlueHand;
  }

  @Override
  public Cell[][] getGrid() {
    Cell[][] grid = new Cell[this.grid.length][this.grid[0].length];
    for (int rowIndex = 0; rowIndex < grid.length; rowIndex++) {
      for (int colIndex = 0; colIndex < grid[0].length; colIndex++) {
        grid[rowIndex][colIndex] = this.grid[rowIndex][colIndex];
      }
    }
    return grid;
  }

  @Override
  public Cell getCellAt(int rowIndex, int colIndex) {
    Cell cell = this.grid[rowIndex][colIndex];
    Cell cellR;
    if (cell.isOccupiedCardCell()) {
      cellR = Cell.createOccupiedCardCell(cell.getCard());
    }
    else if (cell.isEmptyCardCell()) {
      cellR = Cell.createOccupiedCardCell(cell.getCard());
    }
    else {
      cellR = Cell.createHoleCell();
    }
    return cellR;
  }

  @Override
  public boolean isGameOver() {
    boolean gameOver = true;
    for (int rowIndex = 0; rowIndex < this.grid.length; rowIndex++) {
      for (int colIndex = 0; colIndex < this.grid[0].length; colIndex++) {
        Cell cell = this.grid[rowIndex][colIndex];
        if (cell.isEmptyCardCell()) {
          gameOver = false;
        }
      }
    }
    return gameOver;
  }

  @Override
  public PlayerColor getWinner() {
    if (!this.isGameOver()) {
      throw new IllegalStateException("Game is not over");
    }
    int red = 0;
    int blue = 0;
    for (int rowIndex = 0; rowIndex < this.grid.length; rowIndex++) {
      for (int colIndex = 0; colIndex < this.grid[0].length; colIndex++) {
        Cell cell = this.grid[rowIndex][colIndex];
        if (cell.isCardCell() && cell.getCard().getPlayerColor() == PlayerColor.RED) {
          red++;
        }
        else if (cell.isCardCell()) {
          blue++;
        }
      }
    }
    if (red > blue) {
      return PlayerColor.RED;
    }
    return PlayerColor.BLUE;
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    return this.currentPlayerColor;
  }
}
