package cs3500.three.trios.model;

import static org.junit.Assert.assertThrows;

import cs3500.three.trios.Examples;
import cs3500.three.trios.player.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

/**
 * A class to test the observable three trios model.
 */
public class TestObservableThreeTriosModel {

  private ObservableThreeTriosModel notStartedModel3x3;
  private ObservableThreeTriosModel startedModel3x3HumanPlayers;

  private ObservableThreeTriosModel createNotStartedModel(ThreeTriosModel model) {
    return new ObservableThreeTriosModelImpl(model);
  }

  private ObservableThreeTriosModel createStartedModelWithHumanPlayers(ThreeTriosModel model) {
    ObservableThreeTriosModel observableModel = new ObservableThreeTriosModelImpl(model);

    // note: the player constructor will register the players to listen to the model
    new HumanPlayer(observableModel, PlayerColor.RED);
    new HumanPlayer(observableModel, PlayerColor.BLUE);

    return observableModel;
  }

  @Before
  public void setUp() {
    ThreeTriosModel model3x3 = Examples.create3x3ModelWith9CardCells();
    notStartedModel3x3 = createNotStartedModel(model3x3);
    startedModel3x3HumanPlayers = createStartedModelWithHumanPlayers(model3x3);
  }

  @Test
  public void testConstructionThrowsWithNullModel() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ObservableThreeTriosModelImpl(null)
    );
  }

  @Test
  public void testStartGameThrowsWithoutRegisteringTwoPlayers() {
    assertThrows(
        IllegalStateException.class,
        () -> notStartedModel3x3.startGame()
    );
    new HumanPlayer(notStartedModel3x3, PlayerColor.RED);
    assertThrows(
        IllegalStateException.class,
        () -> notStartedModel3x3.startGame()
    );
  }

  @Test
  public void testStartGameThrowsWhenGameIsAlreadyStarted() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          notStartedModel3x3.startGame();
          notStartedModel3x3.startGame();
        }
    );
  }

  @Test
  public void testRegisteringNullPlayerThrows() {
    assertThrows(
        IllegalArgumentException.class,
        () -> startedModel3x3HumanPlayers.registerPlayer(null)
    );
  }

  @Test
  public void testRegisteringExistingPlayerThrows() {
    assertThrows(
        IllegalStateException.class,
        () -> {
          new HumanPlayer(notStartedModel3x3, PlayerColor.RED);
          new HumanPlayer(notStartedModel3x3, PlayerColor.RED);
        }
    );
    assertThrows(
        IllegalStateException.class,
        () -> {
          new HumanPlayer(notStartedModel3x3, PlayerColor.RED);
          new HumanPlayer(notStartedModel3x3, PlayerColor.BLUE);
          new HumanPlayer(notStartedModel3x3, PlayerColor.RED);
        }
    );
  }

  @Test
  public void testPlayCardAtThrowsWithoutStartGame() {
    assertThrows(
        IllegalStateException.class,
        () -> notStartedModel3x3.playCardAt(0, 0, 0)
    );
  }
}
