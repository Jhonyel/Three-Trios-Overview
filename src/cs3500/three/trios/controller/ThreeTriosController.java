package cs3500.three.trios.controller;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.player.Player;
import cs3500.three.trios.util.Requirements;
import cs3500.three.trios.view.ThreeTriosGUIView;

/**
 * The controller of a game of three trios. Implements the features interface and handles events.
 */
public class ThreeTriosController implements Features {

  private final ThreeTriosGUIView view;
  private final ThreeTriosModel model;
  private final Player player;

  /**
   * Creates a new controller with the given non-null view and model.
   *
   * @param view the non-null view to visualize the game.
   * @param model the non-null model to represent the game.
   */
  public ThreeTriosController(ThreeTriosGUIView view, ThreeTriosModel model, Player player) {
    this.view = Requirements.requireNonNull(view);
    this.model = Requirements.requireNonNull(model);
    this.player = Requirements.requireNonNull(player);
  }

  @Override
  public void onHandCardClicked(PlayerColor playerColor, int cardIndex) {
    System.out.printf("Card clicked in %s's hand at index %d\n", playerColor, cardIndex);

    // if playerColor != player.getPlayerColor()
    // - displayMessage("Please select a card in the {player.getPlayerColor()} hand");
    // - view.clearSelection();
    // - return

    // if playerColor != model.getCurrentPlayerColor()
    // - displayMessage("It is not {player.getPlayerColor()}'s turn");
    // - return

    // view.toggleSelection(player, cardIndex);

    if (playerColor == model.getCurrentPlayerColor()) {
      view.toggleSelection(playerColor, cardIndex);
    } else {
      view.clearSelection();
    }
  }

  @Override
  public void onEmptySpaceClicked() {
    view.clearSelection();
  }

  @Override
  public void onCellClicked(int rowIndex, int colIndex) {
    System.out.printf("Cell clicked at row %d, column %d\n", rowIndex, colIndex);
    view.clearSelection();

    if (model.getCurrentPlayerColor() == player.getPlayerColor()) {
      model.playCardAt(rowIndex, colIndex, view.getSelectCardIndex());
    }
  }
}
