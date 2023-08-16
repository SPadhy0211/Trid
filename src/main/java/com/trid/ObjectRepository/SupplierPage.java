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

public class SupplierPage {
	@FindBy(xpath="//i[@class='fas fa-fw fa-plus']")
	private WebElement addBtn;
	
    @FindBy(xpath="//input[@name='companyname']")
    private WebElement companyInput;
    
    @FindBy(xpath="//input[@name='companyname']/ancestor::div[@class='modal-content']/descendant::select[@name='province']")
    private WebElement provDropdown;
    
    @FindBy(xpath="//input[@name='companyname']/ancestor::div[@class='modal-content']/descendant::select[@name='city']")
    private WebElement cityDropdown;
    
    @FindBy(xpath="//form[@action='sup_transac.php?action=add']/descendant::input[@name='phonenumber']")
    private WebElement phNoInput;
    
    @FindBy(xpath="//input[@name='companyname']/ancestor::div[@class='modal-content']/descendant::button[.='Save']")
    private WebElement saveBtn;
    
    @FindBy(xpath="//input[@aria-controls='dataTable']")
    private WebElement searchBar;
    
    public SupplierPage(WebDriver driver)
    {
    	PageFactory.initElements(driver, this);
    }
	public WebElement getAddBtn() {
		return addBtn;
	}
	public WebElement getCompanyInput() {
		return companyInput;
	}
	public WebElement getProvDropdown() {
		return provDropdown;
	}
	public WebElement getCityDropdown() {
		return cityDropdown;
	}
	public WebElement getPhNoInput() {
		return phNoInput;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public WebElement getSearchBar() {
		return searchBar;
	}
	
	WebDriverUtilities wUTIL = new WebDriverUtilities();
	ExcelUtility eUTIL = new ExcelUtility();
	JavaUtility jUTIL = new JavaUtility();
	int random = jUTIL.getRandomNo();
	
	public String clickOnAddBtn(WebDriver driver) throws Throwable
	{
	addBtn.click();
	String companyName="";
	Map<String , String> map = eUTIL.getMultipleDataFromExcel("Supplier1", 0, 1, driver);
	for(Entry<String, String> eachMap : map.entrySet())
	{
		String key = eachMap.getKey();
		String value = eachMap.getValue();
		if(key.contains("province")) 
			wUTIL.select(value, driver.findElement(By.xpath(key)));
		else if(key.contains("city"))
			wUTIL.select(value, driver.findElement(By.xpath(key)));
		else if(key.contains("companyname"))
		{				companyName = value + random;
			driver.findElement(By.xpath(key)).sendKeys(companyName);
		}
		else
	    	driver.findElement(By.xpath(key)).sendKeys(value);
	}
	saveBtn.click();
	return companyName;
	}
}
