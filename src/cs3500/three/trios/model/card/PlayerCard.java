package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.model.PlayerColor;

/**
 * A card that is aware of the color of the player that owns this card.
 */
public class PlayerCard implements Card {

  private final Card card;
  private final PlayerColor playerColor;

  /**
   * Creates a new PlayerCard with the given card and player color.
   *
   * @param card the card containing the attack values and name of this card.
   * @param playerColor the color of the player who owns this card.
   */
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

  /**
   * Returns the color of the player who owns this card.
   */
  public PlayerColor getPlayerColor() {
    return playerColor;
  }
}
