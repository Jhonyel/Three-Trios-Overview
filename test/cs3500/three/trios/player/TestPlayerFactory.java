package cs3500.three.trios.player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.three.trios.examples.ThreeTriosModelExamples;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModelImpl;

/**
 * A class for testing the player factory.
 */
public class TestPlayerFactory {

  private ObservableThreeTriosModel observable;

  @Before
  public void setUp() {
    ThreeTriosModel model = ThreeTriosModelExamples.create5x7ModelWith15CardCells();
    observable = new ObservableThreeTriosModelImpl(model);
  }

  @Test
  public void testPlayerFactoryThrowsWithString() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> PlayerFactory.fromPlayerType("HUMAN", null, null));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> PlayerFactory.fromPlayerType("", observable, PlayerColor.RED));
  }

  @Test
  public void testPlayerFactoryThrowsWithPlayerType() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> PlayerFactory.fromPlayerType(PlayerType.HUMAN, observable, null));
    Assert.assertThrows(IllegalArgumentException.class,
            () -> PlayerFactory.fromPlayerType(PlayerType.HUMAN, null, PlayerColor.RED));
  }
}
