package com.sevebadajoz.ErgScoreAnalytics.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewSwitch {
    public static final String BOAT_LIST_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/boatList.fxml";
    public static final String ADD_LINEUP_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/addLineup.fxml";
    public static final String IMPORT_SHEET_SCENE    = "com/sevebadajoz/ErgScoreAnalytics/view/importSheet.fxml";
    public static final String ERG_STATS_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/ergStats.fxml";

    public static Stage activeStage;
    public static Stage window;

    public static void setStage(Stage stage) {
        activeStage = stage;
    }

    public static void loadScene(String title, String sceneFXML) {
        try {
            activeStage.setTitle(title);
            Scene scene = new Scene(FXMLLoader.load(ViewSwitch.class.getClassLoader().getResource(sceneFXML)));
            activeStage.setScene(scene);
            activeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void newWindow(String title, String sceneFXML) {
        window = new Stage();
        window.setTitle(title);
        try {
            Scene scene = new Scene(FXMLLoader.load(ViewSwitch.class.getClassLoader().getResource(sceneFXML)));
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeWindow() {
        window.close();
    }
}