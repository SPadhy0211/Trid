package accounts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.WebDriverUtilities;
import com.trid.ObjectRepository.AccountsPage;
import com.trid.ObjectRepository.HomePage;
import com.trid.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddAnUserAccountSmokeTest {
	public static void main(String[] args) throws Exception {
		/*FileInputStream fis = new FileInputStream(".//src/test/resources/CommonData.properties");
		Properties pro = new Properties();
		pro.load(fis);
		String Browser = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String UN = pro.getProperty("username");
		String PWD = pro.getProperty("password");
		*/
		FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();
		
		String Browser = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");
		
		
		WebDriver driver;
		if(Browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		driver.get(URL);
		wUTIL.waitForPageLoad(driver);
		wUTIL.maximizePage(driver);
		
		LoginPage lp = new LoginPage(driver);
		lp.loginAsAdmin(driver);
		//driver.findElement(By.xpath("//input[@name='user']")).sendKeys(UN,Keys.TAB,PWD,Keys.ENTER);
		
		//click on account tab
		HomePage hp = new HomePage(driver);
		hp.clickOnAccounts();
		//driver.findElement(By.xpath("//span[text()='Accounts']")).click();
		
		AccountsPage ap = new AccountsPage(driver);
		ap.addUserAccount();
		//driver.findElement(By.xpath("//i[@class='fas fa-fw fa-plus']")).click();
		
		String expected = "Add User Account";
		String actual = driver.findElement(By.xpath("//h5[.='Add User Account']")).getAttribute("innerHTML");
		System.out.println(actual);
		if(actual.equalsIgnoreCase(expected))
			System.out.println(expected + " pop is displayed");
		
		
	}

}
