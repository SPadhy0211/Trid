package com.trid.ObjectRepository;

import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.trid.GenericUtility.ExcelUtility;
import com.trid.GenericUtility.JavaUtility;
import com.trid.GenericUtility.WebDriverUtilities;

public class ProductPage {
      @FindBy(xpath="//h4[contains(text(),'Product')]//a")
      private WebElement addBtn;
      
      @FindBy(xpath="//input[@name='prodcode']")
      private WebElement pdtCodeInput;
      
      @FindBy(xpath="//input[@name='name']")
      private WebElement pdtNameInput;
      
      @FindBy(xpath="//textarea[@placeholder='Description']")
      private WebElement descInput;
      
      @FindBy(xpath="//input[@placeholder='Quantity']")
      private WebElement qtyInput;
      
      @FindBy(xpath="//input[@name='onhand']")
      private WebElement onHandInput;
      
      @FindBy(xpath="//input[@name='price']")
      private WebElement priceInput;
      
      @FindBy(xpath="//select[@name='category']")
      private WebElement catDropdown;
      
      @FindBy(xpath="//select[@name='supplier']")
      private WebElement supDropdown;
      
      @FindBy(xpath="//input[@placeholder='Date Stock In']")
      private WebElement datefield;
      
      @FindBy(xpath="//input[@name='datestock']/../following-sibling::button[1]")
      private WebElement saveBtn;
      
      @FindBy(xpath="//a[text()='Next']/..")
      private WebElement nextBtn;
      
      public ProductPage(WebDriver driver)
      {
    	  PageFactory.initElements(driver,this);
      }

	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getPdtCodeInput() {
		return pdtCodeInput;
	}

	public WebElement getPdtNameInput() {
		return pdtNameInput;
	}

	public WebElement getDescInput() {
		return descInput;
	}

	public WebElement getQtyInput() {
		return qtyInput;
	}

	public WebElement getOnHandInput() {
		return onHandInput;
	}

	public WebElement getPriceInput() {
		return priceInput;
	}

	public WebElement getCatDropdown() {
		return catDropdown;
	}

	public WebElement getSupDropdown() {
		return supDropdown;
	}

	public WebElement getDatefield() {
		return datefield;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getNextBtn() {
		return nextBtn;
	}
      
    public void clickOnAddBtn()
    {
    	addBtn.click();
    }
    
      ExcelUtility eUTIL = new ExcelUtility();
      WebDriverUtilities wUTIL = new WebDriverUtilities();
      JavaUtility jUTIL = new JavaUtility();
      int random = jUTIL.getRandomNo();
      
    public String addProductPopUp(WebDriver driver) throws Throwable
    {
    	Map<String, String> map = eUTIL.getMultipleDataFromExcel("Product1", 0, 1, driver);

		String pCode = "";
		for (Entry<String, String> eachSet : map.entrySet()) {
			String key = eachSet.getKey();
			String value = eachSet.getValue();
			if (key.contains("category") || key.contains("supplier"))
				wUTIL.select(value, driver.findElement(By.name(key)));
			else if (key.contains("description")) {
				driver.findElement(By.xpath(key)).sendKeys(value);
			}
			else if (key.contains("prodcode")) {
				pCode = value + random;
				driver.findElement(By.name(key)).sendKeys(pCode);
			} 
			else if (key.contains("date")) {
				WebElement ele = driver.findElement(By.name(key));
				ele.click();
				ele.sendKeys(value);
			} 
			else
				driver.findElement(By.name(key)).sendKeys(value);
		}
		saveBtn.click();
		return pCode;
    }
      
      
      
}
