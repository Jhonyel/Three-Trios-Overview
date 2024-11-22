package cs3500.three.trios.model.card;

import static cs3500.three.trios.model.card.AttackValue.EIGHT;
import static cs3500.three.trios.model.card.AttackValue.FIVE;
import static cs3500.three.trios.model.card.AttackValue.FOUR;
import static cs3500.three.trios.model.card.AttackValue.NINE;
import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.SEVEN;
import static cs3500.three.trios.model.card.AttackValue.SIX;
import static cs3500.three.trios.model.card.AttackValue.TEN;
import static cs3500.three.trios.model.card.AttackValue.THREE;
import static cs3500.three.trios.model.card.AttackValue.TWO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import cs3500.three.trios.examples.CardListExamples;
import java.io.File;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * A class for testing CardListFactory.
 */
public class TestCardListFactory {

  @Test
  public void testCreateFromConfigurationFilePathWithNullFilePath() {
    Assert.assertThrows(
        IllegalArgumentException.class,
        () -> CardListFactory.createCardListFromFilePath(null)
    );
  }

  @Test
  public void testCreate10CardsFromConfigurationFilePath() {
    List<Card> expectedCards = List.of(
        new CardImpl("BlazingTiger", TEN, SEVEN, THREE, FIVE),
        new CardImpl("FrozenWolf", NINE, ONE, EIGHT, SIX),
        new CardImpl("SilentEagle", FIVE, FOUR, TEN, SEVEN),
        new CardImpl("SwiftLion", SIX, THREE, NINE, TWO),
        new CardImpl("MightyRhino", FOUR, EIGHT, SEVEN, ONE),
        new CardImpl("GoldenFalcon", TEN, FIVE, TWO, NINE),
        new CardImpl("ShadowBear", TWO, TEN, SIX, FOUR),
        new CardImpl("CunningFox", EIGHT, SEVEN, FOUR, THREE),
        new CardImpl("FierceCobra", NINE, SIX, THREE, ONE),
        new CardImpl("GentleDove", THREE, TWO, NINE, FIVE)
    );
    List<Card> actualCards = CardListExamples.create10Cards();
    assertEquals(expectedCards, actualCards);
  }

  @Test
  public void testCreate16CardsFromConfigurationFilePath() {
    List<Card> expectedCards = List.of(
        new CardImpl("BlazingPhoenix", NINE, SEVEN, TEN, FIVE),
        new CardImpl("FrozenDragon", SIX, TEN, FOUR, EIGHT),
        new CardImpl("ThunderLion", THREE, NINE, FIVE, TEN),
        new CardImpl("ShadowWolf", TEN, TWO, SEVEN, SIX),
        new CardImpl("MysticGolem", EIGHT, FOUR, TEN, THREE),
        new CardImpl("CrimsonSerpent", SEVEN, SIX, NINE, TEN),
        new CardImpl("EtherealKnight", FIVE, TEN, THREE, SEVEN),
        new CardImpl("LunarBear", TEN, NINE, EIGHT, TWO),
        new CardImpl("RadiantTiger", FOUR, SEVEN, SIX, TEN),
        new CardImpl("PhantomRaven", TWO, FIVE, TEN, EIGHT),
        new CardImpl("GoldenHawk", TEN, SIX, THREE, NINE),
        new CardImpl("SavageBeast", NINE, EIGHT, TEN, FOUR),
        new CardImpl("CursedGiant", SEVEN, FIVE, FOUR, TEN),
        new CardImpl("FeralFox", TEN, THREE, NINE, SIX),
        new CardImpl("VenomousSpider", SIX, TEN, SEVEN, THREE),
        new CardImpl("IronTurtle", TEN, EIGHT, FIVE, NINE)
    );
    List<Card> actualCards = CardListExamples.create16Cards();
    assertEquals(expectedCards, actualCards);
  }

  @Test
  public void testCreateCardsFromConfigurationFilePathWithInvalidFilePath() {
    assertThrows(
        IllegalArgumentException.class,
        () -> CardListFactory.createCardListFromFilePath("invalid-file-path")
    );
  }

  @Test
  public void testCreateCardsFromConfigurationFilePathWithEmptyFile() {
    assertEquals(
        List.of(),
        CardListFactory.createCardListFromFilePath(
            "configuration-files" + File.separator + "empty-file.txt")
    );
  }

  @Test
  public void testCreateCardsFromConfigurationFilePathWithInvalidFile() {
    assertThrows(
        IllegalArgumentException.class,
        () -> CardListFactory.createCardListFromFilePath(
            "configuration-files" + File.separator + "invalid-cards-configuration.txt")
    );
  }
}
