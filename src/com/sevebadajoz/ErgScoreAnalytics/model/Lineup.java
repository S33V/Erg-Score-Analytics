package com.sevebadajoz.ErgScoreAnalytics.model;

import java.util.Arrays;

public class Lineup {
    private int ID;

    private Rower[] rowers;
    private Split[] splits;

    private Split avgSplit;
    private Split avgAdjSplit;

    public Lineup(int id, Rower stroke, Rower seven, Rower six, Rower five, Rower four, Rower three, Rower two, Rower bow) {
        ID = id;
        rowers = new Rower[]{stroke, seven, six, five, four, three, two, bow};
        splits = new Split[8];
        double seconds = 0, adjSeconds = 0;
        for (int i = 0; i < rowers.length; i++) {
            splits[i] = rowers[i].getSplit();
            seconds += splits[i].textToSeconds();
            adjSeconds += rowers[i].getWeightAdjSplit().textToSeconds();
        }
        avgSplit = new Split(Split.secondsToString(seconds / 8));
        avgAdjSplit = new Split(Split.secondsToString(adjSeconds / 8));
    }

    public int getID() {
        return ID;
    }

    public Rower[] getRowers() {
        return rowers;
    }

    public Split[] getSplits() {
        return splits;
    }

    public Split getAvgSplit() {
        return avgSplit;
    }

    public Split getAvgAdjSplit() {
        return avgAdjSplit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lineup lineup = (Lineup) o;

        return ID == lineup.ID;
    }

    @Override
    public int hashCode() {
        return ID;
    }

    @Override
    public String toString() {
        String toString = Arrays.toString(rowers);
        return toString.substring(1, toString.length() - 1);
    }
}
