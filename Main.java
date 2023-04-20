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
    UserAuthPane userAuthPane = new UserAuthPane();
	DefectsPane defectsPane = new DefectsPane();		
	GridPane effortLogEditor = new GridPane();
	TabPane tabPane = new TabPane();					
	Tab mainTab = new Tab();					
	Tab defectTab = new Tab();
	Tab effortLogEditorTab = new Tab();
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
	
	//****** START OF MAIN TAB FEATURES ********************************
	public void start(Stage primaryStage){
		// Create a GridPane object and set its properties
		pane.setAlignment(Pos.CENTER); //center the GridPane
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5); //set horizontal gap between columns
		pane.setVgap(5.5); //set vertical gap between rows
		pane.setPrefWidth(win_x_size);
		pane.setPrefHeight(win_y_size);
	
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
		tabPane.getSelectionModel().select(0);				//add the tabs to the pane
        tabPane.getTabs().addAll(mainTab, defectTab, effortLogEditorTab);
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
		
		
		//*****************START OF DEFECTS PANE CODE ***********************
		
		
		
		
		
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
				ProjectList.getItems().clear();
				LifeCycleList.getItems().clear();
				EffortCat.getItems().clear();
				EffortCat2.getItems().clear();
				//BEN: if we do want to actually make a submitable form, i can set it so before it 
				//clears the boxes, the choices get set to variables, or we can just leave it as is to make it seem like working
			}
		}
}
