package comp1110.ass2;

import static comp1110.ass2.PieceType.*;

public class Piece {

    private final PieceType type;
    private final PieceDirection dir;
    private final PieceCoordinates coords; // Jiwon 22/08


    // Constructor
    Piece (PieceType type, PieceCoordinates coords, PieceDirection dir) {
        this.type = type;
        this.coords = coords; // Jiwon 22/08
        this.dir = dir;
    }

//    public Piece toPiece(String placement) {
//        int pLength = placement.length();
//        PieceType pTy
//        if (pLength == 4) {
//            type.fromChar(placement.charAt(0));
//        }
//    }

    /* Given a string (two character) position, extract the value of X coordinate.
       Used charAt() to extract the relevant character and since it will be in ASCII code

       returns integer value of x coordinate

       // Jiwon 22/08
     */


    public static int getXCoordinate(String placement) {
        return placement.charAt(0) - 48;
    }

    /* Given a string (two character) position, extract the value of Y coordinate.
       Used charAt() to extract the relevant character and since it will be in ASCII code

       returns integer value of y coordinate

       // Jiwon 22/08
     */

    public static int getYCoordinate(String placement) {
        return placement.charAt(1) - 48;
    }

    // method 1 : private static boolean validPosition()
    // returns true/false whether the piece can fit the board

    // method 2 : private int getXCoordinate(String position)
    // returns the set of coordinate of the piece (left-topmost coordinate)

    // method 3 : private int getYCoordinate(String position)
    // returns the set of coordinate of the piece (left-topmost coordinate)

    // method 4 : where the represented coordinates are
    // Jiwon 22/08

}
