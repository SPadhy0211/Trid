package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedbusCalenderTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();
	    driver.get("https://www.redbus.in/");
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    driver.findElement(By.xpath("//text[@class='dateText']")).click();
	    driver.findElement(By.xpath(""));
	    

	}

}
