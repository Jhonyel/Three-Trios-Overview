package cs3500.three.trios.player;

import static java.util.Comparator.comparing;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A move strategy which chooses the move with the maximum number of flips. If there are multiple
 * moves with the same number of flips, we break ties first by choosing the north most moves, then
 * by choosing the west most moves, then by choosing the move with the lowest card index.
 */
public class MaxNumFlipsMoveStrategy implements MoveStrategy {

  /**
   * Returns the move with the maximum number of flips in the given model for its current player. If
   * there are multiple moves with the same number of flips, we break ties first by choosing the
   * north most moves, then by choosing the west most moves, then by choosing the move with the
   * lowest card index.
   */
  @Override
  public List<Move> getMove(ReadOnlyThreeTriosModel model) {
    Requirements.requireNonNull(model);
    if (model.isGameOver()) {
      throw new IllegalStateException("the game is over. there are no moves.");
    }

    List<Move> moves = getMoves(model);

    Comparator<Move> comparingNumFlips =
        comparing(move -> model.getNumFlipsAt(
            move.getRowIndex(),
            move.getColIndex(),
            move.getCardIndex()
        ));
    comparingNumFlips = comparingNumFlips.reversed();

    return moves.stream()
        .filter(move -> model.isMoveLegalAt(move.getRowIndex(), move.getColIndex()))
        .sorted(comparingNumFlips)
        .collect(Collectors.toList());
  }

  private static List<Move> getMoves(ReadOnlyThreeTriosModel model) {
    List<Move> moves = new ArrayList<>();
    for (int rowIndex = 0; rowIndex < model.getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < model.getWidth(); colIndex++) {
        PlayerColor currentPlayer = model.getCurrentPlayer();
        List<PlayerCard> currentHand = model.getHand(currentPlayer);
        for (int cardIndex = 0; cardIndex < currentHand.size(); cardIndex++) {
          moves.add(new Move(rowIndex, colIndex, cardIndex));
        }
      }
    }
    return moves;
  }
}
