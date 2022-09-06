import java.util.Random;
import java.util.Scanner;

public class ReversiGame 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        Player player2;

        int difficulty = askForDifficulty(scanner);
        Player player1 = createHumanPlayer(scanner);

        System.out.println("You chose the color " + player1.getColor());

        player2 = createOpponent(scanner, difficulty, player1.getColor() == 'w' ? 'b' : 'w');

        boolean keepPlaying = true;
        Reversi game = new Reversi(player1, player2);

        while (keepPlaying) 
        {
            game.reset();
            keepPlaying = game.play(scanner);
        }

        System.out.println(0);
    }

    private static Player createOpponent(Scanner scanner, int difficulty, char color) 
    {
        Player p = null;

        if (difficulty == 0)
            p = new Human(scanner, color);
        
        return p;
    }

    private static int askForDifficulty(Scanner scanner) 
    {
        int difficulty = -1;

        while (difficulty < 0) 
        {
            System.out.println("What difficulty would you like to play? 0 = vs Human, 1 is the easiest and 3 is the hardest.");

            String input = scanner.nextLine();
            char answer = input.charAt(0);
            int answerDigit = answer - '0';

            if (input.length() == 1 && Character.isDigit(answer) && answerDigit < 4) {
                difficulty = answerDigit;
            }
            else 
            {
                System.out.println("Please input a valid response.");
            }
        }

        return difficulty;
    }

    private static Human createHumanPlayer(Scanner scanner)
    {
        Human player1 = null;

        while (player1 == null) 
        {
            System.out.println("Would Player 1 like to be white or black? w = white, b = black, r = random");

            String input = scanner.nextLine();
            char answer = input.charAt(0);

            if (input.length() == 1 && (answer == 'w' || answer == 'b' || answer == 'r')) 
            {
                if (answer == 'r')
                {
                    Random rand = new Random();
                    answer = rand.nextInt(2) == 0 ? 'w' : 'b';
                }

                player1 = new Human(scanner, answer);
            }
            else 
            {
                System.out.println("Please input a valid response.");
            }
        }

        return player1;
    }
}
