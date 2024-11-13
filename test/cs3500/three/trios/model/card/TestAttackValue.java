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

import org.junit.Test;

/**
 * A class to test the AttackValue enum.
 */
public class TestAttackValue {
  @Test
  public void testToString() {
    assertEquals("1", ONE.toString());
    assertEquals("2", TWO.toString());
    assertEquals("3", THREE.toString());
    assertEquals("4", FOUR.toString());
    assertEquals("5", FIVE.toString());
    assertEquals("6", SIX.toString());
    assertEquals("7", SEVEN.toString());
    assertEquals("8", EIGHT.toString());
    assertEquals("9", NINE.toString());
    assertEquals("A", TEN.toString());
  }

  @Test
  public void testToInt() {
    assertEquals(1, ONE.toInt());
    assertEquals(2, TWO.toInt());
    assertEquals(3, THREE.toInt());
    assertEquals(4, FOUR.toInt());
    assertEquals(5, FIVE.toInt());
    assertEquals(6, SIX.toInt());
    assertEquals(7, SEVEN.toInt());
    assertEquals(8, EIGHT.toInt());
    assertEquals(9, NINE.toInt());
    assertEquals(10, TEN.toInt());
  }

  @Test
  public void testFromHexadecimalDigit() {
    assertEquals(ONE, AttackValue.fromHexadecimalDigit('1'));
    assertEquals(TWO, AttackValue.fromHexadecimalDigit('2'));
    assertEquals(THREE, AttackValue.fromHexadecimalDigit('3'));
    assertEquals(FOUR, AttackValue.fromHexadecimalDigit('4'));
    assertEquals(FIVE, AttackValue.fromHexadecimalDigit('5'));
    assertEquals(SIX, AttackValue.fromHexadecimalDigit('6'));
    assertEquals(SEVEN, AttackValue.fromHexadecimalDigit('7'));
    assertEquals(EIGHT, AttackValue.fromHexadecimalDigit('8'));
    assertEquals(NINE, AttackValue.fromHexadecimalDigit('9'));
    assertEquals(TEN, AttackValue.fromHexadecimalDigit('A'));
    assertThrows(IllegalArgumentException.class, () -> AttackValue.fromHexadecimalDigit('B'));
    assertThrows(IllegalArgumentException.class, () -> AttackValue.fromHexadecimalDigit(null));

    assertEquals(ONE, AttackValue.fromHexadecimalDigit("1"));
    assertEquals(TWO, AttackValue.fromHexadecimalDigit("2"));
    assertEquals(THREE, AttackValue.fromHexadecimalDigit("3"));
    assertEquals(FOUR, AttackValue.fromHexadecimalDigit("4"));
    assertEquals(FIVE, AttackValue.fromHexadecimalDigit("5"));
    assertEquals(SIX, AttackValue.fromHexadecimalDigit("6"));
    assertEquals(SEVEN, AttackValue.fromHexadecimalDigit("7"));
    assertEquals(EIGHT, AttackValue.fromHexadecimalDigit("8"));
    assertEquals(NINE, AttackValue.fromHexadecimalDigit("9"));
    assertEquals(TEN, AttackValue.fromHexadecimalDigit("A"));
    assertThrows(IllegalArgumentException.class, () -> AttackValue.fromHexadecimalDigit("B"));
    assertThrows(IllegalArgumentException.class, () -> AttackValue.fromHexadecimalDigit("foobar"));
    assertThrows(IllegalArgumentException.class, () -> AttackValue.fromHexadecimalDigit(null));
  }
}
