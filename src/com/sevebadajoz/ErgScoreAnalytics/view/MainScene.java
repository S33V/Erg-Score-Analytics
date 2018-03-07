package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainScene extends Application {

    @FXML
    private Button browseButton;
    @FXML
    private TextField filePath;
    @FXML
    private Button submitButton;

    private File selectedFile;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewSwitch.setStage(primaryStage);

        browseButton.setOnAction((ActionEvent actionEvent) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Spreadsheet");
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            filePath.setText(selectedFile.getPath());
        });

        submitButton.setOnAction((ActionEvent actionEvent) -> {
            try {
                if(Controller.openSheet(new FileInputStream(selectedFile)) == true);
                //TODO: Add code to fix assumption that name col is titled "NAME"
                ViewSwitch.loadScene("Select Lineup", ViewSwitch.BOAT_LIST_SCENE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}
