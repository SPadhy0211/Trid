package product;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.trid.GenericUtility.ExcelUtility;
import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.JavaUtility;
import com.trid.GenericUtility.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ViewSupplierInAddProductPageSysyemTest {
	public static void main(String[] args) throws Throwable {
		/*Random ran = new Random();
    	int rn = ran.nextInt(1000);

    	FileInputStream fs = new FileInputStream(".//src/test/resources/SDET50.xlsx");
    	Workbook wb = WorkbookFactory.create(fs);
    	Sheet sh = wb.getSheet("Supplier");
    	Row rw = sh.getRow(1);
    	String companyName = rw.getCell(0).getStringCellValue() + rn;
    	String prov = rw.getCell(2).getStringCellValue();
    	String city = rw.getCell(3).getStringCellValue();
    	DataFormatter df = new DataFormatter();
    	String phNo = df.formatCellValue(rw.getCell(1));

    	FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String UN = pObj.getProperty("username");
		String PWD = pObj.getProperty("password");
		 */
		ExcelUtility eUTIL = new ExcelUtility();
		JavaUtility jUTIL = new JavaUtility();
		FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();

		String companyName = eUTIL.readDataFromExcel("Supplier", 1, 0) + jUTIL.getRandomNo();
		String prov = eUTIL.readDataFromExcel("Supplier", 1, 2);
		String city = eUTIL.readDataFromExcel("Supplier", 1, 3);
		String phNo = eUTIL.readDataFromExcel("Supplier", 1, 1);

		String BROWSER = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");
		String UN = fUTIL.readDataFromPropertyFile("username");
		String PWD = fUTIL.readDataFromPropertyFile("password");


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
		driver.findElement(By.xpath("//input[@name='user']")).sendKeys(UN, Keys.TAB, PWD, Keys.ENTER);
		wUTIL.acceptAlert(driver);

		// click on supplier
		driver.findElement(By.xpath("//span[text()='Supplier']")).click();
		driver.findElement(By.xpath("//i[@class='fas fa-fw fa-plus']")).click();
		driver.findElement(By.xpath("//input[@name='companyname']")).sendKeys(companyName, Keys.TAB,Keys.TAB,Keys.TAB, phNo);
		/*new Select(driver.findElement(By.xpath("//input[@name='companyname']/ancestor::div[@class='modal-content']"
				+ "/descendant::select[@name='province']"))).selectByVisibleText(prov);
		new Select(driver.findElement(By.xpath("//input[@name='companyname']/ancestor::div[@class='modal-content']"
				+ "/descendant::select[@name='city']"))).selectByVisibleText(city);
		 */
		wUTIL.select(prov,driver.findElement(By.xpath("//input[@name='companyname']/ancestor::div[@class='modal-content']/descendant::select[@name='province']")));
		wUTIL.select(city,driver.findElement(By.xpath("//input[@name='companyname']/ancestor::div[@class='modal-content']/descendant::select[@name='city']")));

		driver.findElement(By.xpath("//input[@name='companyname']/ancestor::div[@class='modal-content']/descendant::button[.='Save']")).click();
		
		//click on product tab
		driver.findElement(By.xpath("//a[@href='product.php']")).click();
		driver.findElement(By.xpath("//i[@class='fas fa-fw fa-plus']")).click();
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
		if(flag)
			System.out.println("passed");
		else
			System.out.println("failed");		
	}
}
