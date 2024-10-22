package cs3500.three.trios;

import static cs3500.three.trios.util.Requirements.requireNonNullArray;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestUtils {

  /**
   * Asserts that two non-null 2D arrays containing no null elements are equal.
   */
  public static <T> void assert2DArrayEquals(T[][] expected, T[][] actual) {
    requireNonNullArray(expected);
    requireNonNullArray(actual);
    assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertArrayEquals(expected[i], actual[i]);
    }
  }
}
