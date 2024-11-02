package cs3500.three.trios.model;

import static cs3500.three.trios.util.Requirements.requireNonNull;
import static cs3500.three.trios.util.Requirements.requireNonNullArray2D;
import static cs3500.three.trios.util.Requirements.requireNonNullCollection;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class for representing a game of Three Trios.
 */
public class ThreeTriosModelImpl implements ThreeTriosModel {

  private final Cell[][] grid;
  private PlayerColor currentPlayerColor;
  private final List<PlayerCard> redHand;
  private final List<PlayerCard> blueHand;
  private final int gridWidth;
  private final int gridHeight;


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
   *                                  number, cards is null, contains null, does not have a size
   *                                  equal to the number of card cells in the grid plus one, or has
   *                                  duplicate cards.
   */
  public ThreeTriosModelImpl(Cell[][] grid, List<Card> cards, boolean shouldShuffle) {
    requireNonNullArray2D(grid);
    requireGridIsRectangular(grid);
    requireNonNullCollection(cards);
    requireAllCardsAreUnique(cards);
    this.grid = Utils.copyArray2D(grid);
    currentPlayerColor = PlayerColor.RED;
    gridHeight = grid.length;
    gridWidth = grid[0].length;

    // copy the cards arg so that shuffling does not affect the cards arg
    cards = new ArrayList<>(cards);
    if (shouldShuffle) {
      Collections.shuffle(cards);
    }

    int numCardCells = getNumCardCells(grid);
    requireNumCardCellsIsOdd(numCardCells);
    requireNumCardsIsGreaterThanNumCardCells(cards, numCardCells);

    int splitIndex = (numCardCells + 1) / 2;
    redHand = cards.subList(0, splitIndex).stream()
        .map(card -> new PlayerCard(card, PlayerColor.RED))
        .collect(Collectors.toList());

    blueHand = cards.subList(splitIndex, numCardCells + 1).stream()
        .map(card -> new PlayerCard(card, PlayerColor.BLUE))
        .collect(Collectors.toList());
  }

  /**
   * Checks that all cards in the given list are unique, else throws an IllegalArgumentException.
   */
  private void requireAllCardsAreUnique(List<Card> cards) {
    // a set cannot contain duplicates.
    // if a list does not contain duplicates, its size as a list will equal its size as a set.
    if (cards.size() != new HashSet<>(cards).size()) {
      throw new IllegalArgumentException("All cards must be unique");
    }
  }

  /**
   * Checks that the number of cards in the given list is greater than the number of card cells in
   * the grid, else throws an IllegalArgumentException.
   */
  private void requireNumCardsIsGreaterThanNumCardCells(List<Card> cards, int numCardCells) {
    if (cards.size() <= numCardCells) {
      throw new IllegalArgumentException(
          "Number of cards must be at least the number of card cells + 1");
    }
  }

  /**
   * Checks that the number of card cells in the grid is odd, else throws an
   * IllegalArgumentException.
   */
  private void requireNumCardCellsIsOdd(int numCardCells) {
    if (numCardCells % 2 == 0) {
      throw new IllegalArgumentException("Number of card cells must be odd");
    }
  }

  /**
   * Returns the number of card cells in the given grid.
   */
  private int getNumCardCells(Cell[][] grid) {
    int numCardCells = 0;
    for (int rowIndex = 0; rowIndex < gridHeight; rowIndex++) {
      for (int colIndex = 0; colIndex < gridWidth; colIndex++) {
        Cell cell = grid[rowIndex][colIndex];
        if (cell.isCardCell()) {
          numCardCells++;
        }
      }
    }
    return numCardCells;
  }

