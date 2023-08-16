package pomPractise.admin;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;
import com.trid.ObjectRepository.HomePage;
import com.trid.ObjectRepository.ProductPage;
import com.trid.ObjectRepository.SupplierPage;

public class VerifySupplierInProductModule_SysyemTest extends BaseClass{	
	/*	FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();

		String BROWSER = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");

		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.get(URL);
		wUTIL.waitForPageLoad(driver);
		wUTIL.maximizePage(driver);
		// login
		LoginPage lp = new LoginPage(driver);
		lp.loginAsAdmin(driver);
      */
	   @Test(groups = "Integration")
	   public void viewSupplierInAddProductPage() throws Throwable
	   {
		// click on supplier
		HomePage hp = new HomePage(driver);
		hp.clickOnSupplier();
		//add supplier details
		SupplierPage sp = new SupplierPage(driver);
		String companyName = sp.clickOnAddBtn(driver);
		//click on product
		hp.clickOnProduct();

		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddBtn();
		
		List<WebElement> allElement = new Select(driver.findElement(By.xpath("//select[@name='supplier']"))).getOptions();

		boolean flag = false;
		for(WebElement element : allElement )
		{
			String sName = element.getText();
			if(sName.equals(companyName))
			{
				flag=true;
				break;
			}
		}
//		if(flag)
//			System.out.println("supplier name is present");
//		else
//			System.out.println("supplier name is not present");
		assertTrue(flag, "--supplier name not present--");
		System.out.println("supplier added successfully!!!");
		
		driver.navigate().refresh();
	}
}
