package cs3500.three.trios.model.mock;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ReadOnlyThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Requirements;
import java.io.IOException;
import java.util.List;

/**
 * A mock ReadOnlyThreeTriosModel which logs all method calls to a given Appendable.
 */
public class LoggingThreeTriosModel implements ReadOnlyThreeTriosModel {

  private final ReadOnlyThreeTriosModel model;
  private final Appendable output;

  /**
   * Creates a new LoggingThreeTriosModel. Uses the given non-null model as a delegate for methods.
   * Uses the given non-null Appendable for logging.
   */
  public LoggingThreeTriosModel(ReadOnlyThreeTriosModel model, Appendable output) {
    this.model = Requirements.requireNonNull(model);
    this.output = Requirements.requireNonNull(output);
  }

  /**
   * Logs the given string to this model's appendable. If the appendable throws an IOException, we
   * throw a RuntimeException.
   */
  private void log(String string) {
    try {
      output.append(string);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<PlayerCard> getHand(PlayerColor playerColor) {
    log(String.format("getHand(%s)\n", playerColor));
    return model.getHand(playerColor);
  }

  @Override
  public Cell[][] getGrid() {
    log("getGrid()\n");
    return model.getGrid();
  }

  @Override
  public Cell getCellAt(int rowIndex, int colIndex) {
    log(String.format("getCellAt(%d, %d)\n", rowIndex, colIndex));
    return model.getCellAt(rowIndex, colIndex);
  }

  @Override
  public PlayerCard getCardAt(int rowIndex, int colIndex) {
    log(String.format("getCardAt(%d, %d)\n", rowIndex, colIndex));
    return model.getCardAt(rowIndex, colIndex);
  }

  @Override
  public PlayerColor getPlayerAt(int rowIndex, int colIndex) {
    log(String.format("getPlayerAt(%d, %d)\n", rowIndex, colIndex));
    return model.getPlayerAt(rowIndex, colIndex);
  }

  @Override
  public boolean isGameOver() {
    log("isGameOver()\n");
    return model.isGameOver();
  }

  @Override
  public PlayerColor getWinner() {
    log("getWinner()\n");
    return model.getWinner();
  }

  @Override
  public PlayerColor getCurrentPlayerColor() {
    log("getCurrentPlayerColor()\n");
    return model.getCurrentPlayerColor();
  }

  @Override
  public int getWidth() {
    log("getWidth()\n");
    return model.getWidth();
  }

  @Override
  public int getHeight() {
    log("getHeight()\n");
    return model.getHeight();
  }

  @Override
  public boolean isMoveLegalAt(int rowIndex, int colIndex) {
    log(String.format("isMoveLegalAt(%d, %d)\n", rowIndex, colIndex));
    return model.isMoveLegalAt(rowIndex, colIndex);
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, int cardIndex) {
    log(String.format("getNumFlipsAt(%d, %d, %d)\n", rowIndex, colIndex, cardIndex));
    return model.getNumFlipsAt(rowIndex, colIndex, cardIndex);
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, PlayerCard card) {
    log(String.format("getNumFlipsAt(%d, %d, %s)\n", rowIndex, colIndex, card));
    return model.getNumFlipsAt(rowIndex, colIndex, card);
  }

  @Override
  public int getScore(PlayerColor player) {
    log(String.format("getScore(%s)\n", player));
    return model.getScore(player);
  }
}
