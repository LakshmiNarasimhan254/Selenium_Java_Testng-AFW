package testNG;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class SecondTest {
	String baseUrl = "https://www.google.com/";
	WebDriver driver;

	@Test
	public void f() {
		//Once the Driver (browser) is opened. Navigate to BaseURL
		System.out.println("This message is displayed during the test");
		driver.get(baseUrl);
		String Title = driver.getTitle();
		Assert.assertEquals(Title, "Google");


	}
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("This message is displayed before the test");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();


	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("This message is displayed after the test");
	
		driver.quit();
		driver.close();
	}

}
