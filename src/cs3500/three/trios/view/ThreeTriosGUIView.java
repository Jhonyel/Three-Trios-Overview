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
   * Make the view visible to start the game session.
   */
  void makeVisible();

  /**
   * Adds the given features to the components in this view. Components will delegate to the
   * features for all events.
   *
   * @throws IllegalArgumentException if features is null.
   */
  void addFeatures(Features features);

  /**
   * Toggles the selection of the card at the given index in the given player's hand.
   *
   * @throws IndexOutOfBoundsException if the given index is invalid.
   * @throws IllegalArgumentException  if the given player is null.
   */
  void toggleSelection(PlayerColor playerColor, int cardIndex);

  /**
   * Deselects any card currently selected.
   */
  void clearSelection();

  /**
   * Displays the given message in a popup.
   */
  void displayMessage(String message);

  /**
   * Returns the index of the selected card in this view's player's hand.
   *
   * @throws IllegalStateException if no card is selected
   */
  int getSelectedCardIndex();

  /**
   * Returns true if a card in this view is selected, false otherwise.
   */
  boolean hasSelectedCard();

  /**
   * Returns true if hints are enabled in this view
   */
  boolean getHintModeEnabled();
}
