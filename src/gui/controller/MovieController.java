package gui.controller;

import be.Category;
import be.Movie;
import bll.MovieManager;
import dal.db.MovieDAO;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class MovieController implements Initializable {
    public TextField lblMovieTitle;
    public ComboBox catDropDown;
    public TextField lblUrlText;
    public AnchorPane anchorPane;
    public TextField lblIMDBRating;
    private File file;
    final FileChooser fileChooser;
    private CategoryModel categoryModel;

    public MovieController(){
        fileChooser = new FileChooser();
        categoryModel = new CategoryModel();
    }

    public void onChooseBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie files", "*.mp4", "*.wave"));
        file = fileChooser.showOpenDialog(stage); // assigns the chosen file to the file
        fileChooser.setInitialDirectory(file.getParentFile()); //sets the initial file when the fileChooser is opened to the last known directory

        if(file != null){ //DirectoryChooser returns null if the user closes the browse window
            lblUrlText.setText(file.getAbsolutePath().replace("\\", "/"));
            };
        }

        //TODO NEEDS TO CHANGE THE LASTVIEW IN DATABASE INTO NVARCHAR
    public void onSaveBtn(ActionEvent actionEvent) {
        MovieModel movieModel = new MovieModel();
        Date today = Calendar.getInstance().getTime();
        movieModel.createMovie("hello", 10, 10, "fie", "2023");
        // will work when we made the change above.
        //movieModel.createMovie(lblMovieTitle.getText(), 0,Float.parseFloat(lblIMDBRating.getText()), lblUrlText.toString(),today.toString());
        closeStage();
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

        for(Category category : categoryModel.getCategories()) {
            catDropDown.getItems().add(category.getTitle());
        }
    }
}
