package cs3500.three.trios.model.cell;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.Player;
import java.util.Optional;

/**
 * A class to represent a cell in the grid that contains a card.
 */
public class CardCell extends Cell {

  // invariant: card.isEmpty() iff player.isEmpty()
  private Optional<Card> card;
  private Optional<Player> player;

  /**
   * Constructs a new CardCell with no card or player set.
   */
  public CardCell(
      Optional<Cell> north, Optional<Cell> east, Optional<Cell> south, Optional<Cell> west
  ) {
    super(north, east, south, west);
    card = Optional.empty();
    player = Optional.empty();
  }

  /**
   * Returns an empty optional if this cell is empty, otherwise an optional containing the card
   */
  public Optional<Card> getCard() {
    throw new UnsupportedOperationException("not implemented");
  }

  /**
   * Sets the card and player for this card cell.
   *
   * @param card   the card to place in this cell.
   * @param player
   */
  public void set(Card card, Player player) {
    boolean isCardSet = this.card.isPresent();
    if (isCardSet) {
      throw new IllegalStateException("cell already contains a card");
    }
    this.card = Optional.of(card);
    this.player = Optional.of(player);
  }

  public Optional<Player> getPlayer() {
    throw new UnsupportedOperationException("not implemented");
  }

  public void setPlayer(Player player) {
    this.player = Optional.of(player);
  }

  public void battle() {
    throw new UnsupportedOperationException("not implemented");
  }

  // todo: implement a cloning method
}
