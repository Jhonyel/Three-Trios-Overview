package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;

/**
 * An enum class representing the 10 different possible attack values of a card. The order in which
 * the enum constants are declared is important, because we compare attack values; when Java
 * compares enum constants (such as attack values), it takes the first declared enum to be the least
 * and the last declared enum to be the greatest, thus we declare the enums from least to greatest.
 */
public enum AttackValue {
  ONE('1', 1),
  TWO('2', 2),
  THREE('3', 3),
  FOUR('4', 4),
  FIVE('5', 5),
  SIX('6', 6),
  SEVEN('7', 7),
  EIGHT('8', 8),
  NINE('9', 9),
  TEN('A', 10);

  private final int intValue;
  private final char character;

  AttackValue(char character, int intValue) {
    this.character = character;
    this.intValue = intValue;
  }

  /**
   * Returns the hexadecimal digit representing this attack value.
   */
  @Override
  public String toString() {
    return String.valueOf(character);
  }

  /**
   * Returns the integer value of this attack value in the range [1, 10].
   */
  public int toInt() {
    return intValue;
  }

  /**
   * Returns the attack value represented by the given hexadecimal digit character. The character
   * must be a hexadecimal digit 1-A.
   */
  public static AttackValue fromHexadecimalDigit(char digit) {
    switch (digit) {
      case '1':
        return ONE;
      case '2':
        return TWO;
      case '3':
        return THREE;
      case '4':
        return FOUR;
      case '5':
        return FIVE;
      case '6':
        return SIX;
      case '7':
        return SEVEN;
      case '8':
        return EIGHT;
      case '9':
        return NINE;
      case 'A':
        return TEN;
      default:
        throw new IllegalArgumentException("Invalid hexadecimal digit: " + digit);
    }
  }

  /**
   * Returns the attack value represented by the given hexadecimal digit string. The string must
   * have exactly one character, and it must be a hexadecimal digit 1-A.
   */
  public static AttackValue fromHexadecimalDigit(String digitString) {
    requireNonNull(digitString);
    if (digitString.length() != 1) {
      throw new IllegalArgumentException("Invalid hexadecimal digit: " + digitString);
    }
    return fromHexadecimalDigit(digitString.charAt(0));
  }
}
