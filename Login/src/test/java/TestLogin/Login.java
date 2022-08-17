package TestLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import helpers.Helpers;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageProducts;

public class Login {
	
	private String url = "https://www.saucedemo.com/";
	private String driverPath = "..\\Proyectos\\Driver\\chromedriver.exe";
	private WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		 System.setProperty("webdriver.chrome.driver" , driverPath);
	     driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     driver.navigate().to(url);
	     
	     Helpers helpers = new Helpers();
	     helpers.sleepSecunds(5);
	}
	
	@Test(priority = 0)
	public void loginOk() {
		
		PageLogin pageLogin = new PageLogin(driver);
		PageProducts pageProducts = new PageProducts(driver);
		pageLogin.login("standard_user", "secret_sauce");
	    pageProducts.assertLoginProducts();
	}
	
	@Test(priority = 1)
	public void loginNoOK() {
		
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "sauce");
		pageLogon.assertLogonPage();
	    
	    
	}
	
	@AfterMethod
	public void closePage(){
		driver.close();
	}

}
