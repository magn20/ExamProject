package gui;

import gui.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ExamProject extends Application {
    private static FXMLLoader fxmlLoadermain;
    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoadermain = new FXMLLoader(getClass().getResource("./view/MainView.fxml"));
        primaryStage.centerOnScreen();
        Scene scene = new Scene(fxmlLoadermain.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("gui.ExamProject");
        Image image = new Image("gui/Images/icon.png");
        primaryStage.getIcons().add(image);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public MainController getController() {
        return fxmlLoadermain.getController();
    }
}