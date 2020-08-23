package comp1110.ass2;

/** Added PieceCoordinates to specify the location of the piece on the board
 *
 *  Created by Jiwon Sin 22/08
 *
 */

import comp1110.ass2.PieceType.*;

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

//    public int [][] getTwoDArray(String placement) {
//        PieceType type =
//
//
////        char firstChar = placement.charAt(0);
////        PieceType type;
////        switch (firstChar) {
////            case 'b':
////                type = PieceType.b;
////                break;
////            case 'B':
////                type = PieceType.B;
////                break;
////            case 'g':
////                type = PieceType.g;
////                break;
////            case 'G':
////                type = PieceType.G;
////                break;
////            case 'i':
////                type = PieceType.i;
////                break;
////            case 'I':
////                type = PieceType.I;
////                break;
////            case 'l':
////                type = PieceType.l;
////                break;
////            case 'L':
////                type = PieceType.L;
////                break;
////            case 'n':
////                type = PieceType.n;
////                break;
////            case 'N':
////                type = PieceType.N;
////                break;
////            case 'o':
////                type = PieceType.o;
////                break;
////            case 'O':
////                type = PieceType.O;
////                break;
////            case 'p':
////                type = PieceType.p;
////                break;
////            case 'P':
////                type = PieceType.P;
////                break;
////            case 'r':
////                type = PieceType.r;
////                break;
////            case 'R':
////                type = PieceType.R;
////                break;
////            case 's':
////                type = PieceType.s;
////                break;
////            case 'S':
////                type = PieceType.S;
////                break;
////            case 'y':
////                type = PieceType.y;
////                break;
////            case 'Y':
////                type = PieceType.Y;
////                break;
////        }
//
//
//    }
}
