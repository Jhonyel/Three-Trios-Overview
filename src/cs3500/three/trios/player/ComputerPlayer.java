package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.strategy.MoveStrategy;
import cs3500.three.trios.util.Requirements;

public class ComputerPlayer implements Player {
  private final ThreeTriosModel model;
  private final PlayerColor playerColor;
  private final MoveStrategy strategy;

  public ComputerPlayer(ThreeTriosModel model, PlayerColor playerColor, MoveStrategy strategy) {
    this.model = Requirements.requireNonNull(model);
    this.playerColor = Requirements.requireNonNull(playerColor);
    this.strategy = Requirements.requireNonNull(strategy);
  }

  @Override
  public void onTurn() {
    // todo - implement
    throw new UnsupportedOperationException();
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, int cardIndex) {
    // todo - implement
    throw new UnsupportedOperationException();
  }

  @Override
  public PlayerColor getPlayerColor() {
    // todo - implement
    throw new UnsupportedOperationException();
  }
}
