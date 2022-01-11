package gui.controller;

import be.Category;
import be.Movie;
import com.sun.source.tree.WhileLoopTree;
import dal.MovieSearcher;
import gui.model.CatMovieModel;
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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView<Movie> tvMovies;
    public ComboBox<String> categoriesDropDown;

    // tablecolumn for table for movies.
    public TableColumn<Movie, Float> tcIMDBRating;
    public TableColumn<Movie, Float> tcUserRating;
    public TableColumn<Movie, String> tcTitle;
    public TableColumn<Movie, String> tcCategory;
    public TextField searchMovie;

    // creating instances of classes.
    SceneSwapper sceneSwapper = new SceneSwapper();
    CategoryModel categoryModel = new CategoryModel();
    MovieModel movieModel = new MovieModel();
    CatMovieModel catMovieModel = new CatMovieModel();

    ObservableList<Movie> allMovies = FXCollections.observableArrayList();


    /**
     * to initialize our stage so all the data is displayed.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            fillTableview();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fillDropDownCategories();
        MovieSearcher movieSearcher = new MovieSearcher();

        // Search in all songs
        searchMovie.textProperty().addListener((observableValue, oldValue, newValue) -> {

            try {
                tvMovies.getItems().clear();
                tvMovies.setItems(movieSearcher.search(getMovies(), newValue));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void fillDropDownCategories(){
        // initializing the Categories, could have been done on a better way who cares tbh. (just seems so hard to find out how)
        categoriesDropDown.getItems().clear();
        for(Category category : categoryModel.getCategories()) {
            categoriesDropDown.getItems().add(category.getTitle());
        }
    }

    /**
     * fills the tableview with all the movie information.
     */
    public void fillTableview() throws SQLException {
        tcTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("Title"));
        tcUserRating.setCellValueFactory(new PropertyValueFactory<Movie, Float>("personalRating"));
        tcIMDBRating.setCellValueFactory(new PropertyValueFactory<Movie, Float>("imdbRating"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<Movie, String>("categories"));

        tvMovies.setItems(getMovies());
    }

    /**
     * creates and return observablelist from list off all movies.
     * @return an observablelist of all movies.
     */
    public ObservableList<Movie> getMovies() throws SQLException {
        allMovies.clear();
        allMovies.addAll(movieModel.getMovies());
        for(Movie movie : allMovies){
            movie.removeCategories();
            List<Category> categories = catMovieModel.getAllCategoriesFromOneMovie(movie);
            for (Category category : categories){
                movie.setCategories(category.getTitle());
            }
        }
        return allMovies;
    }


    /**
     * the category that is selected. ()really not used.
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
     * opens a browser of the movie information.
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

        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "You sure you wanna do this you idiot");
        a.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {
            for(Category category : categoryModel.getCategories()){
                if(category.getTitle().equals(categoriesDropDown.getSelectionModel().getSelectedItem())){
                    try {
                        categoryModel.deleteCategory(category);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        fillDropDownCategories();

        categoriesDropDown.getItems().remove(categoriesDropDown.getSelectionModel().getSelectedItem());
    }

    /**
     * @return the selected movie object in our tableview.
     */
    public Movie getSelectedMovie(){
       return tvMovies.getSelectionModel().getSelectedItem();
    }

    public void onAddCategory(ActionEvent actionEvent) throws IOException {
        sceneSwapper.sceneSwitch(new Stage(), "AddCategoryConfig.fxml");
    }

    public void onDeleteMovie(ActionEvent actionEvent) throws SQLException {

        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "You sure you wanna do this you idiot");
        a.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {
            movieModel.deleteMovie(tvMovies.getSelectionModel().getSelectedItem());
        });
        fillTableview();
    }
}
