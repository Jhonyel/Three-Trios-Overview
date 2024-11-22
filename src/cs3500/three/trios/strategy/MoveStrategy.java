package cs3500.three.trios.strategy;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import java.util.List;

/**
 * An interface defining a strategy for choosing a move for a player in a game of three trios given
 * a game state.
 */
public interface MoveStrategy {

  /**
   * Given the game state, returns the best moves for the current player as determined by the
   * implementing class. The elements of the list are ordered from best to worst. The best move is
   * at the front of the list (i.e. index 0).
   *
   * @throws IllegalStateException    if the game is over (i.e. there are no more legal moves).
   * @throws IllegalArgumentException if the given model is null.
   */
  List<Move> getMoves(ReadOnlyThreeTriosModel model);

  /**
   * Given the game state, returns the best moves for the given player as determined by the
   * implementing class. The elements of the list are ordered from best to worst. The best move is
   * at the front of the list (i.e. index 0).
   *
   * @throws IllegalStateException    if the game is over (i.e. there are no more legal moves).
   * @throws IllegalArgumentException if the given model or player is null.
   */
  List<Move> getMoves(ReadOnlyThreeTriosModel model, PlayerColor playerColor);

  /**
   * Returns the best move for the current player in the given game state.
   */
  default Move getBestMove(ReadOnlyThreeTriosModel model) {
    List<Move> moves = getMoves(model);
    return moves.get(0);
  }
}
