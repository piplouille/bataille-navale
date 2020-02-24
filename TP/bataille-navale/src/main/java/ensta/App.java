package ensta;

import ensta.ship.Battleship;
import ensta.ship.Carrier;
import ensta.ship.Destroyer;
import ensta.ship.Submarine;
import ensta.ship.AbstractShip;
import ensta.Cardinal;
import annexes.Player;
import annexes.Hit;

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
        Board board = new Board("Joeur 1",10);
        System.out.println("test");

        Board opponentBoard = new Board("Joueur 2", 10);
        board.print();

        Destroyer bato = new Destroyer("destroyer", Cardinal.n);
        Carrier bato2 = new Carrier("carrier", Cardinal.n);

        // Player 1
        List<AbstractShip> ships = new ArrayList<AbstractShip>();
        ships.add(bato);
        ships.add(bato2);

        Player p = new Player(board, opponentBoard, ships);
        p.putShips();

        // Player 2
        List<AbstractShip> ships2 = new ArrayList<AbstractShip>();
        ships2.add(bato);
        ships2.add(bato2);

        Player p2 = new Player(opponentBoard, board, ships2);
        p2.putShips();

        // Player 1 envoie missile à player 2
        int coords[] = new int[2];
        Hit hit = p.sendHit(coords);
        System.out.println(hit.toString());
        board.print();

        // Player 1 envoie missile à player 2
        hit = p.sendHit(coords);
        System.out.println(hit.toString());
        board.print();

        // Hit hit = board.sendHit(5, 8);
        // hit = board.sendHit(6, 8);
        // board.print();
        // System.out.println(bato.isSunk());
        // System.out.println(hit.toString());
        

    }
}
