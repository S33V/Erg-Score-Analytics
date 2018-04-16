package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainScene extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.initStyle(StageStyle.UNDECORATED);
        ViewSwitch.setStage(primaryStage);
        ViewSwitch.loadScene("Import Workbook", ViewSwitch.IMPORT_WORKBOOK_SCENE);
    }



    public static void main(String[] args) {
        launch(args);
    }
}
