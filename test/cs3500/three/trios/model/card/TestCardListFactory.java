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

import cs3500.three.trios.Examples;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestCardListFactory {

  @Test
  public void testCreateFromConfigurationFilePathWithNullFilePath() {
    Assert.assertThrows(
        IllegalArgumentException.class,
        () -> CardListFactory.createFromConfigurationFilePath(null)
    );
  }

  @Test
  public void testCreate10CardsFromConfigurationFilePath() throws IOException {
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
    List<Card> actualCards = Examples.create10Cards();
    assertEquals(expectedCards, actualCards);
  }

  @Test
  public void testCreate16CardsFromConfigurationFilePath() throws IOException {
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
    List<Card> actualCards = Examples.create16Cards();
    assertEquals(expectedCards, actualCards);
  }
}
