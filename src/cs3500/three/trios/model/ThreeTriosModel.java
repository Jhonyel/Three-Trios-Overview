package cs3500.three.trios.model;

import cs3500.three.trios.model.card.Card;
import java.util.List;

/**
 * An interface representing a Three Trios game.
 */
public interface ThreeTriosModel {

  /**
   * Plays the specified card at the specified cell.
   *
   * @param rowIndex the row index of the cell to place the card in
   * @param colIndex the col index of the cell to place the card in
   * @param card     the card to place at the specified cell
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()]
   * @throws IllegalArgumentException  if card is null
   * @throws IllegalStateException     if the specified cell is a hole, or the specified cell is
   *                                   occupied by another card.
   */
  void playCardAt(int rowIndex, int colIndex, Card card);

  /**
   * Returns a copy of the specified player's hand. Modifying the returned list does not affect this
   * model.
   *
   * @param playerColor the player whose hand is to be returned
   * @return a copy of the hand of the specified player
   * @throws IllegalArgumentException if player is null
   */
  List<Card> getHand(PlayerColor playerColor);

  // todo: make sure we are also cloning the cells in the grid and not just the grid
  // we need to make sure that setting the cells of the grid does not affect the grid in the model

  /**
   * Returns a copy of the grid as a 2D array. The element at (i, j) is the cell at row i and col j.
   * Modifying the returned 2D array does not affect this model.
   */
  Cell[][] getGrid();

  /**
   * Returns the cell at the specified row and column.
   *
   * @param rowIndex the row index of the cell to get.
   * @param colIndex the column index of the cell to get.
   * @return the cell at the specified row and column.
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()]
   */
  Cell getCellAt(int rowIndex, int colIndex);

  /**
   * Returns true if the game is over (i.e. if the grid is filled), false otherwise.
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game (i.e. the player owning more cards on the grid).
   *
   * @throws IllegalStateException if the game is not over
   */
  PlayerColor getWinner();

  /**
   * Returns the player whose turn it currently is.
   *
   * @throws IllegalStateException if the game is over.
   */
  PlayerColor getCurrentPlayer();
}
