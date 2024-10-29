package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.Direction;

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
    this.name = requireNonNull(name);
    this.northAttackValue = requireNonNull(northAttackValue);
    this.southAttackValue = requireNonNull(southAttackValue);
    this.eastAttackValue = requireNonNull(eastAttackValue);
    this.westAttackValue = requireNonNull(westAttackValue);
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
    requireNonNull(direction);
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

  @Override
  public boolean equals(Object other) {
    if (other instanceof Card) {
      Card otherCard = (Card) other;
      return otherCard.getName().equals(getName())
          && otherCard.getNorthAttackValue().equals(getNorthAttackValue())
          && otherCard.getSouthAttackValue().equals(getSouthAttackValue())
          && otherCard.getEastAttackValue().equals(getEastAttackValue())
          && otherCard.getWestAttackValue().equals(getWestAttackValue());
    }
    return false;
  }
}
