package comp1110.ass2;

public class AssignmentSkeleton {
    /* This class is temporarily created to present wed-11-d Group's plan regarding the assignment.

       Printer-friendly version of this can be found in the repository.

       Members: Mingxuan Wang (u7202260), Di Mou (u6553212) and Jiwon Sin (u5666033)

       The main objective of this assignment is to create a simple application that visualises
       IQ Fit board game, made by SmartGames.

       After the first meeting on Friday (21/08/20), few basic ideas were laid out.

       First of all, GameBoard class, which establishes common rules of this game.

       Next, we decided to create Pieces class.
       Four main properties can define a class.
       They are
        - Colour of piece
        - Coordinates of a selected piece (both x and y coordinates),
        - Direction of a selected piece (whether N,S,E,W),
        - and Type of the piece (colour, number of protrusions and spine length).

       PieceColour, PieceDirection and PieceType classes are set as enum class as it will represent
       different pieces, which will form a single Piece.


       Further explanation of each classes

       1. PieceColour
        - This class represents different colours of the pieces, from blue to yellow colour.
        - Initially, we thought 10 colours were sufficient but to represent empty spaces, we added noPiece as one of its
          components.
        - PieceColour will be used by PieceType to create different shapes.
        - List of methods implemented:
            -> None at the moment, since its just merely an enum class with piece colours.

       2. PieceCoordinates
        - Upon looking at assignment specifications, it was evident that coordinates of each pieces are needed.
        - PieceCoordinates will represent the piece's position on the board itself.
        - List of methods implemented:
            -> getXCoord() : returns the int value of x coordinate of a piece.
            -> getYCoord() : returns the int value of y coordinate of a piece.

       3. PieceType
        - This class defines the specific properties of each pieces' number of protrusion, colour and spine length.
        - Initially, we wanted to put everything into Piece class only, but it seems like it could get complicated quickly.
        - Instead, Piece class will define the main properties of each Piece.
            -> For example, "r00N" has three main types, which are (1) Piece's type, (2) x and y coordinates
               and (3) the direction the piece is facing.
            -> Hence, it would be easier to make a separate class that defines Piece type, which comprises of
               (1) colour, (2) number of protrusions and (3) spine length.
        - List of methods implemented:
            -> getChar(PieceType type) : converts PieceType to a char type. For example, if PieceType.B, then return 'B'.
            -> fromChar(String placement) : converts placement (such as "r00N") to PieceType (for example, PieceType.r).
            -> getProtrusion() : returns number of protrusion.
            -> getColour : returns the colour of the piece.
            -> getSpineNum : returns the length of spine (whether 3 or 4).

       4. PieceDirection
        - This enum class defines the direction in which the piece is facing.
        - Divided into North, East, South, West.
        - PieceDirection class will be incorporated into Piece.
        - List of methods implemented:
            -> getChar: returns the char value that is equivalent to PieceDirection.
            -> getDirection: returns the Direction (NORTH, EAST, SOUTH, WEST).
            -> rotate: changes the symbol of direction (needs more clarification with group member).

       5. Piece
        - The main class that creates Piece objects.
        - Each Piece will have 3 components that are described from above.
        - List of methods that needs to be implemented
            -> toPiece(String placement) : Converts the string component, whether length == 4 or longer into Piece objects.
            -> getXCoordinate(String placement) : returns the value of Y coordinate of this piece.
            -> getYCoordinate(String placement) : returns the value of X coordinate of this piece.
            -> updatesPiece : When a piece is placed on the board, left-topmost coordinate is recorded and updates the
                              rest of the Piece's coordinate.
            -> toPlacement : converts the Piece object into a String placement.
            -> rotate() : changes the direction of the piece.
            -> changeProtrusion() : changes the current protrusion from 1 ot 2 or vice versa.
            -> changePiece() : (This method is completely optional) Change from one piece to another.
            -> getSpaceOccupied() : Checks the coordinates the piece occupies. Every time a piece is placed, there are
                                    empty spaces. This method updates the board on whether placement of specific piece
                                    occupies which coordinates.
        - Personal Thoughts:
            -> Would it be better by just making Piece class as an abstract class and make 10 different subclasses
               that has same methods implemented? (such as blue ~ yellow class)

       6. DifficultyLevel - Optional, Not in full discussion yet -
        - This class is to set the difficulties of each level.
        - After much research on web, the game objectives were divided into 5 different levels.
        - Starter -> Junior -> Expert -> Master -> Wizard.
        - Still on debate on whether to separate this into an independent class or just add as a method within the
          board class.
        - If it does exist in independent class, it will divide the Games into five different levels and player can
          choose the difficulty level.
        - Also considering adding a method that gives the random number within that difficulty level.

       7. GameBoard
        - The class that will have methods that determines game mechanics.
        - Methods that could be implemented:
            -> initialBoard() : displays initial empty board, by using multidimensional arrays.
            -> importBoard() : Considering the challenges comes with already placed pieces, change the board state to
                               suit the needs.
            -> updateBoard() : updates the board status whenever called. Its main purpose is to update the board when
                               the player placed the piece or removed a piece from the board. If the initialBoard()'s
                               array type is in PieceType, then changes from nP to whatever colour of the Piece is.
                               If initialBoard array is in int type, it will be in coordinates (from (0,0) to (9,4)).
            -> changeState() : changes the board's state from nP(noPiece) to colour and vice versa.
            -> getSolution() : returns the String type solution of current game.
            -> clearBoard() : Clears entire board to initialBoard/importBoard state.
            -> checkOverlap() : this method checks if an array of coordinates (of each pieces) overlaps another.

       8. Designs about GUI
        - This part hasn't been discussed yet.
        - Ideally, would like to add buttons to rotate the pieces. (like when a specific piece is clicked, and by pressing
          rotate right / rotate left buttons, it will change directions of the piece.
            -> or detect mouse right click button to rotate!
        - If DifficultyLevel class is implemented, it would be good idea to include a bar that changes difficulty level.
        - Placement count could be implemented to see how quick user can clear the level (by counting the number of times
          a piece has been moved).

     */
}
