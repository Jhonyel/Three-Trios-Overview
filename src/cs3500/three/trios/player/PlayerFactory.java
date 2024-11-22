package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;
import cs3500.three.trios.strategy.CornerMoveStrategy;
import cs3500.three.trios.strategy.MaxNumFlipsMoveStrategy;
import cs3500.three.trios.util.Requirements;

/**
 * A factory class for creating new players.
 */
public class PlayerFactory {

  /**
   * Returns a player with the given color observing the given model. The type of player is
   * determined by playerTypeString. The playerTypeString must be one of "HUMAN",
   * "MAX_NUM_FLIPS_COMPUTER", or "CORNER_MOVE_COMPUTER".
   *
   * @throws IllegalArgumentException if any argument is null or playerTypeString is not a valid
   *                                  value as described above.
   */
  public static Player fromPlayerType(
      String playerTypeString, ObservableThreeTriosModel model, PlayerColor color
  ) {
    Requirements.requireNonNull(playerTypeString);
    PlayerType playerType = PlayerType.valueOf(playerTypeString);
    return fromPlayerType(playerType, model, color);
  }


  /**
   * Returns a player with the given color observing the given model. The type of player is
   * determined by playerType.
   *
   * @throws IllegalArgumentException if any argument is null.
   */
  public static Player fromPlayerType(
      PlayerType playerType, ObservableThreeTriosModel model, PlayerColor color
  ) {
    Requirements.requireNonNull(playerType);
    Requirements.requireNonNull(model);
    Requirements.requireNonNull(color);

    switch (playerType) {
      case CORNER_MOVE:
        return new ComputerPlayer(model, color, new CornerMoveStrategy());
      case MAX_NUM_FLIPS:
        return new ComputerPlayer(model, color, new MaxNumFlipsMoveStrategy());
      case HUMAN:
        return new HumanPlayer(model, color);
      default:
        throw new IllegalArgumentException("Invalid player type");
    }
  }
}
