package cs3500.three.trios.model.observable;

import cs3500.three.trios.model.PlayerColor;

/**
 * An interface describing an object which observes an observable three trios model. An object must
 * be registered as an observer in order to observe the model. When the model's turn changes, the
 * model notifies all observers of the change by invoking `onNewTurn` with the new player color as
 * an argument.
 */
public interface ThreeTriosModelObserver {

  /**
   * Invoked when the model's turn changes. This method should only ever be invoked by the model.
   * This observer must register itself as an observer of the model in order for this method to be
   * invoked.
   *
   * @param newPlayerColor the color of the player whose turn it is.
   */
  void onNewTurn(PlayerColor newPlayerColor);
}
