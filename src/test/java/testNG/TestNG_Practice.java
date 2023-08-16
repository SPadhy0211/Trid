package testNG;

import org.testng.annotations.Test;

public class TestNG_Practice {
	@Test
	public void productPage()
	{
		System.out.println("product created");
	}
	
	@Test(dependsOnMethods = "productPage")
	public void inventoryList()
	{
		System.out.println("inventory updated");
	}
	
	@Test
	public void customerPage()
	{
		System.out.println("customer created");
	}
	
	@Test(dataProviderClass = DataProviderTest.class, dataProvider = "data")
	public void getData(String name, String loc, String area, String state)
	{
		System.out.println(name+"--->"+loc+"--->"+area+"--->"+state);
	}
	@Test(dataProviderClass = DataProviderTest.class, dataProvider = "data2")
    public void getData(String loc, String transport, int price)
    {
		System.out.println(loc+"--->"+transport+"--->"+price);
    }

}
