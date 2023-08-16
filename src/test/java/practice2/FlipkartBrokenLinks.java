package practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartBrokenLinks {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
        System.out.println(allLinks.size());
        
        ArrayList<String> listLinks = new ArrayList<String>();
        for(int i=0;i<allLinks.size();i++)
        {
        	String eachLink = allLinks.get(i).getAttribute("href");
        	
        }
        
        
	}

}
