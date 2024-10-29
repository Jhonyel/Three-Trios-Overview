package cs3500.three.trios.view;

import static cs3500.three.trios.Examples.create3x5ModelWith9CardCells;
import static cs3500.three.trios.Examples.create5x3ModelWith9CardCells;
import static cs3500.three.trios.Examples.create5x7ModelWith15CardCells;
import static org.junit.Assert.assertEquals;

import cs3500.three.trios.model.ThreeTriosModel;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the three trios text view.
 */
public class TestThreeTriosTextView {

  private ThreeTriosModel new5x7ModelWith15CardCells;
  private ThreeTriosModel new3x5ModelWith9CardCells;
  private ThreeTriosModel new5x3ModelWith9CardCells;
  private Appendable output;

  @Before
  public void setUp() throws IOException {
    this.new5x7ModelWith15CardCells = create5x7ModelWith15CardCells();
    this.new3x5ModelWith9CardCells = create3x5ModelWith9CardCells();
    this.new5x3ModelWith9CardCells = create5x3ModelWith9CardCells();
    this.output = new StringBuilder();
  }

  @Test
  public void testRender5x7With15CardCells() {
    ThreeTriosTextView view = new ThreeTriosTextView(new5x7ModelWith15CardCells, output);
    view.render();
    String expectedOutput = String.join(
        "\n",
        "Player: RED",
        "__    _",
        "_ _   _",
        "_  _  _",
        "_   _ _",
        "_    __",
        "Hand:",
        "BlazingPhoenix 9 7 A 5",
        "FrozenDragon 6 A 4 8",
        "ThunderLion 3 9 5 A",
        "ShadowWolf A 2 7 6",
        "MysticGolem 8 4 A 3",
        "CrimsonSerpent 7 6 9 A",
        "EtherealKnight 5 A 3 7",
        "LunarBear A 9 8 2"
    );
    String actualOutput = output.toString();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testRender3x5With9CardCells() {
    ThreeTriosTextView view = new ThreeTriosTextView(new3x5ModelWith9CardCells, output);
    view.render();
    String expectedOutput = String.join(
        "\n",
        "Player: RED",
        "_ _  ",
        " __ _",
        "__ __",
        "Hand:",
        "BlazingTiger A 7 3 5",
        "FrozenWolf 9 1 8 6",
        "SilentEagle 5 4 A 7",
        "SwiftLion 6 3 9 2",
        "MightyRhino 4 8 7 1"
    );
    String actualOutput = output.toString();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testRender5x3With9CardCells() {
    ThreeTriosTextView view = new ThreeTriosTextView(new5x3ModelWith9CardCells, output);
    view.render();
    String expectedOutput = String.join(
        "\n",
        "Player: RED",
        " __",
        "_ _",
        "_ _",
        " _ ",
        "_ _",
        "Hand:",
        "BlazingTiger A 7 3 5",
        "FrozenWolf 9 1 8 6",
        "SilentEagle 5 4 A 7",
        "SwiftLion 6 3 9 2",
        "MightyRhino 4 8 7 1"
    );
    String actualOutput = output.toString();
    assertEquals(expectedOutput, actualOutput);
  }
}
