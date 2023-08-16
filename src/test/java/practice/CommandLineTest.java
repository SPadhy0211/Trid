package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommandLineTest {
	@Test
    public void readDataFromCommandLine()
    {
    	String Browser = System.getProperty("browser");
    	String URL = System.getProperty("url");
    	String UserName = System.getProperty("username");
    	String Password = System.getProperty("password");
    	WebDriverManager.chromedriver().setup();
    	WebDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get(URL);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.findElement(By.xpath("//input[@name='user']")).sendKeys(UserName,Keys.TAB,Password,Keys.ENTER);
    	driver.switchTo().alert().accept();
    	
    	
    }
}
