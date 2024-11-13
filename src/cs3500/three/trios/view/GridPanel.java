package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;

/**
 * A visual representation of a grid in a game of three trios.
 */
public interface GridPanel {

  /**
   * Adds the given features interface to this grid panel. This grid panel will thereafter delegate
   * to the features interface on cell clicks.
   *
   * @throws IllegalArgumentException if features is null.
   */
  void addFeatures(Features features);
}
