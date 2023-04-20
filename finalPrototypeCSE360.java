package Prototype;

//package StartUp;

/*
 Author: Benjamin Jones
 School: Arizona State University Student
 Date: 4/2/2023
 Summary: This program allows a project leader to create a new project for the effort logger
 app. It breaks down all the projects in the database into types and computes the average
 of all backlog items needed for those projects and suggests them to the team leader.
 The team leader then has the ability to make as many changes to effort logger's suggestions as they like.
 */

 /*import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;*/
 import java.io.*;
import java.util.*;
 import java.util.Scanner;

/*import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;*/

 
 public class finalPrototypeCSE360 {
     
 
     public static void main(String[] args) throws IOException {
        /******************
        Log in first to create a project
        *******************/
        String Username;
        String NewUser;
        String Password;
        String NewPass;
        String st;
        //File UserData = new File("UserData.txt");
        //BufferedReader br = new BufferedReader(new FileReader(UserData));
        //List arrList = new ArrayList<String>();
        //BufferedWriter brw = new BufferedWriter(new FileWriter(UserData, true));

        Scanner input1 = new Scanner(System.in);

        System.out.println("Existing User? Type yes or no. "); // later would be implimented as button by the JavaFX personnel
        String UserType = input1.next();
        //input1.close();
            if (UserType.equals("yes")) {
                //br.close();
                //brw.close();
                authenticate();
                //input1.close();
            } else {
               createUser();
               //input1.close();
            }
        }


    public static void authenticate() throws IOException {
        File UserData = new File("C:\\Users\\benja\\Documents\\ASUWorkspace\\UserAuth\\src\\Prototype\\userData.txt"); //currently using a txt file to store user data once the SQL database has been established this will be updated
        BufferedReader br = new BufferedReader(new FileReader(UserData));
        Scanner scanner = new Scanner(UserData);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Username: ");
        String username;
        username = input.nextLine();
        System.out.println("Enter Password: ");
        String password = input.nextLine();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                if(username.equals(line)){
                    if(password.equals(br.readLine())) {
                    	//********************************
                    	// Now asks if we want to make a new project or edit an old one
                    	//*********************************
                        System.out.println("Access Granted");
                        System.out.println("Would you like to:");
                        System.out.println("1. Create a new project");
                        System.out.println("2. Edit an existing project");
                        String option = input.nextLine();
                        //**************************
                        //Same thing we had before
                        //**************************
                        if (option.equals("1")) {
                        	createProject();
                        }
                        //***************************
                        // Now we can load a project saved in a text file
                        //***************************
                        else if (option.equals("2")) {
                        	System.out.println("What is the path to your project?");
                        	String path = input.nextLine();
                        	Project myP = new Project();
                        	//*************************
                        	// Sets project to what was saved in the file and allows you to view/edit
                        	//*************************
                        	myP.load(path);
                        	myP.edit();
                        }
                        else {
                        	System.out.println("Eror, please select a valid option.");
                        }
                    }
                    else{
                        System.out.println("Access Denied");
                    }
                }

                }

        } finally {
            //br.close();
            //scanner.close();
            //input.close();
        }

    }
    public static void createUser() throws IOException {
        File UserData = new File("C:\\Users\\benja\\Documents\\ASUWorkspace\\UserAuth\\src\\Prototype\\userData.txt");
        BufferedWriter brw = new BufferedWriter(new FileWriter(UserData, true));
        Scanner input4 = new Scanner(System.in);
        System.out.println("Create a  Username : ");
       String NewUser = input4.next();
        brw.write(NewUser + System.lineSeparator());
        brw.flush();

        System.out.println("Create a Password : ");
       String NewPass = input4.next();
        brw.write(NewPass + System.lineSeparator());
        brw.close();
        //input4.close();
        //UserData.delete();

        authenticate();
    }

    /***********************
     Create a new Project
     *********************/
public static void createProject() throws IOException {
         // Initialize Variables
         String line = "";
         ArrayList<String> projectNameList = new ArrayList<String>();
         ArrayList<Project> projectList = new ArrayList<Project>();
         ArrayList<Integer> order = new ArrayList<Integer>();
         ArrayList<String> items = new ArrayList<String>();
         Project com = new Project();
         Project myProject;
         Project tempP;
         BackLogItem item;
         Defect def;
         int numTypes = 0;
         int numProjects = 0;
         int numBL = 0;
         int temp = 0;
         int numDef = 0;
         String name = "";
         String projectTypeName = "";
         
         //*****************************************
         //Create Scanner object for file and terminal
         File file = new File("C:\\Users\\benja\\Documents\\ASUWorkspace\\UserAuth\\src\\Prototype\\dataBase.txt");
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
         myProject = new Project();
         System.out.println("Please enter your project's name: ");
         line = keySc.nextLine();
         myProject.setName(line);
         
         myProject.backLogItems.addAll(com.backLogItems);
         
         // repeatedly ask the Scrum master to print all backLog items
         // or search for, edit, add or remove a backlog item
         myProject.edit();
       }
	
	public static void editProject(String p) throws IOException {
		// repeatedly ask the Scrum master to print all backLog items
        // or search for, edit, add or remove a backlog item
		  Project myProject = new Project();
		  myProject.load(p);
		  myProject.edit();
	}
 }
 // features to be added:
 // integrate with javaFX
 // save project as text file
 // make a project team, maybe each backLogItem has a list of people working on it
 // look into encrypting project file
 
 //simulated database
 /*
2
Type 1.
Sports Video Games
2
Project 1:
Madden 24
2
BackLog Item 1:
Main Menu
4
2
1
Defect 1:
Loss of Connection
2
4
7
BackLog Item 2:
Franchise Mode
9
10
1
Defect 1:
AI trade logic errors
7
4
6
Project 2:
Fifa 24
2
BackLog Item 1:
Ultimate Team
6
7
1
Defect 1:
Hacking
2
8
9
BackLog Item 2:
Exhibition
3
7
1
Defect 1:
Keeper Logic
3
4
4
Type 2.
Social Media Apps
2
Project 1:
Instagram
3
BackLog Item 1:
UserInterface
12
2
1
Defect 1:
Frozen Screen
6
4
4
BackLog Item 2:
Direct Messaging
6
4
1
Defect 1:
Activity Light not working
3
4
2
BackLog Item 3:
Posting
3
22
1
Defect 1:
Loss of Connection
3
14
4
Project 2:
Facebook
3
BackLog Item 1:
My Wall
9
6
1
Defect 1:
Identity Theft
6
10
10
BackLog Item 2:
Friending people
3
2
1
Defect 1:
Blocking people
3
5
3
BackLog Item 3:
Posting
9
7
2
Defect 1:
Loss of Connection
1
10
7
Defect 2:
Incorrect Privacy Setting
5
2
2
 */
 