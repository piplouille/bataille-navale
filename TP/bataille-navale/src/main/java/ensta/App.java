package ensta;
import ensta.ship.Battleship;
import ensta.ship.Carrier;
import ensta.ship.Destroyer;
import ensta.ship.Submarine;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Board board = new Board("cc");
        board.print();
        Destroyer bato = new Destroyer("destroyer", Cardinal.NORTH);
    }
}
