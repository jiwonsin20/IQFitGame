package comp1110.ass2;

/**
 * Added PieceCoordinates to specify the location of the piece on the board
 *
 *  Created by Jiwon Sin 22/08
 *
 */

public class PieceCoordinates {
    public final int xCoord;
    public final int yCoord;

    /**
     * Constructor for piece's coordinate
     *
     * @param x Value of X coordinate
     * @param y Value of Y coordinate
     *
     * Code written by Jiwon Sin
     */

    PieceCoordinates(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * PieceCoordinates returns the value of piece's coordinates in tuple form
     *
     * @param placement Piece's 4-character long string
     * @return Coordinate of the piece in (x, y) form
     *
     * Code written by Jiwon Sin
     */

    public static PieceCoordinates getPCoord(String placement) {
        PieceCoordinates coordinates;
        int x = Character.getNumericValue(placement.charAt(1));
        int y = Character.getNumericValue(placement.charAt(2));

        coordinates = new PieceCoordinates(x, y);

        return coordinates;
    }

    /**
     * Returns the value of X coordinate of piece
     *
     * @return x coordinate of piece
     *
     * Code written by Jiwon Sin
     */

    public int getXCoordinate() {
        return xCoord;
    }

    /**
     * Returns the value of y coordinate of piece
     *
     * @return y coordinate of piece
     *
     * Code written by Jiwon Sin
     */

    public int getYCoordinate() {
        return yCoord;
    }

    public void setxCoord(int xCoord) {
        xCoord = xCoord;
    }

    public void setyCoord(int yCoord) {
        yCoord = yCoord;
    }

}
