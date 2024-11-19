package cs3500.three.trios.player;

import cs3500.three.trios.model.ObservableThreeTriosModel;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.util.Requirements;

/**
 * A class representing a player. A player is aware of which game they belong to as well as what
 * color they are in said game. When the turn changes to this player, the model will notify this
 * player and the `onTurn` method will be called.
 */
public abstract class Player {

  protected final PlayerColor playerColor;
  protected final ThreeTriosModel model;

  /**
   * Creates a new Player using the given observable model and player color. The player will
   * register itself as an observer of the observable model so that upon the turn changing to this
   * player, the model will notify this player and the `onTurn` method will be called.
   *
   * @throws IllegalArgumentException if the given model is null
   * @throws IllegalArgumentException if the given player color is null
   * @throws IllegalStateException    if the model has already been started or a player with the
   *                                  same color as the one specified has already been registered to
   *                                  the model
   */
  public Player(ObservableThreeTriosModel model, PlayerColor playerColor) {
    this.model = Requirements.requireNonNull(model);
    this.playerColor = Requirements.requireNonNull(playerColor);

    model.registerPlayer(this);
  }

  /**
   * Invoked when the turn changes to this player.
   */
  public abstract void onTurn();

  /**
   * Returns the player of this color.
   */
  public PlayerColor getPlayerColor() {
    return playerColor;
  }
}
