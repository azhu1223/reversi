public class Board 
{
    private char[][] m_board;
    private int m_col;
    private int m_row;
    private int m_whiteCells;
    private int m_blackCells;

    public Board(int xSize, int ySize)
    {
        m_col = xSize;
        m_row = ySize;

        m_board = new char[m_row][m_col];

        for (int i = 0; i < m_row; i++)
            for (int j = 0; j < m_col; j++)
                m_board[i][j] = '.';
    }

    public void setSquare(Coord coord, char color) 
    {
        int col = coord.getX();
        int row = coord.getY();

        if (col < 0 | col >= m_col | row < 0 | row >= m_row)
        {
            System.out.println("Error: setSquare Coord out of bounds");
            System.out.println("x: " + col + " y: " + row);

            return;
        }

        if (m_board[row][col] == 'w' && color == 'b')
        {
            m_whiteCells--;
            m_blackCells++;
        }

        else if (m_board[row][col] == 'b' && color == 'w')
        {
            m_whiteCells++;
            m_blackCells--;
        }

        else if (color == 'b')
        {
            m_blackCells++;
        }

        else
        {
            m_whiteCells++;
        }

        m_board[row][col] = color;
    }

    public int getNumCol()
    {
        return m_col;
    }

    public int getNumRow()
    {
        return m_row;
    }

    public int getNumWhiteCells()
    {
        return m_whiteCells;
    }

    public int getNumBlackCells()
    {
        return m_blackCells;
    }
}
