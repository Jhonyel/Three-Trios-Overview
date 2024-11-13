package cs3500.three.trios.model.mock;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.util.List;

/**
 * A mock ReadOnlyThreeTriosModel which returns true for only the top left corner of isMoveLegalAt.
 */
public class TopLeftIsOnlyLegalMoveThreeTriosModel implements ReadOnlyThreeTriosModel {

  private final ReadOnlyThreeTriosModel model;

  /**
   * Creates a new TopLeftIsOnlyLegalMoveThreeTriosModel using the given non-null model as a
   * delegate for methods other than `isMoveLegalAt`.
   */
  public TopLeftIsOnlyLegalMoveThreeTriosModel(ReadOnlyThreeTriosModel model) {
    this.model = Requirements.requireNonNull(model);
  }

  @Override
  public List<PlayerCard> getHand(PlayerColor playerColor) {
    return model.getHand(playerColor);
  }

  @Override
  public Cell[][] getGrid() {
    return model.getGrid();
  }

  @Override
  public Cell getCellAt(int rowIndex, int colIndex) {
    return model.getCellAt(rowIndex, colIndex);
  }

  @Override
  public PlayerCard getCardAt(int rowIndex, int colIndex) {
    return model.getCardAt(rowIndex, colIndex);
  }

  @Override
  public PlayerColor getPlayerAt(int rowIndex, int colIndex) {
    return model.getPlayerAt(rowIndex, colIndex);
  }

  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  @Override
  public PlayerColor getWinner() {
    return model.getWinner();
  }

  @Override
  public PlayerColor getCurrentPlayer() {
    return model.getCurrentPlayer();
  }

  @Override
  public int getWidth() {
    return model.getWidth();
  }

  @Override
  public int getHeight() {
    return model.getHeight();
  }

  @Override
  public boolean isMoveLegalAt(int rowIndex, int colIndex) {
    return rowIndex == 0 && colIndex == 0;
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, int cardIndex) {
    return model.getNumFlipsAt(rowIndex, colIndex, cardIndex);
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, PlayerCard card) {
    return model.getNumFlipsAt(rowIndex, colIndex, card);
  }

  @Override
  public int getScore(PlayerColor player) {
    return model.getScore(player);
  }
}
