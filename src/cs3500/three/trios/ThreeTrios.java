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
import cs3500.three.trios.model.ObservableThreeTriosModel;
import cs3500.three.trios.model.ObservableThreeTriosModelImpl;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.player.HumanPlayer;
import cs3500.three.trios.player.Player;
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

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
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
    ObservableThreeTriosModel observableModel = new ObservableThreeTriosModelImpl(model);
    ThreeTriosGUIViewFrame redView = new ThreeTriosGUIViewFrame(model, PlayerColor.RED);
    ThreeTriosGUIViewFrame blueView = new ThreeTriosGUIViewFrame(model, PlayerColor.BLUE);
    Player redPlayer = new HumanPlayer(observableModel, PlayerColor.RED);
    Player bluePlayer = new HumanPlayer(observableModel, PlayerColor.BLUE);
    ThreeTriosController redController = new ThreeTriosController(
        redView, observableModel, redPlayer
    );
    ThreeTriosController blueController = new ThreeTriosController(
        blueView, observableModel, bluePlayer
    );
    redView.addFeatures(redController);
    blueView.addFeatures(blueController);
    redView.makeVisible();
    blueView.makeVisible();
    observableModel.startGame();

    //model.playCardAt(0, 0, 0);
  }
}
