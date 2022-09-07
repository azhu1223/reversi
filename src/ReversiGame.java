import java.util.Random;
import java.util.Scanner;

public class ReversiGame 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        int difficulty = askForDifficulty(scanner);
        Player player1;
        Player player2;

        if (difficulty > 3)
        {
            player1 = createOpponent(scanner, difficulty, 'w');
        }
        
        else
        {
            player1 = createHumanPlayer(scanner);
        }

        player2 = createOpponent(scanner, difficulty, player1.getColor() == 'w' ? 'b' : 'w');

        boolean keepPlaying = true;
        Reversi game = new Reversi(player1, player2);

        player1.setGame(game);
        player2.setGame(game);

        while (keepPlaying) 
        {
            game.reset();
            keepPlaying = game.play(scanner);
        }

        System.out.println("Player 1 won a total of " + player1.getGameWon() + " games with a total score of " + player1.getTotalScore() + ".");
        System.out.println("Player 2 won a total of " + player2.getGameWon() + " games with a total score of " + player2.getTotalScore() + ".");
        System.out.println("You have played " + game.getGamesPlayed() + " games in total.");

        System.out.println(0);
    }

    private static Player createOpponent(Scanner scanner, int difficulty, char color) 
    {
        Player p = null;

        if (difficulty == 0)
            p = new Human(scanner, color);

        switch(difficulty)
        {
            case 0:
                p = new Human(scanner, color);
                break;

            case 4:
            case 1:
                p = new EasyComputer(scanner, color);
                break;
            
            default:
                break;
        }
        
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

            if (input.length() == 1 && Character.isDigit(answer) && answerDigit < 5) {
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
