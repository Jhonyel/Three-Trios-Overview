package cs3500.three.trios;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import cs3500.three.trios.util.Requirements;

/**
 * A class containing useful helper methods for testing.
 */
public class TestUtils {

  /**
   * Asserts that two non-null 2D arrays containing no null elements are equal.
   */
  public static <T> void assertArray2DEquals(T[][] expected, T[][] actual) {
    Requirements.requireNonNullArray(expected);
    Requirements.requireNonNullArray(actual);
    assertEquals(expected.length, actual.length);
    for (int i = 0; i < expected.length; i++) {
      assertArrayEquals(expected[i], actual[i]);
    }
  }
}
