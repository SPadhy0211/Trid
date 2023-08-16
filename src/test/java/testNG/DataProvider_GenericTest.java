package testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.trid.GenericUtility.ExcelUtility;

public class DataProvider_GenericTest {
	@DataProvider
	public Object[][] getDataFromDP() throws Throwable
	{
		ExcelUtility eUTIL = new ExcelUtility();
		Object[][] value = eUTIL.dataProviderMultipleData("DataProvider");
		return value;
	}
	
	@Test(dataProvider = "getDataFromDP")
	public void fetchData(String book, String price, String author)
	{
		System.out.println(book+"  "+price+"  "+author);
	}

}
