# A1 - Piraten Karpen

- Author: Mankaran Singh Rooprai
- Email: roopraim@mcmaster.ca

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

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

- Status:
  - Pending (P), Started (S), Blocked (B), Done (D)
- Definition of Done (DoD):
  - Feature works according to the feature requirements. Feature can be implemented whenever needed easily due to OOP principles. Feature can be easily maintained if a change needs to be made.

### Backlog

| MVP? | Id  | Feature                                                                                    | Status | Started  | Delivered |
| :--: | :-: | ------------------------------------------------------------------------------------------ | :----: | :------: | :-------: |
|  x   | F01 | Roll a dice                                                                                |   D    | 01/01/23 | 01/12/23  |
|  x   | F02 | Roll eight dices                                                                           |   D    | 01/12/23 | 01/13/23  |
|  x   | F03 | Compute score by counting the numbers of gold coins and diamonds and multiplying it by 100 |   D    | 01/11/23 | 01/13/23  |
|  x   | F04 | Implement second player                                                                    |   D    | 01/12/23 | 01/12/23  |
|  x   | F05 | Reroll until 3 skulls accumulated or 6000 points reached                                   |   D    | 01/12/23 | 01/13/23  |
|  x   | F06 | Roll random number of dice after the first turn                                            |   D    | 01/12/23 | 01/12/23  |
|  x   | F07 | End game once at least 6000 points is reached by each player                               |   D    | 01/12/23 | 01/12/23  |
|  x   | F08 | Run 42 simulations                                                                         |   D    | 01/12/23 | 01/12/23  |
| MVP  | F09 | Print each player's win percentage                                                         |   D    | 01/12/23 | 01/13/23  |
| ...  | F10 | Store roll data in a hashmap in order to count how many of each dice player has            |   D    | 01/18/23 | 01/19/23  |
| ...  | F11 | Compute sets based on how many of each dice player has                                     |   D    | 01/19/23 | 01/19/23  |
