package com.sevebadajoz.ErgScoreAnalytics.controller;

import com.sevebadajoz.ErgScoreAnalytics.model.DBModel;
import com.sevebadajoz.ErgScoreAnalytics.model.SheetHelper;
import javafx.collections.FXCollections;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

public class Controller {
    public static final String DB_NAME = "erg_score_analytics.db";

    public static DBModel lineupsTable;
    public static final String LINEUPS_TABLE_NAME = "lineups";
    public static final String[] LINEUPS_FIELD_NAMES = {"id", "stroke", "7", "6", "5", "4", "3", "2", "bow"};
    public static final String[] LINEUPS_FIELD_TYPES = {"INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "INTEGER",
            "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER"};

    public static DBModel rowersTable;
    public static final String ROWERS_TABLE_NAME = "rowers";
    public static final String[] ROWERS_FIELD_NAMES = {"id", "name"};
    public static final String[] ROWERS_FIELD_TYPES = {"INTEGER PRIMARY KEY", "TEXT"};

    public static SheetHelper sheetHelper;


    private static Controller theOne;

    private Controller() {}

    public static Controller getInstance() {
        if (theOne == null) {
            theOne = new Controller();

            try {
                theOne.lineupsTable = new DBModel(DB_NAME, LINEUPS_TABLE_NAME, LINEUPS_FIELD_NAMES, LINEUPS_FIELD_TYPES);
                theOne.rowersTable = new DBModel(DB_NAME, ROWERS_TABLE_NAME, ROWERS_FIELD_NAMES, ROWERS_FIELD_TYPES);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return theOne;
    }

    public static boolean openSheet(FileInputStream fileName) {
        return openSheet(fileName, 0);
    }

    public static boolean openSheet(FileInputStream fileName, int sheetNum) {
        try {
            sheetHelper = new SheetHelper(fileName, sheetNum);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
