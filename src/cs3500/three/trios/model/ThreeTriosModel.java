package cs3500.three.trios.model;

import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.cell.Cell;
import java.util.List;
import java.util.Set;

public interface ThreeTriosModel {

  /**
   * Places the specified card at the specified cell.
   *
   * @param rowIndex the row index of the cell to place the card in
   * @param colIndex the col index of the cell to place the card in
   * @param card     the card to place at the specified cell
   * @throws IllegalArgumentException if the specified cell is out of bounds
   * @throws IllegalStateException    if the specified cell is a hole or occupied by another card.
   */
  void place(int rowIndex, int colIndex, Card card);

  /**
   * Conducts battle at the specified cell.
   *
   * @param rowIndex the row index of the cell to conduct the battle at
   * @param colIndex the col index of the cell to conduct the battle at
   * @throws IllegalArgumentException if the specified cell is out of bounds
   * @throws IllegalStateException    if the specified cell is not occupied by a card or a hole
   */
  void battle(int rowIndex, int colIndex);

  /**
   * Returns a copy of the specified player's hand. Modifying the returned list does not affect this
   * model.
   *
   * @param player the player whose hand is to be returned
   * @return a copy of the hand of the specified player.
   */
  List<Card> getHand(Player player);

  // todo: make sure we are also cloning the cells in the grid and not just the grid
  // we need to make sure that setting the cells of the grid does not affect the grid in the model
  Cell[][] getGrid();

  Cell getCellAt(int rowIndex, int colIndex);

  void startGame();

  boolean isGameOver();

  boolean isGameWon();

  Player getWinner();

  Set<Card> getAdjacentEnemyCardsAt(int rowIndex, int colIndex);

  int getNumCards(Player player);
}
