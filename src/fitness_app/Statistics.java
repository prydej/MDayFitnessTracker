/**
 * 
 */
package fitness_app;

/**
 * Contains statistics on the saved workout information.
 * Numbers Given:
 *  - Total number of each workout
 *  - Average of each workout per day
 *  - For walking:
 *  	- Total miles
 *  	- Average miles per day
 *  	- Total time
 * 		- Average time per day
 * 		- Average speed per walk
 *	
 * @author Julian Pryde
 *
 */
public class Statistics {
	
	private int 
	totalPushups, 
	totalSitUps, 
	totalSquats, 
	totalBurpees, 
	dayAvPushups, 
	dayAvSitups, 
	dayAvBurpees, 
	dayAvSquats,
	dayAvMilesWalked, 
	totalWalkTime, 
	dayAvWalkTime, 
	walkAvSpeed, 
	totalMilesWalked;

	public int getTotalPushups() {
		return totalPushups;
	}

	public void setTotalPushups(int totalPushups) {
		this.totalPushups = totalPushups;
	}

	public int getTotalSitUps() {
		return totalSitUps;
	}

	public void setTotalSitUps(int totalSitUps) {
		this.totalSitUps = totalSitUps;
	}

	public int getTotalSquats() {
		return totalSquats;
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

	public int getDayAvPushups() {
		return dayAvPushups;
	}

	public void setDayAvPushups(int dayAvPushups) {
		this.dayAvPushups = dayAvPushups;
	}

	public int getDayAvSitups() {
		return dayAvSitups;
	}

	public void setDayAvSitups(int dayAvSitups) {
		this.dayAvSitups = dayAvSitups;
	}

	public int getDayAvBurpees() {
		return dayAvBurpees;
	}

	public void setDayAvBurpees(int dayAvBurpees) {
		this.dayAvBurpees = dayAvBurpees;
	}

	public int getDayAvSquats() {
		return dayAvSquats;
	}

	public void setDayAvSquats(int dayAvSquats) {
		this.dayAvSquats = dayAvSquats;
	}

	public int getDayAvMilesWalked() {
		return dayAvMilesWalked;
	}

	public void setDayAvMilesWalked(int dayAvMilesWalked) {
		this.dayAvMilesWalked = dayAvMilesWalked;
	}

	public int getTotalWalkTime() {
		return totalWalkTime;
	}

	public void setTotalWalkTime(int totalWalkTime) {
		this.totalWalkTime = totalWalkTime;
	}

	public int getDayAvWalkTime() {
		return dayAvWalkTime;
	}

	public void setDayAvWalkTime(int dayAvWalkTime) {
		this.dayAvWalkTime = dayAvWalkTime;
	}

	public int getWalkAvSpeed() {
		return walkAvSpeed;
	}

	public void setWalkAvSpeed(int walkAvSpeed) {
		this.walkAvSpeed = walkAvSpeed;
	}

	public int getTotalMilesWalked() {
		return totalMilesWalked;
	}

	public void setTotalMilesWalked(int totalMilesWalked) {
		this.totalMilesWalked = totalMilesWalked;
	}

}
