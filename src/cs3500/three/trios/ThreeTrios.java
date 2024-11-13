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

import cs3500.three.trios.controller.ThreeTriosController;
import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.view.ThreeTriosGUIViewFrame;
import java.util.List;

/**
 * The class containing the main method to run a game of three trios.
 */
public class ThreeTrios {

  /**
   * The main method to run a game of three trios.
   */
  public static void main(String[] args) {
    Cell holeCell = Cell.createHoleCell();
    Cell emptyCell = Cell.createEmptyCardCell();

    ThreeTriosModelImpl model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {emptyCell, emptyCell, holeCell},
            {emptyCell, emptyCell, emptyCell},
            {holeCell, emptyCell, emptyCell}
        },
        List.of(
            new CardImpl("BlazingTiger", TEN, SEVEN, THREE, FIVE),
            new CardImpl("FrozenWolf", NINE, ONE, EIGHT, SIX),
            new CardImpl("SilentEagle", FIVE, FOUR, TEN, SEVEN),
            new CardImpl("SwiftLion", SIX, THREE, NINE, TWO)
        ),
        List.of(
            new CardImpl("MightyRhino", FOUR, EIGHT, SEVEN, ONE),
            new CardImpl("GoldenFalcon", TEN, FIVE, TWO, NINE),
            new CardImpl("ShadowBear", TWO, TEN, SIX, FOUR),
            new CardImpl("CunningFox", EIGHT, SEVEN, FOUR, THREE)
        )
    );
    ThreeTriosGUIViewFrame view = new ThreeTriosGUIViewFrame(model);
    ThreeTriosController controller = new ThreeTriosController(view, model);
    view.addFeatures(controller);
    view.makeVisible();

    model.playCardAt(0, 0, 0);
    // model.playCardAt(0, 1, 0);
    // model.playCardAt(2, 2, 0);
  }
}
