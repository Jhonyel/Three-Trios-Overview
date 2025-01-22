# Three Trios #
## Overview ##
Two player card game that involves placing cards to a board and battling with the opposing teams cards based off attack values. The winner of the game is determined when the board has been filled and the player with the most cards flipped to their team color. To flip a card, a player must place a card adjacent to an opposing teams card and the attack value in the corresponing direction must be higher (in default variant rules).

## Game Settings ##
Three Trios can be played with the following player-types: Human v Human, Human v AI, AI v AI, with specifed AI strategies.
There are also game variants that can be applied to change how flips occur in the battle phase of the gameplay.
Both of these game settings are determined via the command-line when the game starts. 

## Game Interaction ## 
When the game starts two windows will appear, one for each player. To make a move a user must click a card from their and hand a valid board cell to play to. If playing against AI, a move will automatically be played, else the user must wait for the other player to make a move. 

## Access to Code
Please note that the code for this project is private due to class policies. If you want access to the code, feel free to reach out, and it can be provided upon request.

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

