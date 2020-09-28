package comp1110.ass2;

import java.util.*;

import static comp1110.ass2.Piece.*;


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
    // Code written by Jiwon Sin

    public static PieceType[][] initialBoard = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
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
     *
     * Code written by Mingxuan Wang
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {

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
     *
     * Code written by Di Mou
     */
    public static boolean isPlacementWellFormed(String placement) {

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
            char colorChar = piece[i].charAt(0);
            if (pieces.contains(Character.toLowerCase(colorChar)) ||
                    pieces.contains(Character.toUpperCase(colorChar)))
                return false;
            pieces.add(piece[i].charAt(0));
        }
        return true;
    }

    /**
     * isOnBoard function determines whether the piece is located within the board parameters
     * - For x values, it must be between 0 and 9. For y values it must be between 0 and 5.
     *
     * @param placement A String describing the shape, x and y coordinates and direction.
     * @return False if the piece is out of bounds. True if the position is within board boundaries.
     *
     * Code written by Jiwon Sin
     */


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

    /**
     * Determines whether the current piece overlaps with the board.
     * To check whether the piece is overlapping,
     * - Update the board
     * - Then check whether there is a piece(other than null) at the position where piece should be placed.
     *
     * @param placement A String describing the shape, x and y coordinates and direction.
     * @return False if the piece overlaps with already-placed board pieces.
     *
     * Code written by Jiwon Sin
     */

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
                    if (initialBoard[i][j] != null && array[i - y][j - x] != null) {
                        return false;
                    }
                    if (array[i - y][j - x] != null) {
                        initialBoard[i][j] = array[i - y][j - x];
                    }
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
     *
     * Code modified by Jiwon Sin
     */

    public static boolean isPlacementValid(String placement) {
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
     * Determines the missing pieces in String by comparing the default array with the input placement.
     * Checking the missing pieces is done by
     * - if the length of placement is zero (meaning any piece possible), return entire list in both upper and lowercase.
     * - if there is a viable placement String, then compare its first letter.
     *
     * @param placement A String describing the shape, x and y coordinates and direction.
     * @return List of String of pieces that is absent.
     *
     * Code written by Jiwon Sin
     */

    // what if there is all the elements?

    public static List<String> getMissingPieces (String placement) {
        List<String> collect = new ArrayList<>();
        String [] array = {"b", "g", "i", "l", "n", "o", "p", "r", "s", "y"};
        if (placement.length() == 0) {
            List<String> sortedList = new ArrayList<>();

            for (String s : array) {
                sortedList.add(s);
                sortedList.add(s.toUpperCase());
            }
            Collections.sort(sortedList);
            return sortedList;
        }

        for (String c : array) {
            for (int i = 0; i < placement.length(); i += 4) {
                if (Character.toString(placement.charAt(i)).equals(c) ||
                        Character.toString(placement.charAt(i)).toLowerCase().equals(c))
                    break;

                else if (i == placement.length() - 4) {
                    collect.add(c);
                    collect.add(c.toUpperCase());
                }
            }
        }
        Collections.sort(collect);
        return collect;
    }

    /**
     * This method updates the board with the input placement String.
     * If the piece is considered invalid, then returns null.
     *
     * @param placement A String describing the shape, x and y coordinates and direction.
     * @param initialBoard A PieceType multidimensional array that has piece's locations.
     * @return PieceType 2D-array with updated board. If the placement is invalid, then return null.
     *
     * Code written by Jiwon Sin
     */

//    public static PieceType[][] updateBoard(String placement, PieceType [][] initialBoard) {
//
//        if (!isPlacementValid(placement))
//            return null;
//        Piece[] pieces = toPieces(placement);
//        for (Piece piece : pieces) {
//            PieceType[][] array = piece.getCoords();
//            int x = piece.coords.getXCoordinate();
//            int y = piece.coords.getYCoordinate();
//            for (int j = x; j < x + piece.getXDimensions(); j++) {
//                for (int i = y; i < y + piece.getYDimensions(); i++) {
//                    if (array[i - y][j - x] == null && initialBoard[i][j] != null)
//                        initialBoard[i][j] = initialBoard[i][j];
//                    else
//                        initialBoard[i][j] = array[i - y][j - x];
//                }
//            }
//        }
//        return initialBoard;
//    }

    /**
     * This method checks whether the piece placement overlaps the updated board.
     * boardUpdate method is called to change the piece positions of pre-placed pieces (placement)
     * Then method checks whether the newPlacement piece overlaps with the current board.
     *
     * @param placement A String describing the shape, x and y coordinates and direction.
     * @param newPlacement A String that is going to be added, with shape, x, y coordinates and direction.
     * @param colX A column value
     * @param rowY A row value
     * @return True if the newPlacement does not overlaps with current board. False if it does.
     *
     * Code written by Jiwon Sin
     */

    public static boolean piecePlacementOverlapping (String placement, String newPlacement, int colX, int rowY) {

        Piece piece = toPiece(newPlacement);

        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };
        boardUpdate(placement, initialBoard);
        if (initialBoard[rowY][colX] != null)
            return false;

        PieceType[][] array = piece.getCoords();
        int x = piece.coords.getXCoordinate();
        int y = piece.coords.getYCoordinate();

        for (int j = x; j < x + piece.getXDimensions(); j++) {
            for (int i = y; i < y + piece.getYDimensions(); i++) {
                if (initialBoard[i][j] != null && array[i - y][j - x] != null) {
                    return false;
                }
                else
                    initialBoard[i][j] = array[i - y][j - x];
                }
            }
            if (initialBoard[rowY][colX] == null)
                return false;
        return true;
    }

    /**
     * This method checks whether the (x, y) values are covered by the certain piece
     * @param placement A String describing the shape, x and y coordinates and direction.
     * @param x X coordinate value
     * @param y Y coordinate value
     * @return True if the piece covers the (x, y) coordinate. Else return false.
     *
     * Code written by Jiwon Sin
     */

    public static boolean isCovered (String placement, int x, int y) {
        Piece piece = toPiece(placement);
        int xMin = piece.coords.xCoord;
        int xDim = piece.getXDimensions();
        int xMax = xMin + xDim - 1;
        int yMin = piece.coords.yCoord;
        int yDim = piece.getYDimensions();
        int yMax = yMin + yDim - 1;

        if (xMin > x || xMax < x || yMin > y || yMax < y) {
            return false;
        }

        PieceType[][] array = piece.getCoords();

        return array[y - yMin][x - xMin] != null;

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
     *
     * Code written by Jiwon Sin
     */
    static Set<String> getViablePiecePlacements(String placement, int col, int row) {

        List<String> listOfMissingPieces = getMissingPieces(placement);
        List<String> possiblePiecePlacements = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                String colString = Integer.toString(i);
                String rowString = Integer.toString(j);

                for (String listOfMissingPiece : listOfMissingPieces) {
                    String pieceN = listOfMissingPiece + colString + rowString + "N";
                    String pieceS = listOfMissingPiece + colString + rowString + "S";
                    String pieceE = listOfMissingPiece + colString + rowString + "E";
                    String pieceW = listOfMissingPiece + colString + rowString + "W";

                    possiblePiecePlacements.add(pieceN);
                    possiblePiecePlacements.add(pieceS);
                    possiblePiecePlacements.add(pieceE);
                    possiblePiecePlacements.add(pieceW);
                }
            }
        }

        possiblePiecePlacements.removeIf(piecePlacement -> !isPlacementWellFormed(piecePlacement));
        possiblePiecePlacements.removeIf(piecePlacement -> !isOnBoard(piecePlacement));
        possiblePiecePlacements.removeIf(piecePlacement -> !isCovered(piecePlacement, col, row));
        possiblePiecePlacements.removeIf(piecePlacement ->
                !piecePlacementOverlapping(placement, piecePlacement, col, row));

        Collections.sort(possiblePiecePlacements);

        Set<String> result = new HashSet<>(possiblePiecePlacements);
        if (result.size() == 0)
            return null;

        return result;
    }

    public static void boardUpdate (String placement, PieceType [][] initialBoard) {
//        if (isPlacementNotOverlapping(initialBoard, placement)) {
            Piece[] pieces = toPieces(placement);
            for (Piece piece : pieces) {
                PieceType[][] array = piece.getCoords();
                int x = piece.coords.getXCoordinate();
                int y = piece.coords.getYCoordinate();
                for (int j = x; j < x + piece.getXDimensions(); j++) {
                    for (int i = y; i < y + piece.getYDimensions(); i++) {
//                        if (array[i - y][j - x] == null && initialBoard[i][j] != null)
//                            initialBoard[i][j] = initialBoard[i][j];
                        if (array[i - y][j - x] != null && initialBoard[i][j] == null)
                            initialBoard[i][j] = array[i - y][j - x];
                    }
                }
            }

    }

    public static boolean isPlacementNotOverlapping(PieceType [][] board, String placement) {
        // For 4-character length placement
        Piece piece = toPiece(placement);
        if (piece == null)
            return true;

        PieceType[][] array = piece.getCoords();

        int x = piece.coords.getXCoordinate();
        int y = piece.coords.getYCoordinate();
        for (int j = x; j < x + piece.getXDimensions(); j++) {
            for (int i = y; i < y + piece.getYDimensions(); i++) {
                if (board[i][j] != null && array[i - y][j - x] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    public static int [] nullXCoordinate (PieceType[][] initialBoard) {
        int countEmptySpaces = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (initialBoard[i][j] == null)
                    countEmptySpaces++;
            }
        }

        int [] possibleX = new int[countEmptySpaces];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (initialBoard[i][j] == null) {
                    possibleX[k] = j;
                    k++;
                }
            }
        }
        return possibleX;
    }

    public static int [] nullYCoordinate (PieceType[][] initialBoard) {
        int countEmptySpaces = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (initialBoard[i][j] == null)
                    countEmptySpaces++;
            }
        }

        int [] possibleY = new int[countEmptySpaces];
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (initialBoard[i][j] == null) {
                    possibleY[k] = i;
                    k++;
                }
            }
        }
        return possibleY;
    }

    public static Set<String> combinationXY (int [] xCoordinates, int [] yCoordinates) {
        Set <String> result = new HashSet<>();
        for (int xValue : xCoordinates) {
            for (int yValue : yCoordinates)
            result.add(xValue + Integer.toString(yValue));
        }
        return result;
    }

    public static List<String> listOfPieces (String challenge) {

        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };
        boardUpdate(challenge, initialBoard);



        List<String> missingPieces = getMissingPieces(challenge);
        List<String> possiblePieces = new ArrayList<>();
        Set<String> possibleCoords = combinationXY(nullXCoordinate(initialBoard),nullYCoordinate(initialBoard));

        for (String piece : missingPieces) {
            for (String xyCoords : possibleCoords) {
                possiblePieces.add(piece + xyCoords + "N");
                possiblePieces.add(piece + xyCoords + "E");
                possiblePieces.add(piece + xyCoords + "S");
                possiblePieces.add(piece + xyCoords + "W");
            }

        }
        return possiblePieces;
    }

    public static boolean isPieceOverlappingBoard(String placement, String str) {

        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };
        boardUpdate(placement, initialBoard);

        Piece piece = toPiece(str);
        PieceType [][] pieceTypes = piece.getCoords();
        int xLocation = Character.getNumericValue(str.charAt(1));
        int pieceXDim = piece.getXDimensions();
        int yLocation = Character.getNumericValue(str.charAt(2));
        int pieceYDim = piece.getYDimensions();

        for (int j = xLocation; j < xLocation + pieceXDim; j++) {
            for (int i = yLocation; i < yLocation + pieceYDim; i++) {
                if (initialBoard[i][j] != null) {
                        if (pieceTypes[i - yLocation][j - xLocation] != null)
                            return false;
                    }
                    else {
                        initialBoard[i][j] = pieceTypes[i - yLocation][j - xLocation];
                    }
                }

            }
        return true;
    }

    public static List<String> findSolution(String challenge) {

        PieceType[][] board = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };

        boardUpdate(challenge, board);
        List<String> possibleP = listOfPieces(challenge);
        List<String> rtn = new ArrayList<>();

