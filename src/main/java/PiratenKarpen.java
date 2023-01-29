import pk.Dice;
import pk.Player;
import pk.Game;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        // store command line arguments and pass them to different classes
        String strategy1 = args[0];
        String strategy2 = args[1];
        String trace = "";
        if (args.length == 3) {
            trace = args[2];
        }

        Player player1 = new Player(dice1, strategy1, strategy2, trace);

        Player player2 = new Player(dice2, strategy1, strategy2, trace);

        Game game = new Game(player1, player2, strategy1, strategy2, trace);

        game.startGame();
    }

}
