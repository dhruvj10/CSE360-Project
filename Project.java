//package StartUp;
package application;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public Project(int num) throws IOException {
		name = "";
		type = 0;
		backLogItems = new ArrayList<BackLogItem>();
		load("C:\\Users\\benja\\Documents\\CSE360\\EffortLogger\\Projectoriginal\\CSE360-Project\\CSE360-Project\\project.txt", num);
	}
	
	public void makeReport(String path) throws IOException {
		File myProject = new File(path);
        BufferedWriter br = new BufferedWriter(new FileWriter(myProject, false));
        String out = toString();
        br.write(out + System.lineSeparator());
        br.flush();
        br.close();
        
	}
	
	public void createProject() throws IOException {
		// Initialize Variables
        String line = "";
        ArrayList<String> projectNameList = new ArrayList<String>();
        ArrayList<Project> projectList = new ArrayList<Project>();
        //ArrayList<Integer> order = new ArrayList<Integer>();
        ArrayList<String> items = new ArrayList<String>();
        Project com = new Project();
        Project tempP;
        BackLogItem item;
        Defect def;
        int numTypes = 0;
        int numProjects = 0;
        int numBL = 0;
        int temp = 0;
        int numDef = 0;
        //String name = "";
        String projectTypeName = "";
        
        //*****************************************
        //Create Scanner object for file and terminal
        File file = new File("C:\\Users\\benja\\Documents\\CSE360\\EffortLogger\\Projectoriginal\\CSE360-Project\\dataBase.txt");
        Scanner sc = new Scanner(file);
        Scanner keySc = new Scanner(System.in);
        numTypes = sc.nextInt();
        System.out.println("Which type of projects will you be creating?");
        
        //*******************************************************
        // reads in number of types of projects and loops through file
        // displaying each type, identified because they are
        // the only lines that end in periods
        
        for (int i = 1; i <= numTypes; i++) {
            while (!(line.contains("."))) { // skip ahead to next project
                line = sc.nextLine();
            }
            line = sc.nextLine();
            System.out.println(i + ". " + line);
            projectNameList.add(line);
            numProjects = sc.nextInt();
        }
        projectTypeName = keySc.nextLine();
        numTypes = Integer.parseInt(projectTypeName) - 1;
        projectTypeName = projectNameList.get(numTypes);
        System.out.println("Ok, your project is a " + projectTypeName);
        sc = new Scanner(file);
        
        //*********************************************************
        // now starts at the top of the file again and starts at the beginning
        // of the given project type
        for (int j = 0; j <= numTypes; j++) {
            while(!(line.contains("."))) {
                line = sc.nextLine(); // go through until we get to the right type
            }
            line = sc.nextLine();
        }
        line = sc.nextLine();
        numProjects = Integer.parseInt(line);
        //*****************************************************
        // for each project of the type we go through this for loop once and add it
        // to our project list
        for (int j = 0; j < numProjects; j++) { // save project
            tempP = new Project();
            line = sc.nextLine();
            line = sc.nextLine();
            // skip lines that we do not want to read in, Project x:
            tempP.setName(line);
            // set name of project
            line = sc.nextLine();
            numBL = Integer.parseInt(line);
            projectList.add(tempP);
            // loop through this list once for each backLog item
            for (int i = 0; i < numBL; i++) {
                line = sc.nextLine();
                item = new BackLogItem();
                line = sc.nextLine();
                item.setName(line);
                // line = sc.nextLine();
                line = sc.nextLine();
                temp = Integer.parseInt(line);
                item.setTime(temp);
                line = sc.nextLine();
                temp = Integer.parseInt(line);
                item.setDif(temp);
                line = sc.nextLine();
                numDef = Integer.parseInt(line);
                //line = sc.nextLine(); // skip Defect 1:
                tempP.backLogItems.add(item);
                // add backLog item to project
                // go through this loop once for every defect
                for (int k = 0; k < numDef; k++) {
                    line = sc.nextLine();
                    def = new Defect();
                    line = sc.nextLine();
                    def.setName(line);
                    line = sc.nextLine();
                    temp = Integer.parseInt(line);
                    def.setTime(temp);
                    line = sc.nextLine();
                    temp = Integer.parseInt(line);
                    def.setDif(temp);
                    line = sc.nextLine();
                    temp = Integer.parseInt(line);
                    def.setRec(temp);
                    item.defects.add(def);
                    //line = sc.nextLine(); // make sure it skips over "defect 1:"
                }
            }
        }
        

        for (int i = 0; i < projectList.size(); i++) {
            // add all backLog Items from ith project into temp
            com.backLogItems.addAll(projectList.get(i).backLogItems);
            for (int j = 0; j < projectList.get(i).backLogItems.size(); j++) {
                if (items.contains(projectList.get(i).backLogItems.get(j).getName())) {
                    //com.combineItems(projectList.get(i).backLogItems.get(j), com.backLogItems.get(items.indexOf(projectList.get(i).backLogItems.get(j).getName())), 1);
                }
                else {
                    items.add(projectList.get(i).backLogItems.get(j).getName());
                }
                
            }
        }
        
        ArrayList<BackLogItem> duplicates;
        for (int i = 0; i < items.size(); i++) {
            duplicates = new ArrayList<BackLogItem>();
            for (int j = 0; j < com.backLogItems.size(); j++) {
                if (com.backLogItems.get(j).getName().equals(items.get(i))) {
                    duplicates.add(com.backLogItems.get(j));
                }
            }
            com.combineItems(duplicates);
        }
        
        // create your project based on historical data
        System.out.println("Please enter your project's name: ");
        line = keySc.nextLine();
        this.setName(line);
        
        this.backLogItems.addAll(com.backLogItems);
        
        // repeatedly ask the Scrum master to print all backLog items
        // or search for, edit, add or remove a backlog item
        this.edit();
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
	
	public void edit() throws IOException {
		// same thing we had in the main before
		String input = "";
	       int num = -1;
	       Scanner keySc = new Scanner(System.in);
	    do {
	        //System.out.println(myProject.toString());
	        System.out.println("Press s to search for a backLog item.");
	        System.out.println("Press e to edit for a backLog item.");
	        System.out.println("Press a to add for a backLog item.");
	        System.out.println("Press d to delete for a backLog item.");
	        System.out.println("Press P to print all backLog items.");
	        System.out.println("Press q to end.");
	        input = keySc.nextLine();
	        
	        switch (input) {
	           case "p":
	               System.out.println(toString());
	               break;
	            
	              case "s":
	             System.out.println("Enter the first few letters of the item you would like to search.\n");
	             input = keySc.nextLine();
	             for (int i = 0; i < backLogItems.size(); i++) {
	                 if (backLogItems.get(i).getName().startsWith(input)) {
	                     System.out.print(backLogItems.get(i).toString());
	                 }
	             }
	             
	             break;
	           case "e":
	             // code block
	               System.out.println("Enter the index name of the item you would like to edit.");
	               input = keySc.nextLine();
	               num = Integer.parseInt(input);
	               num--;
	               int index = num;
	               System.out.println(backLogItems.get(index).toString() + "\n\n");
	               do {
	                   System.out.println("Enter 1 to change the name or 0 to leave it.");
	                   input = keySc.nextLine();
	                   if (input.equals("1")) {
	                       System.out.println("Enter new name.");
	                       input = keySc.nextLine();
	                       backLogItems.get(index).setName(input);
	                   }
	                   
	                   System.out.println("Enter 1 to change the difficulty or 0 to leave it.");
	                   input = keySc.nextLine();
	                   num = Integer.parseInt(input);
	                   if (num == 1) {
	                       System.out.println("Enter new difficulty.");
	                       input = keySc.nextLine();
	                       num = Integer.parseInt(input);
	                       backLogItems.get(index).setDif(num);
	                   }
	                   
	                   System.out.println("Enter 1 to change the time or 0 to leave it.");
	                   input = keySc.nextLine();
	                   num = Integer.parseInt(input);
	                   if (num == 1) {
	                       System.out.println("Enter new time.");
	                       input = keySc.nextLine();
	                       num = Integer.parseInt(input);
	                       backLogItems.get(index).setTime(num);
	                   }
	                   
	                   for (int i = 0; i < backLogItems.get(index).defects.size(); i++) {
	                       System.out.println("Enter 1 to change the name or 0 to leave it.");
	                       input = keySc.nextLine();
	                       if (num == 1) {
	                           System.out.println("Enter new name.");
	                           input = keySc.nextLine();
	                           backLogItems.get(index).defects.get(i).setName(input);
	                       }
	                       
	                       System.out.println("Enter 1 to change the difficulty or 0 to leave it.");
	                       input = keySc.nextLine();
	                       num = Integer.parseInt(input);
	                       if (num == 1) {
	                           System.out.println("Enter new difficulty.");
	                           input = keySc.nextLine();
	                           num = Integer.parseInt(input);
	                           backLogItems.get(index).defects.get(i).setDif(num);
	                       }
	                       
	                       System.out.println("Enter 1 to change the time or 0 to leave it.");
	                       input = keySc.nextLine();
	                       num = Integer.parseInt(input);
	                       if (num == 1) {
	                           System.out.println("Enter new time.");
	                           input = keySc.nextLine();
	                           num = Integer.parseInt(input);
	                           backLogItems.get(index).defects.get(i).setTime(num);
	                       }
	                       
	                       System.out.println("Enter 1 to change the recurrency or 0 to leave it.");
	                       input = keySc.nextLine();
	                       num = Integer.parseInt(input);
	                       if (num == 1) {
	                           System.out.println("Enter new recurrence.");
	                           input = keySc.nextLine();
	                           num = Integer.parseInt(input);
	                           backLogItems.get(index).defects.get(i).setRec(num);
	                       }
	                   }
	                   System.out.println("Enter d to quit");
	                   input = keySc.nextLine();
	               } while (!(input.equals("d")));
	             break;
	           case "a":
	             // code block
	             do {
	                 BackLogItem t = new BackLogItem();
	                 System.out.println("Enter backLog item name.");
	                 input = keySc.nextLine();
	                 t.setName(input);
	                 System.out.println("Enter backLog item difficulty.");
	                 input = keySc.nextLine();
	                 num = Integer.parseInt(input);
	                 t.setDif(num);
	                 System.out.println("Enter backLog item time.");
	                 input = keySc.nextLine();
	                 num = Integer.parseInt(input);
	                 t.setTime(num);
	                 do {
	                     Defect d = new Defect();
	                     System.out.println("Enter defect name.");
	                     input = keySc.nextLine();
	                     d.setName(input);
	                     System.out.println("Enter defect difficulty.");
	                     input = keySc.nextLine();
	                     num = Integer.parseInt(input);
	                     d.setDif(num);
	                     System.out.println("Enter defect time.");
	                     input = keySc.nextLine();
	                     num = Integer.parseInt(input);
	                     d.setTime(num);
	                     System.out.println("Enter defect likelyhood of recurrence.");
	                     input = keySc.nextLine();
	                     num = Integer.parseInt(input);
	                     d.setRec(num);
	                     t.defects.add(d);
	                     System.out.println("Enter x to quit.");
	                     input = keySc.nextLine();
	                 } while (!(input.equals("x")));
	                 backLogItems.add(t);
	                 System.out.println("Enter d to quit.");
	                 input = keySc.nextLine();
	             } while (!(input.equals("d")));
	             break;
	           case "d":
	               System.out.println("Enter the item you would like to delete.");
	               input = keySc.nextLine();
	               num = Integer.parseInt(input);
	               System.out.print(backLogItems.remove(num-1).toString());
	               System.out.println("Has been removed");
	             // code block
	             break;
	           case "q":
	               break;
	           default:
	               System.out.println("Invalid input");
	             // code block
	        }
	        
	    } while (!(input.equals("q")));
	    System.out.println("Would you like to save your project? (0 for no, 1 for yes)");
	    String save;
	    save = keySc.nextLine();
	    if (save.equals("1")) {
	    	//save(false, path);
	    }
	    //sc.close();
	    keySc.close();
	}
	
	//***********************************************
	//Reads in from text file at given path and initializes this project with that info
	//***********************************************
	
	public Project load(String p, int num) throws IOException {
		File f = new File(p);
		Scanner fS = new Scanner(f);
		Project mP = new Project();
		//Scanner kS = new Scanner(System.in);
		backLogItems.clear();
		ArrayList<String> projectNameList = new ArrayList<String>();
		//System.out.println("Which project would you like to load?");
		//FIXME make graphics
        //*****************************
		//Each file may have multiple projects, pick one
		//******************************
		int count = 0;
		String line = "";
        while (fS.hasNextLine()) {
            while ((fS.hasNextLine()) && !(line.contains("."))) { // skip ahead to next project
            	line = fS.nextLine();
            }
            if (fS.hasNextLine()) {
            	line = fS.nextLine();
                //System.out.println(count + ". " + line);
                count++;
                projectNameList.add(line);
            }
        }
        //int num = 1;
        //num = Integer.parseInt(kS.nextLine());
        String pName = "";
        pName = projectNameList.get(num-1);
        fS = new Scanner(f);
        while (!(line.equals(pName))) {
        	line = fS.nextLine();
        }
		String in = "";
		int inNum = -1;
		//in = fS.nextLine();
		setName(line);
		in = fS.nextLine();
		inNum = Integer.parseInt(in);
		int numbL = inNum;
		for (int i = 0; i < numbL; i++) {
			in = fS.nextLine();
			BackLogItem bL = new BackLogItem();
			in = fS.nextLine();
			bL.setName(in);
			in = fS.nextLine();
			inNum = Integer.parseInt(in);
			bL.setTime(inNum);
			in = fS.nextLine();
			inNum = Integer.parseInt(in);
			bL.setDif(inNum);
			in = fS.nextLine();
			inNum = Integer.parseInt(in);
			int numD = inNum;
			for (int j = 0; j < numD; j++) {
				in = fS.nextLine();
				Defect d = new Defect();
				in = fS.nextLine();
				d.setName(in);
				in = fS.nextLine();
				inNum = Integer.parseInt(in);
				d.setTime(inNum);
				in = fS.nextLine();
				inNum = Integer.parseInt(in);
				d.setDif(inNum);
				in = fS.nextLine();
				inNum = Integer.parseInt(in);
				d.setRec(inNum);
				bL.defects.add(d);
			}
			backLogItems.add(bL);
		}
		
		fS.close();
		return this;
	}
	
	//*****************************************
	// Writes the project to a text file at a given path
	//****************************************
	public void save(boolean append, String path) throws IOException {
		/*System.out.println("Please enter the path you would like to save to.");
		Scanner s = new Scanner(System.in);
		
		String path = s.nextLine();
		System.out.println("Would you like:");
		System.out.println("1. Write over file");
		System.out.println("2. Add to file");
		int op = Integer.parseInt(s.nextLine());*/
		//int op = 1;
		// have append false for first one and then true
		
		/*if (op == 1) {
			append = false;
		}*/
		File myProject = new File(path);
		//StringBuffer  sB = new StringBuffer();
		//Scanner sc = new Scanner(myProject);
        BufferedWriter brw = new BufferedWriter(new FileWriter(myProject, append));
        
        //Reading lines of the file and appending them to StringBuffer
        String line = "";
        String pName = "";
        /*while (sc.hasNextLine()) {
        	if (!(this.name.equals(pName))) {
        		sB.append(sc.nextLine()+System.lineSeparator());
        	}
        	//line = sc.nextLine();
        	if (line.equals(name)) {
        		pName = line;
        	}
        }
        while ((sc.hasNextLine()) && ((line = sc.nextLine()) != ".")) {
        	// do nothing
        }
        while (sc.hasNextLine()) {
        	sB.append(sc.nextLine()+System.lineSeparator());
        }
        String fileContents = sB.toString();
        
        sc.close();
        brw.write(fileContents);
        brw.flush();*/
        brw.write("."+System.lineSeparator());
        brw.flush();
        brw.write(name + System.lineSeparator());
        brw.flush();
        brw.write(backLogItems.size() + System.lineSeparator());
        brw.flush();
        for (int i = 0; i < backLogItems.size(); i++) {
        	brw.write("BackLog Item " + (i + 1)+ ":" + System.lineSeparator());
        	brw.flush();
        	brw.write(backLogItems.get(i).getName() + System.lineSeparator());
        	brw.flush();
        	brw.write(backLogItems.get(i).getTime() + System.lineSeparator());
        	brw.flush();
        	brw.write(backLogItems.get(i).getDif() + System.lineSeparator());
        	brw.flush();
        	brw.write("" + backLogItems.get(i).defects.size() + System.lineSeparator());
        	brw.flush();
        	for (int j = 0; j < backLogItems.get(i).defects.size(); j++) {
        		brw.write("Defect " + (j+1) + ":" + System.lineSeparator());
        		brw.flush();
            	brw.write(backLogItems.get(i).defects.get(j).getName() + System.lineSeparator());
            	brw.flush();
            	brw.write(backLogItems.get(i).defects.get(j).getTime() + System.lineSeparator());
            	brw.flush();
            	brw.write(backLogItems.get(i).defects.get(j).getDif() + System.lineSeparator());
            	brw.flush();
            	brw.write(backLogItems.get(i).defects.get(j).getRec() + System.lineSeparator());
            	brw.flush();
        	}
        }
        brw.close();
		
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
