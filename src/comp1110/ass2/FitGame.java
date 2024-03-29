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
        return isPlacementWellFormed(placement) && isOnBoard(placement) && isNotOverLapping(placement);
//        if (!isPlacementWellFormed(placement)) {
//            return false;
//        }
//        else {
//            if (!isOnBoard(placement))
//                return false;
//            else {
//                return isNotOverLapping(placement);
//            }
//        }
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

    public static List<String> getMissingPieces (String placement) {
        List<String> stringList = new ArrayList<>();
        List<String> array = new ArrayList<>(Arrays.asList("b", "g", "i", "l", "n", "o", "p", "r", "s", "y"));
        if (placement.length() == 0) {
            for (String s : array) {
                stringList.add(s);
                stringList.add(s.toUpperCase());
            }
        }
        for (String c : array) {
            for (int i = 0; i < placement.length(); i += 4) {
                if (Character.toString(placement.charAt(i)).toLowerCase().equals(c)) {
                    break;
                }
                else if (i == placement.length() - 4) {
                    stringList.add(c);
                    stringList.add(c.toUpperCase());
                }
            }
        }
        Collections.sort(stringList);
        return stringList;
    }

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
        return initialBoard[rowY][colX] != null;
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

        for (int i = 0; i < col + 2 && i < 9 ; i++) {
            for (int j = 0; j < row + 2 &&  j < 4; j++) {
                String xy = i + Integer.toString(j);
                for (String missingPiece : listOfMissingPieces) {
                    String pieceN = missingPiece + xy + "N";
                    String pieceS = missingPiece + xy + "S";
                    if (j > 2) {
                        possiblePiecePlacements.add(pieceN);
                        possiblePiecePlacements.add(pieceS);
                    }
                    else {
                        String pieceE = missingPiece + xy + "E";
                        String pieceW = missingPiece + xy + "W";

                        possiblePiecePlacements.add(pieceN);
                        possiblePiecePlacements.add(pieceS);
                        possiblePiecePlacements.add(pieceE);
                        possiblePiecePlacements.add(pieceW);
                    }
                }
            }
        }
        possiblePiecePlacements.removeIf(piecePlacement -> !isOnBoard(piecePlacement));
        possiblePiecePlacements.removeIf(piecePlacement -> !isCovered(piecePlacement, col, row));
        possiblePiecePlacements.removeIf(piecePlacement ->
                !piecePlacementOverlapping(placement, piecePlacement, col, row));

        Set<String> result = new HashSet<>(possiblePiecePlacements);
        if (result.size() == 0)
            return null;
        return result;
    }

    /**
     * This method updates the board, whenever changes to the board is made (mainly deals with addition of pieces).
     *
     * @param placement : Piece string that is being used
     * @param initialBoard : Current board int 2-dimension array
     *
     * Code Written by Jiwon Sin
     */

    public static void boardUpdate (String placement, PieceType [][] initialBoard) {
        Piece[] pieces = toPieces(placement);
        for (Piece piece : pieces) {
            PieceType[][] array = piece.getCoords();
            int x = piece.coords.getXCoordinate();
            int y = piece.coords.getYCoordinate();
            for (int j = x; j < x + piece.getXDimensions(); j++) {
                for (int i = y; i < y + piece.getYDimensions(); i++) {
                    if (array[i - y][j - x] != null && initialBoard[i][j] == null)
                        initialBoard[i][j] = array[i - y][j - x];
                }
            }
        }
    }

    /**
     * This method checks whether the placement string overlaps with any of the current board.
     * If the board element overlaps with the piece with specific coordinate, will return false.
     * Else, the piece will return true.
     *
     * @param board : Current board in terms of 2-dimensional Array
     * @param placement : Current piece that is compared with array
     * @return True if there is no overlap. False if there is at least 1 overlapping.
     *
     * Code Written by Jiwon Sin
     */

    public static boolean isPlacementNotOverlapping(PieceType [][] board, String placement) {
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

    /**
     * This method finds the list of pieces that could possibly placed on the board.
     * Using getViablePiecePlacements() method, this method checks whether the piece on certain coordinate
     * satisfies conditions below
     *  - piece placement is a valid string
     *      - Piece is positioned entirely on the board
     *  - piece placement does not overlap board (at any point of time)
     * Output will be
     * @param challenge : String placement
     * @param board : Current board of the game
     * @return A set of String placements that could possibly fit in the specific coordinate
     *
     * Code Written by Jiwon Sin
     */

    public static Set<String> listOfPieces (String challenge, PieceType [][] board) {

        Set<String> possiblePs = getViablePiecePlacements(challenge, findOptimalX(challenge,board).get(1), findOptimalX(challenge,board).get(0));
        if (possiblePs != null) {
            possiblePs.removeIf(value -> !isPlacementValid(value));
//            possiblePs.removeIf(value -> !isPieceOverlappingBoard(challenge, value));
        }
        return possiblePs;
    }

    /**
     * This method compares placement String and the target piece String and returns whether it overlaps or not.
     * @param placement : String of placements
     * @param str : Target piece string
     * @return True if piece does not overlap. False if piece overlaps.
     *
     * Code Written by Jiwon Sin
     */

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

    /**
     * Checks whether entire board is filled up by checking whether there is any null
     *
     * @param initialBoard : Current board of the game
     * @return True if there is no null in 10 * 5 board, False if there is at least 1.
     *
     * Code Written by Jiwon Sin
     */

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

    /**
     * This method re-arranges the given solution String into a proper solution string
     *
     * @param solution : Solution string obtained from getSolution method
     * @return Solution String that is re-arranged according to rules
     *
     * Code Written by Jiwon Sin
     */

    public static String changeSequence(String solution) {
        String [] str = new String[solution.length() / 4];
        StringBuilder rtn = new StringBuilder();
        String [] aligned = new String[10];

        for (int i = 0, j = 0; i < solution.length() && j < 10; i+=4, j++ ) {
            str[j] = solution.substring(i, i + 4);
        }
        for (String value : str) {
            char c = value.charAt(0);
            if (c == 66 || c == 98) // B or b
                aligned[0] = value;
            else if (c == 71 || c == 103) // G or g
                aligned[1] = value;
            else if (c == 73 || c == 105) // I or i
                aligned[2] = value;
            else if (c == 76 || c == 108) // L or l
                aligned[3] = value;
            else if (c == 78 || c == 110) // N or n
                aligned[4] = value;
            else if (c == 79 || c == 111) // O or o
                aligned[5] = value;
            else if (c == 80 || c == 112) // P or p
                aligned[6] = value;
            else if (c == 82 || c == 114) // R or r
                aligned[7] = value;
            else if (c == 83 || c == 115) // S or s
                aligned[8] = value;
            else if (c == 89 || c == 121) // Y or y
                aligned[9] = value;
        }

        for (String value : aligned)
            rtn.append(value);
        return rtn.toString();
    }

    /**
     * This method checks whether the placement of this string is logical
     * What does it mean by logical?
     *  - When this placement string is placed, then there should be no cell where getViablePiecePlacement() == null
     * @param challenge : Placement string that is being checked
     * @param initialBoard : Current board which challenge string will be placed
     * @return True if the placement challenge is considered valid, False if its not logical
     *
     * Code written by Jiwon Sin
     */

    public static boolean isThisLogical(String challenge, PieceType [][] initialBoard) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (initialBoard[i][j] == null) {
                    if (getViablePiecePlacements(challenge,j,i) == null)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * This method calls getSolutionREC to find the solution for objective string
     * and re-arranges the string accordingly
     * @param challenge : Objective string
     * @return Solution string obtained from getSolutionREC
     *
     * Code written by Jiwon Sin
     */

    public static String getSolution(String challenge) {
        PieceType[][] initialBoard = {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
        };

        boardUpdate(challenge, initialBoard);
        int numberOfIteration = (40 - challenge.length()) / 4;
        String result = getSolutionREC(challenge, "", initialBoard, numberOfIteration);
        return changeSequence(result);
    }

    /**
     * This method finds the solution recursively.
     * Multiple conditions needs to be met to find correct solution
     *  - list of possible piece after placing placement should not be null
     *  - the board must be completed when recTimes == 1, then there should be no null in board
     *  - then the challenge string adds that placement string
     * If conditions are not met,
     *  - remove the piece from the board
     *  - go back one step of recursion and find another possible piece
     *
     * @param challenge : challenge string of the game
     * @param placement : placement that could be placed
     * @param board : current board
     * @param recTimes : Number of pieces that needs to be found
     * @return Complete String that is NOT arranged.
     *
     * Code written by Jiwon Sin
     */

    public static String getSolutionREC(String challenge, String placement, PieceType [][] board, int recTimes) {
        String rtn = "";
        // base case of recursion
        if (recTimes == 1) {
            Set<String> list = listOfPieces(challenge, board);
            if (list != null) {
                for (String values : list) {
                    boardUpdate(values, board);
                    // even at base level, the board is not complete
                    if (!isComplete(board)) {
                        // incorrect piece so try again
                        clearBoard(values, board);
                    }
                    // it is complete
                    else {
                        return challenge + values;
                    }
                }
            }
            else {
                clearBoard(placement, board);
            }
        }

        // If recTimes is more than 1 (step case)
        else {
            Set<String> pieceList = listOfPieces(challenge, board);
            if (pieceList == null) {
                clearBoard(placement, board);
                challenge = challenge.substring(0, challenge.length() - 4);
            }
            else {
                for (String values : pieceList) {
                    boardUpdate(values, board);

                    if (isThisLogical(challenge + values, board)) {
                        challenge += values;
                        rtn = getSolutionREC(challenge, values,board, recTimes - 1);
                        if (isComplete(board))
                            return rtn;
                        else {
                            clearBoard(values, board);
                            challenge = challenge.substring(0, challenge.length() - 4);
                        }
                    }
                    else {
                        clearBoard(values, board);
                    }
                }
            }
        }
        return rtn;
    }

    /**
     * This method clears the placement string from the board
     * @param placement : placement string that needs to be cleared
     * @param initialBoard : current board of the game
     *
     * Code written by Jiwon Sin
     */

    public static void clearBoard(String placement, PieceType [][] initialBoard) {
        int xLocation = Character.getNumericValue(placement.charAt(1));
        int yLocation = Character.getNumericValue(placement.charAt(2));
        char orientation = placement.charAt(3);
        Piece piece = toPiece(placement);

        if (isPlacementValid(placement)) {
            if (orientation == 'N' || orientation == 'S') {
                for (int i = xLocation; i < xLocation + Objects.requireNonNull(piece).type.getSpineNum(); i++) {
                    for (int j = yLocation; j < yLocation + 2; j++) {
                        if (initialBoard[j][i] == piece.getType())
                            initialBoard[j][i] = null;
                    }
                }
            } else { // West or East
                for (int i = xLocation; i < xLocation + 2; i++) {
                    for (int j = yLocation; j < yLocation + Objects.requireNonNull(piece).type.getSpineNum(); j++) {
                        if (initialBoard[j][i] == piece.getType())
                            initialBoard[j][i] = null;
                    }
                }
            }
        }
    }

    /**
     * Returns the list of integers that has smallest number of pieces (x and y) coordinates
     * Initially sets possiblePieces as ridiculously large number.
     * Replaces when possiblePieces is less than what it has before.
     * This method is used to boost the speed of recursion method above
     *
     * @param challenge : Challenge string of the game
     * @param board : current board
     * @return List of integers (x,y) coordinate that has least number of possible pieces
     *
     * Code written by Jiwon Sin
     */

    public static List<Integer> findOptimalX(String challenge, PieceType [][] board) {
        int possiblePieces = 100;
        int chances;
        List<Integer> array = new ArrayList<>();
        Set<String> numPieces;
        for (int i =0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == null) {
                    numPieces = getViablePiecePlacements(challenge, j, i);
                    if (numPieces != null) {
                        chances = numPieces.size();
                        if (numPieces.size() < possiblePieces) {
                            possiblePieces = chances;
                            array.clear();
                            array.add(i);
                            array.add(j);
                        }
                    }
                }
            }
        }
        return array;
    }
}
