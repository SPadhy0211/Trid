package pomPractise.admin;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;
import com.trid.ObjectRepository.HomePage;
import com.trid.ObjectRepository.InventoryPage;
import com.trid.ObjectRepository.ProductPage;

@Listeners(com.trid.GenericUtility.ListenerImpleClass.class)
public class InventoryModule_SystemTest extends BaseClass {
	/*	ExcelUtility eUTIL = new ExcelUtility();
		JavaUtility jUTIL = new JavaUtility();
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
        //login 
		LoginPage lp = new LoginPage(driver);
		lp.loginAsAdmin(driver);
	*/
        
	    @Test //(groups = "Integration", retryAnalyzer = com.trid.GenericUtility.RetryImplClass.class)
        public void checkAProductInInventoryList() throws Throwable
        {
		//click on product tab
		HomePage hp = new HomePage(driver);
		hp.clickOnProduct();
		
		//click on '+' icon and provide data
		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddBtn();
		String pCode = pp.addProductPopUp(driver);

		//click on inventory
		hp.clickOnInventory();
		Assert.fail();
		//pass pcode in search bar
		InventoryPage ip = new InventoryPage(driver);
		ip.clickOnSearch(pCode);

		//assertion
	/*	try {
			driver.findElement(By.xpath("//td[@class='sorting_1']"));
			System.out.println("product is present");
		}
		catch(Exception e)
		{
			System.out.println("product not present");
		}
	*/
		AssertJUnit.assertTrue(driver.findElements(By.xpath("//td[@class='sorting_1']")).size()!=0);
	}

}
