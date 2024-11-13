package cs3500.three.trios.view;

import cs3500.three.trios.util.Requirements;
import java.awt.Color;

/**
 * A class representing an outline in our GUI. An outline consists of a color and a width.
 */
public class Outline {

  private final Color color;
  private final int width;

  /**
   * Creates a new outline with the given non-null color and non-negative width.
   *
   * @throws IllegalArgumentException if the given width is negative or the given color is null.
   */
  public Outline(Color color, int width) {
    if (width < 0) {
      throw new IllegalArgumentException("Outline width must be non-negative");
    }
    this.color = Requirements.requireNonNull(color);
    this.width = width;
  }

  /**
   * Returns the color of this outline.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Returns the width of this outline.
   */
  public int getWidth() {
    return width;
  }
}
