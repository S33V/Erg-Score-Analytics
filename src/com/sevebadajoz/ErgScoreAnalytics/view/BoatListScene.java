package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
import com.sevebadajoz.ErgScoreAnalytics.model.Lineup;
import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
import com.sevebadajoz.ErgScoreAnalytics.model.Lineup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class BoatListScene implements Initializable {

	private static final Controller mController = Controller.getInstance();
	@FXML
	private Button exitButton;
	@FXML
	private ListView<Lineup> boatList;
    @FXML
    private Button deleteButton;
	@FXML
	private Button editButton;
	@FXML
	private Button addButton;


	@FXML
	public Object exitCheck() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
		alert.showAndWait()
				.filter(response -> response == ButtonType.OK)
				.ifPresent(response -> System.exit(0));
		return this;
	}

	@FXML
	public Object loadAddBoat() {
		ViewSwitch.loadScene("Add a Lineup", ViewSwitch.ADD_LINEUP_SCENE);
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boatList.setItems(mController.getLineups());
	}

	@FXML
	Object enableButton() {
		editButton.setDisable(false);
		return this;
	}

    @FXML
    public Object loadInfo() {
        Lineup lineup = boatList.getSelectionModel().getSelectedItem();
//		mController.setActiveLineup(lineup);
//		ViewSwitch.loadScene("Practices for " + lineup, ViewSwitch.PRACTICE_LIST_SCENE);
        return this;
    }
}
