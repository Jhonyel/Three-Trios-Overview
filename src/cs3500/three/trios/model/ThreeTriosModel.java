package cs3500.three.trios.model;

import cs3500.three.trios.model.card.PlayerCard;
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
   * @param card     the card in the current player's hand to be played
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()]
   * @throws IllegalArgumentException  if the provided card is null.
   * @throws IllegalStateException     if the specified cell is a hole, or the specified cell is
   *                                   occupied by another card, the game is over, or the specified
   *                                   card is not in the current player's hand.
   */
  void playCardAt(int rowIndex, int colIndex, PlayerCard card);

  /**
   * Plays the specified card at the specified cell.
   *
   * @param rowIndex  the row index of the cell to place the card in
   * @param colIndex  the col index of the cell to place the card in
   * @param cardIndex the index of the card in the current player's hand to be played
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()]
   * @throws IllegalArgumentException  if cardIndex is not a valid index
   * @throws IllegalStateException     if the specified cell is a hole, or the specified cell is
   *                                   occupied by another card, or if the game is over.
   */
  void playCardAt(int rowIndex, int colIndex, int cardIndex);

  /**
   * Returns a copy of the specified player's hand. Modifying the returned list does not affect this
   * model.
   *
   * @param playerColor the player whose hand is to be returned
   * @return a copy of the hand of the specified player
   * @throws IllegalArgumentException if player is null
   */
  List<PlayerCard> getHand(PlayerColor playerColor);

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
   * Returns the card at the specified row and column, if one exists, else throws an exception.
   *
   * @param rowIndex the row index of the card to get.
   * @param colIndex the column index of the card to get.
   * @return the card at the specified row and column.
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()]
   * @throws IllegalStateException     if there is no card at the specified row and column
   */
  PlayerCard getCardAt(int rowIndex, int colIndex);

  /**
   * Returns the player at the specified row and column.
   *
   * @param rowIndex the row index of the player to get.
   * @param colIndex the column index of the player to get.
   * @return the player at the specified row and column.
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()]
   * @throws IllegalStateException     if there is no player at the specified row and column
   */
  PlayerColor getPlayerAt(int rowIndex, int colIndex);

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
