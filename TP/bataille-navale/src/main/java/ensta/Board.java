package ensta;

import ensta.ship.*;
import ensta.PutShipException;
import annexes.*;

public class Board implements IBoard {
    private String name;
    private ShipState boats[][]; // pour afficher ma grille avec shipstate
    private Boolean hits[][]; // vision du board de l'adversaire

    public Board(final String n_name, final int size) {
        name = n_name;
        boats = new ShipState[size][size];
        //hits = new Boolean[size][size];
        for(int i=0; i<size; i++)
            for(int j=0; j<size; j++)
                hits[i][j] = null;
    }

    public Board(final String n_name) {
        name = n_name;
        boats = new ShipState[10][10];
        hits = new Boolean[10][10];
    }

    public void print() {
        final Integer size = boats[0].length;
        final int number_size = size.toString().length() +1;
        final int line_size = number_size + size*2-1 +4;

        print(name + "\n");

        print("Navires :");
        // print les espaces
        //print("                  ");
        printSpace(line_size-8);
        print("Frappes :\n");
        /*
        Ici, uniquement avec size = 10
        print("  A B C D E F G H I J        A B C D E J G H I J\n");
        */

        //print nom des colonnes
        printSpace(number_size);
        for (int i=0; i<size; ++i) {
            print((char)('A'+i));
            print(" ");
        }
        printSpace(4 + number_size);
        for (int i=0; i<size; ++i) {
            print((char)('A'+i));
            print(" ");
        }
        print("\n");

        for (Integer i = 0; i < size ; i++) {
            final Integer line = i+1;
            print(line);
            printSpace(number_size - line.toString().length());
            for (int j = 0 ; j < size ; j++) {
                // On print les bateaux
                if (boats[i][j] == null) {
                    print(". ");
                }
                else {
                    print(boats[i][j] + " ");
                }
            }
            // on fait les espaces
            printSpace(4);

            print(line);
            printSpace(number_size - line.toString().length());
            for (int j = 0 ; j < size ; j++) {
                // on print les frappes
                if (hits[i][j] == null) {    
                    print(". ");
                }
                else if (hits[i][j] == Boolean.TRUE) {
                    print_color("X ");
                }
                else {
                    print("X ");
                }
                //print("· ");
            }
            print("\n");
        }
    }

    public static void print(final Object o) {
        // System.out.println(o);
        System.out.print(o);
    }

    public static void print_color(String o) {
        // System.out.println(o);
        System.out.print(ColorUtil.colorize(o, ColorUtil.Color.RED));
    }    

    public static void printSpace(final int n) {
        for (int i=0; i<n; ++i) {
            print(" ");
        }
    }

    public int getSize() {
        return boats[0].length;
    }

    public void putShip(final AbstractShip ship, final int x, final int y) throws PutShipException {
        // on met la case en x et y, puis selon orientation, on met la suite
        final int ship_size = ship.get_size();
        final int grid_size = boats[0].length;
        final Cardinal orientation = ship.get_orientation();
        int vertical = 0;
        int horizontal = 0;

        switch (orientation) {
            case n:
                vertical = 1;
                break;
            case s:
                vertical = -1;
                break;
            case e:
                horizontal = -1;
                break;
            case w:
                horizontal = 1;
                break;
        }

        //On vérifie que les cases sont libres et suffisamment grande pour le bateau à ajouter
        for (int i=0; i<ship_size; i++)
        {
            if ( x+vertical*i >= grid_size || x+vertical*i < 0 || y+horizontal*i >= grid_size || y+horizontal*i < 0) {
                throw new PutShipException();
            }
            else if (boats[x+vertical*i][y+horizontal*i] != null) {
                throw new PutShipException();
            }

        }
        
        for (int i=0; i<ship_size; i++) {
            boats[x+vertical*i][y+horizontal*i].setShip(ship);
        }
    }

    public boolean hasShip(final int x, final int y) {
        if (boats[x][y] != null) {
            return true;
        }
        return false;
    }

    public void setHit(Boolean hit, final int x, final int y) {
        hits[x][y] = hit;
    }

    public Boolean getHit(final int x, final int y) {
        return hits[x][y];
    }

    public Hit sendHit(int x, int y) {
        //ShipState target = boats[x][y];

        if(!boats[x][y].isStruck()) {
            if(boats[x][y].getShip() != null) {
                boats[x][y].addStrike();
                boats[x][y].getShip().addStrike();

                if(boats[x][y].isSunk()) {
                    return Hit.fromInt(boats[x][y].getShip().get_size());
                } 
                else {
                    return Hit.fromInt(-2);
                }
            }
        }
    
        return Hit.fromInt(-1);
    }
}
