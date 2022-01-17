package gui.controller;
import be.Category;
import gui.ExamProject;
import gui.model.CatMovieModel;
import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static be.DisplayMessage.displayError;

public class AddCategoryController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ComboBox comboBoxCategories;
    private CategoryModel categoryModel = new CategoryModel();
    private CatMovieModel catMovieModel = new CatMovieModel();

    /**
     *
     * @param actionEvent
     * @throws SQLException
     */
    public void onSaveBtn(ActionEvent actionEvent) throws SQLException {

        try {
            // reference to maincontroller to replace the tableview with the new movie.
            MainController mainController = new ExamProject().getController();

            // add the category to the movie.
            for (Category category : categoryModel.getCategories()) {
                if (category.getTitle().equals(comboBoxCategories.getSelectionModel().getSelectedItem().toString())) {
                    catMovieModel.addMovieToCategory(mainController.tvMovies.getSelectionModel().getSelectedItem(), category);
                }
            }
            mainController.getMovies();
            mainController.fillTableview();

            closeStage();
        } catch (Exception e){
            displayError(e);
        }
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

    /**
     * sets the items in the combobox.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBoxCategories.getItems().clear();
        for(Category category : categoryModel.getCategories()) {
            comboBoxCategories.getItems().add(category.getTitle());
        }

    }
}

