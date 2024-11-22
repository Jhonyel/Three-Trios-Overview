package cs3500.three.trios.player;

import cs3500.three.trios.examples.ThreeTriosModelExamples;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModelImpl;
import cs3500.three.trios.strategy.CornerMoveStrategy;
import cs3500.three.trios.strategy.MaxNumFlipsMoveStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A class to test that players `onTurn` methods are called when the turn changes.
 */
public abstract class TestPlayer {

  protected ObservableThreeTriosModel observableModel;
  protected ThreeTriosModel model;

  protected abstract Player createPlayer(ObservableThreeTriosModel model, PlayerColor playerColor);

  @Before
  public void setUp() {
    model = ThreeTriosModelExamples.create3x3ModelWith9CardCells();
    observableModel = new ObservableThreeTriosModelImpl(model);
  }

  @Test
  public void testGetPlayerColor() {
    Player redPlayer = createPlayer(observableModel, PlayerColor.RED);
    Player bluePlayer = createPlayer(observableModel, PlayerColor.BLUE);
    Assert.assertEquals(PlayerColor.RED, redPlayer.getPlayerColor());
    Assert.assertEquals(PlayerColor.BLUE, bluePlayer.getPlayerColor());
  }

  @Test
  public void testConstructionWithNullArgsThrows() {
    Assert.assertThrows(
        IllegalArgumentException.class,
        () -> createPlayer(null, PlayerColor.RED)
    );

    Assert.assertThrows(
        IllegalArgumentException.class,
        () -> createPlayer(observableModel, null)
    );
  }

  @Test
  public void testComputerPlayerConstructionWithNullStrategyThrows() {
    Assert.assertThrows(
        IllegalArgumentException.class,
        () -> new ComputerPlayer(observableModel, PlayerColor.RED, null)
    );
  }

  /**
   * A class to test the human player.
   */
  public static class TestHumanPlayer extends TestPlayer {
    @Override
    protected Player createPlayer(ObservableThreeTriosModel model, PlayerColor playerColor) {
      return new HumanPlayer(model, playerColor);
    }
  }

  /**
   * A class to test the computer player with a corner move strategy.
   */
  public static class TestCornerMovePlayer extends TestPlayer {
    @Override
    protected Player createPlayer(ObservableThreeTriosModel model, PlayerColor playerColor) {
      return new ComputerPlayer(model, playerColor, new CornerMoveStrategy());
    }
  }

  /**
   * A class to test the computer player with a max num flips strategy.
   */
  public static class TestMaxNumFlipsPlayer extends TestPlayer {
    @Override
    protected Player createPlayer(ObservableThreeTriosModel model, PlayerColor playerColor) {
      return new ComputerPlayer(model, playerColor, new MaxNumFlipsMoveStrategy());
    }
  }

  @Test
  public void testRedOnTurnCalledAfterStartingGame() {
    StringBuilder log = new StringBuilder();
    new LoggingPlayer(observableModel, PlayerColor.RED, log);
    new HumanPlayer(observableModel, PlayerColor.BLUE);
    observableModel.startGame();
    Assert.assertEquals("Player RED's turn\n", log.toString());
  }

  @Test
  public void testBlueOnTurnCalledAfterRedPlays() {
    StringBuilder log = new StringBuilder();
    new LoggingPlayer(observableModel, PlayerColor.RED, log);
    new LoggingPlayer(observableModel, PlayerColor.BLUE, log);
    observableModel.startGame();
    observableModel.playCardAt(0, 0, 0);
    Assert.assertEquals("Player RED's turn\nPlayer BLUE's turn\n", log.toString());
  }
}
