package cs3500.three.trios.model;

import cs3500.three.trios.model.card.PlayerCard;

/**
 * An interface representing a Three Trios game.
 *
 * <p>Class invariant: The number of cards in the hand of any player is at most (n + 1) / 2,
 * where n is the number of card cells in the grid.
 */
public interface ThreeTriosModel extends ReadOnlyThreeTriosModel {

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


}
