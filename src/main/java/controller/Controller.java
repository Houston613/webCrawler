package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Crawler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller {
    private static final Logger logger = LogManager.getLogger();
    static LinkedHashSet<URL> result;


    @FXML
    private TextField textUrl;

    @FXML
    private TextField numberOfUrl;

    @FXML
    private Button findButton;

    @FXML
    private CheckBox checkBox;

    @FXML
    void initialize() {
        findButton.setOnAction(event -> {

            String text;

            if (!checkBox.isSelected())
                //если запрос не ссылка, а просто текст, то мы вбиваем его в яндекс и добавляем то что нашли
                text = (new StringBuilder().append("https://yandex.ru/search/?text=").append(textUrl.getText()).toString());
            else
                //иначе мы просто считываем текст
                text = textUrl.getText();

            //количество URL, которе мы считаем
            int numOfUrl = Integer.parseInt(numberOfUrl.getText());

            //проверка на правильность введенного кол-ва URl
            if (numOfUrl<=0) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("error");
                alert.setContentText("you entered the wrong number of url");
                alert.showAndWait();
            }
            else {
                //проверка на пустую строку
                if (text.equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("error");
                    alert.setContentText("Enter URl and the number of links");
                } else {
                    // Проверка на формат URl
                    if (text.toLowerCase().startsWith("http") || (text.toLowerCase().startsWith("https"))) {

                        try {
                            //запускаем парсер
                            Crawler crawler = new Crawler(text, numOfUrl);
                            result = crawler.crawl();
                            logger.info("crawler - success");

                            //Вызов окна с результатами
                            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().
                                    getResource("ResultFxml.fxml")));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.showAndWait();

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
            }
        });
    }
}

