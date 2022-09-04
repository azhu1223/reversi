public abstract class Player 
{
    public static int currentID = 0;

    private int m_color;
    private int m_id;
    private int m_score;
    private String m_type;

    public Player(int color, String type)
    {
        m_color = color;
        m_id = currentID;
        m_score = 0;
        m_type = type;

        currentID++;
    }

    public int getColor() 
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

    public abstract Coord takeTurn();
}
