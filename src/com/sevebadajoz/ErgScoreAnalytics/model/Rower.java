package com.sevebadajoz.ErgScoreAnalytics.model;

public class Rower {
    private int id;
    private String name;
    private Split split;
    private Split weightAdjSplit;
    private double weight;

    public Rower(String name, int id, String split, double weight) {
        this.name = name;
        this.split = new Split(split);
        this.weight = weight;
        weightAdjSplit = this.split.weightAdj(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Split getSplit() {
        return split;
    }

    public void setSplit(Split split) {
        this.split = split;
    }

    public Split getWeightAdjSplit() {
        return weightAdjSplit;
    }

    public void setWeightAdjSplit(Split weightAdjSplit) {
        this.weightAdjSplit = weightAdjSplit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rower rower = (Rower) o;

        return name != null ? name.equals(rower.name) : rower.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