//        possibleP.removeIf(value -> !isOnBoard(value));
//        possibleP.removeIf(value -> !isPieceOverlappingBoard(challenge, value));
//        possibleP.removeIf(value -> !checkEmptySpace(value, board));

        for (String s : possibleP) {
            if (isOnBoard(s) && isPieceOverlappingBoard(challenge, s) && checkEmptySpace(challenge, s))
                rtn.add(s);
        }

        return rtn;
    }


    public static boolean isComplete(PieceType[][] initialBoard) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (initialBoard[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean pieceNotUsed (String challenge, String placement) {
        for (int i = 0; i < challenge.length(); i+=4) {
            if ((challenge.charAt(i)) == Character.toLowerCase(placement.charAt(0)) ||
                    (challenge.charAt(i)) == Character.toUpperCase(placement.charAt(0)) ||
                    challenge.charAt(i) == placement.charAt(0))
                return false;
        }
        return true;
    }

    public static boolean checkEmptySpace(String challenge, String placement) {
        PieceType[][] board = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };
        boardUpdate(challenge, board);
        boardUpdate(placement, board);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0) { // when row is 0
                    if (j == 0) {
                        if (board[i][j] == null && board[i][j+1] != null && board[i+1][j] != null)
                            return false;
//                        else if (board[i][j] == null && board[i][j+1] == null &&board[i][j+2] == null && board[i][j+3] == null)
//                            return false;
//                        else if (board[i][j] == null && board[i+1][j] == null &&board[i+2][j] == null && board[i+3][j] == null)
//                            return false;
                    }
                    else if (j < 9) {
                        if (board[i][j] == null && board[i][j + 1] != null && board[i][j - 1] != null && board[i + 1][j] != null)
                            return false;
//                        if (j < 7) {
//                            if (board[i][j] == null && board[i][j + 1] == null && board[i][j + 2] == null && board[i][j + 3] == null)
//                                return false;
//                            else if (board[i][j] == null && board[i + 1][j] == null && board[i + 2][j] == null && board[i + 3][j] == null)
//                                return false;

                    }
                    else {
                        if (board[i][j] == null && board[i][j-1] != null && board[i+1][j] != null)
                            return false;
//                        else if (board[i][j] == null && board[i][j-1] == null &&board[i][j-2] == null && board[i][j-3] == null)
//                            return false;
//                        else if (board[i][j] == null && board[i+1][j] == null &&board[i+2][j] == null && board[i+3][j] == null)
//                            return false;
                    }
                }
                else if (i == 4) { // when row is 4
                    if (j == 0) {
                        if (board[i][j] == null && board[i-1][j] != null && board[i][j+1] != null)
                            return false;
//                        else if (board[i][j] == null && board[i][j+1] == null &&board[i][j+2] == null && board[i][j+3] == null)
//                            return false;
//                        else if (board[i][j] == null && board[i-1][j] == null &&board[i-2][j] == null && board[i-3][j] == null)
//                            return false;
                    }
                    else if (j < 9) {
                        if (board[i][j] == null && board[i][j+1] != null && board[i][j-1] != null && board[i-1][j] != null)
                            return false;
//                        if (j < 7) {
//                            if (board[i][j] == null && board[i][j + 1] == null && board[i][j + 2] == null && board[i][j + 3] == null)
//                                return false;
//                            else if (board[i][j] == null && board[i + 1][j] == null && board[i + 2][j] == null && board[i + 3][j] == null)
//                                return false;

                    }
                    else {
                        if (board[i][j] == null && board[i-1][j] != null && board[i][j-1] != null)
                            return false;
                    }
                }
                else { // when row is 1,2,3
                    if (j == 0) {
                        if (board[i][j] == null && board[i-1][j] != null && board[i+1][j] != null && board[i][j+1] != null)
                            return false;
                    }
                    else if (j == 9) {
                        if (board[i][j] == null && board[i-1][j] != null && board[i+1][j] != null && board[i][j-1] != null)
                            return false;
                    }
                    else {
                        if (board[i][j] == null && (board[i + 1][j] != null && board[i][j + 1] != null && board[i - 1][j] != null && board[i][j - 1] != null))
                            return false;
                    }
                }
            }
        }
        return true;
    }

