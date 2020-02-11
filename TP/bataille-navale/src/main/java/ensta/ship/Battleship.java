package ensta.ship;
import ensta.Cardinal;

public class Battleship extends AbstractShip {

    public Battleship(String n_name, Cardinal n_orientation) {
        super(n_name, 'B', 4, n_orientation);
    }
}