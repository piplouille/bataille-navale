package ensta.ship;

import annexes.ColorUtil;
import ensta.AbstractShip;

public class ShipState {
    private AbstractShip ship; // Référence vers le navire concerné par l'état
    private boolean struck; // True si navire touché en cet endroit

    public void addStrike() {
        // Le navire est touché en cet endroit
        // vérifier que ya pas deja struck
        // verifier qu lest pas coulé isSunk();
        if (!struck) {
            ship.addStrike();
            struck = true;
        }
    }

    public boolean isStruck() {
        return struck;
    }

    public String toString() {
        // retourne le label du navire associé : rouge si touché
        if (isStruck()) {
            return ColorUtil.colorize(ship.get_label(), ColorUtil.Color.RED);
        }
        else {
            return ship.get_label();
        }
    }

    public boolean isSunk() {
        return ship.isSunk();
    }

    public AbstractShip getShip() {
        return ship;
    }
}