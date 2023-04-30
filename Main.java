//GUI Author: Andrew Bartle
//Contributors: Benjamin Jones, Logan Goswick


package runProj;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane; //***
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application{
	
public static void main(String[] args) {
        launch(args);
}

/*********************************************************
Puts list of projects in textfile into arrayList of projects
We decrypt the text file then count how many periods are in the text file
because that shows how many projects there are
we then add the projects with the overloaded constructor that reads them in order
 * @throws IOException 
 **********************************************************/
public static void populate(String p, ArrayList<Project> pL) throws IOException {
	EncryptOrDecrypt(p);
	int count = 0;
	File file = new File(p);
    Scanner sc = new Scanner(file);
    String line = "";
    while (sc.hasNextLine()) {
    	line = sc.nextLine();
    	if (line.equals(".")) {
    		count++;
    	}
    }
    Project pr;
	for (int i = 1; i <= count; i++) {
		pr = new Project(i);
		pL.add(pr);
	}
	
	return;
}

/*************************************
 * 
 * @param fileName
 * @author Logan Goswick
 * this method when called on a file name will either decrypt or encrypt
 */
public static void EncryptOrDecrypt(String fileName) 
{
    
    byte[] key = {0x42, 0x75, 0x67, 0x73, 0x61, 0x72, 0x65, 0x69, 0x6E, 0x79, 0x6F, 0x75, 0x6E, 0x6F, 0x77, 0x21};
    ;
	try 
	{
		byte[] content = Files.readAllBytes(Paths.get(fileName));
	    byte[] encryptedBytes = new byte[content.length]; 
	    for (int i = 0; i < content.length; i++)
	    {
	    	
	    	encryptedBytes[i] = (byte)(content[i] ^ key[i % 16]);
	    }
		Files.write(Paths.get(fileName), encryptedBytes);
		
	} 
	catch (IOException e) 
	{
		e.printStackTrace();
	}

  }

	//****** Declarations of items in full scope ********************************
// for creating project
File file = null;
String line = "";
String projectTypeName = "";
ArrayList<String> projectNameList;
ArrayList<Project> projectList;
ArrayList<String> items;
Project com = new Project();
Project tempP;
BackLogItem item;
Defect def;
int numTypes = 0;
int numProjects = 0;
int numBL = 0;
int temp = 0;
int index = 0;
int numDef = 0;
Project addedProject = new Project();
/****************************
 * Time stuff
 *****************************/
	long startTime = 0;
	long elapsedTime = 0;
	long elapsedSeconds = elapsedTime / 1000;
	long secondsDisplay = elapsedSeconds % 60;
	long elapsedMinutes = elapsedSeconds / 60;
	long elapsedHours = 0;
	long hoursDisplay = 0;
	long minutesDisplay = 0;
	/***************************************************************
	 * Keeps track of the current project, 
	 * backLogItem and defect we are working on and their indexes
	 ***************************************************************/
	Project curP;
	BackLogItem addedB = new BackLogItem();
	Defect addedD = new Defect();
	int pI = 0;
	int dI = 0;
	Defect curD;
	BackLogItem curBl;
	int bLI = 0;
	ArrayList<Project> ListOfProjects = new ArrayList<Project>();
	/*
	 * Just the path I have hard coded for the project to load and save
	 */
	String path = "project.txt";
	/********************************************************
	 * Buttons tabs and panes, plus the userName and pWord
	 ********************************************************/
	final int win_x_size = 500, win_y_size = 200;
	Label timePunch = new Label("You are not clocked in");
    String username;				
    String password;
    Button authenticateBtn = new Button("Log in");
    TextField usernameTxtFld = new TextField();
    TextField passwordTxtFld = new TextField();
    GridPane userAuthPane = new GridPane();
	GridPane defectsPane = new GridPane();		
	GridPane effortLogEditor = new GridPane();
	TabPane tabPane = new TabPane();					
	Tab mainTab = new Tab();					
	Tab defectTab = new Tab();
	Tab effortLogEditorTab = new Tab();
	Tab backlogTab = new Tab();
    Scene scene = new Scene(tabPane);
	Stage primaryStage = new Stage();
	GridPane pane = new GridPane();
	/**********************************************
	 * Combo Boxes
	 *************************************************/
	ComboBox<String> ProjectList = new ComboBox<String>();	
	ComboBox<String> BackLogItem = new ComboBox<String>();
	ComboBox<String> Defect = new ComboBox<String>();
	ComboBox<String> LifeCycleList = new ComboBox<String>();
	ComboBox<String> EffortCat = new ComboBox<String>();
	ComboBox<String> EffortCat2 = new ComboBox<String>();
	ComboBox<String> ProjectType = new ComboBox<String>();
	/******************************************
	 * Labels plus more buttons
	 ********************************************/
	Label header = new Label("Effort Logger Console");
	Button clockInBtn = new Button("Clock In");	
	Label projLabel = new Label("Project: ");
	Label backlogLabel = new Label("BackLogItem: ");
	Label defectLabel = new Label("Defect: ");
	Label lifeCycleLabel = new Label("Life Cycle Step:");
	Label effortCatLabel = new Label("Effort Category:");
	Button clockOutBtn = new Button("Clock out");
	Button mainSubmitBtn = new Button("Submit");
	Button addDefectBtn = new Button("Add new Defect");
	Button removeDefectBtn = new Button("Remove current Defect");
	Button submitDefectBtn = new Button("Submit defect");
	Button submitBackLogItemBtn = new Button("Submit Backlog item");
	Button addBackLogItemBtn = new Button("Add new backlog item");
	Button removeBackLogItemBtn = new Button("Remove current backLog Item");
	GridPane backlogPane = new GridPane();
	RadioButton existingProj = new RadioButton("Existing Project");
	RadioButton newProj = new RadioButton("New Project");
	ToggleGroup projectOptions = new ToggleGroup();
	Button enterbtn = new Button("Enter ");
	TextArea toDisplay = new TextArea();
	String projName;
	int difficulty, defects, time;
	TextField projNameFld = new TextField();
	TextField difficultyFld = new TextField();
	TextField defectsFld = new TextField();
	TextField timeFld = new TextField();
	/************************************
	 * TextFields to add new Defect/BackLogItem
	 **********************************/
	TextField newBlName = new TextField();
	TextField newDName = new TextField();
	/*****************************
	BackLog Items textFields and Labels
	//****************************/
	TextField bLDifTF;
	TextField bLTimeTF;
	TextField blNameTF;
	TextField bLDesTF;
	TextField bLTimeETF;
	TextField bLCurTimeETF;
	TextField bLTimeRTF;
	
	Label bLTimeLbl = new Label("Time (in hours): ");
	Label bLNameLbl = new Label("BackLog Item Name: ");
	Label bLDifLbl = new Label("Difficulty Level (/10): ");
	Label bLDesLbl = new Label("Description: ");
	Label bLTimeELbl = new Label("Time worked on: ");
	Label bLCurTimeELbl = new Label("Time worked on this sprint: ");
	Label bLTimeRLbl = new Label("Estimated time remaining");
	/*****************************
	Defect textFields and Labels
	//****************************/
	
	TextField dDifTF;
	TextField dTimeTF;
	TextField dRecTF;
	TextField dNameTF;
	TextField dTimeETF;
	TextField dCurTimeETF;
	TextField dTimeRTF;
	TextField dCauseTF;
	TextField dSymTF;
	TextField dSolTF;
	
	Label dTimeLbl = new Label("Time (in hours): ");
	Label dNameLbl = new Label("Defect Name: ");
	Label dDifLbl = new Label("Difficulty Level (/10): ");
	Label dRecLbl = new Label("Likelyhood of recurrence: ");
	Label dTimeELbl = new Label("Time worked on: ");
	Label dCurTimeELbl = new Label("Time worked on this sprint: ");
	Label dTimeRLbl = new Label("Estimated time remaining");
	Label dCauseLbl = new Label("Cause: ");
	Label dSymLbl = new Label("Symptom(s): ");
	Label dSolLbl = new Label("Solution: ");
	
	
	Button newProjBtn = new Button("Create Project");
	Label projNameLbl = new Label("Project Name: ");
	Label projDiffLbl = new Label("Project Difficulty Level: ");
	Label projDefectsLbl = new Label("Project Defects: ");
	Label bLNumDefectsLbl = new Label("Project number of Defects: ");
	Label projTimeLbl = new Label("Time (in hours): ");
	// getting path
	TextField pathTF = new TextField();
	Label pathLbl = new Label("Please enter the path: ");
	// temps
	BackLogItem bL;
	Defect d;
	
	//****** START OF MAIN TAB FEATURES ********************************
	public void start(Stage primaryStage) throws IOException{
		/*****************************************************************************************
		* Initializes projects and sets current project, backLogItem and defects to the first one
		******************************************************************************************/
		populate(path, ListOfProjects);
		EncryptOrDecrypt("userData.txt");
		EncryptOrDecrypt("dataBase.txt");
		curP = ListOfProjects.get(0);
		curBl = curP.backLogItems.get(0);
		curD = curBl.defects.get(0);
		// Create a GridPane object and set its properties
		pane.setAlignment(Pos.CENTER); //center the GridPane
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5); //set horizontal gap between columns
		pane.setVgap(5.5); //set vertical gap between rows
		pane.setPrefWidth(800);
		pane.setPrefHeight(500);
	
		pane.add(header, 2, 0);				//add to pane in location 2,0
		GridPane.setHalignment(header, HPos.CENTER);		//set horizontal alignment
		
		timePunch.setTextFill(Color.RED);
		pane.add(timePunch, 2, 1);				//set position
		
		pane.add(clockInBtn, 1, 1);						//add to pane and set position
		clockInBtn.setOnAction(new InButtonHandler());	//tie button to an event handler
		
		/************************************
		 populate drop down menu with list of projects
		 *************************************/
		/*existingProj.setToggleGroup(projectOptions);
		newProj.setToggleGroup(projectOptions);
		pane.add(existingProj, 5, 2);
		pane.add(newProj, 5, 3);*/
		pane.add(enterbtn, 8, 4);
		enterbtn.setOnAction(new enterBtnHandler());
		//toDisplay.setEditable(false);
		pane.add(newProjBtn, 5, 4);
		newProjBtn.setOnAction(new newProjBtnHandler());
		
		pane.add(projLabel, 0, 5);				//add to pane and set position
		for (int i = 0; i < ListOfProjects.size(); i++) {
			ProjectList.getItems().add(ListOfProjects.get(i).getName());
		}
		ProjectList.getSelectionModel().select(0);
		pane.add(ProjectList, 1, 5);			//add to pane and set position
		ProjectList.setOnAction(new projectComboHandler());
		
		pane.add(lifeCycleLabel, 2, 5);			//add to pane and set position
		GridPane.setHalignment(lifeCycleLabel, HPos.RIGHT);			//set alignment for more visually pleasing
		LifeCycleList.getItems().addAll(
				"work",
				"work2",
				"work3");
		pane.add(LifeCycleList, 3, 5);				//add to the pane and set position
		
		pane.add(effortCatLabel, 0, 6);
		EffortCat.getItems().addAll(
				"effort1",
				"effort2",
				"effort3");
		pane.add(EffortCat, 1, 6);			//add to pane and set position
		
		EffortCat2.getItems().addAll(
				"placeholder",
				"placeholder",
				"placeholder");
		pane.add(EffortCat2, 3, 6);				//add to pane and set position

		

		pane.add(clockOutBtn, 1, 7);					//add to pane and set position
		clockOutBtn.setOnAction(new OutButtonHandler());		//tie button to event handler
		
		pane.add(mainSubmitBtn, 3, 7 );
		mainSubmitBtn.setOnAction(new SubmitBtnMainHandler());

		//********************** END OF MAIN TAB CODE *********************
		
		
		//********************** START OF CONNECTING PANES CODE ************
		mainTab.setText("Effort Console");
		mainTab.setContent(pane);
		defectTab.setText("Defect Console");
		defectTab.setContent(defectsPane);
		effortLogEditorTab.setText("Effort Log Editor");
		effortLogEditorTab.setContent(effortLogEditor);
		backlogTab.setText("Backlog Items Catalog");
		backlogTab.setContent(backlogPane);
		tabPane.getSelectionModel().select(0);				//add the tabs to the pane
        tabPane.getTabs().addAll(mainTab, backlogTab, defectTab, effortLogEditorTab);
		Scene userAuthScene = new Scene(userAuthPane);

        //**************************END OF CONNECTING PANES CODE ****************

        
        //*************************START OF AUTHENTICATION PANE CODE *************
        userAuthPane.setAlignment(Pos.CENTER); //center the GridPane
        userAuthPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        userAuthPane.setHgap(5.5); //set horizontal gap between columns
        userAuthPane.setVgap(5.5); //set vertical gap between rows
        userAuthPane.setPrefWidth(win_x_size);
        userAuthPane.setPrefHeight(win_y_size);

        Label usernameLbl = new Label("Username: ");
        Label passwordLbl = new Label("Password: ");
        userAuthPane.add(usernameLbl, 1, 2);
        userAuthPane.add((usernameTxtFld), 2, 2);
        userAuthPane.add(passwordLbl, 1, 3);
        userAuthPane.add((passwordTxtFld), 2, 3);
    	userAuthPane.add(authenticateBtn, 2, 4);	
    	authenticateBtn.setOnAction(new AuthenticateBtnHandler());
    	
        primaryStage.setTitle("Log In"); // Set the stage title
		primaryStage.setScene(userAuthScene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
		
        //*****************END OF AUTHENTICATION PANE CODE ************
		
		
		//*****************START OF BACKLOG PANE CODE ***********************
		/*********************************************
		 * Initialize backLog pane and fill combo
		 ***********************************************/
		backlogPane.add(backlogLabel, 1, 1);				//add to pane and set position
		for (int i = 0; i < curP.backLogItems.size(); i++) {
			BackLogItem.getItems().add(curP.backLogItems.get(i).getName());
		}
		BackLogItem.getSelectionModel().select(0);
		backlogPane.add(BackLogItem, 1, 2);			//add to pane and set position
		BackLogItem.setOnAction(new backlogComboHandler());
		
		/*************************************
		 * ANDREW: We will want the textfield for the description to be bigger
		 * 
		 **************************************/
		
		bLDifTF = new TextField("" + curBl.getDif());
		bLTimeTF = new TextField("" + curBl.getTime());
		bLDesTF = new TextField("" + curBl.getDescription());
		bLTimeETF = new TextField("" + curBl.getTimeE());
		bLCurTimeETF = new TextField("0 seconds");
		bLCurTimeETF.setEditable(false);
		bLTimeRTF = new TextField("" + curBl.getTimeR());
		bLTimeRTF.setEditable(false);
		bLTimeETF = new TextField("" + curBl.getTimeE());
		bLTimeETF.setEditable(false);
		
		bLDifTF.setOnAction(new backLogTextFieldHandler());
		bLTimeTF.setOnAction(new backLogTextFieldHandler());
		bLDesTF.setOnAction(new backLogTextFieldHandler());
		newBlName.setOnAction(new backLogTextFieldHandler());
		removeBackLogItemBtn.setOnAction(new removeButtonHandler());
		addBackLogItemBtn.setOnAction(new addButtonHandler());
		
		backlogPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		backlogPane.setHgap(5.5); //set horizontal gap between columns
        backlogPane.setVgap(5.5); //set vertical gap between rows
		backlogPane.add(bLDifLbl, 1, 3);
		backlogPane.add(bLDifTF, 1, 4);
		backlogPane.add(bLTimeLbl, 1, 5);
		backlogPane.add(bLTimeTF, 1, 6);
		backlogPane.add(bLDesLbl, 1, 7);
		backlogPane.add(bLDesTF, 1, 8);
		backlogPane.add(bLTimeELbl, 1, 9);
		backlogPane.add(bLTimeETF, 1, 10);
		backlogPane.add(bLCurTimeELbl, 2, 1);
		backlogPane.add(bLCurTimeETF, 2, 2);
		backlogPane.add(bLTimeRLbl, 2, 3);
		backlogPane.add(bLTimeRTF, 2, 4);
		backlogPane.add(addBackLogItemBtn, 3, 2);
		backlogPane.add(removeBackLogItemBtn, 3, 3);
		
		
		//****************END OF BACKLOG PANE CODE *************************
		
		//*****************START OF Defect PANE CODE ***********************
				defectsPane.add(defectLabel, 1, 1);				//add to pane and set position
				for (int i = 0; i < curBl.defects.size(); i++) {
					Defect.getItems().add(curBl.defects.get(i).getName());
				}
				Defect.getSelectionModel().select(0);
				defectsPane.add(Defect, 1, 2);	 //add to pane and set position
				Defect.setOnAction(new defectComboHandler());
				
				/*************************************
				 * ANDREW: We will want the textfield for the description,
				 * symptoms, cause and solution to be bigger
				 * 
				 **************************************/
				
				dDifTF = new TextField("" + curD.getDif());
				dTimeTF = new TextField("" + curD.getTime());
				dRecTF = new TextField("" + curD.getRec());
				dSymTF = new TextField(curD.getSymptoms());
				dSolTF = new TextField(curD.getSolution());
				dCauseTF = new TextField("" + curD.getCause());
				dTimeETF = new TextField("" + curD.getTimeE());
				dCurTimeETF = new TextField("0 seconds");
				dCurTimeETF.setEditable(false);
				dTimeRTF = new TextField("" + curD.getTimeR());
				dTimeRTF.setEditable(false);
				dTimeETF = new TextField("" + curD.getTimeE());
				dTimeETF.setEditable(false);
				removeDefectBtn.setOnAction(new removeButtonHandler());
				addDefectBtn.setOnAction(new addButtonHandler());
				
				dDifTF.setOnAction(new backLogTextFieldHandler());
				dTimeTF.setOnAction(new backLogTextFieldHandler());
				dRecTF.setOnAction(new backLogTextFieldHandler());
				dSymTF.setOnAction(new backLogTextFieldHandler());
				dSolTF.setOnAction(new backLogTextFieldHandler());
				dCauseTF.setOnAction(new backLogTextFieldHandler());
				newDName.setOnAction(new backLogTextFieldHandler());
				
				defectsPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
				defectsPane.setHgap(5.5); //set horizontal gap between columns
		        defectsPane.setVgap(5.5); //set vertical gap between rows
				defectsPane.add(dDifLbl, 1, 3);
				defectsPane.add(dDifTF, 1, 4);
				defectsPane.add(dTimeLbl, 1, 5);
				defectsPane.add(dTimeTF, 1, 6);
				defectsPane.add(dRecLbl, 1, 7);
				defectsPane.add(dRecTF, 1, 8);
				defectsPane.add(dTimeELbl, 1, 9);
				defectsPane.add(dTimeETF, 1, 10);
				defectsPane.add(dCurTimeELbl, 2, 1);
				defectsPane.add(dCurTimeETF, 2, 2);
				defectsPane.add(dTimeRLbl, 2, 3);
				defectsPane.add(dTimeRTF, 2, 4);
				defectsPane.add(dSymLbl, 2, 5);
				defectsPane.add(dSymTF, 2, 6);
				defectsPane.add(dCauseLbl, 2, 7);
				defectsPane.add(dCauseTF, 2, 8);
				defectsPane.add(dSolLbl, 2, 9);
				defectsPane.add(dSolTF, 2, 10);
				defectsPane.add(addDefectBtn, 3, 2);
				defectsPane.add(removeDefectBtn, 3, 3);
				
				
				
				//****************END OF Defect PANE CODE *************************
		
		
	}
	
	//**********************Button Handling intensifies************************
	
		private class InButtonHandler implements EventHandler<ActionEvent>{		//Clock in button handler
			public void handle(ActionEvent e) {	
				/****************************
				 * Sets start time
				 *****************************/
				timePunch.setText("You are clocked in");		//sets the text to clocked in
				timePunch.setTextFill(Color.GREEN);				//changes to green text
				startTime = System.currentTimeMillis();
			}
		}
		private class OutButtonHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				/*
				 * Calulates time spent and updates total time spent
				 */
				timePunch.setText("You are not clocked in");
				timePunch.setTextFill(Color.RED);
				elapsedTime = System.currentTimeMillis() - startTime;
				elapsedSeconds = elapsedTime / 1000;
				elapsedMinutes = elapsedSeconds / 60;
				elapsedHours = elapsedMinutes / 60;
				secondsDisplay = elapsedSeconds % 60;
				minutesDisplay = elapsedMinutes % 60;
				hoursDisplay = elapsedHours;
				bLCurTimeETF.setText(hoursDisplay + ":" + minutesDisplay + ":" + secondsDisplay);
				curBl.addTime(elapsedSeconds);
				//curP.backLogItems.get(bLI).addTime(elapsedSeconds);
				bLTimeRTF.setText(curBl.getTimeR()+ " seconds");
				bLTimeETF.setText(curBl.getTimeE()+ " seconds");
				/*********************
				 ANDREW: you will want to somehow know if we are working on the defect or the backlog item selected
				 here I assumed the backlog item, once you add another button or someway to decide you will add something similar
				 */
			}
		}	
		private class AuthenticateBtnHandler implements EventHandler<ActionEvent>{
			private static boolean authenticate(String username, String password) throws IOException {
				/*
				 * Reads through userdata file to check if we have a userName and password that match our input
				 */
				File UserData = new File("userData.txt"); //currently using a txt file to store user data once the SQL database has been established this will be updated
		        BufferedReader br = new BufferedReader(new FileReader(UserData));
		        Scanner scanner = new Scanner(UserData);
		        Scanner input = new Scanner(System.in);
		        try {
		            String line;
		            while ((line = br.readLine()) != null) {
		                // process the line
		                if(username.equals(line)){
		                    if(password.equals(br.readLine())) {
		                    	return true;
		                    }
		                }
		            }

		        } finally {}
				
				return false;
			}
			public void handle(ActionEvent e) {
		    	username = usernameTxtFld.getText();
		    	password = passwordTxtFld.getText();
		    	boolean validUser = true;
		    	//ANDREW: added validation
		    	try {
					validUser = authenticate(username, password);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	if (validUser) {							//if user is valid, go to system screen
		    		primaryStage.setScene(scene);
		    		primaryStage.show();
		    	}
		    	else {
		    		Label wrongInfo = new Label("Sorry, this info is incorrect");  		//if not correct, show an error message
		    		wrongInfo.setTextFill(Color.RED);
		    		userAuthPane.add(wrongInfo, 2, 5);
		    	}
			}
		}
		
		private class removeButtonHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				if (e.getSource().equals(removeBackLogItemBtn)) {
					curP.backLogItems.remove(bLI);
					BackLogItem.getItems().remove(bLI);
					BackLogItem.getSelectionModel().select(0);
				}
				if (e.getSource().equals(removeDefectBtn)) {
					curBl.defects.remove(dI);
					Defect.getItems().remove(dI);
					Defect.getSelectionModel().select(0);
				}
			}
		}
		
		private class submitBtnHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				if (e.getSource().equals(submitBackLogItemBtn)) {
					addedB = new BackLogItem();
					Defect d = new Defect();
					d.setName("New Defect");
					addedB.defects.add(d);
					addedB.setName(newBlName.getText());
					curP.backLogItems.add(addedB);
					BackLogItem.getItems().add(addedB.getName());
					backlogPane.getChildren().remove(newBlName);
					backlogPane.getChildren().remove(submitBackLogItemBtn);
				}
				if (e.getSource().equals(submitDefectBtn)) {
					addedD = new Defect();
					addedD.setName(newDName.getText());
					curBl.defects.add(addedD);
					Defect.getItems().add(addedD.getName());
					defectsPane.getChildren().remove(newDName);
					defectsPane.getChildren().remove(submitDefectBtn);
				}
			}
		}
		
		private class addButtonHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				if (e.getSource().equals(addBackLogItemBtn)) {
					backlogPane.add(newBlName, 1, 12);
					backlogPane.add(submitBackLogItemBtn, 1, 13);
					submitBackLogItemBtn.setOnAction(new submitBtnHandler());
				}
				if (e.getSource().equals(addDefectBtn)) {
					defectsPane.add(newDName, 1, 12);
					defectsPane.add(submitDefectBtn, 1, 13);
					submitDefectBtn.setOnAction(new submitBtnHandler());
				}
			}
		}
		
		private class SubmitBtnMainHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				Label submission = new Label("Submitted.");
				pane.add(submission, 2, 8);
				/*ProjectList.getSelectionModel().clearSelection();
				LifeCycleList.getSelectionModel().clearSelection();
				EffortCat.getSelectionModel().clearSelection();
				EffortCat2.getSelectionModel().clearSelection();*/
				
				//BEN: if we do want to actually make a submitable form, i can set it so before it 
				//clears the boxes, the choices get set to variables, or we can just leave it as is to make it seem like working
				/***********************************************************************
				 This currently is basically a save button that then encrypts the file
				 ******************************************************************/
				for (int i = 0; i < ListOfProjects.size(); i++) {
					File myProject = new File(path);
					try {
						if (i == 0) {
							ListOfProjects.get(i).save(false, path);
						}
						else {
							ListOfProjects.get(i).save(true, path);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try {
					ListOfProjects.get(0).makeReport("report.txt");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				EncryptOrDecrypt("userData.txt");
				EncryptOrDecrypt("dataBase.txt");
				EncryptOrDecrypt(path);
			}
		}
		
		/*
		 * When we select a project it fills the backLogCombo with the selected projects'
		 * backlogItems
		 */
		private class projectComboHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				pI = ProjectList.getSelectionModel().getSelectedIndex();
				curP = new Project();
				curP = ListOfProjects.get(pI);
				/*********************
				 update backLogItem list
				 ***********************/
				BackLogItem.getItems().clear();
				for (int i = 0; i < curP.backLogItems.size(); i++) {
					BackLogItem.getItems().add(curP.backLogItems.get(i).getName());
				}
				BackLogItem.getSelectionModel().select(0);
				bLI = 0;
				
			}
		}
		/*
		 * When we select a backLogItem it fills the defect Combo with the selected backLogItems'
		 * defects
		 */
		
		private class backlogComboHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				bLI = BackLogItem.getSelectionModel().getSelectedIndex();
				if (bLI == -1) {
					bLI = 0;
				}
				curBl = curP.backLogItems.get(bLI);
				bLDifTF.setText(curBl.getDif() + "");
				bLTimeTF.setText(curBl.getTime() + "");
				bLTimeETF.setText("" + curBl.getTimeE());
				bLDesTF.setText(curBl.getDescription());
				bLTimeRTF.setText("" + curBl.getTimeR());
				/*********************
				 update defect list
				 */
				Defect.getItems().clear();
				for (int i = 0; i < curBl.defects.size(); i++) {
					Defect.getItems().add(curBl.defects.get(i).getName());
				}
				Defect.getSelectionModel().select(0);
				dI = 0;
			}
		}
		
		private class defectComboHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				dI = Defect.getSelectionModel().getSelectedIndex();
				curD = new Defect();
				if (dI < 0) {
					dI = 0;
				}
				curD = curBl.defects.get(dI);
				dDifTF.setText(curD.getDif() + "");
				dTimeTF.setText(curD.getTime() + "");
				dRecTF.setText(curD.getRec() + "");
				dTimeETF.setText("" + curD.getTimeE());
				dCauseTF.setText(curD.getCause());
				dTimeRTF.setText("" + curD.getTimeR());
				dSymTF.setText(curD.getSymptoms());
				dSolTF.setText(curD.getSolution());
			}
		}
		
		/*
		 * enter does nothing atm
		 */
		private class enterBtnHandler implements EventHandler<ActionEvent> {
			public void handle(ActionEvent e) {
				//BEN: this would be the spot to have methods producing strings to 
				//output text based on what options your backlog program uses, the .setText takes a string parameter 
				//and displays it
					 index = ProjectType.getSelectionModel().getSelectedIndex();
					 projectNameList = new ArrayList<String>();
					 projectList = new ArrayList<Project>();
					 items = new ArrayList<String>();
					 com.backLogItems.clear();
					 Scanner sc = null;
					 file = new File("dataBase.txt");
				     //numTypes = Integer.parseInt(projectTypeName) - 1;
				        projectTypeName = ProjectType.getSelectionModel().getSelectedItem();
				        //System.out.println("Ok, your project is a " + projectTypeName);
				        try {
							sc = new Scanner(file);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				        /*******
				         * FIXME when you add a second project 
				         */
				        
				        //*********************************************************
				        // now starts at the top of the file again and starts at the beginning
				        // of the given project type.
				        for (int j = 0; j <= index; j++) {
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
				                tempP.backLogItems.add(item);
				            }
				            projectList.add(tempP);
				        }
				            /***************************************************
				             * Here we add every backlog item to a dummy project
				             * this is basically to cover all the bases of every
				             * possible item your project may want
				             * Then we loop through every backlog item and
				             * add them to items which will contain every unique name
				             * if there are two backlog items called "userInterface" the 
				             * dummy project will have both but the arrayList items will 
				             * only have one String
				             **************************************************/
				            for (int i = 0; i < projectList.size(); i++) {
				                // add all backLog Items from ith project into temp
				                com.backLogItems.addAll(projectList.get(i).backLogItems);
				                for (int j = 0; j < projectList.get(i).backLogItems.size(); j++) {
				                    if (items.contains(projectList.get(i).backLogItems.get(j).getName())) {
				                        //do nothing
				                    }
				                    else {
				                        items.add(projectList.get(i).backLogItems.get(j).getName());
				                    }
				                    
				                }
				            }
				            
				            /*************************************************************************
				             * For each unique backlog item name we loop through once
				             * 		Then we loop through and add each backlogitem and check if it's name 
				             * 		is the name of the current index of item
				             * 			If so we add the the arrayList duplicates
				             * 		after this inner loop we have an arrayList of each backlogitem with that name
				             * 		we then call combineItems on this dummy project with the param duplicates
				             * 		we will see how that method works on line 650ish
				             ***************************************************************************/
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
				            
				            /****************
				             * We now have a project with no duplicate backlog items or defects but with all possible
				             * backlog items projects in this category have used
				             */
				            
				            // create your project based on historical data
				            //System.out.println("Please enter your project's name: ");
				            //line = keySc.nextLine();
				            //this.setName(line);
				            
				            addedProject.backLogItems.addAll(com.backLogItems);
				            
				            
						/*
						 * We now have the new initial project
						 */
				addedProject.setName(projNameFld.getText());
				ListOfProjects.add(addedProject);
	            ProjectList.getItems().add(addedProject.getName());
	            
				/*if(existingProj.isSelected()) {
					// do nothing I guess, we currently have those options
					/********
					 * ANDREW: we might want to make just one button that basically says create a new project
					 
				}
				else if(newProj.isSelected()) {
					
				}*/
			}
		}
		
		/*
		 * When we update the text field we update that backLogItem/Defects current attribute
		 */
		private class backLogTextFieldHandler implements EventHandler<ActionEvent> {
			
			public void handle(ActionEvent e) {
				if (e.getSource().equals(blNameTF)) {
					curBl.setName(blNameTF.getText().toString());
					curP.backLogItems.get(bLI).setName(curBl.getName());
				}
				else if (e.getSource().equals(bLTimeTF)) {
					curBl.setTime(Integer.parseInt(bLTimeTF.getText().toString()));
					curP.backLogItems.get(bLI).setDif(Integer.parseInt(bLTimeTF.getText().toString()));
				}
				else if (e.getSource().equals(bLDifTF)) {
					curBl.setDif(Integer.parseInt(bLDifTF.getText().toString()));
					curP.backLogItems.get(bLI).setDif(Integer.parseInt(bLDifTF.getText().toString()));
				}
				else if (e.getSource().equals(dNameTF)) {
					curD.setName(dNameTF.getText().toString());
					curBl.defects.get(dI).setName(curD.getName());
				}
				else if (e.getSource().equals(dTimeTF)) {
					curD.setTime(Integer.parseInt(dTimeTF.getText().toString()));
					curBl.defects.get(dI).setDif(Integer.parseInt(dTimeTF.getText().toString()));
				}
				else if (e.getSource().equals(dDifTF)) {
					curD.setDif(Integer.parseInt(dDifTF.getText().toString()));
					curBl.defects.get(dI).setDif(Integer.parseInt(dDifTF.getText().toString()));
				}
				else if (e.getSource().equals(dRecTF)) {
					curD.setDif(Integer.parseInt(dRecTF.getText().toString()));
					curBl.defects.get(dI).setDif(Integer.parseInt(dRecTF.getText().toString()));
				}
				else if (e.getSource().equals(bLDesTF)) {
					curBl.setDescription(bLDesTF.getText().toString());
					curP.backLogItems.get(bLI).setDescription(curBl.getDescription());
				}
				else if (e.getSource().equals(dSymTF)) {
					curD.setSymptoms(dSymTF.getText().toString());
					curBl.defects.get(dI).setSymptoms(curD.getSymptoms());
				}
				else if (e.getSource().equals(dCauseTF)) {
					curD.setName(dCauseTF.getText().toString());
					curBl.defects.get(dI).setCause(curD.getCause());
				}
				else if (e.getSource().equals(dSolTF)) {
					curD.setSolution(dSolTF.getText().toString());
					curBl.defects.get(dI).setSolution(curD.getSolution());
				}
				else if (e.getSource().equals(projNameFld)) {
					addedProject.setName(projNameFld.getSelectedText());
				}
				else if (e.getSource().equals(newBlName)) {
					addedProject.setName(projNameFld.getSelectedText());
				}
				else if (e.getSource().equals(newDName)) {
					addedProject.setName(projNameFld.getSelectedText());
				}
				/*else if (e.getSource().equals(pathTF)) {
					path = pathTF.getText().toString();
				}*/
				
			}
		}
		
		private class newProjBtnHandler implements EventHandler<ActionEvent>{

			public void handle(ActionEvent e) {
				/****************
				 * Literally pasted old create project
				 */
				projectNameList = new ArrayList<String>();
		        pane.add(projNameFld, 5, 7);
		        pane.add(projNameLbl, 4, 7);
		        projNameFld.setOnAction(new backLogTextFieldHandler());
		        
		        //*****************************************
		        //Create Scanner object for file and terminal
		        File file = new File("dataBase.txt");
		        Scanner sc = null;
		        numTypes = -1;
				try {
					sc = new Scanner(file);
					numTypes = Integer.parseInt(sc.nextLine());
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
		        
		        //*******************************************************
		        // reads in number of types of projects and loops through file
		        // displaying each type, identified because they are
		        // the only lines that end in periods
		        ProjectType.getItems().clear();
		        for (int i = 1; i <= numTypes; i++) {
		            while (!(line.contains("."))) { // skip ahead to next project
		                line = sc.nextLine();
		            }
		            line = sc.nextLine();
		            ProjectType.getItems().add(line);
		            projectNameList.add(line);
		            numProjects = sc.nextInt();
		        }
		        pane.add(ProjectType, 6, 10);
		        //ProjectType.setOnAction(new projectComboHandler());
		        
		        //projectTypeName = keySc.nextLine();
		       
				
			}
		}
}
