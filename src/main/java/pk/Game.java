package pk;

import org.apache.logging.log4j.*;

public class Game {

    private static final Logger LOGGER = LogManager.getLogger(Game.class);

    Player player1;
    Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() {
        for (int i = 0; i < 42; i++) {
            while (player1.getTotalScore() < 6000 || player2.getTotalScore() < 6000) {
                player1.turn();
                player2.turn();
            }

            if (player1.getTotalScore() > player2.getTotalScore()) {
                player1.wonGame();
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                player2.wonGame();
            }

            // if (player1.gamesWon() > player2.gamesWon()) {
            // // System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + "
            // // games won!");
            // // System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + "
            // // games won!");
            // // System.out.println("Games tied: " + (42 - player1.gamesWon() -
            // // player2.gamesWon()));
            // } else if (player1.gamesWon() < player2.gamesWon()) {
            // // System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + "
            // // games won!");
            // // System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + "
            // // games won!");
            // // System.out.println("Games tied: " + (42 - player1.gamesWon() -
            // // player2.gamesWon()));
            // } else {
            // System.out.println("Tie.");
            // }

            player1.resetScore();
            player2.resetScore();
        }

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
        LOGGER.info("This is an INFO level log message");
        LOGGER.trace("HELLO");
        LOGGER.error("yo");
        System.out.println("That's all folks!");

    }
}
