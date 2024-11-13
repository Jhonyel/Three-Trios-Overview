package cs3500.three.trios.view;

import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * A class containing methods for painting components in our GUI.
 */
public class Painter {


  /**
   * Paints the given card in the given bounds on the given g2d. It is imperative that g2d's
   * coordinate system is the same as the coordinate system used in the bounds. Presently, in
   * practice, g2d's coordinate system is converted to a logical coordinate system and the bounds
   * are defined in terms of those logical coordinates. If `isSelected` is true, then the card will
   * have a thicker outline.
   *
   * @throws IllegalArgumentException if any argument is null.
   */
  public static void drawCard(
      Graphics2D g2d, Rectangle bounds, PlayerCard card, boolean isSelected
  ) {
    Requirements.requireNonNull(g2d);
    Requirements.requireNonNull(bounds);
    Requirements.requireNonNull(card);

    drawCardBackground(g2d, bounds, card, isSelected);
    drawCardText(g2d, bounds, card);
  }

  /**
   * Draws the background of the given card in the given bounds on the given g2d. If `isSelected` is
   * true, then the card will have a thicker outline.
   */
  private static void drawCardBackground(
      Graphics2D g2d, Rectangle bounds, PlayerCard card, boolean isSelected
  ) {
    Outline outline = new Outline(Color.BLACK, isSelected ? 10 : 1);
    Color fillColor = card.getPlayerColor().getColor();
    fillAndOutlineRectangle(g2d, bounds, fillColor, outline);
  }

  /**
   * Draws the text of the given card in the given bounds on the given g2d.
   */
  private static void drawCardText(Graphics2D g2d, Rectangle bounds, PlayerCard card) {
    String northAttackValueString = card.getNorthAttackValue().toString();
    String southAttackValueString = card.getSouthAttackValue().toString();
    String eastAttackValueString = card.getEastAttackValue().toString();
    String westAttackValueString = card.getWestAttackValue().toString();

    int x = bounds.x;
    int y = bounds.y;
    int width = bounds.width;
    int height = bounds.height;

    g2d.setColor(Color.BLACK);
    Font font = new Font("arial", Font.PLAIN, 25);
    g2d.setFont(font);

    g2d.drawString(northAttackValueString, x + 0.4f * width, y + 0.25f * height);
    g2d.drawString(southAttackValueString, x + 0.4f * width, y + 0.9f * height);
    g2d.drawString(westAttackValueString, x + 0.1f * width, y + 0.55f * height);
    g2d.drawString(eastAttackValueString, x + 0.75f * width, y + 0.55f * height);
  }

  /**
   * Fills the given bounds with the given fill color on the given g2d. Outlines the given bounds
   * with the given outline on the given g2d.
   *
   * @throws IllegalArgumentException if any argument is null.
   */
  public static void fillAndOutlineRectangle(
      Graphics2D g2d,
      Rectangle bounds,
      Color fillColor,
      Outline outline
  ) {
    Requirements.requireNonNull(g2d);
    Requirements.requireNonNull(bounds);
    Requirements.requireNonNull(fillColor);
    Requirements.requireNonNull(outline);

    g2d.setColor(fillColor);
    g2d.fill(bounds);

    g2d.setStroke(new BasicStroke(outline.getWidth()));
    g2d.setColor(outline.getColor());
    g2d.draw(bounds);
  }
}
