package cs3500.three.trios.view;

/**
 * An interface for displaying a visualization of the state of a game of three trios.
 */
public interface ThreeTriosView {

  /**
   * Renders the visualization of the game.
   *
   * @throws IllegalStateException if this view is unable to render a visualization
   */
  void render();
}
