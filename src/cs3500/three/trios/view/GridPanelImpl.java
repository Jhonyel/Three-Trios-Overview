package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;
import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 * A visual representation of a grid in a game of three trios.
 */
public class GridPanelImpl extends JPanel implements GridPanel {

  protected final ReadOnlyThreeTriosModel model;
  protected final ThreeTriosGUIView view;
  protected static final int LOGICAL_CELL_SIZE = 100;
  private Features features;

  /**
   * Creates a new GridPanelImpl to visualize the grid of the given model.
   *
   * @throws IllegalArgumentException if the model is null.
   */
  public GridPanelImpl(ReadOnlyThreeTriosModel model, ThreeTriosGUIView view) {
    this.model = Requirements.requireNonNull(model);
    this.view = Requirements.requireNonNull(view);

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        AffineTransform physicalToLogical = getPhysicalToLogicalTransform();
        Point2D transformedPoint = physicalToLogical.transform(e.getPoint(), null);
        int rowIndex = (int) Math.floor(transformedPoint.getY()) / LOGICAL_CELL_SIZE;
        int colIndex = (int) Math.floor(transformedPoint.getX()) / LOGICAL_CELL_SIZE;
        features.onCellClicked(rowIndex, colIndex);
      }
    });
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.transform(getLogicalToPhysicalTransform());

    for (int rowIndex = 0; rowIndex < model.getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < model.getWidth(); colIndex++) {
        drawCell(colIndex, rowIndex, g2d);
      }
    }
  }

  /**
   * Draws the cell at the given location on the grid.
   */
  private void drawCell(int colIndex, int rowIndex, Graphics2D g2d) {
    int cellX = colIndex * LOGICAL_CELL_SIZE;
    int cellY = rowIndex * LOGICAL_CELL_SIZE;
    Rectangle bounds = new Rectangle(cellX, cellY, LOGICAL_CELL_SIZE, LOGICAL_CELL_SIZE);
    Outline outline = new Outline(Color.BLACK, 1);

    Cell cell = model.getCellAt(rowIndex, colIndex);
    if (cell.isOccupiedCardCell()) {
      PlayerCard card = cell.getCard();
      Painter.drawCard(g2d, bounds, card, false);
      return;
    }
    Color color = getCellColor(cell);
    Painter.fillAndOutlineRectangle(g2d, bounds, color, outline);
  }

  /**
   * Returns the color to use for the given cell. Empty cards are yellow, holes are gray, and
   * occupied cards are the color of the card.
   */
  private Color getCellColor(Cell cell) {
    if (cell.isEmptyCardCell()) {
      return Color.YELLOW;
    }
    if (cell.isHole()) {
      return Color.GRAY;
    }
    PlayerColor playerColor = cell.getPlayerColor();
    return playerColor.getColor();
  }

  /**
   * Returns a transform that converts from the physical coordinate system to the logical coordinate
   * system with a logical width of `model.getWidth() * LOGICAL_CELL_SIZE` and a logical height of
   * `model.getHeight() * LOGICAL_CELL_SIZE`.
   */
  private AffineTransform getPhysicalToLogicalTransform() {
    double logicalWidth = model.getWidth() * LOGICAL_CELL_SIZE;
    double logicalHeight = model.getHeight() * LOGICAL_CELL_SIZE;
    double scaleX = logicalWidth / getWidth();
    double scaleY = logicalHeight / getHeight();

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }

  /**
   * Returns a transform that converts from the logical coordinate system to the physical coordinate
   * system where the logical width is `model.getWidth() * LOGICAL_CELL_SIZE` and the logical height
   * is `model.getHeight() * LOGICAL_CELL_SIZE`.
   */
  private AffineTransform getLogicalToPhysicalTransform() {
    double logicalWidth = model.getWidth() * LOGICAL_CELL_SIZE;
    double logicalHeight = model.getHeight() * LOGICAL_CELL_SIZE;
    double scaleX = getWidth() / logicalWidth;
    double scaleY = getHeight() / logicalHeight;

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }

  @Override
  public void addFeatures(Features features) {
    this.features = Requirements.requireNonNull(features);
  }
}
