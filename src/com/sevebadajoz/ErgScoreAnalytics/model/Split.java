package com.sevebadajoz.ErgScoreAnalytics.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Split {
    private String textForm;
    private double seconds;

    public Split(String textForm) {
        this.textForm = textForm;
        createSeconds();
    }

    public double textToSeconds(String textForm) {
        double min = Double.parseDouble(textForm.substring(0, 2));
        double sec = Double.parseDouble(textForm.substring(3));
        return 60 * min + sec;
    }

    public double textToSeconds(){
        return textToSeconds(textForm);
    }

    private void createSeconds(){
        seconds = textToSeconds();
    }

    public static String secondsToString(double seconds) {
        int min = (int)seconds / 60;
        double sec = seconds % 60;
        return String.format("%02d", min) + ":" + new DecimalFormat("00.0").format(sec);

    }


    private Split avgSplit(Split... splits) {
        int totalSeconds = 0;
        for (Split split : splits) {
            totalSeconds += split.seconds;
        }
        return new Split(secondsToString(totalSeconds));
    }

    public String toString() {
        return textForm;
    }

    public Split weightAdj(double weight) {
        return new Split(secondsToString(Math.pow(weight / 270.0, .222) * seconds));
    }
}
