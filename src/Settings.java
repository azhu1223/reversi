public class Settings 
{
    private int m_difficulty;
    private int m_player1;
    private char m_color;

    public Settings(int d, int p1, char c)
    {
        m_difficulty = d;
        m_color = c;
        m_player1 = p1;
    }

    public int getDifficulty()
    {
        return m_difficulty;
    }

    public int getPlayer1()
    {
        return m_player1;
    }

    public char getColor()
    {
        return m_color;
    }

    public void setDifficulty(int d)
    {
        m_difficulty = d;
    }

    public void setPlayer1(int p1)
    {
        m_player1 = p1;
    }

    public void setColor(char c)
    {
        m_color = c;
    }
}
