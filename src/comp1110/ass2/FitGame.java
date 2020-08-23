package comp1110.ass2;

import java.util.Set;

// Looked up on internet on how to import static enum class
// Jiwon 23/08
import static comp1110.ass2.PieceColour.*;
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

    public static PieceColour [][] board = {
            {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece},
            {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece},
            {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece},
            {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece},
            {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}, {noPiece}
    };


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

        char [] pieceDescriptor = {'b','g','i','l','n','o','p','r','s','y'};

        if (piecePlacement.length() !=4)
            return false;
        if (piecePlacement.charAt(0) != ' ') {
            for (int i = 0; i < pieceDescriptor.length; i++) {
                if (piecePlacement.charAt(0) == pieceDescriptor[i] || piecePlacement.charAt(0) == Character.toUpperCase(pieceDescriptor[i]))
                    break;
                else if (i == pieceDescriptor.length - 1)
                    return false;
            }
        }
        if (Character.getNumericValue(piecePlacement.charAt(1)) > 9 || Character.getNumericValue(piecePlacement.charAt(1)) < 0)
            return false;
        if (Character.getNumericValue(piecePlacement.charAt(2)) > 4 || Character.getNumericValue(piecePlacement.charAt(2)) < 0)
            return false;
        if (piecePlacement.charAt(3) != 'N' && piecePlacement.charAt(3) != 'S' && piecePlacement.charAt(3) != 'E' && piecePlacement.charAt(3) != 'W')
            return false;
        return true;
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
    public static boolean isPlacementWellFormed(String placement) { // completed task 3 Jiwon 22/08
        // FIXME Task 3: determine whether a placement is well-formed

        int pLength = placement.length();

        // For a placement string to be valid, its length must be in multiple of 4
        // OR not zero.
        if (pLength % 4 != 0 || pLength == 0)
            return false;

        // Splitting the placement string into substrings of four and add them to a list.
        // Example: "r01Nb45S" -> ["r01N", "b45S"].
        for (int i = 0; i < pLength; i += 4) {
            if (pLength == 4) {
                if (!isPiecePlacementWellFormed(placement))
                    return false;
            }
            else {
                if (!isPiecePlacementWellFormed(placement.substring(i, i + 4))) // Site website
                    return false;
            }
        }

        // Creating new charArray that stores all charAt(0) of placement strings.
        char [] order = new char[pLength/4];
        int j = 0;
        for (int i = 0; i < pLength; i += 4) {
            order[j] = Character.toLowerCase(placement.charAt(i));
            j++;
        }

        // Checking whether there are identical pieces
        for (int i = 1; i < order.length; i++) {
            for (int k = 0; k < i; k++) {
                if (order[i] == order[k])
                    return false;
            }
        }

        // Checking whether pieces are ordered in (b -> g -> i -> l -> n -> o -> p -> r -> s -> y)
        for (int i = 1; i < order.length; i++) {
            if (order[i - 1] > order[i])
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String str = "g82EI63SL02Ws23S";
        System.out.println(isOnTheBoard(str));
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


    // For this problem, need think of a method that can
    // 1. Entirely on the board
    // 2. Pieces must not overlap each other

    // Added by Jiwon 23/08
    public static boolean isOnTheBoard(String placement) {
        if (placement.length() > 4) {
            for (int i = 0; i < placement.length()/4; i+=4) {
                String sepString = placement.substring(i, i + 4);
                if (Character.getNumericValue(sepString.charAt(1)) < 0 || Character.getNumericValue(sepString.charAt(1)) > 9)
                    return false;
                else if (Character.getNumericValue(sepString.charAt(2)) < 0 || Character.getNumericValue(sepString.charAt(2)) > 4)
                    return false;
            }
        }
        else if (placement.length() == 4) {
            if (Character.getNumericValue(placement.charAt(1)) < 0 || Character.getNumericValue(placement.charAt(1)) > 9)
                return false;
            else if (Character.getNumericValue(placement.charAt(2)) < 0 || Character.getNumericValue(placement.charAt(2)) > 4)
                return false;
        }
        return true;
    }

//    public static boolean doesOverlap(String placement) {
//
//    }

    public static boolean isPlacementValid(String placement) {
        return false; // FIXME Task 5: determine whether a placement string is valid
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
