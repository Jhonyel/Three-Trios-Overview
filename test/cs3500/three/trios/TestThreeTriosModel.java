package cs3500.three.trios;

import static cs3500.three.trios.model.PlayerColor.BLUE;
import static cs3500.three.trios.model.PlayerColor.RED;
import static cs3500.three.trios.model.card.AttackValue.FOUR;
import static cs3500.three.trios.model.card.AttackValue.NINE;
import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.TWO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.GridFactory;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Utils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the behavior of our model.
 *
 * <p>Some tests include the statement: assertNonNull(model...). This is because some tests use
 * helper test methods so there are no assert statements in the test method bodies themselves, but
 * the java style checker enforces that all test methods include an assert statement even if they
 * are using helper methods.
 */
public class TestThreeTriosModel {

  private Cell[][] grid5x7With15CardCells;

  private Cell[][] grid3x3With9CardCells;

  private List<Card> listOf16Cards;

  private List<Card> listOf10Cards;

  private Cell[][] jaggedGrid;

  private ThreeTriosModel model5x7With15CardCells;

  private ThreeTriosModel modelRedWon;
  private Cell empty;

  @Before
  public void setUp() throws IOException {
    empty = Cell.createEmptyCardCell();
    Cell hole = Cell.createHoleCell();

    grid5x7With15CardCells = Examples.create5x7GridWith15CardCells();
    model5x7With15CardCells = Examples.create5x7ModelWith15CardCells();
    listOf16Cards = Examples.create16Cards();
    listOf10Cards = Examples.create10Cards();

    jaggedGrid = new Cell[][]{
        {hole, hole, empty},
        {empty, empty},
        {hole}
    };

    grid3x3With9CardCells = new Cell[][]{
        {empty, empty, empty},
        {empty, empty, empty},
        {empty, empty, empty}
    };

    Cell[][] grid2x2With3CardCells = new Cell[][]{
        {hole, empty},
        {empty, empty}
    };
    List<Card> listOf4Cards = listOf16Cards.subList(0, 4);
    modelRedWon = ThreeTriosModelImpl.createNewGame(grid2x2With3CardCells, listOf4Cards, true);
    modelRedWon.playCardAt(0, 1, 0);
    modelRedWon.playCardAt(1, 0, 0);
    modelRedWon.playCardAt(1, 1, 0);
  }

