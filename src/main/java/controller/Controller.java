package controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Crawler;

public class Controller {
    public static LinkedHashSet<URL> result;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textUrl;

    @FXML
    private TextField numberOfUrl;

    @FXML
    private Button findButton;

    @FXML
    void initialize() {
        findButton.setOnAction(event -> {
            String text = textUrl.getText();
            int numOfUrl = Integer.parseInt(numberOfUrl.getText());
            if (text.equals("")) {
                Alert alertEmpty = new Alert(AlertType.ERROR);
                alertEmpty.setTitle("error");
                alertEmpty.setContentText("Enter URl and the number of links");
            } else {
                if (text.toLowerCase().startsWith("http") || (text.toLowerCase().startsWith("https"))) {
                    // Проверить формат что строка URl.
                    try {
                        Crawler crawler = new Crawler(text,numOfUrl);

                        result = crawler.crawl();

                        int tempDepth = 0;

                        Parent root = FXMLLoader.load(getClass().getResource("../view/ResultFxml.fxml"));
                        assert root != null;
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        //создаем экзепляры всех используемых классов, а также окно, которое будет вызвано позже

                        stage.showAndWait();

                        //в отдельном потоке создаем тааблицу с результатом.

                        //H2DB dataBase = new H2DB(tempUrl);
                        //dataBase.createDBTable();
                        //for (URL link : result) {
                            //tempDepth++;

                            //dataBase.insertInDB(link.toString(), tempDepth);
                        //}
                        //в другом потоке подрубаемся к БД и добавляем все в нее.

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    Alert alertEmpty = new Alert(AlertType.ERROR);
                    alertEmpty.setTitle("error");
                    alertEmpty.setContentText("URl incorrect");
                    alertEmpty.showAndWait();
                }
            }
        });
    }
}

