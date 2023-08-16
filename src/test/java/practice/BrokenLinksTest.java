package practice;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinksTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://rmgtestingserver/domain/Sales_And_Inventory_System/pages/login.php");
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("unguardable" , Keys.TAB , "admin" , Keys.ENTER);
		Alert alt = driver.switchTo().alert();
		alt.accept();

		
	  
        List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
        int count = allLinks.size();
        System.out.println(count);
      //System.out.println(allLinks.size());
        
        ArrayList<String> linkList = new ArrayList<String>();
        
        for(int i=0;i<allLinks.size();i++)
        {
          String eachLink = allLinks.get(i).getAttribute("href");
          
          //URL url = null;
          int statusCode = 0;
          try
          {
        	  URL url = new URL(eachLink);
        	
        	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        	statusCode = connection.getResponseCode();
        	
        	if(statusCode>=400)
        	{
        		linkList.add(eachLink+"--->"+statusCode);
        	}
          }
        	catch(Throwable e)
        	{
        		linkList.add(eachLink+"----->"+statusCode);
        	}
          
        }
        System.out.println(linkList);
	}

}
