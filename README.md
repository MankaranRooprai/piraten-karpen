# Piraten Karpen

- Author: Mankaran Singh Rooprai
- Email: roopraim@mcmaster.ca

This Java program simulates the popular game "Piraten Karpen" in the command-line where players roll dice to collect treasures and engage in battles.

## Features

- **Player Class**: Contains the main logic for simulating player actions, rolling dice, and calculating scores.
- **Two Strategies**: Two strategies are implemented for the player:
  - **Combo Strategy**: A strategy where the player strategically decides which dice to reroll based on the current game state.
  - **Random Reroll Strategy**: A strategy where the player randomly selects a number of dice to reroll each turn.
- **Logging**: Utilizes Log4j for logging game events and strategies.
- **Card Deck**: Represents a deck of cards that influence gameplay by activating special modes like sea battles or monkey mode.

## Strategies

### Combo Strategy

This strategy involves strategic decision-making based on the current game state. The player decides which dice to reroll to maximize their score while avoiding risks like collecting too many skulls.

### Random Reroll Strategy

In this strategy, the player randomly selects a number of dice to reroll each turn. It's a simpler approach compared to the combo strategy but still aims to maximize score and avoid accumulating skulls.

## Logging

The game utilizes Log4j for logging important events and decisions made during gameplay. This allows for better understanding of the simulation process and helps in debugging.

## Build and Execution

- To clean your working directory:
  - `mvn clean`
- To compile the project:
  - `mvn compile`
- To run the project in development mode:
  - `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
- To package the project as a turn-key artefact:
  - `mvn package`
- To run the packaged delivery:
  - `java -jar target/piraten-karpen-jar-with-dependencies.jar`
- To run the game with command line strategies:
  - `java -jar target/piraten-karpen-jar-with-dependencies.jar random combo`
- To run the game with trace mode on (logging):
  - `java -jar target/piraten-karpen-jar-with-dependencies.jar random combo trace`
- To view log file (if trace mode is on):
  - Open Log.log file

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

- Status:
  - Pending (P), Started (S), Blocked (B), Done (D)
- Definition of Done (DoD):
  - Feature works according to the feature requirements. Feature can be implemented whenever needed easily due to OOP principles. Feature can be easily maintained if a change needs to be made.

### Backlog

| MVP? | Id  | Feature                                                                                                                                               | Status | Started  | Delivered |
| :--: | :-: | ----------------------------------------------------------------------------------------------------------------------------------------------------- | :----: | :------: | :-------: |
|  x   | F01 | Roll a dice                                                                                                                                           |   D    | 01/01/23 | 01/12/23  |
|  x   | F02 | Roll eight dices                                                                                                                                      |   D    | 01/12/23 | 01/13/23  |
|  x   | F03 | Compute score by counting the numbers of gold coins and diamonds and multiplying it by 100                                                            |   D    | 01/11/23 | 01/13/23  |
|  x   | F04 | Implement second player                                                                                                                               |   D    | 01/12/23 | 01/12/23  |
|  x   | F05 | Reroll until 3 skulls accumulated or 6000 points reached                                                                                              |   D    | 01/12/23 | 01/13/23  |
|  x   | F06 | Roll random number of dice after the first turn                                                                                                       |   D    | 01/12/23 | 01/12/23  |
|  x   | F07 | End game once at least 6000 points is reached by each player                                                                                          |   D    | 01/12/23 | 01/12/23  |
|  x   | F08 | Run 42 simulations                                                                                                                                    |   D    | 01/12/23 | 01/12/23  |
|  Y   | F09 | Print each player's win percentage                                                                                                                    |   D    | 01/12/23 | 01/13/23  |
|  Y   | F10 | Store roll data in a hashmap in order to count how many of each dice player has                                                                       |   D    | 01/18/23 | 01/19/23  |
|  Y   | F11 | Compute sets based on how many of each dice player has                                                                                                |   D    | 01/19/23 | 01/19/23  |
|  Y   | F12 | Implement a combo strategy where a player doesn't reroll any dice part of a set (>=3)                                                                 |   D    | 01/19/23 | 01/19/23  |
|  Y   | F13 | Implement a combo strategy where a player doesn't reroll any diamond and gold dice                                                                    |   D    | 01/19/23 | 01/19/23  |
|  Y   | F14 | Implement 35 total cards, 6 of which are Sea Battle cards                                                                                             |   D    | 01/23/23 | 01/23/23  |
|  Y   | F15 | Player draws a card at start of each turn                                                                                                             |   D    | 01/23/23 | 01/23/23  |
|  Y   | F16 | Player earns bonus points if they get the minimum required sabers as written on their Sea Battle Card                                                 |   D    | 01/23/23 | 01/24/23  |
|  Y   | F17 | Create a Sea Battle strategy for combo strategy where user rerolls every dice that's not a saber until they have the sabers required for bonus points |   D    | 01/24/23 | 01/24/23  |
|  Y   | F18 | Implement Sea Battle Cards for random strategy, but no strategy to deal with this scenario.                                                           |   D    | 01/24/23 | 01/24/23  |
|  Y   | F19 | Add monkey cards to deck.                                                                                                                             |   D    | 01/25/23 | 01/26/23  |
|  Y   | F20 | Activate monkey mode if monkey card is drawn.                                                                                                         |   D    | 01/25/23 | 01/26/23  |
|  Y   | F21 | Implement monkey mode to both combo and random strategy where parrot and monkey are added together to form sets.                                      |   D    | 01/25/23 | 01/26/23  |
