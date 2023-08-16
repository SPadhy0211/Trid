package com.trid.ObjectRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.trid.GenericUtility.ExcelUtility;

public class UserHomePage {
	WebDriver driver;
	
	@FindBy(xpath = "//a[.='Headset']")
	private WebElement headsetTab;

	@FindBy(xpath = "//a[.='Motherboard']")
	private WebElement motherboardTab;

	@FindBy(xpath = "//a[.='Processor']")
	private WebElement processorTab;

	@FindBy(xpath = "//a[.='Others']")
	private WebElement othersTab;

	@FindBy(xpath = "//div[@class='btn bg-gradient-danger btn-danger']")
	private WebElement deleteBtn;

	@FindBy(xpath = "//i[@class='fas fa-fw fa-plus']")
	private WebElement addBtn;

	@FindBy(xpath = "//div[@id='poscustomerModal']/descendant::input[@name='firstname']")
	private WebElement firstNameInput;

	@FindBy(xpath = "//div[@id='poscustomerModal']/descendant::input[@name='lastname']")
	private WebElement lastNameInput;

	@FindBy(xpath = "//div[@id='poscustomerModal']/descendant::input[@name='phonenumber']")
	private WebElement phNoInput;

	@FindBy(xpath = "//div[@id='poscustomerModal']/descendant::button[@class='btn btn-success']")
	private WebElement saveBtn;

	@FindBy(xpath = "//select[@name='customer']")
	private WebElement customerDropdown;

	@FindBy(xpath = "//button[.='SUBMIT']")
	private WebElement submitBtn;

	@FindBy(xpath = "//a[@id='userDropdown']")
	private WebElement josueyDropdown;

	@FindBy(xpath = "//i[@class='fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400']")
	private WebElement logoutLink;

	@FindBy(xpath = "//div[@aria-modal='true']/descendant::a")
	private WebElement logoutBtn;

	public UserHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	public WebElement getHeadsetTab() {
		return headsetTab;
	}

	public WebElement getMotherboardTab() {
		return motherboardTab;
	}

	public WebElement getProcessorTab() {
		return processorTab;
	}

	public WebElement getOthersTab() {
		return othersTab;
	}

	public WebElement getDeleteBtn() {
		return deleteBtn;
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

	public WebElement getCustomerDropdown() {
		return customerDropdown;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

	public WebElement getJosueyDropdown() {
		return josueyDropdown;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}

	// Business library for click on headset
	public void clickOnHeadset() {
		headsetTab.click();
	}

	// Business Library for Others tab
	public void clickOnOthersTab() {
		othersTab.click();
	}

	// click on delete button
	public void clickOnDelete() {
		deleteBtn.click();
	}

	ExcelUtility eUTIL = new ExcelUtility();

	// business library for add product
	public void addProduct() throws Throwable {
		String productName = "Fantech EG1";
		String quantity = "5";

		WebElement element = driver.findElement(By.xpath("//h6[text()='" + productName + "']/following-sibling::input[@name='quantity']"));
		element.sendKeys(Keys.CONTROL + "a" + "delete");
		element.sendKeys(quantity);
		driver.findElement(By.xpath("//h6[text()='Fantech EG1']/following-sibling::input[@value='Add']")).click();
	}
	
	//click on any random product and add
	public void clickOnProduct() throws Throwable
	{
        List<WebElement> all = driver.findElements(By.xpath("//div[@class='products']"));
		
		Random r = new Random();
		int random = r.nextInt(all.size());
		WebElement prodEle = all.get(random);
		System.out.println(random);
		Thread.sleep(5000);
		WebElement quantityEle = prodEle.findElement(By.xpath("./descendant::input[@name='quantity']"));
		Thread.sleep(5000);
		quantityEle.clear();
		quantityEle.sendKeys("3");
		
		//click on add button
	    prodEle.findElement(By.xpath("./descendant::input[@name='addpos']")).click();
	}
	/**
	 * Business Library to add customer details
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phNo
	 * @throws Throwable 
	 */
	public String addCustomerPopUp() throws Throwable {
		addBtn.click();
		Map<String, String> map = eUTIL.getMultipleDataFromExcel("POS1", 0, 1, driver);
		String firstname="";
		for(Entry<String, String> eachMap : map.entrySet())
		{
			String key = eachMap.getKey();
			String value = eachMap.getValue();
			if(key.contains("firstname"))
			{
			    firstname = value;
			}
			driver.findElement(By.xpath(key)).sendKeys(value);
		}
		saveBtn.click();
		return firstname;
	}
	// Business library for logout
	public void userLogOut() throws Throwable {
		josueyDropdown.click();
		logoutLink.click();
		Thread.sleep(5000);
		logoutBtn.click();
	}
}
