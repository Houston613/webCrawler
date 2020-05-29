package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application{

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFxml.fxml"));
        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.show();
    }
}
