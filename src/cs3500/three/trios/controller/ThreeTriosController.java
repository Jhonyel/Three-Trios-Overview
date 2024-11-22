package cs3500.three.trios.controller;

import cs3500.three.trios.model.PlayerColor;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.observable.ObservableThreeTriosModel;
import cs3500.three.trios.model.observable.ThreeTriosModelObserver;
import cs3500.three.trios.player.Player;
import cs3500.three.trios.util.Requirements;
import cs3500.three.trios.view.ThreeTriosGUIView;

/**
 * The controller for a single player in a game of three trios.
 */
public class ThreeTriosController implements Features, ThreeTriosModelObserver {

  private final ThreeTriosGUIView view;
  private final ThreeTriosModel model;
  private final Player player;

  /**
   * Creates a new controller with the given non-null view and model.
   *
   * @param view  the non-null view to visualize the game.
   * @param model the non-null model to represent the game.
   */
  public ThreeTriosController(
      ThreeTriosGUIView view, ObservableThreeTriosModel model, Player player
  ) {
    this.view = Requirements.requireNonNull(view);
    this.model = Requirements.requireNonNull(model);
    this.player = Requirements.requireNonNull(player);

    model.registerObserver(this);
    view.addFeatures(this);
  }

  @Override
  public void onHandCardClicked(PlayerColor selectedPlayerColor, int cardIndex) {
    System.out.printf("Card clicked in %s's hand at index %d\n", selectedPlayerColor, cardIndex);

    PlayerColor playerColor = player.getPlayerColor();
    boolean userClickedWrongHand = selectedPlayerColor != playerColor;
    if (userClickedWrongHand) {
      view.displayMessage(String.format("Please select a card in the %s hand", playerColor));

    } else {
      view.toggleSelection(selectedPlayerColor, cardIndex);
    }
  }

  @Override
  public void onEmptySpaceClicked() {
    view.clearSelection();
  }

  @Override
  public void onCellClicked(int rowIndex, int colIndex) {
    System.out.printf("Cell clicked at row %d, column %d\n", rowIndex, colIndex);

    if (model.isGameOver()) {
      return;
    }

    if (!view.hasSelectedCard()) {
      view.displayMessage("Please select a card before attempting to play");

    } else if (model.getCurrentPlayerColor() != player.getPlayerColor()) {
      view.displayMessage(String.format(
          "%s cannot play. It is currently %s's turn",
          player.getPlayerColor(),
          model.getCurrentPlayerColor()
      ));

    } else if (!model.isMoveLegalAt(rowIndex, colIndex)) {
      view.displayMessage(String.format(
          "%s cannot play to the specified cell. That move is illegal", player.getPlayerColor())
      );

    } else {
      model.playCardAt(rowIndex, colIndex, view.getSelectedCardIndex());
      if (model.isGameOver()) {
        PlayerColor winner = model.getWinner();
        boolean isGameTied = winner == null;
        if (isGameTied) {
          view.displayMessage("Game tied");

        } else {
          view.displayMessage(String.format(
              "%s wins with a score of %d",
              winner,
              model.getScore(winner)
          ));
        }
      }
    }

  }

  @Override
  public void onNewTurn(PlayerColor newPlayerColor) {
    view.clearSelection();
    view.refresh();
  }
}
