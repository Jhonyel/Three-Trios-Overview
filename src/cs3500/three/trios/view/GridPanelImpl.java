package cs3500.three.trios.view;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

public class GridPanelImpl extends JPanel {

  private final ReadOnlyThreeTriosModel model;
  private static final int LOGICAL_CELL_SIZE = 100;

  public GridPanelImpl(ReadOnlyThreeTriosModel model) {
    this.model = model;

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        AffineTransform transform = getPhysicalToLogicalTransform();
        Point2D transformedPoint = transform.transform(e.getPoint(), null);
        int rowIndex = (int) Math.floor(transformedPoint.getY()) / LOGICAL_CELL_SIZE;
        int colIndex = (int) Math.floor(transformedPoint.getX()) / LOGICAL_CELL_SIZE;
        System.out.printf("(rowIndex, colIndex) = (%d, %d)\n", rowIndex, colIndex);
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
        Cell cell = model.getCellAt(rowIndex, colIndex);
        Color color = getCellColor(cell);

        g2d.setColor(color);
        g2d.fillRect(
            colIndex * LOGICAL_CELL_SIZE,
            rowIndex * LOGICAL_CELL_SIZE,
            LOGICAL_CELL_SIZE,
            LOGICAL_CELL_SIZE
        );

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawRect(
            colIndex * LOGICAL_CELL_SIZE,
            rowIndex * LOGICAL_CELL_SIZE,
            LOGICAL_CELL_SIZE,
            LOGICAL_CELL_SIZE
        );
      }
    }
  }

  private Color getCellColor(Cell cell) {
    if (cell.isEmptyCardCell()) {
      return Color.YELLOW;
    }
    if (cell.isHole()) {
      return Color.GRAY;
    }
    PlayerColor playerColor = cell.getPlayerColor();
    return playerColor == PlayerColor.RED ? Color.RED : Color.BLUE;
  }

  private AffineTransform getPhysicalToLogicalTransform() {
    double logicalWidth = model.getWidth() * LOGICAL_CELL_SIZE;
    double logicalHeight = model.getHeight() * LOGICAL_CELL_SIZE;
    double scaleX = logicalWidth / getWidth();
    double scaleY = logicalHeight / getHeight();

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }

  private AffineTransform getLogicalToPhysicalTransform() {
    double logicalWidth = model.getWidth() * LOGICAL_CELL_SIZE;
    double logicalHeight = model.getHeight() * LOGICAL_CELL_SIZE;
    double scaleX = getWidth() / logicalWidth;
    double scaleY = getHeight() / logicalHeight;

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }
}
