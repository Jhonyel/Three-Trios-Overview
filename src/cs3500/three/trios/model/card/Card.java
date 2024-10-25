package cs3500.three.trios.model.card;

import cs3500.three.trios.model.Direction;

/**
 * An interface representing a card.
 */
public interface Card {

  /**
   * Gets the attack value of the card in the north direction.
   *
   * @return the north attack value
   */
  AttackValue getNorthAttackValue();

  /**
   * Gets the attack value of the card in the east direction.
   *
   * @return the east attack value
   */
  AttackValue getEastAttackValue();

  /**
   * Gets the attack value of the card in the south direction.
   *
   * @return the south attack value
   */
  AttackValue getSouthAttackValue();

  /**
   * Gets the attack value of the card in the west direction.
   *
   * @return the west attack value
   */
  AttackValue getWestAttackValue();

  /**
   * Gets the attack value of the card in the specified direction.
   *
   * @param direction the direction to get the attack value for
   * @return the attack value in the specified direction
   */
  AttackValue getAttackValue(Direction direction);

  /**
   * Returns the name of this card.
   */
  String getName();

  /**
   * Returns a string representation of this card in the format: CARD_NAME NORTH SOUTH EAST WEST.
   * Where NORTH, SOUTH, EAST, WEST are the attack values of the card in the respective directions.
   */
  String toString();
}
