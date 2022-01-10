package gui.controller;

import bll.MovieManager;
import com.sun.tools.javac.Main;
import gui.ExamProject;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRatingController implements Initializable {
    public ComboBox dropDownRating;
    public AnchorPane anchorPane;
    MovieModel movieModel;

    public void onDropAction(ActionEvent actionEvent) {

    }

    public void onSaveBtn(ActionEvent actionEvent) throws Exception {
        MainController mainController = new ExamProject().getController();
        mainController.getSelectedMovie().setPersonalRating(Float.parseFloat(dropDownRating.getSelectionModel().getSelectedItem().toString()));
        movieModel.updateMovie(mainController.getSelectedMovie());

    }

    public void onCloseBtn(ActionEvent actionEvent) {
        closeStage();
    }

    /**
     * closess the stage.
     */
    public void closeStage(){
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dropDownRating.getItems().addAll("0","1","2","3","4","5","6","7","8","9","10");
        movieModel = new MovieModel();
    }
}
