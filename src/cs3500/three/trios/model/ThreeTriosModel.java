package cs3500.three.trios.model;

import cs3500.three.trios.model.card.PlayerCard;

/**
 * An interface representing a Three Trios game. A game of three trios involves two hands of cards
 * and a grid of cells. There are two ways to construct a ThreeTriosModel: creating a new game or
 * creating a game in progress. Creating a game in progress is often helpful for testing, while
 * creating a new game is helpful for actually playing the game from the start.
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
   * @throws IllegalArgumentException  if the provided card is null or does not belong to the
   *                                   current player's hand.
   * @throws IllegalStateException     if the specified cell is a hole, or the specified cell is
   *                                   occupied by another card, or the game is ove
   */
  void playCardAt(int rowIndex, int colIndex, PlayerCard card);

  /**
   * Plays the specified card at the specified cell.
   *
   * @param rowIndex  the row index of the cell to place the card in
   * @param colIndex  the col index of the cell to place the card in
   * @param cardIndex the index of the card in the current player's hand to be played
   * @throws IndexOutOfBoundsException if rowIndex is not in the range [0, getHeight()], or colIndex
   *                                   is not in the range [0, getWidth()] or if cardIndex is not a
   *                                   valid index.
   * @throws IllegalStateException     if the specified cell is a hole, or the specified cell is
   *                                   occupied by another card, or if the game is over.
   */
  void playCardAt(int rowIndex, int colIndex, int cardIndex);


}
