package gui.controller;

import be.Category;
import gui.model.CategoryModel;
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
import java.util.ResourceBundle;

public class MovieController implements Initializable {
    public TextField lblMovieTitle;
    public ComboBox catDropDown;
    public TextField lblUrlText;
    public AnchorPane anchorPane;
    private File file;
    final FileChooser fileChooser;

    public MovieController(){
        fileChooser = new FileChooser();
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

    public void onSaveBtn(ActionEvent actionEvent) {

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
        CategoryModel categoryModel = new CategoryModel();
        for(Category category : categoryModel.getCategories()) {
            catDropDown.getItems().add(category.getTitle());
        }
    }
}
