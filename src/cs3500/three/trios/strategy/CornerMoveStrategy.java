package cs3500.three.trios.strategy;

import static cs3500.three.trios.model.Direction.EAST;
import static cs3500.three.trios.model.Direction.NORTH;
import static cs3500.three.trios.model.Direction.SOUTH;
import static cs3500.three.trios.model.Direction.WEST;
import static java.util.Comparator.comparing;

import cs3500.three.trios.model.Direction;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A move strategy which chooses moves in the corners of the grid. The strategy chooses the move
 * such that the card placed is the most difficult possible card for the enemy to flip. We say that
 * the most difficult possible card for an enemy to flip is the one with the great average
 * vulnerable attack value. We say that an attack value is vulnerable if it can be attacked. For
 * example, a card placed in the northwest corner can be attacked from the east and from the south,
 * so its east and south attack values are vulnerable. If there are multiple moves with the same
 * average vulnerable attack value, we break ties first by choosing the north most moves, then by
 * choosing the west most moves, then by choosing the move with the lowest card index.
 */
public class CornerMoveStrategy implements MoveStrategy {

  /**
   * An enum representing a corner of a grid. There are 4 corners in a grid. Every corner has two
   * vulnerable attack directions.
   */
  private enum Corner {
    NORTH_WEST(SOUTH, EAST),
    NORTH_EAST(SOUTH, WEST),
    SOUTH_WEST(NORTH, EAST),
    SOUTH_EAST(NORTH, WEST);

    private final Direction vulnerableDirectionA;
    private final Direction vulnerableDirectionB;

    Corner(Direction vulnerableDirectionA, Direction vulnerableDirectionB) {
      this.vulnerableDirectionA = vulnerableDirectionA;
      this.vulnerableDirectionB = vulnerableDirectionB;
    }
  }

  /**
   * A move that also includes the corner of the grid where the card is to be placed.
   */
  private static class CornerMove extends Move {

    private final Corner corner;

    public CornerMove(int rowIndex, int colIndex, int cardIndex, Corner corner) {
      super(rowIndex, colIndex, cardIndex);
      this.corner = corner;
    }
  }

  /**
   * Returns a list of moves in the given model for its current player in the corners of the grid,
   * if there exists an empty corner. If there is no empty corner, we return a list of the northwest
   * most move with a card index of 0. If there is at least one empty corner, we choose the moves
   * ordered by the greatest average vulnerable attack value (read class javadoc for more details).
   * If there are multiple moves with the same average vulnerable attack value, we break ties first
   * by choosing the north most moves, then by choosing the west most moves, then by choosing the
   * move with the lowest card index.
   */
  @Override
  public List<Move> getMoves(ReadOnlyThreeTriosModel model) {
    Requirements.requireNonNull(model);
    return getMoves(model, model.getCurrentPlayer());
  }

  /**
   * Returns a move in the given model for the given player in the corner of the grid, if there
   * exists an empty corner. If there is no empty corner, we return the northwest most move with a
   * card index of 0. If there is at least one empty corner, we choose the move with the greatest
   * average vulnerable attack value (read class javadoc for more details). If there are multiple
   * moves with the same average vulnerable attack value, we break ties first by choosing the north
   * most moves, then by choosing the west most moves, then by choosing the move with the lowest
   * card index.
   */
  @Override
  public List<Move> getMoves(ReadOnlyThreeTriosModel model, PlayerColor playerColor) {
    Requirements.requireNonNull(model);
    if (model.isGameOver()) {
      throw new IllegalStateException("the game is over. there are no moves.");
    }
    if (model.getHand(playerColor).isEmpty()) {
      throw new IllegalStateException("the player has no cards. there are no moves.");
    }
    if (!existsLegalCornerMove(model)) {
      return getNorthWestMostMove(model);
    }

    Comparator<CornerMove> compareAverageVulnerableAttackValues =
        comparing(move -> getAverageVulnerableAttackValue(move, model, playerColor));

    Comparator<CornerMove> comparator =
        compareAverageVulnerableAttackValues.reversed()
            .thenComparing(Move::getRowIndex)
            .thenComparing(Move::getColIndex)
            .thenComparing(Move::getCardIndex);

    List<CornerMove> moves = getCornerMoves(model, playerColor);
    return moves.stream()
        .filter(move -> model.isMoveLegalAt(move.getRowIndex(), move.getColIndex()))
        .sorted(comparator)
        .collect(Collectors.toList());
  }

  /**
   * Returns the move in the northwest most empty card cell in the grid with the card at index 0.
   */
  private List<Move> getNorthWestMostMove(ReadOnlyThreeTriosModel model) {
    for (int rowIndex = 0; rowIndex < model.getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < model.getWidth(); colIndex++) {
        if (model.getCellAt(rowIndex, colIndex).isEmptyCardCell()) {
          return List.of(new Move(rowIndex, colIndex, 0));
        }
      }
    }
    throw new IllegalStateException(
        "getNorthWestMostMove called but there are no empty card cells");
  }

  /**
   * Returns true if there is a legal corner move in the given model, false otherwise.
   */
  private boolean existsLegalCornerMove(ReadOnlyThreeTriosModel model) {
    int northRowIndex = 0;
    int southRowIndex = model.getHeight() - 1;
    int westColIndex = 0;
    int eastColIndex = model.getWidth() - 1;

    return model.isMoveLegalAt(northRowIndex, westColIndex)
        || model.isMoveLegalAt(northRowIndex, eastColIndex)
        || model.isMoveLegalAt(southRowIndex, westColIndex)
        || model.isMoveLegalAt(southRowIndex, eastColIndex);
  }

  /**
   * Returns a list of all moves in the corners of the grids for the given player. Note: not all
   * moves in the returned list are guaranteed to be legal moves. One should filter the returned
   * list to include only legal moves.
   */
  private static List<CornerMove> getCornerMoves(
      ReadOnlyThreeTriosModel model, PlayerColor playerColor
  ) {
    List<CornerMove> moves = new ArrayList<>();

    int northRowIndex = 0;
    int southRowIndex = model.getHeight() - 1;
    int westColIndex = 0;
    int eastColIndex = model.getWidth() - 1;

    List<PlayerCard> hand = model.getHand(playerColor);
    for (int cardIndex = 0; cardIndex < hand.size(); cardIndex++) {
      moves.add(new CornerMove(northRowIndex, westColIndex, cardIndex, Corner.NORTH_WEST));
      moves.add(new CornerMove(northRowIndex, eastColIndex, cardIndex, Corner.NORTH_EAST));
      moves.add(new CornerMove(southRowIndex, westColIndex, cardIndex, Corner.SOUTH_WEST));
      moves.add(new CornerMove(southRowIndex, eastColIndex, cardIndex, Corner.SOUTH_EAST));
    }
    return moves;
  }

  /**
   * Returns the average vulnerable attack value of the given move in the given model.
   */
  private int getAverageVulnerableAttackValue(
      CornerMove move, ReadOnlyThreeTriosModel model, PlayerColor playerColor
  ) {
    Corner corner = move.corner;
    Direction vulnerableDirectionA = corner.vulnerableDirectionA;
    Direction vulnerableDirectionB = corner.vulnerableDirectionB;

    List<PlayerCard> hand = model.getHand(playerColor);
    Card card = hand.get(move.getCardIndex());

    int vulnerableAttackValueA = card.getAttackValue(vulnerableDirectionA).toInt();
    int vulnerableAttackValueB = card.getAttackValue(vulnerableDirectionB).toInt();
    return (vulnerableAttackValueA + vulnerableAttackValueB) / 2;
  }
}
