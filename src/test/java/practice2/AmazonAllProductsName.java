package practice2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonAllProductsName {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.id("twotabsearchtextbox"));
        element.sendKeys("iphone");
        element.submit();
        List<WebElement> allProducts = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
        List<WebElement> allPrices = driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']/../../../"
        		+ "following-sibling::div[@class='sg-row']/descendant::span[@class='a-price-whole']"));
        for(int i=0; i<allProducts.size(); i++)
        {
        	System.out.println(allProducts.get(i).getText()+"----"+allPrices.get(i).getText());
        }
	}

}
