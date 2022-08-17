package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageLogon {

	private WebDriver driver;
	private By messageText;
	
	public PageLogon(WebDriver driver) {
		this.driver= driver;
		messageText = By.tagName("h3");
	}
	
	public void assertLogonPage() {
		
		Assert.assertTrue(driver.findElement(messageText)
	    		.getText().contains("Epic sadface: Username and password do not match any user in this service"));
		
	}
}
