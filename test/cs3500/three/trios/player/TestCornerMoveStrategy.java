package cs3500.three.trios.player;

import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.TEN;

import cs3500.three.trios.Examples;
import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCornerMoveStrategy {

  private Cell[][] grid3x3With9CardCells;
  private List<Card> handOfFiveWeakestCards;
  private Card northEastWinner1AA1;
  private Card northWestWinner1A1A;
  private Card southEastWinnerA1A1;
  private Card southWestWinnerA11A;

  @Before
  public void setUp() throws IOException {
    handOfFiveWeakestCards = List.of(
        new CardImpl("0", ONE, ONE, ONE, ONE),
        new CardImpl("1", ONE, ONE, ONE, ONE),
        new CardImpl("2", ONE, ONE, ONE, ONE),
        new CardImpl("3", ONE, ONE, ONE, ONE),
        new CardImpl("4", ONE, ONE, ONE, ONE)
    );

    grid3x3With9CardCells = Examples.create3x3GridWith9CardCells();

    northEastWinner1AA1 = new CardImpl("", ONE, TEN, TEN, ONE);
    northWestWinner1A1A = new CardImpl("", ONE, TEN, ONE, TEN);
    southEastWinnerA1A1 = new CardImpl("", TEN, ONE, TEN, ONE);
    southWestWinnerA11A = new CardImpl("", TEN, ONE, ONE, TEN);
  }

  @Test
  public void testGetMoveWithClearNorthEastWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(3, northEastWinner1AA1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).peek();
    Move expectedMove = new Move(0, 0, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithClearNorthWestWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(3, northWestWinner1A1A);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).peek();
    Move expectedMove = new Move(0, 2, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithClearSouthEastWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(3, southEastWinnerA1A1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).peek();
    Move expectedMove = new Move(2, 0, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithClearSouthWestWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(3, southWestWinnerA11A);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).peek();
    Move expectedMove = new Move(2, 2, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithTie() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(2, northEastWinner1AA1);
    redHand.set(3, northWestWinner1A1A);
    List<Card> blueHand = handOfFiveWeakestCards;
  }

  @Test
  public void testGetMoveReturnsNorthWestMostCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, southWestWinnerA11A);
    redHand.set(1, southEastWinnerA1A1);
    redHand.set(2, northWestWinner1A1A);
    redHand.set(3, northEastWinner1AA1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).peek();
    Move expectedMove = new Move(0, 0, 2);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveReturnsCardWithLowestIndex() {

  }

}
