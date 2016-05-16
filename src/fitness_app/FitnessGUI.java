package fitness_app;

import javafx.util.Duration;
import java.util.ArrayList;
//import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

/**
 * @author Julian Pryde
 *
 */
public class FitnessGUI extends Application {
	
	//Enter data tab
	private Text pickExercise, enterReps, enterTime, setSaved;
	private RadioButton squats, pushUps, burpees, sitUps, walking;
	private Button enter;
	private TextField number, time;
	private GridPane enterDataPromptsPane, statisticsPane;
	private BorderPane enterData;
	private ToggleGroup exerciseGroup;
	private int maxWOButtonSize = 90;
	private int rowHeight = 25;
	
	//Statistics Tab
	private Text
	totalPushupsText, 
	totalSitupsText, 
	totalSquatsText, 
	totalBurpeesText,
	totalWalkTimeText,
	totalMilesWalkedText,
	avgWalkTimeText, 
	avgWalkingSpeedText,
	avgPushupsText, 
	avgSitupsText, 
	avgBurpeesText, 
	avgSquatsText,
	avgMilesWalkedText,
	strengthHeader,
	cardioHeader;
	
	private int 
	totalPushups, 
	totalSitups, 
	totalSquats, 
	totalBurpees,
	totalWalkTime;
	
	private double 
	totalMilesWalked,
	avgWalkTime, 
	avgWalkingSpeed,
	avgPushups, 
	avgSitups, 
	avgBurpees, 
	avgSquats,
	avgMilesWalked;

	TabPane tabPane;
	Tab enterDataTab, statisticsTab;

	public FitnessGUI(){
		//Instantiate panes
		enterDataPromptsPane = new GridPane();
		statisticsPane = new GridPane();
		enterData = new BorderPane();
		tabPane = new TabPane();

		//Instantiate text
		// --Enter Data tab
		pickExercise = new Text("Exercise:");
		enterReps = new Text("Number of (whole) Reps:");
		enterTime = new Text("Minutes walked:");
		setSaved = new Text("Set Saved!");
		
		// --Retrieve Data tab
		totalPushupsText = new Text("Total Pushups:\t\t" + Integer.toString(totalPushups)); 
		totalSitupsText = new Text("Total Situps:\t\t" + Integer.toString(totalSitups)); 
		totalSquatsText = new Text("Total Squats:\t\t" + Integer.toString(totalSquats));
		totalBurpeesText = new Text("Total Burpees:\t\t" + Integer.toString(totalBurpees));
		totalWalkTimeText  = new Text("Total Walk Time:\t\t" + Integer.toString(totalWalkTime) + "minutes");
		
		totalMilesWalkedText = new Text("Total Miles Walked:\t" + Double.toString(totalMilesWalked) + "miles");
		avgWalkTimeText = new Text("Average Time Per Walk:\t" + Double.toString(avgWalkTime) + "minutes");
		avgWalkingSpeedText = new Text("Average Walking Speed:\t" + Double.toString(avgWalkingSpeed) + "miles per hour");
		avgPushupsText = new Text("Average Pushups Per Set:\t" + Double.toString(avgPushups)); 
		avgSitupsText = new Text("Average Situps Per Set:\t" + Double.toString(avgSitups)); 
		avgBurpeesText = new Text("Average Burpees Per Set:\t" + Double.toString(avgBurpees)); 
		avgSquatsText = new Text("Average Squats Per Set:\t" + Double.toString(avgSquats));
		avgMilesWalkedText = new Text("Average Miles Per Walk:\t" + Double.toString(avgMilesWalked));


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

		//Set tab pane closing policy to unavailable
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		//Set initial grid constraints for enter data tab
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
		GridPane.setConstraints(setSaved, 2, 3);
		
		RowConstraints row0 = new RowConstraints(10);
		RowConstraints row1 = new RowConstraints(rowHeight);
		RowConstraints row2 = new RowConstraints(rowHeight);
		RowConstraints row3 = new RowConstraints(rowHeight);
		RowConstraints row4 = new RowConstraints(rowHeight);
		RowConstraints row5 = new RowConstraints(rowHeight);
		RowConstraints row6 = new RowConstraints(rowHeight);

		enterDataPromptsPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6);
		
