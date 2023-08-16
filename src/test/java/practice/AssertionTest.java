package practice;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AssertionTest {
	WebDriver driver;
     @Test
     public void titleTest()
     {
    	 WebDriverManager.chromedriver().setup();
    	 driver = new ChromeDriver();
    	 driver.get("http://rmgtestingserver/domain/Sales_And_Inventory_System/pages/login.php");
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	 driver.manage().window().maximize();
    	 String title = driver.getTitle();
    	 String exp = "Sales And Inventory";
    	 assertEquals(title, exp, "--not matching--");
     }
     @Test
     public void loginTest()
     {
    	 WebDriverManager.chromedriver().setup();
    	 driver = new ChromeDriver();
    	 driver.get("http://rmgtestingserver/domain/Sales_And_Inventory_System/pages/login.php");
    	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	 driver.manage().window().maximize();
    	 driver.findElement(By.xpath("//input[@name='user']")).sendKeys("unguardable");
    	 driver.findElement(By.name("password")).sendKeys("admin");
    	 
     }
}
