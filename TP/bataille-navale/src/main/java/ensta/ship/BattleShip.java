package ensta.ship;
import ensta.Cardinal;

public class BattleShip extends AbstractShip {

    public BattleShip(String n_name, Cardinal n_orientation) {
        super(n_name, 'B', 4, n_orientation);
    }
}