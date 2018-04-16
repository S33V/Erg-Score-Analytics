package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ImportWorkbookScene implements Initializable{

    @FXML
    private Button browseButton;
    @FXML
    private TextField filePath;
    @FXML
    private Button submitButton;

    private File selectedFile;

    private Controller controller = Controller.getInstance();

    @FXML
    public Object exitCheck() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
        alert.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> System.exit(0));
        return this;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        browseButton.setOnAction(actionEvent ->  {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setInitialDirectory(new File(System.getProperty("user.home"), "Downloads"));
                fileChooser.setTitle("Open Spreadsheet");

                selectedFile = fileChooser.showOpenDialog(((Node)actionEvent.getSource()).getScene().getWindow());
                if(selectedFile != null)
                    filePath.setText(selectedFile.getPath());
        });

        submitButton.setOnAction(event -> {
            try {
                if(controller.openSheet(new FileInputStream(filePath.getText()))) {
                    if(controller.getWorkbookHelper().getSheetNames().length > 1) {

                    }
                    ViewSwitch.loadScene("Select Lineup", ViewSwitch.BOAT_LIST_SCENE);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
