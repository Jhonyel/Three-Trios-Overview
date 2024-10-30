package cs3500.three.trios;

import cs3500.three.trios.model.Cell;
import cs3500.three.trios.model.GridFactory;
import cs3500.three.trios.model.ThreeTriosModel;
import cs3500.three.trios.model.ThreeTriosModelImpl;
import cs3500.three.trios.model.card.Card;
import cs3500.three.trios.model.card.CardListFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Examples {

  public static Cell[][] create5x7GridWith15CardCells() throws IOException {
    return GridFactory.createFromConfigurationFilePath(
        "configuration-files" + File.separator + "5x7-grid-configuration.txt");
  }

  public static Cell[][] create3x5GridWith9CardCells() throws IOException {
    return GridFactory.createFromConfigurationFilePath(
        "configuration-files" + File.separator + "3x5-grid-configuration.txt");
  }

  public static Cell[][] create5x3GridWith9CardCells() throws IOException {
    return GridFactory.createFromConfigurationFilePath(
        "configuration-files" + File.separator + "5x3-grid-configuration.txt");
  }

  public static Cell[][] create3x3GridWith9CardCells() throws IOException {
    return GridFactory.createFromConfigurationFilePath(
            "configuration-files" + File.separator + "3x3-grid-with-no-holes-configuration.txt");
  }

  public static Cell[][] create4x4GridWith9CardCells() throws IOException {
    return GridFactory.createFromConfigurationFilePath(
            "configuration-files" + File.separator + "4x4-disconnected-grid-with-holes.txt"
    );
  }

  public static List<Card> create16Cards() throws IOException {
    return CardListFactory.createFromConfigurationFilePath(
        "configuration-files" + File.separator + "16-cards-configuration.txt");
  }

  public static List<Card> create10Cards() throws IOException {
    return CardListFactory.createFromConfigurationFilePath(
        "configuration-files" + File.separator + "10-cards-configuration.txt");
  }

  public static ThreeTriosModel create5x7ModelWith15CardCells() throws IOException {
    Cell[][] grid = create5x7GridWith15CardCells();
    List<Card> cards = create16Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }

  public static ThreeTriosModel create3x5ModelWith9CardCells() throws IOException {
    Cell[][] grid = create3x5GridWith9CardCells();
    List<Card> cards = create10Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }

  public static ThreeTriosModel create5x3ModelWith9CardCells() throws IOException {
    Cell[][] grid = create5x3GridWith9CardCells();
    List<Card> cards = create10Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }

  public static ThreeTriosModel create3x3ModelWith9CardCells() throws IOException {
    Cell[][] grid = create3x3GridWith9CardCells();
    List<Card> cards = create10Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }
  public static ThreeTriosModel create4x4ModelWith9CardCells() throws IOException {
    Cell[][] grid = create4x4GridWith9CardCells();
    List<Card> cards = create10Cards();
    return new ThreeTriosModelImpl(grid, cards, false);
  }
}
