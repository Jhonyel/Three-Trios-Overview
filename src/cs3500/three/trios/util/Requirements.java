package cs3500.three.trios.util;

import java.util.Collection;

public class Requirements {

  /**
   * Returns the given object if it is non-null. Otherwise, throws an IllegalArgumentException.
   */
  public static <T>  T requireNonNull(T object) {
    if (object == null) {
      throw new IllegalArgumentException();
    }
    return object;
  }

  /**
   * Returns the given collection if it and its elements are all non-null. Otherwise, throws an
   * IllegalArgumentException.
   */
  public static <T, C extends Collection<T>> C requireNonNullCollection(C collection) {
    requireNonNull(collection);
    for (T element : collection) {
      requireNonNull(element);
    }
    return collection;
  }
  /**
   * Returns the given array if it and its elements are all non-null. Otherwise, throws an
   * IllegalArgumentException.
   */
  public static <T> T[] requireNonNullArray(T[] array) {
    requireNonNull(array);
    for (T element : array) {
      requireNonNull(element);
    }
    return array;
  }
}
