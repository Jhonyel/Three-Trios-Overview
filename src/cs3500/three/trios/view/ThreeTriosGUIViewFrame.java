package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.util.Requirements;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The entire visual representation of a game of three trios. Contains a hand panel for the red
 * player on the left, a grid panel in the middle, and a hand panel for the blue player on the
 * right.
 */
public class ThreeTriosGUIViewFrame extends JFrame implements ThreeTriosGUIView {

  private final HandPanel redHandPanel;
  private final HandPanel blueHandPanel;
  private final GridPanel gridPanel;
  private final PlayerColor playerColor;

  /**
   * Creates a new ThreeTriosGUIViewFrame to visualize the given model. Places the frame in the
   * middle of the screen. The title of the frame will convey the player color of this frame and the
   * current player.
   *
   * @throws IllegalArgumentException if any argument is null.
   */
  public ThreeTriosGUIViewFrame(ReadOnlyThreeTriosModel model, PlayerColor playerColor) {
    Requirements.requireNonNull(model);
    Requirements.requireNonNull(playerColor);
    this.playerColor = playerColor;

    setTitle(String.format(
            "Player: %s | Current Player: %s",
            playerColor,
            model.getCurrentPlayerColor()
        )
    );
    setLayout(new GridBagLayout());

    redHandPanel = new HandPanelImpl(model, PlayerColor.RED);
    add((Component) redHandPanel, getLeftHandPanelConstraints());

    gridPanel = new GridPanelImpl(model, this);
    add((Component) gridPanel, getGridPanelConstraints());

    blueHandPanel = new HandPanelImpl(model, PlayerColor.BLUE);
    add((Component) blueHandPanel, getRightHandPanelConstraints());

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(600, 400));
    setSize(new Dimension(600, 400));
    if (playerColor == PlayerColor.RED) {
      centerOnScreen();
    }
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
  public void toggleSelection(PlayerColor playerColor, int cardIndex) {
    Requirements.requireNonNull(playerColor);

    HandPanel handPanel = playerColor == PlayerColor.RED ? redHandPanel : blueHandPanel;
    handPanel.toggleSelection(cardIndex);
    refresh();
  }

  @Override
  public void clearSelection() {
    redHandPanel.clearSelection();
    blueHandPanel.clearSelection();
    refresh();
  }

  @Override
  public void displayMessage(String message) {
    String title = String.format("Player: %s", playerColor);
    int messageType = JOptionPane.INFORMATION_MESSAGE;

    JOptionPane.showMessageDialog(
        null, // "if the parentComponent [is null], a default Frame is used"
        message,
        title,
        messageType
    );
  }

  @Override
  public int getSelectedCardIndex() {
    HandPanel handPanel = playerColor == PlayerColor.RED ? redHandPanel : blueHandPanel;
    return handPanel.getSelectedCardIndex();
  }

  @Override
  public boolean hasSelectedCard() {
    try {
      getSelectedCardIndex();
      return true;

    } catch (IllegalStateException ignored) {
      return false;
    }
  }
}
