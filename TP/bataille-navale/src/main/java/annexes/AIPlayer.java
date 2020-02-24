package annexes;

import java.io.Serializable;
import java.util.List;

import ensta.Board;
import ensta.ship.AbstractShip;;

public class AIPlayer extends Player {
    /*
     * ** Attribut
     */
    private BattleShipsAI ai;

    /*
     * ** Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

    public void putShips(AbstractShip ships[]) {
        ai.putShips(ships);
    }


    
}
