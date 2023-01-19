import pk.Dice;
import pk.Player;
import pk.Game;

public class PiratenKarpen {

    public static void main(String[] args) {
        System.out.println("Welcome to Piraten Karpen Simulator!");

        Dice dice1 = new Dice();
        Dice dice2 = new Dice();

        Player player1 = new Player(dice1);

        Player player2 = new Player(dice2);

        Game game = new Game(player1, player2);

        game.startGame();
    }

}