//    public static String seekSolution(String challenge) {
//        String initial = challenge;
//        PieceType[][] initialBoard = {
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null}
//        };
//        boardUpdate(challenge,initialBoard);
//        List<String> listOfSolutions = findSolution(challenge);
//
//        for (int i = 0; i < listOfSolutions.size(); i++) {
//            if (isPieceOverlappingBoard(challenge, listOfSolutions.get(i))) {
//                challenge += listOfSolutions.get(i);
//                boardUpdate(listOfSolutions.get(i),initialBoard);
//            }
//            for (int j = i + 1; j < listOfSolutions.size(); j++) {
//                if (pieceNotUsed(challenge, listOfSolutions.get(j))) {
//                    if (isPieceOverlappingBoard(challenge, listOfSolutions.get(j))) {
//                        challenge += listOfSolutions.get(j);
//                        boardUpdate(listOfSolutions.get(j),initialBoard);
//                    }
//                }
//            }
//            if (!isComplete(initialBoard)) {
//                challenge = initial;
//            }
//        }
//        return challenge;
//    }


    public static void main(String[] args) {
        String str = "b40Nl30WO01Wp51Nr63SS23SY52S";
        String str2 = "B40S";
        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };
        boardUpdate(str, initialBoard);
//        System.out.println(Arrays.deepToString(initialBoard));

