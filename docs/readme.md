# Overview

The purpose of this codebase is to model and visualize a game of three trios. This document assumes
that the reader is familiar with the rules of three trios and the model/view/controller design. As
future assignments are released, this codebase will change to add variants to the game (e.g. new
rules).

# Quick start

```java
public class QuickStart {

  public static void main(String[] args) {
    ThreeTriosModel model = Examples.create5x7ModelWith15CardCells();

    // get the color of the current player
    PlayerColor currentPlayer = model.getCurrentPlayerColor();

    // get the hand of the current player
    List<PlayerCard> currentHand = model.getHand(currentPlayer);

    // get the grid of the model
    Cell[][] grid = model.getGrid();

    // get the cell at row 2, col 3
    Cell cell = model.getCellAt(2, 3);

    // before calling `getCardAt` or `getPlayer`, check that
    // the cell you are getting from is an occupied card cell
    if (cell.isOccupiedCardCell()) {
      PlayerCard card = model.getCardAt(2, 3);
      PlayerColor playerColor = model.getPlayerAt(2, 3);
    }

    // before calling `getWinner`, check that the game is over
    if (model.isGameOver()) {
      PlayerColor winner = model.getWinner();
    }
  }
}
```

# Key components

The key components of this codebase are: `ThreeTriosModel` and `ThreeTriosModelView`

- `ThreeTriosModel`
    - The grid is represented as a 2D array of `Cell` instances. A `Cell` is either a hole cell, an
      empty card cell, or an occupied card cell.
    - The hands of the players are represented using two `List<Card>` instances. A `Card` has a
      name, and attack values (represented by the `AttackValue` enum) in the directions north,
      south, east, and west (represented by the `Direction` enum)
- `ThreeTriosModelView`
    - `ThreeTriosModelView` takes in a `ThreeTriosModel` and an `Appendable`. Calling `render` will
      create a visualization of the given model and append it to the given appendable.
    - Currently, the only implementation of `ThreeTriosModelView` is `ThreeTriosModelTextView` which
      appends a textual representation to an appendable.

# Source organization

- Files relevant to the model exist in the `cs3500.three.trios.model` package
    - This contains:
        - `Cell`
        - `Direction`
        - `GridFactory`
        - `PlayerColor`
        - `ThreeTriosModel`
        - `ThreeTriosModelImpl`
    - Files relevant to representing cards exist in the package `cs3500.three.trios.model.card`
        - This contains:
            - `AttackValue`
            - `Card`
            - `CardImpl`
            - `CardListFactory`
            - `PlayerCard`
- Files with generally useful methods that should be accessible anywhere exist in
  the `cs3500.three.trios.util` package
    - This contains:
        - `Requirements`
            - Used for validating arguments
        - `Utils`
- Files relevant to the view exist in the `cs3500.three.trios.view` package
    - This contains:
        - `ThreeTriosView`
        - `ThreeTriosTextView`

# Changes for part 2

In part 2, we made the following changes to existing classes

- `ThreeTriosModel`
    - What used to be `ThreeTriosModel` is now two interfaces: `ThreeTriosModel`
      and `ReadOnlyThreeTriosModel`. `ReadOnlyThreeTriosModel` contains only the observation
      methods. `ThreeTriosModel` extends `ReadOnlyThreeTriosModel` and declares the operations.
    - We added the following methods
        - `getWidth`
        - `getHeight`
        - `isMoveLegalAt`
        - `getNumFlipsAt`
            - We implemented this method using the same logic as `battle`
        - `getScore`
            - We implemented this method using the same logic as `getWinner`
    - These methods were missing simply because we had not thought to add them.
    -
- `AttackValue`
    - We added a method `toInt` that returns the integer value of an `AttackValue`. This is useful
      for `CornerMoveStrategy` which needs to know what the strongest card to play is in a
      particular corner. We accomplish this by taking the average of the vulnerable attack values of
      a card in a particular corner. We say that attack values are vulnerable if they can engage in
      battle. For example, if we placed a card in the north
- `PlayerColor`
    - We added a method `getColor` which returns the `java.awt.Color` associated with a player
      color. `PlayerColor.RED.getColor` returns `java.awt.Color.RED`. `PlayerColor.BLUE.getColor`
      returns `java.awt.Color.BLUE`

# Changes for part 3

## New classes and interfaces

- `ObservableThreeTriosModel`
    - A decorator of a `ThreeTriosModel` with extended functionality. Namely:
        - Clients can register `Player` objects as observers of the model. When the turn changes,
          the model will notify the new player that it is their turn by invoking their `onTurn`
          method.
        - Clients can register `ThreeTriosModelObserver` objects as observers of the
          model. `ThreeTriosModelObserver` objects are different from `Player` objects. While both
          do observe the model, there should only ever be one red `Player` and one blue `Player`
          registered to observe a model. On the other hand, there is no limit to how
          many `ThreeTriosModelObserver` objects a client can reasonably register to a model. Thus
          we make the design decision to make these two observers different types.
    - Clients must invoke `startGame` prior to invoking `playCardAt`. This is because the model must
      have one red player and one blue player registered before playing cards. Otherwise, how would
      the model know which player to notify upon a turn changing? An attempt to invoke `playCardAt`
      before invoking `startGame` throws an exception. An attempt to invoke `startGame` before
      registering two players also throws.
        - Note: We likely could have simplified this design by omitting `startGame` and instead
          have `playCardAt` throw an exception if not enough players had been registered. However,
          the assignment description specifically instructed to implement a `startGame` method, so
          we chose to adhere to that.
    - Clients should never directly invoke `registerPlayer` to register a player. Rather, clients
      should simply construct a `Player` and pass in the observable model they would wish to
      register the constructed player to. The `Player` constructor will automatically register the
      new player to observe the given model. Thus, any attempt thereafter to register the same
      player would result in an exception being thrown.
- `ThreeTriosModelObserver`
    - An interface describing objects which observe a model. An object observes a model listening
      for when a turn changes. The object must first be registered to observe the model. This can be
      done by invoking `registerObserver`. Thereafter, when the turn changes to a new player, the
      model will notify all registered observers by invoking each one's respective `onNewTurn`
      method, passing an argument of the color of the player whose turn it now is.
- `Player`
    - An abstract class describing a player in a game of three trios. A `Player` is aware of what
      game it belongs to and what color it is in said game. Upon construction, a `Player` is
      registered to observe the model that was passed in. Upon the turn changing to a `Player`,
      it's `onTurn` method will be invoked.
    - `HumanPlayer`
        - A human player is really just a stub object to satisfy the model's requirement that two
          player's must be registered. It's `onTurn` method does nothing, since the program must
          wait for the user to interact with the GUI before making a move.
    - `ComputerPlayer`
        - A computer player is a player that is also aware of the strategy it uses to make moves.
          This strategy is passed in the constructor.
        - A computer player, unlike a human player, actually does have an `onTurn` method that does
          something. Namely, the computer player gets the best move according to its given strategy,
          and then performs that move on the model to which it belongs.

## Playing the game

- In order to play the game, you must run the Hw7.jar file; i.e. run the following in a terminal

```
java -jar Hw7.jar <red player type> <blue player type> 
```

- The next section describes the command line arguments in the above command

### Command line arguments

- The main method expects 2 command line arguments.
- The first argument is the red player type.
    - One of "HUMAN" "CORNER_MOVE" or "MAX_NUM_FLIPS"
- The second argument is the blue player type.
    - One of "HUMAN" "CORNER_MOVE" or "MAX_NUM_FLIPS"
- If there are not 2 command line arguments, or any of the command line arguments are not valid
  values, an illegal argument exception is thrown