package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainScene extends Application {

    @FXML
    private Button browseButton;
    @FXML
    private TextField filePath;
    @FXML

    private File selectedFile;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewSwitch.setStage(primaryStage);

        browseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Spreadsheet");
                selectedFile = fileChooser.showOpenDialog(primaryStage);
                filePath.setText(selectedFile.getPath());
            }
        });


    }



    public static void main(String[] args) {
        launch(args);
    }
}
