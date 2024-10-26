package cs3500.three.trios.model.card;

import static cs3500.three.trios.model.Direction.EAST;
import static cs3500.three.trios.model.Direction.NORTH;
import static cs3500.three.trios.model.Direction.SOUTH;
import static cs3500.three.trios.model.Direction.WEST;
import static cs3500.three.trios.model.card.AttackValue.FOUR;
import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.THREE;
import static cs3500.three.trios.model.card.AttackValue.TWO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import cs3500.three.trios.model.PlayerColor;
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
   * Creates an example card for testing. Implementation is delegated to extending classes.
   * Extending classes should return an instance of the class being tested.
   */
  protected abstract Card createExampleCard();

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
  }

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
      return new PlayerCard(card, PlayerColor.RED);
    }

    @Test
    public void testConstructionWithNullArguments() {
      assertThrows(
          IllegalArgumentException.class,
          () -> new PlayerCard(null, PlayerColor.RED)
      );
      assertThrows(
          IllegalArgumentException.class,
          () -> new PlayerCard(exampleCard, null)
      );
    }
  }
}
