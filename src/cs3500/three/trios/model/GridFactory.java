package cs3500.three.trios.model;

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
  public static Cell[][] createFromConfigurationFilePath(String configurationFilePath) {
    throw new UnsupportedOperationException("not implemented");
  }
}
