package comp1110.ass2;

public enum PieceDirection {
    NORTH('↑'), SOUTH('↓'), EAST('→'), WEST('←');

    // Changed symbol from character to appropriate direction symbols
    // Jiwon 22/08

    public char symbol;


    // Constructor for direction
    PieceDirection(char symbol) {
        this.symbol = symbol;
    }

    // methods

    // method 1 : private char getChar()
    // Need this to consolidate characters for the solution
    // Done by Jiwon: 22/08

    public static char getChar(PieceDirection dir) {
        if (dir == NORTH)
            return 'N';
        else if (dir == SOUTH)
            return 'S';
        else if (dir == EAST)
            return 'E';
        else if (dir == WEST)
            return 'W';
        else
            return ' ';
    }

    // Done by Jiwon: 22/08

    public static PieceDirection getDirection(String placement) {
        PieceDirection dir;
        char c = placement.charAt(3);
        switch (c) {
            case 'N':
                dir = NORTH;
                break;
            case 'S':
                dir =  SOUTH;
                break;
            case 'W':
                dir = WEST;
                break;
            case 'E':
                dir = EAST;
                break;
            default:
                dir = null;
        }
        return dir;
    }

    // method 2 : rotate()
    // parameter : current direction of the piece
    // return : another direction that the player desires.
    //Mingxuan Wang 08/23

    public char rotate(char symbol){
        if(symbol=='↑')
            symbol = '→';
        else if(symbol =='→')
            symbol = '↓';
        else if(symbol == '↓')
            symbol = '←';
        return symbol;
    }

    // method 3: isValidRotation()
    // returns true/false on whether the protrusion is not facing upwards


    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "symbol=" + symbol +
                '}';
    }
}
