package comp1110.ass2.gui;


import comp1110.ass2.*;

import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.Piece.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int SQUARE_SIZE = 50;
//    private static final int BOARD_MARGIN_X = 300;
//    private static final int BOARD_MARGIN_Y = 150;
    private static final int GRID_L_PADDING = 48;
    private static final int GRID_TOP_PADDING = 25;
    private static final int PLAYABLE_AREA_X = 10 * SQUARE_SIZE + GRID_L_PADDING * 2;
    private static final int PLAYABLE_AREA_Y = 5 * SQUARE_SIZE + GRID_TOP_PADDING * 2;
    private static final int START_X = 10;
    private static final int START_Y = BOARD_HEIGHT - 6 * SQUARE_SIZE;

    // This part is for board image
    private static final String URI_BASE = "assets/";
    private static final String BOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();

    private final Group root = new Group();
    private final Group board = new Group();
    private final Group gamePiece = new Group();

    private static PieceType[][] initialBoard = {
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null},
            {null, null, null, null, null, null, null, null, null, null}
    };

    void setBoard(String placement) {
        String [] pieces = new String[placement.length()/4];
        int j = 0;
        int xValue;
        int yValue;
        int orientation;

        board.getChildren().clear();

        ImageView board = new ImageView();
        board.setImage(new Image(BOARD_URI));
        board.setFitWidth(600);
        board.setPreserveRatio(true);

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
            gamePiece.getChildren().add(pieceImage);
        }

        root.getChildren().add(board);
    }

    private static int getPieceSpineNum(String piece) {
        return toPiece(piece).type.getSpineNum();
    }

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
//            setFitHeight(SQUARE_SIZE * toPiece(piece).getYDimensions());
        }
    }

    static class DraggablePiece extends PieceTile {
        double initialX, initialY;
        double homeX, homeY;
        double mouseX, mouseY;
        int orientation; // 0 = NORTH, 1 = EAST 2 = SOUTH 3 = WEST
        int positionX, positionY;
        char type;

        DraggablePiece(String placement) {
            super(placement);
            type = placement.charAt(0);
            orientation = 1;//PieceDirection.fromChar(placement.charAt(3));
            char piece = placement.charAt(0);
            boolean threeByTwo = piece == 'g' || piece == 'G' || piece == 'n' || piece == 'N' ||
                    piece == 'l' || piece == 'L' || piece == 'i' || piece == 'I';
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
                    initialX = PLAYABLE_AREA_X;
                    initialY = START_Y;
                    break;
                case 'G':
                    initialX = START_X;
                    initialY = START_Y + SQUARE_SIZE * 2.05;
                    break;
                case 'I':
                    initialX = START_X;
                    initialY = START_Y + SQUARE_SIZE * 4.1;
                    break;
                case 'L':
                    initialX = PLAYABLE_AREA_X;
                    initialY = START_Y;
                    break;
                case 'N':
                    initialX = START_X + SQUARE_SIZE * 4.5;
                    initialY = START_Y + SQUARE_SIZE * 2.05;
                    break;
                case 'O':
                    initialX = PLAYABLE_AREA_X;
                    initialY = START_Y + SQUARE_SIZE * 4.1;
                    break;
                case 'P':
                    initialX = PLAYABLE_AREA_X;
                    initialY = START_Y;
                    break;
                case 'R':
                    initialX = PLAYABLE_AREA_X;
                    initialY = START_Y + SQUARE_SIZE * 2.05;
                    break;
                case 'S':
                    initialX = PLAYABLE_AREA_X;
                    initialY = START_Y + SQUARE_SIZE * 5;
                    break;
                case 'Y':
                    initialX = START_X + SQUARE_SIZE * 13.5;
                    initialY = START_Y;
                    break;
            }
            setLayoutX(initialX);
            setLayoutY(initialY);

        // Handling events

            setOnMousePressed(mouseEvent -> {
                homeX = getLayoutX();
                homeY = getLayoutY();
                mouseX = mouseEvent.getSceneX();
                mouseY = mouseEvent.getSceneY();
                toFront();
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
                positionX = (int) getLayoutX() / 50;
                positionY = (int) getLayoutY() / 50;
                snapToGrid();

//                if (isBoardOccupied())
//                updatePosition();
                mouseEvent.consume();
            });
        }

        private boolean isThreeByTwo(String piece) {
            return getPieceSpineNum(piece) == 3;
        }

        private void snapToGrid() {
//
//
//            System.out.println("X :" + getLayoutX());
//            System.out.println("Y :" + getLayoutY());
//
//            System.out.println("gridX : " +snapXToGrid(getLayoutX()));
//            System.out.println("gridY : " +snapYToGrid(getLayoutY()));
            snapLayoutToGrid(snapXToGrid(getLayoutX()), snapYToGrid(getLayoutY()));
//            System.out.println(orientation);
//            System.out.println(pieceID);
        }

        private void snapLayoutToGrid(double x, double y) {
            setLayoutX(x);
            setLayoutY(y);
        }

        private int getPositionX() {
            double snapXValue = snapXToGrid(getLayoutX());
            if (isThreeByTwo(pieceID)) {
                if (orientation == 1 || orientation == 3)
                    return (int) snapXValue / (SQUARE_SIZE + 1);
                else
                    return (int) snapXValue / SQUARE_SIZE;
            }
            else {
                if (orientation == 1 || orientation == 3) {
                    return (int) snapXValue / (SQUARE_SIZE + 3);
                }
                else
                    return (int) snapXValue / (SQUARE_SIZE + 3);
            }
        }

        private int getPositionY() {
            double snapYValue = snapYToGrid(getLayoutY());
            if (isThreeByTwo(pieceID)) {
                if (orientation == 1 || orientation == 3) {
                    return (int) snapYValue / SQUARE_SIZE;
                }
                else {
                    return (int) snapYValue / (SQUARE_SIZE + 10);
                }
            }
            else {
                if (orientation == 1 || orientation == 3) {
                    return (int) snapYValue / SQUARE_SIZE;
                }
                else {
                    return (int) snapYValue / (SQUARE_SIZE + 26);
                }
            }
        }

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
                if (xMax > PLAYABLE_AREA_X || yMax == PLAYABLE_AREA_Y)
                    return false;
                else
                    return true;
            }
            else
                return false;
        }

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
                        return initialX;
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
                        return initialX;
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
                        return initialX;
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
                        return initialX;
                }
            }
        }

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
                        return initialY;
                }
                else { //West and East
                    if (yLayout >= 30 && yLayout < 30 + SQUARE_SIZE)
                        return SQUARE_SIZE + 5;
                    else if (yLayout >= SQUARE_SIZE + 30 && yLayout < SQUARE_SIZE * 2 + 30)
                        return SQUARE_SIZE * 2 + 4;
                    else if (yLayout >= SQUARE_SIZE * 2 + 30 && yLayout < SQUARE_SIZE * 3 + 30)
                        return SQUARE_SIZE * 3 + 4;
                    else
                        return initialY;
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
                        return initialY;
                }
                else { // West and East
                    if (yLayout >= SQUARE_SIZE && yLayout < SQUARE_SIZE * 2)
                        return SQUARE_SIZE * 1.5;
                    else if (yLayout >= SQUARE_SIZE * 2 && yLayout < SQUARE_SIZE * 3)
                        return SQUARE_SIZE * 2.5 + 2;
                    else
                        return initialY;
                }
            }

        }
    }


    // Need to change this
    private void makePieces(String placement){
        //List<String> missing = FitGame.getMissingPieces(placement);
        for (int i = 0; i < placement.length(); i+=4) {
            String x = Character.toString(placement.charAt(i + 1));
            String y = Character.toString(placement.charAt(i + 2));
            gamePiece.getChildren().add(new DraggablePiece(placement.charAt(i) + x + y + placement.charAt(i + 3)));
        }
        gamePiece.toFront();
    }

    private String chooseObjective (int difficulty) {
        Random random = new Random();
        String currentDifficulty;
        int randomNumber = 0;
        switch (difficulty) {
            case 1: //difficulty set to Starter
                randomNumber = random.nextInt(24);
                break;
            case 2:
                randomNumber = random.nextInt(48 - 24) + 24;
                break;
            case 3:
                randomNumber = random.nextInt(72 - 48) + 48;
                break;
            case 4:
                randomNumber = random.nextInt(96 - 72) + 72;
                break;
            case 5:
                randomNumber = random.nextInt(108 - 96) + 96;
                break;
            default:
                break;
                // need to raise an error
        }
        currentDifficulty = Games.getObjective(randomNumber);
        return currentDifficulty;
    }

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



//    public static void main(String[] args) {
//        String obj = "B03SG70Si52SL00Nn01Er41WS40Ny62N";
//        String solution = "B03SG70Si52SL00Nn01Eo63Sp20Er41WS40Ny62N";
//        String missingP = setPlayablePieces(obj, solution);
//        System.out.println(missingP);
//    }

    // FIXME Task 7: Implement a basic playable Fix Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement challenges (you may use assets provided for you in comp1110.ass2.gui.assets)

    // FIXME Task 10: Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ-Fit Game");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        FitGame.boardUpdate("", initialBoard);
        // add an event that determines the difficulty of this piece

        String currentObjective = chooseObjective( 2);
        setBoard(currentObjective);
        FitGame.boardUpdate(currentObjective, initialBoard);
//        makePieces(currentObjective);

        String missing = setPlayablePieces(currentObjective, Games.getSolution(currentObjective));
        makePieces(missing);
        root.getChildren().add(board);
        root.getChildren().add(gamePiece);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
