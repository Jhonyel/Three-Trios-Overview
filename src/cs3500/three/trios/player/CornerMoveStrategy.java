package cs3500.three.trios.player;

import static cs3500.three.trios.model.Direction.EAST;
import static cs3500.three.trios.model.Direction.NORTH;
import static cs3500.three.trios.model.Direction.SOUTH;
import static cs3500.three.trios.model.Direction.WEST;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.PlayerCard;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class CornerMoveStrategy implements MoveStrategy {

  private enum Corner {
    NORTH_WEST(SOUTH, EAST),
    NORTH_EAST(SOUTH, WEST),
    SOUTH_WEST(NORTH, EAST),
    SOUTH_EAST(NORTH, WEST);

    private final Direction vulnerableDirection1;
    private final Direction vulnerableDirection2;

    Corner(Direction vulnerableDirection1, Direction vulnerableDirection2) {
      this.vulnerableDirection1 = vulnerableDirection1;
      this.vulnerableDirection2 = vulnerableDirection2;
    }
  }

  private class CornerMove extends Move {

    private final Corner corner;

    public CornerMove(int rowIndex, int colIndex, int cardIndex, Corner corner) {
      super(rowIndex, colIndex, cardIndex);
      this.corner = corner;
    }
  }

  @Override
  public Queue<Move> getMove(ReadOnlyThreeTriosModel model) {

    Queue<CornerMove> moves = new ArrayDeque<>();

    int northRowIndex = 0;
    int southRowIndex = model.getHeight() - 1;
    int westColIndex = 0;
    int eastColIndex = model.getWidth() - 1;

    PlayerColor currentPlayer = model.getCurrentPlayer();
    List<PlayerCard> hand = model.getHand(currentPlayer);
    for (int cardIndex = 0; cardIndex < hand.size(); cardIndex++) {
      moves.add(new CornerMove(northRowIndex, westColIndex, cardIndex, Corner.NORTH_WEST));
      moves.add(new CornerMove(northRowIndex, eastColIndex, cardIndex, Corner.NORTH_EAST));
      moves.add(new CornerMove(southRowIndex, westColIndex, cardIndex, Corner.SOUTH_WEST));
      moves.add(new CornerMove(southRowIndex, eastColIndex, cardIndex, Corner.SOUTH_EAST));
    }
    Comparator<CornerMove> comparator =
        comparing(move -> getAverageVulnerableAttackValue(move, model));

    comparator = comparator.reversed()
        .thenComparing(Move::getRowIndex)
        .thenComparing(Move::getColIndex)
        .thenComparing(Move::getCardIndex);

    return moves.stream()
        .sorted(comparator)
        .collect(toCollection(ArrayDeque::new));
  }

  private int getAverageVulnerableAttackValue(CornerMove move, ReadOnlyThreeTriosModel model) {
    Corner corner = move.corner;
    Direction vulnerableDirection1 = corner.vulnerableDirection1;
    Direction vulnerableDirection2 = corner.vulnerableDirection2;

    PlayerColor currentPlayer = model.getCurrentPlayer();
    List<PlayerCard> hand = model.getHand(currentPlayer);
    Card card = hand.get(move.getCardIndex());

    int vulnerableAttackValue1 = card.getAttackValue(vulnerableDirection1).toInt();
    int vulnerableAttackValue2 = card.getAttackValue(vulnerableDirection2).toInt();
    return (vulnerableAttackValue1 + vulnerableAttackValue2) / 2;

  }

}
