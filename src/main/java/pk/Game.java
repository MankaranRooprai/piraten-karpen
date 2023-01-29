package pk;

import org.apache.logging.log4j.*;

public class Game {

    private static final Logger LOGGER = LogManager.getLogger(Game.class);

    Player player1;
    Player player2;

    String strategy1 = "";
    String strategy2 = "";
    String trace = "";

    // initalize players
    public Game(Player player1, Player player2, String strategy1, String strategy2, String trace) {
        this.player1 = player1;
        this.player2 = player2;
        this.strategy1 = strategy1;
        this.strategy2 = strategy2;
        this.trace = trace;
    }

    public void startGame() {

        if (trace.equalsIgnoreCase("trace"))
            LOGGER.info("STARTING GAME");

        // run for 42 games
        for (int i = 0; i < 42; i++) {

            // play the game while both player's have less than 6000 points
            while (player1.getTotalScore() < 6000 && player2.getTotalScore() < 6000) {

                if (trace.equalsIgnoreCase("trace")) {
                    LOGGER.info("PLAYER 1's TURN");
                    LOGGER.info("PLAYER 1 IS " + strategy1);
                }

                // select player 1's strategy based off argument 1
                if (strategy1.equals("random")) {
                    player1.randomRerollStrategy();
                } else if (strategy1.equals("combo")) {
                    player1.comboStrategy();
                }

                if (trace.equalsIgnoreCase("trace")) {
                    LOGGER.info("PLAYER 2's TURN");
                    LOGGER.info("PLAYER 2 IS " + strategy2);
                }

                // select player 2's strategy based off argument 2
                if (strategy2.equals("random")) {
                    player2.randomRerollStrategy();
                } else if (strategy2.equals("combo")) {
                    player2.comboStrategy();
                }

            }

            if (trace.equalsIgnoreCase("trace")) {
                LOGGER.info("PLAYER 1 WITH " + player1.getTotalScore() + " POINTS");
                LOGGER.info("PLAYER 2 WITH " + player2.getTotalScore() + " POINTS");
            }

            // check which player won the game depending on their total scores
            if (player1.getTotalScore() > player2.getTotalScore()) {
                if (trace.equalsIgnoreCase("trace"))
                    LOGGER.info("PLAYER 1 WON THE GAME");
                player1.wonGame();
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                if (trace.equalsIgnoreCase("trace"))
                    LOGGER.info("PLAYER 2 WON THE GAME");
                player2.wonGame();
            }

            // reset the score at the end of one game
            player1.resetScore();
            player2.resetScore();
        }

        // calculate the win percentage
        double winPercentage1 = ((Double.valueOf(player1.gamesWon()) / 42) * 100);
        double winPercentage2 = ((Double.valueOf(player2.gamesWon()) / 42) * 100);
        double tiePercentage = ((Double.valueOf((42 - player1.gamesWon() - player2.gamesWon())) / 42) * 100);

        System.out.printf("Player 1 win percentage: %.3f", winPercentage1);
        System.out.print("%");
        System.out.println();
        System.out.printf("Player 2 win percentage: %.3f", winPercentage2);
        System.out.print("%");
        System.out.println();
        System.out.printf("Tie percentage: %.3f", tiePercentage);
        System.out.print("%");
        System.out.println();
        System.out.println("That's all folks!");

    }
}
