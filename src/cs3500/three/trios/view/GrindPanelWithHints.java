package cs3500.three.trios.view;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * An extension of GridPanelImpl that renders hints when the hint mode is enabled.
 */
public class GrindPanelWithHints extends GridPanelImpl {

  /**
   * Creates a new GridPanelImpl to visualize the grid of the given model.
   *
   * @throws IllegalArgumentException if the model is null.
   */
  public GrindPanelWithHints(ReadOnlyThreeTriosModel model, ThreeTriosGUIView view) {
    super(model, view);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    boolean areNumFlipsHintsEnabled = view.getHintModeEnabled();

    for (int rowIndex = 0; rowIndex < model.getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < model.getWidth(); colIndex++) {
        int cellX = colIndex * LOGICAL_CELL_SIZE;
        int cellY = rowIndex * LOGICAL_CELL_SIZE;
        Cell cell = model.getCellAt(rowIndex, colIndex);

        if (areNumFlipsHintsEnabled && !cell.isHole() && view.hasSelectedCard()) {
          int selectedCardIndex = view.getSelectedCardIndex();
          int numFlips = model.getNumFlipsAt(rowIndex, colIndex, selectedCardIndex);
          int padding = 5;
          int numFlipsTextX = cellX + padding;
          int numFlipsTextY = cellY + LOGICAL_CELL_SIZE - padding;
          g2d.setFont(new Font("arial", Font.PLAIN, 20));
          g2d.drawString(String.valueOf(numFlips), numFlipsTextX, numFlipsTextY);
        }
      }
    }

  }
}