		//Set initial grid constraints for statistics tab
		GridPane.setConstraints(strengthHeader, 1, 0);
		GridPane.setConstraints(totalPushupsText, 1, 1);
		GridPane.setConstraints(avgPushupsText, 1, 2);
		GridPane.setConstraints(totalSitupsText, 1, 3);
		GridPane.setConstraints(avgSitupsText, 1, 4);
		GridPane.setConstraints(totalSquatsText, 1, 5);
		GridPane.setConstraints(avgSquatsText, 1, 6);
		GridPane.setConstraints(totalBurpeesText, 1, 7);
		GridPane.setConstraints(avgBurpeesText, 1, 8);
		GridPane.setConstraints(cardioHeader, 1, 10);
		GridPane.setConstraints(totalMilesWalkedText, 1, 11);
		GridPane.setConstraints(avgMilesWalkedText, 1, 12);
		GridPane.setConstraints(totalWalkTimeText, 1, 13);
		GridPane.setConstraints(avgWalkTimeText, 1, 14);
		GridPane.setConstraints(avgWalkingSpeedText, 1, 15);

		//Set Button sizes
		number.setPrefWidth(maxWOButtonSize);

		//Set done button alignment
		GridPane.setHalignment(enter, HPos.RIGHT);

		//Set VGap between grid squares
		enterDataPromptsPane.setHgap(10);

		//Add things to pane
		enterDataPromptsPane.getChildren().addAll(pickExercise, enterReps, enterTime, squats, pushUps, burpees, sitUps, 
				walking, number, time, enter, setSaved);

		//Set time to invisible initially
		time.setVisible(false);
		enterTime.setVisible(false);

		//Set setSaved to green and initially invisible
		setSaved.setVisible(false);
		setSaved.setFill(Color.GREEN);

		enterData.setLeft(enterDataPromptsPane);
		enterData.setBottom(enter);
		BorderPane.setAlignment(enter, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(enter, new Insets(0, 20, 20, 0));

		//Instantiate tabs
		enterDataTab = new Tab();
		statisticsTab = new Tab();

		//add panes to TabPane
		enterDataTab.setText("Enter Data");
		enterDataTab.setContent(enterData);

		statisticsTab.setText("Statistics");
		statisticsTab.setContent(statisticsPane);
		
		tabPane.getTabs().addAll(enterDataTab, statisticsTab);
	}

	@Override
	public void start(Stage stage) throws Exception{

		//Event handlers
		// --Change text when walking
		walking.setOnAction(e -> setWalkingText());

		// --Change text back when another button is selected
		squats.setOnAction(e -> setRegularText());
		pushUps.setOnAction(e -> setRegularText());
		sitUps.setOnAction(e -> setRegularText());
		burpees.setOnAction(e -> setRegularText());
		statisticsTab.setOnSelectionChanged(e -> showStatistics());

		// --Handle enter button
		enter.setOnAction(e -> startTrackerIO());
		
		//Bring it all together
		// --Create scene
		Scene scene = new Scene(tabPane, 400, 250);

		// --Set Stage
		stage.setScene(scene);
		stage.setTitle("Fitness tracker app for Mom");

		// --Show stage
		stage.show();
	}

	public static void main(String[] args){
		launch();
	}

	//Change reps box text to walking miles, instead of reps
	public void setWalkingText(){
		//Change Reps text
		enterReps.setText("Number of miles walked:");
		
		//Move setSaved text below time field
		GridPane.setConstraints(setSaved, 2, 6);

		//Clear number of reps field
		number.clear();

		//make time and enterTime visible
		time.setVisible(true);
		enterTime.setVisible(true);

	}

	public void setRegularText(){
		//Change Reps text
		enterReps.setText("Number of (whole) Reps:");

		//clear number of reps field
		number.clear();
		
		//Move setSaved text back to original position below number box
		GridPane.setConstraints(setSaved, 2, 3);

		//make time and enterTime invisible
		time.setVisible(false);
		enterTime.setVisible(false);
	}

