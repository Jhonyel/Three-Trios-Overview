package cs3500.three.trios.view;

/**
 * Interface for the panels that represent the action for hands of the red and blue players.
 */
public interface HandPanel {

  /**
   * Allows the user to select a card in their hand.
   * @param x coordinate for the click in hand
   * @param y coordinate for the click in hand
   */
  void select(int x, int y);

  /**
   * Allows the user to select a space on the grid to play a card
   * @param x coordinate for the click on the grid
   * @param y coordinate for the click on the grid
   */
  void move(int x, int y);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();
}
