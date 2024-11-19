package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;

public interface Player {
  void onTurn();
  void playCardAt(int rowIndex, int colIndex, int cardIndex);
  PlayerColor getPlayerColor();
}
