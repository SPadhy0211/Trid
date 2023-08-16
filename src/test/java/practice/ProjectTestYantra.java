package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectTestYantra {
        public static void main(String[] args) throws SQLException {
            //String expData = "TY_Project_1111";
            Connection con = null;
            ResultSet result = null;
            
            Random r = new Random();
            int rnum = r.nextInt(1000);
            
            String pName="rmgyantra"+rnum;
            
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("http://rmgtestingserver:8084/");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath("//input[@name='username']")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999",Keys.ENTER);
			driver.findElement(By.xpath("//a[text()='Projects']")).click();
			driver.findElement(By.xpath("//span[text()='Create Project']")).click();
			driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(pName);
			driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Laxmi");
			Select sp = new Select(driver.findElement(By.xpath("(//select[@name='status'])[2]")));
			sp.selectByValue("On Going");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			
			try {
			Driver driver1 = new Driver();
			DriverManager.registerDriver(driver1);
			con = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
			
			Statement state = con.createStatement();
			String query = "select * from project;";
			result = state.executeQuery(query);
			boolean flag = false;
			while(result.next())
			{
				String actual = result.getString(4);
				if(actual.equalsIgnoreCase(pName))
				{
					flag = true;
					break;
				}
			}
			if(flag==true)
			{
				System.out.println("project created successfully");
			}
			else
			{
				System.out.println("project not created");
			}}
			finally {
				con.close();
			}
			driver.close();
}
}