	public void startTrackerIO(){
		//Create TrackerIO Object
		TrackerIO io = new TrackerIO();
		Workout selected = new Workout();

		//Set selected var to instance of chosen workout
		if(pushUps.isSelected()){
			selected = new PushUps();
		} else if (sitUps.isSelected()){
			selected = new SitUps();
		} else if (walking.isSelected()){
			selected = new Walking();
		} else if (burpees.isSelected()){
			selected = new Burpees();
		} else if (squats.isSelected()){
			selected = new Squats();
		}

		//Create ArrayList of information given
		ArrayList<Double> infoGiven = new ArrayList<Double>();
		
		//TODO Modularize this, to only pass one argument with workout object to writeToFile(). Maybe use generics?
		infoGiven.add(Math.floor(Double.parseDouble(number.getText())*100)/100); //Add value in number as double rounded to 2 decimal places

		if (walking.isSelected()){ //only add time if it was asked
			infoGiven.add(Math.floor(Double.parseDouble(time.getText())*100)/100);
		}

		//Call TrackerIO.writeToFile()
		io.writeToFile(infoGiven, selected);
		
		//show setSaved for 5 seconds
		Timeline flash = createFlash(setSaved);
		flash.play();
	}
	
	//Create flashing effect for "set saved" text
	public Timeline createFlash(Node node){
		Timeline flash = new Timeline( //create timeline
				new KeyFrame( //add frame
						Duration.seconds(0),
						new KeyValue(
								node.visibleProperty(), //property dealt with
								true, //end state
								Interpolator.DISCRETE //transition
						)
				),
				new KeyFrame(
						Duration.seconds(2),
						new KeyValue(
								node.visibleProperty(), 
								false, 
								Interpolator.DISCRETE
						)
				)
		);
		
		return flash;
		
	}
	
	//Find and display statistics calculations
	public void showStatistics(){
		
		//Create StatisticsClalc object
		StatisticsCalc statsCalc = new StatisticsCalc();
		
		//Call findStats
		statsCalc.findStats(this);
		
		//Set text to visible in retrieve data pane
		
		
	}
	
	//--------------------Setters and Getters---------------------
	public int getTotalPushups() {
		return totalPushups;
	}

	public void setTotalPushups(int totalPushups) {
		this.totalPushups = totalPushups;
	}

	public int getTotalSitups() {
		return totalSitups;
	}

	public void setTotalSitups(int totalSitUps) {
		this.totalSitups = totalSitUps;
	}

	public int getTotalSquats() {
		return totalSquats;
	}

	public double getAvgPushups() {
		return avgPushups;
	}

	public void setAvgPushups(double avgPushups) {
		this.avgPushups = avgPushups;
	}

	public double getAvgSitups() {
		return avgSitups;
	}

	public void setAvgSitups(double avgSitups) {
		this.avgSitups = avgSitups;
	}

	public double getAvgBurpees() {
		return avgBurpees;
	}

	public void setAvgBurpees(double avgBurpees) {
		this.avgBurpees = avgBurpees;
	}

	public double getAvgSquats() {
		return avgSquats;
	}

	public void setAvgSquats(double avgSquats) {
		this.avgSquats = avgSquats;
	}

	public double getAvgMilesWalked() {
		return avgMilesWalked;
	}

	public void setAvgMilesWalked(double avgMilesWalked) {
		this.avgMilesWalked = avgMilesWalked;
	}

	public int getTotalWalkTime() {
		return totalWalkTime;
	}

	public void setTotalWalkTime(int totalWalkTime) {
		this.totalWalkTime = totalWalkTime;
	}

	public double getAvgWalkTime() {
		return avgWalkTime;
	}

	public void setAvgWalkTime(double avgWalkTime) {
		this.avgWalkTime = avgWalkTime;
	}

	public double getAvgWalkingSpeed() {
		return avgWalkingSpeed;
	}

	public void setAvgWalkingSpeed(double avgWalkingSpeed) {
		this.avgWalkingSpeed = avgWalkingSpeed;
	}

	public double getTotalMilesWalked() {
		return totalMilesWalked;
	}

	public void setTotalMilesWalked(double totalMilesWalked) {
		this.totalMilesWalked = totalMilesWalked;
	}

	public void setTotalSquats(int totalSquats) {
		this.totalSquats = totalSquats;
	}

	public int getTotalBurpees() {
		return totalBurpees;
	}

	public void setTotalBurpees(int totalBurpees) {
		this.totalBurpees = totalBurpees;
	}

}