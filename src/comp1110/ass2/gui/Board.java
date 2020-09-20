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
                    initialX = START_X;
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
                    initialX = START_X + SQUARE_SIZE * 4.5;
                    initialY = START_Y;
                    break;
                case 'N':
                    initialX = START_X + SQUARE_SIZE * 4.5;
                    initialY = START_Y + SQUARE_SIZE * 2.05;
                    break;
                case 'O':
                    initialX = START_X + SQUARE_SIZE * 4.5;
                    initialY = START_Y + SQUARE_SIZE * 4.1;
                    break;
                case 'P':
                    initialX = START_X + SQUARE_SIZE * 9;
                    initialY = START_Y;
                    break;
                case 'R':
                    initialX = START_X + SQUARE_SIZE * 9;
                    initialY = START_Y + SQUARE_SIZE * 2.05;
                    break;
                case 'S':
                    initialX = START_X + SQUARE_SIZE * 9;
                    initialY = START_Y + SQUARE_SIZE * 4.1;
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

            if (!pieceInsideBoard()) {
                setOnScroll(scrollEvent -> {
                    setRotate((orientation) * 90);
                    orientation++;
                    setFitWidth(getPieceSpineNum(pieceID) * SQUARE_SIZE);
                    setPreserveRatio(true);
                    if (orientation == 5)
                        orientation = 1;

                });
            }
            else {
                setOnScroll(scrollEvent -> {});
            }

            setOnMouseReleased(mouseEvent -> {
                positionX = (int) getLayoutX() / 50;
                positionY = (int) getLayoutY() / 50;
//                if (isPieceOnBoard())
                    snapToGrid();
//                else
//                    backHome();
//                if (!isPieceOnBoard())
//                    snapToHome();
//                if (isBoardOccupied())
//                updatePosition();
                mouseEvent.consume();
            });
        }

        private void backHome() {
            setLayoutX(initialX);
            setLayoutX(initialY);
        }

        private boolean pieceInsideBoard() {
            if (getLayoutX() < PLAYABLE_AREA_X && getLayoutY() < PLAYABLE_AREA_Y)
                return true;
            else
                return false;
        }

//        private void updatePosition() {
//
//        }


