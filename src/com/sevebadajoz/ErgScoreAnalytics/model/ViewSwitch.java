package com.sevebadajoz.ErgScoreAnalytics.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewSwitch {
    public static final String BOAT_LIST_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/boatList.fxml";
    public static final String ADD_LINEUP_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/addLineup.fxml";
    public static final String IMPORT_WORKBOOK_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/importWorkbook.fxml";
    public static final String ERG_STATS_SCENE = "com/sevebadajoz/ErgScoreAnalytics/view/ergStats.fxml";

    public static Stage activeStage;
    public static Stage window;

    static double xOffset = 0.0;
    static double yOffset = 0.0;

    public static void setStage(Stage stage) {
        activeStage = stage;
    }

    public static void loadScene(String title, String sceneFXML) {

        try {
            activeStage.setTitle(title);
            Scene scene = new Scene(FXMLLoader.load(ViewSwitch.class.getClassLoader().getResource(sceneFXML)));
            Parent root = scene.getRoot();


            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                activeStage.setX(event.getScreenX() - xOffset);
                activeStage.setY(event.getScreenY() - yOffset);
            });
            activeStage.setScene(scene);
            activeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean prompt(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
       if(alert.showAndWait().get() == ButtonType.YES)
           return true;
       return false;
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