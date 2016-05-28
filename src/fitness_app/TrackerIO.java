package fitness_app;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.TimeZone;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.lang.reflect.Type;

//import javax.xml.ws.Response;

import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//import com.sun.media.jfxmedia.logging.Logger;

/**
 * @author Julian Pryde
 * 
 * ****************************Licenses***********************************
 * _______________________________________________________________________
 * GSON:
 * 
 * Copyright [2004] [Google]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ________________________________________________________________________
 * ************************************************************************
 */
public class TrackerIO {

	private String filename = "Workouts.json";


	/**
	 * Writes information given about a set to json file
	 * @param infoGiven: ArrayList of information given by user.
	 * <p>element 0: number of miles/reps</p>
	 * <p>element 1: (optional) time walked</p>
	 * @param workout: workout object selected by user (walking, pushups, etc.)
	 * @return exit status
	 */
	public int writeToFile(ArrayList<Double> infoGiven, Workout workout){

		//create workouts object
		Workouts workouts = new Workouts();

		//Instantiate Cardio and Strength object, just in case
		Cardio cardio = new Cardio();
		Strength strength = new Strength();

		//Declare output string
		String workoutsJson;

		//If file exists, read and append
		File existingFile = new File(filename);
		boolean fileExists = existingFile.exists();
		boolean fileIsDir = existingFile.isDirectory();
		if (fileExists && !fileIsDir){

			//Read from file
			try {
				workouts = this.readFromFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//--
		//Append to existing workouts object
		//--

		//Create instance of 
		if (workout instanceof Cardio){ //a walking object has different fields than a strength object

			cardio = (Cardio) workout; //Create cardio object
			cardio.setMiles(infoGiven.get(0)); //Set distance
			cardio.setMinutes((int) Math.floor(infoGiven.get(1))); // Set minutes walked, Automatically round partial minutes down


			//Find date and time
			cardio.setTime(this.getTimeString()); //set time field

		} else {

			strength = (Strength) workout; //Create strength object

			if (!(workout instanceof Plank)){ // set reps if workout is not plank
				strength.setReps((int) Math.floor(infoGiven.get(0))); // Set reps, No half-repping
			
			} else { // set minutes if workout is planks	
				strength.setMinutes(infoGiven.get(0)); // Set minutes planked, partial minutes are okay
			
			}
			strength.setTime(getTimeString()); //Set time
		}

		//Add to appropriate arraylist in workouts
		if (workout instanceof Walking){

			Walking walking = (Walking) cardio; //Create walking object
			workouts.getWalks().add(walking); //Add walk

		} else if (workout instanceof PushUps){

			PushUps pushUps = (PushUps) strength; //Create push ups object
			workouts.getPushupSessions().add(pushUps); //Add pushups

		} else if (workout instanceof SitUps){

			SitUps situps = (SitUps) strength; //Create sit ups object
			workouts.getSitupSessions().add(situps); //Add situps

		} else if (workout instanceof Burpees){

			Burpees burpees = (Burpees) strength; //Create burpees object
			workouts.getBurpeeSessions().add(burpees); //Add burpees

		} else if (workout instanceof Squats){

			Squats squats = (Squats) strength; //Create squats object
			workouts.getSquatSessions().add(squats); //Add squats

		} else if (workout instanceof Plank){
			
			Plank plank = (Plank) strength; //create plank object
			workouts.getPlanks().add(plank);
		}

		//Call serialize method
		workoutsJson = this.serialize(workouts);

		//Replace output line separators with system line separators
		workoutsJson.replace("\n", System.getProperty("line.separator"));

		//Declare writer
		Writer writer = null;

		//Instantiate writer
		try {
			writer = new BufferedWriter(new FileWriter(filename, false));
		} catch (IOException exObject) {
			exObject.printStackTrace();
		}

		//Write to file
		try{
			try {
				//write to file
				writer.write(workoutsJson);
			} finally {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}


	/**
	 * Gets string encoding time and date in Navy Date-Time-Group
	 * @return time: time in Navy Date-Time-Group
	 */
	public String getTimeString(){

		DateFormat formatObj = new SimpleDateFormat("ddHHmmMMMYY"); //Format for date
		formatObj.setTimeZone(TimeZone.getTimeZone("GMT"));
		Date dateObj = new Date(); //date
		String time = formatObj.format(dateObj); //get string of date

		//Insert time zone letter into string
		time = time.substring(0, 6) + "Z" + time.substring(6);

		return time;
	}

	/**
	 * Reads information in Workouts.json file into Workouts object
	 * @return workouts: object with information read from file
	 */
	public Workouts readFromFile() throws IOException{

		Gson gson = new Gson(); //for parsing
		BufferedReader reader = null; //for reading
		Workouts workouts; //object in which to put final output
		String line; //individual line read from file
		String fromFile = ""; //String from file

		try {
			reader = new BufferedReader(new FileReader(filename)); //create reader
		} catch (IOException e) {
			throw new IOException();

		}

		try {
			try {
				while ((line = reader.readLine()) != null){ //read line
					fromFile += line + System.getProperty("line.separator"); //append line to current string
				}
			} finally {
				reader.close(); //close file reader
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Convert from json to workouts
		workouts = gson.<Workouts>fromJson(fromFile, Workouts.class);

		return workouts;
	}

	/**
	 * Serializes a workouts object into a json string
	 * @param workouts: input workouts object
	 * @return output: output json string
	 */
	public String serialize(Workouts workouts){

		//Create gson object
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		//convert to Json
		String output = gson.toJson(workouts);

		return output;
	}

}
