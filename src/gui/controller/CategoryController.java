package gui.controller;

import gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CategoryController {
        public TextField categoryName;
        public AnchorPane anchorPane;
        CategoryModel categoryModel = new CategoryModel();

        public void onCloseBtn(ActionEvent actionEvent) {
                closeStage();
        }

        public void onSaveBtn(ActionEvent actionEvent) {
                categoryModel.addCategory(categoryName.getText());
                closeStage();
        }

        /**
         * closess the stage.
         */
        public void closeStage(){
                Stage stage = (Stage) anchorPane.getScene().getWindow();
                stage.close();
        }
}
