import pk.Dice;
import pk.Player;

public class PiratenKarpen {

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
                System.out.println("Player 1 won " + player1.gamesWon() + "games.");
                player1.wonGame();
            } else if (player1.getTotalScore() < player2.getTotalScore()) {
                System.out.println("Player 2 won " + player2.gamesWon() + "games.");
                player2.wonGame();
            }

            player1.resetScore();
            player2.resetScore();

            // while (player1.getTotalScore() >= 6000 && player2.getTotalScore() < 6000) {
            // // System.out.println("Player 2 rolls: ");
            // System.out.println();
            // System.out.println("PLAYER 2");
            // System.out.println();
            // player2.turn();
            // }

            // while (player1.getTotalScore() < 6000 && player2.getTotalScore() >= 6000) {
            // // System.out.println("Player 1 rolls: ");
            // System.out.println();
            // System.out.println("PLAYER 1");
            // System.out.println();
            // player1.turn();
            // }

            // if (player1.getTotalScore() > player2.getTotalScore()) {
            // player1.wonGame();
            // System.out.println("PLAYER 1 IS THE WINNER with " + player1.getTotalScore() +
            // " points!");
            // System.out.println("Player 2 had: " + player2.getTotalScore() + " points.");
            // // System.out.println("Player 1 had: " + player1.getNumTurns() + " turns.");
            // // System.out.println("Player 2 had: " + player2.getNumTurns() + " turns.");
            // } else if (player1.getTotalScore() < player2.getTotalScore()) {
            // player2.wonGame();
            // System.out.println("PLAYER 2 IS THE WINNER with " + player2.getTotalScore() +
            // " points!");
            // System.out.println("Player 1 had: " + player1.getTotalScore() + " points.");
            // // System.out.println("Player 1 had: " + player1.getNumTurns() + " turns.");
            // // System.out.println("Player 2 had: " + player2.getNumTurns() + " turns.");
            // } else {
            // System.out.println("Both players scored " + player1.getTotalScore() + "
            // points.");
            // System.out.println("Tie.");
            // }

            // gamesPlayed++;
        }

        if (player1.gamesWon() > player2.gamesWon()) {
            System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + "games won!");
            System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + "games won!");
        } else if (player1.gamesWon() < player2.gamesWon()) {
            System.out.println("PLAYER 2 IS THE WINNER with " + player2.gamesWon() + "games won!");
            System.out.println("PLAYER 1 IS THE WINNER with " + player1.gamesWon() + "games won!");
        } else {
            System.out.println("Tie.");
        }

        double winPercentage1 = ((Double.valueOf(player1.gamesWon()) / 42) * 100);
        double winPercentage2 = ((Double.valueOf(player2.gamesWon()) / 42) * 100);

        System.out.printf("Player 1 win percentage: %.3f", winPercentage1);
        System.out.print("%");
        System.out.println();
        System.out.printf("Player 2 win percentage: %.3f", winPercentage2);
        System.out.print("%");
        System.out.println();
        System.out.println("That's all folks!");

    }

}
