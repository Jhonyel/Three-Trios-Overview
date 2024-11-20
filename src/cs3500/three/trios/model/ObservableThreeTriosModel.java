package cs3500.three.trios.model;

import cs3500.three.trios.player.Player;

/**
 * An observable version of our model. This interface allows us to register players of the game.
 * When the turn switches to a player, they are notified by this model. That is: when the turn
 * switches to a player, the model invokes that player's `onTurn()` method.
 */
public interface ObservableThreeTriosModel extends ThreeTriosModel {

  /**
   * Starts the game. That is: allows playCardAt to be invoked. This method should only be called
   * after two players have been added using addPlayer and should never be called after that.
   *
   * @throws IllegalStateException if the game has already started, or the client has not yet added
   *                               both a red and blue player using `addPlayer`.
   */
  void startGame();

  /**
   * Registers the given player as an observer of this model. Upon the turn switching to the given
   * player, the player's `onTurn()` method will be invoked. Note: this method should only ever be
   * invoked in the constructor of a player. Since a player is aware of what game they belong to,
   * players are responsible for registering themselves as listeners to an observable model.
   *
   * @throws IllegalArgumentException if the given player is null
   * @throws IllegalStateException    if a player with the same color as the one given has already
   *                                  been registered as an observer of this model. e.g. attempting
   *                                  to register two red players will throw an
   *                                  IllegalStateException
   */
  void registerPlayer(Player player);

  /**
   * Registers the given observer to be notified when the turn changes.
   *
   * @throws IllegalArgumentException if the given observer is null
   */
  void registerObserver(ThreeTriosModelObserver observer);

  /**
   * Returns true if the game has started, false otherwise.
   */
  boolean isGameStarted();

}
