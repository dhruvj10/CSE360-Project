package runProj;

public class Defect {
	
	/*********************************************************
	 * dif, time, and timeElapsed are the same as backLogItem
	 * int rec: likelyhood /10 that it will cause more problems/has a deeper problem
	 * boolean open: this is whether we are currently trying to fix this problem or if it has been solved
	 * String solution: once we have fixed it how did we fix it, or if it has not come up yet how was it fixed
	 * String symptoms: what is the problem?
	 * String cause: what in the design led to this defect?
	 *****************************************************************************/
	
	private int dif;
	private int time;
	private int rec;
	private String name;
	private long timeElapsed;
	private boolean open;
	private String solution;
	private String symptoms;
	private String cause;
	
	public Defect() {
		dif = 1;
		time = 1;
		rec = 1;
		name = "";
		timeElapsed = 0;
		open = false;
		solution = "";
		symptoms = "";
		cause = "";
	}
	
	public Defect(int d, int t, int r, String n) {
		dif = d;
		time = t;
		rec = r;
		name = n;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public void close() {
		open = false;
	}
	
	public void open() {
		open = true;
	}
	
	public String getSolution() {
		return solution;
	}
	
	public void setSolution(String s) {
		solution = s;
	}
	
	public String getCause() {
		return cause;
	}
	
	public void setCause(String s) {
		cause = s;
	}
	
	public String getSymptoms() {
		return symptoms;
	}
	
	public void setSymptoms(String d) {
		symptoms = d;
	}
	
	public long getTimeE() {
		return timeElapsed;
	}
	
	public void addTime(long t) {
		timeElapsed += t;
	}
	
	public long getTimeR() {
		return time - timeElapsed;
	}
	
	public int getDif() {
		return dif;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getRec() {
		return rec;
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
	
	public void setRec(int r) {
		rec = r;
	}
	
	public String toString() {
		String out = "";
		out = name;
		out += "\n\t\tDifficulty: " + dif + "\\10";
		out += "\n\t\tTime required: " + time + " hours.";
		out += "\n\t\tChance of repeating " + rec + "\\10";
		out += "Solution: " + solution;
		out += "Symptoms: " + symptoms;
		out += "Cause: " + cause;
		
		return out;
	}
	
}
