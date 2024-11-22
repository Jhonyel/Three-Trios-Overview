package cs3500.three.trios.examples;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardListFactory;
import java.io.File;
import java.util.List;

/**
 * A class containing examples of card lists.
 */
public class CardListExamples {

  /**
   * Returns a list of 16 cards using the configuration file at
   * configuration-files/16-cards-configuration.txt.
   */
  public static List<Card> create16Cards() {
    return CardListFactory.createCardListFromFilePath(
        "configuration-files" + File.separator + "16-cards-configuration.txt");
  }

  /**
   * Returns a list of 10 cards using the configuration file at
   * configuration-files/10-cards-configuration.txt.
   */
  public static List<Card> create10Cards() {
    return CardListFactory.createCardListFromFilePath(
        "configuration-files" + File.separator + "10-cards-configuration.txt");
  }
}
