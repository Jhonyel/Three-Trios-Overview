package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;

public interface Player {

  /**
   * Invoked when the turn changes to this player. This method should only ever be invoked by the
   * model.
   */
  void onTurn();

  PlayerColor getPlayerColor();
}
