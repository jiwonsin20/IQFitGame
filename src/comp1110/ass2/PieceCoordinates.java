package comp1110.ass2;

// Added PieceCoordinates to specify the location of the piece on the board
// Jiwon 22/08

public class PieceCoordinates {
    int xCoord;
    int yCoord;

    PieceCoordinates (int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    public int getXCoordinate() {
        return xCoord;
    }
    public int getYCoordinate() {
        return yCoord;
    }
}
