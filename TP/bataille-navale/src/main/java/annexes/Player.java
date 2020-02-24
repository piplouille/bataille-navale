package annexes;

import java.io.Serializable;
import java.util.List;

import ensta.Cardinal;
import ensta.Board;
import ensta.ship.AbstractShip;
import ensta.PutShipException;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.get_name(), s.get_size());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            // TODO set ship orientation
            
            switch (res.orientation) {
                case "n" : s.set_orientation(Cardinal.n); break;
                case "e" : s.set_orientation(Cardinal.e); break;
                case "w" : s.set_orientation(Cardinal.w); break;
                case "s" : s.set_orientation(Cardinal.s); break;
            }

            // TODO put ship at given position
            int x = res.x;
            int y = res.y;

            try {
                board.putShip(s, x, y);
            }
            catch (PutShipException e) {
                System.out.println("case invalide");
                --i;
            }

            // TODO when ship placement successful
            ++i;
            done = i == ships.length;

            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done= false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            int size = this.opponentBoard.getSize();
            if (0<=hitInput.x && hitInput.x<size && 0<=hitInput.y && hitInput.y<size) {
                if (board.getHit(hitInput.x, hitInput.y) == null) {
                    hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
                    coords[0] = hitInput.x;
                    coords[1] = hitInput.y;
                    done = true;

                    if (hit == Hit.MISS) {
                        board.setHit(Boolean.FALSE, coords[0], coords[1]);
                    }
                    else {
                        board.setHit(Boolean.TRUE, coords[0], coords[1]);
                    }
                }
            }
            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
