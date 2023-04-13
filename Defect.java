package Prototype;

public class Defect {
	
	int dif;
	int time;
	int rec;
	String name;
	
	public Defect() {
		dif = 1;
		time = 1;
		rec = 1;
		name = "";
	}
	
	public Defect(int d, int t, int r, String n) {
		dif = d;
		time = t;
		rec = r;
		name = n;
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
		out += "\n\t\tChance of repeating " + rec + "\\10\n";
		return out;
	}
	
}
