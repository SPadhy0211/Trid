package practice2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IccAllCountriesName {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.icc-cricket.com/rankings/mens/team-rankings/t20i");
		//driver.get("https://www.icc-cricket.com/rankings/mens/overview");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.findElement(By.xpath("//div[@class='main-navigation__header u-hide-desktop']//a[@href='/rankings/womens/team-rankings']"));
		//driver.findElement(By.xpath("//h4[.='ODI Team Rankings']/ancestor::div[@class='rankings-block__container rankings-block__container--teams']"
		//		+ "/descendant::td[contains(.,'England')]"));
		
	/*  List<WebElement> all = driver.findElements(By.xpath("//table//tr[*]"));
		for(WebElement details:all)
		{
			System.out.println(details.getText());
		}
	*/
		
	/* List<WebElement> allMatches = driver.findElements(By.xpath("//table//tr/td[3][@class='table-body__cell u-center-text' and text()>30]"));
		
		for(WebElement each :allMatches)
		{
			String country = each.findElement(By.xpath("./preceding-sibling::td[1]/span[2]")).getText();
			System.out.println(country+"---"+each.getText());
		}
	*/
		List<WebElement> all = driver.findElements(By.xpath("//table/tbody//tr[position()<11]/td"));
		for(int i=0;i<all.size();i++)
		{
			
			System.out.print(all.get(i).getText() + " ");
			if((i+1)%5==0)
				System.out.println();
			
		}
		
		
		
		
	}

}
