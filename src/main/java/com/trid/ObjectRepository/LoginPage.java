package com.trid.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.trid.GenericUtility.FileUtility;
import com.trid.GenericUtility.WebDriverUtilities;

public class LoginPage {
	@FindBy(xpath = "//input[@name='user']")
	private WebElement userNameInput;

	@FindBy(name = "password")
	private WebElement pwdInput;

	@FindBy(name = "btnlogin")
	private WebElement loginBtn;

	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserNameInput() {
		return userNameInput;
	}
	public WebElement getPwdInput() {
		return pwdInput;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
    WebDriverUtilities wUTIL = new WebDriverUtilities();
	public void loginAsAdmin(WebDriver driver) throws Exception
	{
		FileUtility fUTIL = new FileUtility();

		String AUN = fUTIL.readDataFromPropertyFile("username");
		String APWD = fUTIL.readDataFromPropertyFile("password");

		getUserNameInput().sendKeys(AUN);
		getPwdInput().sendKeys(APWD);
		getLoginBtn().click();
		wUTIL.acceptAlert(driver);
	}

	public void loginAsUser(WebDriver driver) throws Exception
	{
		FileUtility fUTIL = new FileUtility();

		String UN = fUTIL.readDataFromPropertyFile("UserUN");
		String PWD = fUTIL.readDataFromPropertyFile("UserPwd");

		getUserNameInput().sendKeys(UN);
		getPwdInput().sendKeys(PWD);
		getLoginBtn().click();
		wUTIL.acceptAlert(driver);

	}
}
