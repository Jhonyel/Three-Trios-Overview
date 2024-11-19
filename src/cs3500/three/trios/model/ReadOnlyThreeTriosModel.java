package cs3500.three.trios.model;

import cs3500.three.trios.model.card.PlayerCard;
import java.util.List;

/**
 * An interface containing the observations for a game of three trios. Row indices are 0 based, with
 * indices increasing from top to bottom. Column indices are also 0 based, with indices increasing
 * from left to right.
 */
public interface ReadOnlyThreeTriosModel {

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
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()), or colIndex
   *                                   is not in the range [0, getWidth())
   */
  Cell getCellAt(int rowIndex, int colIndex);

  /**
   * Returns the card at the specified row and column, if one exists, else throws an exception.
   *
   * @param rowIndex the row index of the card to get.
   * @param colIndex the column index of the card to get.
   * @return the card at the specified row and column.
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()), or colIndex
   *                                   is not in the range [0, getWidth())
   * @throws IllegalStateException     if there is no card at the specified row and column
   */
  PlayerCard getCardAt(int rowIndex, int colIndex);

  /**
   * Returns the player at the specified row and column.
   *
   * @param rowIndex the row index of the player to get.
   * @param colIndex the column index of the player to get.
   * @return the player at the specified row and column.
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()), or colIndex
   *                                   is not in the range [0, getWidth())
   * @throws IllegalStateException     if there is no player at the specified row and column
   */
  PlayerColor getPlayerAt(int rowIndex, int colIndex);

  /**
   * Returns true if the game is over (i.e. if the grid is filled), false otherwise.
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game (i.e. the player owning more cards both on the grid and in their
   * hand). Returns null if both players own the same number of cards.
   *
   * @throws IllegalStateException if the game is not over
   */
  PlayerColor getWinner();

  /**
   * Returns the player whose turn it currently is.
   *
   * @throws IllegalStateException if the game is over.
   */
  PlayerColor getCurrentPlayerColor();

  ////////////////////////////////////////////////////////////////////////////
  // ADDED IN HW6
  ////////////////////////////////////////////////////////////////////////////

  /**
   * Returns the width of the grid (i.e. the number of columns).
   */
  int getWidth();

  /**
   * Returns the height of the grid (i.e. the number of rows).
   */
  int getHeight();

  /**
   * Returns true if the cell at the specified row and column is an empty card cell, false
   * otherwise.
   *
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()), or colIndex
   *                                   is not in the range [0, getWidth()).
   */
  boolean isMoveLegalAt(int rowIndex, int colIndex);

  /**
   * Returns the number of flips that would occur if the current player were to play the card in
   * their hand at the specified index to the cell at the specified row and column.
   *
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()), or colIndex
   *                                   is not in the range [0, getWidth()), or cardIndex is not in
   *                                   the range [0, getCurrentHand().size()).
   * @throws IllegalStateException     if the game is over.
   */
  int getNumFlipsAt(int rowIndex, int colIndex, int cardIndex);

  /**
   * Returns the number of flips that would occur if the current player were to play the specified
   * card in their hand to the cell at the specified row and column.
   *
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()), or colIndex
   *                                   is not in the range [0, getWidth()).
   * @throws IllegalStateException     if the card is not in the current player's hand, or the game
   *                                   is over.
   */
  int getNumFlipsAt(int rowIndex, int colIndex, PlayerCard card);

  /**
   * Return the score of the specified player (i.e. the number of cards in their hand plus the
   * number of cells on the grid belonging to them).
   *
   * @throws IllegalArgumentException if player is null.
   */
  int getScore(PlayerColor player);
}
