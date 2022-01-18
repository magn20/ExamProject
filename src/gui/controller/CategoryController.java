package gui.controller;

import gui.ExamProject;
import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static be.DisplayMessage.displayError;

public class CategoryController {
    public TextField categoryName;
    public AnchorPane anchorPane;
    CategoryModel categoryModel;


    public CategoryController() {
        categoryModel = new CategoryModel();
    }

    public void onCloseBtn(ActionEvent actionEvent) {
        closeStage();
    }


    public void onSaveBtn(ActionEvent actionEvent) {
        try {
            categoryModel.addCategory(categoryName.getText());
            // reference to maincontroller to replace the tableview with the new movie.
            MainController mainController = new ExamProject().getController();
            mainController.fillDropDownCategories();

            closeStage();
        } catch (Exception e) {
            displayError(e);
        }
    }

    /**
     * closess the stage.
     */
    public void closeStage() {
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        stage.close();
    }
}
