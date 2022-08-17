package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageProducts {

	private WebDriver driver;
	private String expectedUrl;
	
	public PageProducts(WebDriver driver) {
		this.driver= driver;
		expectedUrl = "https://www.saucedemo.com/inventory.html";
	}
	
	public void assertLoginProducts() {
		
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
	}
}
