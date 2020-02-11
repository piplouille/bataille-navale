package ensta.ship;
import ensta.Cardinal;

public class Destroyer extends AbstractShip {

    public Destroyer(String n_name, Cardinal n_orientation) {
        super(n_name, 'D', 2, n_orientation);
    }
}