//        private boolean isBoardOccupied() {
//            String str = type + Integer.toString(positionX) + Integer.toString(positionY + pieceID.charAt(3));
//        }
//
//        private void snapToHome() {
//            setLayoutX(initialX);
//            setLayoutY(initialY);
//            // needs adjustment
//        }

        private boolean isThreeByTwo(String piece) {
            return getPieceSpineNum(piece) == 3;
        }

        private void snapToGrid() {


            System.out.println("X :" + getLayoutX());
            System.out.println("Y :" + getLayoutY());

            System.out.println("gridX : " +snapXtoGrid(getLayoutX()));
            System.out.println("gridY : " +snapYtoGrid(getLayoutY()));

            snapLayoutToGrid(snapXtoGrid(getLayoutX()), snapYtoGrid(getLayoutY()));
            System.out.println(orientation);
            System.out.println(pieceID);
        }

        private void snapLayoutToGrid(double x, double y) {
            setLayoutX(x);
            setLayoutY(y);
        }

        private boolean isPieceOnBoard(){
            if (getLayoutX() > GRID_L_PADDING && (getLayoutX() < PLAYABLE_AREA_X)
                    && getLayoutY() > GRID_TOP_PADDING && (getLayoutY() < PLAYABLE_AREA_Y)) {
                return true;
            }
            else
                return false;
        }

        private double snapXtoGrid (double xLayout) {

            if (isThreeByTwo(pieceID) && (orientation == 2 || orientation == 4)) {
                if (xLayout >= -25 && xLayout < (SQUARE_SIZE + 19))
                    return (SQUARE_SIZE / 2f) -5;
                else if (xLayout >= (SQUARE_SIZE + 19) && xLayout < (SQUARE_SIZE * 2 + 19))
                    return SQUARE_SIZE * 1.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 2 + 19) && xLayout < (SQUARE_SIZE * 3 + 19))
                    return SQUARE_SIZE * 2.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 3 + 19) && xLayout < (SQUARE_SIZE * 4 + 19))
                    return SQUARE_SIZE * 3.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 4 + 19) && xLayout < (SQUARE_SIZE * 5 + 19))
                    return SQUARE_SIZE * 4.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 5 + 19) && xLayout < (SQUARE_SIZE * 6 + 19))
                    return SQUARE_SIZE * 5.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 6 + 19) && xLayout < (SQUARE_SIZE * 7 + 19))
                    return SQUARE_SIZE * 6.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 7 + 19) && xLayout < (SQUARE_SIZE * 8 + 19))
                    return SQUARE_SIZE * 7.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 8 + 19) && xLayout < (SQUARE_SIZE * 9 + 19))
                    return SQUARE_SIZE * 8.5 -5;
                else if (xLayout >= (SQUARE_SIZE * 9 + 19) && xLayout < (SQUARE_SIZE * 10 + 19))
                    return SQUARE_SIZE * 9.5 -5;
                else
                    return 600;
            }
            else if (!isThreeByTwo(pieceID) && (orientation == 2 || orientation == 4)) {
                if (xLayout >= -25 && xLayout < (SQUARE_SIZE * 0.5))
                    return (0);
                else if (xLayout >= (SQUARE_SIZE * 0.5) && xLayout < (SQUARE_SIZE * 1.5))
                    return SQUARE_SIZE;
                else if (xLayout >= (SQUARE_SIZE * 1.5) && xLayout < (SQUARE_SIZE * 2.5))
                    return SQUARE_SIZE * 2;
                else if (xLayout >= (SQUARE_SIZE * 2.5) && xLayout < (SQUARE_SIZE * 3.5))
                    return SQUARE_SIZE * 3;
                else if (xLayout >= (SQUARE_SIZE * 3.5) && xLayout < (SQUARE_SIZE * 4.5))
                    return SQUARE_SIZE * 4;
                else if (xLayout >= (SQUARE_SIZE * 4.5) && xLayout < (SQUARE_SIZE * 5.5))
                    return SQUARE_SIZE * 5;
                else if (xLayout >= (SQUARE_SIZE * 5.5) && xLayout < (SQUARE_SIZE * 6.5))
                    return SQUARE_SIZE * 6;
                else if (xLayout >= (SQUARE_SIZE * 6.5) && xLayout < (SQUARE_SIZE * 7.5))
                    return SQUARE_SIZE * 7;
                else if (xLayout >= (SQUARE_SIZE * 7.5) && xLayout < (SQUARE_SIZE * 8.5))
                    return SQUARE_SIZE * 8;
                else if (xLayout >= (SQUARE_SIZE * 8.5) && xLayout < (SQUARE_SIZE * 9.5))
                    return SQUARE_SIZE * 9;
                else
                    return 600;
            }
            else {
                if (xLayout >= -50 && xLayout < (SQUARE_SIZE + 19))
                    return (SQUARE_SIZE / 2f + 19);
                else if (xLayout >= (SQUARE_SIZE + 19) && xLayout < (SQUARE_SIZE * 2 + 19))
                    return SQUARE_SIZE * 1.5 + 16;
                else if (xLayout >= (SQUARE_SIZE * 2 + 19) && xLayout < (SQUARE_SIZE * 3 + 19))
                    return SQUARE_SIZE * 2.5 + 18;
                else if (xLayout >= (SQUARE_SIZE * 3 + 19) && xLayout < (SQUARE_SIZE * 4 + 19))
                    return SQUARE_SIZE * 3.5 + 20;
                else if (xLayout >= (SQUARE_SIZE * 4 + 19) && xLayout < (SQUARE_SIZE * 5 + 19))
                    return SQUARE_SIZE * 4.5 + 22;
                else if (xLayout >= (SQUARE_SIZE * 5 + 19) && xLayout < (SQUARE_SIZE * 6 + 19))
                    return SQUARE_SIZE * 5.5 + 24;
                else if (xLayout >= (SQUARE_SIZE * 6 + 19) && xLayout < (SQUARE_SIZE * 7 + 19))
                    return SQUARE_SIZE * 6.5 + 25;
                else if (xLayout >= (SQUARE_SIZE * 7 + 19) && xLayout < (SQUARE_SIZE * 8 + 19))
                    return SQUARE_SIZE * 7.5 + 26;
                else if (xLayout >= (SQUARE_SIZE * 8 + 19) && xLayout < (SQUARE_SIZE * 9 + 19))
                    return SQUARE_SIZE * 8.5 + 28;
                else if (xLayout >= (SQUARE_SIZE * 9 + 19) && xLayout < (SQUARE_SIZE * 10 + 19))
                    return SQUARE_SIZE * 9.5 + 29;
                else
                    return 600;
            }
        }

        private double snapYtoGrid (double yLayout) {
            if (isThreeByTwo(pieceID) && (orientation == 2 || orientation == 4)) {
                if (yLayout > 20 && yLayout < (SQUARE_SIZE * 1.5))
                    return SQUARE_SIZE;
                else if (yLayout >= (SQUARE_SIZE * 1.5) && yLayout < (SQUARE_SIZE * 2.5))
                    return SQUARE_SIZE * 2 + 2;
                else if (yLayout >= (SQUARE_SIZE * 2.5) && yLayout < (SQUARE_SIZE * 3.5))
                    return SQUARE_SIZE * 3 + 2;
                else if (yLayout >= (SQUARE_SIZE * 3.5) && yLayout < (SQUARE_SIZE * 4.5))
                    return SQUARE_SIZE * 4 + 3;
                else if (yLayout >= (SQUARE_SIZE * 4.5) && yLayout < (SQUARE_SIZE * 5.5))
                    return SQUARE_SIZE * 5 + 4;
                else
                    return START_Y;
            }

            else {
                if (yLayout > 0 && yLayout < (SQUARE_SIZE))
                    return SQUARE_SIZE * 0.5;
                else if (yLayout >= (SQUARE_SIZE) && yLayout < (SQUARE_SIZE * 2))
                    return SQUARE_SIZE * 1.5;
                else if (yLayout >= (SQUARE_SIZE * 2) && yLayout < (SQUARE_SIZE * 3))
                    return SQUARE_SIZE * 2.5;
                else if (yLayout >= (SQUARE_SIZE * 3) && yLayout < (SQUARE_SIZE * 4))
                    return SQUARE_SIZE * 3.5;
                else if (yLayout >= (SQUARE_SIZE * 4) && yLayout < (SQUARE_SIZE * 5))
                    return SQUARE_SIZE * 4.5;
                else
                    return START_Y;
            }
        }
    }



    // Need to change this
    private void makePieces(String placement){
        //List<String> missing = FitGame.getMissingPieces(placement);
        for (int i = 0; i < placement.length(); i+=4) {
            gamePiece.getChildren().add(new DraggablePiece(placement.charAt(i) + "00" + placement.charAt(i + 3)));
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
        // add an event that determines the difficulty of this piece

        String currentObjective = chooseObjective( 2);
        setBoard(currentObjective);
//        makePieces(currentObjective);

        String missing = setPlayablePieces(currentObjective, Games.getSolution(currentObjective));
        makePieces(missing);
        root.getChildren().add(board);
        root.getChildren().add(gamePiece);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
