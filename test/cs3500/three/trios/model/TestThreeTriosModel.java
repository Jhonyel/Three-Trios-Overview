package cs3500.three.trios.model;

import static cs3500.three.trios.model.PlayerColor.BLUE;
import static cs3500.three.trios.model.PlayerColor.RED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

import cs3500.three.trios.Examples;
import cs3500.three.trios.TestUtils;
import cs3500.three.trios.model.card.AttackValue;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the behavior of our model.
 */
public class TestThreeTriosModel {

  private Cell[][] grid5x7With15CardCells;
  private List<Card> listOf16Cards;
  private List<Card> listOf10Cards;
  private Cell[][] jaggedGrid;

  /**
   * Grid:
   * <p>CCXXXXC
   * <p>CXCXXXC
   * <p>CXXCXXC
   * <p>CXXXCXC
   * <p>CXXXXCC
   */
  private ThreeTriosModel model5x7With15CardCells;

  /**
   * Grid:
   * <p>XC
   * <p>CC
   */
  private ThreeTriosModel model2x2With3CardCells;
  private Cell empty;
  private Cell hole;

  @Before
  public void setUp() throws IOException {
    empty = Cell.createEmptyCardCell();
    hole = Cell.createHoleCell();

    grid5x7With15CardCells = Examples.create5x7GridWith15CardCells();
    model5x7With15CardCells = Examples.create5x7ModelWith15CardCells();
    listOf16Cards = Examples.create16Cards();
    listOf10Cards = Examples.create10Cards();

    jaggedGrid = new Cell[][]{
        {hole, hole, empty},
        {empty, empty},
        {hole}
    };

    Cell[][] grid2x2With3CardCells = new Cell[][]{
        {hole, empty},
        {empty, empty}
    };
    List<Card> listOf4Cards = listOf16Cards.subList(0, 4);
    model2x2With3CardCells = new ThreeTriosModelImpl(grid2x2With3CardCells, listOf4Cards, true);
  }

