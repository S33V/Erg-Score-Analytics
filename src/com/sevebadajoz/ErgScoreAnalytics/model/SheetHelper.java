package com.sevebadajoz.ErgScoreAnalytics.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetHelper {
    FileInputStream file;

    XSSFWorkbook workbook;
    XSSFSheet sheet;



    public SheetHelper(FileInputStream file) throws IOException {
        this.file = file;
        workbook = new XSSFWorkbook(file);
        //TODO: Ask user what sheet
        sheet = workbook.getSheetAt(2);
    }

    public SheetHelper(FileInputStream file, int sheetNum) throws IOException {
        this(file);
        sheet = workbook.getSheetAt(sheetNum);
    }


    public String[] getColHeaders() {
        Row headerRow = sheet.getRow(0);
        Iterator<Cell> cell = headerRow.cellIterator();
        String[] ret = new String[headerRow.getPhysicalNumberOfCells()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = cell.next().getStringCellValue();
        }
        return ret;
    }
    public String[] getRowValues(int colNum) {
        Iterator<Row> rowIterator = sheet.rowIterator();
        DataFormatter df = new DataFormatter();
        rowIterator.next();
        String[] ret = new String[sheet.getPhysicalNumberOfRows() - 1];
        int i = 0;
        while (rowIterator.hasNext()) {
            ret[i] = df.formatCellValue(rowIterator.next().getCell(i));
        }
        return ret;
    }

    public String[] getColValues(int rowNum) {
        Row row = sheet.getRow(rowNum);
        DataFormatter df = new DataFormatter();
        String[] ret = new String[row.getPhysicalNumberOfCells()];
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            Cell cell = row.getCell(i);
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            ret[i] = df.formatCellValue(cell, evaluator);
        }
        return ret;
    }

    public XSSFSheet getSheet() {
        return sheet;
    }
}
