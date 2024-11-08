package cs3500.three.trios.model;

import static cs3500.three.trios.model.PlayerColor.BLUE;
import static cs3500.three.trios.model.PlayerColor.RED;
import static cs3500.three.trios.util.Requirements.requireNonNull;
import static cs3500.three.trios.util.Requirements.requireNonNullArray2D;
import static cs3500.three.trios.util.Requirements.requireNonNullCollection;
import static cs3500.three.trios.util.Requirements.requireRectangularArray2D;
import static cs3500.three.trios.util.Requirements.requireUniqueCollection;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Utils;
import java.util.ArrayList;
import java.util.Collections;
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

  private ThreeTriosModelImpl(
      Cell[][] grid, List<? extends Card> redHand, List<? extends Card> blueHand
  ) {
    requireNonNullArray2D(grid);
    requireRectangularArray2D(grid);
    requireNonNullCollection(redHand);
    requireUniqueCollection(redHand);
    requireNonNullCollection(blueHand);
    requireUniqueCollection(blueHand);

    this.grid = Utils.copyArray2D(grid);
    this.currentPlayerColor = RED;
    this.redHand = new ArrayList<>();
    this.blueHand = new ArrayList<>();
    this.gridWidth = grid[0].length;
    this.gridHeight = grid.length;

    for (Card card : redHand) {
      this.redHand.add(new PlayerCard(card, RED));
    }
    for (Card card : blueHand) {
      this.blueHand.add(new PlayerCard(card, BLUE));
    }
  }

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
  public static ThreeTriosModelImpl createNewGame(
      Cell[][] grid, List<Card> cards, boolean shouldShuffle
  ) {
    requireNonNullArray2D(grid);
    requireRectangularArray2D(grid);
    requireCardCellsAreEmpty(grid);
    requireNonNullCollection(cards);
    requireUniqueCollection(cards);

    // copy the cards arg so that shuffling does not affect the cards arg
    cards = new ArrayList<>(cards);
    if (shouldShuffle) {
      Collections.shuffle(cards);
    }

    int numCardCells = getNumCardCells(grid);
    requireNumCardCellsIsOdd(numCardCells);
    requireNumCardsIsGreaterThanNumCardCells(cards, numCardCells);

    int splitIndex = (numCardCells + 1) / 2;
    List<PlayerCard> redHand = cards.subList(0, splitIndex).stream()
        .map(card -> new PlayerCard(card, RED))
        .collect(Collectors.toList());

    List<PlayerCard> blueHand = cards.subList(splitIndex, numCardCells + 1).stream()
        .map(card -> new PlayerCard(card, BLUE))
        .collect(Collectors.toList());

    return new ThreeTriosModelImpl(grid, redHand, blueHand);
  }

  /**
   * Creates a game in progress given by the grid, red hand, and blue hand.
   *
   * @param grid     the grid of the game.
   * @param redHand  the hand of the red player.
   * @param blueHand the hand of the blue player.
   * @return a new ThreeTriosModelImpl representing the game in progress.
   */
  public static ThreeTriosModelImpl createGameInProgress(
      Cell[][] grid, List<Card> redHand, List<Card> blueHand
  ) {
    return new ThreeTriosModelImpl(grid, redHand, blueHand);
  }

  private static void requireCardCellsAreEmpty(Cell[][] grid) {
    int gridWidth = grid[0].length;
    int gridHeight = grid.length;
    for (int rowIndex = 0; rowIndex < gridHeight; rowIndex++) {
      for (int colIndex = 0; colIndex < gridWidth; colIndex++) {
        Cell cell = grid[rowIndex][colIndex];
        if (cell.isOccupiedCardCell()) {
          throw new IllegalArgumentException("all card cells must be empty");
        }
      }
    }
  }

  private static void requireNumCardsIsGreaterThanNumCardCells(List<Card> cards, int numCardCells) {
    if (cards.size() <= numCardCells) {
      throw new IllegalArgumentException(
          "Number of cards must be at least the number of card cells + 1");
    }
  }

  private static void requireNumCardCellsIsOdd(int numCardCells) {
    if (numCardCells % 2 == 0) {
      throw new IllegalArgumentException("Number of card cells must be odd");
    }
  }

  /**
   * Returns the number of card cells in the given grid.
   */
  private static int getNumCardCells(Cell[][] grid) {
    int numCardCells = 0;
    int gridHeight = grid.length;
    int gridWidth = grid[0].length;
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

  ////////////////////////////////////////////////////////////////////////////

  @Override
  public void playCardAt(int rowIndex, int colIndex, PlayerCard card) {
    requireGameIsNotOver();
    requireValidRowIndex(rowIndex);
    requireValidColIndex(colIndex);
    requireCellIsEmptyCardCell(rowIndex, colIndex);
    requireNonNull(card);
    requireCurrentHandContainsCard(card);

    List<PlayerCard> currentHand = currentPlayerColor == RED ? redHand : blueHand;
    currentHand.remove(card);
    grid[rowIndex][colIndex] = Cell.createOccupiedCardCell(card);

    battle(rowIndex, colIndex);

    currentPlayerColor = (currentPlayerColor == RED)
        ? BLUE
        : RED;
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
    return new ArrayList<>(playerColor == RED ? redHand : blueHand);
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
        if (playerColor.equals(RED)) {
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
    return numRedCards > numBlueCards ? RED : BLUE;
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    requireGameIsNotOver();
    return currentPlayerColor;
  }

  ////////////////////////////////////////////////////////////////////////////

  @Override
  public int getWidth() {
    return grid[0].length;
  }

  @Override
  public int getHeight() {
    return grid.length;
  }

  @Override
  public boolean isMoveLegalAt(int rowIndex, int colIndex) {
    return getCellAt(rowIndex, colIndex).isEmptyCardCell();
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, int cardIndex) {
    PlayerColor currentPlayer = getCurrentPlayer();
    List<PlayerCard> hand = getHand(currentPlayer);
    PlayerCard card = hand.get(cardIndex);

    return getNumFlipsAt(rowIndex, colIndex, card);
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, PlayerCard card) {
    int numFlips = 0;

    for (Direction direction : Direction.values()) {
      if (!isAdjacentCellEnemy(rowIndex, colIndex, direction)) {
        continue;
      }
      int adjacentRowIndex = rowIndex + direction.getRowOffset();
      int adjacentColIndex = colIndex + direction.getColOffset();
      PlayerCard adjacentCard = getCardAt(adjacentRowIndex, adjacentColIndex);
      if (card.beats(adjacentCard, direction)) {
        numFlips++;
      }
    }
    return numFlips;
  }

  @Override
  public int getScore(PlayerColor player) {
    int score = 0;
    for (int rowIndex = 0; rowIndex < gridHeight; rowIndex++) {
      for (int colIndex = 0; colIndex < gridWidth; colIndex++) {
        Cell cell = getCellAt(rowIndex, colIndex);
        if (!cell.isOccupiedCardCell()) {
          continue;
        }
        PlayerCard card = cell.getCard();
        if (card.getPlayerColor() == player) {
          score++;
        }
      }
    }
    score += getHand(player).size();
    return score;
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
