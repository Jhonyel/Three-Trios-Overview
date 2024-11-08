package cs3500.three.trios.view;

import cs3500.three.trios.model.card.PlayerCard;

/**
 * Interface that represents the actions that the grid has.
 */
public interface GridPanel {

  /**
   * Sets the cell to contain a specific player card if possible.
   * @param row the row in the grid
   * @param col the column in the grid
   * @param card the card that will be set in this cell
   */
  void setCell(int row, int col, PlayerCard card);

  /**
   * Update the grid to reflect any changes in the game state.
   */
  void updateGrid();
}
