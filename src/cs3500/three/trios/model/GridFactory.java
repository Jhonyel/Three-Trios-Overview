package cs3500.three.trios.model;

import static cs3500.three.trios.util.Requirements.requireFileIsNotEmpty;
import static cs3500.three.trios.util.Requirements.requireNonNull;
import static cs3500.three.trios.util.Requirements.requireValidFilePath;

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
  public static Cell[][] createFromConfigurationFilePath(String configurationFilePath)
      throws IOException {
    requireNonNull(configurationFilePath);
    requireValidFilePath(configurationFilePath);
    requireFileIsNotEmpty(configurationFilePath);

    Path path = Paths.get(configurationFilePath);
    List<String> lines = Files.readAllLines(path);

    // we remove the first line so that the lines list only contains the row strings
    String firstLine = lines.remove(0);
    requireFirstLineHasTwoIntegers(firstLine);

    int numRows = Integer.parseInt(firstLine.split(" ")[0]);
    int numCols = Integer.parseInt(firstLine.split(" ")[1]);
    requireNumLinesEqualsNumRows(lines, numRows);

    Cell[][] grid = new Cell[numRows][numCols];
    for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
      String line = lines.get(rowIndex);
      requireLineLengthEqualsNumCols(line, numCols);
      Cell[] row = getRowFromString(line);
      grid[rowIndex] = row;
    }
    return grid;
  }

  private static void requireLineLengthEqualsNumCols(String line, int numCols) {
    if (line.length() != numCols) {
      throw new IllegalArgumentException("Invalid number of grid columns in line: " + line);
    }
  }

  private static void requireNumLinesEqualsNumRows(List<String> lines, int numRows) {
    if (lines.size() != numRows) {
      throw new IllegalArgumentException("Invalid number of grid lines");
    }
  }

  private static void requireFirstLineHasTwoIntegers(String line) {
    requireNonNull(line);
    if (!line.matches("\\d+\\s+\\d+")) {
      throw new IllegalArgumentException("Invalid grid line: " + line);
    }
  }

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
