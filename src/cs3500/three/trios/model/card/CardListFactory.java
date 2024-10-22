package cs3500.three.trios.model.card;

import java.util.List;

/**
 * A class for constructing lists of cards from configuration filepaths.
 */
public class CardListFactory {

  /**
   * Creates and returns a new list of cards based on the configuration file at the specified path.
   * Each line in the configuration file represents a card and must be of the format:
   * <br>CARD_NAME NORTH SOUTH EAST WEST
   * <br>where CARD_NAME is the name of the card, and NORTH SOUTH EAST and WEST are the attack
   * values in each direction
   *
   * @param configurationFilePath The path of the configuration file to use to create the list of
   *                              cards.
   * @return The newly constructed list of cards.
   * @throws IllegalArgumentException if the configuration file path is null, the file referred to
   *                                  does not exist, or its contents are not of the right format.
   */
  public static List<Card> createFromConfigurationFilePath(String configurationFilePath) {
    throw new UnsupportedOperationException("not implemented");
  }

}
