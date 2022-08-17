package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageLogin {
	
	private WebDriver driver;
	private By userField;
	private By passField;
	private By loginButton;
	
	public PageLogin(WebDriver driver) {
		this.driver= driver;
		userField = By.cssSelector("#user-name");
		passField = By.cssSelector("#password");
		loginButton = By.cssSelector("#login-button");
	}
	
	public void login(String user, String pass) {
		
		driver.findElement(userField).sendKeys(user);
	    driver.findElement(passField).sendKeys(pass);
	    driver.findElement(loginButton).click();
	}
}
