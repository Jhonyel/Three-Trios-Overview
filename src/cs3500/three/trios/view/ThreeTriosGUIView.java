package cs3500.three.trios.view;

import java.awt.event.ActionListener;

/**
 * Interface that represents the actions that the GUI view will have.
 */
public interface ThreeTriosGUIView {

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * Transmit an error message to the view, in case
   * the command could not be processed correctly.
   *
   * @param error the error message
   */
  void showErrorMessage(String error);

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();
}
