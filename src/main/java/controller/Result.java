package controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.DownloadPage;
import model.H2DB;


public class Result {
    ObservableList<URL> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button downloadButton;

    @FXML
    private Button addToDB;

    @FXML
    private ListView<String> ResultList;

    @FXML
    void initialize(){
        //вывод на экран
        addToResult();
        downloadButton.setOnAction(event -> {
            DownloadPage downloadPage = new DownloadPage();
            int iter = 0;
            for (URL link: Controller.result){
                iter++;
                downloadPage.DownloadWebPage(link,iter);
            }
        });
        addToDB.setOnAction(event -> {

            H2DB h2DB = new H2DB();
            h2DB.createDb();
            for (URL link :Controller.result) {
                h2DB.insertInDB(String.valueOf(link));
            }
        });
    }




    private void addToResult(){
        list.addAll(Controller.result);
        for (URL link :list){
            ResultList.getItems().add(String.valueOf(link));
        }
    }
}
