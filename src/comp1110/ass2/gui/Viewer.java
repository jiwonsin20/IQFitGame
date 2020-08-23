package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        // FIXME Task 4: implement the simple placement viewer

        // Clearing previous placement group
        // Jiwon 22/08
        placements.getChildren().clear();
        char verifier = ' ';

        if (placement.charAt(0) >= 65 && placement.charAt(0) <= 90)
            verifier = '2';
        else if (placement.charAt(0) >= 97 && placement.charAt(0) <= 122)
            verifier = '1';

        // This bit of code is from online, searched it up. Will add in the URL of the source.
        // Jiwon 22/08

        ImageView firstImage = new ImageView
                (new Image(getClass().getResource
                        (URI_BASE + Character.toUpperCase(placement.charAt(0)) + verifier + ".png").toString()));

        // Check if the rotation is needed
        // Jiwon 22/08
        switch (placement.charAt(3)) {
            case 'N':
                firstImage.setRotate(0);
                break;
            case 'E':
                firstImage.setRotate(90);
                break;
            case 'S':
                firstImage.setRotate(180);
                break;
            case 'W':
                firstImage.setRotate(270);
                break;
        }

        firstImage.setFitHeight(125);
        firstImage.setPreserveRatio(true);

        // Position of the image set to the mid point of the scene
        // Jiwon 22/08
        firstImage.setX(VIEWER_WIDTH/4);
        firstImage.setY((VIEWER_HEIGHT - 50)/4);


        placements.getChildren().add(firstImage);
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
