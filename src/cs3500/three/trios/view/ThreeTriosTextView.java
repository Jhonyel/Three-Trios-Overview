package cs3500.three.trios.view;

import static cs3500.three.trios.util.Requirements.requireNonNull;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.util.Utils;
import java.io.IOException;
import java.util.List;

/**
 * A class for displaying a textual representation of the state of a Three Trios game.
 */
public class ThreeTriosTextView implements ThreeTriosView {

  private final ThreeTriosModel model;
  private final Appendable output;

  /**
   * Creates a new ThreeTriosTextView that appends visualizations of the given model to the given
   * appendable.
   *
   * @throws IllegalArgumentException if any argument is null
   */
  public ThreeTriosTextView(ThreeTriosModel model, Appendable output) {
    this.model = requireNonNull(model);
    this.output = requireNonNull(output);
  }

  @Override
  public void render() {
    append(toString());
  }

  private void appendLine(String string) {
    append(string + "\n");
  }

  private void append(String string) {
    try {
      output.append(string);
    } catch (IOException e) {
      throw new IllegalStateException("unable to append output");
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    PlayerColor currentPlayer = model.getCurrentPlayer();
    stringBuilder.append(String.format("Player: %s\n", currentPlayer));

    Cell[][] grid = model.getGrid();
    for (Cell[] row : grid) {
      String rowString = getRowString(row);
      stringBuilder.append(rowString);
    }

    List<PlayerCard> hand = model.getHand(currentPlayer);
    stringBuilder.append("Hand:\n");
    stringBuilder.append(Utils.joinToString(hand, "\n"));

    return stringBuilder.toString();
  }

  private String getRowString(Cell[] row) {
    StringBuilder stringBuilder = new StringBuilder();
    for (Cell cell : row) {
      String cellCharacter = getCellCharacter(cell);
      stringBuilder.append(cellCharacter);
    }
    stringBuilder.append("\n");
    return stringBuilder.toString();
  }

  private String getCellCharacter(Cell cell) {
    if (cell.isHole()) {
      return " ";
    }
    if (cell.isEmptyCardCell()) {
      return "_";
    }
    // if we reach this point, `cell` must be an occupied card cell
    PlayerColor playerColor = cell.getCard().getPlayerColor();
    char playerColorChar = playerColor.toString().charAt(0);
    return String.valueOf(playerColorChar);
  }
}
