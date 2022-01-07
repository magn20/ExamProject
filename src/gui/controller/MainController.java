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

    public void movieConfigBtn(ActionEvent actionEvent) throws IOException {
        sceneSwapper.sceneSwitch(new Stage(), "MovieConfig.fxml");
    }
}
