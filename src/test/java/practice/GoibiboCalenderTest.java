package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoibiboCalenderTest {
	public static void main(String[] args) {
		String Month = "September";
		int Year = 2023;
		int Date = 25;

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.goibibo.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		driver.findElement(By.xpath("//span[text()='Departure']")).click();

		String actualDate = "//div[text()='" + Month + " " + Year + "']/../../descendant::p[text()=" + Date + "]";
        String arrowPath = "(//span[@role='button'])[2]";
        
        while(true)
        {
        	try
        	{
        	   driver.findElement(By.xpath(actualDate)).click();
        	   break;
        	}
        	catch (Exception e) {
		       driver.findElement(By.xpath(arrowPath)).click();
			}
        }
	}
}
