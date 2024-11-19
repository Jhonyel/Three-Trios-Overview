package cs3500.three.trios.model;

import cs3500.three.trios.player.Player;

public interface ModelFeatures {

  /**
   * Starts the game. That is: allows playCardAt to be invoked. This method should only be called
   * after two players have been added using addPlayer and should never be called after that.
   *
   * @throws IllegalStateException if the game has already started, or if there have not yet been 2
   *                               players added
   */
  void startGame();

  void addPlayer(Player player);
}
