package testLogin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.PageLogin;

public class Test_Failed {
	
	private String url = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
	private String chromeDriver = "..\\LoginFailed\\Driver\\chromedriver.exe";
	private String fireFoxDriver = "..\\LoginFailed\\Driver\\geckodriver.exe";
	private WebDriver driver;
	
	
	@BeforeTest
	@Parameters("navigetor")
	public void setUs(String navigetor) {
		
		if (navigetor.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromeDriver);
			driver = new ChromeDriver();
		} else {
			System.setProperty("webdriver.gecko.driver", fireFoxDriver);
			driver = new FirefoxDriver();	
		}
		
		driver.manage().window().maximize();
		driver.navigate().to(url);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body.authentication.hide-left-column.hide-right-column.lang_en:nth-child(2) div.columns-container div.container div.row:nth-child(3) div.center_column.col-xs-12.col-sm-12 div.row div.col-xs-12.col-sm-6:nth-child(1) form.box div.form_content.clearfix div.submit:nth-child(4) button.btn.btn-default.button.button-medium.exclusive:nth-child(2) > span:nth-child(1)")));
	    
	    Assert.assertEquals(driver.getCurrentUrl(), "http://automationpractice.com/index.php?controller=authentication&back=my-account");
	    Assert.assertEquals(driver.getTitle(), "Login - My Store");
	}
	
	@Test(description= "CP01 - Crear Cuenta - emial Vacio" ,priority=1)
	public void signUpEmpty() {	
		driver.findElement(By.cssSelector("#email_create")).sendKeys("");
		driver.findElement(By.cssSelector("#SubmitCreate")).click();
		
		Assert.assertNotNull(driver.findElement(By.cssSelector("#create_account_error")));
	}
	
	
	@Test(description= "CP02 - Ya Registrado emial o contraseña vacios", priority=2)
	public void alreadyRegisteredEmpty() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("", "");
		
		Assert.assertNotNull(driver.findElement(By.cssSelector("body.authentication.hide-left-column.hide-right-column.lang_en:nth-child(2) div.columns-container div.container div.row:nth-child(3) div.center_column.col-xs-12.col-sm-12 > div.alert.alert-danger")));
	}
	
	@Test(description= "CP03 - Ya Registrado emial o contraseña incorrecta", priority=3)
	public void alreadyRegisteredFailed() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login("correoAgo2022@mailinator.com", "12345");
		
		Assert.assertNotNull(driver.findElement(By.cssSelector("body.authentication.hide-left-column.hide-right-column.lang_en:nth-child(2) div.columns-container div.container div.row:nth-child(3) div.center_column.col-xs-12.col-sm-12 > div.alert.alert-danger")));
	}
	
	@AfterSuite
	public void ClosePage() {
		driver.close();	
	}
}
