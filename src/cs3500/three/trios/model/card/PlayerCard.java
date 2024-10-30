package cs3500.three.trios.model.card;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.PlayerColor;

/**
 * A card that is aware of the color of the player that owns this card.
 */
public class PlayerCard extends CardImpl {

  private final PlayerColor playerColor;

  /**
   * Creates a new PlayerCard with the given card and player color.
   *
   * @param card the card containing the attack values and name of this card.
   * @param playerColor the color of the player who owns this card.
   */
  public PlayerCard(Card card, PlayerColor playerColor) {
    this(
        requireNonNull(card).getName(),
        requireNonNull(card).getNorthAttackValue(),
        requireNonNull(card).getSouthAttackValue(),
        requireNonNull(card).getEastAttackValue(),
        requireNonNull(card).getWestAttackValue(),
        requireNonNull(playerColor)
    );
  }

  /**
   * Creates a player card with the given name, attack values, and player color.
   *
   * @param name the name of the card
   * @param northAttackValue the attack value in the north direction
   * @param southAttackValue the attack value in the south direction
   * @param eastAttackValue the attack value in the east direction
   * @param westAttackValue the attack value in the west direction
   * @param playerColor the color of the player who owns this card
   */
  public PlayerCard(
      String name,
      AttackValue northAttackValue,
      AttackValue southAttackValue,
      AttackValue eastAttackValue,
      AttackValue westAttackValue,
      PlayerColor playerColor
  ) {
    super(name, northAttackValue, southAttackValue, eastAttackValue, westAttackValue);
    this.playerColor = requireNonNull(playerColor);
  }

  /**
   * Returns the color of the player who owns this card.
   */
  public PlayerColor getPlayerColor() {
    return playerColor;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof PlayerCard) {
      PlayerCard otherCard = (PlayerCard) other;
      return super.equals(otherCard) && playerColor == otherCard.playerColor;
    }
    return false;
  }
}
