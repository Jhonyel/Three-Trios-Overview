package cs3500.three.trios.player;

import static org.junit.Assert.assertThrows;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.PlayerCard;
import java.util.List;
import org.junit.Test;

/**
 * A behavior that is common to both player strategies is that they throw an exception if the
 * getMoves method is called when the supplied model is over. We will test that common behavior in
 * this class.
 */
public abstract class TestMoveStrategy {

  protected abstract MoveStrategy createMoveStrategy();

  @Test
  public void testGetMovesThrowsWhenGameIsOver() {
    MoveStrategy strategy = createMoveStrategy();
    Cell redCell = Cell.createOccupiedCardCell(new PlayerCard("name 1 1 1 1", PlayerColor.RED));
    ReadOnlyThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {redCell, redCell, redCell},
            {redCell, redCell, redCell},
            {redCell, redCell, redCell}
        },
        List.of(),
        List.of()
    );
    assertThrows(IllegalStateException.class, () -> strategy.getMoves(model));
  }

  @Test
  public void testGetMoveThrowsWhenModelIsNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> createMoveStrategy().getMoves(null, PlayerColor.RED)
    );
  }

  @Test
  public void testGetMoveThrowsWhenPlayerColorIsNull() {
    ReadOnlyThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {Cell.createEmptyCardCell()}
        },
        List.of(),
        List.of()
    );
    assertThrows(
        IllegalArgumentException.class,
        () -> createMoveStrategy().getMoves(model, null)
    );
  }

}
