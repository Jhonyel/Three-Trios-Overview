package cs3500.three.trios;

import static cs3500.three.trios.model.Direction.*;
import static cs3500.three.trios.model.card.AttackValue.FOUR;
import static cs3500.three.trios.model.card.AttackValue.ONE;
import static cs3500.three.trios.model.card.AttackValue.THREE;
import static cs3500.three.trios.model.card.AttackValue.TWO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.model.card.AttackValue;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCard {

  private AttackValue exampleNorthAttackValue;
  private AttackValue exampleSouthAttackValue;
  private AttackValue exampleEastAttackValue;
  private AttackValue exampleWestAttackValue;
  private String exampleName;
  private Card exampleCard;

  @Before
  public void setUp() {
    exampleNorthAttackValue = ONE;
    exampleSouthAttackValue = TWO;
    exampleEastAttackValue = THREE;
    exampleWestAttackValue = FOUR;
    exampleName = "name";
    exampleCard = new CardImpl(
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
}
