package cs3500.three.trios.model;

import cs3500.three.trios.util.Requirements;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A class for constructing grids from configuration filepaths.
 */
public class GridFactory {

  /**
   * Creates and returns a new grid based on the configuration file at the specified path. The
   * contents of the configuration file must be of the format:
   * <br>ROWS COLS
   * <br>ROW_0
   * <br>ROW_1
   * <br>ROW_2
   * <br>where ROWS and COLS are positive integers representing the number of rows and columns in
   * the grid.
   * <br>and ROW_i is a sequence of COL chars representing the cells of the i'th row of the grid.
   * Each character is either a 'X' (if the cell is a hole) or a 'C' (if the cell is a card).
   *
   * @param configurationFilePath The path of the configuration file to use to create the grid.
   * @return The newly constructed grid.
   * @throws IllegalArgumentException if the configuration file path is null, the file referred to
   *                                  does not exist, or its contents are not of the right format.
   */
  public static Cell[][] createGridFromFilePath(String configurationFilePath) {
    try {
      Requirements.requireNonNull(configurationFilePath);
      Requirements.requireValidFilePath(configurationFilePath);
      Requirements.requireFileIsNotEmpty(configurationFilePath);

      Path path = Paths.get(configurationFilePath);
      List<String> lines = Files.readAllLines(path);

      String firstLine = lines.get(0);
      requireLineHasTwoIntegers(firstLine);

      int numRows = Integer.parseInt(firstLine.split(" ")[0]);
      int numCols = Integer.parseInt(firstLine.split(" ")[1]);
      requireNumLinesEqualsNumRowsPlusOne(lines, numRows);

      Cell[][] grid = new Cell[numRows][numCols];
      for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
        String line = lines.get(rowIndex + 1);
        requireLineLengthEqualsNumCols(line, numCols);
        Cell[] row = getRowFromString(line);
        grid[rowIndex] = row;
      }
      return grid;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Checks that the specified line's length is equal to the specified number of columns. Throws an
   * IllegalArgumentException if not.
   */
  private static void requireLineLengthEqualsNumCols(String line, int numCols) {
    if (line.length() != numCols) {
      throw new IllegalArgumentException("Invalid number of grid columns in line: " + line);
    }
  }

  /**
   * Checks that the specified number of lines is equal to the specified number of rows. Throws an
   * IllegalArgumentException if not.
   */
  private static void requireNumLinesEqualsNumRowsPlusOne(List<String> lines, int numRows) {
    if (lines.size() != numRows + 1) {
      throw new IllegalArgumentException("Invalid number of grid lines");
    }
  }

  /**
   * Checks that the first line of the configuration file has two integers separated by a space.
   * Throws an IllegalArgumentException if not.
   */
  private static void requireLineHasTwoIntegers(String line) {
    Requirements.requireNonNull(line);
    if (!line.matches("\\d+ \\d+")) {
      throw new IllegalArgumentException("Invalid grid line: " + line);
    }
  }

  /**
   * Parses the specified line into a row of cells and returns it.
   *
   * @param line the line to parse into a row of cells.
   * @return the row of cells parsed from the specified line.
   */
  private static Cell[] getRowFromString(String line) {
    int numCols = line.length();
    Cell[] row = new Cell[numCols];
    for (int colIndex = 0; colIndex < numCols; colIndex++) {
      if (line.charAt(colIndex) == 'X') {
        row[colIndex] = Cell.createHoleCell();

      } else if (line.charAt(colIndex) == 'C') {
        row[colIndex] = Cell.createEmptyCardCell();

      } else {
        throw new IllegalArgumentException("Invalid grid line: " + line);
      }
    }
    return row;
  }
}
