package comp1110.ass2.gui;

import comp1110.ass2.PieceDirection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Board extends Application {

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;
    private static final int GRID_L_PADDING = 63;
    private static final int GRID_TOP_PADDING = 35;
    private static final int SQUARE_SIZE = 80;
    private static final int START_X = 10;
    private static final int START_Y = 10;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group background = new Group();

    private GridPane gridPane = new GridPane();

    void setBackground() {

        for (int j = 0; j< 5;j++) {
            RowConstraints row = new RowConstraints(SQUARE_SIZE);
            gridPane.getRowConstraints().add(row);

            for (int i = 0; i < 2; i++) {
                ColumnConstraints col = new ColumnConstraints(SQUARE_SIZE);
                gridPane.getColumnConstraints().add(col);
            }
        }
        gridPane.setPadding(new Insets(GRID_TOP_PADDING,0,0, GRID_L_PADDING));
        gridPane.setGridLinesVisible(true);

        ImageView backgroundImg = new ImageView(new Image(getClass().getResource(URI_BASE + "board.png").toString()));
        backgroundImg.setOpacity(0.7);
        backgroundImg.setFitWidth(BOARD_WIDTH);
        backgroundImg.setPreserveRatio(true);

        background.getChildren().addAll(gridPane,backgroundImg);
    }

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
                if ((piece.charAt(0) != pieceArray[i] && piece.charAt(0) != pieceArrayUp[i]) && i == 9) {
                    throw new IllegalArgumentException("Bad piece: " + piece);
                }
            }
            this.pieceID = piece;
            setFitWidth(SQUARE_SIZE);
            setFitHeight(SQUARE_SIZE);
        }
    }

    class DraggableTile extends PieceTile {
        double initialX, initialY;
        double mouseX, mouseY;
        int orientation; // 0 = NORTH, 1 = EAST 2 = SOUTH 3 = WEST

        /**
         * Constructor for pieces that can be played
         *
         * @param placement
         */
        DraggableTile(String placement) {
            super(placement);
            orientation = PieceDirection.fromChar(placement.charAt(3));

            int positionX = Character.getNumericValue(placement.charAt(1));
            int positionY = Character.getNumericValue(placement.charAt(2));

            Image pieceImage = new Image(getClass().getResource(URI_BASE + placement + ".png").toString());
            setImage(pieceImage);

            char pieceID = placement.charAt(0);

            switch (pieceID) {
                case 'B':
                    initialX = START_X;
                    initialY = START_Y;
                    break;
                case 'G':
                    initialX = START_X;
                    initialY = START_Y + SQUARE_SIZE * 1.5;
                    break;
                case 'I':
                    initialX = START_X;
                    initialY = START_Y + SQUARE_SIZE * 2.5;
                    break;
                case 'L':
                    initialX = START_X;
                    initialY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'N':
                    initialX = START_X;
                    initialY = START_Y + SQUARE_SIZE * 4.5;
                    break;
                case 'O':
                    initialY = START_X + SQUARE_SIZE * 4;
                    initialY = START_Y;
                case 'P':
                    initialX = START_X + SQUARE_SIZE * 4;
                    initialY = START_Y + SQUARE_SIZE * 1.5;
                    break;
                case 'R':
                    initialX = START_X + SQUARE_SIZE * 4;
                    initialY = START_Y + SQUARE_SIZE * 2.5;
                    break;
                case 'S':
                    initialX = START_X + SQUARE_SIZE * 4;
                    initialY = START_Y + SQUARE_SIZE * 3.5;
                    break;
                case 'Y':
                    initialX = START_X + SQUARE_SIZE * 4;
                    initialY = START_Y + SQUARE_SIZE * 4.5;
                    break;
            }

//            if (pieceID == 'B') {
//                initialX = START_X;
//                initialY = START_Y;
//            }
            setLayoutX(initialX);
            setLayoutY(initialY);
            setRotate(orientation * 90);

        // Handling events


        }
    }

    // FIXME Task 7: Implement a basic playable Fix Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement challenges (you may use assets provided for you in comp1110.ass2.gui.assets)

    // FIXME Task 10: Implement hints (should become visible when the user presses '/' -- see gitlab issue for details)

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IQFit Game");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        setBackground();
        root.getChildren().add(background);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
