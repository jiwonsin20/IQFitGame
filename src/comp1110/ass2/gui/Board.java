package comp1110.ass2.gui;

// Entire Board Application created by Jiwon Sin, edited by Jiwon Sin, Di Mou, Mingxuan Wang

import comp1110.ass2.*;

import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.Piece.*;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.*;
import java.util.List;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 50;
    private static final int GRID_L_PADDING = 48;
    private static final int GRID_TOP_PADDING = 25;
    private static final int PLAYABLE_AREA_X = 10 * SQUARE_SIZE + GRID_L_PADDING * 2;
    private static final int PLAYABLE_AREA_Y = 275;
    private static final int START_Y = BOARD_HEIGHT - 6 * SQUARE_SIZE -25;
    private static final Text completingText = new Text("Good Job!");

    private static final ComboBox difficultyBox = new ComboBox();

    // This part is for board image
    private static final String URI_BASE = "assets/";
    private static final String BOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();
    private static final String TITLE_ICON = Board.class.getResource(URI_BASE + "timg.jpeg").toString();

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group gameBoard = new Group();
    private final Group gamePiece = new Group();
    private final Group gameHintPiece = new Group();
    private final Group backgroundImage = new Group();

    private static boolean isSlashKeyPressed = false;
    private static final List<String> addedPieces = new ArrayList<>();

    private static String objective;
    private static String solution;

    private static final PieceType[][] initialBoard = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
    };

    /**
     * This method sets the board for the game GUI
     * First, the background image is set then other pieces that are present in placement string is placed on top.
     *
     * @param placement : Objective String selected in random
     *
     * Code Written by Jiwon Sin
     */

    void setBoard(String placement) {
        String [] pieces = new String[placement.length()/4];
        int j = 0;
        int xValue;
        int yValue;
        int orientation;

        gameBoard.getChildren().clear();

        ImageView board = new ImageView();
        board.setImage(new Image(BOARD_URI));
        board.setFitWidth(600);
        board.setPreserveRatio(true);
        gameBoard.getChildren().add(board);

        for (int i = 0; i < placement.length(); i+=4) {
            pieces[j] = placement.substring(i, i + 4);
            j++;
        }

        for (String piece : pieces) {
            char c = piece.charAt(0);
            String identifier = "1";
            orientation = PieceDirection.fromChar(piece.charAt(3));


            if (Character.isUpperCase(c))
                identifier = "2";

            xValue = Character.getNumericValue(piece.charAt(1)) * SQUARE_SIZE + GRID_L_PADDING;
            yValue = Character.getNumericValue(piece.charAt(2)) * SQUARE_SIZE + GRID_TOP_PADDING;
            ImageView pieceImage = new ImageView(new Image(getClass().getResource(URI_BASE + Character.toUpperCase(c) + identifier + ".png").toString()));

            if (getPieceSpineNum(piece) == 3)
                pieceImage.setFitWidth(SQUARE_SIZE * 3);
            else
                pieceImage.setFitWidth(SQUARE_SIZE * 4);

            pieceImage.setPreserveRatio(true);
            pieceImage.setRotate((orientation - 1) * 90);
            pieceImage.setLayoutX(xValue);
            pieceImage.setLayoutY(yValue);


            switch (orientation) {
                case 1: // N
                    if (getPieceSpineNum(piece) == 3) {
                        pieceImage.setTranslateX(1);
                        pieceImage.setTranslateY(2);
                    } else {
                        pieceImage.setTranslateX(0);
                        pieceImage.setTranslateY(3);
                    }
                    break;
                case 2: // E
                    if (getPieceSpineNum(piece) == 3) {
                        pieceImage.setTranslateX(-SQUARE_SIZE / 2f - 1);
                        pieceImage.setTranslateY(SQUARE_SIZE / 2f + 1);
                    } else {
                        pieceImage.setTranslateX(-50);
                        pieceImage.setTranslateY(SQUARE_SIZE + 2);
                    }
                    break;
                case 3: // S
                    if (getPieceSpineNum(piece) == 3) {
                        pieceImage.setTranslateX(-1);
                        pieceImage.setTranslateY(1);
                    }
                    break;
                case 4: // W
                    if (getPieceSpineNum(piece) == 3) {
                        pieceImage.setTranslateX(-SQUARE_SIZE / 2f - 1);
                        pieceImage.setTranslateY(SQUARE_SIZE / 2f + 1);
                    } else {
                        pieceImage.setTranslateX(-49);
                        pieceImage.setTranslateY(SQUARE_SIZE);
                    }
                    break;
                default:
                    break;
            }
            FitGame.boardUpdate(placement, initialBoard);
            gameBoard.getChildren().add(pieceImage);
        }
        FitGame.boardUpdate(placement, initialBoard);
    }

    /**
     * This method returns the number of piece's spine
     * @param piece : Piece placement
     * @return length of spine for that particular piece
     *
     * Code Written by Jiwon Sin
     */

    private static int getPieceSpineNum(String piece) {
        return toPiece(piece).type.getSpineNum();
    }

    /**
     * This class represents the pieceTile that will be added into the game.
     * Class written by Jiwon Sin
     */

    static class PieceTile extends ImageView {
        String pieceID;

        PieceTile(String piece) {
            char [] pieceArray = {'b','g','i','l','n','o','p','r','s','y'};
            char [] pieceArrayUp = {'B','G','I','L','N','O','P','R','S','Y'};
            for (int i = 0; i < pieceArray.length; i++){
                if ((piece.charAt(0) == pieceArray[i] || piece.charAt(0) == pieceArrayUp[i])) {
                    break;
                }
                else {
                    if (i == 9) {
                        throw new IllegalArgumentException("Bad piece: " + piece + " at " + i);
                    }
                }
            }
            this.pieceID = piece;
            setFitWidth(SQUARE_SIZE * fromChar(piece).getSpineNum());
            setPreserveRatio(true);
        }
    }

    /**
     * This class produces draggable pieces, with snapping positions
     * Class written by Jiwon Sin
     */

    class DraggablePiece extends PieceTile {
        double homeX, homeY;
        double mouseX, mouseY;
        int orientation; // 0 = NORTH, 1 = EAST 2 = SOUTH 3 = WEST
        int positionX, positionY;
        char type;

        DraggablePiece(String placement) {
            super(placement);
            type = placement.charAt(0);
            orientation = 1;
            char piece = placement.charAt(0);

            Image pieceImage;

            positionX = Character.getNumericValue(placement.charAt(1));
            positionY = Character.getNumericValue(placement.charAt(2));

            if (Character.isLowerCase(type))
                pieceImage = new Image(getClass().getResource(URI_BASE + Character.toUpperCase(placement.charAt(0)) +"1.png").toString());
            else
                pieceImage = new Image(getClass().getResource(URI_BASE + (placement.charAt(0)) +"2.png").toString());
            setImage(pieceImage);

            switch (Character.toUpperCase(piece)) {
                case 'B':
                    homeX = 0;
                    homeY = START_Y;
                    break;
                case 'O':
                    homeX = SQUARE_SIZE * 4 - 1;
                    homeY = START_Y;
                    break;
                case 'R':
                    homeX = SQUARE_SIZE * 8;
                    homeY = START_Y;
                    break;
                case 'I':
                    homeX = SQUARE_SIZE * 12;
                    homeY = START_Y;
                    break;
                case 'N':
                    homeX = 0;
                    homeY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'G':
                    homeX = SQUARE_SIZE * 3 - 1;
                    homeY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'P':
                    homeX = SQUARE_SIZE * 6 - 1;
                    homeY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'Y':
                    homeX = SQUARE_SIZE * 10;
                    homeY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'S':
                    homeX = SQUARE_SIZE * 14;
                    homeY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'L':
                    homeX = SQUARE_SIZE * 15;
                    homeY = START_Y;
                    break;
            }
            setLayoutX(homeX);
            setLayoutY(homeY);

        // Handling events

            setOnMousePressed(mouseEvent -> {
                mouseX = mouseEvent.getSceneX();
                mouseY = mouseEvent.getSceneY();
                toFront();
                if (FitGame.isPlacementValid(pieceID)) {
                    clearInitialBoard(pieceID);
                    Board.addedPieces.remove(pieceID);
                }
                mouseEvent.consume();
            });

            setOnMouseDragged(mouseEvent -> {
                double movementX = mouseEvent.getSceneX() - mouseX;
                double movementY = mouseEvent.getSceneY() - mouseY;
                setLayoutX(getLayoutX() + movementX);
                setLayoutY(getLayoutY() + movementY);
                mouseX = mouseEvent.getSceneX();
                mouseY = mouseEvent.getSceneY();
                mouseEvent.consume();
            });

            setOnScroll(scrollEvent -> {
                if (!isPieceOnBoard()) {
                    setRotate((orientation) * 90);
                    orientation++;
                    setFitWidth(getPieceSpineNum(pieceID) * SQUARE_SIZE);
                    setPreserveRatio(true);
                    if (orientation == 5)
                        orientation = 1;
                }
            });

            setOnMouseReleased(mouseEvent -> {
                updatePieceID();
                if (FitGame.isPlacementValid(pieceID)) {
                    if (FitGame.isPlacementNotOverlapping(initialBoard, pieceID)) {
                        snapToGrid();
                        FitGame.boardUpdate(pieceID, initialBoard);
                        Board.addedPieces.add(pieceID);
                        if (isItComplete()) {
                            showCompletionText();
                            makeCompletionText();
                        }
                    }
                    else {
                        setLayoutX(homeX);
                        setLayoutY(homeY);
                    }
                }
                else {
                    setLayoutX(homeX);
                    setLayoutY(homeY);
                }
                mouseEvent.consume();
            });
        }

        /**
         * Clears the specific placement from the board
         * @param placement : String that needs to be removed from the board
         *
         * Code Written by Jiwon Sin
         */

        private void clearInitialBoard(String placement) {
            int xLocation = Character.getNumericValue(placement.charAt(1));
            int yLocation = Character.getNumericValue(placement.charAt(2));

            Piece piece = toPiece(placement);

            if (FitGame.isPlacementValid(placement)) {
                if (orientation == 1 || orientation == 3) {
                    for (int i = xLocation; i < xLocation + getPieceSpineNum(placement); i++) {
                        for (int j = yLocation; j < yLocation + 2; j++) {
                            if (Board.initialBoard[j][i] == piece.getType())
                                Board.initialBoard[j][i] = null;
                        }
                    }
                } else { // West or East
                    for (int i = xLocation; i < xLocation + 2; i++) {
                        for (int j = yLocation; j < 5; j++) {
                            if (Board.initialBoard[j][i] == piece.getType())
                                Board.initialBoard[j][i] = null;
                        }
                    }
                }
            }
        }

        /**
         * Updates Piece's placement by obtaining x Values and y Values with orientation
         * Code Written by Jiwon Sin
         */

        private void updatePieceID() {
            int xValue = getPositionX(snapXToGrid(getLayoutX()));
            int yValue = getPositionY(snapYToGrid(getLayoutY()));
            pieceID = type + Integer.toString(xValue) + yValue + PieceDirection.getChar(orientation);
        }

        /**
         * Checks whether the piece spine length = 3
         * @param piece : Piece String
         * @return True if its length = 3. Else false
         *
         * Code Written by Jiwon Sin
         */

        private boolean isThreeByTwo(String piece) {
            return getPieceSpineNum(piece) == 3;
        }

        /**
         * Puts selected piece onto the board
         * Code Written by Jiwon Sin
         */

        private void snapToGrid() {
            snapLayoutToGrid(snapXToGrid(getLayoutX()), snapYToGrid(getLayoutY()));
        }

        /**
         * Makes 'snapping' animation
         *  - if x or y values are within certain range, the value sets into place
         * @param x : x value on GUI
         * @param y : y value on GUI
         *
         * Code Written by Jiwon Sin
         */

        private void snapLayoutToGrid(double x, double y) {
            if (getPositionX(x) <= 9 && getPositionY(y) <= 4) {
                setLayoutX(x);
                setLayoutY(y);
            }
            else {
                setLayoutX(homeX);
                setLayoutY(homeY);
            }
        }

        /**
         * Converts x value to appropriate snapping position
         * @param x : x coordinate within the game itself
         * @return Integer values converted
         *
         * Code Written by Jiwon Sin
         */

        private int getPositionX(double x) {
            if (x != homeX) {
                if (isThreeByTwo(pieceID)) {
                    if (orientation == 1 || orientation == 3)
                        return (int) x / (SQUARE_SIZE + 1);
                    else
                        return (int) x / SQUARE_SIZE;
                } else {
                    if (orientation == 1 || orientation == 3) {
                        return (int) x / (SQUARE_SIZE + 3);
                    } else
                        return (int) x / (SQUARE_SIZE - 5);
                }
            }
            else
                return (int) homeX;
        }

        /**
         * Converts y value to appropriate snapping position
         * @param y : y Coordinate within the game itself
         * @return Integer values converted (different regarding orientation of the piece)
         *
         * Code Written by Jiwon Sin
         */

        private int getPositionY(double y) {
            if (y != homeY) {
                if (isThreeByTwo(pieceID)) {
                    if (orientation == 1 || orientation == 3) {
                        return (int) y / SQUARE_SIZE;
                    } else {
                        return (int) y / (SQUARE_SIZE + 10);
                    }
                } else {
                    if (orientation == 1 || orientation == 3) {
                        return (int) y / (SQUARE_SIZE + 1);
                    } else {
                        return (int) y / (SQUARE_SIZE + 26);
                    }
                }
            }
            else
                return (int) homeY;
        }

        /**
         * Checks whether the piece gets out of the 10 X 5 board
         * @return True if it isn't, False if it is.
         *
         * Code Written by Jiwon Sin
         */

        private boolean isPieceOnBoard() {
            double xMax;
            double yMax;
            if (getLayoutX() >= 0 && getLayoutX() <= PLAYABLE_AREA_X && getLayoutY() >= 0 && getLayoutY() <= PLAYABLE_AREA_Y) {
                if (isThreeByTwo(pieceID)) {
                    if (orientation % 2 == 0) {
                        xMax = getLayoutX() + SQUARE_SIZE * 2;
                        yMax = getLayoutY() + SQUARE_SIZE * 3;
                    }
                    else {
                        xMax = getLayoutX() + SQUARE_SIZE * 3;
                        yMax = getLayoutY() + SQUARE_SIZE * 2;
                    }
                }
                else {
                    if (orientation % 2 == 0) {
                        xMax = getLayoutX() + SQUARE_SIZE * 2;
                        yMax = getLayoutY() + SQUARE_SIZE * 4;
                    }
                    else {
                        xMax = getLayoutX() + SQUARE_SIZE * 4;
                        yMax = getLayoutY() + SQUARE_SIZE * 2;
                    }
                }
                return !(xMax > PLAYABLE_AREA_X) && yMax != PLAYABLE_AREA_Y;
            }
            else
                return false;
        }

        /**
         * By using x coordinate value obtained through dragging, returns converted value of X
         * @param xLayout : x coordinate obtained by dragging and releasing
         * @return
         *
         * Code Written by Jiwon Sin
         */

        private double snapXToGrid(double xLayout) {
            if (isThreeByTwo(pieceID)) {
                if (orientation == 1 || orientation == 3) { // North and South
                    if (xLayout >= 20 && xLayout < SQUARE_SIZE + 19)
                        return SQUARE_SIZE - 3;
                    else if (xLayout >= SQUARE_SIZE + 19 && xLayout < 2 * SQUARE_SIZE + 19)
                        return SQUARE_SIZE * 2 - 5;
                    else if (xLayout >= SQUARE_SIZE * 2 + 19 && xLayout < 3 * SQUARE_SIZE + 19)
                        return SQUARE_SIZE * 3;
                    else if (xLayout >= SQUARE_SIZE * 3 + 19 && xLayout < 4 * SQUARE_SIZE + 19)
                        return SQUARE_SIZE * 4;
                    else if (xLayout >= SQUARE_SIZE * 4 + 19 && xLayout < 5 * SQUARE_SIZE +19)
                        return SQUARE_SIZE * 5;
                    else if (xLayout >= SQUARE_SIZE * 5 + 19 && xLayout < 6 * SQUARE_SIZE +19)
                        return SQUARE_SIZE * 6;
                    else if (xLayout >= SQUARE_SIZE * 6 + 19 && xLayout < 7 * SQUARE_SIZE +19)
                        return SQUARE_SIZE * 7;
                    else if (xLayout >= SQUARE_SIZE * 7 + 19 && xLayout < 8 * SQUARE_SIZE + 19)
                        return SQUARE_SIZE * 8 + 2;
                    else
                        return homeX;
                }
                else { //East and West
                    if (xLayout >= -5 && xLayout < SQUARE_SIZE * 0.5 + 20)
                        return 20;
                    else if (xLayout >= SQUARE_SIZE * 0.5 + 20 && xLayout < 1.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 1.5 - 5;
                    else if (xLayout >= 1.5 * SQUARE_SIZE + 20 && xLayout < 2.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 2.5 - 5;
                    else if (xLayout >= 2.5 * SQUARE_SIZE + 20 && xLayout < 3.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 3.5 - 5;
                    else if (xLayout >= 3.5 * SQUARE_SIZE + 20 && xLayout < 4.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 4.5 - 2;
                    else if (xLayout >= 4.5 * SQUARE_SIZE + 20 && xLayout < 5.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 5.5 + 2;
                    else if (xLayout >= 5.5 * SQUARE_SIZE + 20 && xLayout < 6.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 6.5 + 2;
                    else if (xLayout >= 6.5 * SQUARE_SIZE + 20 && xLayout < 7.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 7.5 + 2;
                    else if (xLayout >= 7.5 * SQUARE_SIZE + 20 && xLayout < 8.5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 8.5 + 3;
                    else
                        return homeX;
                }
            }
            else { // if piece is in 4 by 2 orientation
                if (orientation == 1 || orientation == 3) { //North and South
                    if (xLayout >= 20 && xLayout < SQUARE_SIZE + 20)
                        return 45;
                    else if (xLayout >= SQUARE_SIZE + 20 && xLayout < 2 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 2 - 5;
                    else if (xLayout >= 2 * SQUARE_SIZE + 20 && xLayout < 3 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 3 - 3;
                    else if (xLayout >= 3 * SQUARE_SIZE + 20 && xLayout < 4 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 4;
                    else if (xLayout >= 4 * SQUARE_SIZE + 20 && xLayout < 5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 5 - 1;
                    else if (xLayout >= 5 * SQUARE_SIZE + 20 && xLayout < 6 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 6;
                    else if (xLayout >= 6 * SQUARE_SIZE + 20 && xLayout < 7 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 7 + 2;
                    else
                        return homeX;
                }
                else { // East and West
                    if (xLayout >= -30 && xLayout < 20)
                        return -5;
                    else if (xLayout >= 20 && xLayout < SQUARE_SIZE + 20)
                        return SQUARE_SIZE - 5;
                    else if (xLayout >= SQUARE_SIZE + 20 && xLayout < 2 * SQUARE_SIZE + 20)
                        return 2 * SQUARE_SIZE - 5;
                    else if (xLayout >= 2 * SQUARE_SIZE + 20 && xLayout < 3 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 3 - 2;
                    else if (xLayout >= 3 * SQUARE_SIZE + 20 && xLayout < 4 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 4 - 2;
                    else if (xLayout >= 4 * SQUARE_SIZE + 20 && xLayout < 5 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 5;
                    else if (xLayout >= 5 * SQUARE_SIZE + 20 && xLayout < 6 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 6;
                    else if (xLayout >= 6 * SQUARE_SIZE + 20 && xLayout < 7 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 7;
                    else if (xLayout >= 7 * SQUARE_SIZE + 20 && xLayout < 8 * SQUARE_SIZE + 20)
                        return SQUARE_SIZE * 8 + 2;
                    else
                        return homeX;
                }
            }
        }

        /**
         * This method converts y coordinate into respective values on the board itself
         * @param yLayout : y coordinate of the piece
         * @return y Value on the 10 X 5 board
         *
         * Code Written by Jiwon Sin
         */

        private double snapYToGrid (double yLayout) {
            if (isThreeByTwo(pieceID)) {
                if (orientation == 1 || orientation == 3) { //North and South
                    if (yLayout >= 0 && yLayout < SQUARE_SIZE)
                        return SQUARE_SIZE * 0.5;
                    else if (yLayout >= SQUARE_SIZE && yLayout < SQUARE_SIZE * 2 + 1)
                        return SQUARE_SIZE * 1.5 + 1;
                    else if (yLayout >= SQUARE_SIZE * 2 + 1 && yLayout < SQUARE_SIZE * 3 + 2)
                        return SQUARE_SIZE * 2.5 + 3;
                    else if (yLayout >= SQUARE_SIZE * 3 + 2 && yLayout < SQUARE_SIZE * 4 + 2)
                        return SQUARE_SIZE * 3.5 + 5;
                    else
                        return homeY;
                }
                else { //West and East
                    if (yLayout >= 30 && yLayout < 30 + SQUARE_SIZE)
                        return SQUARE_SIZE + 5;
                    else if (yLayout >= SQUARE_SIZE + 30 && yLayout < SQUARE_SIZE * 2 + 30)
                        return SQUARE_SIZE * 2 + 4;
                    else if (yLayout >= SQUARE_SIZE * 2 + 30 && yLayout < SQUARE_SIZE * 3 + 30)
                        return SQUARE_SIZE * 3 + 4;
                    else
                        return homeY;
                }
            }
            else { // Four by two piece
                if (orientation == 1 || orientation == 3) { // North and South
                    if (yLayout >= 0 && yLayout < SQUARE_SIZE)
                        return SQUARE_SIZE * 0.5;
                    else if (yLayout >= SQUARE_SIZE && yLayout < 2 * SQUARE_SIZE)
                        return SQUARE_SIZE * 1.5;
                    else if (yLayout >= 2 * SQUARE_SIZE && yLayout < 3 * SQUARE_SIZE)
                        return SQUARE_SIZE * 2.5 + 2;
                    else if (yLayout >= 3 * SQUARE_SIZE && yLayout < 4 * SQUARE_SIZE)
                        return SQUARE_SIZE * 3.5 + 4;
                    else
                        return homeY;
                }
                else { // West and East
                    if (yLayout >= SQUARE_SIZE && yLayout < SQUARE_SIZE * 2)
                        return SQUARE_SIZE * 1.5;
                    else if (yLayout >= SQUARE_SIZE * 2 && yLayout < SQUARE_SIZE * 3)
                        return SQUARE_SIZE * 2.5 + 2;
                    else
                        return homeY;
                }
            }
        }
    }

    /**
     * Checks whether the board has any empty spaces
     * @return True if all filled, False if there is at least 1 empty
     *
     * Code written by Jiwon Sin
     */

    private boolean isItComplete(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j ++) {
                if (Board.initialBoard[j][i] == null)
                    return false;
            }
        }
        return true;
    }

    /**
     * Puts up completionText and at the same time removes the board (signifies end of game)
     *
     * Code written by Jiwon Sin
     */

    private void showCompletionText(){
        completingText.toFront();
        gamePiece.setDisable(true);
        completingText.setOpacity(1);
    }

    /**
     * Constructs completionText
     */
    private void makeCompletionText() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(7.0);
        completingText.setEffect(dropShadow);
        completingText.setFill(Color.WHITE);
        completingText.setX(SQUARE_SIZE*2.25);
        completingText.setY(175);
        completingText.setTextAlignment(TextAlignment.CENTER);
        completingText.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 70));
        root.getChildren().add(completingText);
    }

    /**
     * Sets the background image of the game
     *
     * Image obtained from https://www.rawpixel.com/image/569984/blue-teal-sand-paper
     * Code Written by Jiwon Sin
     */

    private void backgroundImg() {
        ImageView background1 =new ImageView (new Image(Board.class.getResource("assets/background2.jpg").toString()));
        background1.setX(12*SQUARE_SIZE);
        background1.setY(0);
        background1.setFitWidth(933);
        background1.setFitHeight(7*SQUARE_SIZE);
        ImageView background2 =new ImageView (new Image(Board.class.getResource("assets/background2.jpg").toString()));
        background2.setX(0);
        background2.setY(5*SQUARE_SIZE);
        background2.setFitWidth(933);
        background2.setFitHeight(700);
        backgroundImage.getChildren().add(background1);
        backgroundImage.getChildren().add(background2);
        root.getChildren().add(backgroundImage);
        backgroundImage.toBack();
    }

    /**
     * Making controls for New Game, Resetting pieces
     * Code Written by Jiwon Sin, Di Mou, Mingxuan Wang
     */

    private void makeControls() {

        // Title of the game
        Text title = new Text("IQ - FIT GAME");
        title.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 30));
        title.setFill(Color.FLORALWHITE);
        title.setLayoutX(12.75 * SQUARE_SIZE);
        title.setLayoutY(SQUARE_SIZE * 1.25);
        controls.getChildren().add(title);

        // Sets the NEW GAME button
        Button newGame = new Button("NEW GAME");
        newGame.setLayoutX(12 * SQUARE_SIZE + 150);
        newGame.setLayoutY(2 * SQUARE_SIZE);
        newGame.setStyle("-fx-font: 15 Grotesque; -fx-base: #18ee11;");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chooseGame();
            }
        });

        // Sets the RESET button
        Button clear = new Button("RESET"); // CLEAR
        clear.setLayoutX(12*SQUARE_SIZE+50);
        clear.setLayoutY(2*SQUARE_SIZE);
        clear.setStyle("-fx-font: 15 Grotesque; -fx-base: #d91d0e;");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearBoard();
            }
        });

        controls.getChildren().add(clear);
        controls.getChildren().add(newGame);

        difficultyBox.getItems().addAll(
                "Starter", "Junior", "Expert", "Master", "Wizard"
        );
        difficultyBox.setLayoutX(15 * SQUARE_SIZE + 10);
        difficultyBox.setLayoutY(3*SQUARE_SIZE);
        difficultyBox.setMinWidth(150);
        difficultyBox.getSelectionModel().selectFirst();
        controls.getChildren().add(difficultyBox);

        // Sets "Difficulty" label
        final Label difficultyCaption = new Label("DIFFICULTY :");
        difficultyCaption.setTextFill(Color.WHITE);
        difficultyCaption.setFont(Font.font("Courier New",FontWeight.BOLD,20));
        difficultyCaption.setLayoutX(12*SQUARE_SIZE+10);
        difficultyCaption.setLayoutY(3*SQUARE_SIZE);
        controls.getChildren().add(difficultyCaption);

        // Sets "how to play" text
        Text tip0 = new Text("HOW TO PLAY");
        tip0.setFill(Color.WHITE);
        tip0.setFont(Font.font("Courier New",FontWeight.BOLD,20));
        tip0.setLayoutX(12 * SQUARE_SIZE + 10);
        tip0.setLayoutY(SQUARE_SIZE * 4 + 10);
        controls.getChildren().add(tip0);

        // Sets tip 1
        Text tip1 = new Text("1. Select the difficulty and click NEW GAME");
        tip1.setFill(Color.WHITE);
        tip1.setFont(Font.font("Verdana",FontWeight.NORMAL,14));
        tip1.setLayoutX(12 * SQUARE_SIZE + 10);
        tip1.setLayoutY(SQUARE_SIZE * 4 + 40);
        controls.getChildren().add(tip1);

        // Sets tip 2
        Text tip2 = new Text("2. Scroll to rotate pieces");
        tip2.setFill(Color.WHITE);
        tip2.setFont(Font.font("Verdana",FontWeight.NORMAL,14));
        tip2.setLayoutX(12 * SQUARE_SIZE + 10);
        tip2.setLayoutY(SQUARE_SIZE * 5 + 20);
        controls.getChildren().add(tip2);

        // Sets tip 3
        Text tip3 = new Text("3. Click RESET button to restart the game");
        tip3.setFill(Color.WHITE);
        tip3.setFont(Font.font("Verdana",FontWeight.NORMAL,14));
        tip3.setLayoutX(12 * SQUARE_SIZE + 10);
        tip3.setLayoutY(SQUARE_SIZE * 6);
        controls.getChildren().add(tip3);

        // Sets tip 4
        Text tip4 = new Text("4. Press '/' to get hints");
        tip4.setFill(Color.WHITE);
        tip4.setFont(Font.font("Verdana",FontWeight.NORMAL,14));
        tip4.setLayoutX(12 * SQUARE_SIZE + 10);
        tip4.setLayoutY(SQUARE_SIZE * 6 + 30);
        controls.getChildren().add(tip4);

    }

    /**
     * Sets up button for newGame with hints
     * @param objective : Objective string
     * @param solution : Solution string
     *
     * Code written by Jiwon Sin, Di Mou
     */
    private void newGame(String objective, String solution) {
        root.getChildren().remove(completingText);
        gameBoard.setOpacity(1);
        gamePiece.setDisable(false);
        gamePiece.setOpacity(1);
        setBoard(objective);
        FitGame.boardUpdate(objective, initialBoard);
        addedPieces.clear();
        isSlashKeyPressed = false;
        gameHintPiece.getChildren().clear();
        gamePiece.getChildren().clear();
        makePieces(setPlayablePieces(objective, solution));
    }

    /**
     * Resets the board (works with reset button)
     *
     * Code written by Jiwon Sin
     */

    private void resetBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                initialBoard[j][i] = null;
            }
        }
    }

    /**
     * This method is to implement reset.
     *
     * Code written by Jiwon Sin
     */

    private void clearBoard() {
        newGame(objective, solution);
        resetBoard();
        FitGame.boardUpdate(objective,initialBoard);
    }

    /**
     * Chooses the game randomly by taking the input from difficulty bar
     *
     * Code written by Jiwon Sin
     */

    private void chooseGame(){
        resetBoard();
        objective = chooseObjective((String)(difficultyBox.getValue()));
        solution = Games.getSolution(objective);
        newGame(objective, solution);
    }

    /**
     * Makes the pieces of the game.
     * The pieces that are placed on GUI will be initialised to 'N' direction.
     * User can then scroll to rotate the pieces.
     *
     * @param placement : placement of the piece
     *
     * Code written by Jiwon Sin
     */

    private void makePieces(String placement){
        for (int i = 0; i < placement.length(); i+=4) {
            String x = Character.toString(placement.charAt(i + 1));
            String y = Character.toString(placement.charAt(i + 2));
            gamePiece.getChildren().add(new DraggablePiece(placement.charAt(i) + x + y + "N"));
        }
        gamePiece.toFront();
    }

    /**
     * This method chooses the difficulty of the game.
     * The input will be the difficulty bar level, which will then by calculated.
     *
     * @param difficulty : Difficulty bar level
     * @return Game difficulty from 1 to 120
     *
     * Code written by Jiwon Sin
     */

    private String chooseObjective (String difficulty) {
        Random random = new Random();
        String currentDifficulty = "";
        int randomNumber = 0;
        if (difficulty != null) {
            switch (difficulty) {
                //difficulty set to Starter
                case "Starter":
                    randomNumber = random.nextInt(24);
                    break;
                case "Junior":
                    randomNumber = random.nextInt(48 - 24) + 24;
                    break;
                case "Expert":
                    randomNumber = random.nextInt(72 - 48) + 48;
                    break;
                case "Master":
                    randomNumber = random.nextInt(96 - 72) + 72;
                    break;
                case "Wizard":
                    randomNumber = random.nextInt(108 - 96) + 96;
                    break;
                default:
                    break;
            }
        }
        currentDifficulty = Games.getObjective(randomNumber);
        return currentDifficulty;
    }

    /**
     * Sets the playable piece by comparing the objective string and solution string.
     * Basically solution pieces - objective string = playable pieces.
     *
     * @param objective : objective string
     * @param solution : Solution string
     * @return playable pieces concatenated into string form.
     */

    private static String setPlayablePieces (String objective, String solution) {
        List<String> obj = new ArrayList<>(Arrays.asList(objective.split("(?<=\\G.{4})")));
        List<String> sol = new ArrayList<>(Arrays.asList(solution.split("(?<=\\G.{4})")));

        for (String value : obj) {
            sol.removeIf(value::equals);
        }
        String result = "";

        for (String values : sol) {
            result += values;
        }
        return result;
    }

    private void onKeyPressed(javafx.scene.input.KeyEvent e) {
        if (e.getCode() == KeyCode.SLASH) {
            if (!isSlashKeyPressed) {
                isSlashKeyPressed = true;
                ImpHints(objective, String.join("", addedPieces), solution);
            }
            e.consume();
        }
    }

    private void onKeyReleased(javafx.scene.input.KeyEvent e) {
        if (e.getCode() == KeyCode.SLASH) {
            isSlashKeyPressed = false;
            ImpHints(objective, "", "");
            e.consume();
        }
    }

    /**
     * Basically, if I hold down the '/' key, I should be pointed towards a placement that makes up part of the solution (if this is possible).
     **
     * @param challenge the game's challenge
     * @param placement the piece placement that the players has added
     * @param solution the correct solution to the challenge
     *
     * first, check whether the placement is the right subString of solution
     * then, find the next piece string in solution that need to be added
     * finally, highlight the piece and the position when the player hold down the '/' key
     *
     * Code written by Di Mou
     * Code edited by Mingxuan Wang
    */

    private void ImpHints(String challenge,String placement,String solution){
        if (solution.length() == 0) {
            gameHintPiece.getChildren().clear();
            return;
        }

        List<String> solutionPieces = new ArrayList<>();
        for (int i = 0; i < solution.length(); i += 4) {
            solutionPieces.add(solution.substring(i, i + 4));
        }
        List<String> placementPieces = new ArrayList<>();
        for (int i = 0; i < placement.length(); i += 4) {
            placementPieces.add(placement.substring(i, i + 4));
        }
        for (String piecePlacement : placementPieces) {
            if (!solutionPieces.contains(piecePlacement)) {
                System.out.print("Solution does not contain " + piecePlacement);
                return;
            }
        }

        List<String> challengePieces = new ArrayList<>();
        for (int i = 0; i < challenge.length(); i += 4) {
            challengePieces.add(challenge.substring(i, i + 4));
        }
        for (String piecePlacement : solutionPieces) {
            if (challengePieces.contains(piecePlacement) || placement.contains(piecePlacement)) {
                continue;
            }

            char colorChar = piecePlacement.charAt(0);
            char index = Character.isUpperCase(colorChar) ? '2' : '1';
            int orientation = PieceDirection.fromChar(piecePlacement.charAt(3));
            int xValue = Character.getNumericValue(piecePlacement.charAt(1)) * SQUARE_SIZE + GRID_L_PADDING;
            int yValue = Character.getNumericValue(piecePlacement.charAt(2)) * SQUARE_SIZE + GRID_TOP_PADDING;

            Image pieceImage = new Image(getClass().getResource(URI_BASE + Character.toUpperCase(colorChar) + index + ".png").toString());
            ImageView pieceImageView = new ImageView(pieceImage);
            pieceImageView.setFitWidth(SQUARE_SIZE * (getPieceSpineNum(piecePlacement) == 3 ? 3 : 4));
            pieceImageView.setPreserveRatio(true);
            pieceImageView.setRotate((orientation - 1) * 90);
            pieceImageView.setLayoutX(xValue);
            pieceImageView.setLayoutY(yValue);
            pieceImageView.setOpacity(0.5);

            switch (orientation) {
                case 1: // N
                    if (getPieceSpineNum(piecePlacement) == 3) {
                        pieceImageView.setTranslateX(1);
                        pieceImageView.setTranslateY(2);
                    } else {
                        pieceImageView.setTranslateX(0);
                        pieceImageView.setTranslateY(3);
                    }
                    break;
                case 2: // E
                    if (getPieceSpineNum(piecePlacement) == 3) {
                        pieceImageView.setTranslateX(-SQUARE_SIZE / 2f - 1);
                        pieceImageView.setTranslateY(SQUARE_SIZE / 2f + 1);
                    } else {
                        pieceImageView.setTranslateX(-50);
                        pieceImageView.setTranslateY(SQUARE_SIZE + 2);
                    }
                    break;
                case 3: // S
                    if (getPieceSpineNum(piecePlacement) == 3) {
                        pieceImageView.setTranslateX(-1);
                        pieceImageView.setTranslateY(1);
                    }
                    break;
                case 4: // W
                    if (getPieceSpineNum(piecePlacement) == 3) {
                        pieceImageView.setTranslateX(-SQUARE_SIZE / 2f - 1);
                        pieceImageView.setTranslateY(SQUARE_SIZE / 2f + 1);
                    } else {
                        pieceImageView.setTranslateX(-49);
                        pieceImageView.setTranslateY(SQUARE_SIZE);
                    }
                    break;
                default:
                    break;
            }
            gameHintPiece.getChildren().add(pieceImageView);
            break;
        }
    }

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    /**
     * Generate new challenges
     **
     * @param iNumber the original challenge's number
     *
     * first, get the original challenges and placement from the game
     * then separate the original challenges into Pieces
     * delete each Piece from the  placement
     * Finally, the left placement can be the new challenges
     * and the original challenges placement will be the solution
     * as the original challenges are unique, the complementary of them are also unique
     * Code written by Mingxuan Wang
     */
        public static String GenerateChallenges(int iNumber){
           String placement1;
           String placement2="";
           String objective;
           String[] s1 = new String[10];
           objective = Games.getObjective(iNumber);
           placement1 = Games.getSolution(objective);
            for (int j = 0; j <placement1.length()/4 ; j++) {
                for (int i = 0; i < objective.length(); i+=4) {
                    s1[j]=objective.substring(j,j+4);
                    placement2=placement1.replaceAll(s1[j],"");
                }
            }
            return placement2;
        }
        public void getNewChallenges(){
        String[] newChallenges = new String[120];
            for (int i = 1; i <121 ; i++) {
                newChallenges[i]=GenerateChallenges(i);
            }
        }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ-Fit Game");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(gameBoard);
        root.getChildren().add(gamePiece);
        root.getChildren().add(controls);


        GridPane gpTitle = new GridPane();
        gpTitle.setStyle("-fx-background-color: darkgray");

        objective = chooseObjective( "");
        solution = Games.getSolution(objective);

        backgroundImg();

        makeControls();

        setBoard(objective);
        FitGame.boardUpdate(objective, initialBoard);
        makePieces(setPlayablePieces(objective, solution));


        // Add hints (basically get the solution from Games.java and display it one by one with opacity of 0.5
        scene.setOnKeyPressed(e -> onKeyPressed(e));
        scene.setOnKeyReleased(e -> onKeyReleased(e));
        root.getChildren().add(gameHintPiece);

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(TITLE_ICON));
        primaryStage.show();
    }
}