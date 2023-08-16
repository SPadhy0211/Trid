package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTripCalenderTest {

	public static void main(String[] args) {
		String Month = "July";
		int year = 2023;
		int date = 28;
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.navigate().refresh();
		Actions act = new Actions(driver);
		act.moveByOffset(40, 50).click().perform();
		
		driver.findElement(By.xpath("//label[@for='departure']")).click();
		String actualDate = "(//div[text()='"+Month+"']/span[text()='"+year+"']//ancestor::div[3]/descendant::p[text()="+date+"])[2]";
		
		String expDate = "(//span[@role='button'])[2]";
		
		 driver.findElement(By.xpath(actualDate)).click();
		
		 /*
		for(;;)
		 try
		{
			 driver.findElement(By.xpath(actualDate)).click();
			 break;
		}
		catch (Exception e) {
		     driver.findElement(By.xpath(expDate)).click();
		}*/

	}

}
