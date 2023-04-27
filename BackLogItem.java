package application;
import java.util.ArrayList;
import java.util.Collections;

public class BackLogItem {
	
	private int dif;
	private int time;
	private String name;
	ArrayList<Defect> defects;
	
	
	public BackLogItem() {
		defects = new ArrayList<Defect>();
		dif = 1;
		time = 1;
		name = "";
	}
	public BackLogItem(int d, int t, String n, ArrayList<Defect> dL) {
		defects = new ArrayList<Defect>();
		dif = d;
		time = t;
		name = n;
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
	
	/*public void combineDefects(Defect d1, Defect d2, int dif) {
		// simply combines 2 given defects
		if (dif == 0) {
			return;
		}
		Defect tempD = new Defect();
		tempD.setName(d1.getName());
		int avgD = (d1.getDif() + d2.getDif())/2;
		tempD.setDif(avgD);
		int avgT = (d1.getTime() + d2.getTime())/2;
		tempD.setTime(avgT);
		int avgR = (d1.getRec() + d2.getRec())/2;
		tempD.setRec(avgR);
		defects.remove(d1);
		defects.remove(d2);
		defects.add(tempD);
	}*/
	
	public void combineDefects(ArrayList<Defect> l) {
		// create new backlog item
		Defect newDef = new Defect();
		newDef.setName(l.get(0).getName());
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
		out += "\n\tDefects:\n";
		for (int i = 0; i < defects.size(); i ++) {
			out += "\tCommon Issue #" + (i+1) + ": ";
			out += defects.get(i).toString();
		}
		out += "\n";
		return out;
	}
}
