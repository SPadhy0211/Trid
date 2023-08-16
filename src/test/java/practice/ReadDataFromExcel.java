package practice;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\SDET50.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");

		for (int i = 0; i <= sh.getLastRowNum(); i++) {
			Row row = sh.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				System.out.print(cell.getStringCellValue()+" ");
			}
			System.out.println();
		}
		wb.close();

	}
}