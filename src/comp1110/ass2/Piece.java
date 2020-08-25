package comp1110.ass2;


import java.util.Arrays;

import static comp1110.ass2.PieceDirection.*;
import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.PieceCoordinates.*;


/** This Class defines each Piece on
 *  (1) PieceType
 *  (2) PieceDirection
 *  (3) PieceCoordinates
 *
 *
 *
 */


public class Piece {

    public final PieceType type;
    public final PieceDirection dir;
    public final PieceCoordinates coords; // Jiwon 22/08

    Piece (PieceType type, PieceCoordinates coords, PieceDirection dir) {
        this.type = type;
        this.coords = coords; // Jiwon 22/08
        this.dir = dir;
    }

    /**
     * This sets the piece dimension, especially on what space it occupies.
     */

    public Piece [][] piece = new Piece [this.getXDimensions()] [this.getYDimensions()];


    public static Piece toPiece(String placement) {
        PieceType type = PieceType.fromChar(placement);
        PieceCoordinates coords = getPCoord(placement);
        PieceDirection dir = PieceDirection.getDirection(placement);
        return new Piece(type, coords, dir);

    }

    public static void main(String[] args) {
        String str = "b23NB45N";
        String str2 = "b43N";

        System.out.println(Arrays.deepToString(toPiece(str2).getCoords()));
    }

    public static Piece [] toPieces(String placement) {
        int r = 0;
        Piece [] pieces = new Piece[placement.length()/4];

        for (int i = 0; i < placement.length(); i+=4) {
            String [] str = new String [placement.length() / 4];
            str[r] = placement.substring(i, i + 4);
            pieces[r] = toPiece(str[r]);
            r++;
        }
        return pieces;
    }

    public PieceType [][] getCoords() {
        PieceType [][] array = new PieceType[getXDimensions()][getYDimensions()];
        switch (type) {
            case b:
                if (dir == NORTH) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j < getYDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = b;
                            else if (j == 1 && i == 0)
                                array[i][j] = b;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = b;
                            else if (i == 0 && j == 0)
                                array[i][j] = b;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = b;
                            else if (i == 3 && j == 0)
                                array[i][j] = b;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = b;
                            else if (i == 1 && j == 3)
                                array[i][j] = b;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                break;

            case B:
                if (dir == NORTH) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (j == 0)
                                array[i][j] = B;
                            else if (j == 1 && i == 1)
                                array[i][j] = B;
                            else if (i == 3 && j == 1)
                                array[i][j] = B;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                if (dir == EAST) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (i == 1)
                                array[i][j] = B;
                            else if (i == 0 && j == 1)
                                array[i][j] = B;
                            else if (i == 0 && j == 3)
                                array[i][j] = B;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                if (dir == SOUTH) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (j == 1)
                                array[i][j] = B;
                            else if (i == 0 && j == 0)
                                array[i][j] = B;
                            else if (i == 2 && j == 0)
                                array[i][j] = B;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                if (dir == WEST) {
                    for (int i = 0; i < getXDimensions(); i++) {
                        for (int j = 0; j <getYDimensions(); j++) {
                            if (i == 0)
                                array[i][j] = B;
                            else if (i == 1 && j == 0)
                                array[i][j] = B;
                            else if (i == 1 && j == 2)
                                array[i][j] = B;
                            else
                                array[i][j] = nP;
                        }
                    }
                }
                break;
        }
    return array;
    }

//    public int [] entireCoords(String placement) {
//        PieceCoordinates [] array = new PieceCoordinates [this.getXDimensions() + this.getYDimensions()];
//        PieceCoordinates [] hi = new PieceCoordinates[] {(1,2)}
//        int x = Character.getNumericValue(placement.charAt(1));
//        int y = Character.getNumericValue(placement.charAt(2));
//
//        switch (fromChar(placement)) {
//            case b:
//                if (placement.charAt(3) == 'N') {
//                    array = {(x, y), (x + 1, y + 1)};
//                }
//                else if (placement.charAt(3) == 'S') {
//
//                }
//                else if (placement.charAt(3) == 'E') {
//
//                }
//                else if (placement.charAt(3) == 'W') {
//
//                }
//                break;
//            case B:
//
//        }
//    }

    /**
     * Using the Piece, find out the coordinates its occupying.
     *
     * @param placement : Must be in four letter string.
     * @return          : Returns the value of coordinates that is occupied by this piece.
     */

//    public static ArrayList<Integer> getIndividualCoords(String placement) {
//        int x = Character.getNumericValue(placement.charAt(1));
//        int y = Character.getNumericValue(placement.charAt(2));
//        PieceType type = fromChar(placement);
//        PieceDirection dir = getDirection(placement);
//        Piece piece = toPiece(placement);
//
//
//        switch (type) {
//            case b:
//                if (dir == NORTH) {
//
//
//
//                }
//        }
//        return ;
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
        return placement.charAt(1) - 48;
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
        return placement.charAt(2) - 48;
    }

    // method 1 : private static boolean validPosition()
    // returns true/false whether the piece can fit the board

    // method 2 : private int getXCoordinate(String position)
    // returns the set of coordinate of the piece (left-topmost coordinate)

    // method 3 : private int getYCoordinate(String position)
    // returns the set of coordinate of the piece (left-topmost coordinate)

    // method 4 : where the represented coordinates are
    // Jiwon 22/08




    public PieceDirection getDir() {
        return dir;
    }
}
