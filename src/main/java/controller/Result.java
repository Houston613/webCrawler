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


public class Result {
    ObservableList<URL> list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button downloadButton;

    @FXML
    private ListView<String> ResultList;

    @FXML
    void initialize(){
        addToResult();
    }
    private void addToResult(){

        list.addAll(Controller.result);
        for (URL link :list){
            ResultList.getItems().add(String.valueOf(link));
        }
    }
}
