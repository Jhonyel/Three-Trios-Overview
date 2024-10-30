package cs3500.three.trios.model;

import static cs3500.three.trios.model.card.AttackValue.ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the behavior of the Cell class.
 */
public class TestCell {

  private Cell holeCell;
  private Cell emptyCardCell;
  private Cell occupiedCardCell;
  private PlayerCard card;

  @Before
  public void setUp() {
    holeCell = Cell.createHoleCell();
    emptyCardCell = Cell.createEmptyCardCell();

    card = new PlayerCard(
        new CardImpl("name", ONE, ONE, ONE, ONE),
        PlayerColor.RED
    );
    occupiedCardCell = Cell.createOccupiedCardCell(card);
  }

  @Test
  public void testCreateHoleCellCreatesHoleCell() {
    assertTrue(holeCell.isHole());
    assertFalse(holeCell.isCardCell());
    assertFalse(holeCell.isOccupiedCardCell());
    assertFalse(holeCell.isEmptyCardCell());
  }

  @Test
  public void testCreateEmptyCardCellCreatesEmptyCardCell() {
    assertTrue(emptyCardCell.isCardCell());
    assertTrue(emptyCardCell.isEmptyCardCell());
    assertFalse(emptyCardCell.isHole());
    assertFalse(emptyCardCell.isOccupiedCardCell());
  }

  @Test
  public void testCreateOccupiedCardCellCreatesOccupiedCardCell() {
    assertTrue(occupiedCardCell.isCardCell());
    assertTrue(occupiedCardCell.isOccupiedCardCell());
    assertFalse(occupiedCardCell.isHole());
    assertFalse(occupiedCardCell.isEmptyCardCell());
  }

  @Test
  public void testCallGetCardOnEmptyCardCellThrows() {
    assertThrows(IllegalStateException.class, () -> emptyCardCell.getCard());
  }

  @Test
  public void testCallGetCardOnHoleCardCellThrows() {
    assertThrows(IllegalStateException.class, () -> holeCell.getCard());
  }

  @Test
  public void testCallGetCardOnOccupiedCardCellReturnsCard() {
    assertEquals(card, occupiedCardCell.getCard());
  }
}
