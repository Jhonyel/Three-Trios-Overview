package cs3500.three.trios.model;

import cs3500.three.trios.model.card.PlayerCard;
import cs3500.three.trios.player.Player;
import cs3500.three.trios.util.LatchBoolean;
import cs3500.three.trios.util.Requirements;
import cs3500.three.trios.util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An observable version of our model. This interface allows us to register players of the game.
 * When the turn switches to a player, they are notified by this model. That is: when the turn
 * switches to a player, the model invokes that player's `onTurn()` method.
 */
public class ObservableThreeTriosModelImpl implements ObservableThreeTriosModel {

  private final ThreeTriosModel model;
  private final Map<PlayerColor, Player> players;
  private final LatchBoolean isGameStarted;
  private final List<ThreeTriosModelObserver> observers;

  /**
   * Creates a new ObservableThreeTriosModelImpl with the given model. Upon construction, no players
   * are registered as observers of this model. Invoking `playCardAt` on the supplied model will
   * have no effect on this model.
   *
   * @throws IllegalArgumentException if the model is null.
   */
  public ObservableThreeTriosModelImpl(ThreeTriosModel model) {
    Requirements.requireNonNull(model);
    this.model = model.getCopy();
    this.players = new HashMap<>();
    this.isGameStarted = new LatchBoolean();
    this.observers = new ArrayList<>();
  }

  @Override
  public void startGame() {
    requireExistsRedAndBluePlayers();
    requireGameHasNotStarted();
    isGameStarted.setTrue();
    onTurnChanged();
  }

  @Override
  public void registerPlayer(Player player) {
    Requirements.requireNonNull(player);
    if (players.containsKey(player.getPlayerColor())) {
      throw new IllegalStateException(String.format(
          "A %s player has already been added", player.getPlayerColor())
      );
    }
    players.put(player.getPlayerColor(), player);
  }

  @Override
  public void registerObserver(ThreeTriosModelObserver observer) {
    Requirements.requireNonNull(observer);
    observers.add(observer);
  }

  @Override
  public boolean isGameStarted() {
    return isGameStarted.isTrue();
  }

  /**
   * Invoked when the turn changes. Notifies the new player that it is now their turn.
   */
  private void onTurnChanged() {
    // note: `playCardAt` updates the current player color.
    // thus: `currentPlayer` refers to the player whose turn it is after `playCardAt` is invoked.
    PlayerColor currentPlayerColor = model.getCurrentPlayerColor();
    Player currentPlayer = players.get(currentPlayerColor);
    observers.forEach(observer -> observer.onNewTurn(currentPlayerColor));
    if (!isGameOver()) {
      currentPlayer.onTurn();
    }
  }

  ////////////////////////////////////////////////////////////////////////////
  // REQUIREMENTS
  ////////////////////////////////////////////////////////////////////////////


  /**
   * Checks that the game has not started. Throws an IllegalStateException if the game has started.
   */
  private void requireGameHasNotStarted() {
    if (isGameStarted()) {
      throw new IllegalStateException("Game has already started");
    }
  }

  /**
   * Checks that the game has started. Throws an IllegalStateException if the game has not started.
   */
  private void requireGameHasStarted() {
    if (!isGameStarted()) {
      throw new IllegalStateException("Game has not started");
    }
  }

  /**
   * Checks that `players` contains both red and blue players. Throws an ISE if not.
   */
  private void requireExistsRedAndBluePlayers() {
    boolean existsRedPlayer = players.containsKey(PlayerColor.RED);
    boolean existsBluePlayer = players.containsKey(PlayerColor.BLUE);
    if (!existsRedPlayer || !existsBluePlayer) {
      throw new IllegalStateException("Must have red and blue players");
    }
  }

  ////////////////////////////////////////////////////////////////////////////
  // DELEGATE METHODS
  ////////////////////////////////////////////////////////////////////////////

  @Override
  public List<PlayerCard> getHand(PlayerColor playerColor) {
    return model.getHand(playerColor);
  }

  @Override
  public Cell[][] getGrid() {
    return model.getGrid();
  }

  @Override
  public Cell getCellAt(int rowIndex, int colIndex) {
    return model.getCellAt(rowIndex, colIndex);
  }

  @Override
  public PlayerCard getCardAt(int rowIndex, int colIndex) {
    return model.getCardAt(rowIndex, colIndex);
  }

  @Override
  public PlayerColor getPlayerAt(int rowIndex, int colIndex) {
    return model.getPlayerAt(rowIndex, colIndex);
  }

  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  @Override
  public PlayerColor getWinner() {
    return model.getWinner();
  }

  @Override
  public PlayerColor getCurrentPlayerColor() {
    return model.getCurrentPlayerColor();
  }

  @Override
  public int getWidth() {
    return model.getWidth();
  }

  @Override
  public int getHeight() {
    return model.getHeight();
  }

  @Override
  public boolean isMoveLegalAt(int rowIndex, int colIndex) {
    return model.isMoveLegalAt(rowIndex, colIndex);
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, int cardIndex) {
    return model.getNumFlipsAt(rowIndex, colIndex, cardIndex);
  }

  @Override
  public int getNumFlipsAt(int rowIndex, int colIndex, PlayerCard card) {
    return model.getNumFlipsAt(rowIndex, colIndex, card);
  }

  @Override
  public int getScore(PlayerColor playerColor) {
    return model.getScore(playerColor);
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, PlayerCard card) {
    requireGameHasStarted();
    model.playCardAt(rowIndex, colIndex, card);
    onTurnChanged();
  }

  @Override
  public void playCardAt(int rowIndex, int colIndex, int cardIndex) {
    requireGameHasStarted();
    model.playCardAt(rowIndex, colIndex, cardIndex);
    onTurnChanged();
  }

  ////////////////////////////////////////////////////////////////////////////

  @Override
  public boolean equals(Object other) {
    if (other instanceof ReadOnlyThreeTriosModel) {
      return Utils.areModelsEqual(this, (ReadOnlyThreeTriosModel) other);
    }
    return false;
  }

}
