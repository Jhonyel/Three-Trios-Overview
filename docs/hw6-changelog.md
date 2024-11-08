In homework 6, we made the following changes

- `ThreeTriosModel`
    - What used to be `ThreeTriosModel` is now two interfaces: `ThreeTriosModel`
      and `ReadOnlyThreeTriosModel`. `ReadOnlyThreeTriosModel` contains only the observation
      methods. `ThreeTriosModel` extends `ReadOnlyThreeTriosModel` and declares the operations.
    - We added the following methods
        - `getWidth`
        - `getHeight`
        - `isMoveLegalAt`
        - `getNumFlipsAt`
        - `getScore`
- `AttackValue`
    - We added a method `toInt` that returns the integer value of an `AttackValue`. This is useful
      for `CornerMoveStrategy` which needs to know what the strongest card to play is in a
      particular corner. We accomplish this by taking the average of the vulnerable attack values of
      a card in a particular corner. We say that attack values are vulnerable if they can engage in
      battle. For example, if we placed a card in the north 