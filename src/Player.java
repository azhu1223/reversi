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

    public abstract Coord takeTurn();
}
