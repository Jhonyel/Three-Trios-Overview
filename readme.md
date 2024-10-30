# Overview
The purpose of this codebase is to model and visualize a game of three trios. This document assumes
that the reader is familiar with the rules of three trios and the model/view/controller design. As
future assignments are released, this codebase will change to add variants to the game (e.g. new rules).

# Quick start

```java
ThreeTriosModel model = Examples.create5x7ModelWith15CardCells();

// get the color of the current player
PlayerColor currentPlayer = model.getCurrentPlayer();

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
 
```

# Key components

The key components of this codebase are: `ThreeTriosModel` and `ThreeTriosModelView`

- `ThreeTriosModel`
  - The grid is represented as a 2D array of `Cell` instances. A `Cell` is either a hole cell, an empty card cell, or an occupied card cell.
  - The hands of the players are represented using two `List<Card>` instances. A `Card` has a name, and attack values (represented by the `AttackValue` enum) in the directions north, south, east, and west (represented by the `Direction` enum)
- `ThreeTriosModelView`
  - `ThreeTriosModelView` takes in a `ThreeTriosModel` and an `Appendable`. Calling `render` will create a visualization of the given model and append it to the given appendable.
  - Currently, the only implementation of `ThreeTriosModelView` is `ThreeTriosModelTextView` which appends a textual representation to an appendable.

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
- Files with generally useful methods that should be accessible anywhere exist in the `cs3500.three.trios.util` package
  - This contains:
    - `Requirements`
      - Used for validating arguments
    - `Utils`
- Files relevant to the view exist in the `cs3500.three.trios.view` package
  - This contains:
    - `ThreeTriosView`
    - `ThreeTriosTextView`
 