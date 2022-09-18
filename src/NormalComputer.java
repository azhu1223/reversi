import java.util.ArrayList;
import java.util.Scanner;

public class NormalComputer extends Player
{
    public NormalComputer(Scanner scanner, char c)
    {
        super(scanner, c, "normal");
    }

    @Override
    public Coord takeTurn()
    {
        Board gameBoard = getGame().getBoard();
        ArrayList<Coord> validMoves = Reversi.getValidMoves(gameBoard, this);

        Coord moveForHighestScoringBoard = validMoves.get(0);
        Board highestScoringBoard = applyMove(gameBoard, moveForHighestScoringBoard);

        for (Coord c : validMoves) 
        {
            Board b = applyMove(gameBoard, c);

            switch (getColor())
            {
                case 'w':
                    if (b.getNumWhiteCells() > highestScoringBoard.getNumWhiteCells())
                    {
                        moveForHighestScoringBoard = c;
                        highestScoringBoard = b;
                    }
                    break;

                case 'b':
                    if (b.getNumBlackCells() > highestScoringBoard.getNumBlackCells())
                        {
                            moveForHighestScoringBoard = c;
                            highestScoringBoard = b;
                        }
                    break;

                default:
                    break;
            }
        }

        return moveForHighestScoringBoard;
    }

    private Board applyMove(Board b, Coord c)
    {
        return new Board(b, c, getColor());
    }
}