//        System.out.println(listOfPieces(str));
        System.out.println(findSolution(str));

//        System.out.println(isOnBoard(str2) && isPieceOverlappingBoard(str, str2));
//        System.out.println(checkEmptySpace(str2, initialBoard));
//        System.out.println(Arrays.deepToString(initialBoard));


    }



    public static void insertPiecePlacement(List<String> piecePlacements, String piecePlacement) {
        char colorChar = Character.toLowerCase(piecePlacement.charAt(0));
        for (int i = 0; i < piecePlacements.size(); ++i) {
            String currPiecePlacement = piecePlacements.get(i);
            char currColorChar = Character.toLowerCase(currPiecePlacement.charAt(0));
            if (colorChar < currColorChar) {
                piecePlacements.add(i, piecePlacement);
                return;
            }
        }
        piecePlacements.add(piecePlacements.size(), piecePlacement);
    }
    /**
     * Determine whether the piece can move for a specified distance
     **
     * @param placement A piece string.
     * @param m piece move m distance in x direction
     * @param n piece move n distance in y direction
     * @return whether the end position of the piece is valid in the Board
     *
     * Code written by Mingxuan Wang
     */
    public static boolean pieceMove(String placement, int m ,int n){
        Piece piece = toPiece(placement);
        assert piece != null;
        int xStart = piece.coords.xCoord;
        int xDim = piece.getXDimensions();
        int xEnd = xStart + xDim + m -1;
        int yStart = piece.coords.yCoord;
        int yDim = piece.getYDimensions();
        int yEnd = yStart + yDim + n -3;

        if(xEnd<0||xEnd>9||yEnd<0||yEnd>4){
            return false;
        }

        return isPiecePlacementWellFormed(placement);

    }


    private static String getSolutionHelper(List<String> piecePlacements, Set<String> triedPlacements) {
//        piecePlacements.sort(new PieceComparator());

        String placement = String.join("", piecePlacements);

        if (triedPlacements.contains(placement)) {
            return null;
        }

//        System.out.println("oldSize: " + piecePlacements.size());
//        System.out.println(" > " + placement);

        if (!isPlacementWellFormed(placement)) {
            System.out.println(placement);
            System.exit(-2);
        }

        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };
        boardUpdate(placement, initialBoard);

        boolean isBoardFull = true;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (initialBoard[j][i] != null) {
                    continue;
                }
                isBoardFull = false;
                Set<String> viablePiecePlacements = getViablePiecePlacements(placement, i, j);
                if (viablePiecePlacements == null) {
                    return null;
                }
                for (String viablePiecePlacement : viablePiecePlacements) {
                    List<String> newPiecePlacements = new ArrayList<String>(piecePlacements);
                    insertPiecePlacement(newPiecePlacements, viablePiecePlacement);
                    String solution = getSolutionHelper(newPiecePlacements, triedPlacements);
                    if (solution != null) {
                        return solution;
                    }
                }
            }
        }

        if (isBoardFull) {
            return placement;
        }

        return null;
    }

