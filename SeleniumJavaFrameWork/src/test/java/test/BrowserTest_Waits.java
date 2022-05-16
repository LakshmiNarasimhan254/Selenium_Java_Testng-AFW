package test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BrowserTest_Waits {

	public static void main(String[] args){
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		/**Implicit Wait **/ 
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		driver.get("http://automationpractice.com/index.php");
		
		
		/**explicit  Wait **/ 
		//WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign in')]")));
		//element.click();

		/**fluent  Wait **/ 
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(30))
				  .pollingEvery(Duration.ofSeconds(5))
				  .ignoring(NoSuchElementException.class);
		
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign in')]")));
		element.click();
		
		
		//driver.findElement(By.xpath("//a[contains(text(),'Sign-in')]")).click();
	}

}
