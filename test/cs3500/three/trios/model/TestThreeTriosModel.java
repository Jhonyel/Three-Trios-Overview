package cs3500.three.trios.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.three.trios.model.card.AttackValue;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;

import static cs3500.three.trios.Examples.create16Cards;
import static cs3500.three.trios.Examples.create5x7GridWith15CardCells;
import static org.junit.Assert.assertThrows;

public class TestThreeTriosModel {
  private Cell[][] grid;
  private List<Card> cards;
  private List<Card> nullCards;
  private Cell[][] jaggedGrid;
  private Cell emptyCell;
  private Cell holeCell;
  private Cell[][] evenCardGrid;
  private List<Card> evenCards;
  private Card cardEx;
  private ThreeTriosModel model;

  @Before
  public void setUp() throws IOException {
    this.grid = create5x7GridWith15CardCells();
    this.cards = create16Cards();
    this.nullCards = null;
    this.emptyCell = Cell.createEmptyCardCell();
    this.holeCell = Cell.createHoleCell();
    this.jaggedGrid = new Cell[][]{
            {this.holeCell, this.holeCell, this.emptyCell},
            {this.emptyCell, this.emptyCell},
            {this.holeCell}
    };
    this.evenCardGrid = new Cell[][]{
            {this.holeCell, this.holeCell},
            {this.holeCell, this.holeCell}
    };
    this.cardEx = new CardImpl(
            "Example", AttackValue.EIGHT, AttackValue.FIVE, AttackValue.TEN, AttackValue.ONE);
    this.evenCards = new ArrayList<>();
    this.evenCards.add(this.cardEx);
    this.evenCards.add(this.cardEx);
    this.model = new ThreeTriosModelImpl(this.grid, this.cards, false);
  }

  @Test
  public void testConstructionWithNullGrid() {
    assertThrows(IllegalArgumentException.class,
            () -> new ThreeTriosModelImpl(null, this.cards, true));
  }

  @Test
  public void testConstructionWithNullCards() {
    assertThrows(IllegalArgumentException.class,
            () -> new ThreeTriosModelImpl(this.grid, null, true));
  }

  @Test
  public void testConstructionWithListWithNullCards() {
    assertThrows(IllegalArgumentException.class,
            () -> new ThreeTriosModelImpl(this.grid, this.nullCards, true));
  }

  @Test
  public void testConstructionWithNonRectangularGrid() {
    assertThrows(IllegalArgumentException.class,
            () -> new ThreeTriosModelImpl(this.jaggedGrid, this.cards, false));
  }

  @Test
  public void testConstructionWithEvenNumberOfCardCells() {
    assertThrows(IllegalArgumentException.class,
            () -> new ThreeTriosModelImpl(this.evenCardGrid, this.cards, false));
  }

  @Test
  public void testConstructionWithWrongNumberOfCards() {
    assertThrows(IllegalArgumentException.class,
            () -> new ThreeTriosModelImpl(this.grid, this.evenCards, true));
  }


  @Test
  public void testModifyingCardAfterConstructionDoesNotAffectModel() {
    List<Card> hand = this.model.getHand(PlayerColor.RED);
    Assert.assertEquals(this.model.getHand(PlayerColor.RED), hand);
    hand.remove(0);
    Assert.assertNotEquals(this.model.getHand(PlayerColor.RED), hand);
  }

  @Test
  public void testModifyingGridAfterConstructionDoesNotAffectModel() {
    Cell[][] grid = this.model.getGrid();
    Assert.assertEquals(this.model.getGrid(), grid);
    grid[0][0] = this.holeCell;
    Assert.assertNotEquals(this.model.getGrid(), grid);
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testPlayCardToOutOfBoundsCell() {
    Assert.assertThrows(IndexOutOfBoundsException.class,
            () -> this.model.playCardAt(5,6, this.cardEx));
  }

  @Test
  public void testPlayCardToEmptyCardCell() {
    Assert.assertNotEquals(this.model.getCellAt(5,6).getCard(),this.cardEx);
    this.model.playCardAt(5, 6, this.cardEx);
    Assert.assertEquals(this.model.getCellAt(5, 6).getCard(),this.cardEx);
  }

  @Test
  public void testPlayCardToOccupiedCardCell() {
    this.model.playCardAt(1, 2, this.cardEx);
    Assert.assertThrows(IllegalStateException.class,
            () -> this.model.playCardAt(1, 2, this.cardEx));
  }

  @Test
  public void testPlayCardToHole() {
    Assert.assertThrows(IllegalStateException.class,
            () -> this.model.playCardAt(0, 2, this.cardEx));
  }

  @Test
  public void testPlayNullCard() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> this.model.playCardAt(1, 2, null));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetHand() {

  }

  @Test
  public void testGetHandNullPlayer() {

  }

  @Test
  public void testPlayCardThenGetHand() {

  }

  @Test
  public void testModifyingHandDoesNotAffectModel() {

  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetGrid() {

  }

  @Test
  public void testPlayCardThenGetGrid() {

  }

  @Test
  public void testModifyingGridDoesNotAffectModel() {

  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCell() {

  }

  @Test
  public void testGetOutOfBoundsCell() {

  }

  @Test
  public void testPlayCardThenGetCell() {

  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testIsGameOverWhenGameIsNotOver() {

  }

  @Test
  public void testIsGameOverWhenGameIsOver() {

  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetWinnerWhenGameIsNotOver() {

  }

  @Test
  public void testGetWinnerWhenGameIsOver() {

  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCurrentPlayer() {

  }

  @Test
  public void testPlayCardThenGetCurrentPlayer() {

  }

  @Test
  public void testGetCurrentPlayerWhenGameIsOver() {

  }
}
