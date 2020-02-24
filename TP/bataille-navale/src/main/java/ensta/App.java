package ensta;

import ensta.ship.BattleShip;
import ensta.ship.Carrier;
import ensta.ship.Destroyer;
import ensta.ship.Submarine;
import ensta.ship.AbstractShip;
import ensta.Cardinal;
import annexes.Player;
import annexes.Hit;
import annexes.BattleShipsAI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Hello world!
 *
 */
public class App {
    public static void main (String[] args) {
        // Initialisation de board
        Board board = new Board("Joueur 1", 10);
        board.print();
        int size = 5;

        // Initialisation liste de navires
        Destroyer bato0 = new Destroyer("destroyer", Cardinal.n);
        Carrier bato1 = new Carrier("carrier", Cardinal.n);
        Submarine subm0 = new Submarine("SUB0", Cardinal.n);
        Submarine subm1 = new Submarine("SUB1", Cardinal.n);
        Submarine subm2 = new Submarine("SUB2", Cardinal.n);
        
        AbstractShip ships[] = new AbstractShip[size];
        ships[0] = bato0;
        ships[1] = bato1;
        ships[2] = subm0;
        ships[3] = subm1;
        ships[4] = subm2;

        // Initialisation IA
        BattleShipsAI ai = new BattleShipsAI(board, board);
        ai.putShips(ships);
        board.print();
        // Compteur du nombre de bateaux détruits
        int no_bato = size;

        int coords[] = new int[2];
        Hit hit;
        while (no_bato != 0) {
            hit = ai.sendHit(coords);
            Board.print("Frappe envoyée à (" + coords[0] + ", " + coords[1] + "), " + hit.toString());
            if (hit.get_value() > 0) {
                no_bato --;
            }
            board.print();
            Board.print("\n\n\n\n");
            sleep(400);
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        


    public static void main_test( String[] args ) {
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
