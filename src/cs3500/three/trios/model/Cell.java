package cs3500.three.trios.model;

import cs3500.three.trios.model.card.Card;
import java.util.Optional;

public class Cell {

  private final Optional<Card> card;
  private final Optional<Player> player;

  public enum Type {
    CARD, HOLE
  }

  private Type type;

  private Cell(Type type, Optional<Card> card, Optional<Player> player) {
    this.type = type;
    this.card = card;
    this.player = player;
  }

  public static Cell createHoleCell() {
    return new Cell(Type.HOLE, Optional.empty(), Optional.empty());
  }

  public static Cell createEmptyCardCell() {
    return new Cell(Type.CARD, Optional.empty(), Optional.empty());
  }

  public static Cell createCardCell(Card card, Player player) {
    return new Cell(Type.CARD, Optional.of(card), Optional.of(player));
  }

  public Card getCard() {
    return card.orElseThrow();
  }

  public Player getPlayer() {
    return player.orElseThrow();
  }

  public boolean isHole() {
    return type.equals(Type.HOLE);
  }

  public boolean isCardCell() {
    return type.equals(Type.CARD);
  }

  public boolean isEmptyCardCell() {
    return isCardCell() && card.isEmpty();
  }

  public boolean hasCard() {
    return card.isPresent();
  }
}
