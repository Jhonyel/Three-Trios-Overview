package cs3500.three.trios.player;

import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import java.util.List;
import java.util.Queue;

public interface MoveStrategy {
  Queue<Move> getMove(ReadOnlyThreeTriosModel model);
}
