package practice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AnnotationTest {
	@BeforeSuite
	public void connectDB()
	{
		System.out.println("--DB connected!!--");
	}
	
	@BeforeClass
	public void launchBrowser()
	{
		System.out.println("--browser launched!!--");
	}
	
	@BeforeMethod
	public void loginOperation()
	{
		System.out.println("--loggedin to app!!--");
	}
	
	@Test
	public void addCustomer()
	{
		System.out.println("--customer added!!--");
	}
	
	@AfterMethod
	public void logoutOp()
	{
		System.out.println("--loggedout from app--");
	}
	
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("--browser closed!!--");
	}
	
	@AfterSuite
	public void closeDB()
	{
		System.out.println("--DB closed!!--");
	}
	
	@Test
	public void test2()
	{
		System.out.println("--test2--");
	}
	

}
