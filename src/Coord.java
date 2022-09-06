public class Coord 
{
    private int m_x;
    private int m_y;

    public Coord(int x, int y)
    {
        m_x = x;
        m_y = y;
    }

    public Coord(Coord c, Direction d)
    {
        m_x = c.getX() + d.getH();
        m_y = c.getY() + d.getV();
    }

    public int getX()
    {
        return m_x;
    }

    public int getY()
    {
        return m_y;
    }
}
