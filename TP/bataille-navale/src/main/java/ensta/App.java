package ensta;

import ensta.ship.Battleship;
import ensta.ship.Carrier;
import ensta.ship.Destroyer;
import ensta.ship.Submarine;
import ensta.ship.AbstractShip;
import ensta.Cardinal;
import annexes.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Board board = new Board("cc",10);
        Board opponentBoard = new Board("cc", 10);
        board.print();

        Destroyer bato = new Destroyer("destroyer", Cardinal.n);
        Carrier bato2 = new Carrier("carrier", Cardinal.n);

        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(bato);
        ships.add(bato2);

        Player p = new Player(board, opponentBoard, ships);
        p.putShips();
    }
}
