import pk.Dice;
import pk.Player;
import org.apache.logging.log4j.*;

public class PiratenKarpen {

    private static final Logger LOGGER = LogManager.getLogger(PiratenKarpen.class);

    int totalTurns = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        int gamesPlayed = 1;

        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        Player player1 = new Player(dice1);
        Player player2 = new Player(dice2);

        for (int i = 0; i < 42; i++) {
            // System.out.println(gamesPlayed);

            while (player1.getTotalScore() < 6000 || player2.getTotalScore() < 6000) {
                // System.out.println("Player 1 rolls: ");

                System.out.println();
                System.out.println("PLAYER 1 SCORE: " + player1.getTotalScore());
                System.out.println("PLAYER 1");
                System.out.println();
                player1.turn();
                // System.out.println("Player 2 rolls: ");
                // System.out.println("PLAYER 2");
                System.out.println();
                System.out.println("PLAYER 2 SCORE: " + player2.getTotalScore());
                System.out.println("PLAYER 2");
                System.out.println();
                player2.turn();
            }

            if (player1.getTotalScore() > player2.getTotalScore()) {
                System.out.println("Player 1 won " + player1.gamesWon() + " games.");
                player1.wonGame();
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                System.out.println("Player 2 won " + player2.gamesWon() + " games.");
                player2.wonGame();
            }

            player1.resetScore();
            player2.resetScore();
        }

        if (player1.gamesWon() > player2.gamesWon()) {
            System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + " games won!");
            System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + " games won!");
            System.out.println("Games tied: " + (42 - player1.gamesWon() - player2.gamesWon()));
        } else if (player1.gamesWon() < player2.gamesWon()) {
            System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + " games won!");
            System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + " games won!");
            System.out.println("Games tied: " + (42 - player1.gamesWon() - player2.gamesWon()));
        } else {
            System.out.println("Tie.");
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
