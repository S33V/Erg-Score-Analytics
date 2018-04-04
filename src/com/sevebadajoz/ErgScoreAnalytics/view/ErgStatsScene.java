package com.sevebadajoz.ErgScoreAnalytics.view;

import com.sevebadajoz.ErgScoreAnalytics.controller.Controller;
import com.sevebadajoz.ErgScoreAnalytics.model.Lineup;
import com.sevebadajoz.ErgScoreAnalytics.model.Rower;
import com.sevebadajoz.ErgScoreAnalytics.model.Split;
import com.sevebadajoz.ErgScoreAnalytics.model.ViewSwitch;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ErgStatsScene implements Initializable{
    @FXML
    Text title;

    @FXML
    Text rank1;
    @FXML
    Text rank2;
    @FXML
    Text rank3;
    @FXML
    Text rank4;
    @FXML
    Text rank5;
    @FXML
    Text rank6;
    @FXML
    Text rank7;
    @FXML
    Text rank8;

    @FXML
    Text average;
    @FXML
    Text range;

    @FXML
    Button back;
    @FXML
    Button change;


    Controller controller = Controller.getInstance();
    Lineup lineup = controller.getActiveLineup();
    Text[] ranks;
    boolean raw;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       ranks = new Text[]{rank1, rank2, rank3, rank4, rank5, rank6, rank7, rank8};
        raw = false;
        generateStats();
        back.setOnAction(actionEvent -> {
            ViewSwitch.loadScene("Select Lineup", ViewSwitch.BOAT_LIST_SCENE);
        });

        change.setOnAction(actionEvent ->{
            generateStats();
        });
    }

    private void generateStats() {
        Rower[] rowers;
        Split rangeSplit;
        if(!raw) {
            rowers = Stream.of(lineup.getRowers()).sorted(Comparator.comparing(rower -> rower.getSplit().toString())).toArray(Rower[]::new);
            rangeSplit = new Split(Split.secondsToString(rowers[7].getSplit().textToSeconds() - rowers[0].getSplit().textToSeconds()));
            title.setText("Raw Ranking");
            change.setText("Weight Adjusted");
        }
        else {
            rowers = Stream.of(lineup.getRowers()).sorted(Comparator.comparing(rower -> rower.getWeightAdjSplit().toString())).toArray(Rower[]::new);
            rangeSplit = new Split(Split.secondsToString(rowers[7].getWeightAdjSplit().textToSeconds() - rowers[0].getWeightAdjSplit().textToSeconds()));
            title.setText("Weight Adjusted Ranking");
            change.setText("Raw");
        }

        for (int i = 0; i < rowers.length; i++) {
            Rower rower = rowers[i];
            if(!raw)
                ranks[i].setText(rower.getName() + "   -   " + rower.getSplit());
            else
                ranks[i].setText(rower.getName() + "   -   " + rower.getWeightAdjSplit());
        }

        average.setText(lineup.getAvgSplit().toString());
        range.setText(rangeSplit.toString());

        raw = !raw;
    }
}
