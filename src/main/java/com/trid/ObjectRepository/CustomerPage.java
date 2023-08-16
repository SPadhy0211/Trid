package com.trid.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerPage {
     @FindBy(xpath="//i[@class='fas fa-fw fa-plus']")
     private WebElement addBtn;
     
     @FindBy(xpath="//form[@action='cust_transac.php?action=add']/descendant::input[@name='firstname']")
     private WebElement firstNameInput;
     
     @FindBy(xpath="//form[@action='cust_transac.php?action=add']/descendant::input[@name='lastname']")
     private WebElement lastNameInput;
     
     @FindBy(xpath="//form[@action='cust_transac.php?action=add']/descendant::input[@name='phonenumber']")
     private WebElement phNoInput;
     
     @FindBy(xpath="//form[@action='cust_transac.php?action=add']/descendant::button[@class='btn btn-success']")
     private WebElement saveBtn;
     
     @FindBy(xpath="//form[@action='cust_transac.php?action=add']/descendant::button[@class='btn btn-danger']")
     private WebElement resetBtn;
     
     @FindBy(xpath="//form[@action='cust_transac.php?action=add']/descendant::button[.='Cancel']")
     private WebElement cancelBtn;
     
     @FindBy(xpath="//input[@aria-controls='dataTable']")
     private WebElement searchBar;
     
     @FindBy(xpath="//a[.='Next']")
     private WebElement nextBtn;
     
     @FindBy(xpath="//a[.='Previous']")
     private WebElement previousBtn;
     
     @FindBy(xpath="//select[@name='dataTable_length']")
     private WebElement dropDown;
     
     public CustomerPage(WebDriver driver)
     {
    	 PageFactory.initElements(driver, this);
     }

	public WebElement getAddBtn() {
		return addBtn;
	}

	public WebElement getFirstNameInput() {
		return firstNameInput;
	}

	public WebElement getLastNameInput() {
		return lastNameInput;
	}

	public WebElement getPhNoInput() {
		return phNoInput;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getResetBtn() {
		return resetBtn;
	}

	public WebElement getCancelBtn() {
		return cancelBtn;
	}

	public WebElement getSearchBar() {
		return searchBar;
	}

	public WebElement getNextBtn() {
		return nextBtn;
	}

	public WebElement getPreviousBtn() {
		return previousBtn;
	}

	public WebElement getDropDown() {
		return dropDown;
	}
    
	public void clickOnSearch(String firstname)
	{
		searchBar.click();
		searchBar.sendKeys(firstname);
	}
     
     
}
