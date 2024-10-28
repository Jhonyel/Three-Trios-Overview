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
import cs3500.three.trios.model.card.PlayerCard;

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
  private Cell[][] smallGrid;
  private ThreeTriosModel smallModel;
  private PlayerCard playerCard;

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
    this.smallGrid = new Cell[][]{
            {this.holeCell, this.emptyCell},
            {this.emptyCell, this.emptyCell}
    };
    this.smallModel = new ThreeTriosModelImpl(this.smallGrid, this.cards, true);
    this.playerCard = new PlayerCard(this.cardEx, PlayerColor.BLUE);
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
            () -> this.model.playCardAt(5,6, this.playerCard));
  }

  @Test
  public void testPlayCardToEmptyCardCell() {
    Assert.assertNotEquals(this.model.getCellAt(4,6).getCard(),this.cardEx);
    this.model.playCardAt(4, 6, this.playerCard);
    Assert.assertEquals(this.model.getCellAt(4, 6).getCard(),this.cardEx);
  }

  @Test
  public void testPlayCardToOccupiedCardCell() {
    this.model.playCardAt(1, 2, this.playerCard);
    Assert.assertThrows(IllegalStateException.class,
            () -> this.model.playCardAt(1, 2, this.playerCard));
  }

  @Test
  public void testPlayCardToHole() {
    Assert.assertThrows(IllegalStateException.class,
            () -> this.model.playCardAt(0, 2, this.playerCard));
  }

  @Test
  public void testPlayNullCard() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> this.model.playCardAt(1, 2, null));
  }

  @Test
  public void testPlayAfterGameEnds() {
    this.smallModel.playCardAt(0, 1, this.playerCard);
    this.smallModel.playCardAt(1, 1, this.playerCard);
    this.smallModel.playCardAt(1, 0, this.playerCard);
    Assert.assertThrows(IllegalStateException.class,
            () -> this.smallModel.playCardAt(1, 2, this.playerCard));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetHand() {
    List<PlayerCard> cards = new ArrayList<>();
    for (int i = 0; i < this.cards.size()/2; i++) {
      cards.add(new PlayerCard(this.cards.get(i), PlayerColor.RED));
    }
    Assert.assertEquals(cards, this.model.getHand(PlayerColor.RED));
  }

  @Test
  public void testGetHandNullPlayer() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> this.model.getHand(null));
  }

  @Test
  public void testPlayCardThenGetHand() {
    List<PlayerCard> cards = new ArrayList<>();
    for (int i = 0; i < this.cards.size()/2; i++) {
      cards.add(new PlayerCard(this.cards.get(i), PlayerColor.RED));
    }
    this.model.playCardAt(0, 1, cards.remove(0));
    Assert.assertEquals(cards, this.model.getHand(PlayerColor.RED));
  }

  @Test
  public void testModifyingHandDoesNotAffectModel() {
    List<PlayerCard> hand = this.model.getHand(PlayerColor.RED);
    Assert.assertEquals(this.model.getHand(PlayerColor.RED), hand);
    hand.remove(0);
    Assert.assertNotEquals(this.model.getHand(PlayerColor.RED), hand);
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetGrid() {
    Cell[][] grid = this.model.getGrid();
    Assert.assertEquals(this.model.getGrid(), grid);
  }

  @Test
  public void testPlayCardThenGetGrid() {
    Cell[][] grid = this.model.getGrid();
    Assert.assertEquals(this.model.getGrid(), grid);
    this.model.playCardAt(4, 6, this.playerCard);
    Assert.assertNotEquals(this.model.getGrid(), grid);
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCell() {
    Cell cell = this.model.getCellAt(4, 6);
    Assert.assertEquals(this.model.getCellAt(4, 6), cell);
  }

  @Test
  public void testGetOutOfBoundsCell() {
    Assert.assertThrows(IndexOutOfBoundsException.class,
            () -> this.model.getCellAt(8, 6));
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testIsGameOverWhenGameIsNotOver() {
    Assert.assertFalse(this.model.isGameOver());
  }

  @Test
  public void testIsGameOverWhenGameIsOver() {
    this.smallModel.playCardAt(0, 1, this.playerCard);
    this.smallModel.playCardAt(1, 1, this.playerCard);
    this.smallModel.playCardAt(1, 0, this.playerCard);
    Assert.assertTrue(this.smallModel.isGameOver());
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetWinnerWhenGameIsNotOver() {
    Assert.assertThrows(IllegalStateException.class,
            () -> this.model.getWinner());
  }

  @Test
  public void testGetWinnerWhenGameIsOver() {
    this.smallModel.playCardAt(0, 1, this.playerCard);
    this.smallModel.playCardAt(1, 1, this.playerCard);
    this.smallModel.playCardAt(1, 0, this.playerCard);
    Assert.assertEquals(this.model.getWinner(), PlayerColor.BLUE);
  }

  ////////////////////////////////////////////////////////////////////////////

  @Test
  public void testGetCurrentPlayer() {
    PlayerColor color = PlayerColor.RED;
    Assert.assertEquals(color, this.model.getCurrentPlayer());
  }

  @Test
  public void testPlayCardThenGetCurrentPlayer() {
    PlayerColor color = PlayerColor.RED;
    Assert.assertEquals(color, this.model.getCurrentPlayer());
    this.model.playCardAt(4, 6, this.playerCard);
    PlayerColor color2 = PlayerColor.BLUE;
    Assert.assertEquals(color2, this.model.getCurrentPlayer());
  }

  @Test
  public void testGetCurrentPlayerWhenGameIsOver() {
    this.smallModel.playCardAt(0, 1, this.playerCard);
    this.smallModel.playCardAt(1, 1, this.playerCard);
    this.smallModel.playCardAt(1, 0, this.playerCard);
    Assert.assertThrows(IllegalStateException.class,
            () -> this.smallModel.getCurrentPlayer());
  }
}
