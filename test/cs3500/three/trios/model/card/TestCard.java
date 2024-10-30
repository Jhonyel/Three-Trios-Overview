package cs3500.three.trios.model.card;

import static cs3500.three.trios.model.Direction.EAST;
import static cs3500.three.trios.model.Direction.NORTH;
import static cs3500.three.trios.model.Direction.SOUTH;
import static cs3500.three.trios.model.Direction.WEST;
import static cs3500.three.trios.model.PlayerColor.BLUE;
import static cs3500.three.trios.model.PlayerColor.RED;
import static cs3500.three.trios.model.card.AttackValue.FOUR;
import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.THREE;
import static cs3500.three.trios.model.card.AttackValue.TWO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import cs3500.three.trios.model.Direction;
import org.junit.Before;
import org.junit.Test;

/**
 * An abstract class to test implementing classes of the Card interface.
 */
public abstract class TestCard {

  protected AttackValue exampleNorthAttackValue;
  protected AttackValue exampleSouthAttackValue;
  protected AttackValue exampleEastAttackValue;
  protected AttackValue exampleWestAttackValue;
  protected String exampleName;
  protected Card exampleCard;

  @Before
  public void setUp() {
    exampleNorthAttackValue = ONE;
    exampleSouthAttackValue = TWO;
    exampleEastAttackValue = THREE;
    exampleWestAttackValue = FOUR;
    exampleName = "name";
    exampleCard = createExampleCard();
  }

  /**
   * Creates and returns an example card for testing. Implementation is delegated to extending
   * classes. Extending classes should return an instance of the class being tested.
   */
  protected abstract Card createExampleCard();

  /**
   * Creates and returns a card with the given name and attack values. Implementation is delegated
   * to extending  classes. Extending classes should return an instance of the class being tested.
   */
  protected abstract Card createCard(
      String name,
      AttackValue northAttackValue,
      AttackValue southAttackValue,
      AttackValue eastAttackValue,
      AttackValue westAttackValue
  );

  @Test
  public void testGetNorthAttackValue() {
    assertEquals(exampleNorthAttackValue, exampleCard.getNorthAttackValue());
  }

  @Test
  public void testGetSouthAttackValue() {
    assertEquals(exampleSouthAttackValue, exampleCard.getSouthAttackValue());
  }

  @Test
  public void testGetEastAttackValue() {
    assertEquals(exampleEastAttackValue, exampleCard.getEastAttackValue());
  }

  @Test
  public void testGetWestAttackValue() {
    assertEquals(exampleWestAttackValue, exampleCard.getWestAttackValue());
  }

  @Test
  public void testGetAttackValue() {
    // assert that calling getNorthAttackValue is the same as calling getAttackValue(NORTH)
    // assert the same is true for all other directions
    assertEquals(exampleNorthAttackValue, exampleCard.getAttackValue(NORTH));
    assertEquals(exampleSouthAttackValue, exampleCard.getAttackValue(SOUTH));
    assertEquals(exampleEastAttackValue, exampleCard.getAttackValue(EAST));
    assertEquals(exampleWestAttackValue, exampleCard.getAttackValue(WEST));
  }

  @Test
  public void testGetAttackValueNullDirection() {
    assertThrows(
        IllegalArgumentException.class,
        () -> exampleCard.getAttackValue(null)
    );
  }

  @Test
  public void testBeats() {
    Card card2222 = createCard("name", TWO, TWO, TWO, TWO);
    Card card3333 = createCard("name", THREE, THREE, THREE, THREE);
    Card card4222 = createCard("name", FOUR, TWO, TWO, TWO);

    for (Direction direction : Direction.values()) {
      assertTrue(card3333.beats(card2222, direction));
      assertFalse(card2222.beats(card3333, direction));
      assertFalse(card3333.beats(card3333, direction));
    }

    assertTrue(card4222.beats(card3333, NORTH));
    assertTrue(card4222.beats(card4222, NORTH));
    assertFalse(card4222.beats(card3333, SOUTH));
    assertFalse(card4222.beats(card3333, EAST));
    assertFalse(card4222.beats(card3333, WEST));
  }

  /**
   * A subclass of TestCard to test CardImpl.
   */
  public static class TestCardImpl extends TestCard {

    @Override
    protected Card createExampleCard() {
      return new CardImpl(
          exampleName,
          exampleNorthAttackValue,
          exampleSouthAttackValue,
          exampleEastAttackValue,
          exampleWestAttackValue
      );
    }

    @Override
    protected Card createCard(
        String name,
        AttackValue northAttackValue,
        AttackValue southAttackValue,
        AttackValue eastAttackValue,
        AttackValue westAttackValue
    ) {
      return new CardImpl(
          name,
          northAttackValue,
          southAttackValue,
          eastAttackValue,
          westAttackValue
      );
    }

    @Test
    public void testConstructionWithNullArguments() {
      assertThrows(
          IllegalArgumentException.class,
          () -> new CardImpl(null, ONE, ONE, ONE, ONE)
      );
      assertThrows(
          IllegalArgumentException.class,
          () -> new CardImpl("name", null, ONE, ONE, ONE)
      );
      assertThrows(
          IllegalArgumentException.class,
          () -> new CardImpl("name", ONE, null, ONE, ONE)
      );
      assertThrows(
          IllegalArgumentException.class,
          () -> new CardImpl("name", ONE, ONE, null, ONE)
      );
      assertThrows(
          IllegalArgumentException.class,
          () -> new CardImpl("name", ONE, ONE, ONE, null)
      );

    }

    @Test
    public void testEquals() {
      assertEquals(
          new CardImpl("name", ONE, ONE, ONE, ONE),
          new CardImpl("name", ONE, ONE, ONE, ONE)
      );
      assertNotEquals(
          new CardImpl("name", ONE, ONE, ONE, ONE),
          new CardImpl("name", TWO, ONE, ONE, ONE));
    }
  }

  /**
   * A subclass of TestCard to test PlayerCard.
   */
  public static class TestPlayerCard extends TestCard {

    @Override
    protected Card createExampleCard() {
      CardImpl card = new CardImpl(
          exampleName,
          exampleNorthAttackValue,
          exampleSouthAttackValue,
          exampleEastAttackValue,
          exampleWestAttackValue
      );
      return new PlayerCard(card, RED);
    }

    @Override
    protected Card createCard(
        String name,
        AttackValue northAttackValue,
        AttackValue southAttackValue,
        AttackValue eastAttackValue,
        AttackValue westAttackValue
    ) {
      return new PlayerCard(
          name,
          northAttackValue,
          southAttackValue,
          eastAttackValue,
          westAttackValue,
          RED
      );
    }

    @Test
    public void testConstructionWithNullArguments() {
      assertThrows(
          IllegalArgumentException.class,
          () -> new PlayerCard(null, RED)
      );
      assertThrows(
          IllegalArgumentException.class,
          () -> new PlayerCard(exampleCard, null)
      );
    }

    @Test
    public void testEquals() {
      assertEquals(
          new PlayerCard(exampleCard, RED),
          new PlayerCard(exampleCard, RED)
      );
      assertNotEquals(
          new PlayerCard(exampleCard, RED),
          new PlayerCard(exampleCard, BLUE)
      );
      assertNotEquals(
          new PlayerCard("name", ONE, ONE, ONE, ONE, RED),
          new PlayerCard("name", TWO, ONE, ONE, ONE, RED)
      );
    }
  }
}
