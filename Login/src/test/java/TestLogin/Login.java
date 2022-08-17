package TestLogin;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageProducts;

public class Login {
	
	private String url = "https://www.saucedemo.com/";
	private String driverPath = "..\\Login\\Driver\\chromedriver.exe";
	private WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		 System.setProperty("webdriver.chrome.driver" , driverPath);
	     driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     driver.navigate().to(url);
	     
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	     wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login-button")));     
	}
	
	@Test(priority = 0)
	public void loginOk() {
		
		PageLogin pageLogin = new PageLogin(driver);
		PageProducts pageProducts = new PageProducts(driver);
		pageLogin.login("standard_user", "secret_sauce");
	   
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.
	    elementToBeClickable(By.cssSelector("div.page_wrapper div:nth-child(1) div.header_container:nth-child(1) "
	    		+ "div.header_secondary_container > span.title")));
	    
	    pageProducts.assertLoginProducts();
	}
	
	@Test(priority = 1)
	public void loginNoOK() {
		
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "sauce");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	    wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h3"))); 
	    
		pageLogon.assertLogonPage();   
	}
	
	@AfterMethod
	public void closePage(){
		driver.close();
	}
}
