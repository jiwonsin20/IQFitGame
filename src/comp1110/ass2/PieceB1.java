package comp1110.ass2;

import java.util.Arrays;

public class PieceB1 extends Piece{
    PieceB1(PieceType type, PieceCoordinates coords, PieceDirection dir) {
        super(type, coords, dir);
    }

    public int [] pieceXCoordinates() {
        int [] array = new int[4];
        int x  = coords.getXCoordinate();

        for (int i = 0; i < array.length; i++) {
            array[i] = x;
            x++;
        }
        return array;
    }

    public int [] pieceYCoordinates() {
        int [] array = new int [2];
        int y = coords.getYCoordinate();

        for (int i = 0; i < array.length; i++) {
            array[i] = y;
            y++;
        }
        return array;
    }

}
