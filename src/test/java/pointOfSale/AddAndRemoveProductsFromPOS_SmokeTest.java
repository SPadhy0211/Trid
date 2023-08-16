package pointOfSale;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddAndRemoveProductsFromPOS_SmokeTest {
	     public static void main(String[] args) throws Exception {
		  /*FileInputStream fis = new FileInputStream(".//src/test/resources/CommonData.properties");
			Properties pro = new Properties();
			pro.load(fis);
			String Browser = pro.getProperty("browser");
			String URL = pro.getProperty("UserUrl");
			String UN = pro.getProperty("UserUN");
			String PWD = pro.getProperty("UserPwd");
			*/
	    	 FileUtility fUTIL = new FileUtility();
	    	 WebDriverUtilities wUTIL = new WebDriverUtilities();
	    	 
	    	 String Browser = fUTIL.readDataFromPropertyFile("browser");
	    	 String URL = fUTIL.readDataFromPropertyFile("UserUrl");
	    	 String UN = fUTIL.readDataFromPropertyFile("UserUN");
	    	 String PWD = fUTIL.readDataFromPropertyFile("UserPwd");
	    	 
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
			driver.findElement(By.name("user")).sendKeys(UN,Keys.TAB,PWD,Keys.ENTER);
			wUTIL.acceptAlert(driver);
	
			driver.findElement(By.xpath("//a[text()='Headset']")).click();
			driver.findElement(By.xpath("//h6[text()='Fantech EG1']/following-sibling::input[@name='quantity']")).sendKeys("3");
			driver.findElement(By.xpath("//h6[text()='Fantech EG1']/following-sibling::input[@value='Add']")).click();
			String pName = driver.findElement(By.xpath("//td//input[contains(@value,'Fantech')]")).getAttribute("innerHTML");
			System.out.println(pName);
			driver.findElement(By.xpath("//i[@class='fas fa-fw fa-trash']")).click();
			
			
			
			
			
   }
    
}
