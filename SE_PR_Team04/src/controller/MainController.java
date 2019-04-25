/*
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
public class MainController implements Initializable{
	
	/** The exercises. */
	private List<Exercise> exercises = new LinkedList<Exercise>();;
	
	 /** The root. */
 	@FXML
	 private AnchorPane root;
	 
	 /** The lv exercises. */
 	@FXML
	 private ListView<Exercise> lv_exercises;
	 
 	/** The ov exercises. */
 	private ObservableList<Exercise> ov_exercises;
	 
	 /** The cb workouts. */
 	@FXML
	 private ComboBox<String> cb_workouts;
	 
 	/** The ov workouts. */
 	private ObservableList<String> ov_workouts;
	 
	/** The btn new workout. */
	@FXML
	private Button btn_NewWorkout;
	
	/**
	 * Handle button new workout.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleButton_newWorkout(ActionEvent event) throws IOException {
		
		  Stage oldStage;
          oldStage = (Stage)root.getScene().getWindow();

          FXMLLoader fxmlLoader = new FXMLLoader();
          fxmlLoader.setLocation(getClass().getResource("/view/Create_Workout.fxml"));
          Parent root2 = (Parent) fxmlLoader.load();
          Stage stage = new Stage();
          stage.setTitle("Create Workout!");
          stage.setScene(new Scene(root2));  
          stage.show();
          oldStage.close();
		
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	
	}
	
}
