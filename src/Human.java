import java.util.Scanner;

public class Human extends Player
{
    public Human(Scanner scanner, char color)
    {
        super(scanner, color, "human");
    }

    @Override
    public Coord takeTurn()
    {
        return new Coord(askXCoord(), askYCoord());
    }

    private int askXCoord()
    {
        int x = -1;

        while (x < 0)
        {
            System.out.println("What x coordinate would you like to place a disk on?");

            String input = getScanner().nextLine();
            int answer = stringToNumber(input);

            x = answer;

            if (answer < 0)
            {
                System.out.println("Please input a valid number.");
            }
        }

        return x;
    }

    private int askYCoord()
    {
        int y = -1;

        while (y < 0)
        {
            System.out.println("What y coordinate would you like to place a disk on?");

            String input = getScanner().nextLine();
            int answer = stringToNumber(input);

            y = answer;

            if (answer < 0)
            {
                System.out.println("Please input a valid number.");
            }
        }

        return y;
    }

    private int stringToNumber(String s)
    {
        int stringSize = s.length();

        int convertedNumber = 0;

        for (int i = stringSize - 1; i >= 0 && convertedNumber >= 0; i--)
        {
            convertedNumber *= 10;

            char c = s.charAt(i);

            if (Character.isDigit(c))
            {
                convertedNumber += c - '0';
            }
            else
            {
                convertedNumber = -1;
            }
        }

        return convertedNumber;
    }
}
