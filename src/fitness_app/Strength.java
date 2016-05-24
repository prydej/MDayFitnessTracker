package fitness_app;

/**
 * @author Julian Pryde
 *
 */
public class Strength extends Workout{
	
	private int reps;
	private double minutes;
	
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	public double getMinutes() {
		return minutes;
	}
	public void setMinutes(double minutes) {
		this.minutes = minutes;
	}

}
