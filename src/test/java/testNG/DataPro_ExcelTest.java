package testNG;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.trid.GenericUtility.IPathConstants;

public class DataPro_ExcelTest {
	@Test(dataProvider = "getDataFromExcel")
	public void readData(String customer,String phNo,String loc)
	{
		System.out.println(customer+"  "+phNo+"  "+loc);
	}
	
	@DataProvider
	public Object[][] getDataFromExcel() throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.ExcelPath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("DataProvider");
		int lastRow = sh.getLastRowNum();
		int lastCell = sh.getRow(1).getLastCellNum();
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

}
