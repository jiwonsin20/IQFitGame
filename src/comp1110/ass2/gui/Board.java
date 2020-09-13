package comp1110.ass2.gui;

import comp1110.ass2.FitGame;
import comp1110.ass2.Games;
import comp1110.ass2.PieceDirection;
import static comp1110.ass2.PieceType.*;
import static comp1110.ass2.Piece.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board extends Application {

    private static final int BOARD_WIDTH = 1500;
    private static final int BOARD_HEIGHT = 1000;
    private static final int BOARD_MARGIN_X = 300;
    private static final int BOARD_MARGIN_Y = 150;
    private static final int PLAYABLE_AREA_X = BOARD_WIDTH - BOARD_MARGIN_X;
    private static final int PLAYABLE_AREA_Y = BOARD_HEIGHT - BOARD_MARGIN_Y;
    private static final int GRID_L_PADDING = 63;
    private static final int GRID_TOP_PADDING = 35;
    private static final int SQUARE_SIZE = 80;
    private static final int START_X = 10;
    private static final int START_Y = BOARD_HEIGHT - 6 * SQUARE_SIZE;

    // This part is for board image
    private static final String URI_BASE = "assets/";
    private static final String BOARD_URI = Board.class.getResource(URI_BASE + "board.png").toString();




    private final Group root = new Group();
    private final Group board = new Group();
    private final Group gamePiece = new Group();

//    private GridPane gridPane = new GridPane();
    void setBoard(String placement) {
        String [] pieces = new String[placement.length()/4];
        int j = 0;
        int xValue;
        int yValue;
        int orientation;

        board.getChildren().clear();

        ImageView board = new ImageView();
        board.setImage(new Image(BOARD_URI));
        board.setFitWidth(933);
        board.setPreserveRatio(true);

        for (int i = 0; i < placement.length(); i+=4) {
            pieces[j] = placement.substring(i, i + 4);
            j++;
        }

        for (int i = 0; i < pieces.length; i++) {
            char c = pieces[i].charAt(0);
            String identifier = "1";
            orientation = PieceDirection.fromChar(pieces[i].charAt(3));


            if (Character.isUpperCase(c))
                identifier = "2";

            xValue = Character.getNumericValue(pieces[i].charAt(1)) * SQUARE_SIZE + GRID_L_PADDING;
            yValue = Character.getNumericValue(pieces[i].charAt(2)) * SQUARE_SIZE + GRID_TOP_PADDING;
            ImageView pieceImage = new ImageView(new Image(getClass().getResource(URI_BASE + Character.toUpperCase(c) + identifier+ ".png").toString()));

            if (getPieceSpineNum(pieces[i]) == 3)
                pieceImage.setFitWidth(240);
            else
                pieceImage.setFitWidth(320);

            pieceImage.setPreserveRatio(true);
            pieceImage.setRotate(orientation * 90);
            pieceImage.setLayoutX(xValue);
            pieceImage.setLayoutY(yValue);


            switch (orientation) {
                case 1:
                    if (getPieceSpineNum(pieces[i]) == 3) {
                        pieceImage.setTranslateX(-40);
                        pieceImage.setTranslateY(40);
                    }
                    else {
                        pieceImage.setTranslateX(-79);
                        pieceImage.setTranslateY(80);
                    }
                    break;
                case 3:
                    if (getPieceSpineNum(pieces[i]) == 3) {
                        pieceImage.setTranslateX(-35);
                        pieceImage.setTranslateY(40);
                    }
                    else {
                        pieceImage.setTranslateX(-79);
                        pieceImage.setTranslateY(80);
                    }
                    break;
                default:
                    break;
            }

            gamePiece.getChildren().add(pieceImage);
        }

        root.getChildren().add(board);
    }

    private int getPieceSpineNum(String piece) {
        return toPiece(piece).type.getSpineNum();
    }

//    void setBoard(String placement) {
//
//        for (int j = 0; j< 5;j++) {
//            RowConstraints row = new RowConstraints(SQUARE_SIZE);
//            gridPane.getRowConstraints().add(row);
//
//            for (int i = 0; i < 2; i++) {
//                ColumnConstraints col = new ColumnConstraints(SQUARE_SIZE);
//                gridPane.getColumnConstraints().add(col);
//            }
//        }
//        gridPane.setPadding(new Insets(GRID_TOP_PADDING,0,0, GRID_L_PADDING));
//        gridPane.setGridLinesVisible(true);
//
//        ImageView backgroundImg = new ImageView(new Image(getClass().getResource(URI_BASE + "board.png").toString()));
//        backgroundImg.setFitWidth(933);
//        backgroundImg.setOpacity(0.7);
//        backgroundImg.setPreserveRatio(true);
//
//        board.getChildren().addAll(gridPane,backgroundImg);
//
//    }

    class PieceTile extends ImageView {
        String pieceID;

        /**
         * Constructor for pieces that can be played
         * @param piece
         */

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
            setFitHeight(SQUARE_SIZE * toPiece(piece).getYDimensions());
        }
    }

    class DraggablePiece extends PieceTile {
        double initialX, initialY;
        double mouseX, mouseY;
        int orientation; // 0 = NORTH, 1 = EAST 2 = SOUTH 3 = WEST
        char type;

        /**
         * Constructor for pieces that can be played
         *
         * @param placement
         */
        DraggablePiece(String placement) {
            super(placement);
            type = placement.charAt(0);
            orientation = PieceDirection.fromChar(placement.charAt(3));
            Image pieceImage;
            ImageView view;


            int positionX = Character.getNumericValue(placement.charAt(1));
            int positionY = Character.getNumericValue(placement.charAt(2));

            if (Character.isLowerCase(type))
                pieceImage = new Image(getClass().getResource(URI_BASE + Character.toUpperCase(placement.charAt(0)) +"1.png").toString());
            else
                pieceImage = new Image(getClass().getResource(URI_BASE + (placement.charAt(0)) +"2.png").toString());

            setImage(pieceImage);

            char pieceID = placement.charAt(0);

            switch (pieceID) {
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
                initialX = getLayoutX();
                initialY = getLayoutY();
                mouseX = mouseEvent.getSceneX();
                mouseY = mouseEvent.getSceneY();
                toFront();
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
                Image current = getImage();
                double scrollY = scrollEvent.getDeltaY();
                setRotate(scrollY * 2.25);

                if (current.getWidth() == 240) {
                    setTranslateX(-SQUARE_SIZE / 2);
                    setTranslateY(-SQUARE_SIZE / 2);
                }
                else {
                    setTranslateX(-SQUARE_SIZE);
                    setTranslateY(-SQUARE_SIZE);
                }

                scrollEvent.consume();
            });

            setOnMouseReleased(mouseEvent -> {
                double currentX = getLayoutX() + SQUARE_SIZE/2;
                double currentY = getLayoutY() + SQUARE_SIZE / 2;
                snapToGrid(currentX, currentY);
            });
        }
