package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.util.Requirements;

/**
 * A player which logs to a string builder when it's their turn.
 */
public class LoggingPlayer implements Player {

  private final StringBuilder log;
  private final Player player;

  /**
   * Decorates the given player. Logs output to the given string builder on the player's turn.
   */
  public LoggingPlayer(Player player, StringBuilder log) {
    this.player = Requirements.requireNonNull(player);
    this.log = Requirements.requireNonNull(log);
  }


  @Override
  public void onTurn() {
    log.append(String.format("Player %s's turn\n", getPlayerColor()));
    player.onTurn();
  }

  @Override
  public PlayerColor getPlayerColor() {
    return player.getPlayerColor();
  }
}
