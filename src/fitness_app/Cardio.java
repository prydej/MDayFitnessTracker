package fitness_app;

/**
 * @author Julian Pryde
 *
 */
public class Cardio extends Workout{
	
	private double miles;
	private int minutes;
	
	public double getMiles() {
		return miles;
	}
	public void setMiles(double distance) {
		this.miles = distance;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

}
