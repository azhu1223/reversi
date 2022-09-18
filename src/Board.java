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

        resetBoard();
    }

    public Board(Board b)
    {
        m_col = b.m_col;
        m_row = b.m_row;

        m_board = new char[m_row][m_col];

        for (int i = 0; i < m_row; i++) 
        {
            for (int j = 0; j < m_col; j++)
            {
                m_board[i][j] = b.m_board[i][j];
            }
        }

        m_whiteCells = b.m_whiteCells;
        m_blackCells = b.m_blackCells;
    }

    public Board(char[][] board)
    {
        m_col = board[0].length;
        m_row = board.length;

        m_board = board;

        m_whiteCells = 0;
        m_blackCells = 0;

        for (int i = 0; i < m_row; i++) 
        {
            for (int j = 0; j < m_col; j++)
            {
                if (board[i][j] == 'w')
                {
                    m_whiteCells++;
                }

                else if (board[i][j] == 'b')
                {
                    m_blackCells++;
                }
            }
        }
    }

    public Board(Board b, Coord c, char color)
    {
        this(b);

        setSquare(c, color);
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

    public void resetBoard()
    {
        for (int i = 0; i < m_row; i++)
            for (int j = 0; j < m_col; j++)
                m_board[i][j] = '.';

        m_board[3][3] = 'b';
        m_board[3][4] = 'w';
        m_board[4][4] = 'b';
        m_board[4][3] = 'w';

        m_whiteCells = 2;
        m_blackCells = 2;
    }

    public String toString()
    {
        String boardString = "";
        String horizontalBorder = "- ";

        for (int i = 0; i < m_col; i++)
        {
            horizontalBorder += i + " ";
        }

        horizontalBorder += '-';

        boardString += horizontalBorder + '\n';

        for (int i = 0; i < m_row; i++)
        {
            boardString += i + " ";

            for (int j = 0; j < m_col; j++)
            {
                boardString += m_board[i][j] + " ";
            }

            boardString += i + "\n";
        }

        boardString += horizontalBorder + '\n';

        return boardString;
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

    public char getCellContent(Coord c)
    {
        return m_board[c.getY()][c.getX()];
    }

    public boolean isValidCoord (Coord c)
    {
        int x = c.getX();
        int y= c.getY();

        return x >= 0 && x < m_col && y >= 0 && y < m_row;
    }
}
