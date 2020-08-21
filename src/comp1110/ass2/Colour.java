package comp1110.ass2;

public enum Colour {
    Y("yellow", 2), y("yellow", 1),
    B("blue", 2), b("blue", 1),
    G("green", 2), g("green", 1),
    I("indigo", 2), i("indigo", 1),
    L("limegreen", 2), l("limegreen", 1),
    N("navyblue", 2), n("navyblue", 1),
    O("orange", 2), o("orange", 1),
    P("purple", 2), p("purple", 1),
    R("red", 2), r("red", 1),
    S("skyblue", 2), s("skyblue", 1);

    private final String colour;
    private final int protrusion;
    // Uppercase : Double protrusion
    // Lowercase : Single protrusion

    // Methods that can be implemented




    Colour (String colour, int protrusion) {
        this.colour = colour;
        this.protrusion = protrusion;
    }

    // method: getProtrusion()
    // return: char('1') char('2')

    //toString




    //
}
