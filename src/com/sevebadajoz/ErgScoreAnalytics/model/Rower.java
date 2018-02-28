package com.sevebadajoz.ErgScoreAnalytics.model;

public class Rower {
    private String name;

    public Rower(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
