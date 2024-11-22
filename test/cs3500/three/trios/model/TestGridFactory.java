package cs3500.three.trios.model;

import static org.junit.Assert.assertThrows;

import cs3500.three.trios.TestUtils;
import cs3500.three.trios.examples.GridExamples;
import org.junit.Assert;
import org.junit.Test;

/**
 * A class for testing the GridFactory class. Some tests include the statement: assertNotNull(...).
 * This is because we use helper test methods that use assert methods rather than using assert
 * statements in the test bodies themselves. However, the style checker requires that all test
 * bodies include at least one assert statement, including those that use helper test methods. Thus,
 * we assertNotNull to comply with the style checker.
 */
public class TestGridFactory {

  @Test
  public void testCreateFromConfigurationFilePathWithNullFilePath() {
    assertThrows(
        IllegalArgumentException.class,
        () -> GridFactory.createGridFromFilePath(null)
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
    Cell[][] actualGrid = GridExamples.create5x7GridWith15CardCells();
    TestUtils.assertArray2DEquals(expectedGrid, actualGrid);

    Assert.assertNotNull(actualGrid); // for style checker. see class javadoc
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
    Cell[][] actualGrid = GridExamples.create3x5GridWith9CardCells();
    TestUtils.assertArray2DEquals(expectedGrid, actualGrid);

    Assert.assertNotNull(actualGrid); // for style checker. see class javadoc
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
    Cell[][] actualGrid = GridExamples.create5x3GridWith9CardCells();
    TestUtils.assertArray2DEquals(expectedGrid, actualGrid);

    Assert.assertNotNull(actualGrid); // for style checker. see class javadoc
  }
}
