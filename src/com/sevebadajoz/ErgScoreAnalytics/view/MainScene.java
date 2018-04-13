package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.View;

public class MainScene extends Application {

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
