package gui.controller;
import be.Category;
import gui.ExamProject;
import gui.model.CatMovieModel;
import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static be.DisplayMessage.displayError;

public class AddCategoryController implements Initializable {

    @FXML
    private ComboBox comboBoxCategories;
    private CategoryModel categoryModel;
    private CatMovieModel catMovieModel;
    private MainController mainController;



    public AddCategoryController(){
        categoryModel = new CategoryModel();
        catMovieModel = new CatMovieModel();
        mainController = new ExamProject().getController();
    }

    /**
     * sets the items in the combobox.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // fills up the dropdown with all categories.
        comboBoxCategories.getItems().clear();
        for(Category category : categoryModel.getCategories()) {
            comboBoxCategories.getItems().add(category.getTitle());
        }

    }


    /**
     *  Gives a Movie a Category.
     * @param actionEvent
     * @throws SQLException
     */
    public void onSaveBtn(ActionEvent actionEvent) throws SQLException {

        try {
            // add the category to the movie.
            for (Category category : categoryModel.getCategories()) {
                if (category.getTitle().equals(comboBoxCategories.getSelectionModel().getSelectedItem().toString())) {
                    catMovieModel.addMovieToCategory(mainController.tvMovies.getSelectionModel().getSelectedItem(), category);
                }
            }
            updateMainController();
            closeStage();

        } catch (Exception e){
            displayError(e);
            }
        }

    /**
     * calls MainController Java class to get all the movies and reupdate the values in the tableview.
     * @throws SQLException
     */
    public void updateMainController() throws SQLException {
            mainController.getMovies();
            mainController.fillTableview();
        }

    /**
     * clsoses the stage
     * @param actionEvent when a action is performed on the button.
     */
    public void onCloseBtn(ActionEvent actionEvent) {
        closeStage();
    }
        /**
         * closes the stage.
         */
        public void closeStage() {
            Stage stage = (Stage) comboBoxCategories.getScene().getWindow();
            stage.close();
        }

}

