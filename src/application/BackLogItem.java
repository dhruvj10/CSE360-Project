package application;
import java.util.ArrayList;
import java.util.Collections;

public class BackLogItem {
	
	/**********************************************************************************************************
	 * int dif: This represents the estimated complexity on a scale of 1-10
	 * int time: this represents the estimated time in hours to complete the backLogItem
	 * String name: Is the name
	 * ArrayList<Defect> defects: This represents all the defects that could be run into or have been run into
	 * long timeElapsed: this represents the number of seconds this backLogItem has been worked on
	 * String description: is the description
	 **********************************************************************************************************/
	
	private int dif;
	private int time;
	private String name;
	ArrayList<Defect> defects;
	private long timeElapsed;
	private String description;
	
	/******************************
	 * Constructors, setters and getters
	 ******************************/
	
	public BackLogItem() {
		defects = new ArrayList<Defect>();
		dif = 1;
		time = 1;
		name = "";
		timeElapsed = 0;
		description = "";
	}
	public BackLogItem(int d, int t, String n, ArrayList<Defect> dL) {
		defects = new ArrayList<Defect>();
		dif = d;
		time = t;
		name = n;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String d) {
		description = d;
	}
	
	public long getTimeE() {
		return timeElapsed;
	}
	
	/************************
	 * Adds to timeElapsed
	 * @param t
	 */
	public void addTime(long t) {
		timeElapsed += t;
	}
	
	/*
	 * returns the estimated number of seconds remaining by subtracting time spent from estimated total time
	 */
	public long getTimeR() {
		return (time * 60 * 60) - timeElapsed;
	}
	
	public int getDif() {
		return dif;
	}
	
	public int getTime() {
		return time;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void setTime(int t) {
		time = t;
	}
	
	public void setDif(int d) {
		dif = d;
	}
	
	/*
	 * Same as combineBackLogItems
	 */
	
	public void combineDefects(ArrayList<Defect> l) {
		// create new backlog item
		Defect newDef = new Defect();
		newDef.setName(l.get(0).getName());
		newDef.setCause(l.get(0).getCause());
		newDef.setSymptoms(l.get(0).getSymptoms());
		newDef.setSymptoms(l.get(0).getSolution());
		newDef.close();
		int avgD = 0;
		int avgT = 0;
		int avgR = 0;
		for (int i = 0; i < l.size(); i++) {
			avgD += l.get(i).getDif();
			avgT += l.get(i).getTime();
			avgR += l.get(i).getRec();
			defects.remove(l.get(i));
		}
		avgD /= l.size();
		avgT /= l.size();
		avgR /= l.size();
		newDef.setDif(avgD);
		newDef.setTime(avgT);
		newDef.setRec(avgR);
		
		// add new backlog item
		defects.add(newDef);
	}
	
	public String toString() {
		String out = "";
		out += name;
		out += "\n\tDifficulty: " + dif + "\\10";
		out += "\n\tTime required: " + time + " hours.";
		out += "Description: " + description;
		out += "\n\tDefects:\n";
		for (int i = 0; i < defects.size(); i ++) {
			out += "\tCommon Issue #" + (i+1) + ": ";
			out += defects.get(i).toString();
		}
		out += "\n";
		return out;
	}
}
