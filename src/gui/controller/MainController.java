package gui.controller;

import be.Category;
import gui.model.CategoryModel;
import gui.util.SceneSwapper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView tvMovies;
    public ComboBox categoriesDropDown;
    SceneSwapper sceneSwapper = new SceneSwapper();
    CategoryModel categoryModel = new CategoryModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Category category : categoryModel.getCategories()) {
            categoriesDropDown.getItems().add(category.getTitle());
        }

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
}
