package cs3500.three.trios.controller;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.util.Requirements;
import cs3500.three.trios.view.ThreeTriosGUIView;

/**
 * The controller of a game of three trios. Implements the features interface and handles events.
 */
public class ThreeTriosController implements Features {

  private final ThreeTriosGUIView view;
  private final ThreeTriosModel model;

  /**
   * Creates a new controller with the given non-null view and model.
   *
   * @param view the non-null view to visualize the game.
   * @param model the non-null model to represent the game.
   */
  public ThreeTriosController(ThreeTriosGUIView view, ThreeTriosModel model) {
    this.view = Requirements.requireNonNull(view);
    this.model = Requirements.requireNonNull(model);
  }

  @Override
  public void onHandCardClicked(PlayerColor player, int cardIndex) {
    System.out.printf("Card clicked in %s's hand at index %d\n", player, cardIndex);
    if (player == model.getCurrentPlayer()) {
      view.toggleSelection(player, cardIndex);
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
  }

}
