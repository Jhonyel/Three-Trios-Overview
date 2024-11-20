package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;

/**
 * A visual representation of the hand of a single player in a game of three trios.
 */
public interface HandPanel {

  /**
   * Adds the given features interface to this hand panel. This hand panel will thereafter delegate
   * to the features interface on card clicks and empty space clicks.
   *
   * @throws IllegalArgumentException if features is null.
   */
  void addFeatures(Features features);

  /**
   * Toggles the selection of the card at the given index.
   *
   * @throws IndexOutOfBoundsException if the given index is invalid.
   */
  void toggleSelection(int cardIndex);

  /**
   * Deselects any card currently selected.
   */
  void clearSelection();

  /**
   * Returns the index of the selected card.
   *
   * @throws IllegalStateException if no card is selected
   */
  int getSelectedCardIndex();
}
