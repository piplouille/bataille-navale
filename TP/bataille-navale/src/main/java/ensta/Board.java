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

    public print() {
        int size = boats[0].size();
        // print première ligne avec "Navires"
        // print première ligne avec "Frappes"
        for (int i = 0; i < size ; i++) {
            // On print le numéro de ligne
            for (int j = 0 ; j < size ; j++) {
                // On print les bateaux
            }
            // on fait les espaces
            // On print numéro de ligne
            for (int j = 0 ; j < size ; j++) {
                // on print les frappes
            }
        }
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
