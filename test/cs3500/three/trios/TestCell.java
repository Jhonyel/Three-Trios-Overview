package cs3500.three.trios;

import static cs3500.three.trios.model.card.AttackValue.ONE;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.card.CardImpl;
import cs3500.three.trios.model.card.PlayerCard;
import org.junit.Before;
import org.junit.Test;

public class TestCell {

  private Cell holeCell;
  private Cell emptyCardCell;
  private Cell occupiedCardCell;

  @Before
  public void setUp() {
    holeCell = Cell.createHoleCell();
    emptyCardCell = Cell.createEmptyCardCell();
    occupiedCardCell = Cell.createOccupiedCardCell(
        new PlayerCard(
            new CardImpl("name", ONE, ONE, ONE, ONE),
            PlayerColor.RED
        )
    );
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
  public void testCreateCardCellCreatesCardCell() {
    assertTrue(occupiedCardCell.isCardCell());
    assertTrue(occupiedCardCell.isOccupiedCardCell());
    assertFalse(occupiedCardCell.isHole());
    assertFalse(occupiedCardCell.isEmptyCardCell());
  }
}