//        private void snapToHome() {
//            snapLayoutToGrid(initialX, initialY);
//            // needs adjustment
//        }

        private void snapToGrid(double x, double y) {
            snapLayoutToGrid(snapXtoGrid(x), snapYtoGrid(y));
        }

        private void snapLayoutToGrid(double x, double y) {
            setLayoutX(x + GRID_L_PADDING);
            setLayoutY(y + GRID_TOP_PADDING);
        }

        private boolean isPieceOnBoard(){
            if ((getLayoutX() > GRID_L_PADDING && getLayoutX() < 933) &&
                (getLayoutY() > GRID_TOP_PADDING && getLayoutY() < 450)) {
                return true;
            }
            else
                return false;
        }

        private double snapXtoGrid (double xLayout) {
            if (xLayout >= GRID_L_PADDING && xLayout < (SQUARE_SIZE+ GRID_L_PADDING))
                return 0;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 2))
                return SQUARE_SIZE;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 2) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 3))
                return SQUARE_SIZE * 2 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 3) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 4))
                return SQUARE_SIZE * 3 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 4) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 5))
                return SQUARE_SIZE * 4 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 5) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 6))
                return SQUARE_SIZE * 5 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 6) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 7))
                return SQUARE_SIZE * 6 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 7) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 8))
                return SQUARE_SIZE * 7 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 8) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 9))
                return SQUARE_SIZE * 8 ;
            else if (xLayout >= (GRID_L_PADDING + SQUARE_SIZE * 9) && xLayout < (GRID_L_PADDING + SQUARE_SIZE * 10))
                return SQUARE_SIZE * 9 ;
            else
                return 0;
        }

        private double snapYtoGrid (double yLayout) {
            if (yLayout >= GRID_TOP_PADDING && yLayout < (SQUARE_SIZE + GRID_TOP_PADDING))
                return 0 ;
            else if (yLayout >= (GRID_TOP_PADDING + SQUARE_SIZE) && yLayout < (GRID_TOP_PADDING + SQUARE_SIZE * 2))
                return SQUARE_SIZE  ;
            else if (yLayout >= (GRID_TOP_PADDING + SQUARE_SIZE * 2) && yLayout < (GRID_TOP_PADDING + SQUARE_SIZE * 3))
                return SQUARE_SIZE * 2;
            else if (yLayout >= (GRID_TOP_PADDING + SQUARE_SIZE * 3) && yLayout < (GRID_TOP_PADDING + SQUARE_SIZE * 4))
                return SQUARE_SIZE * 3 ;
            else if (yLayout >= (GRID_TOP_PADDING + SQUARE_SIZE * 4) && yLayout < (GRID_TOP_PADDING + SQUARE_SIZE * 5))
                return SQUARE_SIZE * 4 ;
            else
                return 0;
        }

    }



    private void makePieces(String placement){
        List<String> missing = FitGame.getMissingPieces(placement);
        for (int i = 0; i < missing.size()/2; i++) {
            gamePiece.getChildren().add(new DraggablePiece(missing.get(i)+ "00N"));
        }
        gamePiece.toFront();
    }

    private String chooseObjective (int difficulty) {
        Random random = new Random();
        String currentDifficulty = "";
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
        currentDifficulty = Games.getPlacement(randomNumber);
        return currentDifficulty;
    }

    // FIXME Task 7: Implement a basic playable Fix Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement challenges (you may use assets provided for you in comp1110.ass2.gui.assets)

    // FIXME Task 10: Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQ-Fit Game");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        // add an event that determines the difficulty of this piece

        String currentObjective = chooseObjective( 1);
        setBoard(currentObjective);
        makePieces(currentObjective);
        root.getChildren().add(board);
        root.getChildren().add(gamePiece);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
