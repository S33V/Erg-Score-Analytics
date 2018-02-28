package com.sevebadajoz.ErgScoreAnalytics.model;

public class Split {
    private String textForm;
    private double seconds;

    public Split(String textForm) {
        this.textForm = textForm;
        createSeconds();
    }

    public double textToSeconds(String textForm) {
        double min = Double.parseDouble(textForm.substring(0, 1));
        double sec = Double.parseDouble(textForm.substring(3));
        return 60 * min + sec;
    }

    public double textToSeconds(){
        return textToSeconds(textForm);
    }


    private void createSeconds(){
        seconds = textToSeconds();
    }

    public String toString() {
        return textForm;
    }
}
