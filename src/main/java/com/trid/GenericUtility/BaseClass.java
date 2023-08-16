package com.trid.GenericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.trid.ObjectRepository.HomePage;
import com.trid.ObjectRepository.LoginPage;
import com.trid.ObjectRepository.UserHomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	String loginType = "admin";

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public ExcelUtility eUTIL = new ExcelUtility();
	public FileUtility fUTIL = new FileUtility();
	public DatabaseUtility dUTIL = new DatabaseUtility();
	public WebDriverUtilities wUTIL = new WebDriverUtilities();
	public static WebDriver driver;
	//public static WebDriver sdriver;

	@BeforeSuite(alwaysRun = true)
	public void connectDB() throws Throwable {
		dUTIL.connectToDB();
		System.out.println("--DB connected!!--");
	}

	// @Parameters("Browser")
	@BeforeClass(alwaysRun = true)
	public void launchBrowser() throws Throwable {
		String Browser = fUTIL.readDataFromPropertyFile("browser");

		if (Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		System.out.println("---browser launched!!---");
		wUTIL.maximizePage(driver);
	}

	@BeforeMethod(alwaysRun = true)
	public void loginOp() throws Throwable {
		/*
		 * String AUN = fUTIL.readDataFromPropertyFile("username"); 
		 * String APWD = fUTIL.readDataFromPropertyFile("password"); 
		 * String UN = fUTIL.readDataFromPropertyFile("UserUN"); 
		 * String PWD = fUTIL.readDataFromPropertyFile("UserPwd");
		 */
		String URL = fUTIL.readDataFromPropertyFile("url");
		driver.get(URL);
		wUTIL.waitForPageLoad(driver);

		String type = this.getClass().getPackageName();

		LoginPage lp = new LoginPage(driver);
		if (type.contains("admin")) {
			lp.loginAsAdmin(driver);
		} else {
			setLoginType("user");
			lp.loginAsUser(driver);
		}

		System.out.println("--" + loginType + " loggedin successfull--");

		// lp.loginAsUser(driver);
		// System.out.println("--user loggedin successful---");
	}

	@AfterMethod(alwaysRun = true)
	public void logoutOp() throws Throwable {
		String type = this.getClass().getPackageName();

		if (type.contains("admin")) {

			HomePage hp = new HomePage(driver);
			hp.adminLogOut(driver);
		} else {
			UserHomePage uhp = new UserHomePage(driver);
			uhp.userLogOut();
		}

		System.out.println("--" + loginType + " loggedout successfull--");

		// UserHomePage uhp = new UserHomePage(driver);
		// uhp.userLogOut();
		// System.out.println("--user logged out--");
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
		System.out.println("--browser closed--");
	}

	@AfterSuite(alwaysRun = true)
	public void closeDB() throws Throwable {
		dUTIL.closeDB();
		System.out.println("--DB closed--");
	}
}
