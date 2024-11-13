package cs3500.three.trios.view;

import cs3500.three.trios.controller.Features;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * A visual representation of the hand of a single player in a game of three trios.
 */
public class HandPanelImpl extends JPanel implements HandPanel {

  private final PlayerColor playerColor;
  private final int initialNumCards;
  private final ReadOnlyThreeTriosModel model;

  /**
   * The index of the currently selected card. -1 if no card is selected.
   */
  private int selectedCardIndex;
  private Features features;

  private static final int LOGICAL_CARD_SIZE = 100;

  /**
   * Creates a new HandPanelImpl to visualize the hand of the given player in the given model.
   *
   * @throws IllegalArgumentException if any argument is null.
   */
  public HandPanelImpl(ReadOnlyThreeTriosModel model, PlayerColor playerColor) {
    this.model = Requirements.requireNonNull(model);
    this.playerColor = Requirements.requireNonNull(playerColor);
    initialNumCards = getCards().size();
    selectedCardIndex = -1;

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        AffineTransform physicalToLogical = getPhysicalToLogicalTransform();
        Point2D transformedPoint = physicalToLogical.transform(e.getPoint(), null);
        int cardIndex = (int) Math.floor(transformedPoint.getY()) / LOGICAL_CARD_SIZE;
        boolean isCardIndexValid = cardIndex < getCards().size();
        if (isCardIndexValid) {
          features.onHandCardClicked(playerColor, cardIndex);
        } else {
          features.onEmptySpaceClicked();
        }
      }
    });
  }

  @Override
  public void addFeatures(Features features) {
    this.features = Requirements.requireNonNull(features);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    g2d.transform(getLogicalToPhysicalTransform());

    List<PlayerCard> cards = getCards();
    for (int cardIndex = 0; cardIndex < cards.size(); cardIndex++) {
      Rectangle bounds = new Rectangle(
          0,
          cardIndex * LOGICAL_CARD_SIZE,
          LOGICAL_CARD_SIZE,
          LOGICAL_CARD_SIZE
      );
      boolean isSelected = cardIndex == selectedCardIndex;
      Painter.drawCard(g2d, bounds, cards.get(cardIndex), isSelected);
    }
  }

  /**
   * Returns a list of the cards in this player's hand.
   */
  private List<PlayerCard> getCards() {
    return model.getHand(playerColor);
  }

  @Override
  public void toggleSelection(int cardIndex) {
    selectedCardIndex = cardIndex == selectedCardIndex ? -1 : cardIndex;
  }

  @Override
  public void clearSelection() {
    selectedCardIndex = -1;
  }

  /**
   * Returns a transform that converts from the logical coordinate system to the physical coordinate
   * system where the logical width is `LOGICAL_CARD_SIZE` and the logical height is
   * `initialNumCards * LOGICAL_CARD_SIZE`.
   */
  private AffineTransform getLogicalToPhysicalTransform() {
    double logicalWidth = LOGICAL_CARD_SIZE;
    double logicalHeight = initialNumCards * LOGICAL_CARD_SIZE;
    double scaleX = getWidth() / logicalWidth;
    double scaleY = getHeight() / logicalHeight;

    AffineTransform transform = new AffineTransform();
    transform.scale(scaleX, scaleY);
    return transform;
  }

  /**
   * Returns a transform that converts from the physical coordinate system to the logical coordinate
   * system with a logical width of `LOGICAL_CARD_SIZE` and a logical height of `initialNumCards *
   * LOGICAL_CARD_SIZE`.
   */
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
