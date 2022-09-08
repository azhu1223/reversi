public class Settings 
{
    private int m_difficulty;
    private char m_color;

    public Settings(int d, char c)
    {
        m_difficulty = d;
        m_color = c;
    }

    public int getDifficulty()
    {
        return m_difficulty;
    }

    public char getColor()
    {
        return m_color;
    }

    public void setDifficulty(int d)
    {
        m_difficulty = d;
    }

    public void setColor(char c)
    {
        m_color = c;
    }
}
