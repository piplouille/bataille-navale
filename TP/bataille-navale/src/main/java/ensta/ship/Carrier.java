package ensta.ship;

public class Carrier extends AbstractShip {

    public Carrier(String n_name, Cardinal n_orientation) {
        super(n_name, 'C', 5, n_orientation);
    }
}