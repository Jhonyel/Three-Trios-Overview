package cs3500.three.trios.player;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import java.util.Queue;

public class CornerMoveStrategy implements MoveStrategy {

  @Override
  public Queue<Move> getMove(ReadOnlyThreeTriosModel model) {
    return null;
  }

  private boolean isCorner(int rowIndex, int colIndex, Cell[][] grid) {
    return true;
  }
}
