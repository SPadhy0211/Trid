package pomPractise.admin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;
import com.trid.ObjectRepository.HomePage;

public class SupplierModule_SmokeTest extends BaseClass{
	/*	FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();

		String Browser = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");

		WebDriver driver;
		if(Browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(URL);
		wUTIL.waitForPageLoad(driver);
		wUTIL.maximizePage(driver);

		LoginPage lp = new LoginPage(driver);
		lp.loginAsAdmin(driver);
	*/
	    @Test(groups = "Smoke")
	    public void viewTheSupplierList()
	    {
        //click on supplier
		HomePage hp = new HomePage(driver);
		hp.clickOnSupplier();
		
		//assertion
		String actual = driver.findElement(By.xpath("//h4[contains(text(),'Supplier')]")).getText().strip();
		String exp = "Supplier";
		Assert.assertEquals(actual, exp);

	}
}
