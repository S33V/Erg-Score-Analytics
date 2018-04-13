package com.sevebadajoz.ErgScoreAnalytics.controller;

import com.sevebadajoz.ErgScoreAnalytics.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.util.SystemOutLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    public static final String DB_NAME = "erg_score_analytics.db";

    public static DBModel lineupsTable;
    public static final String LINEUPS_TABLE_NAME = "lineups";
    public static final String[] LINEUPS_FIELD_NAMES = {"id", "stroke", "seven", "six", "five", "four", "three", "two", "bow"};
    public static final String[] LINEUPS_FIELD_TYPES = {"INTEGER PRIMARY KEY", "INTEGER", "INTEGER", "INTEGER",
            "INTEGER", "INTEGER", "INTEGER", "INTEGER", "INTEGER"};

    public static DBModel rowersTable;
    public static final String ROWERS_TABLE_NAME = "rowers";
    public static final String[] ROWERS_FIELD_NAMES = {"id", "name", "split", "weight"};
    public static final String[] ROWERS_FIELD_TYPES = {"INTEGER PRIMARY KEY", "TEXT", "TEXT", "REAL"};

    public static SheetHelper sheetHelper;

    private ObservableList<Rower> rowers;
    private ObservableList<Lineup> lineups;

    private Lineup activeLineup;

    private static Controller theOne;

    private Controller() {
    }

    public static Controller getInstance() {
        if (theOne == null) {
            theOne = new Controller();

            theOne.rowers = FXCollections.observableArrayList();
            theOne.lineups = FXCollections.observableArrayList();

            try {
                lineupsTable = new DBModel(DB_NAME, LINEUPS_TABLE_NAME, LINEUPS_FIELD_NAMES, LINEUPS_FIELD_TYPES);
                rowersTable = new DBModel(DB_NAME, ROWERS_TABLE_NAME, ROWERS_FIELD_NAMES, ROWERS_FIELD_TYPES);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return theOne;
    }

    public static boolean openSheet(FileInputStream fileName) {
        try {
            sheetHelper = new SheetHelper(fileName);
            return theOne.addRowersFromSheet();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

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

    public boolean addRowersFromSheet() {
        try {
            rowersTable.deleteAllRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int nameCol = 0, weightCol = 0, splitCol = 0, bestSplitCol = 0;
        String name, split;
        double weight;
        String[] colHeaders = sheetHelper.getColHeaders();
        for (int i = 0; i < colHeaders.length; i++) {
            String colHeader = colHeaders[i].trim().toUpperCase();
            switch (colHeader) {
                case ("NAME"):
                    nameCol = i;
                    break;
                case ("WEIGHT"):
                    weightCol = i;
                    break;
                case ("AVG. SPLIT"):
                    splitCol = i;
                    break;
                default:
                    if(colHeader.contains("BEST")
                            && !colHeader.contains("WA")
                            && ViewSwitch.prompt("Would you like to use this year's best scores?")) {
                        System.out.println("USING BEST SCORES:");
                        bestSplitCol = i;
                    }
            }

        }

//        Return false if any of the required col headers were not found
        if (nameCol == 0 || weightCol == 0 || splitCol == 0) return false;

        String[][] rows = new String[sheetHelper.getSheet().getLastRowNum()][colHeaders.length];

        for (int i = 0; i < rows.length; i++)
            rows[i] = sheetHelper.getColValues(i);

        for (int i = 0; i < rows.length; i++) {
//            Check to see if the first cell in the row is a number
//            This should be true if the row is a rower, because first cell should be rank
            if (rows[i] == null || rows[i][0] == null) rows[i] = new String[]{""};
            Scanner digitChecker = new Scanner(rows[i][0].trim());
            if (!digitChecker.hasNextInt())
                continue;
            digitChecker.nextInt();
            if (!digitChecker.hasNext()) {
                name = rows[i][nameCol];
                if(bestSplitCol != 0) {
                    String str = rows[i][bestSplitCol];
                    double seconds = Double.parseDouble(str.substring(0, str.indexOf(":"))) * 60
                            + Double.parseDouble(str.substring(str.indexOf(":") + 1));
                    seconds /= 4;
                    split = Split.secondsToString(seconds);
                }
                else
                    split = rows[i][splitCol];
                weight = Double.valueOf(rows[i][weightCol]);
                if (!addNewRower(name, split, weight)) {
                    System.out.println("NOT ADDED: " + name);
                    return false;
                } else

                    System.out.println("ADDED: " + name + ", " + split);
            }
        }
//        Update the ObservableList
        getRowers();
        return true;
    }

    public boolean addNewRower(String name, String split, double weight) {
        String[] values = new String[ROWERS_FIELD_NAMES.length - 1];
        values[0] = name;
        values[1] = split;
        values[2] = Double.toString(weight);

        try {
            rowersTable.createRecord(Arrays.copyOfRange(ROWERS_FIELD_NAMES, 1, ROWERS_FIELD_NAMES.length), values);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ObservableList<Rower> getRowers() {
        try {
            ArrayList<ArrayList<String>> rs = rowersTable.getAllRecords();

            for (ArrayList<String> values : rs) {
                boolean notFound = true;
                int ID = Integer.parseInt(values.get(0));
                for (Rower rower : rowers) {
                    if (rower.getID() == ID) notFound = false;
                }
                String name = values.get(1);
                String split = values.get(2);
                double weight = Double.parseDouble(values.get(3));


                if (notFound) theOne.rowers.add(new Rower(ID, name, split, weight));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return theOne.rowers;
    }

    public boolean addNewLineup(Rower[] rowers) {
        String[] IDs = new String[rowers.length];
        for (int i = 0; i < IDs.length; i++) {
            IDs[i] = Integer.toString(rowers[i].getID());
        }
        try {
            lineupsTable.createRecord(Arrays.copyOfRange(LINEUPS_FIELD_NAMES, 1, LINEUPS_FIELD_NAMES.length), IDs);
            System.out.println("ADDED LINEUP: " + rowers);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ObservableList<Lineup> getLineups() {
        try {
            ArrayList<ArrayList<String>> rs = lineupsTable.getAllRecords();
//            Iterate through the rows in lineupsTable
            for (ArrayList<String> values : rs) {
                Rower[] addRowers = new Rower[8];
                int count = 0;
//                Iterate through the table row, skipping the first value(lineup ID)
                for (Object value : values.stream().skip(1).toArray()) {
                    String casted = (String) value;
//                    Search through rowers for value(rower ID), if found Rower to array
                    for (Rower rower : theOne.rowers) {
//                            System.out.println("Value ID: " + casted + ", Rower ID: " + Integer.toString(rower.getID()));
                        if (casted.equals(Integer.toString(rower.getID()))) {
//                                System.out.println("MATCH, count: " + count);
                            addRowers[count++] = rower;
                        }
                    }
                }
//                If the array is not null create a new Lineup from array
                if (addRowers[0] != null) {
                    int lineupID = Integer.parseInt(values.get(0));
                    Lineup newLineup = new Lineup(lineupID, addRowers[0], addRowers[1],
                            addRowers[2], addRowers[3], addRowers[4], addRowers[5], addRowers[6], addRowers[7]);
//                    If the new Lineup doesn't already exist in the ObservableList, add it
                    if (!theOne.lineups.contains(newLineup))
                        theOne.lineups.add(newLineup);
                    else {
                        System.out.println(newLineup + " already exists");
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return theOne.lineups;
    }

    public Lineup getActiveLineup() {
        return activeLineup;
    }

    public void setActiveLineup(Lineup activeLineup) {
        this.activeLineup = activeLineup;
    }
}
