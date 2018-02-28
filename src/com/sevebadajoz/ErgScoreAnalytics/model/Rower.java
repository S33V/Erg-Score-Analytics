package com.sevebadajoz.ErgScoreAnalytics.model;

public class Rower {
    private String name;
    private Split split;

    public Rower(String name, String split) {
        this.name = name;
        this.split = new Split(split);
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
