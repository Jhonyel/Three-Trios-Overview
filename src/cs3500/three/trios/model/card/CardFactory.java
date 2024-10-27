package cs3500.three.trios.model.card;

import java.io.File;
import java.util.List;

/**
 * A class for constructing Cards from configuration strings, files, or filepaths.
 */
public class CardFactory {

  /**
   * Creates and returns a new List of Cards based on the configuration string. A configuration
   * string is a line with an arbitrary number of lines, each line of the format:
   * <br>CARD_NAME NORTH SOUTH EAST WEST
   * <br>where CARD_NAME
   *
   * @param configurationString the file that is to be read by this method
   * @return a list of cards that represents the starting configuration of the cards for the game
   */
  public static List<Card> fromConfigurationString(String configurationString) {
    throw new UnsupportedOperationException("not implemented");
  }

  public static List<Card> fromConfigurationFile(File configurationFile) {
    throw new UnsupportedOperationException("not implemented");
  }

  public static List<Card> fromConfigurationFilePath(String configurationFilePath) {
    throw new UnsupportedOperationException("not implemented");
  }

}
