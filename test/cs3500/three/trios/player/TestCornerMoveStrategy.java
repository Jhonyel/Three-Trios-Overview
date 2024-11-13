package cs3500.three.trios.player;

import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.TEN;

import cs3500.three.trios.Examples;
import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.mock.LoggingThreeTriosModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCornerMoveStrategy extends TestMoveStrategy {

  private Cell[][] grid3x3With9CardCells;
  private List<Card> handOfFiveWeakestCards;
  private Card northWestWinner1AA1;
  private Card northEastWinner1A1A;
  private Card southWestWinnerA1A1;
  private Card southEastWinnerA11A;
  private Card card9999;
  private int northRowIndex3x3;
  private int southRowIndex3x3;
  private int eastColIndex3x3;
  private int westColIndex3x3;
  private Cell emptyCell;
  private Cell holeCell;

  @Override
  protected MoveStrategy createMoveStrategy() {
    return new CornerMoveStrategy();
  }

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

    northRowIndex3x3 = 0;
    southRowIndex3x3 = 2;
    eastColIndex3x3 = 2;
    westColIndex3x3 = 0;
  }

  private Move getBestCornerMove(ThreeTriosModel model) {
    return new CornerMoveStrategy().getMoves(model).get(0);
  }

  @Test
  public void testGetMoveWithClearNorthEastWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, northEastWinner1A1A);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(northRowIndex3x3, eastColIndex3x3, 3)
    );
  }

  @Test
  public void testGetMoveWithClearNorthWestWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, northWestWinner1AA1);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(northRowIndex3x3, westColIndex3x3, 3)
    );

  }

  @Test
  public void testGetMoveWithClearSouthEastWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, southEastWinnerA11A);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(southRowIndex3x3, eastColIndex3x3, 3)
    );
  }

  @Test
  public void testGetMoveWithClearSouthWestWinner() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, card9999);
    redHand.set(3, southWestWinnerA1A1);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(southRowIndex3x3, westColIndex3x3, 3)
    );
  }

  @Test
  public void testGetMoveTieReturnsNorthWestCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, southEastWinnerA11A);
    redHand.set(1, southWestWinnerA1A1);
    redHand.set(2, northEastWinner1A1A);
    redHand.set(3, northWestWinner1AA1);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(northRowIndex3x3, westColIndex3x3, 3)
    );
  }

  @Test
  public void testGetMoveTieReturnsNorthMostCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, southEastWinnerA11A);
    redHand.set(1, southWestWinnerA1A1);
    redHand.set(2, northEastWinner1A1A);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(northRowIndex3x3, eastColIndex3x3, 2)
    );
  }

  @Test
  public void testGetMoveTieInSameRowReturnsWestMostCard() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, northEastWinner1A1A);
    redHand.set(1, northWestWinner1AA1);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(northRowIndex3x3, westColIndex3x3, 1)
    );
  }

  @Test
  public void testGetMoveTieReturnsCardWithLowestIndex() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, northWestWinner1AA1);
    redHand.set(1, northWestWinner1AA1);

    Assert.assertEquals(
        getBestCornerMove(
            ThreeTriosModelImpl.createGameInProgress(
                grid3x3With9CardCells,
                redHand,
                List.of()
            )
        ),
        new Move(northRowIndex3x3, westColIndex3x3, 0)
    );
  }

  @Test
  public void testGetMoveOnlyReturnsLegalMoves() {
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {holeCell, holeCell, holeCell},
            {emptyCell, emptyCell, emptyCell},
        },
        handOfFiveWeakestCards,
        List.of()
    );
    List<Move> moves = new CornerMoveStrategy().getMoves(model);
    for (Move move : moves) {
      Assert.assertTrue(model.isMoveLegalAt(move.getRowIndex(), move.getColIndex()));
    }
    Assert.assertFalse(moves.contains(new Move(0, 0, 0)));
    Assert.assertFalse(moves.contains(new Move(0, 2, 0)));
  }

  @Test
  public void testGetMoveReturnsCorrectMoves() {
    List<Card> redHand = new ArrayList<>(handOfFiveWeakestCards);
    redHand.set(0, southEastWinnerA11A);
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells,
        redHand,
        List.of()
    );
    List<Move> actualMoves = new CornerMoveStrategy().getMoves(model);
    List<Move> expectedMoves = List.of(
        new Move(southRowIndex3x3, eastColIndex3x3, 0),
        new Move(northRowIndex3x3, eastColIndex3x3, 0),
        new Move(southRowIndex3x3, westColIndex3x3, 0),

        new Move(northRowIndex3x3, westColIndex3x3, 0),
        new Move(northRowIndex3x3, westColIndex3x3, 1),
        new Move(northRowIndex3x3, westColIndex3x3, 2),
        new Move(northRowIndex3x3, westColIndex3x3, 3),
        new Move(northRowIndex3x3, westColIndex3x3, 4),

        new Move(northRowIndex3x3, eastColIndex3x3, 1),
        new Move(northRowIndex3x3, eastColIndex3x3, 2),
        new Move(northRowIndex3x3, eastColIndex3x3, 3),
        new Move(northRowIndex3x3, eastColIndex3x3, 4),

        new Move(southRowIndex3x3, westColIndex3x3, 1),
        new Move(southRowIndex3x3, westColIndex3x3, 2),
        new Move(southRowIndex3x3, westColIndex3x3, 3),
        new Move(southRowIndex3x3, westColIndex3x3, 4),

        new Move(southRowIndex3x3, eastColIndex3x3, 1),
        new Move(southRowIndex3x3, eastColIndex3x3, 2),
        new Move(southRowIndex3x3, eastColIndex3x3, 3),
        new Move(southRowIndex3x3, eastColIndex3x3, 4)
    );
    Assert.assertEquals(actualMoves, expectedMoves);
  }

  @Test
  public void testGetMoveReturnsOnlyCornerMoves() {
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        grid3x3With9CardCells,
        handOfFiveWeakestCards,
        List.of()
    );
    List<Move> moves = new CornerMoveStrategy().getMoves(model);
    for (Move move : moves) {
      int rowIndex = move.getRowIndex();
      int colIndex = move.getColIndex();
      boolean isMoveNorthOrSouth = rowIndex == northRowIndex3x3 || rowIndex == southRowIndex3x3;
      boolean isMoveWestOrEast = colIndex == westColIndex3x3 || colIndex == eastColIndex3x3;
      Assert.assertTrue(isMoveNorthOrSouth && isMoveWestOrEast);
    }
  }

  @Test
  public void testGetMoveChecksAllCorners() {
    StringBuilder output = new StringBuilder();
    LoggingThreeTriosModel loggingModel = new LoggingThreeTriosModel(
        ThreeTriosModelImpl.createGameInProgress(
            grid3x3With9CardCells,
            List.of(northWestWinner1AA1),
            List.of()
        ),
        output
    );
    new CornerMoveStrategy().getMoves(loggingModel);
    List<String> logs = List.of(output.toString().split("\n"));
    Assert.assertTrue(logs.contains("isMoveLegalAt(0, 0)"));
    Assert.assertTrue(logs.contains("isMoveLegalAt(0, 2)"));
    Assert.assertTrue(logs.contains("isMoveLegalAt(2, 0)"));
    Assert.assertTrue(logs.contains("isMoveLegalAt(2, 2)"));
  }
}