//   public static String getSolution(String challenge) {
//        List<String> piecePlacements = new ArrayList<String>();
//        Set<String> triedPlacements = new HashSet<String>();

//        for (int i = 0; i < challenge.length() - 4; i += 4) {
//           String pieceString = challenge.substring(i, i + 4);
//            piecePlacements.add(pieceString);
//        }

//        return getSolutionHelper(piecePlacements, triedPlacements);
//    }

//        boardUpdate(challenge,initialBoard);
//        List<String> listOfSolutions = findSolution(challenge);
//
//        for (int i = 0; i < listOfSolutions.size(); i++) {
//            if (isPieceOverlappingBoard(challenge, listOfSolutions.get(i))) {
//                challenge += listOfSolutions.get(i);
//                boardUpdate(listOfSolutions.get(i),initialBoard);
//            }
//            for (int j = i + 1; j < listOfSolutions.size(); j++) {
//                if (pieceNotUsed(challenge, listOfSolutions.get(j))) {
//                    if (isPieceOverlappingBoard(challenge, listOfSolutions.get(j))) {
//                        challenge += listOfSolutions.get(j);
//                        boardUpdate(listOfSolutions.get(j),initialBoard);
//                    }
//                }
//            }
//            if (!isComplete(initialBoard)) {
//                challenge = initial;
//            }
//        }

