This document is meant to explain our `ThreeTriosModel` design and how human or AI players could
interact with the model. Players will not interact directly with the model but rather interact with
a controller that interacts with the model and a view that visualizes the model. We describe how the
controller will construct a model, how the controller and view will interact with it, and how the
player will interact with the controller and view.

# Constructing a `ThreeTriosModel`

In order for players to interact with the model, one must first construct a model and pass it into a
controller and a view. Currently, only one
implementation of `ThreeTriosModel` exists: `ThreeTriosModelImpl`. The `ThreeTriosModelImpl`
constructor takes in 3
arguments: `Cell[][] grid`, `List<Card> cards`, `boolean shouldShuffle`. `grid` is
a 2D array of `Cell` instances.

The following must be true about `grid`:

- `grid` must be non-null
- `grid` must be non-empty
- for every `row` in `grid`:
    - `row` must be non-null
    - `row` must be non-empty
    - `row` must not contain null elements
- `grid` must be rectangular
    - i.e. every row in `grid` must have the same length
- `grid` must have an odd number of card cells

In practice, `grid` can be constructed using the `GridFactory` class, which has one static
method `createFromConfigurationFilePath`, which takes in a `String` of the file path to a
configuration file and returns the grid represented by the configuration file's contents.

The following must be true about `cards`

- `cards` must be non-null
- `cards` must be non-empty
- every card in `cards` must be non-null
- `cards` must contain at least `n + 1` cards
    - where `n` is the number of card cells in `grid`

In practice, `cards` can be constructed using the `CardListFactory` class, which has one static
method `createFromConfigurationFilePath`, which takes in a `String` of the file path to a
configuration file and returns the list of cards represented by the configuration file's contents.

If `shouldShuffle` is true, a copy of `cards` will be shuffled before dealing cards. When dealing
cards, the first `(n + 1) / 2` cards will be dealt to the red player. The next `(n + 1) / 2` cards
will be dealt to the blue player. The rest of the cards will be discarded.

# How players interact with a `ThreeTriosModel`

## How players observe a `ThreeTriosModel`

The following methods are used in the view so that players can visualize the state of the game.

- `getCurrentPlayerColor()` returns the color of the current player. The view will show the players whose
  turns it is using this method, if the game is not over.
- `getHand(PlayerColor playerColor)` returns a copy of the hand of the player with the specified
  player color. The view will show the current player their hand using this method, if the game is
  not over.
- `getGrid()` returns a copy of the current grid. The view will show the players the current game
  state using
  this method.
- `getWinner()` returns the color of the winning player if the game is over. The view will show the
  players who won using this method, if the game is over.

## How players mutate a `ThreeTriosModel`

Players mutate a `ThreeTriosModel` through the controller, which provides one action to the players:
playing a card and having it battle. The controller will accomplish this by using the `playCardAt`
methods. There are two overloads of the method. Both take in a `int rowIndex` in the
range `[0, height)` and a `int colIndex` in the range `[0, width)`. One overload of the method takes
in a card in the current player's hand, while the other takes in an index of a card in the current
player's hand. Regardless of which overload is called, `playCardAt` plays the specified card to the
specified indices and then conducts battle against adjacent cells. Any cell that is flipped also
conducts battle against its adjacent cells.