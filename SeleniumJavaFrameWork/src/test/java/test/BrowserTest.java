package test;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import config.Properties_File;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.Login_MyStore;
import pages.MyStore;
import wrapper.Excel_Methods;
import wrapper.Wrapper_Methods;

public class BrowserTest {

	public static void main(String[] args) throws Exception {
		WebDriver driver = null;
		String strTestName = "TC-1";
		String strUrl = Properties_File.getProperties("URL");
		
	
		
		Wrapper_Methods wm = new Wrapper_Methods(driver, strTestName);
		Excel_Methods em = new Excel_Methods(strTestName);
		String ProjectPath = System.getProperty("user.dir");
		//String strBrowser = em.getCellValueString(1, 0);
		String strBrowser = "edge";
		String stxtboxvalue =em.getCellValueString(1, 2);
		
		driver = wm.setBrowser(strBrowser);
		wm.LaunchApp(driver,strUrl);	
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);
		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
		Thread.sleep(1000);
		
		wm.CloseApp(driver);;
		
	

	}

}
