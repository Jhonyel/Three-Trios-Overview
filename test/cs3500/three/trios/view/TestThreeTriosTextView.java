package cs3500.three.trios.view;

import static cs3500.three.trios.Examples.create3x3ModelWith9CardCells;
import static cs3500.three.trios.Examples.create3x5ModelWith9CardCells;
import static cs3500.three.trios.Examples.create4x4ModelWith9CardCells;
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
  private ThreeTriosModel new3x3ModelWith9CardCells;
  private ThreeTriosModel new4x4ModelWith9CardCells;
  private Appendable output;

  @Before
  public void setUp() throws IOException {
    this.new5x7ModelWith15CardCells = create5x7ModelWith15CardCells();
    this.new3x5ModelWith9CardCells = create3x5ModelWith9CardCells();
    this.new5x3ModelWith9CardCells = create5x3ModelWith9CardCells();
    this.new3x3ModelWith9CardCells = create3x3ModelWith9CardCells();
    this.new4x4ModelWith9CardCells = create4x4ModelWith9CardCells();
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

  @Test
  public void testRender5x3With9CardCellsAfterPlayingCards() {
    ThreeTriosTextView view = new ThreeTriosTextView(new5x3ModelWith9CardCells, output);
    new5x3ModelWith9CardCells.playCardAt(0, 1, 1);
    view.render();
    String expectedOutput = String.join(
            "\n",
            "Player: BLUE",
            " R_",
            "_ _",
            "_ _",
            " _ ",
            "_ _",
            "Hand:",
            "GoldenFalcon A 5 2 9",
            "ShadowBear 2 A 6 4",
            "CunningFox 8 7 4 3",
            "FierceCobra 9 6 3 1",
            "GentleDove 3 2 9 5"
    );
    String actualOutput = output.toString();
    assertEquals(expectedOutput, actualOutput);
    new5x3ModelWith9CardCells.playCardAt(0, 2, 0);
    view.render();
    actualOutput = view.toString();
    expectedOutput = String.join(
            "\n",
            "Player: RED",
            " BB",
            "_ _",
            "_ _",
            " _ ",
            "_ _",
            "Hand:",
            "BlazingTiger A 7 3 5",
            "SilentEagle 5 4 A 7",
            "SwiftLion 6 3 9 2",
            "MightyRhino 4 8 7 1"
    );
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testWinEndScreen() {
    ThreeTriosTextView view = new ThreeTriosTextView(new3x3ModelWith9CardCells, output);
    new3x3ModelWith9CardCells.playCardAt(0, 1, 1);
    new3x3ModelWith9CardCells.playCardAt(0, 0, 0);
    new3x3ModelWith9CardCells.playCardAt(1, 0, 0);
    new3x3ModelWith9CardCells.playCardAt(2, 0, 0);
    new3x3ModelWith9CardCells.playCardAt(0, 2, 0);
    new3x3ModelWith9CardCells.playCardAt(1, 1, 0);
    new3x3ModelWith9CardCells.playCardAt(2, 1, 0);
    new3x3ModelWith9CardCells.playCardAt(1, 2, 0);
    new3x3ModelWith9CardCells.playCardAt(2, 2, 0);
    view.render();
    String expectedOutput = String.join(
            "\n",
            "Winner: BLUE",
            "BBB",
            "RBB",
            "BRR"
    );
    String actualOutput = output.toString();
    assertEquals(expectedOutput, actualOutput);
  }

  @Test
  public void testTieEndScreen() {
    ThreeTriosTextView view = new ThreeTriosTextView(new4x4ModelWith9CardCells, output);
    new4x4ModelWith9CardCells.playCardAt(0, 1, 1);
    new4x4ModelWith9CardCells.playCardAt(3, 0, 0);
    new4x4ModelWith9CardCells.playCardAt(0, 2, 0);
    new4x4ModelWith9CardCells.playCardAt(3, 3, 0);
    new4x4ModelWith9CardCells.playCardAt(2, 0, 0);
    new4x4ModelWith9CardCells.playCardAt(3, 1, 0);
    new4x4ModelWith9CardCells.playCardAt(0, 0, 0);
    new4x4ModelWith9CardCells.playCardAt(3, 2, 0);
    new4x4ModelWith9CardCells.playCardAt(0, 3, 0);
    view.render();
    String expectedOutput = String.join(
            "\n",
            "Winner: BLUE",
            "BBB",
            "RBB",
            "BRR"
    );
    String actualOutput = output.toString();
    assertEquals(expectedOutput, actualOutput);
  }
}
