package gui.controller;

import be.Category;
import be.Movie;
import gui.ExamProject;
import gui.model.CatMovieModel;
import gui.model.CategoryModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import static be.DisplayMessage.displayError;

public class MovieController implements Initializable {
    public TextField lblMovieTitle;
    public ComboBox catDropDown;
    public TextField lblUrlText;
    public AnchorPane anchorPane;
    public TextField lblIMDBRating;

    private File file;
    final FileChooser fileChooser;

    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;
    private MovieModel movieModel;


    /**
     * the constructor for the MovieController class, used for creating instances for the different classes.
     */
    public MovieController() {
        movieModel = new MovieModel();
        fileChooser = new FileChooser();
        categoryModel = new CategoryModel();
        catMovieModel = new CatMovieModel();
    }


    /**
     * initialize the drop box for categories. so all categories is displayed.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (Category category : categoryModel.getCategories()) {
            catDropDown.getItems().add(category.getTitle());
        }
    }


    /**
     * gives user option to choose a file they want to use for program.
     * will put the url of the file location on users pc into the url label.
     *
     * @param actionEvent runs when an action is performed on the button.
     */
    public void onChooseBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) anchorPane.getScene().getWindow(); // get stage.
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie files", "*.mp4", "*.mpeg4"));
        file = fileChooser.showOpenDialog(stage); // assigns the chosen file to the file
        fileChooser.setInitialDirectory(file.getParentFile()); //sets the initial file when the fileChooser is opened to the last known directory

        if (file != null) { //DirectoryChooser returns null if the user closes the browse window
            lblUrlText.setText(file.getAbsolutePath().replace("\\", "/"));
        }
        ;
    }

    /**
     * handles the creating of the movie and added a category to it and saves it. and displays the change affected in maincontroller
     * <p>
     * (maybe method needs to have less function.)
     *
     * @param actionEvent
     * @throws Exception
     */
    public void onSaveBtn(ActionEvent actionEvent) throws Exception {
        try {
            // get the current day.
            Date today = Calendar.getInstance().getTime();

            // creates the movie.
            Movie movie = new Movie(lblMovieTitle.getText(), 0, Float.parseFloat(lblIMDBRating.getText()), lblUrlText.getText(), today.toString(), catDropDown.getSelectionModel().getSelectedItem().toString());
            movieModel.createMovie(movie);

            addCategoryToNewMovie();
            updateMainController();

            //closes the stage
            closeStage();
        } catch (Exception e) {
            displayError(e);
        }
    }

    public void updateMainController() throws SQLException {
        // reference to maincontroller to replace the tableview with the new movie.
        MainController mainController = new ExamProject().getController();
        mainController.getMovies();
        mainController.fillTableview();

    }

    public void addCategoryToNewMovie() throws SQLException {

        // add the category to the movie.
        for (Category category : categoryModel.getCategories()) {
            if (category.getTitle().equals(catDropDown.getSelectionModel().getSelectedItem().toString())) {
                catMovieModel.addMovieToCategory(movieModel.getMovies().get(movieModel.getMovies().size() - 1), category);
            }
        }
    }

    /**
     * closes the stage.
     *
     * @param actionEvent
     */
    public void onCloseBtn(ActionEvent actionEvent) {
        closeStage();
    }

    /**
     * closes the stage.
     */
    public void closeStage() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}
