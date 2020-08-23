package comp1110.ass2;

import static comp1110.ass2.PieceDirection.*;

/** This Class defines each Piece on
 *  (1) PieceType
 *  (2) PieceDirection
 *  (3) PieceCoordinates
 *
 *
 *
 */


public class Piece {

    private final PieceType type;
    private final PieceDirection dir;
    private final PieceCoordinates coords; // Jiwon 22/08


//     Constructor
    Piece (PieceType type, PieceCoordinates coords, PieceDirection dir) {
        this.type = type;
        this.coords = coords; // Jiwon 22/08
        this.dir = dir;
    }
    // Need a method that could tell what coordinates each piece occupies
    // Jiwon 23/08

    /**
     * This sets the piece dimension, especially on what space it occupies.
     */

    private Piece [][] piece = new Piece [this.getXDimensions()] [this.getYDimensions()];
//    public Piece toPiece(String placement) {
//        int pLength = placement.length();
//        PieceType pTy
//        if (pLength == 4) {
//            type.fromChar(placement.charAt(0));
//        }
//    }

    /**
     * Knowing the piece's direction, return the dimensions of the piece.
     * Knowing that whatever the Piece is, if the Direction is North or South, dimension will always be
     * Spine length.
     *
     * And if the direction is West or East, dimension will always be 2 by Spine length.
     *
     * @return An integer value that indicates the width of piece.
     */

    public int getXDimensions() {
        int xValue = 0;
        if (dir == EAST || dir == WEST)
            xValue = 2;
        else if (dir == NORTH || dir == SOUTH) {
            assert type != null;
            xValue = type.getSpineNum();
        }

        return xValue;
    }

    /**
     * Knowing the piece's direction, return the dimensions of the piece.
     * Knowing that whatever the Piece is, if the Direction is North or South, dimension will always be
     * Spine length.
     *
     * And if the direction is West or East, dimension will always be 2 by Spine length.
     *
     * @return An integer value that indicates the height of piece.
     */

    public int getYDimensions() {
        int yValue = 0;
        if (dir == NORTH || dir == SOUTH)
            yValue = 2;
        else if (dir == WEST || dir == EAST) {
            assert type != null;
            yValue = type.getSpineNum();
        }

        return yValue;
    }

    /** Given a string (two character) position, extract the value of X coordinate.
     *  Used charAt() to extract the relevant character and since it will be in ASCII code
     *
     *  returns integer value of x coordinate
     *
     *  Jiwon 22/08
     **/


    public static int getXCoordinate(String placement) {
        return placement.charAt(0) - 48;
    }

    /** Given a string (two character) position, extract the value of Y coordinate.
     *  Used charAt() to extract the relevant character and since it will be in ASCII code
     *
     *  Param: String type input
     *  Returns: Y coordinate of this Piece.
     *
     *  Jiwon 22/08
     **/

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
