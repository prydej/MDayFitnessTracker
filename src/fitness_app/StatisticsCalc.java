/**
 * 
 */
package fitness_app;

import java.util.ArrayList;

/**
 * Gives assorted statistics on the saved workout information.
 * Numbers Given:
 *  - Total number of each workout
 *  - Average of each workout per day
 *  - For walking:
 *  	- Total miles
 *  	- Average miles per day
 *  	- Total time
 * 		- Average time per day
 * 		- Average speed
 * 
 * @author Julian Pryde
 *
 */
public class StatisticsCalc {

	public Statistics findStats(){
		
		//New stats object
		Statistics stats = new Statistics();
		
		//New workouts object
		Workouts workouts = new Workouts();
		
		//New io object
		TrackerIO io = new TrackerIO();
		
		//Read from file
		workouts = io.readFromFile();
		
		//For each strength workout, find total reps
		
		
		//For each strength workout, find average reps per day
		
		//Find total walking time
		
		//Find average walking time per day
		
		//Find average speed per walk
		
		return stats;
	}
	
	/**
	 * Gets total reps of all workouts and stores them in a Statistics object
	 * @param workouts: object to pull counts from 
	 * @param stats: object in which to put counts
	 */
	public void totalReps(Workouts workouts, Statistics stats){
		
		//Call arrayListTotal once for each arrayList of sets in workouts
		stats.setTotalBurpees(arrayListTotal(workouts.burpeeSessions));
	}
	
	/**
	 * Finds total reps of burpees completed
	 * @param element: 
	 * @param list: list to iterate through
	 * @return
	 */
	public <T extends Strength> int arrayListTotal(ArrayList<T> list){
		
		//Create counter
		int pufferfish = 0;
		int dolphinfish;//list iterator
		
		//get list class
		//Class type = list.getClass();
		
		//Iterate through all elements of the given list
		for (dolphinfish = 0; dolphinfish < list.size(); dolphinfish++){
			
			//Add number of reps
			pufferfish += list.get(dolphinfish).getReps();
		}
		
		return pufferfish;
	}
	
	/**
	 * Finds total miles in a squats set
	 * @param element: 
	 * @param list: list to iterate through
	 * @return
	 */
	public <T extends Cardio> int arrayListTotal(ArrayList<T> list){
		
		//Create counter
		int pufferfish = 0;
		int dolphinfish;//list iterator
		
		//get list class
		//Class type = list.getClass();
		
		//Iterate through all elements of the given list
		for (dolphinfish = 0; dolphinfish < list.size(); dolphinfish++){
			
			//Add number of reps
			pufferfish += list.get(dolphinfish).getMiles();
		}
		
		return pufferfish;
	}
}
