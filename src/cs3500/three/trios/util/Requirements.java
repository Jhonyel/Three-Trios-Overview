package cs3500.three.trios.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * A class containing methods to validate arguments in methods.
 */
public class Requirements {

  /**
   * Returns the given object if it is non-null. Otherwise, throws an IllegalArgumentException.
   */
  public static <T> T requireNonNull(T object) {
    if (object == null) {
      throw new IllegalArgumentException("object cannot be null");
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

  /**
   * Returns the given array if it and its elements are all non-null. Otherwise, throws an
   * IllegalArgumentException.
   */
  public static <T> T[][] requireNonNullArray2D(T[][] array2D) {
    requireNonNullArray(array2D);
    for (T[] row : array2D) {
      requireNonNullArray(row);
    }
    return array2D;
  }

  /**
   * Returns the given array 2d if it is rectangular. A rectangular array has at least one row, each
   * row has at least one element, and each row has the same length. If the given array 2d is not
   * rectangular, this method throws an IllegalArgumentException.
   */
  public static <T> T[][] requireRectangularArray2D(T[][] array2D) {
    requireNonNullArray2D(array2D);
    int height = array2D.length;
    if (height == 0) {
      throw new IllegalArgumentException("array2D must have at least one row");
    }
    int width = array2D[0].length;
    for (T[] row : array2D) {
      if (row.length == 0) {
        throw new IllegalArgumentException("array2D must have at least one column");
      }
      if (row.length != width) {
        throw new IllegalArgumentException("array2D must be rectangular");
      }
    }
    return array2D;
  }

  /**
   * Returns the given collection if all of its elements are unique. Otherwise, throws an
   * IllegalArgumentException.
   */
  public static <T, C extends Collection<T>> C requireUniqueCollection(C collection) {
    requireNonNullCollection(collection);
    if (collection.size() != new HashSet<>(collection).size()) {
      throw new IllegalArgumentException("collection must contain unique elements");
    }
    return collection;
  }

  /**
   * Returns the given file path if it exists in the file system. Otherwise, throws an
   * IllegalArgumentException.
   */
  public static String requireValidFilePath(String filePath) {
    requireNonNull(filePath);
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      throw new IllegalArgumentException("File does not exist: " + filePath);
    }
    return filePath;
  }

  /**
   * Checks that the file at the given file path is not empty. Otherwise, throws an
   * IllegalArgumentException.
   */
  public static String requireFileIsNotEmpty(String filePath) {
    try {
      requireValidFilePath(filePath);
      Path path = Paths.get(filePath);
      List<String> lines = Files.readAllLines(path);
      if (lines.isEmpty()) {
        throw new IllegalArgumentException("Configuration file is empty");
      }
      return filePath;
      
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}
