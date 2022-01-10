package gui.controller;

import be.Category;
import be.Movie;
import bll.MovieManager;
import dal.db.CatMovieDAO;
import dal.db.MovieDAO;
import gui.model.CatMovieModel;
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
import java.sql.SQLException;
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
    private  CatMovieModel catMovieModel;

    public MovieController(){
        fileChooser = new FileChooser();
        categoryModel = new CategoryModel();
        catMovieModel = new CatMovieModel();
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
    public void onSaveBtn(ActionEvent actionEvent) throws SQLException {
        MovieModel movieModel = new MovieModel();
        Date today = Calendar.getInstance().getTime();
        // will work when we made the change above.
        Movie movie = new Movie(lblMovieTitle.getText(), 0,Float.parseFloat(lblIMDBRating.getText()), lblUrlText.toString(),today.toString(), catDropDown.getSelectionModel().getSelectedItem().toString());
        movieModel.createMovie(movie);

            for(Category category : categoryModel.getCategories()){
             if(category.getTitle().equals(catDropDown.getSelectionModel().getSelectedItem().toString())){
            catMovieModel.addMovieToCategory(movie, category);
            }
            }

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
