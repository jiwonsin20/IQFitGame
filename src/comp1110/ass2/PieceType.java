package comp1110.ass2;

import static comp1110.ass2.PieceColour.*;

public enum PieceType {

    /**
     * This
     */

    B(blue, 2,4), b(blue, 1,4),
    G(green, 2,3), g(green, 1,3),
    I(indigo, 2,3), i(indigo, 1,3),
    L(limegreen, 2,3), l(limegreen, 1,3),
    N(navyblue, 2,3), n(navyblue, 1,3),
    O(orange, 2,4), o(orange, 1,4),
    P(pink, 2,4), p(pink, 1,4),
    R(red, 2,4), r(red, 1,4),
    S(skyblue, 2,4), s(skyblue, 1,4),
    Y(yellow, 2,4), y(yellow, 1,4);


    // colour can just be used in constructor
    // Jiwon 22/08/20

    public final PieceColour colour;
    public final int protrusion;
    //protrusion is changed, so it can't be final, Mingxuan Wang 08/23
    public final int spineNum;

    PieceType(PieceColour colour, int protrusion, int spineNum) {
        this.colour = colour;
        this.protrusion = protrusion;
        this.spineNum = spineNum;
    }



    public static char toChar(PieceType type) {
        char result = ' ';
        switch (type) {
            case B:
                result = 'B';
                break;
            case b:
                result = 'b';
                break;
            case G:
                result = 'G';
                break;
            case g:
                result = 'g';
                break;
            case I:
                result = 'I';
                break;
            case i:
                result = 'i';
                break;
            case L:
                result = 'L';
                break;
            case l:
                result = 'l';
                break;
            case N:
                result = 'N';
                break;
            case n:
                result = 'n';
                break;
            case O:
                result = 'O';
                break;
            case o:
                result = 'o';
                break;
            case P:
                result = 'P';
                break;
            case p:
                result = 'p';
                break;
            case R:
                result = 'R';
                break;
            case r:
                result = 'r';
                break;
            case S:
                result = 'S';
                break;
            case s:
                result = 's';
                break;
            case Y:
                result = 'Y';
                break;
            case y:
                result = 'y';
                break;
        }
        return result;
    }

    public static PieceType fromChar(String placement) {
        char firstChar = placement.charAt(0);
        PieceType result = null;
        switch (firstChar) {
            case 'B':
                result = B;
                break;
            case 'b':
                result = b;
                break;
            case 'G':
                result = G;
                break;
            case 'g':
                result = g;
                break;
            case 'I':
                result = I;
                break;
            case 'i':
                result = i;
                break;
            case 'L':
                result = L;
                break;
            case 'l':
                result = l;
                break;
            case 'N':
                result = N;
                break;
            case 'n':
                result = n;
                break;
            case 'O':
                result = O;
                break;
            case 'o':
                result = o;
                break;
            case 'P':
                result = P;
                break;
            case 'p':
                result = p;
                break;
            case 'R':
                result = R;
                break;
            case 'r':
                result = r;
                break;
            case 'S':
                result = S;
                break;
            case 's':
                result = s;
                break;
            case 'Y':
                result = Y;
                break;
            case 'y':
                result = y;
                break;
        }
        return result;
    }

    public int getProtrusion() { return protrusion; }

    public PieceColour getColour() {
        return colour;
    }

    public int getSpineNum() {
        return spineNum;
    }
}
