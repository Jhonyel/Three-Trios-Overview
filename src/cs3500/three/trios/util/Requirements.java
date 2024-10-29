package cs3500.three.trios.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
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
  public static void requireFileIsNotEmpty(String filePath) throws IOException {
    requireValidFilePath(filePath);
    Path path = Paths.get(filePath);
    List<String> lines = Files.readAllLines(path);
    if (lines.isEmpty()) {
      throw new IllegalArgumentException("Configuration file is empty");
    }
  }
}
