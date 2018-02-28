package com.sevebadajoz.ErgScoreAnalytics.model;

public class Lineup {
    private Rower stroke;
    private Rower seven;
    private Rower six;
    private Rower five;
    private Rower four;
    private Rower three;
    private Rower two;
    private Rower bow;

    private Split avgSplit;
    private Split avgAdjSplit;

    public Lineup(Rower stroke, Rower seven, Rower six, Rower five, Rower four, Rower three, Rower two, Rower bow) {
        this.stroke = stroke;
        this.seven = seven;
        this.six = six;
        this.five = five;
        this.four = four;
        this.three = three;
        this.two = two;
        this.bow = bow;
    }





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lineup lineup = (Lineup) o;

        if (stroke != null ? !stroke.equals(lineup.stroke) : lineup.stroke != null) return false;
        if (seven != null ? !seven.equals(lineup.seven) : lineup.seven != null) return false;
        if (six != null ? !six.equals(lineup.six) : lineup.six != null) return false;
        if (five != null ? !five.equals(lineup.five) : lineup.five != null) return false;
        if (four != null ? !four.equals(lineup.four) : lineup.four != null) return false;
        if (three != null ? !three.equals(lineup.three) : lineup.three != null) return false;
        if (two != null ? !two.equals(lineup.two) : lineup.two != null) return false;
        return bow != null ? bow.equals(lineup.bow) : lineup.bow == null;
    }

    @Override
    public int hashCode() {
        int result = stroke != null ? stroke.hashCode() : 0;
        result = 31 * result + (seven != null ? seven.hashCode() : 0);
        result = 31 * result + (six != null ? six.hashCode() : 0);
        result = 31 * result + (five != null ? five.hashCode() : 0);
        result = 31 * result + (four != null ? four.hashCode() : 0);
        result = 31 * result + (three != null ? three.hashCode() : 0);
        result = 31 * result + (two != null ? two.hashCode() : 0);
        result = 31 * result + (bow != null ? bow.hashCode() : 0);
        return result;
    }

}
