package testNG;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {
	@DataProvider
	public Object[][] data()
	{
		Object[][] obj = new Object[3][4];
		
		obj[0][0] = "Test Yantra";
		obj[0][1] = "Banashankari";
		obj[0][2] = "Bangalore";
		obj[0][3] = "Karnataka";
		
		obj[1][0] = "Qspiders";
		obj[1][1] = "BTM";
		obj[1][2] = "Hyderabad";
		obj[1][3] = "Telengana";
		
		obj[2][0] = "Jspider";
		obj[2][1] = "kphb";
		obj[2][2] = "Hyd";
		obj[2][3] = "Tel";
				
		
		return obj;
	}
	@DataProvider
	public Object[][] data2()
	{
		Object[][] obj = new Object[2][3];
		
		obj[0][0] = "Hyderabad";
		obj[0][1] = "bus";
		obj[0][2] = 1000;
		
		obj[1][0] = "bangalore";
		obj[1][1] = "bus";
		obj[1][2] = 2000;
		
		return obj;
	}
}
