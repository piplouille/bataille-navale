package ensta.ship;

public class Submarine extends AbstractShip {

    public Submarine(String n_name, Cardinal n_orientation) {
        super(n_name, 'S', 3, n_orientation);
    }
}
