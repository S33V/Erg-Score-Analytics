package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.View;

public class MainScene extends Application {

    private double xOffset = 0.0;
    private double yOffset = 0.0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        ViewSwitch.setStage(primaryStage);
        ViewSwitch.loadScene("Import Sheet", ViewSwitch.IMPORT_SHEET_SCENE);
    }



    public static void main(String[] args) {
        launch(args);
    }
}