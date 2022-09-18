import java.util.ArrayList;
import java.util.Scanner;

public class Reversi
{
    private Player m_player1;
    private Player m_player2;
    private Board m_board;

    private int m_gamesPlayed;
    
    public Reversi(Player p1, Player p2)
    {
        m_player1 = p1;
        m_player2 = p2;

        m_gamesPlayed = 0;

        m_board = new Board(8, 8);
    }

    public Reversi(Player p1, Player p2, Board b)
    {
        m_player1 = p1;
        m_player2 = p2;

        m_gamesPlayed = 0;

        m_board = b;
    }

    public int getGamesPlayed()
    {
        return m_gamesPlayed;
    }

    public void reset() 
    {
        m_board.resetBoard();
        m_gamesPlayed++;
    }

    public boolean play(Scanner scanner) 
    {
        boolean player1HasMoves = playerHasValidMoves(m_board, m_player1);
        boolean player2HasMoves = playerHasValidMoves(m_board, m_player2);

        System.out.println(m_board);

        while (player1HasMoves || player2HasMoves)
        {
            if (player1HasMoves)
            {
                playerTurn(m_board, m_player1);
                System.out.println(m_board);
            }

            player2HasMoves = playerHasValidMoves(m_board, m_player2);

            if (player2HasMoves)
            {
                playerTurn(m_board, m_player2);
                System.out.println(m_board);
            }

            player1HasMoves = playerHasValidMoves(m_board, m_player1);
        }

        Player winner = findWinner();

        if (winner == null)
        {
            System.out.println("The game has resulted in a draw.");
        }

        else
        {
            System.out.println("Congratulations! Player " + winner.getID() + " won this game.");
        }

        char keepPlaying = 'x';

        while (keepPlaying == 'x')
        {
            System.out.println("Would you like to replay? y = yes, n = no");

            String input = scanner.nextLine();

            if (!input.isEmpty())
            {
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

            else
            {
                System.out.println("Invalid option. Please choose a valid option");
            }
        }

        return keepPlaying == 'y' ? true : false;
    }

    public static ArrayList<Coord> getValidMoves(Board b, Player p)
    {
        ArrayList<Coord> validMoves = new ArrayList<>();

        int rows = b.getNumRow();
        int cols = b.getNumCol();

        char color = p.getColor();

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Coord c = new Coord(j, i);
                ArrayList<Direction> directions = validDirections(b, p, c);

                int directionSize = directions.size();

                for (int k = 0; k < directionSize; k++)
                {
                    Direction d = directions.get(k);
                    if (checkLineInDirection(b, new Coord(c, d), d, color))
                    {
                        validMoves.add(c);
                        continue;
                    }
                }
            }
        }

