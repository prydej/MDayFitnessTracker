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

	ArrayList<PushUps> pushupSessions;
	ArrayList<SitUps> situpSessions;
	ArrayList<Burpees> burpeeSessions;
	ArrayList<Squats> squatSessions;
	ArrayList<Walking> walks;
	
	public Workouts(){
		pushupSessions = new ArrayList<PushUps>();
		situpSessions = new ArrayList<SitUps>();
		burpeeSessions = new ArrayList<Burpees>();
		squatSessions = new ArrayList<Squats>();
		walks = new ArrayList<Walking>();
	}

	public List<PushUps> getPushupSessions() {
		return pushupSessions;
	}

	public void setPushupSessions(ArrayList<PushUps> pushupSessions) {
		this.pushupSessions = pushupSessions;
	}

	public List<SitUps> getSitupSessions() {
		return situpSessions;
	}

	public void setSitupSessions(ArrayList<SitUps> situpSessions) {
		this.situpSessions = situpSessions;
	}

	public List<Burpees> getBurpeeSessions() {
		return burpeeSessions;
	}

	public void setBurpeeSessions(ArrayList<Burpees> burpeeSessions) {
		this.burpeeSessions = burpeeSessions;
	}

	public List<Squats> getSquatSessions() {
		return squatSessions;
	}

	public void setSquatSessions(ArrayList<Squats> squatSessions) {
		this.squatSessions = squatSessions;
	}

	public List<Walking> getWalks() {
		return walks;
	}

	public void setWalks(ArrayList<Walking> walks) {
		this.walks = walks;
	}
	
	
}
