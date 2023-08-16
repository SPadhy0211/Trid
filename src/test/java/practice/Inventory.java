package practice;

import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;

public class Inventory extends BaseClass {
	@Test(groups = "Smoke")
	public void addInventory()
	{
		System.out.println("inventory added");
	}
	
	@Test(groups = "Regression")
	public void editInventory()
	{
		System.out.println("inventory edited");
	}

}
