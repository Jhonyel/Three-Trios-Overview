package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.model.PlayerColor;

public class PlayerCard implements Card {

  private final Card card;
  private final PlayerColor playerColor;

  public PlayerCard(Card card, PlayerColor playerColor) {
    this.card = requireNonNull(card);
    this.playerColor = requireNonNull(playerColor);
  }

  @Override
  public AttackValue getNorthAttackValue() {
    return card.getNorthAttackValue();
  }

  @Override
  public AttackValue getEastAttackValue() {
    return card.getEastAttackValue();
  }

  @Override
  public AttackValue getSouthAttackValue() {
    return card.getSouthAttackValue();
  }

  @Override
  public AttackValue getWestAttackValue() {
    return card.getWestAttackValue();
  }

  @Override
  public AttackValue getAttackValue(Direction direction) {
    return card.getAttackValue(direction);
  }

  @Override
  public String getName() {
    return card.getName();
  }

  public PlayerColor getPlayerColor() {
    return playerColor;
  }
}
