package testLogin;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import pages.PageLogin;
import utilities.CaptureEvidence;
import utilities.DatosExcel;

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
	
	@Test(dataProvider = "Datos Login Excel")
	public void loginOk(String user, String pass) throws InvalidFormatException, IOException, InterruptedException {
		
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.login(user, pass);
		pageLogin.clickLogin();
		
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		
		try { // Caso Negativo
			
			Assert.assertFalse(driver.findElement(By.tagName("h3")).getTagName().isEmpty());
			CaptureEvidence.writeTitle(evidenceRoute + documentName, "Documento de Evidencias", 20);
			CaptureEvidence.takeAScreenshot(driver, evidenceRoute + temporaryNameImg , evidenceRoute + documentName, "Login No Ok");
			
		} catch (Exception e) {
			// Caso Positivo
			Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		}
	}
	
	@DataProvider(name = "Datos Login Excel")
	public Object[][] userDataExcel() throws Exception{
		return DatosExcel.leerExcel("..\\Login\\UserData\\DataProviderExcel24Ago2022.xlsx", "Hoja1");
	}
	
	/*@DataProvider(name = "Datos Login Ok")
	public Object[][] userData() {
		Object data[][] = new Object[4][2];
		
		data[0][0] = "standard_user";
		data[0][1] = "secret_sauce";
		
		data[1][0] = "locked_out_user";
		data[1][1] = "secret_sauce";
		
		data[2][0] = "problem_user";
		data[2][1] = "secret_sauce";
		
		data[3][0] = "performance_glitch_user";
		data[3][1] = "secret_sauce";
		
		return data;
	}*/
	
	@AfterMethod
	public void closePage(){
		driver.close();
	}
}
