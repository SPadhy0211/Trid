package com.trid.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.trid.GenericUtility.ExcelUtility;
import com.trid.GenericUtility.WebDriverUtilities;

public class AccountsPage {
	WebDriverUtilities wUTIL = new WebDriverUtilities();
	ExcelUtility eUTIL = new ExcelUtility();
	
	@FindBy(xpath="//i[@class='fas fa-fw fa-plus']")
    private WebElement userAddBtn;
	
	@FindBy(xpath="//select[@name='empid']")
	private WebElement employeeDropdown;
	
	@FindBy(xpath="//form[@action='us_transac.php?action=add']/descendant::input[@name='username']")
	private WebElement usernameInput;
	
	@FindBy(xpath="//form[@action='us_transac.php?action=add']/descendant::input[@name='password']")
	private WebElement passwordInput;
	
	@FindBy(xpath="//form[@action='us_transac.php?action=add']/descendant::button[@class='btn btn-success']")
	private WebElement saveBtn;
	
	public AccountsPage(WebDriver driver)
	{
		//PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, AccountsPage.class);
	}

	public WebElement getUserAddBtn() {
		return userAddBtn;
	}

	public WebElement getEmployeeDropdown() {
		return employeeDropdown;
	}

	public WebElement getUsernameInput() {
		return usernameInput;
	}

	public WebElement getPasswordInput() {
		return passwordInput;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public void addUserAccount()
	{
		getUserAddBtn().click();
	}
	
}
