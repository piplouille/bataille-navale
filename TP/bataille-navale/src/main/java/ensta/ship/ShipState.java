package ensta.ship;

import annexes.ColorUtil;
import ensta.ship.AbstractShip;

public class ShipState {
    private AbstractShip ship; // Référence vers le navire concerné par l'état
    private boolean struck; // True si navire touché en cet endroit

    public ShipState() {
        struck = false;
    }

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
        // Bateau touché ici
        return struck;
    }

    public String toString() {
        // retourne le label du navire associé : rouge si touché
        if (isStruck()) {
            return ColorUtil.colorize(ship.get_label(), ColorUtil.Color.RED);
            //return ship.get_label().toString();
        }
        else {
            return ship.get_label().toString();
        }
    }

    public boolean isSunk() {
        // Bateau est coulé
        return ship.isSunk();
    }

    public AbstractShip getShip() {
        return ship;
    }

    public void setShip(AbstractShip s) {
        ship = s;
    }
}