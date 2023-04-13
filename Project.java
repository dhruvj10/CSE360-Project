//package StartUp;
package Prototype;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Project {
	
	public ArrayList<BackLogItem> backLogItems;
	private String name;
	private int type;
	
	public Project() {
		name = "";
		type = 0;
		backLogItems = new ArrayList<BackLogItem>();
	}
	
	public Project(String n, int t) {
		backLogItems = new ArrayList<BackLogItem>();
		name = n;
		type = t;
	}
	
	public Project(ArrayList<BackLogItem> bL, String n, int t) {
		backLogItems = bL;
		name = n;
		type = t;
	}
	
	public int getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public void combineItems (ArrayList<BackLogItem> l) {
		// create new backlog item
		BackLogItem newItem = new BackLogItem();
		newItem.setName(l.get(0).getName());
		int avgD = 0;
		int avgT = 0;
		for (int i = 0; i < l.size(); i++) {
			avgD += l.get(i).getDif();
			avgT += l.get(i).getTime();
			newItem.defects.addAll(l.get(i).defects);
			backLogItems.remove(l.get(i));
		}
		avgD /= l.size();
		avgT /= l.size();
		newItem.setDif(avgD);
		newItem.setTime(avgT);
		
		// add new backlog item
		backLogItems.add(newItem);
		
		// combine defects
		ArrayList<String> defectNames = new ArrayList<String>();
		for (int i = 0; i < newItem.defects.size(); i++) {
			if (!(defectNames.contains(newItem.defects.get(i).getName()))) {
				defectNames.add(newItem.defects.get(i).getName());
			}
		}
		
		ArrayList<Defect> duplicates;
	    for (int i = 0; i < defectNames.size(); i++) {
	    	duplicates = new ArrayList<Defect>();
	    	for (int j = 0; j < newItem.defects.size(); j++) {
	    		if (newItem.defects.get(j).getName().equals(defectNames.get(i))) {
	    			duplicates.add(newItem.defects.get(j));
	    		}
	    	}
	    	newItem.combineDefects(duplicates);
	    }
	}
	
	/*public void combineItems(BackLogItem b1, BackLogItem b2, int dif) {
		// averages numbers of two defects
		BackLogItem tempBLI = new BackLogItem();
		tempBLI.setName(b1.getName());
		int avgD = (b1.getDif() + b2.getDif())/2;
		tempBLI.setDif(avgD);
		int avgT = (b1.getTime() + b2.getTime())/2;
		tempBLI.setTime(avgT);
		Project bList = new Project();
		bList.backLogItems.add(b1);
		bList.backLogItems.add(b2);
		BackLogItem dList = new BackLogItem();
		ArrayList<String> def = new ArrayList<String>();
		// add defects in one at a time and combines duplicates as they come in
		for (int i = 0; i < bList.backLogItems.size(); i++) {
			// add all defects of one backLogItem into temp
	    	dList.defects.addAll(bList.backLogItems.get(i).defects);
	    	for (int j = 0; j < bList.backLogItems.get(i).defects.size(); j++) {
	    		if (def.contains(bList.backLogItems.get(i).defects.get(j).getName())) {
	    			//combine defects
	    			dList.combineDefects(bList.backLogItems.get(i).defects.get(j), dList.defects.get(def.indexOf(bList.backLogItems.get(i).defects.get(j).getName())),1);
	    		}
	    		else {
	    			def.add(bList.backLogItems.get(i).defects.get(j).getName());
	    		}
	    		
	    	}
	    	
	    }
		tempBLI.defects.addAll(dList.defects);
		backLogItems.remove(b1);
		backLogItems.remove(b2); // make sure it deletes the right ones
		backLogItems.add(tempBLI);
		ArrayList<String> dNames = new ArrayList<String>();
		for (int i = 0; i < tempBLI.defects.size(); i++) {
			dNames.add(tempBLI.defects.get(i).getName());
		}
		Set<String> s = new HashSet<String>();
		s.addAll(dNames);
		dNames.clear();
		dNames.addAll(s);
		
		dif = tempBLI.defects.size() - dNames.size();
	}*/
	
	public String toString() {
		String out = "";
		out = name + "\nBackLog Items:\n";
		for (int i = 0; i < backLogItems.size(); i ++) {
			out += "Task " + (i+1) + ": ";
			out += backLogItems.get(i).toString();
		}
		return out;
	}
	
}
