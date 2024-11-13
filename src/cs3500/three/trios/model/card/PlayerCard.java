package cs3500.three.trios.model.card;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.util.Requirements;
import java.util.Objects;

/**
 * A card that is aware of the color of the player that owns this card.
 */
public class PlayerCard extends CardImpl {

  private final PlayerColor playerColor;

  /**
   * Creates a new PlayerCard with the given card and player color.
   *
   * @param card        the card containing the attack values and name of this card.
   * @param playerColor the color of the player who owns this card.
   * @throws IllegalArgumentException if any of the arguments are null.
   */
  public PlayerCard(Card card, PlayerColor playerColor) {
    this(
        Requirements.requireNonNull(card).getName(),
        Requirements.requireNonNull(card).getNorthAttackValue(),
        Requirements.requireNonNull(card).getSouthAttackValue(),
        Requirements.requireNonNull(card).getEastAttackValue(),
        Requirements.requireNonNull(card).getWestAttackValue(),
        Requirements.requireNonNull(playerColor)
    );
  }

  /**
   * Creates a player card with the given name, attack values, and player color.
   *
   * @param name             the name of the card
   * @param northAttackValue the attack value in the north direction
   * @param southAttackValue the attack value in the south direction
   * @param eastAttackValue  the attack value in the east direction
   * @param westAttackValue  the attack value in the west direction
   * @param playerColor      the color of the player who owns this card
   * @throws IllegalArgumentException if any of the arguments are null.
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
    this.playerColor = Requirements.requireNonNull(playerColor);
  }

  /**
   * Creates a player card from the given card string and player color.
   *
   * @throws IllegalArgumentException if any of the arguments are null or if card string has an
   *                                  invalid format.
   */
  public PlayerCard(String cardString, PlayerColor playerColor) {
    this(
        new CardImpl(Requirements.requireNonNull(cardString)),
        Requirements.requireNonNull(playerColor)
    );
  }

  /**
   * Returns the color of the player who owns this card.
   */
  public PlayerColor getPlayerColor() {
    return playerColor;
  }

  /**
   * Returns true if `other` has the same name, attack values, and player color as this card, false
   * otherwise.
   */
  @Override
  public boolean equals(Object other) {
    if (other instanceof PlayerCard) {
      PlayerCard otherCard = (PlayerCard) other;
      return super.equals(otherCard) && playerColor == otherCard.playerColor;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getName(),
        getNorthAttackValue(),
        getSouthAttackValue(),
        getEastAttackValue(),
        getWestAttackValue(),
        playerColor
    );
  }

  /**
   * Returns a string in the format: COLOR CARD_NAME NORTH SOUTH EAST WEST, where COLOR is the color
   * of the player who owns this card, CARD_NAME is the name of the card, and NORTH, SOUTH, EAST,
   * WEST are the attack values of the card in the respective directions.
   */
  @Override
  public String toString() {
    return String.format("%s %s", playerColor, super.toString());
  }
}
