package cs3500.three.trios.model.card;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.util.Requirements;
import java.util.Objects;

/**
 * An implementation of our Card interface.
 */
public class CardImpl implements Card {

  private final String name;
  private final AttackValue northAttackValue;
  private final AttackValue southAttackValue;
  private final AttackValue eastAttackValue;
  private final AttackValue westAttackValue;

  /**
   * Creates a new CardImpl with the given name and attack values.
   *
   * @param name             the name of the card
   * @param northAttackValue the attack value in the north direction
   * @param southAttackValue the attack value in the south direction
   * @param eastAttackValue  the attack value in the east direction
   * @param westAttackValue  the attack value in the west direction
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public CardImpl(
      String name,
      AttackValue northAttackValue,
      AttackValue southAttackValue,
      AttackValue eastAttackValue,
      AttackValue westAttackValue
  ) {
    this.name = Requirements.requireNonNull(name);
    this.northAttackValue = Requirements.requireNonNull(northAttackValue);
    this.southAttackValue = Requirements.requireNonNull(southAttackValue);
    this.eastAttackValue = Requirements.requireNonNull(eastAttackValue);
    this.westAttackValue = Requirements.requireNonNull(westAttackValue);
  }

  /**
   * Returns a card based on `cardString` assumed to be in the format:
   * <br>CARD_NAME NORTH SOUTH EAST WEST
   * <br>where CARD_NAME is the name of the card, and NORTH, SOUTH, EAST, and WEST are the attack
   * values in each direction.
   */
  public CardImpl(String cardString) {
    Requirements.requireNonNull(cardString);
    String[] tokens = cardString.split(" ");
    if (tokens.length != 5) {
      throw new IllegalArgumentException("cardString must have 5 tokens");
    }
    name = tokens[0];
    northAttackValue = AttackValue.fromHexadecimalDigit(tokens[1]);
    southAttackValue = AttackValue.fromHexadecimalDigit(tokens[2]);
    eastAttackValue = AttackValue.fromHexadecimalDigit(tokens[3]);
    westAttackValue = AttackValue.fromHexadecimalDigit(tokens[4]);
  }

  @Override
  public AttackValue getNorthAttackValue() {
    return northAttackValue;
  }

  @Override
  public AttackValue getEastAttackValue() {
    return eastAttackValue;
  }

  @Override
  public AttackValue getSouthAttackValue() {
    return southAttackValue;
  }

  @Override
  public AttackValue getWestAttackValue() {
    return westAttackValue;
  }

  @Override
  public AttackValue getAttackValue(Direction direction) {
    Requirements.requireNonNull(direction);
    switch (direction) {
      case NORTH:
        return northAttackValue;
      case EAST:
        return eastAttackValue;
      case SOUTH:
        return southAttackValue;
      case WEST:
        return westAttackValue;
      default:
        throw new IllegalArgumentException();
    }
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Returns a string representation of the card in the format: CARD_NAME NORTH SOUTH EAST WEST,
   * where NORTH, SOUTH, EAST, WEST are the attack values of the card in the respective directions
   * and CARD_NAME is the name of the card.
   */
  @Override
  public String toString() {
    return String.join(
        " ",
        name,
        northAttackValue.toString(),
        southAttackValue.toString(),
        eastAttackValue.toString(),
        westAttackValue.toString()
    );
  }

  /**
   * Returns true if `other` has the same name and attack values as this card, false otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof Card) {
      Card otherCard = (Card) other;
      return otherCard.getName().equals(getName())
          && otherCard.getNorthAttackValue() == getNorthAttackValue()
          && otherCard.getSouthAttackValue() == getSouthAttackValue()
          && otherCard.getEastAttackValue() == getEastAttackValue()
          && otherCard.getWestAttackValue() == getWestAttackValue();
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        name,
        northAttackValue,
        southAttackValue,
        eastAttackValue,
        westAttackValue
    );
  }

  @Override
  public boolean beats(Card otherCard, Direction direction) {
    AttackValue thisAttackValue = getAttackValue(direction);
    AttackValue otherAttackValue = otherCard.getAttackValue(direction.getOppositeDirection());
    return thisAttackValue.compareTo(otherAttackValue) > 0;
  }
}
