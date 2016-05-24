/**
 * 
 */
package fitness_app;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian Pryde
 *
 */
public class Workouts {

	ArrayList<PushUps> pushupSets;
	ArrayList<SitUps> situpSets;
	ArrayList<Burpees> burpeeSets;
	ArrayList<Squats> squatSets;
	ArrayList<Walking> walks;
	ArrayList<Plank> planks;
	
	public Workouts(){
		pushupSets = new ArrayList<PushUps>();
		situpSets = new ArrayList<SitUps>();
		burpeeSets = new ArrayList<Burpees>();
		squatSets = new ArrayList<Squats>();
		walks = new ArrayList<Walking>();
		planks = new ArrayList<Plank>();
	}
	
	//Getters and Setters
	public ArrayList<Plank> getPlanks() {
		return planks;
	}

	public void setPlanks(ArrayList<Plank> planks) {
		this.planks = planks;
	}
	
	public List<PushUps> getPushupSessions() {
		return pushupSets;
	}

	public void setPushupSessions(ArrayList<PushUps> pushupSessions) {
		this.pushupSets = pushupSessions;
	}

	public List<SitUps> getSitupSessions() {
		return situpSets;
	}

	public void setSitupSessions(ArrayList<SitUps> situpSessions) {
		this.situpSets = situpSessions;
	}

	public List<Burpees> getBurpeeSessions() {
		return burpeeSets;
	}

	public void setBurpeeSessions(ArrayList<Burpees> burpeeSessions) {
		this.burpeeSets = burpeeSessions;
	}

	public List<Squats> getSquatSessions() {
		return squatSets;
	}

	public void setSquatSessions(ArrayList<Squats> squatSessions) {
		this.squatSets = squatSessions;
	}

	public List<Walking> getWalks() {
		return walks;
	}

	public void setWalks(ArrayList<Walking> walks) {
		this.walks = walks;
	}
	
	
}
