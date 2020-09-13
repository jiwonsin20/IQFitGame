package comp1110.ass2;

import static comp1110.ass2.PieceColour.*;

// This class shows different types of pieces with different spine length and protrusion.
// Class created by Jiwon Sin

public enum PieceType {

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


    public final PieceColour colour;
    public final int protrusion;
    public final int spineNum;

    /**
     * Constructor for each PieceType.
     * Each piece will comprise of
     * - colour
     * - number of protrusions
     * - Length of spine
     *
     * @param colour Colour of the piece
     * @param protrusion Number of protrusions
     * @param spineNum Length of spine
     *
     * Code written by Jiwon Sin
     */

    PieceType(PieceColour colour, int protrusion, int spineNum) {
        this.colour = colour;
        this.protrusion = protrusion;
        this.spineNum = spineNum;
    }

    /**
     * This method takes in PieceType and returns the piece in char form
     *
     * @param type PieceType of the piece
     * @return char form of the piece eg. 'B' or 'r'
     *
     * Code written by Jiwon Sin
     */
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

    /**
     * This method takes in String value and returns the PieceType of that string
     * @param placement Strictly 4-character length String eg.B12S
     * @return PieceType of the placement string
     *
     * Code written by Jiwon Sin
     */
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
