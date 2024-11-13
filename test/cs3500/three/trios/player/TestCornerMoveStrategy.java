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
  private Card northWestWinner1AA1;
  private Card northEastWinner1A1A;
  private Card southWestWinnerA1A1;
  private Card southEastWinnerA11A;
  private Card card9999;
  private int northRowIndex;
  private int southRowIndex;
  private int eastColIndex;
  private int westColIndex;
  private Cell emptyCell;
  private Cell holeCell;

  @Before
  public void setUp() throws IOException {
    emptyCell = Cell.createEmptyCardCell();
    holeCell = Cell.createHoleCell();

    handOfFiveWeakestCards = List.of(
        new CardImpl("name", ONE, ONE, ONE, ONE),
        new CardImpl("name", ONE, ONE, ONE, ONE),
        new CardImpl("name", ONE, ONE, ONE, ONE),
        new CardImpl("name", ONE, ONE, ONE, ONE),
        new CardImpl("name", ONE, ONE, ONE, ONE)
    );

    grid3x3With9CardCells = Examples.create3x3GridWith9CardCells();

    northWestWinner1AA1 = new CardImpl("name", ONE, TEN, TEN, ONE);
    northEastWinner1A1A = new CardImpl("name", ONE, TEN, ONE, TEN);
    southWestWinnerA1A1 = new CardImpl("name", TEN, ONE, TEN, ONE);
    southEastWinnerA11A = new CardImpl("name", TEN, ONE, ONE, TEN);
    card9999 = new CardImpl("name 9 9 9 9");

    northRowIndex = 0;
    southRowIndex = 2;
    eastColIndex = 2;
    westColIndex = 0;
  }

  @Test
  public void testGetMoveWithClearNorthEastWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, northEastWinner1A1A);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(northRowIndex, eastColIndex, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithClearNorthWestWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, northWestWinner1AA1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(northRowIndex, westColIndex, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithClearSouthEastWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, southEastWinnerA11A);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(southRowIndex, eastColIndex, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveWithClearSouthWestWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, southWestWinnerA1A1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(southRowIndex, westColIndex, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveTieReturnsNorthWestCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, southEastWinnerA11A);
    redHand.set(1, southWestWinnerA1A1);
    redHand.set(2, northEastWinner1A1A);
    redHand.set(3, northWestWinner1AA1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(northRowIndex, westColIndex, 3);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveTieReturnsNorthMostCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, southEastWinnerA11A);
    redHand.set(1, southWestWinnerA1A1);
    redHand.set(2, northEastWinner1A1A);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(northRowIndex, eastColIndex, 2);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveTieInSameRowReturnsWestMostCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, northEastWinner1A1A);
    redHand.set(1, northWestWinner1AA1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();

    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(northRowIndex, westColIndex, 1);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveTieReturnsCardWithLowestIndex() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, northWestWinner1AA1);
    redHand.set(1, northWestWinner1AA1);

    List<Card> blueHand = handOfFiveWeakestCards;

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells, redHand, blueHand);

    MoveStrategy strategy = new CornerMoveStrategy();
    Move actualMove = strategy.getMove(model).get(0);
    Move expectedMove = new Move(northRowIndex, westColIndex, 0);
    Assert.assertEquals(expectedMove, actualMove);
  }

  @Test
  public void testGetMoveOnlyReturnsLegalMoves() {
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {holeCell, holeCell},
            {emptyCell, emptyCell},
        },
        handOfFiveWeakestCards,
        handOfFiveWeakestCards
    );
    List<Move> moves = new CornerMoveStrategy().getMove(model);
    for (Move move : moves) {
      Assert.assertTrue(model.isMoveLegalAt(move.getRowIndex(), move.getColIndex()));
    }
    Assert.assertFalse(moves.contains(new Move(0, 0, 0)));
    Assert.assertFalse(moves.contains(new Move(0, 1, 0)));
  }

  @Test
  public void testGetMoveReturnsCorrectOrderOfMoves() {

  }

}
