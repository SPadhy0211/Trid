package practice2;

import org.testng.annotations.Test;

public class SampleMaven2 {
	@Test(groups = "Smoke")
	public void product()
	{
		System.out.println("---product added---");
	}
	
	@Test(groups = "Regression")
	public void modifyProduct()
	{
		System.out.println("---product modified----");
	}

}
