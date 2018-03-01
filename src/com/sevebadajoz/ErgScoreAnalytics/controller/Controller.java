package com.sevebadajoz.ErgScoreAnalytics.controller;

import javafx.collections.FXCollections;

import java.sql.SQLException;

public class Controller {
    public static final String DB_NAME = "erg_score_analytics.db";

    public static final String LINEUPS_TABLE_NAME = "lineups";
    public static final String[] LINEUPS_FIELD_NAMES = {"id", "stroke", "7", "6", "5", "4", "3", "2", "bow"};
    public static final String[] LINEUPS_FIELD_TYPES = {"INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "INTEGER",
            "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER"};

    public static final String ROWERS_TABLE_NAME = "rowers";
    public static final String[] ROWERS_FIELD_NAMES = {"id", "name"};
    public static final String[] ROWERS_FIELD_TYPES = {"INTEGER PRIMARY KEY", "TEXT"};

    private static Controller theOne;

    private Controller() {}

    public static Controller getInstance() {
        if (theOne == null) {
            theOne = new Controller();
        }
        return theOne;
    }
}
