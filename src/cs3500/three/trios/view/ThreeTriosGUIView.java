package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;
import cs3500.three.trios.model.PlayerColor;

/**
 * Interface that represents the actions that the GUI view will have.
 */
public interface ThreeTriosGUIView {

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Transmit an error message to the view, in case the command could not be processed correctly.
   *
   * @param error the error message
   */
  void showErrorMessage(String error);

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  // Additionally, your view should (temporarily) print a message (using System.out) containing
  // the index of the card that was clicked on as well as which player owns that hand.
  void onCardClicked(PlayerColor player, int cardIndex);

  // a user should be able to click on a cell in the grid and the view should (temporarily) print
  // a message (using System.out) containing the coordinates of the cell that was clicked on, in
  // whatever coordinate system you used in your model. Note: this is not the same thing as the
  // physical mouse coordinates of the mouse event!
  void onCellClicked(int rowIndex, int colIndex);

  void addFeatures(Features features);
}
