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
    public static char getChar(int dir) {
        if (dir == 1)
            return 'N';
        else if (dir == 2)
            return 'E';
        else if (dir == 3)
            return 'S';
        else if (dir == 4)
            return 'W';
        else
            return ' ';
    }

    /**
     * This method is reverse of getChar method.
     * Takes in the character and returns
     * @param c character c of direction
     * @return Value of direction in terms of int.
     * eg.North = 0, East = 1 South = 2, West = 3
     *
     * Code written by Jiwon Sin
     */

    public static int fromChar(char c) {
        int dir = 0;
        switch (c) {
            case 'N':
                dir = 1;
                break;
            case 'E':
                dir = 2;
                break;
            case 'S':
                dir = 3;
                break;
            case 'W':
                dir = 4;
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

    public char rotate(char symbol){
        if(symbol=='↑')
            symbol = '→';
        else if(symbol =='→')
            symbol = '↓';
        else if(symbol == '↓')
            symbol = '←';
        return symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}
