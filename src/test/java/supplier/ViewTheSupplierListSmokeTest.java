package supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewTheSupplierListSmokeTest {
	public static void main(String[] args) throws Exception {
		/*FileInputStream fis = new FileInputStream(".//src/test/resources/CommonData.properties");
	    Properties pro = new Properties();
	    pro.load(fis);
	    String Browser = pro.getProperty("browser");
	    String UN = pro.getProperty("username");
	    String PWD = pro.getProperty("password");
	    String URL = pro.getProperty("url");
		 */
		FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();

		String Browser = fUTIL.readDataFromPropertyFile("browser");
		String UN = fUTIL.readDataFromPropertyFile("username");
		String PWD = fUTIL.readDataFromPropertyFile("password");
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

		driver.findElement(By.xpath("//input[@name='user']")).sendKeys(UN,Keys.TAB,PWD,Keys.ENTER);
		wUTIL.acceptAlert(driver);

		driver.findElement(By.xpath("//span[text()='Supplier']")).click();
		//assertion
		String actual = driver.findElement(By.xpath("//h4[contains(text(),'Supplier')]")).getText().strip();
		String exp = "Supplier";
		Assert.assertEquals(actual, exp);

		//driver.quit();






	}
}
