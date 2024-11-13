package cs3500.three.trios.model;

import static cs3500.three.trios.model.Direction.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * A class for testing the Direction enum.
 */
public class TestDirection {

  @Test
  public void testGetOppositeDirection() {
    assertEquals(WEST, EAST.getOppositeDirection());
    assertEquals(EAST, WEST.getOppositeDirection());
    assertEquals(NORTH, SOUTH.getOppositeDirection());
    assertEquals(SOUTH, NORTH.getOppositeDirection());
  }

  @Test
  public void testGetRowOffset() {
    assertEquals(0, EAST.getRowOffset());
    assertEquals(0, WEST.getRowOffset());
    assertEquals(-1, NORTH.getRowOffset());
    assertEquals(1, SOUTH.getRowOffset());
  }

  @Test
  public void testGetColOffset() {
    assertEquals(1, EAST.getColOffset());
    assertEquals(-1, WEST.getColOffset());
    assertEquals(0, NORTH.getColOffset());
    assertEquals(0, SOUTH.getColOffset());
  }
}
