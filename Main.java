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
	
	Label timePunch;
	final int win_x_size = 500, win_y_size = 200;
	public void start(Stage primaryStage){
		// Create a GridPane object and set its properties
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER); //center the GridPane
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5); //set horizontal gap between columns
		pane.setVgap(5.5); //set vertical gap between rows
		pane.setPrefWidth(win_x_size);
		pane.setPrefHeight(win_y_size);
	
		Label header = new Label("Effort Logger Console");	//header for top of the GUI
		pane.add(header, 2, 0);				//add to pane in location 2,0
		GridPane.setHalignment(header, HPos.CENTER);		//set horizontal alignment
		
		timePunch = new Label("You are not clocked in");		//basic punch in message
		timePunch.setTextFill(Color.RED);
		pane.add(timePunch, 2, 1);				//set position
		
		
		Button clockInBtn = new Button("Clock In");		//create a button to clock in
		pane.add(clockInBtn, 1, 1);						//add to pane and set position
		clockInBtn.setOnAction(new InButtonHandler());	//tie button to an event handler
		
		Label projLabel = new Label("Project:");			//label for projects
		pane.add(projLabel, 0, 5);				//add to pane and set position
		ComboBox ProjectList = new ComboBox();			//create a drop down box and then add the items
		ProjectList.getItems().addAll(
				"project1",
				"project2",
				"project3");
		pane.add(ProjectList, 1, 5);			//add to pane and set position
		
		Label lifeCycleLabel = new Label("Life Cycle Step:");		//label for life cycle
		pane.add(lifeCycleLabel, 2, 5);			//add to pane and set position
		GridPane.setHalignment(lifeCycleLabel, HPos.RIGHT);			//set alignment for more visually pleasing
		ComboBox LifeCycleList = new ComboBox();			//create drop down box and add items
		LifeCycleList.getItems().addAll(
				"work",
				"work2",
				"work3");
		pane.add(LifeCycleList, 3, 5);				//add to the pane and set position
		
		Label effortCatLabel = new Label("Effort Category:");
		pane.add(effortCatLabel, 0, 6);
		ComboBox EffortCat = new ComboBox();	//create drop down box and add items
		EffortCat.getItems().addAll(
				"effort1",
				"effort2",
				"effort3");
		pane.add(EffortCat, 1, 6);			//add to pane and set position
		
		ComboBox EffortCat2 = new ComboBox();		// create drop down box and add items
		EffortCat2.getItems().addAll(
				"placeholder",
				"placeholder",
				"placeholder");
		pane.add(EffortCat2, 3, 6);				//add to pane and set position

		
		Button clockOutBtn = new Button("Clock out");		//create a button to clock out
		pane.add(clockOutBtn, 1, 7);					//add to pane and set position
		clockOutBtn.setOnAction(new OutButtonHandler());		//tie button to event handler

		// Create a scene and place it in the stage
		
		
		UserAuthPane userAuthPane = new UserAuthPane();
		DefectsPane defectsPane = new DefectsPane();		//make a new defects pane to add
		TabPane tabPane = new TabPane();					// make a tab pane to set the tabs on
		Tab mainTab = new Tab();					//create tabs for the required menus, and then set content and their title
		Tab defectTab = new Tab();
		mainTab.setText("Effort Console");
		mainTab.setContent(pane);
		defectTab.setText("Defect Console");
		defectTab.setContent(defectsPane);
		tabPane.getSelectionModel().select(0);				//add the tabs to the pane
        tabPane.getTabs().addAll(mainTab, defectTab);
        UserAuthPane userAuth = new UserAuthPane();
		Scene userAuthScene = new Scene(userAuthPane);
		Scene scene = new Scene(tabPane);
        
        Button authenticateBtn = new Button("Log in");		//create a button to clock out
    	userAuthPane.add(authenticateBtn, 1, 1);					//add to pane and set position
    	authenticateBtn.setOnAction(e -> primaryStage.setScene(scene));		//tie button to event handler
        
		
		primaryStage.setTitle("Log In"); // Set the stage title
		primaryStage.setScene(userAuthScene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	
		
        
 
		
		
	}
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
}