  @Test
  public void testConstructionWithNullGrid() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ThreeTriosModelImpl(null, listOf16Cards, true)
    );
  }

  @Test
  public void testConstructionWithNullCards() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ThreeTriosModelImpl(grid5x7With15CardCells, null, true)
    );
  }

  @Test
  public void testConstructionWithListWithNullCards() {
    List<Card> listWithNull = new ArrayList<>(listOf16Cards);
    listWithNull.add(null);
    assertThrows(
        IllegalArgumentException.class,
        () -> new ThreeTriosModelImpl(grid5x7With15CardCells, listWithNull, true)
    );
  }

  @Test
  public void testConstructionWithNonRectangularGrid() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ThreeTriosModelImpl(jaggedGrid, listOf16Cards, false)
    );
  }

  @Test
  public void testConstructionWithEvenNumberOfCardCells() {
    Cell[][] evenCardGrid = new Cell[][]{
        {empty, empty},
        {empty, empty}
    };
    assertThrows(
        IllegalArgumentException.class,
        () -> new ThreeTriosModelImpl(evenCardGrid, listOf16Cards, false)
    );
  }

  @Test
  public void testConstructionWithWrongNumberOfCards() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new ThreeTriosModelImpl(grid5x7With15CardCells, listOf10Cards, true)
    );
  }

  @Test
  public void testModifyingGridAfterConstructionDoesNotAffectModel() {
    // we construct model5x7 using grid5x7 in the setup method
    Cell[][] originalGrid = Utils.copyArray2D(grid5x7With15CardCells);
    Cell[][] gridToModify = grid5x7With15CardCells;

    // switch the copied grid from a card cell to a hole
    gridToModify[0][0] = Cell.createHoleCell();

    // that switch should not affect the model
    TestUtils.assert2DArrayEquals(originalGrid, model5x7With15CardCells.getGrid());
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testPlayCardToOutOfBoundsCell() {
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.playCardAt(5, 6, 0)
    );
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.playCardAt(-5, 6, 0)
    );
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.playCardAt(5, -6, 0)
    );
  }

  @Test
  public void testPlayCardToEmptyCardCell() {
    List<PlayerCard> hand = model5x7With15CardCells.getHand(RED);
    PlayerCard card = hand.get(0);
    Cell expectedCell = Cell.createOccupiedCardCell(card);

    model5x7With15CardCells.playCardAt(0, 0, 0);
    Cell actualCell = model5x7With15CardCells.getCellAt(0, 0);

    assertEquals(expectedCell, actualCell);
  }

  @Test
  public void testPlayCardToOccupiedCardCell() {
    model5x7With15CardCells.playCardAt(0, 0, 0);
    assertThrows(
        IllegalStateException.class,
        () -> model5x7With15CardCells.playCardAt(0, 0, 0)
    );
  }

  @Test
  public void testPlayCardToHole() {
    assertThrows(
        IllegalStateException.class,
        () -> model5x7With15CardCells.playCardAt(0, 2, 0)
    );
  }

  @Test
  public void testPlayNullCard() {
    assertThrows(
        IllegalArgumentException.class,
        () -> model5x7With15CardCells.playCardAt(1, 2, null)
    );
  }

  @Test
  public void testPlayAfterGameEnds() {
    model2x2With3CardCells.playCardAt(0, 1, 0);
    model2x2With3CardCells.playCardAt(1, 1, 0);
    model2x2With3CardCells.playCardAt(1, 0, 0);
    assertThrows(
        IllegalStateException.class,
        () -> model2x2With3CardCells.playCardAt(1, 2, 0)
    );
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetHand() {
    List<Card> listOfFirst8Cards = listOf16Cards.subList(0, 8);
    List<Card> listOfLast8Cards = listOf16Cards.subList(8, 16);
    assertEquals(listOfFirst8Cards, model5x7With15CardCells.getHand(RED));
    assertEquals(listOfLast8Cards, model5x7With15CardCells.getHand(BLUE));
  }

  @Test
  public void testGetHandNullPlayer() {
    assertThrows(
        IllegalArgumentException.class,
        () -> model5x7With15CardCells.getHand(null)
    );
  }

  @Test
  public void testPlayCardThenGetHand() {
    List<Card> expectedHand = listOf16Cards.subList(0, 8);
    model5x7With15CardCells.playCardAt(0, 1, 0);
    expectedHand.remove(0);
    assertEquals(expectedHand, model5x7With15CardCells.getHand(RED));
  }

  @Test
  public void testModifyingHandDoesNotAffectModel() {
    List<PlayerCard> originalHand = model5x7With15CardCells.getHand(RED);
    model5x7With15CardCells.getHand(RED).clear();
    assertEquals(originalHand, model5x7With15CardCells.getHand(RED));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetGrid() {
    Cell[][] grid = model5x7With15CardCells.getGrid();
    TestUtils.assert2DArrayEquals(model5x7With15CardCells.getGrid(), grid);
  }

  @Test
  public void testPlayCardThenGetGrid() {
    Cell[][] grid = model5x7With15CardCells.getGrid();
    TestUtils.assert2DArrayEquals(model5x7With15CardCells.getGrid(), grid);
    model5x7With15CardCells.playCardAt(4, 6, 0);
    Assert.assertNotEquals(model5x7With15CardCells.getGrid(), grid);
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCell() {
    for (int rowIndex = 0; rowIndex < 5; rowIndex++) {
      for (int colIndex = 0; colIndex < 7; colIndex++) {
        Cell expectedCell = grid5x7With15CardCells[rowIndex][colIndex];
        Cell actualCell = model5x7With15CardCells.getCellAt(rowIndex, colIndex);
        assertEquals(expectedCell, actualCell);
      }
    }
  }

  @Test
  public void testGetOutOfBoundsCell() {
    assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.getCellAt(8, 6)
    );
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testIsGameOverWhenGameIsNotOver() {
    assertFalse(model5x7With15CardCells.isGameOver());
  }

  @Test
  public void testIsGameOverWhenGameIsOver() {
    model2x2With3CardCells.playCardAt(0, 1, 0);
    model2x2With3CardCells.playCardAt(1, 1, 0);
    model2x2With3CardCells.playCardAt(1, 0, 0);
    Assert.assertTrue(model2x2With3CardCells.isGameOver());
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetWinnerWhenGameIsNotOver() {
    assertThrows(
        IllegalStateException.class,
        () -> model5x7With15CardCells.getWinner()
    );
  }

  @Test
  public void testGetWinnerWhenGameIsOver() {
    model2x2With3CardCells.playCardAt(0, 1, 0);
    model2x2With3CardCells.playCardAt(1, 1, 0);
    model2x2With3CardCells.playCardAt(1, 0, 0);
    assertEquals(RED, model2x2With3CardCells.getWinner());
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCurrentPlayer() {
    assertEquals(RED, model5x7With15CardCells.getCurrentPlayer());
  }

  @Test
  public void testPlayCardThenGetCurrentPlayer() {
    assertEquals(RED, model5x7With15CardCells.getCurrentPlayer());
    model5x7With15CardCells.playCardAt(4, 6, 0);
    assertEquals(BLUE, model5x7With15CardCells.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayerWhenGameIsOver() {
    model2x2With3CardCells.playCardAt(0, 1, 0);
    model2x2With3CardCells.playCardAt(1, 1, 0);
    model2x2With3CardCells.playCardAt(1, 0, 0);
    assertThrows(
        IllegalStateException.class,
        () -> model2x2With3CardCells.getCurrentPlayer()
    );
  }
}
