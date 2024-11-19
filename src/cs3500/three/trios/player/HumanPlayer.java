package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.util.Requirements;

public class HumanPlayer implements Player {

  private final ThreeTriosModel model;
  private final PlayerColor playerColor;

  public HumanPlayer(ThreeTriosModel model, PlayerColor playerColor) {
    this.model = Requirements.requireNonNull(model);
    this.playerColor = Requirements.requireNonNull(playerColor);
  }

  @Override
  public void onTurn() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, int cardIndex) {
    throw new UnsupportedOperationException();
  }

  @Override
  public PlayerColor getPlayerColor() {
    throw new UnsupportedOperationException();
  }
}