        return validMoves;
    }

    public Board getBoard()
    {
        return m_board;
    }

    private static ArrayList<Direction> checkAdjacentSquares(Board b, Player p, Coord c)
    {
        ArrayList<Direction> validAdjacentSquares = new ArrayList<Direction>();

        if (!b.isValidCoord(c) || b.getCellContent(c) != '.')
        {
            return validAdjacentSquares;
        }

        char colorToCheckFor = p.getColor() == 'w' ? 'b' : 'w';

        int x = c.getX();
        int y = c.getY();

        //t = top, m = middle, b = bottom, l = left, r = right
        Direction tl = new Direction(-1, -1);
        Direction tm = new Direction(0, -1);
        Direction tr = new Direction(1, -1);
        Direction ml = new Direction(-1, 0);
        Direction mr = new Direction(1, 0);
        Direction bl = new Direction(-1, 1);
        Direction bm = new Direction(0, 1);
        Direction br = new Direction(1, 1);

        if (x < b.getNumCol() - 1)
        {
            if (b.getCellContent(new Coord(c, mr)) == colorToCheckFor)
                validAdjacentSquares.add(mr);

            if (y > 0 && b.getCellContent(new Coord(c, tr)) == colorToCheckFor)
                validAdjacentSquares.add(tr);

            if (y < b.getNumRow() - 1 && b.getCellContent(new Coord(c, br)) == colorToCheckFor)
                validAdjacentSquares.add(br);
        }

        if (x > 0)
        {
            if (b.getCellContent(new Coord(c, ml)) == colorToCheckFor)
                validAdjacentSquares.add(ml);
            
            if (y > 0 && b.getCellContent(new Coord(c, tl)) == colorToCheckFor)
                validAdjacentSquares.add(tl);

            if (y < b.getNumRow() - 1 && b.getCellContent(new Coord(c, bl)) == colorToCheckFor)
                validAdjacentSquares.add(bl);
        }

        if (y > 0 && b.getCellContent(new Coord(c, tm)) == colorToCheckFor)
            validAdjacentSquares.add(tm);

        if (y < b.getNumRow() - 1 && b.getCellContent(new Coord(c, bm)) == colorToCheckFor)
            validAdjacentSquares.add(bm);

        return validAdjacentSquares;
    }

    private static boolean checkLineInDirection(Board b, Coord c, Direction d, char colorToBePlaced)
    {
        if (!b.isValidCoord(c) || b.getCellContent(c) == '.')
        {
            return false;
        }

        if (b.getCellContent(c) == colorToBePlaced)
        {
            return true;
        }

        return checkLineInDirection(b, new Coord(c, d), d, colorToBePlaced);
    }

    private static ArrayList<Direction> validDirections(Board b, Player p, Coord c)
    {
        ArrayList<Direction> directions = checkAdjacentSquares(b, p, c);
        ArrayList<Direction> validDirections = new ArrayList<Direction>();

        char color = p.getColor();
        int directionsSize = directions.size();

        for (int i = 0; i < directionsSize; i++)
        {
            Direction d = directions.get(i);

            if (checkLineInDirection(b, new Coord (c, d), d, color))
            {
                validDirections.add(d);
            }
        }

        return validDirections;
    }

    private void flipLine(Board b, Coord c, Direction d, char colorToFlipTo)
    {
        Coord nextCoord = new Coord(c, d);

        if (b.getCellContent(nextCoord) == colorToFlipTo)
        {
            return;
        }

        b.setSquare(nextCoord, colorToFlipTo);
        flipLine(b, nextCoord, d, colorToFlipTo);
    }

    private void placeColor(Board b, Player p, Coord c, ArrayList<Direction> directionsToPlaceIn)
    {
        int directionsSize = directionsToPlaceIn.size();
        char color = p.getColor();

        b.setSquare(c, color);

        for (int i = 0; i < directionsSize; i++)
        {
            flipLine(b, c, directionsToPlaceIn.get(i), color);
        }
    }

    private void playerTurn(Board b, Player p)
    {
        Coord coordToChange = null;
        ArrayList<Direction> directions = null;

        int playerID = p.getID();

        while (coordToChange == null)
        {
            System.out.println("Player " + playerID + "'s turn");

            Coord c = p.takeTurn();
            directions = validDirections(b, p, c);

            if (directions.isEmpty())
            {
                System.out.println("Please choose a possible coordinate.");
            }
            else
            {
                coordToChange = c;
            }
        }

        placeColor(b, p, coordToChange, directions);
    }

    private boolean playerHasValidMoves(Board b, Player p)
    {
        boolean validMoves = false;
        int rows = b.getNumRow();
        int cols = b.getNumRow();

        for (int i = 0; i < rows && !validMoves; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Coord c = new Coord(j, i);

                if (!validDirections(b, p, c).isEmpty())
                {
                    validMoves = true;
                }
            }
        }

        return validMoves;
    }

    private Player findWinner()
    {
        Player winner;
        char winningColor;
        int numWinningColor;
        int numLosingColor;

        int numWhite = m_board.getNumWhiteCells();
        int numBlack = m_board.getNumBlackCells();

        if (numWhite > numBlack)
        {
            winningColor = 'w';

            numWinningColor = numWhite;
            numLosingColor = numBlack;
        }

        else
        {
            winningColor = 'b';

            numWinningColor = numBlack;
            numLosingColor = numWhite;
        }

        if (m_player1.getColor() == winningColor)
        {
            winner = m_player1;
            m_player1.incrementGamesWon();

            m_player1.addToTotalScore(numWinningColor);
            m_player2.addToTotalScore(numLosingColor);
        }

        else if (numWhite != numBlack)
        {
            winner = m_player2;
            m_player2.incrementGamesWon();

            m_player1.addToTotalScore(numLosingColor);
            m_player2.addToTotalScore(numWinningColor);
        }

        else
        {
            winner = null;

            m_player1.addToTotalScore(numWhite);
            m_player2.addToTotalScore(numWhite);
        }

        return winner;
    }
}
