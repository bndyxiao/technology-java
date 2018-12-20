/**   
 * Copyright (c) 2012-2022 厦门市美亚柏科信息股份有限公司.
 */
package com.technology.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @Description: 一个简单的excel导出类
 */
public class ExcelSimpleExport {

    /** 每一个sheet表格默认可以导出多少行*/
    public int maxSheetRows = 65500;
    
    private int rowNum = 0;

    private FileOutputStream fos = null;

    private HSSFWorkbook work = null;

    private HSSFSheet sheet = null;

    private String[] header = null;
    
    private String sheetName = null;
    
    private int sheets = 0;
    
    private File file ;

    public ExcelSimpleExport(File file,String sheetName,String[] header) throws FileNotFoundException {
        this.header = header;
        this.sheetName = sheetName;
        this.file = file;
        this.fos = new FileOutputStream(file);
        this.work = new HSSFWorkbook();
        this.sheet = work.createSheet(sheetName+sheets);
    }

    public void write(String[] line){
        if(rowNum == 0){
            writeRow(header);
        }
        writeRow(line);
        if(rowNum > maxSheetRows){
            rowNum = 0;
            sheets ++;
            sheet = work.createSheet(sheetName + sheets);
        }
    }
    
    private void writeRow(String[] line) {
        HSSFRow row = sheet.createRow(rowNum);
        for (int i = 0; i < line.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(line[i]);
        }
        rowNum++;
    }

    public void flush() throws IOException {
        work.write(fos);
        fos.close();
    }
    
    public String getFilePath(){
        return file.getPath();
    }
}
