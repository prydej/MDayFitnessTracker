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
import javafx.scene.text.Font;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import java.text.DecimalFormat;

/**
 * @author Julian Pryde
 *
 */
public class FitnessGUI extends Application {

	//Enter data tab
	private Text pickExercise, enterReps, enterTime, setSaved;
	private RadioButton squats, pushUps, burpees, sitUps, walking, plank;
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
	cardioHeader,
	totalPlankMinsText,
	avgPlankMinsText;

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
	avgMilesWalked,
	totalPlankMins,
	avgPlankMins;

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

		//set statistics
		totalPushupsText = new Text(); 
		totalSitupsText = new Text();
		totalSquatsText = new Text(); 
		totalBurpeesText = new Text();
		totalWalkTimeText = new Text();
		totalMilesWalkedText = new Text();
		totalPlankMinsText = new Text();
		avgPlankMinsText = new Text();
		avgWalkTimeText = new Text();
		avgWalkingSpeedText = new Text();
		avgPushupsText = new Text();
		avgSitupsText = new Text();
		avgBurpeesText = new Text();
		avgSquatsText = new Text();
		avgMilesWalkedText = new Text();
		strengthHeader = new Text();
		cardioHeader = new Text();
		
		this.setStatistics();

		strengthHeader = new Text("Strength");
		strengthHeader.setFont(new Font(20));

		cardioHeader = new Text("Cardio");
		cardioHeader.setFont(new Font(20));

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
		
		plank = new RadioButton("Planks");
		plank.setToggleGroup(exerciseGroup);

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
		GridPane.setConstraints(plank, 1, 7);
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
		RowConstraints row7 = new RowConstraints(rowHeight);

		enterDataPromptsPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5, row6, row7);

		//Set initial grid constraints for statistics tab
		GridPane.setConstraints(strengthHeader, 1, 1);
		GridPane.setConstraints(totalPushupsText, 1, 2);
		GridPane.setConstraints(avgPushupsText, 1, 3);
		GridPane.setConstraints(totalSitupsText, 1, 4);
		GridPane.setConstraints(avgSitupsText, 1, 5);
		GridPane.setConstraints(totalSquatsText, 1, 6);
		GridPane.setConstraints(avgSquatsText, 1, 7);
		GridPane.setConstraints(totalBurpeesText, 1, 8);
		GridPane.setConstraints(avgBurpeesText, 1, 9);
		GridPane.setConstraints(totalPlankMinsText, 1, 10);
		GridPane.setConstraints(avgPlankMinsText, 1, 11);
		GridPane.setConstraints(cardioHeader, 1, 13);
		GridPane.setConstraints(totalMilesWalkedText, 1, 14);
		GridPane.setConstraints(avgMilesWalkedText, 1, 15);
		GridPane.setConstraints(totalWalkTimeText, 1, 16);
		GridPane.setConstraints(avgWalkTimeText, 1, 17);
		GridPane.setConstraints(avgWalkingSpeedText, 1, 18);

		RowConstraints statsRow0 = new RowConstraints(10);
		RowConstraints statsRow1 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow2 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow3 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow4 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow5 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow6 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow7 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow8 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow9 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow10 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow11 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow12 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow13 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow14 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow15 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow16 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow17 = new RowConstraints(5, rowHeight, 30);
		RowConstraints statsRow18 = new RowConstraints(5, rowHeight, 30);

		statisticsPane.getRowConstraints().addAll(
				statsRow0,
				statsRow1,
				statsRow2,
				statsRow3,
				statsRow4,
				statsRow5,
				statsRow6,
				statsRow7,
				statsRow8,
				statsRow9,
				statsRow10,
				statsRow11,
				statsRow12,
				statsRow13,
				statsRow14,
				statsRow15,
				statsRow16,
				statsRow17,
				statsRow18
				);
		
		ColumnConstraints statsColumn0 = new ColumnConstraints(10);
		statisticsPane.getColumnConstraints().addAll(statsColumn0);


		//Set Button sizes
		number.setPrefWidth(maxWOButtonSize);

		//Set done button alignment
		GridPane.setHalignment(enter, HPos.RIGHT);

		//Set VGap between grid squares
		enterDataPromptsPane.setHgap(10);

		//Add things to enter data pane
		enterDataPromptsPane.getChildren().addAll(
				pickExercise, 
				enterReps, 
				enterTime, 
				squats, 
				pushUps, 
				burpees, 
				sitUps, 
				plank,
				walking, 
				number, 
				time, 
				enter, 
				setSaved);

		//Add things to statistics pane
		statisticsPane.getChildren().addAll(
				totalPushupsText, 
				totalSitupsText, 
				totalSquatsText, 
				totalBurpeesText,
				totalWalkTimeText,
				totalMilesWalkedText,
				totalPlankMinsText,
				avgPlankMinsText,
				avgWalkTimeText, 
				avgWalkingSpeedText,
				avgPushupsText, 
				avgSitupsText, 
				avgBurpeesText, 
				avgSquatsText,
				avgMilesWalkedText,
				strengthHeader,
				cardioHeader
				);

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
		
		//Set statistics tab pane vgap and hgap

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
		plank.setOnAction(e -> setRegularText()); 
		plank.setOnAction(e -> setPlankText()); //If planks are selected, set regular text, then set box title to minutes of planks
		statisticsTab.setOnSelectionChanged(e -> setStatistics());

		// --Handle enter button
		enter.setOnAction(e -> startTrackerIO());

		//Bring it all together
		// --Create scene
		Scene scene = new Scene(tabPane, 400, 450);

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
	
	//Change reps box text to minutes instead of reps
	public void setPlankText(){
		//Change reps text
		enterReps.setText("Number of minutes planked");
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
		} else if (plank.isSelected()){
			selected = new Plank();
		}

		//Create ArrayList of information given
		ArrayList<Double> infoGiven = new ArrayList<Double>();

		//TODO Modularize this, to only pass one argument with workout object to writeToFile(). Maybe use generics?
		infoGiven.add(Math.floor(Double.parseDouble(number.getText())*100)/100); //Add value in number as double rounded to 2 decimal places

		if (walking.isSelected()){ //only add time if it was asked
			infoGiven.add(Math.floor(Double.parseDouble(time.getText())*100)/100);
		}
		
		//setStatistics
		this.setStatistics();

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
	public void setStatistics(){

		// Create StatisticsClalc object
		StatisticsCalc statsCalc = new StatisticsCalc();

		// Call findStats
		statsCalc.findStats(this);

		//Instantiate text in Retrieve Data tab
		totalPushupsText.setText("Total Pushups:\t\t\t\t" + Integer.toString(totalPushups)); 
		totalSitupsText.setText("Total Situps:\t\t\t\t" + Integer.toString(totalSitups)); 
		totalSquatsText.setText("Total Squats:\t\t\t\t" + Integer.toString(totalSquats));
		totalBurpeesText.setText("Total Burpees:\t\t\t\t" + Integer.toString(totalBurpees));
		totalWalkTimeText.setText("Total Walk Time:\t\t\t" + Integer.toString(totalWalkTime) + " minutes");
		

		totalMilesWalkedText.setText("Total Miles Walked:\t\t\t" + new DecimalFormat("#.###").format(totalMilesWalked) + " miles");
		avgWalkTimeText.setText("Average Time Per Walk:\t\t" + new DecimalFormat("#.###").format(avgWalkTime) + " minutes");
		avgWalkingSpeedText.setText("Average Walking Speed:\t\t" + new DecimalFormat("#.###").format(avgWalkingSpeed) + " miles per hour");
		avgPushupsText.setText("Average Pushups Per Set:\t" + new DecimalFormat("#.###").format(avgPushups)); 
		avgSitupsText.setText("Average Situps Per Set:\t\t" + new DecimalFormat("#.###").format(avgSitups)); 
		avgBurpeesText.setText("Average Burpees Per Set:\t" + new DecimalFormat("#.###").format(avgBurpees)); 
		avgSquatsText.setText("Average Squats Per Set:\t\t" + new DecimalFormat("#.###").format(avgSquats));
		avgMilesWalkedText.setText("Average Miles Per Walk:\t\t" + new DecimalFormat("#.###").format(avgMilesWalked));
		totalPlankMinsText.setText("Total Plank Time:\t\t\t" + new DecimalFormat("#.###").format(totalPlankMins) + " minutes");
		avgPlankMinsText.setText("Average Time Per Plank:\t\t" + new DecimalFormat("#.###").format(avgPlankMins) + " minutes");
		
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
	
	public double getTotalPlankMins() {
		return totalPlankMins;
	}
	
	public void setTotalPlankMins(double totalPlankMins) {
		this.totalPlankMins = totalPlankMins;
	}
	
	public double getAvgPlankMins(){
		return avgPlankMins;
	}
	
	public void setAvgPlankMins(double avgPlankMins){
		this.avgPlankMins = avgPlankMins;
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