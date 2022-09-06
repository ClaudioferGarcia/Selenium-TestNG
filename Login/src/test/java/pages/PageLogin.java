package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageLogin {
	
	@FindBy(xpath ="//input[@id='user-name']")
	private WebElement userField;
	
	@FindBy(xpath ="//input[@id='password']")
	private WebElement passField;
	
	@FindBy(xpath ="//input[@id='login-button']")
	private WebElement btnLogin;
	
	
	public PageLogin(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String user, String pass) {
		userField.sendKeys(user);
		passField.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
}
