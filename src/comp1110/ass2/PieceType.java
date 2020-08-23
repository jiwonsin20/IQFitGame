package comp1110.ass2;

import static comp1110.ass2.PieceColour.*;

public enum PieceType {

    // Changed name from Colour to PieceType because its not the colour that matters
    // what matters is whether it has multiple protrusion or single
    // Added spine number
    // Jiwon 22/08

    B(blue, 2,4), b(blue, 1,4),
    G(green, 2,3), g(green, 1,3),
    I(indigo, 2,3), i(indigo, 1,3),
    L(limegreen, 2,3), l(limegreen, 1,3),
    N(navyblue, 2,3), n(navyblue, 1,3),
    O(orange, 2,4), o(orange, 1,4),
    P(purple, 2,4), p(purple, 1,4),
    R(red, 2,4), r(red, 1,4),
    S(skyblue, 2,4), s(skyblue, 1,4),
    Y(yellow, 2,4), y(yellow, 1,4);


    // colour can just be used in constructor
    // Jiwon 22/08/20

    private final PieceColour colour;
    private  int protrusion;//protrusion is changed, so it can't be final, Mingxuan Wang 08/23
    private final int spineNum;

    PieceType(PieceColour colour, int protrusion, int spineNum) {
        this.colour = colour;
        this.protrusion = protrusion;
        this.spineNum = spineNum;
    }

    public char toChar(PieceType type) {
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

    // Added three methods that will get the protrusion value, colour and number of circles of spines
    // Jiwon 22/08
    public int getProtrusion(PieceType Type) {
        switch(Type){
            case B: protrusion =2;break;
            case b: protrusion =1;break;
            case G: protrusion =2;break;
            case g: protrusion =1;break;
            case I: protrusion =2;break;
            case i: protrusion =1;break;
            case L: protrusion =2;break;
            case l: protrusion =1;break;
            case N: protrusion =2;break;
            case n: protrusion =1;break;
            case O: protrusion =2;break;
            case o: protrusion =1;break;
            case P: protrusion =2;break;
            case p: protrusion =1;break;
            case R: protrusion =2;break;
            case r: protrusion =1;break;
            case S: protrusion =2;break;
            case s: protrusion =1;break;
            case Y: protrusion =2;break;
            case y: protrusion =1;break;
        }
        return protrusion;
    }
    //Mingxuan Wang 08/23

    public PieceColour getColour() {
        return colour;
    }

    public int getSpineNum() {
        return spineNum;
    }

    // Uppercase : Double protrusion
    // Lowercase : Single protrusion

    // Methods that can be implemented

    // method: getProtrusion()
    // return: char('1') char('2')

    //toString

    //
}
