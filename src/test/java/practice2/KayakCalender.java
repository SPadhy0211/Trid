package practice2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KayakCalender {
	public static void main(String[] args) {
		int year = 2023;
		String mon = "September";
		int date = 10;
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.kayak.co.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		WebElement from = driver.findElement(By.xpath("//input[@aria-label='Flight origin input']"));
		from.click();
		from.sendKeys("Bangalore");
		driver.findElement(By.xpath("//input[@placeholder='To?']")).sendKeys("Delhi");
		driver.findElement(By.xpath("//span[@aria-label='Start date calendar input']//descendant::span[@class='sR_k-value']")).click();
		driver.findElement(By.xpath("//div[text()='"+mon+" "+year+"']/ancestor::div[@class='onx_ onx_-pres-mcfly onx_-double']/descendant::div[.='"+date+"']")).click();		
	}

}
