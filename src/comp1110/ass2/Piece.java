package comp1110.ass2;

public class Piece {

//    private final Colour pieceColour;
//    private final Direction dir;
    private final PieceType type;
    private final PieceDirection dir;
    public int xCoords; // Jiwon 22/08
    public int yCoords; // Jiwon 22/08

    // Constructor
    Piece (PieceType type, String position,  PieceDirection dir) {
        this.type = type;
        this.dir = dir;
        this.xCoords = getXCoordinate(position); // Jiwon 22/08
        this.yCoords = getYCoordinate(position); // Jiwon 22/08

    }

    /* Given a string (two character) position, extract the value of X coordinate.
       Used charAt() to extract the relevant character and since it will be in ASCII code

       returns integer value of x coordinate

       // Jiwon 22/08
     */


    public static int getXCoordinate(String position) {
        return position.charAt(0) - 48;
    }

    /* Given a string (two character) position, extract the value of Y coordinate.
       Used charAt() to extract the relevant character and since it will be in ASCII code

       returns integer value of y coordinate

       // Jiwon 22/08
     */

    public static int getYCoordinate(String position) {
        return position.charAt(1) - 48;
    }

    // method 1 : private static boolean validPosition()
    // returns true/false whether the piece can fit the board

    // method 2 : private int getXCoordinate(String position)
    // returns the set of coordinate of the piece (left-topmost coordinate)

    // method 3 : private int getYCoordinate(String position)
    // returns the set of coordinate of the piece (left-topmost coordinate)

    // method 4 :

}
