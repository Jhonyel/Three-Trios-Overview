package cs3500.three.trios.player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import cs3500.three.trios.Examples;
import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;

import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.TEN;

public class TestMaxNumFlipsStrategy {

  private Cell[][] grid3x3With9CardCells;
  private Cell[][] grid3x3InProgress;
  private List<Card> handOfFiveWeakestCards;
  private ThreeTriosModel modelRedWon;
  private MoveStrategy moveStrategy;
  private Card northWestWinner1AA1;

  @Before
  public void setUp() throws IOException {
    handOfFiveWeakestCards = List.of(
            new CardImpl("0", ONE, ONE, ONE, ONE),
            new CardImpl("1", ONE, ONE, ONE, ONE),
            new CardImpl("2", ONE, ONE, ONE, ONE),
            new CardImpl("3", ONE, ONE, ONE, ONE),
            new CardImpl("4", ONE, ONE, ONE, ONE)
    );

    Cell emptyCell = Cell.createEmptyCardCell();
    Cell holeCell = Cell.createHoleCell();

    Card card1111 = new CardImpl("name 1 1 1 1");
    Cell redCell1111 = Cell.createOccupiedCardCell(new PlayerCard(card1111, PlayerColor.RED));
    Cell blueCell1111 = Cell.createOccupiedCardCell(new PlayerCard(card1111, PlayerColor.BLUE));

    grid3x3InProgress = new Cell[][]{
            {emptyCell, blueCell1111, redCell1111},
            {blueCell1111, redCell1111, holeCell},
            {emptyCell, blueCell1111, holeCell}
    };

    modelRedWon = ThreeTriosModelImpl.createGameInProgress(
            new Cell[][]{
                    {blueCell1111, redCell1111, redCell1111},
                    {redCell1111, redCell1111, holeCell}
            },
            List.of(),
            List.of()
    );

    moveStrategy = new MaxNumFlipsMoveStrategy();
    grid3x3With9CardCells = Examples.create3x3GridWith9CardCells();

    northWestWinner1AA1 = new CardImpl("", ONE, TEN, TEN, ONE);
  }

  @Test
  public void testMaxNumFlipsWhenGameIsOver() {
    Assert.assertThrows(IllegalStateException.class,
            () -> moveStrategy.getMove(modelRedWon));
  }

  @Test
  public void testMaxNumFlipsWithNullModel() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> moveStrategy.getMove(null));
  }

  @Test
  public void testMaxNumFlipsWithEmptyBoard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    List<Card> blueHand = new ArrayList<>(handOfFiveWeakestCards);

    ThreeTriosModel game = ThreeTriosModelImpl.createGameInProgress(
            grid3x3With9CardCells, redHand, blueHand);

    Move actualMove = moveStrategy.getMove(game).get(0);
    Move expectedMove = new Move(0, 0, 0);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testMaxNumFlipsWithCorrectMove() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    List<Card> blueHand = new ArrayList<>(handOfFiveWeakestCards);

    redHand.set(2, northWestWinner1AA1);

    ThreeTriosModel game = ThreeTriosModelImpl.createGameInProgress(
            grid3x3InProgress, redHand, blueHand);

    Move actualMove = moveStrategy.getMove(game).get(0);
    Move expectedMove = new Move(0, 0, 2);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testMaxNumFlipsWithTie() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    List<Card> blueHand = new ArrayList<>(handOfFiveWeakestCards);

    redHand.set(2, northWestWinner1AA1);
    redHand.set(3, northWestWinner1AA1);

    ThreeTriosModel game = ThreeTriosModelImpl.createGameInProgress(
            grid3x3InProgress, redHand, blueHand);

    Move actualMove = moveStrategy.getMove(game).get(0);
    Move expectedMove = new Move(0, 0, 2);
    Assert.assertEquals(expectedMove, actualMove);
  }
}
