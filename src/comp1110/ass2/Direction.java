package comp1110.ass2;

public enum Direction {
    NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');

    final private char symbol;


    // Constructor for direction
    Direction (char symbol) {
        this.symbol = symbol;
    }

    // methods

    // method 1 : private char getChar()
    // Need this to consolidate characters for the solution

    // method 2 : rotate()
    // parameter : current direction of the piece
    // return : another direction that the player desires.

    // method 3: isValidRotation()
    // returns true/false on whether the protrusion is not facing upwards



    @Override
    public String toString() {
        return "Direction{" +
                "symbol=" + symbol +
                '}';
    }
}
