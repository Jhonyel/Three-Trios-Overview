package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.util.Requirements;

public class CardImpl implements Card {

  private final String cardName;
  private final AttackValue north;
  private final AttackValue south;
  private final AttackValue east;
  private final AttackValue west;

  public CardImpl(
      String cardName, AttackValue north, AttackValue south, AttackValue east, AttackValue west
  ) {
    this.cardName = requireNonNull(cardName);
    this.north = requireNonNull(north);
    this.south = requireNonNull(south);
    this.east = requireNonNull(east);
    this.west = requireNonNull(west);
  }

  @Override
  public AttackValue getNorthAttackValue() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public AttackValue getEastAttackValue() {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public AttackValue getSouthAttackValue() {
    throw new UnsupportedOperationException("not implemented");

  }

  @Override
  public AttackValue getWestAttackValue() {
    throw new UnsupportedOperationException("not implemented");

  }

  @Override
  public AttackValue getAttackValue(Direction direction) {
    throw new UnsupportedOperationException("not implemented");

  }

  @Override
  public String getName() {
    throw new UnsupportedOperationException("not implemented");

  }
}
