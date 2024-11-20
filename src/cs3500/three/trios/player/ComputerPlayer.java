package cs3500.three.trios.player;

import cs3500.three.trios.model.ObservableThreeTriosModel;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.strategy.Move;
import cs3500.three.trios.strategy.MoveStrategy;
import cs3500.three.trios.util.Requirements;

/**
 * A computer player that uses a {@link MoveStrategy} to play cards.
 */
public class ComputerPlayer extends Player {

  private final MoveStrategy strategy;

  /**
   * Creates a new ComputerPlayer using the given observable model and player color. The player will
   * register itself as an observer of the observable model so that upon the turn changing to this
   * player, the model will notify this player and the `onTurn` method will be called. When it is
   * this player's turn, it will use the given strategy to play cards.
   *
   * @throws IllegalArgumentException if the given model is null
   * @throws IllegalArgumentException if the given player color is null
   * @throws IllegalStateException    if the model has already been started or a player with the
   *                                  same color as the one specified has already been registered to
   *                                  the model
   */
  public ComputerPlayer(
      ObservableThreeTriosModel model, PlayerColor playerColor, MoveStrategy strategy
  ) {
    super(model, playerColor);
    this.strategy = Requirements.requireNonNull(strategy);
  }

  /**
   * Uses this computer's strategy to determine the best move, then executes that move on the model.
   */
  @Override
  public void onTurn() {
    System.out.printf("%s computer player playing\n", playerColor);
    Move bestMove = strategy.getBestMove(model);
    model.playCardAt(bestMove.getRowIndex(), bestMove.getColIndex(), bestMove.getCardIndex());
  }
}
