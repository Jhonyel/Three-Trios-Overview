package cs3500.three.trios;

import static cs3500.three.trios.model.card.AttackValue.EIGHT;
import static cs3500.three.trios.model.card.AttackValue.FIVE;
import static cs3500.three.trios.model.card.AttackValue.FOUR;
import static cs3500.three.trios.model.card.AttackValue.NINE;
import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.SEVEN;
import static cs3500.three.trios.model.card.AttackValue.SIX;
import static cs3500.three.trios.model.card.AttackValue.TEN;
import static cs3500.three.trios.model.card.AttackValue.THREE;
import static cs3500.three.trios.model.card.AttackValue.TWO;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.view.ThreeTriosGUIViewFrame;
import java.util.List;

public class ThreeTrios {

  public static void main(String[] args) {
    Cell X = Cell.createHoleCell();
    Cell C = Cell.createEmptyCardCell();
    PlayerCard card = new PlayerCard("", ONE, ONE, ONE, ONE, PlayerColor.RED);
    Cell R = Cell.createOccupiedCardCell(card);

    Cell[][] grid = {
        {C, C, X},
        {C, C, C},
        {X, R, C}
    };
    List<Card> redHand = List.of(
        new CardImpl("BlazingTiger", TEN, SEVEN, THREE, FIVE),
        new CardImpl("FrozenWolf", NINE, ONE, EIGHT, SIX),
        new CardImpl("SilentEagle", FIVE, FOUR, TEN, SEVEN),
        new CardImpl("SwiftLion", SIX, THREE, NINE, TWO)
    );
    List<Card> blueHand = List.of(
        new CardImpl("MightyRhino", FOUR, EIGHT, SEVEN, ONE),
        new CardImpl("GoldenFalcon", TEN, FIVE, TWO, NINE),
        new CardImpl("ShadowBear", TWO, TEN, SIX, FOUR),
        new CardImpl("CunningFox", EIGHT, SEVEN, FOUR, THREE)
    );

    ThreeTriosModelImpl model = ThreeTriosModelImpl.createGameInProgress(
        grid, redHand, blueHand);
    ThreeTriosGUIViewFrame threeTriosGUIViewFrame = new ThreeTriosGUIViewFrame(model);
    threeTriosGUIViewFrame.makeVisible();
  }
}
