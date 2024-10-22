package cs3500.three.trios;

import static cs3500.three.trios.TestUtils.assert2DArrayEquals;
import static org.junit.Assert.assertThrows;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.GridFactory;
import org.junit.Test;

public class TestGridFactory {

  @Test
  public void testCreateFromConfigurationFilePathWithNullFilePath() {
    assertThrows(
        IllegalArgumentException.class,
        () -> GridFactory.createFromConfigurationFilePath(null)
    );
  }

  @Test
  public void testCreate5x7GridWith15CardCells() {
    Cell empty = Cell.createEmptyCardCell();
    Cell hole = Cell.createHoleCell();
    Cell[][] expectedGrid = {
        {empty, empty, hole, hole, hole, hole, empty},
        {empty, hole, empty, hole, hole, hole, empty},
        {empty, hole, hole, empty, hole, hole, empty},
        {empty, hole, hole, hole, empty, hole, empty},
        {empty, hole, hole, hole, hole, empty, empty}
    };
    Cell[][] actualGrid = Examples.create5x7GridWith15CardCells();
    assert2DArrayEquals(expectedGrid, actualGrid);
  }

  @Test
  public void testCreate3x5GridWith9CardCells() {
    Cell empty = Cell.createEmptyCardCell();
    Cell hole = Cell.createHoleCell();
    Cell[][] expectedGrid = {
        {empty, hole, empty, hole, hole},
        {hole, empty, empty, hole, empty},
        {empty, empty, hole, empty, empty}
    };
    Cell[][] actualGrid = Examples.create3x5GridWith9CardCells();
    assert2DArrayEquals(expectedGrid, actualGrid);
  }

  @Test
  public void testCreate5x3GridWith9CardCells() {
    Cell empty = Cell.createEmptyCardCell();
    Cell hole = Cell.createHoleCell();
    Cell[][] expectedGrid = {
        {hole, empty, empty},
        {empty, hole, empty},
        {empty, hole, empty},
        {hole, empty, hole},
        {empty, hole, empty}
    };
    Cell[][] actualGrid = Examples.create5x3GridWith9CardCells();
    assert2DArrayEquals(expectedGrid, actualGrid);
  }
}
