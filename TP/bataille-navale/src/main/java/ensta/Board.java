package ensta;

public class Board {
    private String name;
    private Character boats[][];
    private boolean hits[][];

    public Board(String n_name, int size) {
        name = n_name;
        boats = new Character[size][size];
        hits = new boolean[size][size];
    }

    public Board(String n_name) {
        name = n_name;
        boats = new Character[10][10];
        hits = new boolean[10][10];
    }

    public void print() {
        int size = boats[0].length;
        print("Navires :");
        // print les espaces
        print("                  ");
        print("Frappes :\n");
        print("  A B C D E F G H I J        A B C D E J G H I J\n");
        for (int i = 0; i < size ; i++) {
            print(i);
            print(" ");
            for (int j = 0 ; j < size ; j++) {
                // On print les bateaux
                print("· ");
            }
            // on fait les espaces
            print("     ");
            print(i);
            print(" ");
            for (int j = 0 ; j < size ; j++) {
                // on print les frappes
                // switch (hits[i][j]) {
                //     case true: print(".") ; break;
                //     default: print("x") ; break;
                // }
                print("· ");
            }
            print("\n");
        }
    }

    public static void print(Object o) {
        // System.out.println(o);
        System.out.print(o);
    }
}
