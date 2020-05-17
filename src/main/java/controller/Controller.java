package controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Crawler;

public class Controller {

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
    private ListView<?> tableWithResults;

    @FXML
    void initialize() throws MalformedURLException {
        String tempUrl  = textUrl.getText();
        if (tempUrl.equals(""))
            throw new IllegalArgumentException("Введите URl");
        //если не ввели строку, то не запускаем программу
        if (tempUrl.toLowerCase().startsWith("http") || (tempUrl.toLowerCase().startsWith("https"))) {
            // Проверить формат что строка URl.
            URL url = new URL(tempUrl);
            int depth = Integer.parseInt(numberOfUrl.getText());
            findButton.setOnAction(event -> {
                Crawler crawler = new Crawler(url, depth);
            });
        }else throw new IllegalArgumentException("Формат URL неверный");
    }
}

