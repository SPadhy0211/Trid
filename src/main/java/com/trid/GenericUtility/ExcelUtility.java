package com.trid.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExcelUtility {
	/**
	 * This method is to read and fetch data from excel
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws Throwable
	 */
	public String readDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		FileInputStream fs = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fs);
		DataFormatter df = new DataFormatter();
		String value = df.formatCellValue(wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum));
		return value;
	}

	/**
	 * This method is to create data in excel
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param celNum
	 * @param value
	 * @throws Throwable
	 */
	public void createDataIntoExcel(String sheetName, int rowNum, int celNum, String value) throws Throwable {
		FileInputStream fs = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fs);
		wb.createSheet(sheetName).createRow(rowNum).createCell(celNum).setCellValue(value);

		FileOutputStream fos = new FileOutputStream(IPathConstants.ExcelPath);
		wb.write(fos);
		wb.close();
	}

	/**
	 * This method is to get the last row number
	 * 
	 * @param sheetName
	 * @return
	 * @throws Throwable
	 */
	public int getLastRowNum(String sheetName) throws Throwable {
		FileInputStream fs = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fs);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		return lastRow;
	}

	/**
	 * This method is to fetch multiple data from excel
	 * 
	 * @param sheetName
	 * @param keyColumn
	 * @param valueColumn
	 * @return
	 * @throws Throwable
	 */
	public Map<String, String> getMultipleDataFromExcel(String sheetName, int keyColumn, int valueColumn,WebDriver driver) throws Throwable {
		FileInputStream fs = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fs);
		Sheet sh = wb.getSheet(sheetName);
		int count = sh.getLastRowNum();

		Map<String, String> map = new LinkedHashMap<>();

		for (int i = 1; i <= count; i++) {
			String key = dataFormatCellToString(sh.getRow(i).getCell(keyColumn));
			String value = dataFormatCellToString(sh.getRow(i).getCell(valueColumn));
			map.put(key, value);
		}
		return map;
	}
	
	public Object[][] dataProviderMultipleData(String sheetName) throws Throwable
	{
		FileInputStream fs = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fs);
		Sheet sh = wb.getSheet(sheetName);
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(0).getLastCellNum();
		DataFormatter df = new DataFormatter();
		
		Object[][] obj = new Object[lastRow+1][lastCell];
		for(int i=0; i<=lastRow; i++)
		{
			for(int j=0; j<lastCell; j++)
			{
			obj[i][j] = df.formatCellValue(sh.getRow(i).getCell(j));
			}
		}
		return obj;
	}

	public int lastcell(String sheetName, int rowNum) throws Throwable
	{
		FileInputStream fs = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fs);
		Sheet sh = wb.getSheet(sheetName);
		int cellNum = sh.getRow(rowNum).getLastCellNum();
		return cellNum;
	}

	public String dataFormatCellToString(Cell cell) {
		DataFormatter ds = new DataFormatter();
		return ds.formatCellValue(cell);
	}
}
