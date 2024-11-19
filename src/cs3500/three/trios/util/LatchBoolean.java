package cs3500.three.trios.util;

/**
 * A wrapper class for a boolean value that is initially set to false, can be set to true, but
 * cannot be set back to false after being set to true.
 */
public class LatchBoolean {

  private final Latch<Boolean> latchBoolean;

  /**
   * Creates a LatchBoolean with the value set to false.
   */
  public LatchBoolean() {
    latchBoolean = new Latch<>(false);
  }

  /**
   * Returns true if this LatchBoolean is true, false otherwise.
   */
  public boolean isTrue() {
    return latchBoolean.get();
  }

  /**
   * Sets this LatchBoolean to true.
   */
  public void setTrue() {
    latchBoolean.set(true);
  }
}
