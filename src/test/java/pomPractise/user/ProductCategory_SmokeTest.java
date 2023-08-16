package pomPractise.user;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;
import com.trid.ObjectRepository.UserHomePage;

public class ProductCategory_SmokeTest extends BaseClass {
		 
	      /* FileUtility fUTIL = new FileUtility();
	    	 WebDriverUtilities wUTIL = new WebDriverUtilities();
	    
	         String Browser = fUTIL.readDataFromPropertyFile("browser");
	    	 String URL = fUTIL.readDataFromPropertyFile("UserUrl");
	    	 
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
			lp.loginAsUser(driver);
			wUTIL.acceptAlert(driver);
	        */
	        @Test(groups = "Smoke")
	        public void AddAndRemoveProductsFromPOS() throws Throwable
	        {
			//click on headset
			UserHomePage uhp = new UserHomePage(driver);
			uhp.clickOnHeadset();
			
			uhp.addProduct();
			
			String pName = driver.findElement(By.xpath("//table//tr/td[1]")).getText();
			String exp = "Fantech EG1";
			String qty = driver.findElement(By.xpath("//table//tr/td[2]")).getText();
			String expQty = "5";
			System.out.println(pName+" "+qty);
			//assertion
			assertEquals(pName, exp);
			assertEquals(qty, expQty);
			//driver.findElement(By.xpath("//i[@class='fas fa-fw fa-trash']")).click();
			uhp.clickOnDelete();
	        }		
   }
    
