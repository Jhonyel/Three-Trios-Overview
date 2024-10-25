package cs3500.three.trios;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;

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

  }


  @Test
  public void testModifyingCardAfterConstructionDoesNotAffectModel() {

  }

  @Test
  public void testModifyingGridAfterConstructionDoesNotAffectModel() {

  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testPlayCardToOutOfBoundsCell() {

  }

  @Test
  public void testPlayCardToEmptyCardCell() {

  }

  @Test
  public void testPlayCardToOccupiedCardCell() {

  }

  @Test
  public void testPlayCardToHole() {

  }

  @Test
  public void testPlayNullCard() {

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