//        List<String> nList = new ArrayList<>();
//        for (int i = 0; i < challenge.length(); i+=4) {
//            nList.add(challenge.substring(i,i+4));
//        }
//
//        Collections.sort(nList);
//        String result = "";
//
//        for (String value : nList) {
//            result += value;
//        }
//
//        return result;
    public static int getMinimumXValue(PieceType [][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == null)
                    return j;
            }
        }
        return 10;
    }

    public static int getMinimumYValue(PieceType [][] board) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == null)
                    return i;
            }
        }
        return 5;
    }

//    public static String getSolutionHelper(String challenge, int limit) {
//        int minX = getMinimumXValue(initialBoard);
//        int minY = getMinimumYValue(initialBoard);
//
//        if (limit == 1 && getViablePiecePlacements(challenge, minX, minY) != null) {
//            if (challenge.length() == 40)
//                return getViablePiecePlacements(challenge, minX, minY).toString();
//            else
//                return
//        }
//    }



    public static String getSolution(String challenge) {
        String initial = challenge;
//        PieceType[][] initialBoard = {
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null},
//                {null, null, null, null, null, null, null, null, null, null}
//        };
        boardUpdate(challenge, initialBoard);


        int minX = getMinimumXValue(initialBoard);
        int minY = getMinimumYValue(initialBoard);

        int numberOfIteration = (40 - challenge.length()) / 4;

        Set<String> possiblePositions = getViablePiecePlacements(challenge, minX, minY);

//        for (String values : possiblePositions) {
//            challenge += values;
//            String str = getSolutionHelper(challenge, numberOfIteration - 1);
//            if (str.length() != 40)
//                challenge = initial;
//        }

        return null;
    }

        // FIXME Task 9: determine the solution to the game, given a particular challenge

}
