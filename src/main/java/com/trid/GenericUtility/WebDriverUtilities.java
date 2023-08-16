package com.trid.GenericUtility;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities {
	/**
	 * This method is used to maximize the window
	 * @param driver
	 */
     public void maximizePage(WebDriver driver)
     {
    	 driver.manage().window().maximize();
     }
     /**
      * This method is used to wait for page load
      * @param driver
      */
     public void waitForPageLoad(WebDriver driver)
     {
    	// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	 driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    	 //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     }
     /**
      * This method is used to wait until element to be visible
      * @param driver
      * @param element
      */
     public void waitUntilElementToBeVisible(WebDriver driver, WebElement element)
     {
    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	 wait.until(ExpectedConditions.visibilityOf(element));
     }
     /**
      * This method is used to handle dropdown using index
      * @param element
      * @param index
      */
     public void select(WebElement element,int index)
     {
    	 Select sel = new Select(element);
    	 sel.selectByIndex(index);
     }
     /**
      * This method is used to handle dropdown using value
      * @param element
      * @param value
      */
     public void select(WebElement element , String value)
     {
    	  new Select(element).selectByValue(value);
    	// sel.selectByValue(value);
     }
     /**
      * This method is used to handle dropdown using visible text
      * @param text
      * @param element
      */
     public void select(String text,WebElement element)
     {
    	 Select sel = new Select(element);
    	 sel.selectByVisibleText(text);
     }
     /**
      * This method is to perform move to an element action
      * @param driver
      * @param element
      */
     public void moveToElement(WebDriver driver,WebElement element)
     {
    	 Actions act = new Actions(driver);
    	 act.moveToElement(element).click().perform();
     }
     /**
      * This method is to perform drag and drop action
      * @param driver
      * @param src
      * @param dest
      */
     public void dragAndDrop(WebDriver driver,WebElement src,WebElement dest)
     {
    	 Actions act = new Actions(driver);
    	 act.dragAndDrop(src, dest);
     }
     /**
      * This method is to perform double click on a webelement
      * @param driver
      */
     public void doubleClick(WebDriver driver)
     {
    	 Actions act = new Actions(driver);
    	 act.doubleClick().perform();		 
     }
     /**
      * This method is to perform right click on a web page
      * @param driver
      */
     public void rightClick(WebDriver driver)
     {
    	 Actions act = new Actions(driver);
    	 act.contextClick().perform();
     }
     /**
      * This method is to perform right click on an element
      * @param driver
      * @param element
      */
     public void rightClick(WebDriver driver,WebElement element)
     {
    	 Actions act = new Actions(driver);
    	 act.contextClick(element).perform();
     }
     /**
      * This method is to press enter key
      * @param driver
      */
     public void enterKeyPress(WebDriver driver)
     {
    	 Actions act = new Actions(driver);
    	 act.sendKeys(Keys.ENTER).perform();
     }
     public void enterKey(WebDriver driver) throws Throwable
     {
    	 Robot rb = new Robot();
    	 rb.keyPress(KeyEvent.VK_ENTER);
     }
     public void enterRelease(WebDriver driver) throws Throwable
     {
    	 Robot rb = new Robot();
    	 rb.keyRelease(KeyEvent.VK_ENTER);
     }
     public void switchToFrame(WebDriver driver,int index)
     {
    	 driver.switchTo().frame(index);
     }
     public void switchToFrame(WebDriver driver,String nameOrID)
     {
    	 driver.switchTo().frame(nameOrID);
     }
     public void switchToFrame(WebDriver driver,WebElement address)
     {
    	 driver.switchTo().frame(address);
     }
     public void acceptAlert(WebDriver driver)
     {
    	 driver.switchTo().alert().accept();
     }
     public void dismissAlert(WebDriver driver)
     {
    	 driver.switchTo().alert().dismiss();
     }
     public void getTextAlert(WebDriver driver)
     {
    	 driver.switchTo().alert().getText();
     }
     public void switchToWindow(WebDriver driver,String partialTitle)
     {
    	 //step-1: use getWindowHandles to capture all window IDs
    	 Set<String> windows = driver.getWindowHandles();
    	 
    	 //step-2: iterate through the windows
    	 Iterator<String> it = windows.iterator();
    	 
    	 //step-3: check whether there is next window
    	 while(it.hasNext())
    	 {
    		 //step-4: capture current window ID
    		 String windowID = it.next();
    		 
    		 //step-5: switch to current window and capture title
    		 String currentWinTitle = driver.switchTo().window(windowID).getTitle();
    		 
    		 //step-6: check whether current window is expected
    		 if(currentWinTitle.contains(partialTitle))
    		 {
    			 break;
    		 }
    	 }
     }
     public static File getScreenShot(WebDriver driver, String screenShotName) throws Throwable
     {
    	 TakesScreenshot ts = (TakesScreenshot)driver;
    	 File src = ts.getScreenshotAs(OutputType.FILE);
    	 String path = ".//Screenshot//"+screenShotName+".png";
    	 File dest = new File(path);
    	 FileUtils.copyFile(src, dest);
    	 return dest;
     }
     public void scrollbarAction(WebDriver driver)
     {
    	 JavascriptExecutor jse = (JavascriptExecutor)driver;
    	 jse.executeScript("window.scrollBy(0,400);");
     }
     public void scrollAction(WebDriver driver, WebElement element)
     {
    	 JavascriptExecutor jse = (JavascriptExecutor)driver;
    	 int y = element.getLocation().getY();
    	 jse.executeScript("wendow.scrollBy(0,"+y+")", element);
       //jse.executeScript("argument[0].scrollIntoView()",element);
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
