package cs3500.three.trios.examples;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import java.util.List;

public class ThreeTriosModelExamples {

  /**
   * Returns a 5x7 model with 15 card cells.
   */
  public static ThreeTriosModel create5x7ModelWith15CardCells() {
    Cell[][] grid = GridExamples.create5x7GridWith15CardCells();
    List<Card> cards = CardListExamples.create16Cards();
    return ThreeTriosModelImpl.createNewGame(grid, cards, false);
  }

  /**
   * Returns a 3x5 model with 9 card cells.
   */
  public static ThreeTriosModel create3x5ModelWith9CardCells() {
    Cell[][] grid = GridExamples.create3x5GridWith9CardCells();
    List<Card> cards = CardListExamples.create10Cards();
    return ThreeTriosModelImpl.createNewGame(grid, cards, false);
  }

  /**
   * Returns a 5x3 model with 9 card cells.
   */
  public static ThreeTriosModel create5x3ModelWith9CardCells() {
    Cell[][] grid = GridExamples.create5x3GridWith9CardCells();
    List<Card> cards = CardListExamples.create10Cards();
    return ThreeTriosModelImpl.createNewGame(grid, cards, false);
  }

  /**
   * Returns a 3x3 model with 9 card cells.
   */
  public static ThreeTriosModel create3x3ModelWith9CardCells() {
    Cell[][] grid = GridExamples.create3x3GridWith9CardCells();
    List<Card> cards = CardListExamples.create10Cards();
    return ThreeTriosModelImpl.createNewGame(grid, cards, false);
  }

  /**
   * Returns a 4x4 model with 9 card cells.
   */
  public static ThreeTriosModel create4x4ModelWith9CardCells() {
    Cell[][] grid = GridExamples.create4x4GridWith9CardCells();
    List<Card> cards = CardListExamples.create10Cards();
    return ThreeTriosModelImpl.createNewGame(grid, cards, false);
  }
}
