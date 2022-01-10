package gui.controller;

import be.Category;
import be.Movie;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import gui.util.SceneSwapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView<Movie> tvMovies;
    public ComboBox<String> categoriesDropDown;

    // tablecolum for table for movies.
    public TableColumn<Movie, Float> tcIMDBRating;
    public TableColumn<Movie, Float> tcUserRating;
    public TableColumn<Movie, String> tcTitle;


    SceneSwapper sceneSwapper = new SceneSwapper();
    CategoryModel categoryModel = new CategoryModel();
    MovieModel movieModel = new MovieModel();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillTableview();

        // initializing the Categories, could have been done on a better way who cares tbh. (just seems so hard to find out how)
        for(Category category : categoryModel.getCategories()) {
            categoriesDropDown.getItems().add(category.getTitle());
        }
    }

    public void fillTableview(){

        // initializing the Table Collum
        tcTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
        tcUserRating.setCellValueFactory(new PropertyValueFactory<Movie, Float>("personalRating"));
        tcIMDBRating.setCellValueFactory(new PropertyValueFactory<Movie, Float>("imdbRating"));

        tvMovies.setItems(getMovies());
    }

    /**
     * creates and ruturn observablelist from list off all movies.
     * @return an observablelist of all movies.
     */
    public ObservableList<Movie> getMovies(){
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        movies.addAll(movieModel.getMovies());
        return movies;
    }


    /**
     * the category that is selected.
     * @param actionEvent
     */
    public void onCategoriesBtn(ActionEvent actionEvent)  {
     //TODO
    }

    /**
     * opens a mediaplayer on users pc.
     * @param actionEvent
     */
    public void onWatchBtn(ActionEvent actionEvent) {
        //TODO add so it opens a mediaplayer
    }

    /**
     * opens a browser of the movie infomation.
     * @param actionEvent
     */
    public void onInfoBtn(ActionEvent actionEvent) {
        //TODO open browser for info for movie
    }

    /**
     * switches to a rate selection scene.
     * @param actionEvent
     */
    public void onRateBtn(ActionEvent actionEvent) throws IOException {
        sceneSwapper.sceneSwitch(new Stage(), "UserRatingConfig.fxml");
    }

    /**
     * switches to category config scene
     * @param actionEvent
     * @throws IOException
     */
    public void onCategoryConfigBtn(ActionEvent actionEvent) throws IOException {
        sceneSwapper.sceneSwitch(new Stage(), "CategoryConfig.fxml");
    }

    /**
     *  Switches to the movie config scene
     * @param actionEvent
     * @throws IOException
     */
    public void onMovieConfigBtn(ActionEvent actionEvent) throws IOException {
        sceneSwapper.sceneSwitch(new Stage(), "MovieConfig.fxml");
    }

    /**
     *  Deletes the chosen category
     * @param actionEvent
     * @throws IOException
     */

    public void onDeleteBtn(ActionEvent actionEvent) throws SQLException {

        System.out.println(categoriesDropDown.getSelectionModel().getSelectedItem());
        for(Category category : categoryModel.getCategories()){
            if(category.getTitle().equals(categoriesDropDown.getSelectionModel().getSelectedItem())){
                categoryModel.deleteCategory(category);
            }
        }
        categoriesDropDown.getItems().remove(categoriesDropDown.getSelectionModel().getSelectedItem());
    }
    public Movie getSelectedMovie(){
       return tvMovies.getSelectionModel().getSelectedItem();
    }
}
