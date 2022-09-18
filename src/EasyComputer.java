import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EasyComputer extends Player
{
    public EasyComputer(Scanner scanner, char color)
    {
        super(scanner, color, "easy");
    }

    @Override
    public Coord takeTurn()
    {
        Random rand = new Random();

        ArrayList<Coord> possibleMoves = Reversi.getValidMoves(getGame().getBoard(), this);

        return possibleMoves.get(rand.nextInt(possibleMoves.size()));
    }
}