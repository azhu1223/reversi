import java.util.ArrayList;
import java.util.Scanner;

public class HardComputer extends Player
{
    public HardComputer(Scanner scanner, char color)
    {
        super(scanner, color, "hard");
    }

    @Override
    public Coord takeTurn()
    {
        Node initialNode = new Node(getGame().getBoard(), getColor());

        Node bestMove = findBestMove(initialNode, 5, true, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return bestMove.getFirstMove();
    }

    private Node findBestMove(Node n, int maxDepth, boolean maxPlayer, int alpha, int beta)
    {
        if (maxDepth == 0)
        {
            return n;
        }

        int a = alpha;
        int b = beta;

        Board board = n.getBoard();
        ArrayList<Coord> validMoves = Reversi.getValidMoves(board, this);
        int numValidMoves = validMoves.size();

        if (numValidMoves == 1)
        {
            return new Node(validMoves.get(0), 0);
        }

        Coord firstMove = n.getFirstMove();

        if (numValidMoves == 0)
        {
            if (maxPlayer)
            {
                return new Node(firstMove, 1);
            }

            else
            {
                return new Node(firstMove, 0);
            }
        }

        if (maxPlayer)
        {
            Node maxValueNode = new Node(1);

            for (Coord validMove : validMoves)
            {
                Node testingNode = firstMove == null ? new Node(n.getBoard(), validMove, getColor()) : new Node(n, firstMove);
                
                Node currentNode = findBestMove(testingNode, maxDepth - 1, false, a, b);
                int maxValue = maxValueNode.getValue();
                int currentNodeValue = currentNode.getValue();

                maxValueNode = Math.max(maxValue, currentNodeValue) == maxValue ? maxValueNode : currentNode;
                a = Math.max(a, maxValue);

                if (b <= a)
                {
                    break;
                }
            }

            if (maxValueNode.getValue() == Integer.MIN_VALUE)
            {
                return new Node(n, validMoves.get(0));
            }

            return maxValueNode;
        }

        else
        {
            Node minValueNode = new Node(0);

            for (Coord validMove : validMoves)
            {
                Node testingNode = firstMove == null ? new Node(n.getBoard(), validMove, getColor()) : new Node(n, firstMove);
                
                Node currentNode = findBestMove(testingNode, maxDepth - 1, true, a, b);
                int minValue = minValueNode.getValue();
                int currentNodeValue = currentNode.getValue();

                minValueNode = Math.min(minValue, currentNodeValue) == minValue ? minValueNode : currentNode;
                b = Math.max(b, minValue);

                if (b <= a)
                {
                    break;
                }
            }

            if (minValueNode.getValue() == Integer.MAX_VALUE)
            {
                return new Node(n, validMoves.get(0));
            }

            return minValueNode;
        }
    }
}
