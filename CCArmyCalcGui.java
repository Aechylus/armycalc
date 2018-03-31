//JavaFX import lines
import javafx.application.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;

/**
 * Calculates CC troop movements using GUI
 */
public class CCArmyCalcGui extends Application {
    ////////////////////////////////// FIELDS //////////////////////////////////
    private BorderPane topLevelPane; //top level pane
    private GridPane mainMenuPane; //pane for main menu

    //////////////////////////////// CONSTANTS /////////////////////////////////
    private static final double SPEED_ON_ROAD = 165.0;
    private static final double SPEED_OFF_ROAD = 110.0;

    private static final int PADDING_SIZE = 15;
    private static final int TITLE_SIZE = 30;
    private static final int PREF_WIDTH = 325;
    private static final int PREF_HEIGHT = 350;

    ///////////////////////////////// METHODS //////////////////////////////////
    @Override
    public void start(Stage primaryStage) {
        //top level pane
        topLevelPane = new BorderPane();
        topLevelPane.setPadding(new Insets(PADDING_SIZE));

        //title text, top
        Text titleText = new Text();
        titleText.setText("CC Army Movement Calculator");
        titleText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, TITLE_SIZE));
        topLevelPane.setTop(titleText);

        //main menu pane, center
        mainMenuPane = new GridPane();
        mainMenuPane.setAlignment(Pos.CENTER);
        mainMenuPane.setPadding(new Insets(PADDING_SIZE));
        mainMenuPane.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        mainMenuPane.setHgap(PADDING_SIZE);
        mainMenuPane.setVgap(PADDING_SIZE);
        topLevelPane.setCenter(mainMenuPane);

        //road mode button & action
        Button roadButton = new Button("Road");
        mainMenuPane.add(roadButton, 0, 0);
        roadButton.setOnAction(e-> {
            setRoadPane();
        });

        //non road mode button & action
        Button nonRoadButton = new Button("Non Road");
        mainMenuPane.add(nonRoadButton, 1, 0);
        nonRoadButton.setOnAction(e-> {
            setNonRoadPane();
        });

        GridPane bottomPane = new GridPane();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setHgap(PREF_WIDTH-150);
        topLevelPane.setBottom(bottomPane);

        //main menu button & action
        Button mainMenuButton = new Button("Main Menu");
        bottomPane.add(mainMenuButton, 0, 0);
        // GridPane.setHalignment(mainMenuButton, HPos.LEFT);
        mainMenuButton.setOnAction(e-> {
            topLevelPane.setCenter(mainMenuPane);
        });

        //exit button & action
        Button exitButton = new Button("Quit");
        bottomPane.add(exitButton, 1, 0);
        // GridPane.setHalignment(exitButton, HPos.RIGHT);
        exitButton.setOnAction(e-> {
            System.exit(0);
        });

        Scene scene = new Scene(topLevelPane);
        primaryStage.setTitle("CC Army Movement Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Sets the center pane for road mode
     */
    private void setRoadPane() {
        //main gridpane, center
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(PADDING_SIZE));
        mainPane.setHgap(PADDING_SIZE);
        mainPane.setVgap(PADDING_SIZE);
        topLevelPane.setCenter(mainPane);

        //subtitle
        Text subtitleText = new Text("Road Mode");
        subtitleText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 25));
        mainPane.add(subtitleText, 0, 0, 2, 1);
        GridPane.setHalignment(subtitleText, HPos.CENTER);

        /* lines include label and text fields */
        //line 1: road distance
        Label roadDistLabel = new Label("Road distance:");
        mainPane.add(roadDistLabel, 0, 1);
        GridPane.setHalignment(roadDistLabel, HPos.RIGHT);
        TextField roadDistField = new TextField();
        mainPane.add(roadDistField, 1, 1);

        //duration
        GridPane durationPane = new GridPane();
        durationPane.setAlignment(Pos.CENTER_LEFT);
        mainPane.add(durationPane, 0, 3, 2, 1);

        Text preDurationText = new Text("Duration of the march: ");
        preDurationText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        durationPane.add(preDurationText, 0, 0);
        GridPane.setHalignment(preDurationText, HPos.RIGHT);
        Text durationText = new Text(" ");
        durationText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        durationPane.add(durationText, 1, 0);

        //calculate button & action
        Button calcButton = new Button("Calculate");
        mainPane.add(calcButton, 0, 2, 2, 1);
        GridPane.setHalignment(calcButton, HPos.CENTER);
        calcButton.setOnAction(e-> {
            double roadDist = Double.parseDouble(roadDistField.getText());
            int roadDays = (int)Math.ceil(roadDist / SPEED_ON_ROAD);
            durationText.setText(roadDays + " days.");
        });
    }

    /**
     * Sets center pane for non road mode
     */
    private void setNonRoadPane() {
        //main gridpane, center
        GridPane mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setPadding(new Insets(PADDING_SIZE));
        mainPane.setHgap(PADDING_SIZE);
        mainPane.setVgap(PADDING_SIZE);
        topLevelPane.setCenter(mainPane);

        //subtitle
        Text subtitleText = new Text("Non Road Mode");
        subtitleText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 25));
        mainPane.add(subtitleText, 0, 0, 2, 1);
        GridPane.setHalignment(subtitleText, HPos.CENTER);

        /* lines include label and text fields */
        //line 1: first x
        Label coord1xLabel = new Label("First x coordinate:");
        mainPane.add(coord1xLabel, 0, 1);
        GridPane.setHalignment(coord1xLabel, HPos.RIGHT);
        TextField coord1xField = new TextField();
        mainPane.add(coord1xField, 1, 1);

        //line 2: first z
        Label coord1zLabel = new Label("First z coordinate:");
        mainPane.add(coord1zLabel, 0, 2);
        GridPane.setHalignment(coord1zLabel, HPos.RIGHT);
        TextField coord1zField = new TextField();
        mainPane.add(coord1zField, 1, 2);

        //line 3: second x
        Label coord2xLabel = new Label("Second x coordinate:");
        mainPane.add(coord2xLabel, 0, 3);
        GridPane.setHalignment(coord2xLabel, HPos.RIGHT);
        TextField coord2xField = new TextField();
        mainPane.add(coord2xField, 1, 3);

        //line 4: second z
        Label coord2zLabel = new Label("Second z coordinate:");
        mainPane.add(coord2zLabel, 0, 4);
        GridPane.setHalignment(coord2zLabel, HPos.RIGHT);
        TextField coord2zField = new TextField();
        mainPane.add(coord2zField, 1, 4);

        //duration
        GridPane durationPane = new GridPane();
        durationPane.setAlignment(Pos.CENTER_LEFT);
        mainPane.add(durationPane, 0, 6, 2, 1);

        Text preDurationText = new Text("Duration of the march: ");
        preDurationText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        durationPane.add(preDurationText, 0, 0);
        GridPane.setHalignment(preDurationText, HPos.RIGHT);
        Text durationText = new Text(" ");
        durationText.setFont(Font.font("Helvetica Neue", FontWeight.BOLD, 20));
        durationPane.add(durationText, 1, 0);

        //calculate button & action
        Button calcButton = new Button("Calculate");
        mainPane.add(calcButton, 0, 5, 2, 1);
        GridPane.setHalignment(calcButton, HPos.CENTER);
        calcButton.setOnAction(e-> {
            double coord1x = Double.parseDouble(coord1xField.getText());
            double coord1z = Double.parseDouble(coord1zField.getText());
            double coord2x = Double.parseDouble(coord2xField.getText());
            double coord2z = Double.parseDouble(coord2zField.getText());

            double deltaX = coord2x-coord1x;
            double deltaZ = coord2z-coord1z;

            double nonRoadDist = Math.hypot(deltaX, deltaZ);
            int nonRoadDays = (int)Math.ceil(nonRoadDist / SPEED_OFF_ROAD);
            durationText.setText(nonRoadDays + " days.");
        });
    }
}
