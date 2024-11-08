package cs3500.three.trios.controller;

import cs3500.three.trios.model.PlayerColor;

public interface Features {
  // Additionally, your view should (temporarily) print a message (using System.out) containing
  // the index of the card that was clicked on as well as which player owns that hand.
  void onCardClicked(PlayerColor player, int cardIndex);

  // a user should be able to click on a cell in the grid and the view should (temporarily) print
  // a message (using System.out) containing the coordinates of the cell that was clicked on, in
  // whatever coordinate system you used in your model. Note: this is not the same thing as the
  // physical mouse coordinates of the mouse event!
  void onCellClicked(int rowIndex, int colIndex);

  void quit();
}
