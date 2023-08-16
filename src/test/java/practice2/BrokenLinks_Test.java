package practice2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLinks_Test {

	public static void main(String[] args) {
	     WebDriverManager.chromedriver().setup();
	     WebDriver driver = new ChromeDriver();
	     driver.get("https://www.irctc.co.in/nget/train-search");
	     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	     List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
         int count = allLinks.size();
         System.out.println(count);
         
         ArrayList<String> linkList = new ArrayList<String>();
         
         for(int i=0;i<3;i++)
         {
        	String eachLink = allLinks.get(i).getAttribute("href");
        	URL url = null;
        	int statusCode = 0;
        	try
        	{
        		url = new URL(eachLink);
        		
        		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        		statusCode = connection.getResponseCode();
        		
        		if(statusCode>=400)
        		{
        			linkList.add(eachLink+"--->"+statusCode);
        			
        		}
        	}
        	catch(Exception e)
        	{
        		linkList.add(eachLink + "--->" + statusCode);
        	}
         }
         System.out.println(linkList);
         driver.quit();
	}

}
