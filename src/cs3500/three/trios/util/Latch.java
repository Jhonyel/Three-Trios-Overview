package cs3500.three.trios.util;

/**
 * A wrapper class for a value of type T that is initialized to some value, can be set once to
 * another value, but can never be reassigned afterward.
 */
public class Latch<T> {

  private T value;
  private boolean isSet;

  /**
   * Constructs a new Latch with the given initial value.
   */
  public Latch(T initialValue) {
    value = initialValue;
    isSet = false;
  }

  /**
   * Sets this Latch to the given value, if it has not already been set.
   *
   * @throws IllegalStateException if this Latch has already been set.
   */
  public void set(T value) {
    if (isSet) {
      throw new IllegalStateException("Latch has already been set");
    }
    this.value = value;
    isSet = true;
  }

  /**
   * Returns the value of this latch.
   */
  public T get() {
    return value;
  }

  /**
   * Returns true if this Latch has been set, false otherwise.
   */
  public boolean isSet() {
    return isSet;
  }
}
