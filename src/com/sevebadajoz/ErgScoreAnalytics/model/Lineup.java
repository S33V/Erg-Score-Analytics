package com.sevebadajoz.ErgScoreAnalytics.model;

public class Lineup {
    private int ID;

    private Rower[] rowers;
    private Split[] splits;

    private Split avgSplit;
    private Split avgAdjSplit;

    public Lineup(int id, Rower stroke, Rower seven, Rower six, Rower five, Rower four, Rower three, Rower two, Rower bow) {
        rowers = new Rower[]{stroke, seven, six, five, four, three, two, bow};
        splits = new Split[8];
        double seconds = 0;
        for (int i = 0; i < rowers.length; i++) {
            splits[i] = rowers[i].getSplit();
            seconds += splits[i].textToSeconds();
        }
        avgSplit = new Split(Split.secondsToString(seconds));
    }

    public int getID() {
        return ID;
    }
}
