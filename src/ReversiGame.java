import java.util.Random;
import java.util.Scanner;

public class ReversiGame 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        Settings settings = parseCommandLine(args);

        int difficulty = settings.getDifficulty();
        if (difficulty < 0)
        {
            difficulty = askForDifficulty(scanner);
            settings.setDifficulty(difficulty);
        }

        char player1Color = settings.getColor();
        if (player1Color == 'x')
        {
            player1Color = askForColor(scanner);
            
            if (player1Color == 'r')
            {
                Random rand = new Random();

                switch (rand.nextInt(2))
                {
                    case 0:
                        player1Color = 'w';
                        break;

                    case 1:
                        player1Color = 'b';
                        break;
                    
                    default:
                        break;
                }
            }

            settings.setColor(player1Color);
        }

        Player player1;
        Player player2;

        if (difficulty > 3)
        {
            player1 = createPlayer(scanner, player1Color, 1);
        }
        
        else
        {
            player1 = createPlayer(scanner, player1Color, 0);
        }

        player2 = createPlayer(scanner, player1.getColor() == 'w' ? 'b' : 'w', difficulty);

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

    private static char askForColor(Scanner scanner)
    {
        char color = 'x';

        while (color == 'x') 
        {
            System.out.println("Would Player 1 like to be white or black? w = white, b = black, r = random");

            String input = scanner.nextLine();
            char answer = input.charAt(0);

            if (input.length() == 1 && (answer == 'w' || answer == 'b' || answer == 'r')) 
            {
                color = answer;
            }
            else 
            {
                System.out.println("Please input a valid response.");
            }
        }

        return color;
    }

    private static Player createPlayer(Scanner scanner, char color, int type)
    {
        Player p = null;

        switch (type)
        {
            case 0:
                p = new Human(scanner, color);
                break;

            case 1:
                p = new EasyComputer(scanner, color);
                break;
            
            default:
                break;
        }

        return p;
    }

    private static Settings parseCommandLine(String[] args)
    {
        int difficulty = -1;
        char color = 'x';

        int numArgs = args.length;

        for (int i = 0; i < numArgs - 1; i++)
        {
            String label = args[i];
            String arg = args[i + 1];
            switch(label)
            {
                case "-d":
                    char digit = arg.charAt(0);
                    if (Character.isDigit(digit))
                        difficulty = digit - '0';
                    break;
                
                case "-c":
                    char c = arg.charAt(0);
                    if (c == 'w' | c == 'b' | c == 'r')
                        color = c;
                    break;

                default:
                    break;
            }
        }

        return new Settings(difficulty, color);
    }
}
