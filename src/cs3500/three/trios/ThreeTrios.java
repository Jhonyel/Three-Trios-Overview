package cs3500.three.trios;

import cs3500.three.trios.controller.ThreeTriosController;
import cs3500.three.trios.examples.ThreeTriosModelExamples;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModelImpl;
import cs3500.three.trios.player.Player;
import cs3500.three.trios.player.PlayerFactory;
import cs3500.three.trios.view.ThreeTriosGUIViewFrame;

/**
 * The class containing the main method to run a game of three trios.
 */
public class ThreeTrios {

  /**
   * The main method to run a game of three trios.
   */
  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException(
          "args must have two elements: redPlayerType, bluePlayerType, and GameType");
    }
    boolean hints = args[2].equals("HINTS");

    ThreeTriosModel model = ThreeTriosModelExamples.create3x3ModelWith9CardCells();
    ObservableThreeTriosModel observableModel = new ObservableThreeTriosModelImpl(model);
    ThreeTriosGUIViewFrame redView = new ThreeTriosGUIViewFrame(observableModel, PlayerColor.RED,
            hints);
    ThreeTriosGUIViewFrame blueView = new ThreeTriosGUIViewFrame(observableModel, PlayerColor.BLUE,
            hints);
    Player redPlayer = PlayerFactory.fromPlayerType(args[0], observableModel, PlayerColor.RED);
    Player bluePlayer = PlayerFactory.fromPlayerType(args[1], observableModel, PlayerColor.BLUE);
    ThreeTriosController redController = new ThreeTriosController(
        redView, observableModel, redPlayer
    );
    ThreeTriosController blueController = new ThreeTriosController(
        blueView, observableModel, bluePlayer
    );
    observableModel.startGame();
    redView.makeVisible();
    blueView.makeVisible();
  }
}
