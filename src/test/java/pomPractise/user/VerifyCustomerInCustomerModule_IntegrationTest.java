package pomPractise.user;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.trid.GenericUtility.BaseClass;
import com.trid.ObjectRepository.CustomerPage;
import com.trid.ObjectRepository.HomePage;
import com.trid.ObjectRepository.LoginPage;
import com.trid.ObjectRepository.UserHomePage;

public class VerifyCustomerInCustomerModule_IntegrationTest extends BaseClass {
	/*	JavaUtility jUTIL = new JavaUtility();
		ExcelUtility eUTIL = new ExcelUtility();
		FileUtility fUTIL = new FileUtility();
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
  */
        @Test(groups = {"Integration","Smoke"})
        public void addCustomerFromPOSPage() throws Throwable
        {
		UserHomePage uhp = new UserHomePage(driver);
		uhp.clickOnOthersTab();
		
		//click on product
		uhp.clickOnProduct();
		
		//click on + button
	    String firstname = uhp.addCustomerPopUp();
				
		//Alert
		wUTIL.acceptAlert(driver);
		
		//logout
        uhp.userLogOut();
        System.out.println("---user loggedout!!---");
        
        //login to 
        setLoginType("admin");
        LoginPage lp = new LoginPage(driver);
        lp.loginAsAdmin(driver);
        System.out.println("---admin loggedin!!---");
       
        HomePage hp = new HomePage(driver);
        hp.clickOnCustomer();
        
        CustomerPage cp = new CustomerPage(driver);
        cp.clickOnSearch(firstname);
        
       /* 
        //assertion using try catch
        try
        {
        driver.findElement(By.xpath("//td[@class='sorting_1']"));
        System.out.println("validation successful");
        }
        catch (Exception e) {
        	System.out.println("validation failed");
        }
		*/
        
		//assertion using findElements
    /*   if(driver.findElements(By.xpath("//td[@class='sorting_1']")).size()!=0)
        	System.out.println("validation successful");
        else
        	System.out.println("validation not successful");
     */
        //assertion
        String expData = driver.findElement(By.xpath("//td[@class='sorting_1']")).getText();
        Assert.assertEquals(firstname , expData, "customer name not present");
        System.out.println("Customer name added successfully...!!!");
		
	}

}