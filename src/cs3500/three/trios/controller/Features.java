package cs3500.three.trios.controller;

import cs3500.three.trios.model.PlayerColor;

/**
 * The interface defining the features of a game of three trios. In essence, this interface defines
 * a set of events that can occur in a game of three trios. The controller which implements this
 * interface will be responsible for handling these events.
 */
public interface Features {

  /**
   * Invoked when the user clicks a card in a hand panel. If it is currently the given player's
   * turn, the selection of the clicked card is toggled. If it is not currently the given player's
   * turn, any selected card is deselected.
   *
   * @param player    the player of the card clicked.
   * @param cardIndex the index of the card clicked.
   */
  void onHandCardClicked(PlayerColor player, int cardIndex);

  /**
   * Invoked when the user clicks empty space in a hand panel. Any selected card is deselected.
   */
  void onEmptySpaceClicked();

  /**
   * Invoked when the user clicks a cell in the grid. Any selected card is deselected.
   */
  void onCellClicked(int rowIndex, int colIndex);

}
