package cs3500.three.trios.model;

import java.io.File;

/**
 * A class for constructing ThreeTriosModels from configuration strings, files, or filepaths.
 */
public class ThreeTriosModelFactory {

  /**
   * Creates and returns a new ThreeTriosModel instance based on the configuration string. A
   * configuration string has the format:
   * <br>ROWS COLS
   * <br>ROW_0
   * <br>ROW_1
   * <br>ROW_2
   * <br>ROWS and COLS are positive integers representing the number of rows and columns in the
   * grid.
   * <br>ROW_i is a sequence of COL chars representing the cells of the i'th row of the grid. Each
   * character is either a 'X' (if the cell is a hole) or a 'C' (if the cell is a card).
   *
   * @param configurationString The configuration string to use to create the model.
   * @return The newly constructed model.
   * @throws IllegalArgumentException if the configuration string is null or not of the right
   *                                  format.
   */
  public static ThreeTriosModel createFromConfigurationString(String configurationString) {
    throw new UnsupportedOperationException("not implemented");
  }

  /**
   * Creates and returns a new ThreeTriosModel instance based on the configuration file. The
   * contents of the configuration file must be a configuration string described above.
   *
   * @param configurationFile The configuration file to use to create the model.
   * @return The newly constructed model.
   * @throws IllegalArgumentException if the configuration string is null or not of the right
   *                                  format.
   */
  public static ThreeTriosModel createFromConfigurationFile(File configurationFile) {
    throw new UnsupportedOperationException("not implemented");
  }

  /**
   * Creates and returns a new ThreeTriosModel instance based on the configuration file at the
   * specified path. The contents of the configuration file must be a configuration string described
   * above.
   *
   * @param configurationFilePath The path of the configuration file to use to create the model.
   * @return The newly constructed model.
   * @throws IllegalArgumentException if the configuration string is null or not of the right
   *                                  format.
   */
  public static ThreeTriosModel createFromConfigurationFilePath(String configurationFilePath) {
    throw new UnsupportedOperationException("not implemented");
  }
}
