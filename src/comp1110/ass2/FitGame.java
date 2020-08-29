package comp1110.ass2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Looked up on internet on how to import static enum class
// Jiwon 23/08
import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.Piece.*;
import static comp1110.ass2.PieceB1.*;
import static comp1110.ass2.PieceType.y;

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

    public static PieceType[][] initialBoard = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
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

        return (piecePlacement.length() == 4) &&
                (piecePlacement.charAt(0) == 'b' || piecePlacement.charAt(0) == 'B' ||
                        piecePlacement.charAt(0) == 'g' || piecePlacement.charAt(0) == 'G' ||
                        piecePlacement.charAt(0) == 'i' || piecePlacement.charAt(0) == 'I' ||
                        piecePlacement.charAt(0) == 'l' || piecePlacement.charAt(0) == 'L' ||
                        piecePlacement.charAt(0) == 'n' || piecePlacement.charAt(0) == 'N' ||
                        piecePlacement.charAt(0) == 'o' || piecePlacement.charAt(0) == 'O' ||
                        piecePlacement.charAt(0) == 'p' || piecePlacement.charAt(0) == 'P' ||
                        piecePlacement.charAt(0) == 'r' || piecePlacement.charAt(0) == 'R' ||
                        piecePlacement.charAt(0) == 's' || piecePlacement.charAt(0) == 'S' ||
                        piecePlacement.charAt(0) == 'y' || piecePlacement.charAt(0) == 'Y') &&
                (piecePlacement.charAt(1) == '0' || piecePlacement.charAt(1) == '1' ||
                        piecePlacement.charAt(1) == '2' || piecePlacement.charAt(1) == '3' ||
                        piecePlacement.charAt(1) == '4' || piecePlacement.charAt(1) == '5' ||
                        piecePlacement.charAt(1) == '6' || piecePlacement.charAt(1) == '7' ||
                        piecePlacement.charAt(1) == '8' || piecePlacement.charAt(1) == '9') &&
                (piecePlacement.charAt(2) == '0' || piecePlacement.charAt(2) == '1' ||
                        piecePlacement.charAt(2) == '2' || piecePlacement.charAt(2) == '3' ||
                        piecePlacement.charAt(2) == '4') &&
                (piecePlacement.charAt(3) == 'N' || piecePlacement.charAt(3) == 'S' ||
                        piecePlacement.charAt(3) == 'E' || piecePlacement.charAt(3) == 'W');

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

        if (placement.length() == 0 || placement.length() % 4 != 0)
            return false;

        char[] order = new char[placement.length() / 4];
        int temp = 0;
        for (int i = 0; i < placement.length(); i+= 4) {
                order[temp++] = Character.toLowerCase(placement.charAt(i));
        }

        String tempString = new String(order);
        for (int i = 1; i < tempString.length(); i++) {
            if (tempString.charAt(i - 1) > tempString.charAt(i))
                return false;
        }

        Set<Character> pieces = new HashSet<>();
        String[] piece = new String[placement.length()/4];

        for (int i = 0; i < placement.length() / 4; i++) {
            piece[i] = placement.substring(i * 4, (i + 1) * 4);
            if (!isPiecePlacementWellFormed(piece[i]))
                return false;
            if (pieces.contains(piece[i].charAt(0)))
                return false;
            pieces.add(piece[i].charAt(0));
        }
        return true;
    }


    public static boolean isOnBoard(String placement) {
        Piece [] piece = Piece.toPieces(placement);

        for (Piece value : piece) {
            int x = value.getXDimensions();
            int y = value.getYDimensions();

            int xCoord = value.coords.xCoord;
            int yCoord = value.coords.yCoord;

            if (xCoord + x - 1 > 9 || yCoord + y - 1 > 4 || xCoord + x - 1 < 0 || yCoord + y - 1 < 0)
                return false;
        }
        return true;
    }

    public static boolean isNotOverLapping(String placement) {

        Piece[] pieces = toPieces(placement);

        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };

        for (Piece piece : pieces) {
            PieceType[][] array = piece.getCoords();
            int x = piece.coords.getXCoordinate();
            int y = piece.coords.getYCoordinate();
            for (int j = x; j < x + piece.getXDimensions(); j++) {
                for (int i = y; i < y + piece.getYDimensions(); i++) {
                    if (initialBoard[i][j] != array[i - y][j - x]) {
                        if (initialBoard[i][j] != null && array[i - y][j - x] != null)
                            return false;
                        else if (array[i - y][j - x] == null)
                            initialBoard[i][j] = initialBoard[i][j];
                        else
                            initialBoard[i][j] = array[i - y][j - x];
                    }
                    else
                        initialBoard[i][j] = array[i - y][j - x];
                }
            }
        }
        return true;
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
        // FIXME Task 5: determine whether a placement string is valid
        if (!isPlacementWellFormed(placement)) {
            return false;
        }
        else {
            if (!isOnBoard(placement))
                return false;
            else {
                return isNotOverLapping(placement);
            }
        }
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
