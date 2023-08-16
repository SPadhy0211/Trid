package customer;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.trid.GenericUtility.ExcelUtility;
import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.JavaUtility;
import com.trid.GenericUtility.WebDriverUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddCustomerFromPOSPage_IntegrationTest {
	public static void main(String[] args) throws Throwable {
		/*Random ran = new Random();
		int rn = ran.nextInt(1000);
	    */
	  /*FileInputStream fs = new FileInputStream(".//src/test/resources/SDET50.xlsx");
    	Workbook wb = WorkbookFactory.create(fs);
    	Sheet sh = wb.getSheet("POS");
    	Row rw = sh.getRow(1);
    	String firstName = rw.getCell(0).getStringCellValue()+rn;
    	String lastName = rw.getCell(1).getStringCellValue();
    	DataFormatter df = new DataFormatter();
    	String phNo = df.formatCellValue(rw.getCell(2));
    	
		FileInputStream fis = new FileInputStream(".//src/test/resources/CommonData.properties");
		Properties pro = new Properties();
		pro.load(fis);
		String Browser = pro.getProperty("browser");
		String URL = pro.getProperty("UserUrl");
		String UN = pro.getProperty("UserUN");
		String PWD = pro.getProperty("UserPwd");
		String AUN = pro.getProperty("username");
		String APWD = pro.getProperty("password");
		*/
		JavaUtility jUTIL = new JavaUtility();
		ExcelUtility eUTIL = new ExcelUtility();
		FileUtility fUTIL = new FileUtility();
		WebDriverUtilities wUTIL = new WebDriverUtilities();
		
		String firstName = eUTIL.readDataFromExcel("POS", 1, 0) + jUTIL.getRandomNo();
		String lastName = eUTIL.readDataFromExcel("POS", 1, 1);
		String phNo = eUTIL.readDataFromExcel("POS", 1, 2);
		
		String Browser = fUTIL.readDataFromPropertyFile("browser");
		String URL = fUTIL.readDataFromPropertyFile("UserUrl");
		String UN = fUTIL.readDataFromPropertyFile("UserUN");
		String PWD = fUTIL.readDataFromPropertyFile("UserPwd");
		String AUN = fUTIL.readDataFromPropertyFile("username");
		String APWD = fUTIL.readDataFromPropertyFile("password");
		
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
		
		driver.findElement(By.xpath("//a[@href='#others']")).click();
		
		//click on product
		List<WebElement> all = driver.findElements(By.xpath("//div[@class='products']"));
		
		Random r = new Random();
		int random = r.nextInt(all.size());
		WebElement prodEle = all.get(random);
		System.out.println(random);
		Thread.sleep(5000);
		WebElement quantityEle = prodEle.findElement(By.xpath("./descendant::input[@name='quantity']"));
		Thread.sleep(5000);
		quantityEle.clear();
		quantityEle.sendKeys("3");
		//click on add button
		prodEle.findElement(By.xpath("./descendant::input[@name='addpos']")).click();
	
		//click on + button
		driver.findElement(By.xpath("//i[@class='fas fa-fw fa-plus']")).click();
		
		driver.findElement(By.xpath("//div[@id='poscustomerModal']/descendant::input[@name='firstname']"))
		.sendKeys(firstName,Keys.TAB,lastName,Keys.TAB,phNo,Keys.ENTER);
		//Alert
		wUTIL.acceptAlert(driver);
		
		
		//logout
        driver.findElement(By.xpath("//a[@id='userDropdown']")).click();
        driver.findElement(By.xpath("//i[@class='fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//div[@aria-modal='true']/descendant::a")).click();
        
        //login to admin
        driver.findElement(By.name("user")).sendKeys(AUN , Keys.TAB , APWD , Keys.ENTER);
        wUTIL.acceptAlert(driver);
        driver.findElement(By.xpath("//a[@href='customer.php']")).click();
        WebElement search = driver.findElement(By.xpath("//input[@aria-controls='dataTable']"));
        search.click();
        search.sendKeys(firstName);
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
        if(driver.findElements(By.xpath("//td[@class='sorting_1']")).size()!=0)
        	 System.out.println("validation successful");
        else
        	System.out.println("validation not successful");
        /*
        //assertion
        String expData = driver.findElement(By.xpath("//td[@class='sorting_1']")).getText();
        Assert.assertEquals(firstName , expData);
		*/
	}

}
