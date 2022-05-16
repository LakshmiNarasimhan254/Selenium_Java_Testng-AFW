package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import config.Properties_File;
import pages.Login_MyStore;
import pages.MyStore;
import wrapper.Excel_Methods;
import wrapper.Wrapper_Methods;

public class TestNg_ListenerTC2 {
	WebDriver driver = null;
	static String strTestName = "TC-1";
	String ProjectPath = System.getProperty("user.dir");
	Wrapper_Methods wm = new Wrapper_Methods(driver, strTestName);
	Excel_Methods em = new Excel_Methods(strTestName);
	String strBrowser = em.getCellValueString(1, 0);
	String stxtboxvalue = em.getCellValueString(1, 2);	

	@BeforeTest
	public void testSetup() {
		String strUrl = Properties_File.getProperties("URL");	

		driver = wm.setBrowser(strBrowser);
		wm.LaunchApp(driver,strUrl);
	}
	@Test
	public void TC1(){
		System.out.println("Test-4");
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
	}
	
	@Test
	public void TC2(){
		System.out.println("Test-4");
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
		Assert.assertTrue(false);
		
	}
	
	@Test(dependsOnMethods = "TC2")
	public void TC3(){
		System.out.println("Test-6");
		MyStore mystore = new MyStore(driver, strTestName);		
		mystore.ClickSignin(wm);		
		Login_MyStore loginmystore = new Login_MyStore(driver, strTestName);		
		loginmystore.EnterTxtCreateEmail(wm, stxtboxvalue);
		loginmystore.ClickCreateAccount(wm);
		
	}
	@AfterTest
	public void tearDown(){		
		wm.CloseApp(driver);;
	}



}


