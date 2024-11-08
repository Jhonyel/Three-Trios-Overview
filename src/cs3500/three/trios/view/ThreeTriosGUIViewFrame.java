package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.PlayerCard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;
import javax.swing.JFrame;

public class ThreeTriosGUIViewFrame extends JFrame implements ThreeTriosGUIView {

  private final ReadOnlyThreeTriosModel model;

  public ThreeTriosGUIViewFrame(ReadOnlyThreeTriosModel model) {
    super();

    this.model = model;

    setLayout(new GridBagLayout());

    List<PlayerCard> redHand = model.getHand(PlayerColor.RED);
    HandPanelImpl redHandPanel = new HandPanelImpl(redHand);
    this.add(redHandPanel, getLeftHandPanelConstraints());

    GridPanelImpl gridPanel = new GridPanelImpl(model);
    this.add(gridPanel, getGridPanelConstraints());

    List<PlayerCard> blueHand = model.getHand(PlayerColor.BLUE);
    HandPanelImpl blueHandPanel = new HandPanelImpl(blueHand);
    this.add(blueHandPanel, getRightHandPanelConstraints());

    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setMinimumSize(new Dimension(800, 600));
    this.setSize(new Dimension(800, 600));
    this.setLocationRelativeTo(null);
  }

  private GridBagConstraints getDefaultConstraints() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    return gbc;
  }

  private GridBagConstraints getLeftHandPanelConstraints() {
    GridBagConstraints gbc = getDefaultConstraints();
    gbc.gridx = 0;
    gbc.weightx = 0.20;
    return gbc;
  }

  private GridBagConstraints getGridPanelConstraints() {
    GridBagConstraints gbc = getDefaultConstraints();
    gbc.gridx = 1;
    gbc.weightx = 0.6;
    return gbc;
  }

  private GridBagConstraints getRightHandPanelConstraints() {
    GridBagConstraints gbc = getDefaultConstraints();
    gbc.gridx = 2;
    gbc.weightx = 0.20;
    return gbc;
  }

  @Override
  public void refresh() {

  }

  @Override
  public void showErrorMessage(String error) {

  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void onCardClicked(PlayerColor player, int cardIndex) {

  }

  @Override
  public void onCellClicked(int rowIndex, int colIndex) {

  }

  @Override
  public void addFeatures(Features features) {

  }
}
