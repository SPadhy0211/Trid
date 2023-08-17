package practice2;

import org.testng.annotations.Test;

public class SampleMaven {
	@Test(groups = "Regression")
	public void customer()
	{
		System.out.println("---customer added----");
	}
	
	@Test(groups = {"Smoke","Regression"})
	public void editCustomer()
	{
		System.out.println("---edited customer---");
	}

}
