package product;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trid.GenericUtility.ExcelUtility;
import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.JavaUtility;
import com.trid.GenericUtility.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddAProductTest {
	public static void main(String[] args) throws Throwable {
		/*
		 * Random rn = new Random(); int rNum = rn.nextInt(1000);
		 */
		JavaUtility jUTIL = new JavaUtility();
		ExcelUtility eUTIL = new ExcelUtility();
		FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();
		int random = jUTIL.getRandomNo();

		/*
		 * FileInputStream fx = new
		 * FileInputStream(".//src/test/resources/SDET50.xlsx"); Workbook book =
		 * WorkbookFactory.create(fx); Sheet sh = book.getSheet("Product"); Row r =
		 * sh.getRow(1); String pCode = r.getCell(0).getStringCellValue() + rNum; String
		 * pName = r.getCell(1).getStringCellValue(); String desc =
		 * r.getCell(2).getStringCellValue(); String category =
		 * r.getCell(6).getStringCellValue(); String supplier =
		 * r.getCell(7).getStringCellValue(); DataFormatter df = new DataFormatter();
		 * String qty = df.formatCellValue(r.getCell(3)); String onHand =
		 * df.formatCellValue(r.getCell(4)); String price =
		 * df.formatCellValue(r.getCell(5));
		 */

		/*
		 * FileInputStream fis = new
		 * FileInputStream(".\\src\\test\\resources\\CommonData.properties"); Properties
		 * pObj = new Properties(); pObj.load(fis); String BROWSER =
		 * pObj.getProperty("browser"); String URL = pObj.getProperty("url"); String UN
		 * = pObj.getProperty("username"); String PWD = pObj.getProperty("password");
		 */
		String BROWSER = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");
		String UN = fUTIL.readDataFromPropertyFile("username");
		String PWD = fUTIL.readDataFromPropertyFile("password");

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
		driver.findElement(By.xpath("//input[@name='user']")).sendKeys(UN, Keys.TAB, PWD, Keys.ENTER);
		wUTIL.acceptAlert(driver);
		// click on product tab
		driver.findElement(By.xpath("//span[text()='Product']")).click();
		// click on '+' icon and provide data
		driver.findElement(By.xpath("//h4[contains(text(),'Product')]//a")).click();
//		for(int k=0;k<=eUTIL.lastcell("Product",1);k++) {
//			String key = eUTIL.readDataFromExcel("Product", 2, k);
//	        String value = eUTIL.readDataFromExcel("Product", 1, k);
//	        if(key.equals("//input[@name='prodcode']"))
//	        {
//	        	pCode=value+jUTIL.getRandomNo();
//	        	driver.findElement(By.xpath(key)).sendKeys(value);
//	        }
//	        else {
//	        driver.findElement(By.xpath(key)).sendKeys(value);
//	        }
//			}
//		
		Map<String, String> map = eUTIL.getMultipleDataFromExcel("Product1", 0, 1, driver);

		String pCode = "";
		for (Entry<String, String> eachSet : map.entrySet()) {
			String key = eachSet.getKey();
			String value = eachSet.getValue();
			if (key.contains("category") || key.contains("supplier"))
				wUTIL.select(value, driver.findElement(By.name(key)));
			else if (key.contains("description")) {
				driver.findElement(By.xpath(key)).sendKeys(value);
			}
			else if (key.contains("prodcode")) {
				pCode = value + random;
				driver.findElement(By.name(key)).sendKeys(pCode);
			} 
			else if (key.contains("date")) {
				WebElement ele = driver.findElement(By.name(key));
				ele.click();
				ele.sendKeys(value);
			} 
			else
				driver.findElement(By.name(key)).sendKeys(value);
		}

		// click on select category dropdown
		// new
		// Select(driver.findElement(By.xpath("//select[@name='category']"))).selectByVisibleText(category);
		//wUTIL.select("category", driver.findElement(By.xpath("//select[@name='category']")));

		// click on select supplier dropdown
		// new
		// Select(driver.findElement(By.xpath("//select[@name='supplier']"))).selectByVisibleText(supplier);
		//wUTIL.select("supplier", driver.findElement(By.xpath("//select[@name='supplier']")));
		// click on date stock in textbox
		//WebElement element = driver.findElement(By.xpath("//input[@placeholder='Date Stock In']"));
		//element.click();
		//element.sendKeys("12022023");

		// click on save button
		driver.findElement(By.xpath("//input[@name='datestock']/../following-sibling::button[1]")).click();

		// WebElement eleNext = driver.findElement(By.xpath("//a[text()='Next']"));

		String con = "//a[text()='Next']/..";

		boolean flag = false;
		while (!driver.findElement(By.xpath(con)).getAttribute("class").contains("disabled")) {

			List<WebElement> expData = driver.findElements(By.xpath("//td[@class='sorting_1']"));

			java.util.Iterator<WebElement> itr = expData.iterator();

			while (itr.hasNext()) {
				String actual = itr.next().getText();

				if (actual.equals(pCode)) {
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

		if (flag)
			System.out.println("product created successfully");
		else
			System.out.println("product creation failed");

		// logout operation
		driver.findElement(By.xpath("//span[text()='Prince Ly Cesar']")).click();

		driver.findElement(By.xpath("//a[@data-target='#logoutModal']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@aria-modal='true']/descendant::a")).click();

	}

}