  /**
   * Checks that the grid is rectangular (i.e. is non-empty, and all rows have the same length),
   * else throws an IllegalArgumentException.
   */
  private void requireGridIsRectangular(Cell[][] grid) {
    int height = grid.length;
    if (height == 0) {
      throw new IllegalArgumentException("Grid must have at least one row");
    }
    int width = grid[0].length;
    for (Cell[] row : grid) {
      if (row.length != width) {
        throw new IllegalArgumentException("Grid must be rectangular");
      }
    }
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, PlayerCard card) {
    requireGameIsNotOver();
    requireValidRowIndex(rowIndex);
    requireValidColIndex(colIndex);
    requireCellIsEmptyCardCell(rowIndex, colIndex);
    requireNonNull(card);
    requireCurrentHandContainsCard(card);

    List<PlayerCard> currentHand = currentPlayerColor == PlayerColor.RED ? redHand : blueHand;
    currentHand.remove(card);
    grid[rowIndex][colIndex] = Cell.createOccupiedCardCell(card);

    battle(rowIndex, colIndex);

    currentPlayerColor = (currentPlayerColor == PlayerColor.RED)
        ? PlayerColor.BLUE
        : PlayerColor.RED;
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, int cardIndex) {
    requireCardIndexIsValid(cardIndex);
    PlayerCard card = getCurrentHand().get(cardIndex);
    playCardAt(rowIndex, colIndex, card);
  }

  /**
   * Checks that the given card index is an index of the current hand, else throws an
   * IndexOutOfBoundsException.
   */
  private void requireCardIndexIsValid(int cardIndex) {
    if (cardIndex < 0 || cardIndex >= getCurrentHand().size()) {
      throw new IndexOutOfBoundsException("Card index is not valid");
    }
  }


  /**
   * Checks that the current hand contains the given card, else throws an IllegalArgumentException.
   */
  private void requireCurrentHandContainsCard(PlayerCard card) {
    if (!getCurrentHand().contains(card)) {
      throw new IllegalArgumentException("Current hand does not contain card");
    }
  }

  /**
   * Checks that the given cell is an empty card cell, else throws an IllegalStateException.
   */
  private void requireCellIsEmptyCardCell(int rowIndex, int colIndex) {
    if (!grid[rowIndex][colIndex].isEmptyCardCell()) {
      throw new IllegalStateException("Cell is not an empty card cell");
    }
  }

  /**
   * Conducts battle at the given row and column in all 4 directions.
   */
  private void battle(int rowIndex, int colIndex) {
    for (Direction direction : Direction.values()) {
      battle(rowIndex, colIndex, direction);
    }
  }

  /**
   * Conducts battle at the given row and column in the given direction. If the card battled is
   * flipped, the flipped card conducts battle in all 4 directions.
   */
  private void battle(int rowIndex, int colIndex, Direction direction) {
    requireValidRowIndex(rowIndex);
    requireValidColIndex(colIndex);
    if (!isAdjacentCellEnemy(rowIndex, colIndex, direction)) {
      return;
    }
    int adjacentRowIndex = rowIndex + direction.getRowOffset();
    int adjacentColIndex = colIndex + direction.getColOffset();
    Card currentCard = getCardAt(rowIndex, colIndex);
    Card enemyCard = getCardAt(adjacentRowIndex, adjacentColIndex);
    if (currentCard.beats(enemyCard, direction)) {
      PlayerCard flippedCard = new PlayerCard(enemyCard, currentPlayerColor);
      Cell flippedCell = Cell.createOccupiedCardCell(flippedCard);
      grid[adjacentRowIndex][adjacentColIndex] = flippedCell;
      battle(adjacentRowIndex, adjacentColIndex);
    }
  }

  /**
   * Returns true if the card at the given row and column has an adjacent enemy cell in the given
   * direction, else returns false.
   */
  private boolean isAdjacentCellEnemy(int rowIndex, int colIndex, Direction direction) {
    int adjacentRowIndex = rowIndex + direction.getRowOffset();
    int adjacentColIndex = colIndex + direction.getColOffset();
    if (!isRowIndexValid(adjacentRowIndex) || !isColIndexValid(adjacentColIndex)) {
      return false;
    }
    Cell adjacentCell = getCellAt(adjacentRowIndex, adjacentColIndex);

    if (!adjacentCell.isOccupiedCardCell()) {
      return false;
    }
    PlayerColor adjacentPlayerColor = adjacentCell.getCard().getPlayerColor();
    return !adjacentPlayerColor.equals(currentPlayerColor);
  }

  @Override
  public List<PlayerCard> getHand(PlayerColor playerColor) {
    requireNonNull(playerColor);
    return new ArrayList<>(playerColor == PlayerColor.RED ? redHand : blueHand);
  }

