package annexes;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.Board;
import ensta.Cardinal;
import ensta.ship.AbstractShip;
import ensta.ship.BattleShip;
import ensta.ship.Carrier;
import ensta.ship.Destroyer;
import ensta.ship.Submarine;

public class Game {

    /*
     * *** Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /*
     * *** Attributs
     */
    private Player player1;
    private Player player2;
    private Scanner sin;

    /*
     * *** Constructeurs
     */
    public Game() {
    }

    public Game init() {
        if (!loadSave()) {
            // init attributes
            System.out.println("entre ton nom: ");

            // TODO use a scanner to read player name
            sin = new Scanner(System.in);
            String name = sin.nextLine();
            sin.close();

            System.out.print("\n");
            System.out.println("entre la taille du terrain : ");
            //On peut rajouter des exceptions pour gérer les entrées invalides. 
            sin = new Scanner(System.in);
            int size = sin.nextInt();
            sin.close();

            // TODO init boards
            Board b1, b2;
            b1 = new Board(name, size);
            b2 = new Board("AI", size);


            // TODO init this.player1 & this.player2

            //List<AbstractShip> ships = createDefaultShips();
            List<AbstractShip> ships = Arrays.asList(new AbstractShip[] { new Destroyer("bato", Cardinal.n) });
            this.player1 = new Player(b1, b2, ships);
            this.player2 = new AIPlayer(b2, b1, ships);

            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /*
     * *** Méthodes
     */
    public void run() {
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = Hit.MISS; // TODO player1 send a hit
            boolean strike = hit != Hit.MISS; // TODO set this hit on his board (b1)

            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();

            if (!done && !strike) {
                do {
                    hit = Hit.MISS; // TODO player2 send a hit.

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while (strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }

    private void save() {
        try {
            // TODO bonus 2 : uncomment
            if (!SAVE_FILE.exists()) {
            SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
            }

            // TODO bonus 2 : serialize players
            try {
                FileOutputStream f = new FileOutputStream(SAVE_FILE);
                ObjectOutputStream o = new ObjectOutputStream(f);

                o.writeObject(player1);
                o.writeObject(player2);

                o.close();
                f.close();
            }
            catch (FileNotFoundException e) {
                print("File not found");
            }
            catch (IOException e) {
                print("Error initializing stream");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players
                FileInputStream fi = new FileInputStream(SAVE_FILE);
                ObjectInputStream oi = new ObjectInputStream(fi);

                Player player1 = (Player) oi.readObject();
                Player player2 = (Player) oi.readObject();

                oi.close();
                fi.close();

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[] { player1, player2 }) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
        case MISS:
            msg = hit.toString();
            break;
        case STIKE:
            msg = hit.toString();
            color = ColorUtil.Color.RED;
            break;
        default:
            msg = hit.toString() + " coulé";
            color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[] { new Destroyer("bato", Cardinal.n), new Submarine("sous", Cardinal.n),
                new Submarine("marin", Cardinal.n), new BattleShip("Bato", Cardinal.n), new Carrier("Grand BATO", Cardinal.n) });
    }
}
