/*
 * 
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Database;
import model.Exercise;
import model.ReadAndWriteCSV;
import model.Workout;
import java.sql.SQLException;
import model.Database;
import model.Exercise;


// TODO: Auto-generated Javadoc
/**
 * The Class CreateWorkoutController.
 */
public class CreateWorkoutController implements Initializable{

	List<Exercise> hexercises = new LinkedList<Exercise>();
	
	/** The exercises. */
	List<Exercise> exercises = new LinkedList<Exercise>();

	/** The workoutname. */
	String workoutname = "";
	
	@FXML
    TableView<ExerciseTV> tvExercise;
    ObservableList<ExerciseTV> olist;

    @FXML
    TableColumn<ExerciseTV, String> nameCol = new TableColumn<ExerciseTV,String>("Name");

    @FXML
    TableColumn<ExerciseTV, String> muscleCol = new TableColumn<ExerciseTV,String>("Muscle");

    @FXML
    TableColumn<ExerciseTV, Integer> repsCol = new TableColumn<ExerciseTV,Integer>("Reps");

    @FXML
    TableColumn<ExerciseTV, Boolean> activeCol = new TableColumn<ExerciseTV,Boolean>("Select");


	/** The root. */
	@FXML
	private AnchorPane root;

	/** The tf workoutname. */
	@FXML
	private TextField tf_workoutname;


    @FXML
    private DatePicker datePicker;
    
	/** The lv exercises. */
	@FXML
	private ListView<String> lv_exercises;
	
	//@FXML
	//private CheckBoxListCell<String> clv_exercises;

	/** The ov exercises. */
	private ObservableList<String> ov_exercises;

	/**
	 * Handle btn new exercise.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void handleBtn_newExercise(ActionEvent event) throws IOException {

		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Create_Exercise.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		CreateExerciseController create = fxmlLoader.getController();
		create.setData(exercises, tf_workoutname.getText().toString());
		Stage stage = new Stage();
		stage.setTitle("Create Workout!");
		stage.setScene(new Scene(root2));
		stage.show();
		oldStage.close();

	}

	
	/**
	 * Handle btn save workout.
	 *
	 * @param event the event
	 * @throws IOException  Signals that an I/O exception has occurred.
	 * @throws SQLException the SQL exception
	 */
	@FXML
	private void handleBtn_saveWorkout(ActionEvent event){

		for (int i = 0; i < olist.size(); i++) {
            if(olist.get(i).getActive().isSelected() == true){
                exercises.add(new Exercise(olist.get(i).getName(),olist.get(i).getTrains(),olist.get(i).getReps()));
            }
        }
       
        try {
           
            Database.getInstance().createWorkout(tf_workoutname.getText(), datePicker.getValue(), exercises);


		Stage oldStage;
		oldStage = (Stage) root.getScene().getWindow();

		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("/view/Main.fxml"));
		Parent root2 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setTitle("Create Workout!");
		stage.setScene(new Scene(root2));
		stage.show();
		oldStage.close();
	 } catch (SQLException ex) {
         Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (IOException ex) {
         Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
     }
	}

	/*
	 * @Override public void initialize(URL location, ResourceBundle resources) {
	 * 
	 * tf_workoutname.setText(workoutname); List<String> sexercise = new
	 * LinkedList<String>(); for(Exercise exercise : exercises) {
	 * sexercise.add(exercise.getName()); } ov_exercises =
	 * FXCollections.observableArrayList(sexercise);
	 * lv_exercises.setItems(ov_exercises); }
	 */
	
	  @FXML
		private void handleBtn_backToMain(ActionEvent event) {

	            try {
	                Stage oldStage;
	                oldStage = (Stage)root.getScene().getWindow();
	                
	                FXMLLoader fxmlLoader = new FXMLLoader();
	                fxmlLoader.setLocation(getClass().getResource("/view/Main.fxml"));
	                Parent root2 = (Parent) fxmlLoader.load();
	                Stage stage = new Stage();
	                stage.setTitle("Workouts");
	                stage.setScene(new Scene(root2));
	                stage.show();
	                oldStage.close();
	            } catch (IOException ex) {
	                Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
	            }
	           
			
		}
	/**
	 * Sets the data.
	 *
	 * @param oexercises   the oexercises
	 * @param oworkoutname the oworkoutname
	 */
	public void setData(List<Exercise> oexercises, String oworkoutname) {

        hexercises = oexercises;
        workoutname = oworkoutname;
        
        tf_workoutname.setText(workoutname);
	}
	
	
	public void initialize(URL location, ResourceBundle resources) {
        try {
           hexercises = Database.getInstance().getAllExercises();
           //  Database.getInstance().createExercise("Ober",new Muscle("help"),15);
           //Database.getInstance().getMuscleId("help");
        } catch (SQLException ex) {
            Logger.getLogger(CreateWorkoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nameCol.setCellValueFactory(new PropertyValueFactory<ExerciseTV, String>("name"));
        muscleCol.setCellValueFactory(new PropertyValueFactory("trains"));
        repsCol.setCellValueFactory(new PropertyValueFactory("reps"));

        activeCol.setCellValueFactory(new PropertyValueFactory("active"));

        getExerciseList();
        tvExercise.setItems(olist);
       // tvExercise.getColumns().setAll(nameCol, muscleCol,repsCol,activeCol);
       
        //tvExercise.getColumns().addAll(nameCol, muscleCol, repsCol, activeCol);
 
        
     /* List<String> sexercise = new LinkedList<String>();
	for(Exercise exercise : hexercises) {
		sexercise.add(exercise.getName());
	}
	ov_exercises = FXCollections.observableArrayList(sexercise);
	lv_exercises.setItems(ov_exercises);
        System.out.println("");*/
}
	
	private void getExerciseList() {
        List<ExerciseTV> list = new LinkedList<ExerciseTV>();
        olist = FXCollections.observableArrayList();
        for (int i = 0; i < hexercises.size(); i++) {

            olist.add(new ExerciseTV(hexercises.get(i).getName(), hexercises.get(i).getTrains(), 
            hexercises.get(i).getReps(), true));

        }


}
