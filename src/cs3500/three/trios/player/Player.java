package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;

/**
 * An interface representing a player. A player is aware of which game they belong to as well as
 * what color they are in said game. When the turn changes to this player, the model will notify
 * this player and the `onTurn` method will be called.
 */
public interface Player {

  /**
   * Invoked when the turn changes to this player. This method should only ever be invoked by the
   * model.
   */
  void onTurn();

  /**
   * Returns the player color of this player.
   */
  PlayerColor getPlayerColor();
}
