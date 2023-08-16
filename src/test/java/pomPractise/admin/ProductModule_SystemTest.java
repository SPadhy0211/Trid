package pomPractise.admin;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;
import com.trid.ObjectRepository.HomePage;
import com.trid.ObjectRepository.ProductPage;
@Listeners(com.trid.GenericUtility.ListenerImpleClass.class)
public class ProductModule_SystemTest extends BaseClass {
	/*	FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();
		

		String BROWSER = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");

		WebDriver driver = null;
		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
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
	    @Test(groups = "System")
	    public void addAProduct() throws Throwable
	    {
		// click on product tab
		HomePage hp = new HomePage(driver);
		hp.clickOnProduct();
		// click on '+' icon and provide data
		ProductPage pp = new ProductPage(driver);
		pp.clickOnAddBtn();
		String pCode = pp.addProductPopUp(driver);

		//click on next button and validate
		Assert.fail();
		String con = "//a[text()='Next']/..";

		boolean flag = false;
		while (!driver.findElement(By.xpath(con)).getAttribute("class").contains("disabled"))
		{
			List<WebElement> expData = driver.findElements(By.xpath("//td[@class='sorting_1']"));

			java.util.Iterator<WebElement> itr = expData.iterator();

			while (itr.hasNext()) 
			{
				String actual = itr.next().getText();

				if (actual.equals(pCode+"r5r")) 
				{
					System.out.println("actual : " + actual);
					System.out.println("pcode :" + pCode);
					flag = true;
					break;
				}
			}
			if (flag)
				break;
			driver.findElement(By.xpath("//a[text()='Next']")).click();
		}

//		if (flag)
//			System.out.println("product created successfully");
//		else
//			System.out.println("product creation failed");
		assertTrue(flag, "product not present");
		System.out.println("product is present");
	}

}
