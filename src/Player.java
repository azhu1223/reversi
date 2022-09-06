import java.util.Scanner;

public abstract class Player 
{
    public static int currentID = 0;

    private char m_color;
    private int m_id;
    private int m_score;
    private String m_type;
    private Scanner m_scanner;

    public Player(Scanner scanner, char color, String type)
    {
        m_color = color;
        m_id = currentID;
        m_score = 0;
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

    public int getScore() 
    {
        return m_score;
    }

    public String getType() 
    {
        return m_type;
    }

    public Scanner getScanner()
    {
        return m_scanner;
    }

    public abstract Coord takeTurn();
}
