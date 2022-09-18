import java.util.Scanner;

public abstract class Player 
{
    public static int currentID = 1;

    private char m_color;
    private int m_id;
    private int m_totalScore;
    private int m_gamesWon;
    private String m_type;
    private Scanner m_scanner;
    private Reversi m_game;

    public class Node 
    {
        private Board m_b;
        private Coord m_firstMove;
        private int m_value;
        private char m_lastColor;

        public Node(Board b, char color)
        {
            m_b = b;
            m_firstMove = null;
            m_lastColor = color == 'w' ? 'b' : 'w';
        }

        public Node(Board b, Coord firstMove, char color)
        {
            m_b = new Board(b, firstMove, color);
            m_firstMove = firstMove;
            m_lastColor = color;

            m_value = color == 'w' ? m_b.getNumWhiteCells() : m_b.getNumBlackCells();
        }

        public Node(Node n, Coord c)
        {
            m_lastColor = n.getNextColor();
            m_b = new Board(n.m_b, c, m_lastColor);
            m_firstMove = n.m_firstMove;

            m_value = m_lastColor == 'w' ? m_b.getNumWhiteCells() : m_b.getNumBlackCells();
        }

        public Node(int type)
        {
            m_value = type == 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        public Node(Coord firstMove, int type)
        {
            this(type);
            m_firstMove = firstMove;
        }

        public Board getBoard()
        {
            return m_b;
        }

        public Coord getFirstMove()
        {
            return m_firstMove;
        }

        public int getValue()
        {
            return m_value;
        }

        public char getNextColor()
        {
            return m_lastColor == 'w' ? 'b' : 'w';
        }
    }

    public Player(Scanner scanner, char color, String type)
    {
        m_color = color;
        m_id = currentID;
        m_totalScore = 0;
        m_gamesWon = 0;
        m_type = type;
        m_scanner = scanner;

        currentID++;
    }

    public char getColor() 
    {
        return m_color;
    }

    public int getID() 
    {
        return m_id;
    }

    public String getType() 
    {
        return m_type;
    }

    public Scanner getScanner()
    {
        return m_scanner;
    }

    public Reversi getGame()
    {
        return m_game;
    }

    public int getTotalScore()
    {
        return m_totalScore;
    }

    public int getGameWon()
    {
        return m_gamesWon;
    }

    public void addToTotalScore(int score)
    {
        m_totalScore += score;
    }

    public void incrementGamesWon()
    {
        m_gamesWon++;
    }

    public void setGame(Reversi game)
    {
        m_game = game;
    }

    public abstract Coord takeTurn();
}
