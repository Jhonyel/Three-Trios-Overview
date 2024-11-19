package cs3500.three.trios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Utils;
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
  private Cell[][] jaggedGrid;

  private List<Card> listOf16Cards;
  private List<Card> listOf10Cards;

  private ThreeTriosModel model5x7With15CardCells;
  private ThreeTriosModel modelRedWon;

  private Card card1111;
  private Card cardAAAA;

  private Cell emptyCell;
  private Cell holeCell;

  private Cell redCell1111;
  private Cell blueCell1111;
  private Cell blueCellAAAA;

  @Before
  public void setUp()  {
    grid5x7With15CardCells = Examples.create5x7GridWith15CardCells();
    model5x7With15CardCells = Examples.create5x7ModelWith15CardCells();

    emptyCell = Cell.createEmptyCardCell();
    holeCell = Cell.createHoleCell();

    jaggedGrid = new Cell[][]{
        {holeCell, holeCell, emptyCell},
        {emptyCell, emptyCell},
        {holeCell}
    };

    listOf16Cards = Examples.create16Cards();
    listOf10Cards = Examples.create10Cards();

    card1111 = new CardImpl("name 1 1 1 1");
    cardAAAA = new CardImpl("name A A A A");

    PlayerCard redCard1111 = new PlayerCard(card1111, PlayerColor.RED);

    PlayerCard blueCard1111 = new PlayerCard(card1111, PlayerColor.BLUE);
    PlayerCard blueCardAAAA = new PlayerCard(cardAAAA, PlayerColor.BLUE);

    redCell1111 = Cell.createOccupiedCardCell(redCard1111);

    blueCell1111 = Cell.createOccupiedCardCell(blueCard1111);
    blueCellAAAA = Cell.createOccupiedCardCell(blueCardAAAA);

    modelRedWon = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell1111, redCell1111, redCell1111},
            {redCell1111, redCell1111, holeCell}
        },
        List.of(),
        List.of()
    );

  }

  @Test
  public void testCreateNewGameWithNullGrid() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(null, listOf16Cards, true)
    );
  }

  @Test
  public void testCreateNewGameWithNullCards() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(grid5x7With15CardCells, null, true)
    );
  }

  @Test
  public void testCreateNewGameWithListWithNullCards() {
    List<Card> listWithNull = new ArrayList<>(listOf16Cards);
    listWithNull.add(null);
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(grid5x7With15CardCells, listWithNull, true)
    );
  }

  @Test
  public void testCreateNewGameWithNonRectangularGrid() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(jaggedGrid, listOf16Cards, false)
    );
  }

  @Test
  public void testCreateNewGameWithEvenNumberOfCardCells() {
    Cell[][] evenCardGrid = new Cell[][]{
        {emptyCell, emptyCell},
        {emptyCell, emptyCell}
    };
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(evenCardGrid, listOf16Cards, false)
    );
  }

  @Test
  public void testCreateNewGameWithoutEnoughCards() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ThreeTriosModelImpl.createNewGame(grid5x7With15CardCells, listOf10Cards, true)
    );
  }

  @Test
  public void testModifyingGridAfterCreateNewGameDoesNotAffectModel() {
    assertNotNull(model5x7With15CardCells); // for java style checker; see comment in class javadoc

    // we construct model5x7 using grid5x7 in the setup method
    Cell[][] originalGrid = Utils.copyArray2D(grid5x7With15CardCells);
    Cell[][] gridToModify = Utils.copyArray2D(grid5x7With15CardCells);

    // switch the copied grid from a card cell to a hole
    gridToModify[0][0] = Cell.createHoleCell();

    // that switch should not affect the model
    TestUtils.assertArray2DEquals(originalGrid, model5x7With15CardCells.getGrid());
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
    List<PlayerCard> hand = model5x7With15CardCells.getHand(PlayerColor.RED);
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
    assertEquals(listOfFirst8Cards, model5x7With15CardCells.getHand(PlayerColor.RED));
    assertEquals(listOfLast8Cards, model5x7With15CardCells.getHand(PlayerColor.BLUE));
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
    assertEquals(expectedHand, model5x7With15CardCells.getHand(PlayerColor.RED));
  }

  @Test
  public void testModifyingHandDoesNotAffectModel() {
    List<PlayerCard> originalHand = new ArrayList<>(
        model5x7With15CardCells.getHand(PlayerColor.RED));
    model5x7With15CardCells.getHand(PlayerColor.RED).clear();
    assertEquals(originalHand, model5x7With15CardCells.getHand(PlayerColor.RED));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetGrid() {
    assertNotNull(model5x7With15CardCells); // for java style checker; see comment in class javadoc

    Cell[][] grid = model5x7With15CardCells.getGrid();
    TestUtils.assertArray2DEquals(model5x7With15CardCells.getGrid(), grid);
  }

  @Test
  public void testPlayCardThenGetGrid() {
    Cell[][] grid = model5x7With15CardCells.getGrid();
    TestUtils.assertArray2DEquals(model5x7With15CardCells.getGrid(), grid);
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
    assertEquals(PlayerColor.RED, modelRedWon.getWinner());
  }

  @Test
  public void testGetWinnerWhenGameIsTied() {

    ThreeTriosModelImpl modelTied = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {redCell1111, redCell1111, redCell1111},
            {holeCell, holeCell, holeCell},
            {holeCell, blueCell1111, blueCell1111}
        },
        List.of(),
        List.of(card1111)
    );

    assertNull(modelTied.getWinner());
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCurrentPlayerColor() {
    assertEquals(PlayerColor.RED, model5x7With15CardCells.getCurrentPlayerColor());
  }

  @Test
  public void testPlayCardThenGetCurrentPlayerColor() {
    assertEquals(PlayerColor.RED, model5x7With15CardCells.getCurrentPlayerColor());
    model5x7With15CardCells.playCardAt(4, 6, 0);
    assertEquals(PlayerColor.BLUE, model5x7With15CardCells.getCurrentPlayerColor());
  }

  @Test
  public void testGetCurrentPlayerColorWhenGameIsOver() {
    assertThrows(
        IllegalStateException.class,
        () -> modelRedWon.getCurrentPlayerColor()
    );
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testSimpleBattlePlacedCardWins() {
    // red's top middle card's west attack value of A should beat
    // blue's top left card's east attack value of 1 and flip the card

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell1111, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell},
        },
        List.of(cardAAAA),
        List.of()
    );
    model.playCardAt(0, 1, 0);
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 0));
  }

  @Test
  public void testSimpleBattleDoesNotFlipOwnCard() {
    // red's top middle card's west attack value of A should beat
    // red's top left card's east attack value of 1, but should not flip the card

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {redCell1111, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell}
        },
        List.of(cardAAAA),
        List.of()
    );
    model.playCardAt(0, 1, 0);
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 0));
  }

  @Test
  public void testSimpleBattleFlipsTwoDirectionsAtOnce() {
    // red's top middle card's west and east attack value of A should beat
    // blue's top left and right card's attack values of 1

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell1111, emptyCell, blueCell1111},
            {emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell}
        },
        List.of(cardAAAA),
        List.of()
    );
    model.playCardAt(0, 1, 0);
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 0));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 2));

  }

  @Test
  public void testSimpleBattlePlacedCardLoses() {
    // red's top middle card's west attack value of 1 should lose to
    // blue's top left card's west attack value of 2. neither card should flip

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCellAAAA, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell}
        },
        List.of(card1111),
        List.of()
    );

    model.playCardAt(0, 1, 0);
    assertEquals(PlayerColor.BLUE, model.getPlayerAt(0, 0));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 1));
  }

  @Test
  public void testSimpleBattlePlacedCardTies() {
    // red's top middle card's east attack value of 1 should tie
    // blue's top left card's west attack value of 1. neither card should flip

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell1111, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell}
        },
        List.of(card1111),
        List.of()
    );

    model.playCardAt(0, 1, 0);
    assertEquals(PlayerColor.BLUE, model.getPlayerAt(0, 0));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 1));
  }

  @Test
  public void testBattleWithOneComboPhase() {
    // red's top right card's west attack value of A should beat
    // blue's top middle card's east attack value of 1 and flip the card
    // red's new top middle card's west attack value of A should beat
    // blue's top left card's east attack value of 1 and flip the card

    Cell blueCell111A = Cell.createOccupiedCardCell(
        new PlayerCard("name 1 1 1 A", PlayerColor.BLUE));

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell111A, blueCell111A, emptyCell},
            {emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell}
        },
        List.of(cardAAAA),
        List.of()
    );

    model.playCardAt(0, 2, 0);
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 0));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 1));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 2));
  }

  @Test
  public void testBattleWithTwoComboPhases() {
    Cell blueCell111A = Cell.createOccupiedCardCell(
        new PlayerCard("name 1 1 1 A", PlayerColor.BLUE));

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell111A, blueCell111A, blueCell111A, emptyCell},
            {emptyCell, emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell, holeCell}
        },
        List.of(cardAAAA),
        List.of()
    );

    model.playCardAt(0, 3, 0);
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 0));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 1));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 2));
    assertEquals(PlayerColor.RED, model.getPlayerAt(0, 3));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetWidth() {
    Cell[][] grid = grid5x7With15CardCells;
    int width = grid[0].length;
    Assert.assertEquals(width, model5x7With15CardCells.getWidth());
  }

  @Test
  public void testGetHeight() {
    Cell[][] grid = grid5x7With15CardCells;
    int height = grid.length;
    Assert.assertEquals(height, model5x7With15CardCells.getHeight());
  }

  @Test
  public void testIsMoveLegalInvalidIndex() {
    Assert.assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.isMoveLegalAt(5, 7)
    );
  }

  @Test
  public void testIsMoveLegal() {
    Assert.assertTrue(model5x7With15CardCells.isMoveLegalAt(0, 0));
    Assert.assertFalse(model5x7With15CardCells.isMoveLegalAt(0, 2));
  }

  @Test
  public void testGetScoreWithNullPlayer() {
    Assert.assertThrows(
        IllegalArgumentException.class,
        () -> model5x7With15CardCells.getScore(null)
    );
  }

  @Test
  public void testGetScoreWithCells() {
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {redCell1111, blueCellAAAA, emptyCell},
        },
        List.of(),
        List.of()
    );
    assertEquals(1, model.getScore(PlayerColor.RED));
    assertEquals(1, model.getScore(PlayerColor.BLUE));
  }

  @Test
  public void testGetScoreWithHands() {
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {emptyCell},
        },
        List.of(cardAAAA),
        List.of(cardAAAA)
    );
    assertEquals(1, model.getScore(PlayerColor.RED));
    assertEquals(1, model.getScore(PlayerColor.BLUE));
  }

  @Test
  public void testGetScoreWithCellsAndHands() {
    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {redCell1111, blueCellAAAA, emptyCell},
        },
        List.of(cardAAAA),
        List.of(cardAAAA)
    );
    assertEquals(2, model.getScore(PlayerColor.RED));
    assertEquals(2, model.getScore(PlayerColor.BLUE));
  }

  @Test
  public void testGetNumFlippedInvalidIndex() {
    Assert.assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.getNumFlipsAt(5, 7, 0)
    );
    Assert.assertThrows(
        IndexOutOfBoundsException.class,
        () -> model5x7With15CardCells.getNumFlipsAt(4, 6, 17)
    );
  }

  @Test
  public void testGetNumFlippedCardsMultiple() {
    Cell blueCell111A = Cell.createOccupiedCardCell(
        new PlayerCard("name 1 1 1 A", PlayerColor.BLUE));

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
        new Cell[][]{
            {blueCell111A, blueCell111A, blueCell111A, emptyCell},
            {emptyCell, emptyCell, emptyCell, emptyCell},
            {emptyCell, emptyCell, emptyCell, holeCell}
        },
        List.of(cardAAAA),
        List.of()
    );

    assertEquals(3, model.getNumFlipsAt(0, 3, 0));
    assertEquals(0, model.getNumFlipsAt(2, 0, 0));
  }

  @Test
  public void testGetNumFlippedCardsMultipleDirections() {
    Cell blueCell1111 = Cell.createOccupiedCardCell(
            new PlayerCard("name 1 1 1 1", PlayerColor.BLUE));

    ThreeTriosModel model = ThreeTriosModelImpl.createGameInProgress(
            new Cell[][]{
                    {emptyCell, blueCell1111, blueCell1111, emptyCell},
                    {emptyCell, emptyCell, blueCell1111, emptyCell},
                    {emptyCell, emptyCell, emptyCell, holeCell}
            },
            List.of(cardAAAA),
            List.of()
    );

    assertEquals(2, model.getNumFlipsAt(1, 1, 0));
    assertEquals(0, model.getNumFlipsAt(2, 0, 0));
  }
}