  /**
   * Returns the hand of the current player.
   */
  private List<PlayerCard> getCurrentHand() {
    // note: we use currentPlayerColor instead of getCurrentPlayer() because getCurrentPlayer()
    // only works if the game is not over. we want to call getCurrentHand() even if the game is
    // over because we still have to do battle after the last card is played.
    return getHand(currentPlayerColor);
  }

  @Override
  public Cell[][] getGrid() {
    return Utils.copyArray2D(grid);
  }

  @Override
  public Cell getCellAt(int rowIndex, int colIndex) {
    requireValidRowIndex(rowIndex);
    requireValidColIndex(colIndex);
    return grid[rowIndex][colIndex];
  }

  @Override
  public PlayerCard getCardAt(int rowIndex, int colIndex) {
    requireValidRowIndex(rowIndex);
    requireValidColIndex(colIndex);
    Cell cell = getCellAt(rowIndex, colIndex);
    if (!cell.isOccupiedCardCell()) {
      throw new IllegalStateException("Cell is not an occupied card cell");
    }
    return cell.getCard();
  }

  @Override
  public PlayerColor getPlayerAt(int rowIndex, int colIndex) {
    requireValidRowIndex(rowIndex);
    requireValidColIndex(colIndex);
    Cell cell = getCellAt(rowIndex, colIndex);
    if (!cell.isOccupiedCardCell()) {
      throw new IllegalStateException("Cell is not an occupied card cell");
    }
    return cell.getCard().getPlayerColor();
  }

  @Override
  public boolean isGameOver() {
    for (int rowIndex = 0; rowIndex < gridHeight; rowIndex++) {
      for (int colIndex = 0; colIndex < gridWidth; colIndex++) {
        Cell cell = grid[rowIndex][colIndex];
        if (cell.isEmptyCardCell()) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public PlayerColor getWinner() {
    requireGameIsOver();

    int numRedCards = 0;
    int numBlueCards = 0;

    for (int rowIndex = 0; rowIndex < gridHeight; rowIndex++) {
      for (int colIndex = 0; colIndex < gridWidth; colIndex++) {
        Cell cell = grid[rowIndex][colIndex];
        if (cell.isHole()) {
          continue;
        }
        PlayerColor playerColor = cell.getCard().getPlayerColor();
        if (playerColor.equals(PlayerColor.RED)) {
          numRedCards++;
        } else {
          numBlueCards++;
        }
      }
    }

    numRedCards += redHand.size();
    numBlueCards += blueHand.size();

    if (numRedCards == numBlueCards) {
      return null;
    }
    return numRedCards > numBlueCards ? PlayerColor.RED : PlayerColor.BLUE;
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    requireGameIsNotOver();
    return currentPlayerColor;
  }

  ////////////////////////////////////////////////////////////////////////////


  /**
   * Checks that the given column index is valid, else throws an IndexOutOfBoundsException.
   */
  private void requireValidColIndex(int colIndex) {
    if (colIndex < 0 || colIndex >= gridWidth) {
      throw new IndexOutOfBoundsException("Invalid column index");
    }
  }

  /**
   * Checks that the given row index is valid, else throws an IndexOutOfBoundsException.
   */
  private void requireValidRowIndex(int rowIndex) {
    if (rowIndex < 0 || rowIndex >= gridHeight) {
      throw new IndexOutOfBoundsException("Invalid row index");
    }
  }

  /**
   * Checks that the game is over, else throws an IllegalStateException.
   */
  private void requireGameIsOver() {
    if (!isGameOver()) {
      throw new IllegalStateException("Game is not over");
    }
  }

  /**
   * Checks that the game is not over, else throws an IllegalStateException.
   */
  private void requireGameIsNotOver() {
    if (isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
  }

  /**
   * Returns true if the given row index is valid, false otherwise.
   */
  private boolean isRowIndexValid(int rowIndex) {
    return rowIndex >= 0 && rowIndex < gridHeight;
  }

  /**
   * Returns true if the given column index is valid, false otherwise.
   */
  private boolean isColIndexValid(int colIndex) {
    return colIndex >= 0 && colIndex < gridWidth;
  }
}
