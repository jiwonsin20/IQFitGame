package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


/**
 * A very simple viewer for piece placements in the IQ-Fit game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private final Group placements = new Group();
    private final Group board = new Group();
    private TextField textField;



    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     * authored by Jiwon Sin
     */

    void makePlacement(String placement) {
        // Clearing previous placement group
        placements.getChildren().clear();

        GridPane gridpane = new GridPane();

        for (int j = 0; j< 5;j++) {
            RowConstraints row = new RowConstraints(61);
            gridpane.getRowConstraints().add(row);

            for (int i = 0; i < 2; i++) {
                ColumnConstraints col = new ColumnConstraints(61);
                gridpane.getColumnConstraints().add(col);
            }
        }
        gridpane.setPadding(new Insets(30,50,50,50));

        for (int i = 0; i < placement.length(); i+=4) {
            char verifier = ' ';
            char piece = placement.charAt(i);

            int x = Character.getNumericValue(placement.charAt(i+1));
            int y = Character.getNumericValue(placement.charAt(i+2));

            if (placement.charAt(i) >= 65 && placement.charAt(i) <= 90)
                verifier = '2';
            else if (placement.charAt(i) >= 97 && placement.charAt(i) <= 122)
                verifier = '1';

            ImageView firstImage = new ImageView (new Image(getClass().getResource
                    (URI_BASE + Character.toUpperCase(placement.charAt(i)) + verifier + ".png").toString()));

            firstImage.setFitHeight(125);
            firstImage.setPreserveRatio(true);
            firstImage.setTranslateY(34);

            boolean threeByTwo = piece == 'g' || piece == 'G' || piece == 'n' || piece == 'N' ||
                    piece == 'l' || piece == 'L' || piece == 'i' || piece == 'I';
            switch (placement.charAt(i+3)) {
                case 'N':
                    gridpane.add(firstImage, x, y);
                    break;
                case 'E':
                    firstImage.setRotate(90);
                    if (threeByTwo) {
                        gridpane.add(firstImage, x , y + 1);
                        firstImage.setTranslateX(-35);
                        firstImage.setTranslateY(0);
                        break;
                    }
                    gridpane.add(firstImage, x , y + 1);
                    firstImage.setTranslateX(-60);
                    break;
                case 'S':
                    firstImage.setRotate(180);
                    gridpane.add(firstImage, x, y);
                    break;
                case 'W':
                    firstImage.setRotate(-90);
                    if (threeByTwo) {
                        gridpane.add(firstImage, x , y + 1);
                        firstImage.setTranslateX(-30);
                        firstImage.setTranslateY(0);
                        break;
                    }
                    gridpane.add(firstImage, x , y + 1);
                    firstImage.setTranslateX(-60);
                    break;
            }
        }
        placements.getChildren().add(gridpane);
    }


    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);

        // Creating a button using Button class
        Button button = new Button("Refresh");

        // What happens if we click the button?
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Allow user to key in some text inside the textField we declared earlier
                // Whatever used as input into textField will be inserted to makePlacement method
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Setting title for our View application
        primaryStage.setTitle("FitGame Viewer");

        // Setting the scene to
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);





        // Adding the main board first
        ImageView boardImg = new ImageView(new Image(getClass().getResource(URI_BASE + "board.png").toString()));
        boardImg.setOpacity(0.7);
        boardImg.setFitWidth(VIEWER_WIDTH);
        boardImg.setPreserveRatio(true);
        board.getChildren().add(boardImg);
        root.getChildren().addAll(board,controls);
        root.getChildren().add(placements);


        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
