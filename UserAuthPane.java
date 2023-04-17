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
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
public class UserAuthPane extends GridPane {
	
public void start(Stage stage) {
	GridPane pane = new GridPane();
	final int win_x_size = 500, win_y_size = 200;
	pane.setAlignment(Pos.CENTER); //center the GridPane
	pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	pane.setHgap(5.5); //set horizontal gap between columns
	pane.setVgap(5.5); //set vertical gap between rows
	pane.setPrefWidth(win_x_size);
	pane.setPrefHeight(win_y_size);
	
	Button authenticateBtn = new Button("Log in");		//create a button to clock out
	pane.add(authenticateBtn, 1, 1);					//add to pane and set position
	authenticateBtn.setOnAction(new ButtonHandler());		//tie button to event handler
}

	public static boolean userCheck(int check) {
		if(check == 1)
			return true;
		else
			return false;
	}
	private class ButtonHandler implements EventHandler<ActionEvent>{	
		public void handle(ActionEvent e) {	
			userCheck(1);
		}
	}
}
