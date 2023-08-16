package com.trid.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.trid.GenericUtility.WebDriverUtilities;

public class HomePage {
     @FindBy(xpath="//a[@href='customer.php']")
     private WebElement customerLink;
     
     @FindBy(xpath="//a[@href='employee.php']")
     private WebElement employeeLink;
     
     @FindBy(xpath="//span[.='Product']")
     private WebElement productLink;
     
     @FindBy(xpath="//span[.='Inventory']")
     private WebElement inventoryLink;
     
     @FindBy(xpath="//span[.='Transaction']")
     private WebElement transactionLink;
     
     @FindBy(xpath="//span[.='Supplier']")
     private WebElement supplierLink;
     
     @FindBy(xpath="//span[.='Accounts']")
     private WebElement accountLink;
     
     @FindBy(xpath="//span[text()='Prince Ly Cesar']")
     private WebElement princeLyDropdown;
     
     @FindBy(xpath="//a[@data-target='#logoutModal']")
     private WebElement logoutLink;
     
     @FindBy(xpath="//div[@aria-modal='true']/descendant::a")
     private WebElement logoutBtn;
     
     public HomePage(WebDriver driver)
     {
    	 PageFactory.initElements(driver, this);
     }

	public WebElement getCustomerLink() {
		return customerLink;
	}
	public WebElement getEmployeeLink() {
		return employeeLink;
	}
	public WebElement getProductLink() {
		return productLink;
	}
	public WebElement getInventoryLink() {
		return inventoryLink;
	}
	public WebElement getTransactionLink() {
		return transactionLink;
	}
	public WebElement getSupplierLink() {
		return supplierLink;
	}
	public WebElement getAccountLink() {
		return accountLink;
	}
	public WebElement getPrinceLyDropdown() {
		return princeLyDropdown;
	}
	public WebElement getLogoutLink() {
		return logoutLink;
	}
	public WebElement getLogoutBtn() {
		return logoutBtn;
	}
     
    public void clickOnCustomer()
    {
    	getCustomerLink().click();
    }
    public void clickOnProduct()
    {
    	getProductLink().click();
    }
    public void clickOnInventory()
    {
    	getInventoryLink().click();
    }
    public void clickOnSupplier()
    {
    	getSupplierLink().click();
    }
    public void clickOnAccounts()
    {
    	getAccountLink().click();
    }
    WebDriverUtilities wUTIL = new WebDriverUtilities();
    
    public void adminLogOut(WebDriver driver)
    {
    	princeLyDropdown.click();
        wUTIL.moveToElement(driver, logoutLink);
        wUTIL.waitUntilElementToBeVisible(driver, logoutBtn);
        logoutBtn.click();
    }
}
