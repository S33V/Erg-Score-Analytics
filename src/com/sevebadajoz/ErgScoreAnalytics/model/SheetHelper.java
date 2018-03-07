package com.sevebadajoz.ErgScoreAnalytics.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetHelper {
    FileInputStream file;

    XSSFWorkbook workbook;
    XSSFSheet sheet;



    public SheetHelper(FileInputStream file) throws IOException {
        this.file = file;
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheetAt(0);
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
        rowIterator.next();
        String[] ret = new String[sheet.getPhysicalNumberOfRows() - 1];
        int i = 0;
        while (rowIterator.hasNext()) {
            ret[i] = rowIterator.next().getCell(i).getStringCellValue();
        }
        return ret;
    }
}
