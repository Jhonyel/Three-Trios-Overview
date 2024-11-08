package cs3500.three.trios.view;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.card.PlayerCard;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;
import javax.swing.JPanel;

public class HandPanelImpl extends JPanel {

  private final Color color;
  private final List<PlayerCard> cards;
  private final int initialNumCards;

  private static final int LOGICAL_CARD_SIZE = 100;

  public HandPanelImpl(List<PlayerCard> cards) {
    this.cards = cards;
    this.color = cards.get(0).getPlayerColor() == PlayerColor.RED ? Color.RED : Color.BLUE;
    initialNumCards = cards.size();

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        AffineTransform transform = getPhysicalToLogicalTransform();
        Point2D transformedPoint = transform.transform(e.getPoint(), null);
        int cardIndex = (int) Math.floor(transformedPoint.getY()) / LOGICAL_CARD_SIZE;
        System.out.printf("cardIndex = %d\n", cardIndex);
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    AffineTransform transform = getLogicalToPhysicalTransform();
    g2d.transform(transform);

    for (int cardIndex = 0; cardIndex < cards.size(); cardIndex++) {
      drawBackground(g2d, cardIndex);
      drawText(cardIndex, g2d);
    }
  }

  private void drawText(int cardIndex, Graphics2D g2d) {
    PlayerCard card = cards.get(cardIndex);
    g2d.setColor(Color.BLACK);
    Font font = new Font("arial", Font.PLAIN, 25);
    g2d.setFont(font);

    g2d.drawString(
        card.getNorthAttackValue().toString(),
        0.4f * LOGICAL_CARD_SIZE,
        (cardIndex + 0.25f) * LOGICAL_CARD_SIZE
    );
    g2d.drawString(
        card.getSouthAttackValue().toString(),
        0.4f * LOGICAL_CARD_SIZE,
        (cardIndex + 0.9f) * LOGICAL_CARD_SIZE
    );
    g2d.drawString(
        card.getWestAttackValue().toString(),
        0.1f * LOGICAL_CARD_SIZE,
        (cardIndex + 0.55f) * LOGICAL_CARD_SIZE
    );
    g2d.drawString(
        card.getEastAttackValue().toString(),
        0.75f * LOGICAL_CARD_SIZE,
        (cardIndex + 0.55f) * LOGICAL_CARD_SIZE
    );
  }

  private void drawBackground(Graphics2D g2d, int cardIndex) {
    g2d.setColor(color);
    g2d.fillRect(
        0,
        cardIndex * LOGICAL_CARD_SIZE,
        LOGICAL_CARD_SIZE,
        LOGICAL_CARD_SIZE
    );
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(1));
    g2d.drawRect(
        0,
        cardIndex * LOGICAL_CARD_SIZE,
        LOGICAL_CARD_SIZE,
        LOGICAL_CARD_SIZE
    );
  }

  private AffineTransform getLogicalToPhysicalTransform() {

    double logicalWidth = LOGICAL_CARD_SIZE;
    double logicalHeight = initialNumCards * LOGICAL_CARD_SIZE;
    double scaleX = getWidth() / logicalWidth;
    double scaleY = getHeight() / logicalHeight;

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }

  private AffineTransform getPhysicalToLogicalTransform() {

    double logicalWidth = LOGICAL_CARD_SIZE;
    double logicalHeight = initialNumCards * LOGICAL_CARD_SIZE;
    double scaleX = logicalWidth / getWidth();
    double scaleY = logicalHeight / getHeight();

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }
}
