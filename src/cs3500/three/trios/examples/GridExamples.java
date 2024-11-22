package cs3500.three.trios.examples;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.GridFactory;
import java.io.File;

/**
 * A class for creating examples of grids.
 */
public class GridExamples {

  /**
   * Returns a 5x7 grid with 15 card cells using the configuration file at
   * configuration-files/5x7-grid-configuration.txt.
   */
  public static Cell[][] create5x7GridWith15CardCells() {
    return GridFactory.createGridFromFilePath(
        "configuration-files" + File.separator + "5x7-grid-configuration.txt");
  }

  /**
   * Returns a 3x5 grid with 9 card cells using the configuration file at
   * configuration-files/3x5-grid-configuration.txt.
   */
  public static Cell[][] create3x5GridWith9CardCells() {
    return GridFactory.createGridFromFilePath(
        "configuration-files" + File.separator + "3x5-grid-configuration.txt");
  }

  /**
   * Returns a 5x3 grid with 9 card cells using the configuration file at
   * configuration-files/5x3-grid-configuration.txt.
   */
  public static Cell[][] create5x3GridWith9CardCells() {
    return GridFactory.createGridFromFilePath(
        "configuration-files" + File.separator + "5x3-grid-configuration.txt");
  }

  /**
   * Returns a 3x3 grid with 9 card cells using the configuration file at
   * configuration-files/3x3-grid-with-no-holes-configuration.txt.
   */
  public static Cell[][] create3x3GridWith9CardCells() {
    return GridFactory.createGridFromFilePath(
        "configuration-files" + File.separator + "3x3-grid-with-no-holes-configuration.txt");
  }

  /**
   * Returns a 4x4 grid with 9 card cells using the configuration file at
   * configuration-files/4x4-disconnected-grid-with-holes.txt.
   */
  public static Cell[][] create4x4GridWith9CardCells() {
    return GridFactory.createGridFromFilePath(
        "configuration-files" + File.separator + "4x4-disconnected-grid-with-holes.txt"
    );
  }
}