  @Test
  public void testConstructionWithNullGrid() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(null, listOf16Cards, true)
    );
  }

  @Test
  public void testConstructionWithNullCards() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(grid5x7With15CardCells, null, true)
    );
  }

  @Test
  public void testConstructionWithListWithNullCards() {
    List<Card> listWithNull = new ArrayList<>(listOf16Cards);
    listWithNull.add(null);
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(grid5x7With15CardCells, listWithNull, true)
    );
  }

  @Test
  public void testConstructionWithNonRectangularGrid() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(jaggedGrid, listOf16Cards, false)
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
        () -> ThreeTriosModelImpl.createNewGame(evenCardGrid, listOf16Cards, false)
    );
  }

  @Test
  public void testConstructionWithoutEnoughCards() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(grid5x7With15CardCells, listOf10Cards, true)
    );
  }

  @Test
  public void testModifyingGridAfterConstructionDoesNotAffectModel() {
    assertNotNull(model5x7With15CardCells); // for java style checker; see comment in class javadoc

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
    assertThrows(
        IllegalStateException.class,
        () -> modelRedWon.playCardAt(1, 2, 0)
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
    List<PlayerCard> originalHand = new ArrayList<>(model5x7With15CardCells.getHand(RED));
    model5x7With15CardCells.getHand(RED).clear();
    assertEquals(originalHand, model5x7With15CardCells.getHand(RED));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetGrid() {
    assertNotNull(model5x7With15CardCells); // for java style checker; see comment in class javadoc

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
    assertTrue(modelRedWon.isGameOver());
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
    assertEquals(RED, modelRedWon.getWinner());
  }

  @Test
  public void testGetWinnerWhenGameIsTied() throws IOException {
    Cell[][] disconnectedGrid3x4 = GridFactory.createFromConfigurationFilePath(
        "configuration-files" + File.separator + "3x4-disconnected-grid.txt");

    ThreeTriosModel model = ThreeTriosModelImpl.createNewGame(
        disconnectedGrid3x4, listOf10Cards, false);

    model.playCardAt(0, 0, 0); // red plays
    model.playCardAt(2, 1, 0); // blue plays
    model.playCardAt(0, 1, 0); // red plays
    model.playCardAt(2, 2, 0); // blue plays
    model.playCardAt(0, 2, 0); // red plays
    model.playCardAt(2, 3, 0); // blue plays
    model.playCardAt(0, 3, 0); // red plays
    assertEquals(0, model.getHand(RED).size());
    assertEquals(1, model.getHand(BLUE).size());
    assertNull(model.getWinner());
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
    assertThrows(
        IllegalStateException.class,
        () -> modelRedWon.getCurrentPlayer()
    );
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testSimpleBattlePlacedCardWins() {
    // red's top middle card's east attack value of 2 should beat
    // blue's top right card's west attack value of 1
    Card blueTopRightCard = new CardImpl("blue's top right card", ONE, ONE, ONE, ONE);
    Card redTopMiddleCard = new CardImpl("red's top middle card", ONE, ONE, TWO, ONE);

    // the second card in red's hand will be the top middle card
    listOf10Cards.set(1, redTopMiddleCard);

    // the first card in blue's hand will be the top right card
    listOf10Cards.set(5, blueTopRightCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);

    model3x3.playCardAt(0, 0, 0);
    // R__
    // ___
    // ___

    model3x3.playCardAt(0, 2, 0);
    // R_B
    // ___
    // ___

    model3x3.playCardAt(0, 1, 0);
    // RRB
    // ___
    // ___
    // after battle:
    // RRR
    // ___
    // ___

    // the top left card should belong to red after battle
    assertEquals(RED, model3x3.getPlayerAt(0, 2));
    //assertEquals(RED, model3x3.getPlayerAt(0, 0));
  }

  @Test
  public void testSimpleBattleDoesNotFlipOwnCard() {
    // red's top middle card's west attack value of 4 should beat
    // red's top left card's east attack value of 2, but should not flip the card
    Card redTopMiddleCard = new CardImpl("red's top middle card", ONE, ONE, TWO, FOUR);
    Card redTopLeftCard = new CardImpl("red's top left card", ONE, ONE, TWO, FOUR);


    // the second card in red's hand will be the top middle card
    listOf10Cards.set(1, redTopMiddleCard);

    // the first card in red's hand will be the top left card
    listOf10Cards.set(0, redTopLeftCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);

    model3x3.playCardAt(0, 0, 0);
    // R__
    // ___
    // ___

    model3x3.playCardAt(0, 2, 0);
    // R_B
    // ___
    // ___

    model3x3.playCardAt(0, 1, 0);
    // RRB
    // ___
    // ___

    // the top left card should belong to red after battle
    assertEquals(RED, model3x3.getPlayerAt(0, 0));
  }

  @Test
  public void testSimpleBattleFlipsTwoDirectionsAtOnce() {
    // red's top middle card's west and east attack value of 4 should beat
    // blue's top left and right card's attack values of 2
    Card redTopMiddleCard = new CardImpl("red's top middle card", ONE, ONE, FOUR, FOUR);
    Card blueTopLeftCard = new CardImpl("blue's top left card", ONE, ONE, TWO, TWO);
    Card blueTopRightCard = new CardImpl("blue's top right card", ONE, ONE, TWO, TWO);

    // the second card in red's hand will be the top middle card
    listOf10Cards.set(2, redTopMiddleCard);

    // the first card in red's hand will be the top left card
    listOf10Cards.set(6, blueTopLeftCard);
    listOf10Cards.set(5, blueTopRightCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);

    model3x3.playCardAt(1, 0, 0);
    // ___
    // R__
    // ___

    model3x3.playCardAt(0, 2, 0);
    // __B
    // R__
    // ___

    model3x3.playCardAt(2, 0, 0);
    // __B
    // R__
    // R__

    model3x3.playCardAt(0, 0, 0);
    // B_B
    // R__
    // R__

    model3x3.playCardAt(0, 1, 0);
    // BRB
    // R__
    // R__
    // after battle:
    // RRR
    // R__
    // R__

    // the top left and right cards should belong to red after battle
    assertEquals(RED, model3x3.getPlayerAt(0, 0));
    assertEquals(RED, model3x3.getPlayerAt(0, 2));
  }

  @Test
  public void testSimpleBattlePlacedCardLoses() {
    // red's top middle card's east attack value of 1 should lose to
    // blue's top left card's west attack value of 2
    Card blueTopRightCard = new CardImpl("blue's top right card", ONE, ONE, ONE, TWO);
    Card redTopMiddleCard = new CardImpl("red's top middle card", ONE, ONE, ONE, ONE);

    // the second card in red's hand will be the top middle card
    listOf10Cards.set(1, redTopMiddleCard);

    // the first card in blue's hand will be the top right card
    listOf10Cards.set(5, blueTopRightCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);

    model3x3.playCardAt(0, 0, 0);
    // R__
    // ___
    // ___

    model3x3.playCardAt(0, 2, 0);
    // R_B
    // ___
    // ___

    model3x3.playCardAt(0, 1, 0);
    // RRB
    // ___
    // ___

    // the top left card should still belong to blue after battle
    assertEquals(BLUE, model3x3.getPlayerAt(0, 2));
  }

  @Test
  public void testSimpleBattlePlacedCardTies() {
    // red's top middle card's east attack value of 1 should tie
    // blue's top left card's west attack value of 1
    Card blueTopRightCard = new CardImpl("blue's top right card", ONE, ONE, ONE, ONE);
    Card redTopMiddleCard = new CardImpl("red's top middle card", ONE, ONE, ONE, ONE);

    // the second card in red's hand will be the top middle card
    listOf10Cards.set(1, redTopMiddleCard);

    // the first card in blue's hand will be the top right card
    listOf10Cards.set(5, blueTopRightCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);

    model3x3.playCardAt(0, 0, 0);
    // R__
    // ___
    // ___

    model3x3.playCardAt(0, 2, 0);
    // R_B
    // ___
    // ___

    model3x3.playCardAt(0, 1, 0);
    // RRB
    // ___
    // ___

    // the top left card should still belong to blue after battle
    assertEquals(BLUE, model3x3.getPlayerAt(0, 2));
  }

  @Test
  public void testBattleWithOneComboPhase() {
    // blue's top middle card's west attack value of 2 should flip
    // red's top left card's west attack value of 1
    // blue's flipped top left card's south attack value of 2 should then flip
    // red's middle left card's north attack value of 1
    Card blueTopMiddleCard = new CardImpl("blue's top middle card", ONE, ONE, ONE, TWO);
    Card redTopLeftCard = new CardImpl("red's top left card", ONE, TWO, ONE, ONE);
    Card redMiddleLeftCard = new CardImpl("red's middle left card", ONE, ONE, ONE, ONE);

    // the first and second cards in red's hand will be the top left and middle left cards
    // respectively
    listOf10Cards.set(0, redTopLeftCard);
    listOf10Cards.set(1, redMiddleLeftCard);

    // the second card in blue's hand will be the top middle card
    listOf10Cards.set(6, blueTopMiddleCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);
    model3x3.playCardAt(0, 0, 0);
    model3x3.playCardAt(0, 2, 0);
    model3x3.playCardAt(1, 0, 0);
    // R_B
    // R__
    // ___

    model3x3.playCardAt(0, 1, 0);
    // RBB
    // R__
    // ___
    // after battle:
    // BBB
    // B__
    // ___

    assertEquals(BLUE, model3x3.getPlayerAt(0, 0));
    assertEquals(BLUE, model3x3.getPlayerAt(1, 0));
  }

  @Test
  public void testBattleWithTwoComboPhases() {
    // blue's top middle card's west attack value of 2 should flip
    // red's top left card's west attack value of 1
    // blue's flipped top left card's south attack value of 2 should then flip
    // red's middle left card's north attack value of 1
    // blue's flipped middle left card's south attack value of 2 should then flip
    // red's bottom left card's north attack value of 1
    Card blueTopMiddleCard = new CardImpl("blue's top middle card", ONE, ONE, ONE, TWO);

    // the first, second and third cards in red's hand will all be a card with south attack value
    // two and other attack values one
    Card redTopLeftCard = new CardImpl("red's top left card", ONE, TWO, ONE, ONE);
    Card redMiddleLeftCard = new CardImpl("red's middle left card", ONE, TWO, ONE, ONE);
    Card redBottomLeftCard = new CardImpl("red's bottom left card", ONE, TWO, ONE, ONE);
    listOf10Cards.set(0, redTopLeftCard);
    listOf10Cards.set(1, redMiddleLeftCard);
    listOf10Cards.set(2, redBottomLeftCard);

    // the third card in blue's hand will be the top middle card
    listOf10Cards.set(7, blueTopMiddleCard);

    ThreeTriosModel model3x3 = ThreeTriosModelImpl.createNewGame(grid3x3With9CardCells, listOf10Cards, false);
    model3x3.playCardAt(0, 0, 0);
    model3x3.playCardAt(0, 2, 0);
    model3x3.playCardAt(1, 0, 0);
    model3x3.playCardAt(1, 2, 0);
    model3x3.playCardAt(2, 0, 0);
    // R_B
    // R_B
    // R__

    model3x3.playCardAt(0, 1, 0);
    // RBB
    // R_B
    // R__
    // after battle:
    // BBB
    // B_B
    // B__
    assertEquals(BLUE, model3x3.getPlayerAt(0, 0));
    assertEquals(BLUE, model3x3.getPlayerAt(0, 1));
    assertEquals(BLUE, model3x3.getPlayerAt(0, 2));
    assertEquals(BLUE, model3x3.getPlayerAt(1, 0));
    assertEquals(BLUE, model3x3.getPlayerAt(1, 2));
    assertEquals(BLUE, model3x3.getPlayerAt(2, 0));
  }
}
