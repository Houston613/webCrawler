package view;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;

public class Main extends Application{
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Log4j2ExampleApp started.");
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainFxml.fxml")));
        primaryStage.setScene(new Scene(root,1280,720));
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.show();
    }
}
