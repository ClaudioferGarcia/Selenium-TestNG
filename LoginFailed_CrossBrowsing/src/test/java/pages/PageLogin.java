package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageLogin {

	private WebDriver driver;
	private By userField;
	private By passField;
	private By loginButton;
	
	public PageLogin(WebDriver driver) {		
		this.driver = driver;
		userField = By.cssSelector("#email");
		passField = By.cssSelector("#passwd");
		loginButton = By.cssSelector("#SubmitLogin");
	}
	
	public void login(String user, String pass) {
		
		driver.findElement(userField).sendKeys(user);
		driver.findElement(passField).sendKeys(pass);
		driver.findElement(loginButton).click();
	}
	
}
