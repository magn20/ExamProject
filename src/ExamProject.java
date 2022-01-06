import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamProject extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./gui/view/MainView.fxml"));
        primaryStage.centerOnScreen();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ExamProject");
        Image image = new Image("gui/Images/icon.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}