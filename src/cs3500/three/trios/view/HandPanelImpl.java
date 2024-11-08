package cs3500.three.trios.view;

import cs3500.three.trios.model.card.Card;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

public class HandPanelImpl extends JPanel {

  private final int initialNumCards;

  public HandPanelImpl(List<? extends Card> cards) {
    initialNumCards = cards.size();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;



  }
}
