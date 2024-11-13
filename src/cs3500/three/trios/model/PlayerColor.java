package cs3500.three.trios.model;

import java.awt.Color;

/**
 * An enum class to represent the colors of the players in the game.
 */
public enum PlayerColor {
  RED(Color.RED), BLUE(Color.BLUE);

  private final Color color;

  PlayerColor(Color color) {
    this.color = color;
  }

  /**
   * Returns the visual color associated with this player color.
   */
  public Color getColor() {
    return color;
  }
}
