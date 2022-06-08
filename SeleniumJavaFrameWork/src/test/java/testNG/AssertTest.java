package testNG;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AssertTest {
	
	WebDriver driver;
	
	@Test (priority = 0)
	public void CloseBrowser() {
		driver.close();
		Reporter.log("Driver Closed After Testing");
	}
	
	@Test (priority = -1)
	public void OpenBrowser() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		    Reporter.log("This test verifies the current selenium compatibility with TestNG by launching the chrome driver");
	        Reporter.log("Launching Google Chrome Driver version 81 for this test"); 
	        
	        driver.get("http://automationpractice.com/index.php");
	        
	        Reporter.log("The website used was AutomationPractice for this test", true);
	        String expectedTitle = "My Stores";
	        String originalTitle = driver.getTitle();
	        Assert.assertEquals(originalTitle, expectedTitle, "Not Matching");
  }
}