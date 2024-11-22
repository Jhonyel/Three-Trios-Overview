package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;
import cs3500.three.trios.util.Requirements;

/**
 * A player which logs to a string builder when it's their turn.
 */
public class LoggingPlayer extends AbstractPlayer {

  private final StringBuilder log;

  public LoggingPlayer(
      ObservableThreeTriosModel model, PlayerColor playerColor,
      StringBuilder log
  ) {
    super(model, playerColor);
    this.log = Requirements.requireNonNull(log);
  }

  /**
   * Decorates the given player. Logs output to the given string builder on the player's turn.
   */
  @Override
  public void onTurn() {
    log.append(String.format("Player %s's turn\n", getPlayerColor()));
  }
}
