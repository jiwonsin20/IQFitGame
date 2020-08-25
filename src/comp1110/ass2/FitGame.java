package comp1110.ass2;

import java.util.Set;

// Looked up on internet on how to import static enum class
// Jiwon 23/08
import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.Piece.*;
import static comp1110.ass2.PieceB1.*;
/**
 * This class provides the text interface for the IQ Fit Game
 * <p>
 * The game is based directly on Smart Games' IQ-Fit game
 * (https://www.smartgames.eu/uk/one-player-games/iq-fit)
 */
public class FitGame {

    // This sets the board of the game.
    // Used multi-dimensional array that can keep the values of PieceColour elements.
    // When nothing is placed, it will be noPiece. However when some piece is added, noPiece will change to
    // whatever the colour of the piece is (blue ~ yellow)

    public static PieceType [][] initialBoard = {
            {nP, nP, nP, nP, nP, nP, nP, nP, nP, nP},
            {nP, nP, nP, nP, nP, nP, nP, nP, nP, nP},
            {nP, nP, nP, nP, nP, nP, nP, nP, nP, nP},
            {nP, nP, nP, nP, nP, nP, nP, nP, nP, nP},
            {nP, nP, nP, nP, nP, nP, nP, nP, nP, nP},
    };
//
//    public static void main(String[] args) {
//        System.out.println(initialBoard[1][4]);
//        initialBoard[1][4] = y;
//        System.out.println(initialBoard[1][4]);
//        System.out.println(initialBoard[2][4]);
//    }

    // updateBoard(PieceType [][])

    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is a valid piece descriptor character (b, B, g, G, ... y, Y)
     * - the second character is in the range 0 .. 9 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in valid orientation N, S, E, W
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
    // FIXME Task 2: determine whether a piece placement is well-formed
        return false;
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     * - the pieces are ordered correctly within the string
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        return true;
    }


    // For this problem, need think of a method that can
    // 1. Entirely on the board
    // 2. Pieces must not overlap each other

    /**
     * Given a placement string, the isOnTheBoard method determines whether the piece coordinate (left corner)
     * exists within the board.
     *
     * Since placement string only has coordinate for the left-corner of the piece, the piece's dimension
     * must be taken into account (whether its 4 x 2, 2 x 4, 3 x 2, or 2 x 3).
     *
     * @param
     * @return True if the placement string is located within the board.
     */

    // Added by Jiwon 23/08
//    public static boolean isOnTheBoard(String placement) {
//        if (placement.length() > 4) {
//            for (int i = 0; i < placement.length() / 4; i += 4) {
//                String sepString = placement.substring(i, i + 4);
//                if (Character.getNumericValue(sepString.charAt(1)) < 0 || Character.getNumericValue(sepString.charAt(1)) > 9)
//                    return false;
//                else if (Character.getNumericValue(sepString.charAt(2)) < 0 || Character.getNumericValue(sepString.charAt(2)) > 4)
//                    return false;
//            }
//        } else if (placement.length() == 4) {
//            if (Character.getNumericValue(placement.charAt(1)) < 0 || Character.getNumericValue(placement.charAt(1)) > 9)
//                return false;
//            else if (Character.getNumericValue(placement.charAt(2)) < 0 || Character.getNumericValue(placement.charAt(2)) > 4)
//                return false;
//        }
//        return true;
//    }
//    public static void main(String[] args) {
//        String str = "p01W";
//        System.out.println(isOnBoard(str));
//    }

    public static boolean isOnBoard(String placement) {
        Piece piece = Piece.toPiece(placement);

        int x = piece.getXDimensions();
        int y = piece.getYDimensions();
        int xCoord = getXCoordinate(placement);
        int yCoord = getYCoordinate(placement);

        return xCoord + x - 1 <= 9 && yCoord + y - 1 <= 4;
    }

    public static void main(String[] args) {
        String placement = "b20NB12N";
        System.out.println(doesOverlap(placement));
    }

    public static boolean doesOverlap(String placement) {
//        int x = Character.getNumericValue(placement.charAt(1));
//        int y = Character.getNumericValue(placement.charAt(2));
        Piece[] pieces = toPieces(placement);

        for (int k = 0; k < placement.length()/4; k++) {
            PieceType[][] array = pieces[k].getCoords();
            int x = pieces[k].coords.getXCoordinate();
            int y = pieces[k].coords.getYCoordinate();


            for (int i = x; i < x + pieces[k].getXDimensions() || i < 10; i++) {
                for (int j = y; j < y + pieces[k].getYDimensions() || j < 5; j++) {
                    if (initialBoard[i][j] != nP && array[i - x][j - y] != nP)
                        return true;
                    else
                        initialBoard[i][j] = array[i - x][j - y];
                }
            }

        }
        return false;
    }



    /**
     * Determine whether a placement string is valid.
     *
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     *   rules of the game:
     *   - pieces must be entirely on the board
     *   - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */


    public static boolean isPlacementValid(String placement) {
        if (!isPiecePlacementWellFormed(placement))
            return false;

        if (!isOnBoard(placement))
            return false;


        // To check whether they are entirely on the board, need to know the dimension of each piece

        return true; // FIXME Task 5: determine whether a placement string is valid
    }

    /**
     * Given a string describing a placement of pieces, and a location
     * that must be covered by the next move, return a set of all
     * possible next viable piece placements which cover the location.
     *
     * For a piece placement to be viable it must:
     *  - be a well formed piece placement
     *  - be a piece that is not already placed
     *  - not overlap a piece that is already placed
     *  - cover the location
     *
     * @param placement A starting placement string
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of all viable piece placements, or null if there are none.
     */
    static Set<String> getViablePiecePlacements(String placement, int col, int row) {
        return null; // FIXME Task 6: determine the set of all viable piece placements given existing placements
    }

    /**
     * Return the solution to a particular challenge.
     **
     * @param challenge A challenge string.
     * @return A placement string describing the encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        return null;  // FIXME Task 9: determine the solution to the game, given a particular challenge
    }
}
