package practice;

import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;

public class Account extends BaseClass {
	@Test(groups = "Regression")
	public void createUserAccount()
	{
		System.out.println("account created");
	}
	
	@Test(groups = {"Regression","Smoke"})
	public void editAccount()
	{
		System.out.println("account edited");
	}
	
	@Test
	public void deleteAccount()
	{
		System.out.println("account deleted");
	}
	

}
