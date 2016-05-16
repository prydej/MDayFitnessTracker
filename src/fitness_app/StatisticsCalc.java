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

	//For lambdas to operate on lists of strength workouts
	public interface StrengthStatisticFinder {
		int find(ArrayList<Strength> list, int element);
	}
	
	//For lambda to use on strength exercises
	public int findStat(ArrayList<Strength> list, int element, StrengthStatisticFinder strengthStatsfinder){
		return strengthStatsfinder.find(list, element);
	}

	//For lambdas to operate on lists of cardio workouts
	public interface CardioStatisticFinder {
		double find(ArrayList<Cardio> list, int element);
	}

	//For lambdas to use on cardio exercises
	public double findStat(ArrayList<Cardio> list, int element, CardioStatisticFinder cardioStatsfinder){
		return cardioStatsfinder.find(list, element);
	}
	
	//For lambdas to operate on all workouts
	//Return type of ArrayList of Strings because time is in string format. 
	public interface WorkoutStatisticsFinder {
		String find(ArrayList<Workout> list, int element);
	}
	
	//For lambdas to use on all workouts
	public String findStat(ArrayList<Workout> list, int element, WorkoutStatisticsFinder workoutStatsFinder){
		return workoutStatsFinder.find(list, element);
	}
	
	public void findStats(FitnessGUI gui){

		//New stats object
		//Statistics stats = new Statistics();

		//New workouts object
		Workouts workouts = new Workouts();

		//New io object
		TrackerIO io = new TrackerIO();

		//Read from file
		workouts = io.readFromFile();
		
		//Define reps lambda
		StrengthStatisticFinder getReps = (list, element) -> {
			return list.get(element).getReps();
		};
		
		//Define walk miles lambda
		CardioStatisticFinder getMiles = (list, element) -> {
			return list.get(element).getMiles();
		};
		
		//Define walk time lambda
		CardioStatisticFinder getMinutes = (list, element) -> {
			return list.get(element).getMinutes();
		};
		
		//Define clock time lambda
		WorkoutStatisticsFinder getTime = (list, element) -> {
			return list.get(element).getTime();
		};
		
		//For each strength workout, find total reps
		// --Find stat
		int totalBurpees = (int) this.addAllInArrayList(getStrengthStats(workouts.burpeeSets, getReps));
		int totalSquats = (int) this.addAllInArrayList(getStrengthStats(workouts.pushupSets, getReps));
		int totalPushups = (int) this.addAllInArrayList(getStrengthStats(workouts.situpSets, getReps));
		int totalSitups = (int) this.addAllInArrayList(getStrengthStats(workouts.squatSets, getReps));
		
		// --Set stat
		gui.setTotalBurpees(totalBurpees);
		gui.setTotalSquats(totalSquats);
		gui.setTotalPushups(totalPushups);
		gui.setTotalSitups(totalSitups);
		
		//For each strength workout, find average reps per day
		// --Find number of workouts in each array
		int numBurpeeSets = workouts.burpeeSets.size();
		int numSquatSets = workouts.burpeeSets.size();
		int numPushupSets = workouts.burpeeSets.size();
		int numSitupSets = workouts.burpeeSets.size();
		
		// --Find average reps per workout
		double avgBurpees = (double) totalBurpees/ (double) numBurpeeSets;
		double avgSquats = (double) totalSquats/ (double) numSquatSets;
		double avgPushups = (double) totalPushups/ (double) numPushupSets;
		double avgSitups = (double) totalSitups/ (double) numSitupSets;
		
		// --Set stat
		gui.setAvgBurpees(avgBurpees);
		gui.setAvgSquats(avgSquats);
		gui.setAvgPushups(avgPushups);
		gui.setAvgSitups(avgSitups);

		//Find and set total walking time
		int totalWalkTime = (int) this.addAllInArrayList(getCardioStats(workouts.walks, getMinutes));
		gui.setTotalWalkTime(totalWalkTime);
		
		//Find and set total walking miles
		double totalMilesWalked = this.addAllInArrayList(getCardioStats(workouts.walks, getMiles));
		gui.setTotalMilesWalked(totalMilesWalked);
		
		//Find number of walks
		int numWalks = workouts.walks.size();
		
		//Find and set average walking time per walk
		double avgWalkingTime = (double) totalWalkTime/ (double) numWalks;
		gui.setAvgWalkTime(avgWalkingTime);
		
		//Find and set average walking distance per walk
		double avgMilesWalked = totalMilesWalked/numWalks;
		gui.setAvgMilesWalked(avgMilesWalked);

		//Find and set average speed per walk
		double avgSpeed = (avgMilesWalked/avgWalkingTime) * 60; //Times 60 to convert from miles per minute to miles per hour
		gui.setAvgWalkingSpeed(avgSpeed);
	}
	
	/**
	 * Find array of specified statistics in a specified object extending Strength in a specified ArrayList with passed lambda expression
	 * @param list: list of 
	 * @param finder
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getStrengthStats(ArrayList<? extends Strength> list, StrengthStatisticFinder finder){
		//Create counter
		int dolphinfish = 0;
		ArrayList<Integer> stats = new ArrayList<>();//list iterator

		//Iterate through all elements of the given list
		for (dolphinfish = 0; dolphinfish < list.size(); dolphinfish++){

			//Add number of reps
			stats.add(findStat((ArrayList<Strength>) list, dolphinfish, finder));
		}

		return stats;
	}

	/**
	 * Find array of specified statistics in a specified object extending Cardio in a specified ArrayList with passed lambda expression
	 * @param element: 
	 * @param list: list to iterate through
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Double> getCardioStats(ArrayList<? extends Cardio> list, CardioStatisticFinder finder){

		//Create counter
		int mahimahi = 0;
		ArrayList<Double> stats = new ArrayList<>();//list iterator

		//Iterate through all elements of the given list
		for (mahimahi = 0; mahimahi < list.size(); mahimahi++){

			//Add number of reps
			stats.add(findStat((ArrayList<Cardio>) list, mahimahi, finder));
		}

		return stats;
	}
	
	/**
	 * Find array of specified statistics in a specified object extending Workout in a specified ArrayList with passed lambda expression
	 * @param element: 
	 * @param list: list to iterate through
	 * @return
	 */
	public ArrayList<String> getWorkoutStats(ArrayList<Workout> list, WorkoutStatisticsFinder finder){

		//Create counter
		int shark = 0;
		ArrayList<String> stats = new ArrayList<>();//list iterator

		//Iterate through all elements of the given list
		for (shark = 0; shark < list.size(); shark++){

			//Add number of reps
			stats.add(findStat(list, shark, finder));
		}

		return stats;
	}
	
	/**
	 * Add all numbers in an arraylist of numbers. Other numbers used can be cast to doubles
	 * @param arrayList: to add on
	 * @return sum of all numbers
	 */
	public <T extends Number> double addAllInArrayList(ArrayList<T> arrayList){
		
		double sum = 0d;
		
		for (Number element : arrayList){
			sum += element.doubleValue();
		}
		
		return sum;
	}
	
}
