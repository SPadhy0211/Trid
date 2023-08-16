package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.trid.GenericUtility.ExcelUtility;
import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.JavaUtility;
import com.trid.GenericUtility.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckAProductInInventoryList_SystemTest {
	public static void main(String[] args) throws Throwable {
		/* Random rn = new Random();
	  		int rNum = rn.nextInt(1000);

	  		FileInputStream fx = new FileInputStream(".//src/test/resources/SDET50.xlsx");
	  		Workbook book = WorkbookFactory.create(fx);
	  		Sheet sh = book.getSheet("Product");
	  		Row r = sh.getRow(1);
	  		String pCode = r.getCell(0).getStringCellValue() + rNum;
	  		String pName = r.getCell(1).getStringCellValue();
	  		String desc = r.getCell(2).getStringCellValue();
	  		String category = r.getCell(6).getStringCellValue();
	  		String supplier = r.getCell(7).getStringCellValue();

	  		DataFormatter df = new DataFormatter();
	  		String qty = df.formatCellValue(r.getCell(3));
	  		String onHand =df.formatCellValue(r.getCell(4));
	  		String price = df.formatCellValue(r.getCell(5));

			FileInputStream fis = new FileInputStream(".//src/test/resources/CommonData.properties");
			Properties pro = new Properties();
			pro.load(fis);
			String Browser = pro.getProperty("browser");
			String URL = pro.getProperty("url");
			String UN = pro.getProperty("username");
			String PWD = pro.getProperty("password");
		 */
		ExcelUtility eUTIL = new ExcelUtility();
		JavaUtility jUTIL = new JavaUtility();
		FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();

		String Browser = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("url");
		String UN = fUTIL.readDataFromPropertyFile("username");
		String PWD = fUTIL.readDataFromPropertyFile("password");

		String pCode = eUTIL.readDataFromExcel("Product", 1, 0) + jUTIL.getRandomNo();
		String pName = eUTIL.readDataFromExcel("Product", 1, 1);
		String desc = eUTIL.readDataFromExcel("Product", 1, 2);
		String category = eUTIL.readDataFromExcel("Product", 1, 6);
		String supplier = eUTIL.readDataFromExcel("Product", 1, 7);
		String qty = eUTIL.readDataFromExcel("Product", 1, 3);
		String onHand = eUTIL.readDataFromExcel("Product", 1, 4);
		String price = eUTIL.readDataFromExcel("Product", 1, 5);

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

		//click on product tab
		driver.findElement(By.xpath("//span[text()='Product']")).click();
		//click on '+' icon and provide data
		driver.findElement(By.xpath("//h4[contains(text(),'Product')]//a")).click();
		driver.findElement(By.xpath("//input[@name='prodcode']")).sendKeys(pCode,Keys.TAB,pName,Keys.TAB,desc,Keys.TAB,
				qty,Keys.TAB,onHand,Keys.TAB,price);

		//click on select category dropdown
		//new Select(driver.findElement(By.xpath("//select[@name='category']"))).selectByVisibleText(category);
        wUTIL.select(category, driver.findElement(By.xpath("//select[@name='category']")));
		
		//click on select supplier dropdown
		//new Select(driver.findElement(By.xpath("//select[@name='supplier']"))).selectByVisibleText(supplier);
        wUTIL.select(supplier,driver.findElement(By.xpath("//select[@name='supplier']")) );

		//click on date stock in textbox
		WebElement element = driver.findElement(By.xpath("//input[@placeholder='Date Stock In']"));
		element.click();
		element.sendKeys("12022023");

		//click on save button
		driver.findElement(By.xpath("//input[@name='datestock']/../following-sibling::button[1]")).click();

		//click on inventory
		driver.findElement(By.xpath("//span[text()='Inventory']")).click();
		//pass pcode in search bar
		driver.findElement(By.xpath("//input[@class='form-control form-control-sm']")).sendKeys(pCode);

		//assertion
		try {
			driver.findElement(By.xpath("//td[@class='sorting_1']"));
			System.out.println("product is present");
		}
		catch(Exception e)
		{
			System.out.println("product not present");
		}
		// logout operation
		driver.findElement(By.xpath("//span[text()='Prince Ly Cesar']")).click();

		driver.findElement(By.xpath("//a[@data-target='#logoutModal']")).click();

		// click on logout on popup
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();


	}

}
