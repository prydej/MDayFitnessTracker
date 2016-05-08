package fitness_app;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * @author Julian Pryde
 *
 */
public class FitnessGUI extends Application {

	Text pickExercise, enterReps, enterTime;
	RadioButton squats, pushUps, burpees, sitUps, walking;
	Button enter;
	TextField number, time;
	GridPane gridPane;
	BorderPane enterData;
	int maxWOButtonSize = 90;
	int rowHeight = 25;
	ToggleGroup exerciseGroup;
	
	TabPane tabPane;
	Tab enterDataTab, retrieveDataTab;
	
	Tracker tracker;

	public FitnessGUI(){
		//Instantiate panes
		gridPane = new GridPane();
		enterData = new BorderPane();
		tabPane = new TabPane();
		
		//Instantiate text
		pickExercise = new Text("Exercise:");
		enterReps = new Text("Number of Reps:");
		enterTime = new Text("Time walked:");

		//Instantiate exercise toggle goup
		exerciseGroup = new ToggleGroup();

		//Instantiate buttons and set group
		squats = new RadioButton("Squats");
		squats.setToggleGroup(exerciseGroup);
		squats.setSelected(true);

		pushUps = new RadioButton("Push Ups");
		pushUps.setToggleGroup(exerciseGroup);

		burpees = new RadioButton("Burpees");
		burpees.setToggleGroup(exerciseGroup);

		sitUps = new RadioButton("Sit Ups");
		sitUps.setToggleGroup(exerciseGroup);

		walking = new RadioButton("Walking");
		walking.setToggleGroup(exerciseGroup);

		enter = new Button("Enter");
		GridPane.setHalignment(enter, HPos.RIGHT);
		GridPane.setValignment(enter, VPos.BOTTOM);

		//Instantiate text field
		number = new TextField();
		time = new TextField();

		//Set initial grid constraints
		GridPane.setConstraints(pickExercise, 1, 1);
		GridPane.setConstraints(enterReps, 2, 1);
		GridPane.setConstraints(squats, 1, 2);
		GridPane.setConstraints(pushUps, 1, 3);
		GridPane.setConstraints(burpees, 1, 4);
		GridPane.setConstraints(sitUps, 1, 5);
		GridPane.setConstraints(walking, 1, 6);
		GridPane.setConstraints(number, 2, 2);
		GridPane.setConstraints(enter, 3, 7);
		GridPane.setConstraints(enterTime, 2, 4);
		GridPane.setConstraints(time, 2, 5);

		RowConstraints row0 = new RowConstraints(10);
		RowConstraints row1 = new RowConstraints(rowHeight);
		RowConstraints row2 = new RowConstraints(rowHeight);
		RowConstraints row3 = new RowConstraints(rowHeight);
		RowConstraints row4 = new RowConstraints(rowHeight);
		RowConstraints row5 = new RowConstraints(rowHeight);
		RowConstraints row6 = new RowConstraints(rowHeight);

		gridPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6);

		//Set Button sizes
		number.setPrefWidth(maxWOButtonSize);

		//Set done button alignment
		GridPane.setHalignment(enter, HPos.RIGHT);

		//Set VGap between grid squares
		gridPane.setHgap(10);

		//Add things to pane
		gridPane.getChildren().addAll(pickExercise, enterReps, enterTime, squats, pushUps, burpees, sitUps, walking, number, time, enter);

		//Set time to invisible initially
		time.setVisible(false);
		enterTime.setVisible(false);
		
		enterData.setLeft(gridPane);
		enterData.setBottom(enter);
		BorderPane.setAlignment(enter, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(enter, new Insets(0, 20, 20, 0));
		
		//Instantiate tabs
		enterDataTab = new Tab();
		retrieveDataTab = new Tab();
		
		//add panes to TabPane
		enterDataTab.setText("Enter Data");
		enterDataTab.setContent(enterData);
		
		retrieveDataTab.setText("Retrieve Data");
		tabPane.getTabs().addAll(enterDataTab, retrieveDataTab);
	}

	@Override
	public void start(Stage stage) throws Exception{

		//
		//Event handlers

		//Change text when walking
		walking.setOnAction(e -> setWalkingText());

		//Change text back when another button is selected
		squats.setOnAction(e -> setRegularText());
		pushUps.setOnAction(e -> setRegularText());
		sitUps.setOnAction(e -> setRegularText());
		burpees.setOnAction(e -> setRegularText());
		
		//Handle enter button
		enter.setOnAction(e -> startTracker());
		
		//
		//Bring it all together

		//Create scene
		Scene scene = new Scene(tabPane, 400, 250);

		//Set Stage
		stage.setScene(scene);
		stage.setTitle("Fitness tracker app for Mom");

		//Show stage
		stage.show();
	}

	public static void main(String[] args){
		launch();
	}

	//Change reps box text to walking miles, instead of reps
	public void setWalkingText(){
		//Change Reps text
		enterReps.setText("Number of miles walked:");

		//make time and enterTime visible
		time.setVisible(true);
		enterTime.setVisible(true);

	}
	
	public void setRegularText(){
		//Change Reps text
		enterReps.setText("Number of Reps:");
		
		//make time and enterTime invisible
		time.setVisible(false);
		enterTime.setVisible(false);
	}
	
	public void startTracker(){
		//Create Tracker Object
		tracker = new Tracker();
		
		//Call Tracker.writeToFile
		tracker.writeToFile();
	}

}