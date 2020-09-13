package comp1110.ass2;

// This class defines the direction of all pieces, in N,E,S,W format.
// Class created and written by Jiwon Sin

public enum PieceDirection {
    NORTH('↑'), SOUTH('↓'), EAST('→'), WEST('←');

    public char symbol;


    /**
     * Constructor for piece's direction
     * @param symbol The symbol (in direction) of the piece
     *
     * Code written by Jiwon Sin
     */
    PieceDirection(char symbol) {
        this.symbol = symbol;
    }

    /**
     * This method returns the direction of the piece in char form.
     * @param dir Direction of the piece in terms of PieceDirection
     * @return Direction in char form
     *
     * Code written by Jiwon Sin
     */
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

    /**
     * This method is reverse of getChar method.
     * Takes in the character and returns
     * @param c character c of direction
     * @return Value of direction in terms of PieceDirection
     *
     * Code written by Jiwon Sin
     */

    public static PieceDirection fromChar(char c) {
        PieceDirection dir = null;
        switch (c) {
            case 'N':
                dir = NORTH;
                break;
            case 'E':
                dir = EAST;
                break;
            case 'S':
                dir = SOUTH;
                break;
            case 'W':
                dir = WEST;
                break;
            default:
                break;
        }
        return dir;
    }

    /**
     * From the placement string, which is 4 character long, returns the direction
     *
     * @param placement 4-word length String eg. "B12S"
     * @return Direction of the piece, as written from the placement string.
     *
     * Code written by Jiwon Sin
     */

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
}
