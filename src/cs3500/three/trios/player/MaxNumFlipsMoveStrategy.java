package cs3500.three.trios.player;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class MaxNumFlipsMoveStrategy implements MoveStrategy {
  @Override
  public Queue<Move> getMove(ReadOnlyThreeTriosModel model) {
    int maxNumFlips = -1;
    Queue<Move> moves = new ArrayDeque<>();
    for (int rowIndex = 0; rowIndex < model.getHeight(); rowIndex++) {
      for (int colIndex = 0; colIndex < model.getWidth(); colIndex++) {
        if (!model.isMoveLegalAt(rowIndex, colIndex)) {
          continue;
        }
        PlayerColor currentPlayer = model.getCurrentPlayer();
        List<PlayerCard> currentHand = model.getHand(currentPlayer);
        int currentHandSize = currentHand.size();
        for (int cardIndex = 0; cardIndex < currentHandSize; cardIndex++) {
          int numFlips = model.getNumFlipsAt(rowIndex, colIndex, cardIndex);
          if (numFlips > maxNumFlips) {
            maxNumFlips = numFlips;
            moves.add(new Move(rowIndex, colIndex, cardIndex));
          }
        }
      }
    }
    return moves;
  }
}
