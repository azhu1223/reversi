import java.util.Random;
import java.util.Scanner;

public class ReversiGame 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Reversi game = new Reversi();

        int difficulty = -1;
        Player player1 = null;
        Player player2;

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

                player1 = new Human(answer);
            }
            else 
            {
                System.out.println("Please input a valid response.");
            }
        }

        System.out.println("You chose the color " + player1.getColor());

        player2 = createOpponent(difficulty);

        boolean keepPlaying = true;

        while (keepPlaying) 
        {
            game.reset();
            keepPlaying = game.play();
        }

        System.out.println(0);
    }

    private static Player createOpponent(int difficulty) {
        return null;
    }
}
}
