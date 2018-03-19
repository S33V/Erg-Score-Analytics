package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportSheetScene implements Initializable{

    @FXML
    private Button browseButton;
    @FXML
    private TextField filePath;
    @FXML
    private Button submitButton;

    private File selectedFile;

    private Controller controller = Controller.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        browseButton.setOnAction(actionEvent ->  {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Spreadsheet");

                selectedFile = fileChooser.showOpenDialog(((Node)actionEvent.getSource()).getScene().getWindow());
        });

        submitButton.setOnAction(event -> {
            try {
                if(controller.openSheet(new FileInputStream(selectedFile)))
                ViewSwitch.loadScene("Select Lineup", ViewSwitch.BOAT_LIST_SCENE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
