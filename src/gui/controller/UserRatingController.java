package gui.controller;
import gui.ExamProject;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import static be.DisplayMessage.displayError;

public class UserRatingController implements Initializable {
    public ComboBox dropDownRating;
    public Button btnClose;
    MovieModel movieModel;

    /**
     * initialize the dropdown.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropDownRating.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10");
        movieModel = new MovieModel();
    }


    /**
     * Saves a users rating on a movie.
     */
    public void onSaveBtn(ActionEvent actionEvent) throws Exception {

        try {
            // reference to maincontroller.
            MainController mainController = new ExamProject().getController();
            // gets the selected movie and sets the personalrating as a float. using the rating the user picked.
            mainController.getSelectedMovie().setPersonalRating(Float.parseFloat(dropDownRating.getSelectionModel().getSelectedItem().toString()));
            //updates the movie in the Database
            movieModel.updateMovie(mainController.getSelectedMovie());
            //updates the gui with the changed information
            mainController.getMovies();
            mainController.fillTableview();
            //closes the stage
            closeStage();
        } catch (Exception e){
            displayError(e);
        }

    }

    public void onCloseBtn(ActionEvent actionEvent) {
        closeStage();
    }

    /**
     * closess the stage.
     */
    public void closeStage(){
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }


}
