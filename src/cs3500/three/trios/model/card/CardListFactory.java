package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;
import static cs3500.three.trios.util.Requirements.requireValidFilePath;

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

    requireNonNull(configurationFilePath);
    requireValidFilePath(configurationFilePath);
    Path path = Paths.get(configurationFilePath);
    List<String> lines = Files.readAllLines(path);
    List<Card> cards = new ArrayList<>();
    for (String line : lines) {
      requireLineHasFiveTokens(line);
      Card card = getCardFromString(line);
      cards.add(card);
    }
    return cards;
  }

  /**
   * Returns a card based on the given line of the configuration file. `line` is assumed to be in
   * the format:
   * <br>CARD_NAME NORTH SOUTH EAST WEST
   * <br>where CARD_NAME is the name of the card, and NORTH SOUTH EAST and WEST are the attack
   * values in each direction
   */
  private static Card getCardFromString(String line) {
    String[] tokens = line.split(" ");
    String cardName = tokens[0];
    AttackValue northAttackValue = AttackValue.fromHexadecimalDigit(tokens[1]);
    AttackValue southAttackValue = AttackValue.fromHexadecimalDigit(tokens[2]);
    AttackValue eastAttackValue = AttackValue.fromHexadecimalDigit(tokens[3]);
    AttackValue westAttackValue = AttackValue.fromHexadecimalDigit(tokens[4]);
    return new CardImpl(
        cardName,
        northAttackValue,
        southAttackValue,
        eastAttackValue,
        westAttackValue
    );
  }

  /**
   * Checks that `line` has 5 tokens. Throws an IllegalArgumentException if not.
   */
  private static void requireLineHasFiveTokens(String line) {
    String[] tokens = line.split(" ");
    if (tokens.length != 5) {
      throw new IllegalArgumentException("Invalid card line: " + line);
    }
  }
}
