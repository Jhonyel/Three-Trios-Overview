package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;

/**
 * A class representing a human player. Since the human player acts asynchronously, the onTurn
 * method does nothing. Human players will instead make moves by interacting with the GUI.
 */
public class HumanPlayer extends Player {

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
  public HumanPlayer(ObservableThreeTriosModel model, PlayerColor playerColor) {
    super(model, playerColor);
  }

  /**
   * Invoked when the turn changes to this player. Since the human player interacts with the GUI to
   * make moves, this method does nothing.
   */
  @Override
  public void onTurn() {
    System.out.printf("%s human player playing\n", playerColor);
  }
}
