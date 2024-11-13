package cs3500.three.trios.model.card;

import cs3500.three.trios.util.Requirements;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
   * @throws IOException              if an I/O error occurs while reading the file.
   */
  public static List<Card> createFromConfigurationFilePath(String configurationFilePath)
      throws IOException {

    Requirements.requireNonNull(configurationFilePath);
    Requirements.requireValidFilePath(configurationFilePath);
    Path path = Paths.get(configurationFilePath);
    List<String> lines = Files.readAllLines(path);
    List<Card> cards = new ArrayList<>();
    for (String line : lines) {
      Card card = new CardImpl(line);
      cards.add(card);
    }
    return cards;
  }
}
