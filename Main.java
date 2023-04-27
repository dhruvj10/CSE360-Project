package application;
	
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Main extends Application{
	
public static void main(String[] args) {
        launch(args);
}


	//****** Declarations of items in full scope ********************************
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
	ComboBox<String> ProjectList = new ComboBox<String>();	
	ComboBox<String> LifeCycleList = new ComboBox<String>();
	ComboBox<String> EffortCat = new ComboBox<String>();
	ComboBox<String> EffortCat2 = new ComboBox<String>();
	Label header = new Label("Effort Logger Console");
	Button clockInBtn = new Button("Clock In");	
	Label projLabel = new Label("Project:");
	Label lifeCycleLabel = new Label("Life Cycle Step:");
	Label effortCatLabel = new Label("Effort Category:");
	Button clockOutBtn = new Button("Clock out");
	Button mainSubmitBtn = new Button("Submit");
	GridPane backlogPane = new GridPane();
	RadioButton existingProj = new RadioButton("Existing Project");
	RadioButton newProj = new RadioButton("New Project");
	ToggleGroup projectOptions = new ToggleGroup();
	Button enterbtn = new Button("Enter");
	TextArea toDisplay = new TextArea();
	String projName;
	int difficulty, defects, time;
	TextField projNameFld = new TextField();
	TextField difficultyFld = new TextField();
	TextField defectsFld = new TextField();
	TextField timeFld = new TextField();
	Button newProjBtn = new Button("Create Project");
	Label projNameLbl = new Label("Project Name: ");
	Label projDiffLbl = new Label("Project Difficulty Level: ");
	Label projDefectsLbl = new Label("Project Defects: ");
	Label projTimeLbl = new Label("Time (in hours): ");
	
	Label defSelProjLbl= new Label("Select the Project: ");
	Label defNeworCurrLbl = new Label("New or Current Defect?");
	Label defNameLbl = new Label("Defect Name and number: ");
	Label defStatusLbl = new Label("Status of defect: ");
	Label defSymptorResLbl = new Label("Defect Symptoms or Cause/Resolution:");
	Label defInjStepLbl = new Label("Step when injected:");
	Label defRemStepLbl = new Label("Step when removed: ");
	Label defDefCatLbl = new Label("Defect Label: ");
	Label defStatusMsgLbl = new Label("");
	ComboBox<String> defSelProjBox = new ComboBox<String>();
	ComboBox<String> defNeworCurrBox = new ComboBox<String>();
	TextField defNameFld = new TextField();
	ComboBox<String> defStatusBox = new ComboBox<String>();
	TextField defSymptorResFld = new TextField();
	ComboBox<String> defInjStepBox = new ComboBox<String>();
	ComboBox<String> defRemStepBox = new ComboBox<String>();
	ComboBox<String> defDefCatBox = new ComboBox<String>();
	Button defSubmitBtn = new Button("Submit Changes");
	Button defClearBtn = new Button("Clear Fields");
	
	//****** START OF MAIN TAB FEATURES ********************************
	public void start(Stage primaryStage){
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
		
		pane.add(projLabel, 0, 5);				//add to pane and set position
		ProjectList.getItems().addAll(
				"project1",
				"project2",
				"project3");
		pane.add(ProjectList, 1, 5);			//add to pane and set position
		
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
		backlogPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        backlogPane.setHgap(5.5); //set horizontal gap between columns
        backlogPane.setVgap(5.5); //set vertical gap between rows
		existingProj.setToggleGroup(projectOptions);
		newProj.setToggleGroup(projectOptions);
		backlogPane.add(existingProj, 2, 2);
		backlogPane.add(newProj, 2, 3);
		backlogPane.add(enterbtn, 2, 4);
		enterbtn.setOnAction(new enterBtnHandler());
		toDisplay.setEditable(false);
		
		
		//****************END OF BACKLOG PANE CODE *************************
		
		//*****************START OF DEFECTS PANE CODE ***********************
        defectsPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        defectsPane.setHgap(5.5); //set horizontal gap between columns
        defectsPane.setVgap(5.5); //set vertical gap between rows
		defectsPane.add(defSelProjLbl, 1,1,1,1);
		defectsPane.add(defSelProjBox, 1, 2,1,1);
		defSelProjBox.getItems().addAll("Development Project", "Business Project");
		defectsPane.add(defNeworCurrLbl, 1 , 3,1,1);
		defectsPane.add(defNeworCurrBox, 1, 4,1,1);
		defNeworCurrBox.getItems().addAll("New", "Current");
		defectsPane.add(defNameLbl, 1, 5,1,1);
		defectsPane.add(defNameFld, 1, 6,1,1);
		defectsPane.add(defStatusLbl, 1, 7,1,1);
		defectsPane.add(defStatusBox, 1, 8,1,1);
		defStatusBox.getItems().addAll("Close", "Reopen");
		defectsPane.add(defSymptorResLbl, 1, 9,1,1);
		defectsPane.add(defSymptorResFld, 1, 10,1,1);
		defectsPane.add(defInjStepLbl, 1, 11,1,1);
		defectsPane.add(defInjStepBox, 1, 12,1,1);
		defInjStepBox.getItems().addAll("Drafting", "Finalizing", "Team meeting", "Coach meeting", "Stakeholder meeting");
		defectsPane.add(defRemStepLbl, 2, 11,1,1);
		defectsPane.add(defRemStepBox, 2, 12,1,1);
		defRemStepBox.getItems().addAll("Planning", "Information gathering", "Information understanding", "Verifying", "Understanding");
		defectsPane.add(defDefCatLbl, 3, 11,1,1);
		defectsPane.add(defDefCatBox, 3, 12,1,1);
		defDefCatBox.getItems().addAll("60 checking", "70 Data", "80 Function", "90 System", "100 Environment");
		defectsPane.add(defSubmitBtn, 1, 13,1,1);
		defSubmitBtn.setOnAction(new defSubmitBtnHandler());
		defectsPane.add(defClearBtn, 3, 1,1,1);
		defClearBtn.setOnAction(new defClearBtnHandler());
		
		
		//****************END OF DEFECTS PANE CODE *************************
	}
	
	//**********************Button Handling intensifies************************
	
		private class InButtonHandler implements EventHandler<ActionEvent>{		//Clock in button handler
			public void handle(ActionEvent e) {	
				timePunch.setText("You are clocked in");		//sets the text to clocked in
				timePunch.setTextFill(Color.GREEN);				//changes to green text
			}
		}
		private class OutButtonHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				timePunch.setText("You are not clocked in");
				timePunch.setTextFill(Color.RED);
			}
		}	
		private class AuthenticateBtnHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
		    	username = usernameTxtFld.getText();
		    	password = passwordTxtFld.getText();
		    	boolean validUser = true;
		    	//BEN: tie in the method that checks for if a user is valid to this boolean, and have it set the validUser to true or false
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
		private class SubmitBtnMainHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				Label submission = new Label("Submitted.");
				pane.add(submission, 2, 8);
				ProjectList.getSelectionModel().clearSelection();
				LifeCycleList.getSelectionModel().clearSelection();
				EffortCat.getSelectionModel().clearSelection();
				EffortCat2.getSelectionModel().clearSelection();
				//BEN: if we do want to actually make a submitable form, i can set it so before it 
				//clears the boxes, the choices get set to variables, or we can just leave it as is to make it seem like working
			}
		}
		private class enterBtnHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				//BEN: this would be the spot to have methods producing strings to 
				//output text based on what options your backlog program uses, the .setText takes a string parameter 
				//and displays it
				if(existingProj.isSelected()) {
					backlogPane.add(toDisplay, 2, 5);
					toDisplay.setText("existing");
				}
				else if(newProj.isSelected()) {
					backlogPane.add(projNameLbl, 2, 6);
					backlogPane.add(projDiffLbl, 2, 7);
					backlogPane.add(projDefectsLbl, 2, 8);
					backlogPane.add(projTimeLbl, 2, 9);
					backlogPane.add(projNameFld, 3, 6);
					backlogPane.add(difficultyFld, 3, 7);
					backlogPane.add(defectsFld, 3, 8);
					backlogPane.add(timeFld, 3, 9);
					backlogPane.add(newProjBtn, 2, 10);
					newProjBtn.setOnAction(new newProjBtnHandler());
				}
			}
		}
		private class newProjBtnHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				projName = projNameFld.getText();
				difficulty = Integer.parseInt(difficultyFld.getText());
				defects = Integer.parseInt(defectsFld.getText());
				time = Integer.parseInt(timeFld.getText());
				
			}
		}
		private class defSubmitBtnHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				defSelProjBox.getSelectionModel().clearSelection();
				defNeworCurrBox.getSelectionModel().clearSelection();
				defNameFld.clear();
				defStatusBox.getSelectionModel().clearSelection();
				defSymptorResFld.clear();
				defInjStepBox.getSelectionModel().clearSelection();
				defRemStepBox.getSelectionModel().clearSelection();
				defDefCatBox.getSelectionModel().clearSelection();
				defStatusMsgLbl.setText("Submitted");
				defStatusMsgLbl.setTextFill(Color.GREEN);
				defectsPane.add(defStatusMsgLbl, 2, 13,1,1);
				
			}
		}
		private class defClearBtnHandler implements EventHandler<ActionEvent>{
			public void handle(ActionEvent e) {
				defSelProjBox.getSelectionModel().clearSelection();
				defNeworCurrBox.getSelectionModel().clearSelection();
				defNameFld.clear();
				defStatusBox.getSelectionModel().clearSelection();
				defSymptorResFld.clear();
				defInjStepBox.getSelectionModel().clearSelection();
				defRemStepBox.getSelectionModel().clearSelection();
				defDefCatBox.getSelectionModel().clearSelection();
				
			}
		}
}
