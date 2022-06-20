import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SingleDriver {
	private static SingleDriver sd =null;
	public WebDriver driver;
	
	private SingleDriver(){
	}
	
	public static SingleDriver getInstance(){
		if (sd == null){
			sd =new SingleDriver();
		}
		
		return sd;
		
	}
	public WebDriver driverinit(){
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
		
	}
	


}
