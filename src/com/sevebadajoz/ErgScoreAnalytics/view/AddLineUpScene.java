package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;

import com.sevebadajoz.ErgScoreAnalytics.model.Lineup;
import com.sevebadajoz.ErgScoreAnalytics.model.Rower;
import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class AddLineUpScene implements Initializable {
    static final Controller mController = Controller.getInstance();
    @FXML
    Button mBack;
    @FXML
    Button mAddLineup;


    @FXML
    ComboBox<Rower> mStroke;
    @FXML
    ComboBox<Rower> mSevenSeat;
    @FXML
    ComboBox<Rower> mSixSeat;
    @FXML
    ComboBox<Rower> mFiveSeat;
    @FXML
    ComboBox<Rower> mFourSeat;
    @FXML
    ComboBox<Rower> mThreeSeat;
    @FXML
    ComboBox<Rower> mTwoSeat;
    @FXML
    ComboBox<Rower> mBowSeat;

    Lineup activeLineup;

    @FXML
    Object addLineup() {
        Rower stroke = mStroke.getSelectionModel().getSelectedItem();
        Rower seven = mSevenSeat.getSelectionModel().getSelectedItem();
        Rower six = mSixSeat.getSelectionModel().getSelectedItem();
        Rower five = mFiveSeat.getSelectionModel().getSelectedItem();
        Rower four = mFourSeat.getSelectionModel().getSelectedItem();
        Rower three = mThreeSeat.getSelectionModel().getSelectedItem();
        Rower two = mTwoSeat.getSelectionModel().getSelectedItem();
        Rower bow = mBowSeat.getSelectionModel().getSelectedItem();
        Rower[] rowers = {stroke, seven, six, five, four, three, two, bow};
        if(mController.isEditMode()) {
            activeLineup.setRowers(rowers);
            if(mController.editLineup(activeLineup))
                System.out.println("EDIT SUCCESSFUL: " + activeLineup);
            else
                System.out.println("EDIT FAILED: " + activeLineup);
        }
        else
            mController.addNewLineup(rowers);

        ViewSwitch.loadScene("Select Lineup", ViewSwitch.BOAT_LIST_SCENE);
        return this;
    }

    @FXML
    Object exit() {
        ViewSwitch.loadScene("Select Lineup", ViewSwitch.BOAT_LIST_SCENE);
        return this;
    }



    @FXML
    Object checkCompletion() {
        if (mStroke.getSelectionModel().isEmpty() || mSevenSeat.getSelectionModel().isEmpty() ||
                mSixSeat.getSelectionModel().isEmpty() || mFiveSeat.getSelectionModel().isEmpty() ||
                mFourSeat.getSelectionModel().isEmpty() || mThreeSeat.getSelectionModel().isEmpty()
                || mTwoSeat.getSelectionModel().isEmpty() || mBowSeat.getSelectionModel().isEmpty()) {
            mAddLineup.setDisable(true);
        } else mAddLineup.setDisable(false);
        return this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Rower> allRowers = FXCollections.observableArrayList(mController.getRowers());
        allRowers.sort(Comparator.comparing(Rower::getName));
        mStroke.getItems().setAll(allRowers);
        mSevenSeat.getItems().setAll(allRowers);
        mSixSeat.getItems().setAll(allRowers);
        mFiveSeat.getItems().setAll(allRowers);
        mFourSeat.getItems().setAll(allRowers);
        mThreeSeat.getItems().setAll(allRowers);
        mTwoSeat.getItems().setAll(allRowers);
        mBowSeat.getItems().setAll(allRowers);
        if(mController.isEditMode()) {
            activeLineup = mController.getActiveLineup();

            mAddLineup.setText("EDIT");

            mStroke.setValue(activeLineup.getRowers()[0]);
            mSevenSeat.setValue(activeLineup.getRowers()[1]);
            mSixSeat.setValue(activeLineup.getRowers()[2]);
            mFiveSeat.setValue(activeLineup.getRowers()[3]);
            mFourSeat.setValue(activeLineup.getRowers()[4]);
            mThreeSeat.setValue(activeLineup.getRowers()[5]);
            mTwoSeat.setValue(activeLineup.getRowers()[6]);
            mBowSeat.setValue(activeLineup.getRowers()[7]);
        }
    }
}
