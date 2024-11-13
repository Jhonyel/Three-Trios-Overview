package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.util.Requirements;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 * The entire visual representation of a game of three trios. Contains a hand panel for the red
 * player on the left, a grid panel in the middle, and a hand panel for the blue player on the
 * right.
 */
public class ThreeTriosGUIViewFrame extends JFrame implements ThreeTriosGUIView {

  private final HandPanelImpl redHandPanel;
  private final HandPanelImpl blueHandPanel;
  private final GridPanelImpl gridPanel;

  /**
   * Creates a new ThreeTriosGUIViewFrame to visualize the given model. Places the frame in the
   * middle of the screen.
   *
   * @throws IllegalArgumentException if the model is null.
   */
  public ThreeTriosGUIViewFrame(ReadOnlyThreeTriosModel model) {
    Requirements.requireNonNull(model);

    setLayout(new GridBagLayout());

    redHandPanel = new HandPanelImpl(model, PlayerColor.RED);
    add(redHandPanel, getLeftHandPanelConstraints());

    gridPanel = new GridPanelImpl(model);
    add(gridPanel, getGridPanelConstraints());

    blueHandPanel = new HandPanelImpl(model, PlayerColor.BLUE);
    add(blueHandPanel, getRightHandPanelConstraints());

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(800, 600));
    setSize(new Dimension(800, 600));
    centerOnScreen();
  }

  /**
   * Centers this frame on the screen.
   */
  private void centerOnScreen() {
    setLocationRelativeTo(null);
  }

  /**
   * Returns a GridBagConstraints that specifies that a component should fill the available vertical
   * space.
   */
  private GridBagConstraints getFillVerticalSpaceConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    return gbc;
  }

  /**
   * Returns a GridBagConstraints that specifies that the left hand panel should fill the available
   * vertical space and the leftmost 20% of the available horizontal space.
   */
  private GridBagConstraints getLeftHandPanelConstraints() {
    GridBagConstraints gbc = getFillVerticalSpaceConstraints();
    gbc.gridx = 0;
    gbc.weightx = 0.20;
    return gbc;
  }

  /**
   * Returns a GridBagConstraints that specifies that the grid panel should fill the available
   * vertical space and the center 60% of the available horizontal space.
   */
  private GridBagConstraints getGridPanelConstraints() {
    GridBagConstraints gbc = getFillVerticalSpaceConstraints();
    gbc.gridx = 1;
    gbc.weightx = 0.6;
    return gbc;
  }

  /**
   * Returns a GridBagConstraints that specifies that the right hand panel should fill the available
   * vertical space and the rightmost 20% of the available horizontal space.
   */
  private GridBagConstraints getRightHandPanelConstraints() {
    GridBagConstraints gbc = getFillVerticalSpaceConstraints();
    gbc.gridx = 2;
    gbc.weightx = 0.20;
    return gbc;
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    Requirements.requireNonNull(features);
    redHandPanel.addFeatures(features);
    blueHandPanel.addFeatures(features);
    gridPanel.addFeatures(features);
  }

  @Override
  public void toggleSelection(PlayerColor player, int cardIndex) {
    Requirements.requireNonNull(player);

    HandPanel handPanel = player == PlayerColor.RED ? redHandPanel : blueHandPanel;
    handPanel.toggleSelection(cardIndex);
    refresh();
  }

  @Override
  public void clearSelection() {
    redHandPanel.clearSelection();
    blueHandPanel.clearSelection();
    refresh();
  }
}
