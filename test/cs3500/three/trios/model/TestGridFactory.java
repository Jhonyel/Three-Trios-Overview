package cs3500.three.trios.model;

import static org.junit.Assert.assertThrows;

import cs3500.three.trios.Examples;
import cs3500.three.trios.TestUtils;
import java.io.IOException;
import org.junit.Test;

/**
 * A class for testing the GridFactory class.
 */
public class TestGridFactory {

  @Test
  public void testCreateFromConfigurationFilePathWithNullFilePath() {
    assertThrows(
        IllegalArgumentException.class,
        () -> GridFactory.createFromConfigurationFilePath(null)
    );
  }

  @Test
  public void testCreate5x7GridWith15CardCells() throws IOException {
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
    TestUtils.assertArray2DEquals(expectedGrid, actualGrid);
  }

  @Test
  public void testCreate3x5GridWith9CardCells() throws IOException {
    Cell empty = Cell.createEmptyCardCell();
    Cell hole = Cell.createHoleCell();
    Cell[][] expectedGrid = {
        {empty, hole, empty, hole, hole},
        {hole, empty, empty, hole, empty},
        {empty, empty, hole, empty, empty}
    };
    Cell[][] actualGrid = Examples.create3x5GridWith9CardCells();
    TestUtils.assertArray2DEquals(expectedGrid, actualGrid);
  }

  @Test
  public void testCreate5x3GridWith9CardCells() throws IOException {
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
    TestUtils.assertArray2DEquals(expectedGrid, actualGrid);
  }
}
