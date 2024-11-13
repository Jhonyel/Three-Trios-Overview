package cs3500.three.trios.player;

import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import java.util.Queue;

/**
 * An interface defining a strategy for choosing a move for a player in a game of three trios given
 * a game state.
 */
public interface MoveStrategy {

  /**
   * Given the game state, returns the best moves as determined by the implementing class. The
   * elements of the queue are ordered from best to worst. The best move is at the front of the
   * queue.
   */
  Queue<Move> getMove(ReadOnlyThreeTriosModel model);
}
