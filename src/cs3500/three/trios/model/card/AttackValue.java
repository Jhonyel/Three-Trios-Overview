package cs3500.three.trios.model.card;

/**
 * An enum class representing the 10 different possible attack values of a card. The order in which
 * the enum constants are declared is important, because we compare attack values; when Java
 * compares enum constants (such as attack values), it takes the first declared enum to be the least
 * and the last declared enum to be the greatest, thus the order in which we declare the enum
 * constants is important.
 */
public enum AttackValue {
  ONE('1'),
  TWO('2'),
  THREE('3'),
  FOUR('4'),
  FIVE('5'),
  SIX('6'),
  SEVEN('7'),
  EIGHT('8'),
  NINE('9'),
  TEN('A');

  private final char character;

  AttackValue(char character) {
    this.character = character;
  }

  /**
   * Returns the hexadecimal digit representing this attack value.
   */
  @Override
  public String toString() {
    return String.valueOf(character);
  }
}
