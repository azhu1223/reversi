import java.util.ArrayList;
import java.util.Scanner;

public class Reversi
{
    Player m_player1;
    Player m_player2;
    Board m_board;
    
    public Reversi(Player p1, Player p2)
    {
        m_player1 = p1;
        m_player2 = p2;

        m_board = new Board(8, 8);
    }

    public void reset() 
    {
        m_board.resetBoard();
    }

    public boolean play(Scanner scanner) 
    {
        boolean player1HasMoves = playerHasValidMoves(m_player1);
        boolean player2HasMoves = playerHasValidMoves(m_player2);

        System.out.println(m_board);

        while (player1HasMoves || player2HasMoves)
        {
            if (player1HasMoves)
            {
                playerTurn(m_player1);
                System.out.println(m_board);
            }

            player2HasMoves = playerHasValidMoves(m_player2);

            if (player2HasMoves)
            {
                playerTurn(m_player2);
                System.out.println(m_board);
            }

            player1HasMoves = playerHasValidMoves(m_player1);
        }

        char keepPlaying = 'x';

        while (keepPlaying == 'x')
        {
            System.out.println("Would you like to replay? y = yes, n = no");

            String input = scanner.nextLine();
            char answer = Character.toLowerCase(input.charAt(0));

            if (answer == 'y' || answer == 'n')
            {
                keepPlaying = answer;
            }
            else
            {
                System.out.println("Invalid option. Please choose a valid option");
            }
        }

        return keepPlaying == 'y' ? true : false;
    }

    private ArrayList<Direction> checkAdjacentSquares(Player p, Coord c)
    {
        ArrayList<Direction> validAdjacentSquares = new ArrayList<Direction>();

        char colorToCheckFor = p.getColor() == 'w' ? 'b' : 'w';

        int x = c.getX();
        int y = c.getY();

        if (x >= m_board.getNumCol() || x < 0 || y >= m_board.getNumRow() || y < 0 )
        {
            return validAdjacentSquares;
        }

        //t = top, m = middle, b = bottom, l = left, r = right
        Direction tl = new Direction(-1, -1);
        Direction tm = new Direction(0, -1);
        Direction tr = new Direction(1, -1);
        Direction ml = new Direction(-1, 0);
        Direction mr = new Direction(1, 0);
        Direction bl = new Direction(-1, 1);
        Direction bm = new Direction(0, 1);
        Direction br = new Direction(1, 1);

        if (x < m_board.getNumCol() - 1)
        {
            if (m_board.getCellContent(new Coord(c, mr)) == colorToCheckFor)
                validAdjacentSquares.add(mr);

            if (y > 0 && m_board.getCellContent(new Coord(c, tr)) == colorToCheckFor)
                validAdjacentSquares.add(tr);

            if (y < m_board.getNumRow() - 1 && m_board.getCellContent(new Coord(c, br)) == colorToCheckFor)
                validAdjacentSquares.add(br);
        }

        if (x > 0)
        {
            if (m_board.getCellContent(new Coord(c, ml)) == colorToCheckFor)
                validAdjacentSquares.add(ml);
            
            if (y > 0 && m_board.getCellContent(new Coord(c, tl)) == colorToCheckFor)
                validAdjacentSquares.add(tl);

            if (y < m_board.getNumRow() - 1 && m_board.getCellContent(new Coord(c, bl)) == colorToCheckFor)
                validAdjacentSquares.add(bl);
        }

        if (y > 0 && m_board.getCellContent(new Coord(c, tm)) == colorToCheckFor)
            validAdjacentSquares.add(tm);

        if (y < m_board.getNumRow() - 1 && m_board.getCellContent(new Coord(c, bm)) == colorToCheckFor)
            validAdjacentSquares.add(bm);

        return validAdjacentSquares;
    }

    private boolean checkLineInDirection(Coord c, Direction d, char colorToBePlaced)
    {
        if (m_board.getCellContent(c) == '.')
        {
            return false;
        }

        if (m_board.getCellContent(c) == colorToBePlaced)
        {
            return true;
        }

        return checkLineInDirection(new Coord(c, d), d, colorToBePlaced);
    }

    private ArrayList<Direction> validDirections(Player p, Coord c)
    {
        ArrayList<Direction> directions = checkAdjacentSquares(p, c);
        ArrayList<Direction> validDirections = new ArrayList<Direction>();

        char color = p.getColor();
        int directionsSize = directions.size();

        for (int i = 0; i < directionsSize; i++)
        {
            Direction d = directions.get(i);

            if (checkLineInDirection(new Coord (c, d), d, color))
            {
                validDirections.add(d);
            }
        }

        return validDirections;
    }

    private void flipLine(Coord c, Direction d, char colorToFlipTo)
    {
        Coord nextCoord = new Coord(c, d);

        if (m_board.getCellContent(nextCoord) == colorToFlipTo)
        {
            return;
        }

        m_board.setSquare(nextCoord, colorToFlipTo);
        flipLine(nextCoord, d, colorToFlipTo);
    }

    private void placeColor(Player p, Coord c, ArrayList<Direction> directionsToPlaceIn)
    {
        int directionsSize = directionsToPlaceIn.size();
        char color = p.getColor();

        m_board.setSquare(c, color);

        for (int i = 0; i < directionsSize; i++)
        {
            flipLine(c, directionsToPlaceIn.get(i), color);
        }
    }

    private void playerTurn(Player p)
    {
        Coord coordToChange = null;
        ArrayList<Direction> directions = null;

        int playerID = p.getID();

        while (coordToChange == null)
        {
            System.out.println("Player " + playerID + "'s turn");

            Coord c = p.takeTurn();
            directions = validDirections(p, c);

            if (directions.isEmpty())
            {
                System.out.println("Please choose a possible coordinate.");
            }
            else
            {
                coordToChange = c;
            }
        }

        placeColor(p, coordToChange, directions);
    }

    private boolean playerHasValidMoves(Player p)
    {
        boolean validMoves = false;
        int rows = m_board.getNumRow();
        int cols = m_board.getNumRow();

        for (int i = 0; i < rows && !validMoves; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Coord c = new Coord(j, i);

                if (!validDirections(p, c).isEmpty())
                {
                    validMoves = true;
                }
            }
        }

        return validMoves;
    }
}
