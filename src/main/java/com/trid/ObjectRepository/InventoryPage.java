package com.trid.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
	
	 @FindBy(xpath="//input[@class='form-control form-control-sm']")
     private WebElement searchBar;
     
     @FindBy(xpath="//li[.='Next']")
     private WebElement nextBtn;
    
     public InventoryPage(WebDriver driver)
     {
    	 PageFactory.initElements(driver, this);
     }

	public WebElement getSearchBar() {
		return searchBar;
	}
	public WebElement getNextBtn() {
		return nextBtn;
	}
     
    public void clickOnSearch(String pCode)
    {
    	searchBar.sendKeys(pCode);
    }
}
