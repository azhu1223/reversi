public class Human extends Player
{
    public Human(int color, String type)
    {
        super(color, type);
    }

    @Override
    public Coord takeTurn()
    {
        return new Coord(0, 0);
    }
}
