public class ReversiGame 
{
    public static void main(String[] args) 
    {
        Reversi game = new Reversi();
        boolean keepPlaying = true;

        while (keepPlaying) {
            game.reset();
            keepPlaying = game.play();
        }

        System.out.println(0);
    }
}