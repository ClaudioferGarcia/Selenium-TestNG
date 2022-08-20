package TestLogin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import Utilities.CaptureEvidence;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageProducts;

public class Login {
	
	private String url = "https://www.saucedemo.com/";
	private String driverPath = "..\\Login\\Driver\\chromedriver.exe";
	private WebDriver driver;
	private String evidenceRoute = "..\\Login\\Evidence\\";
	private String documentName = "Evidence - Saucedemo.docx";
	private String temporaryNameImg = "img.jpg";
	
	
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
	public void loginOk() throws InvalidFormatException, IOException, InterruptedException {
		
		CaptureEvidence.writeTitle(evidenceRoute + documentName, "Documento de Evidencias", 20);
		CaptureEvidence.takeAScreenshot(driver, evidenceRoute + temporaryNameImg , evidenceRoute + documentName, "Login");
		
		PageLogin pageLogin = new PageLogin(driver);
		PageProducts pageProducts = new PageProducts(driver);
		pageLogin.login("standard_user", "secret_sauce");
		
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.
	    elementToBeClickable(By.cssSelector("div.page_wrapper div:nth-child(1) div.header_container:nth-child(1) "
	    		+ "div.header_secondary_container > span.title")));
	    
	    CaptureEvidence.takeAScreenshot(driver, evidenceRoute + temporaryNameImg , evidenceRoute + documentName, "Logueado Ok");
	    
	    pageProducts.assertLoginProducts();
	}
	
	@Test(priority = 1)
	public void loginNoOK() throws InvalidFormatException, IOException, InterruptedException {
		
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.login("user", "sauce");
		
		CaptureEvidence.takeAScreenshot(driver, evidenceRoute + temporaryNameImg , evidenceRoute + documentName, "Login NO OK");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	    wait.until(ExpectedConditions.elementToBeClickable(By.tagName("h3"))); 
	    
		pageLogon.assertLogonPage();   
	}
	
	@AfterMethod
	public void closePage(){
		driver.close();
	}
}
