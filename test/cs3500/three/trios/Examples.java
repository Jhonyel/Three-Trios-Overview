package cs3500.three.trios;

import static java.io.File.pathSeparator;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.GridFactory;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardListFactory;
import java.util.List;

public class Examples {

  public static Cell[][] create5x7GridWith15CardCells() {
    return GridFactory.createFromConfigurationFilePath(
        "configuration-files" + pathSeparator + "5x7-grid-configuration.txt");
  }

  public static Cell[][] create3x5GridWith9CardCells() {
    return GridFactory.createFromConfigurationFilePath(
        "configuration-files" + pathSeparator + "3x5-grid-configuration.txt");
  }

  public static Cell[][] create5x3GridWith9CardCells() {
    return GridFactory.createFromConfigurationFilePath(
        "configuration-files" + pathSeparator + "5x3-grid-configuration.txt");
  }

  public static List<Card> create16Cards() {
    return CardListFactory.createFromConfigurationFilePath(
        "configuration-files" + pathSeparator + "16-cards-configuration.txt"
    );
  }

  public static List<Card> create10Cards() {
    return CardListFactory.createFromConfigurationFilePath(
        "configuration-files" + pathSeparator + "10-cards-configuration.txt"
    );
  }

  public static ThreeTriosModel create5x7ModelWith15CardCells() {
    Cell[][] grid = create5x7GridWith15CardCells();
    List<Card> cards = create16Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }

  public static ThreeTriosModel create3x5ModelWith9CardCells() {
    Cell[][] grid = create3x5GridWith9CardCells();
    List<Card> cards = create10Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }

  public static ThreeTriosModel create5x3ModelWith9CardCells() {
    Cell[][] grid = create3x5GridWith9CardCells();
    List<Card> cards = create10Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }
}
