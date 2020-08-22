package comp1110.ass2;

import static comp1110.ass2.PieceColour.*;

public enum PieceType {

    // Changed name from Colour to PieceType because its not the colour that matters
    // what matters is whether it has multiple protrusion or single

    B(blue, 2), b(blue, 1),
    G(green, 2), g(green, 1),
    I(indigo, 2), i(indigo, 1),
    L(limegreen, 2), l(limegreen, 1),
    N(navyblue, 2), n(navyblue, 1),
    O(orange, 2), o(orange, 1),
    P(purple, 2), p(purple, 1),
    R(red, 2), r(red, 1),
    S(skyblue, 2), s(skyblue, 1),
    Y(yellow, 2), y(yellow, 1);

    // colour can just be used in constructor
    // Jiwon 22/08/20
    private final PieceColour colour;
    private int protrusion;

    PieceType(PieceColour colour, int protrusion) {
        this.colour = colour;
        this.protrusion = protrusion;
    }





    // Uppercase : Double protrusion
    // Lowercase : Single protrusion

    // Methods that can be implemented






    // method: getProtrusion()
    // return: char('1') char('2')

    //toString




    //
}
