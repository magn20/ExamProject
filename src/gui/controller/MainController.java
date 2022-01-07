package gui.controller;

import gui.util.SceneSwapper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView tvMovies;
    SceneSwapper sceneSwapper = new SceneSwapper();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    /**
     * the category that is selected.
     * @param actionEvent
     */
    public void onCategoriesBtn(ActionEvent actionEvent)  {

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
    public void onRateBtn(ActionEvent actionEvent) {
        //TODO do make a pop up window where you can rate the movie selected
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

}
